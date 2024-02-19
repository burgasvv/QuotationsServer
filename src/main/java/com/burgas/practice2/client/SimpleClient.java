package com.burgas.practice2.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

import static java.lang.System.*;

/**
 * The type Simple client.
 */
public class SimpleClient {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = null;
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 8088)){

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(STR."Message related: \{reader.readLine()}");

        } catch (IOException e) {
            throw new RuntimeException(e);

        } finally {
            Objects.requireNonNull(reader).close();
        }
    }
}
