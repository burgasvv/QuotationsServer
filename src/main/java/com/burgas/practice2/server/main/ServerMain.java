package com.burgas.practice2.server.main;

import com.burgas.practice2.server.Server;

import java.io.*;

public class ServerMain {

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.start();
    }
}
