package com.example.slabberjan.main.ServerCommunication;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendingMessage extends Thread {
    private Socket socket;
    private String name;
    private String message;


    public SendingMessage(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
        this.message = "";
    }

    @Override
    public void run() {
            try {
                //Create output stream and send message
                OutputStream outputStream = this.socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeUTF(this.name + "," + this.message);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    //Sets the message to be sent
    public void setMessage(String message) {
        this.message = message;
    }
}
