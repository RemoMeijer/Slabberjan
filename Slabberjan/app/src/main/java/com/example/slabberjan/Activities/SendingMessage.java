package com.example.slabberjan.Activities;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;



public class SendingMessage extends Thread {


    private final String name;
    private String message;
    private final Socket socket;


    public SendingMessage(Socket socket, String name) {
        this.name = name;
        this.message = "no message";
        this.socket = socket;

    }

    @Override
    public void run() {
        try {

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(this.name + "," + message);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setMessage(String message) {
        this.message = message;
    }

}
