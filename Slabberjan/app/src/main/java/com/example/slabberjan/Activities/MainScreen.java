package com.example.slabberjan.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.slabberjan.R;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutionException;


public class MainScreen extends AppCompatActivity {

    String localhost = "192.168.11.50";
    Socket socket;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        initServerConnection();

        Intent intent = getIntent();
        final String intentName = intent.getStringExtra("name");
        this.name = intentName;

        //Init textviews
        TextView nameTextView = findViewById(R.id.names);
        nameTextView.setText(intentName);
        TextView slabberJanToken = findViewById(R.id.slabber_jan_token);
        slabberJanToken.setText("");
        TextView allNamesTextView = findViewById(R.id.all_names);
        allNamesTextView.setText("");
        TextView status = findViewById(R.id.status);
        status.setText("Waiting for other players");

        //Button init
        Button tradeButton = findViewById(R.id.trade_button);
        tradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendingMessage.setMessage("Trade button clicked with socket: " + socket);

            }
        });

        //Handles messages from server
        Handler messageFromServerHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {

                Bundle bundle = message.getData();
                String dataFromServer = bundle.getString("message");

                switch (dataFromServer) {
                    case ("0") :
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

                    case ("Players Turn!") :
                        status.setText("Your turn!");
                        break;
                    default:
                        allNamesTextView.setText(dataFromServer);
                }

                return true;
            }
        });

        //Init message to server containing: "connecting"
        SendingMessage sendingMessage = new SendingMessage(this.socket, this.name);
        sendingMessage.setMessage("Connecting");
        sendingMessage.start();

        //Init of the receiving messages
        ReceivingMessages receivingMessages = new ReceivingMessages(this.socket, messageFromServerHandler);
        receivingMessages.start();

    }

    //Initialisation of the server
    private void initServerConnection(){
        //Create socket
        SocketCreator socketCreator = new SocketCreator();
        socketCreator.execute(this.localhost, String.valueOf(8000));
        try {
            //Get socket
            this.socket = socketCreator.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    //Creates a socket to talk to server in an AsyncTask
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
}