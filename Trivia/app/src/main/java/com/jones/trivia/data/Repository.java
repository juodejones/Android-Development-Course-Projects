package com.jones.trivia.data;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jones.trivia.controller.AppController;
import com.jones.trivia.model.Question;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    int listSize;
    ArrayList<Question> questionArrayList = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public ArrayList<Question> getQuestions(final AnswerListAsyncResponse callBack) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
        response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    questionArrayList.add(new Question(response.getJSONArray(i).getString(0),
                            response.getJSONArray(i).getBoolean(1)));
//                    Log.d("Repo", "getQuestions: " + questionArrayList.get(i).getQuesText());
                } catch (JSONException e) {
                }
            }

            if (callBack != null) {
                callBack.processFinished(questionArrayList);

            }
        }, error -> {

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questionArrayList;
    }

    public void getSize() {
        Log.d("TAG", "getSize: " + questionArrayList.size());
    }
}
