package com.example.autopilot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText ipEdittext;
    EditText portEdittext;
    Button activateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipEdittext = (EditText) findViewById(R.id.editTextIP);
        portEdittext = (EditText) findViewById(R.id.editTextPort);
        activateButton = (Button) findViewById(R.id.Button);


        activateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ipEdittext.getText().toString();
                int port = Integer.parseInt(portEdittext.getText().toString());

                TCPThread thread = new TCPThread(ip, port, true);
                thread.start();

            }
        });

    }






}
