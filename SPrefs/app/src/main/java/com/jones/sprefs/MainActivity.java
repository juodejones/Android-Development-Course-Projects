package com.jones.sprefs;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final String MESSAGE_ID = "message_prefs";

    private EditText enteredText;
    private TextView showText;
    private Button  changeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredText = findViewById(R.id.entered_text);
        showText = findViewById(R.id.show_text);
        changeButton = findViewById(R.id.change_button);

        changeButton.setOnClickListener(view -> {

            String message = enteredText.getText().toString().trim();

            if (!message.isEmpty()) {
                SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("message", message);

                editor.apply(); //saving to disk
            }

        });

        // getting back from sharedPreferences
        SharedPreferences getShareData = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        String value = getShareData.getString("message", "Nothing Yet");

        showText.setText(value);

    }

}
