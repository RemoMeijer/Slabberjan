package com.example.slabberjan.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.slabberjan.R;

public class MainActivity extends AppCompatActivity {

    private Button sendButton;
    private EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sendButton = findViewById(R.id.button);
        this.nameField = findViewById(R.id.device_name);

        this.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameField.getText().toString();

                //If a name is filled in, pass it on to the next activity
                if(!name.isEmpty()) {
                    Intent switchToMainScreen = new Intent(MainActivity.this, MainScreen.class);
                    switchToMainScreen.putExtra("name", name);
                    startActivity(switchToMainScreen);

                }
            }
        });
    }


}