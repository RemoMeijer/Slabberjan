package com.example.slabberjan.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.slabberjan.R;

public class MainActivity extends AppCompatActivity {

    Button sendButton;
    EditText nameField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.button);
        nameField = findViewById(R.id.device_name);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameField.getText().toString();
                //If a name is filled in, pass it on to the server
                if(!name.isEmpty()) {
                    Intent switchToMainScreen = new Intent(MainActivity.this, MainScreen.class);
                    switchToMainScreen.putExtra("name", name);
                    startActivity(switchToMainScreen);

                }
            }
        });
    }


}