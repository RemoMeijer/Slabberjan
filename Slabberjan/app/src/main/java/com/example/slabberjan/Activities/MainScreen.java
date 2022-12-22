package com.example.slabberjan.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.slabberjan.R;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

public class MainScreen extends AppCompatActivity {

    String localhost = "192.168.11.50";
    SendingMessage sendingMessage;
    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");

        TextView nameTextView = findViewById(R.id.names);
        nameTextView.setText(name);
        TextView slabberJanToken = findViewById(R.id.slabber_jan_token);
        slabberJanToken.setText("");
        TextView allNamesTextView = findViewById(R.id.all_names);
        allNamesTextView.setText("");

        Handler messageFromServerHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {

                Bundle bundle = message.getData();
                String dataFromServer = bundle.getString("message");


                switch (dataFromServer) {
                    case ("1") :
                    case ("2") :
                    case ("3") :
                    case ("4") :
                    case ("5") :
                    case ("6") :
                    case ("7") :
                    case ("8") :
                    case ("9") :
                    case ("10") :
                        slabberJanToken.setText(dataFromServer);
                        break;
                    default:
                        allNamesTextView.setText(dataFromServer);
                }

                return true;
            }
        });

        SocketCreator socketCreator = new SocketCreator();
        socketCreator.execute(localhost, String.valueOf(8000));
        try {
            socket = socketCreator.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        sendingMessage = new SendingMessage(this.socket, nameTextView.getText().toString());
        sendMessage(name);

        ReceivingMessages receivingMessages = new ReceivingMessages(this.socket, messageFromServerHandler);
        receivingMessages.start();

    }




    private static class SocketCreator extends AsyncTask<String, Void, Socket> {

        @Override
        protected Socket doInBackground(String... params) {
            String serverIp = params[0];
            int serverPort = Integer.parseInt(params[1]);
            try {
                return new Socket(serverIp, serverPort);
            } catch (IOException e) {
                return null;
            }
        }
    }


    public void sendMessage(String message){

          sendingMessage.setMessage(message);
          sendingMessage.start();

    }
}