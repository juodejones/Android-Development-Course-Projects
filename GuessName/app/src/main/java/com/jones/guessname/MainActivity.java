package com.jones.guessname;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button showGuess;
    private EditText enterGuess;
    private final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showGuess = findViewById(R.id.guess_button);
        enterGuess = findViewById(R.id.enter_guess);

        showGuess.setOnClickListener(view -> {
            String guess = enterGuess.getText().toString().trim();

            if (!guess.isEmpty()) {
                Intent intent = new Intent(this, ShowGuess.class);
                intent.putExtra("guess", guess);
                intent.putExtra("name", "bond");
                intent.putExtra("age", 32);
//                startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                Toast.makeText(this,R.string.toast_string,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            String message = data.getStringExtra("message_back");
            
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}