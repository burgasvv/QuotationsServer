package com.burgas.practice2.client.main;

import com.burgas.practice2.client.Client;

import java.io.IOException;

/**
 * The type Client main 3.
 */
public class ClientMain3 {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        Client client = new Client();
        client.connect();
    }
}
