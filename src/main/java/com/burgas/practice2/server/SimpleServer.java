package com.burgas.practice2.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The type Simple server.
 */
public class SimpleServer {

    private static final int PORT = 8088;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket accept = serverSocket.accept();
             PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(accept.getOutputStream()));){

            printWriter.println("Message for client");
            printWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
