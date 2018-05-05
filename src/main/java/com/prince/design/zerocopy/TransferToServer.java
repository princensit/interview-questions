package com.prince.design.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

// https://www.ibm.com/developerworks/linux/library/j-zerocopy/
public class TransferToServer {

    private ServerSocketChannel listener = null;

    public static void main(String[] args) {
        TransferToServer toServer = new TransferToServer();
        toServer.setup();
        toServer.readData();
    }

    private void setup() {
        InetSocketAddress socketAddress = new InetSocketAddress(9026);

        try {
            listener = ServerSocketChannel.open();
            ServerSocket ss = listener.socket();
            ss.setReuseAddress(true);
            ss.bind(socketAddress);
            System.out.println("Listening on port : " + socketAddress.toString());
        } catch (IOException e) {
            System.out.println("Failed to bind, is port : "
                    + socketAddress.toString()
                    + " already in use ? Error Msg : "
                    + e.getMessage());
            e.printStackTrace();
        }
    }

    private void readData() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        try {
            while (true) {
                SocketChannel conn = listener.accept();
                System.out.println("Accepted : " + conn);
                conn.configureBlocking(true);
                int nread = 0;
                while (nread != -1) {
                    try {
                        nread = conn.read(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                        nread = -1;
                    }

                    byteBuffer.rewind();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
