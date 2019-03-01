package com.dcgabriel.simplesms;

import android.Manifest;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText recipientEditText;
    EditText messageEditText;
    CardView sendCardView;

    String recipientString;
    String messageString;

    private final int SMS_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipientEditText = findViewById(R.id.to_editText);
        messageEditText = findViewById(R.id.message_editText);
        sendCardView = findViewById(R.id.send_cardView);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_REQUEST_CODE);

    }


    public void sendSMS(View view) {
        //todo handle check for permission
        recipientString = recipientEditText.getText().toString();
        messageString = messageEditText.getText().toString();

        if (recipientString.isEmpty()) {
            Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT);
            return;
        }
        if (messageString.isEmpty()) {
            Toast.makeText(this, "Enter message", Toast.LENGTH_SHORT).show();
            return;
        }

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(recipientString, null, messageString, null, null); //todo create intents for sentIntent, delivaryIntent
        Toast.makeText(this, "Sending", Toast.LENGTH_SHORT).show();
    }

}
