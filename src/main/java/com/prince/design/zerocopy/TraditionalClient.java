package com.prince.design.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

// https://www.ibm.com/developerworks/linux/library/j-zerocopy/
public class TraditionalClient {

    public static void main(String[] args) {
        String server = "localhost";
        int port = 2000;

        String fname = "sendfile/NetworkInterfaces.c";
        try (
                Socket socket = new Socket(server, port);
                FileInputStream inputStream = new FileInputStream(fname);
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            long start = System.currentTimeMillis();
            byte[] b = new byte[4096];
            long read;
            long total = 0;
            while ((read = inputStream.read(b)) >= 0) {
                total = total + read;
                output.write(b);
            }
            System.out.println("bytes send --" + total + " and total time --" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
