package com.jones.guessname;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowGuess extends AppCompatActivity {

    private TextView showGuessTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        showGuessTextView = findViewById(R.id.received_text_view);
        Bundle extra = getIntent().getExtras();

//        String value = getIntent().getStringExtra("guess");

//        if (value != null)
//            showGuessTextView.setText(value);

        if (extra != null) {
            showGuessTextView.setText(extra.getString("guess"));
        }

        showGuessTextView.setOnClickListener(view -> {
            Intent intent = getIntent();
            intent.putExtra("message_back", "From Second Activity");
            setResult(RESULT_OK, intent);
            finish();
        });

    }
}