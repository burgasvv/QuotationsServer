package com.burgas.practice2.server;

import com.burgas.practice2.quotation.QuotationList;
import com.burgas.practice2.utils.Util;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.System.out;

/**
 * Class, created to receive clients.
 */
public class Server {

    private ServerSocket serverSocket;
    private QuotationList quotationList;
    /**
     * The client counter.
     */
    public int countClients = 0;

    /**
     * Simple constructor. Instantiates a new Server.
     */
    public Server() {

        try {
            serverSocket = new ServerSocket(Util.PORT);
            quotationList = new QuotationList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor with parameters. Instantiates a new Server.
     *
     * @param serverSocket the server socket
     */
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Starts the server and accepts client sockets.
     *
     * @throws IOException the io exception
     */
    public void start() throws IOException {

        out.println("Server capacity: 2 clients");

        try {
            while (!serverSocket.isClosed()) {

                if (countClients <= Util.CLIENT_LIMIT) {
                    Socket socket = serverSocket.accept();
                    clientLimitThread(socket);

                    countClients++;
                    if (countClients <= Util.CLIENT_LIMIT){

                        String dateTime = logConnectionInfo(socket);

                        ServerThread serverThread = new ServerThread(socket);
                        serverThread.start();

                        disconnectionThread(serverThread, socket, dateTime);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);

        } finally {
            serverSocket.close();
        }
    }

    /**
     * Gets connection info on server.
     *
     * @param socket accepted socket
     * @return dateTime
     */
    private String logConnectionInfo(Socket socket) {

        String dateTime = getDateTime();
        out.println(STR."Client \{socket.getInetAddress().getHostName()} CONNECTED");
        out.println(STR."Connection time: \{dateTime}");
        out.println(quotationList.getQuotations());
        out.println(STR."Clients: \{countClients}");

        return dateTime;
    }

    /**
     * Checking for client limit in thread and sending a message to client.
     *
     * @param socket get the accepted socket to make temp socket for message
     */
    private void clientLimitThread(Socket socket) {

        Thread threadLimit = new Thread(() -> {

            if (countClients > Util.CLIENT_LIMIT) {

                out.println("Server is OVERLOADED!");
                try (Socket tempSocket = socket;
                     PrintStream printStream = new PrintStream(tempSocket.getOutputStream())) {
                    printStream.println("Server is overloaded. Try to connect later...");
                    countClients--;

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        threadLimit.start();
    }

    /**
     * Launch disconnection thread with join after connection.
     *
     * @param serverThread the server thread
     * @param socket the accepted socket
     * @param dateTime get disconnect time
     */
    private void disconnectionThread(ServerThread serverThread, Socket socket, String dateTime) {

        Thread thread = new Thread(() -> {
            try {
                serverThread.join();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            } finally {
                if (socket.isClosed()) {
                    out.println(STR."Client \{socket.getInetAddress().getHostName()} DISCONNECTED");
                    out.println(STR."Disconnection time: \{dateTime}");
                    countClients--;
                    out.println(STR."Clients: \{countClients}");
                }
            }
        });

        thread.start();
    }

    /**
     * Gets connect and disconnect time.
     *
     * @return datetime format.
     */
    private String getDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss"));
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
