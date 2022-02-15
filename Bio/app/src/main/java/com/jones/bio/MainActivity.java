package com.jones.bio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jones.bio.data.Bio;
import com.jones.bio.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Bio bio = new Bio();
//    private EditText inputText;
//    private Button done;
//    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        inputText = findViewById(R.id.inputText);
//        done = findViewById(R.id.button);
//        textView = findViewById(R.id.textView);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        bio.setName("APJ Abdul Kalam");
        binding.setBio(bio);

        binding.doneButton.setOnClickListener(view ->  {
            if (binding.inputText.getText() != null) {
                bio.setAchievement("New Achievement : " + binding.inputText.getText());
                binding.textView.setVisibility(View.VISIBLE);
//                binding.textView.setText("New Achievement : " + binding.inputText.getText());
                binding.invalidateAll();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethodManager != null;
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
    }
}