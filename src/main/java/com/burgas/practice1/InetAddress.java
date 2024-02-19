package com.burgas.practice1;

import java.io.IOException;
import java.net.Socket;

import static java.lang.System.*;

/**
 * Сведения об IP адресе компьютера, интернет-ресурсы
 */
public class InetAddress {

    /**
     * method description
     * @param args parameters for method
     */
    public static void main(String[] args) {

        try (Socket simpleClient = new Socket()){

            java.net.InetAddress currentIp = java.net.InetAddress.getLocalHost();
            java.net.InetAddress name = java.net.InetAddress.getByName("google.com");

            out.println(STR."Current IP: \{currentIp}");
            out.println(STR."Google IP: \{name}");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
