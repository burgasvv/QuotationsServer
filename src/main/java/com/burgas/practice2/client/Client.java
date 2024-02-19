package com.burgas.practice2.client;

import com.burgas.practice2.utils.Util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

/**
 * The type Client.
 */
public class Client {

    private Socket socket;

    /**
     * Instantiates a new Client.
     *
     * @param socket the socket
     */
    public Client(Socket socket) {
        this.socket = socket;
    }

    /**
     * Simple constructor. Instantiates a new Client.
     */
    public Client() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), Util.PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts connection.
     *
     * @throws IOException the io exception
     */
    public void connect() throws IOException {
        ClientConnection clientConnection = new ClientConnection(socket);
        clientConnection.processConnection();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Client client = (Client) object;
        return Objects.equals(socket, client.socket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket);
    }

    @Override
    public String toString() {
        return STR."Client{socket=\{socket}\{'}'}";
    }
}
