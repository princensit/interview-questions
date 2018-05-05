package com.prince.design.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

// https://www.ibm.com/developerworks/linux/library/j-zerocopy/
public class TransferToClient {

    public static void main(String[] args) throws IOException {
        TransferToClient toClient = new TransferToClient();
        toClient.testSendfile();
    }

    public void testSendfile() throws IOException {
        String host = "localhost";
        int port = 9026;

        SocketAddress socketAddress = new InetSocketAddress(host, port);

        try (SocketChannel socketChannel = SocketChannel.open()) {

            socketChannel.connect(socketAddress);
            socketChannel.configureBlocking(true);

            String fname = "sendfile/NetworkInterfaces.c";
            long fsize = 183678375L;

            FileChannel fileChannel = new FileInputStream(fname).getChannel();
            long start = System.currentTimeMillis();
            long curnset = fileChannel.transferTo(0, fsize, socketChannel);
            System.out.println("total bytes transferred --"
                    + curnset
                    + " and time taken in MS --"
                    + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
