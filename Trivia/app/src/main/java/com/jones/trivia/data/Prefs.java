package com.jones.trivia.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;
    private final String HIGHEST_SCORE = "highest_score";
    private final String STATE = "trivia_state";

    public Prefs(Activity context) {
        this.preferences = context.getPreferences(Context.MODE_PRIVATE);
    }

    public boolean saveHighestScore(int score) {

        if (score > preferences.getInt(HIGHEST_SCORE, 0)) {
            preferences.edit().putInt(HIGHEST_SCORE, score).apply();
            return true;
        }

        return false;
    }

    public int getHighestScore() {
        return preferences.getInt(HIGHEST_SCORE, 0);
    }

    public void setState(int index) {
        preferences.edit().putInt(STATE, index).apply();
    }

    public int getState() {
        return preferences.getInt(STATE,0);
    }
}
