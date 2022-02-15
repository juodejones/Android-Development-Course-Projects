package com.jones.trivia;

import android.graphics.Color;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.jones.trivia.data.AnswerListAsyncResponse;
import com.jones.trivia.data.Prefs;
import com.jones.trivia.data.Repository;
import com.jones.trivia.databinding.ActivityMainBinding;
import com.jones.trivia.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private Prefs prefs;

    List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        prefs = new Prefs(MainActivity.this);
        binding.highestScoreView.setText(String.valueOf(prefs.getHighestScore()));

        questionList = new Repository().getQuestions(questionArrayList -> {
            MainActivity.this.updateCounter(questionArrayList);
            binding.questionText.setText(questionList.get(currentQuestionIndex).getQuesText());
        });

        binding.nextButton.setOnClickListener(click -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
            updateQuestion();
        });

        binding.trueButton.setOnClickListener(click -> checkAnswer(true));
        binding.falseButton.setOnClickListener(click -> checkAnswer(false));

    }

    private void updateQuestion() {
        binding.questionText.setText(questionList.get(currentQuestionIndex).getQuesText());
        updateCounter((ArrayList<Question>) questionList);
    }

    private void updateCounter(ArrayList<Question> questions) {
        binding.questionNo.setText(String.format(getString(R.string.quest_no), currentQuestionIndex+1, questions.size()));
    }

    private void checkAnswer(boolean userChoice) {
        boolean answer = questionList.get(currentQuestionIndex).isAnswerTrue();
        String msgId;

        if (userChoice == answer){
            msgId = getString(R.string.correct_answer);
            fadeAnimation();
            incScore();
        } else {
            msgId = getString(R.string.wrong_answer);
            shakeAnimation();
            deductScore();
        }

        Snackbar.make(binding.cardView, msgId, Snackbar.LENGTH_SHORT).show();
        currentQuestionIndex++;
        updateQuestion();

    }

    private void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake_anim);
        binding.cardView.startAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionText.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionText.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void fadeAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        binding.cardView.startAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionText.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionText.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void incScore() {
        score += 100;
        binding.scoreView.setText(String.valueOf(score));
    }

    private void deductScore() {
        score -= 50;
        binding.scoreView.setText(String.valueOf(score));
    }

    @Override
    protected void onPause() {
        prefs.saveHighestScore(score);
        super.onPause();
    }

}
