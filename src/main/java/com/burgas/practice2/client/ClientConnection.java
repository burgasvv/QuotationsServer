package com.burgas.practice2.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.*;

/**
 * Сlass for connecting and communicating with the server.
 */
public class ClientConnection {

    private Socket socket;
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Scanner scanner;
    private int quotationsLimit;
    private static final int CLIENT_TIMEOUT = 1000;

    /**
     * Constructor with parameters. Instantiates a new Client Connection.
     *
     * @param socket the socket
     */
    public ClientConnection(Socket socket) {

        try {
            this.socket = socket;
            printStream = new PrintStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(in);
            quotationsLimit = 5;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Send and receive messages.
     *
     * @throws IOException the io exception
     */
    public void processConnection() throws IOException {

        try {
            String answer = null;
            while (!"You logged in!".equals(answer)){

                String message = bufferedReader.readLine();
                out.println(message);

                if ("Server is overloaded. Try to connect later...".equals(message))
                    return;

                String choose = scanner.nextLine();
                printStream.println(choose);

                switch (choose){
                    case "1" -> {
                        out.println(bufferedReader.readLine());
                        String login = scanner.nextLine();
                        printStream.println(login);
                        out.println(bufferedReader.readLine());
                        String password = scanner.nextLine();
                        printStream.println(password);
                        answer = bufferedReader.readLine();
                        out.println(answer);
                    }
                    case "2" -> {
                        return;
                    }
                }
            }

            while (true) {

                out.println(bufferedReader.readLine());
                String choose = scanner.nextLine();

                printStream.println(choose);

                switch (choose) {
                    case "1" -> pingPong();
                    case "2" -> getRandomQuotation();
                    case "3"-> {
                        return;
                    }
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace(printStream.append(" Disconnect."));

        } finally {
            disconnect();
        }
    }

    /**
     * Disconnect from the server.
     *
     * @throws IOException the io exception
     */
    private void disconnect() throws IOException {
        if (printStream != null)
            socket.close();
        if (bufferedReader != null)
            bufferedReader.close();
        if (socket != null)
            socket.close();
        if (scanner != null)
            scanner.close();
    }

    /**
     * Launch Ping-Pong test.
     *
     * @throws IOException the io exception
     * @throws InterruptedException the interrupted exception
     */
    private void pingPong() throws IOException, InterruptedException {

        for (int i = 0; i < 10; i++) {
            printStream.println("PING");
            out.println(bufferedReader.readLine());
            Thread.sleep(CLIENT_TIMEOUT);
        }
        printStream.println("finish");
    }

    /**
     * Get random quotation.
     *
     * @throws IOException the io exception
     */
    private void getRandomQuotation() throws IOException {

        if (quotationsLimit < 0) {
            quotationsLimit = 0;
        }
        if (quotationsLimit == 0) {
            printStream.println(quotationsLimit);
            out.println(bufferedReader.readLine());
            socket.close();
        } else {
            printStream.println(quotationsLimit);
            out.println(bufferedReader.readLine());
        }

        quotationsLimit -= 1;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
