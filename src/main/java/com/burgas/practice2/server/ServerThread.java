package com.burgas.practice2.server;

import com.burgas.practice2.quotation.QuotationList;
import com.burgas.practice2.server.user.UserList;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

import static java.lang.System.out;

/**
 * The type Server thread.
 */
public class ServerThread extends Thread {

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintStream printStream;
    private UserList userList;

    /**
     * Instantiates a new Server thread.
     *
     * @param socket the socket
     * @throws IOException the io exception
     */
    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printStream = new PrintStream(socket.getOutputStream());
            userList = new UserList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        super.run();

        try {
            while (true) {

                printStream.println("Choose an option: | 1. Enter login and password | 2. Quit");

                String choose = bufferedReader.readLine();
                switch (choose) {
                    case "1" -> {

                        printStream.println("login: ");
                        String login = bufferedReader.readLine();
                        printStream.println("password: ");
                        String password = bufferedReader.readLine();

                        boolean anyMatch = userList.getUserList().stream()
                                .anyMatch(user -> user.getLogin().equals(login) && user.getPassword().equals(password));

                        if (!anyMatch) {
                            printStream.println("Wrong login or password...");

                        } else {
                            printStream.println("You logged in!");

                            while (true) {

                                printStream.println("Choose an option: | 1. Ping - Pong  | 2. Get random quotation | 3. Disconnect");
                                choose = bufferedReader.readLine();
                                switch (choose) {
                                    case "1" -> pingPong();
                                    case "2" -> getRandomQuotation();
                                    case "3" -> {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    case "2" -> {
                        return;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace(printStream.append(" Disconnect."));

        } finally {
            disconnect();
        }
    }

    /**
     * Test connection with client.
     *
     * @throws IOException the io exception
     */
    private void pingPong() throws IOException {

        int counter = 1;
        String message;

        while (!"finish".equals(message = bufferedReader.readLine())) {

            if ("PING".equals(message))
                printStream.println(STR."PONG \{counter}");

            out.println(STR."PING-PONG \{counter} from \{InetAddress.getLocalHost()}");
            counter++;
        }
    }

    /**
     * Gets random quotation.
     *
     * @throws IOException the io exception
     */
    public void getRandomQuotation() throws IOException {

        String quotations = bufferedReader.readLine();
        if (Integer.parseInt(quotations) <= 0) {
            printStream.println("You have exceeded the limit of available quotations. DISCONNECTED");
            socket.close();
        } else {
            QuotationList quotationList = new QuotationList();
            printStream.println(quotationList.getQuotations().get(new Random().nextInt(0, 19)));
        }
    }

    /**
     * Disconnect from server
     */
    public void disconnect() {
        if (printStream != null) {
            printStream.close();
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }
}
