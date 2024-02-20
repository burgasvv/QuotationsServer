package com.burgas.practice2.client.main;

import com.burgas.practice2.client.Client;

import java.io.IOException;

public class ClientMain1 {

    public static void main(String[] args) throws IOException {

        Client client = new Client();
        client.connect();
    }
}
