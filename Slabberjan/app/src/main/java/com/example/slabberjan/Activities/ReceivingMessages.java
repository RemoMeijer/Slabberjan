package com.example.slabberjan.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class ReceivingMessages extends Thread{

    private final Socket socket;
    private final Handler messageHandler;

    public ReceivingMessages(Socket socket, Handler messageHandler) {
        this.socket = socket;
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = this.socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            while(true) {
                String message = dataInputStream.readUTF();
                System.out.println("Got message: " + message);

                Message dataForMainUI = this.messageHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("message", message);
                dataForMainUI.setData(bundle);
                this.messageHandler.sendMessage(dataForMainUI);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
