package com.jones.truecitizen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;
import com.jones.truecitizen.databinding.ActivityMainBinding;
import com.jones.truecitizen.model.Question;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {
            //create/instantiate question objects
            new Question(R.string.question_amendments, false), //correct: 27
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
            //and add more!

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setQuestion(questionBank[currentQuestionIndex]);

        binding.nextButton.setOnClickListener(view -> {
            currentQuestionIndex++;
            updateQuestion();
        });

        binding.prevButton.setOnClickListener(view -> {
            currentQuestionIndex--;
            updateQuestion();
        });

        binding.trueButton.setOnClickListener(view -> {
            if (checkAnswer(true))
                currentQuestionIndex++;
            updateQuestion();
        });

        binding.falseButton.setOnClickListener(view -> {
            if (checkAnswer(false))
                currentQuestionIndex++;
            updateQuestion();
        });
    }

    private void updateQuestion() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < questionBank.length) {
            if (currentQuestionIndex == 0) {
                binding.nextButton.setEnabled(true);
                binding.prevButton.setEnabled(false);
            } else {
                binding.prevButton.setEnabled(true);
            }
            if (currentQuestionIndex == questionBank.length - 1) {
                binding.nextButton.setEnabled(false);
                binding.prevButton.setEnabled(true);
            } else {
                binding.nextButton.setEnabled(true);
            }
            binding.setQuestion(questionBank[currentQuestionIndex]);
        }
    }

    private boolean checkAnswer(boolean userChoice) {
        boolean isCorrect = questionBank[currentQuestionIndex].isAnswerTrue();
        int msgId;

        if (userChoice == isCorrect) msgId = R.string.correct_answer;
        else msgId = R.string.wrong_answer;

        Snackbar.make(binding.image, msgId, Snackbar.LENGTH_LONG).show();
        return userChoice==isCorrect;
    }

}