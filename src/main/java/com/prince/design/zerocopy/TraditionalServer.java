package com.prince.design.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// https://www.ibm.com/developerworks/linux/library/j-zerocopy/
public class TraditionalServer {

    public static void main(String args[]) {
        int port = 2000;
        try (
                ServerSocket serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream())) {
            System.out.println("Server waiting for client on port " + serverSocket.getLocalPort());

            // server infinite loop
            while (true) {
                System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
                // print received data
                byte[] byteArray = new byte[4096];
                int nread = input.read(byteArray, 0, 4096);
                if (0 == nread) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
