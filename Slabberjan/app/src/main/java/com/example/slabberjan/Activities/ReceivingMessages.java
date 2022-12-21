package com.example.slabberjan.Activities;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class ReceivingMessages extends Thread{

    private Socket socket;
    private CountDownLatch socketLatch;

    public ReceivingMessages(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            while(true) {
                String message = dataInputStream.readUTF();
                System.out.println("Got message: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
