package com.jones.parsedata;

import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    String url = "https://www.google.com";
    String apiUrl = "https://jsonplaceholder.typicode.com/comments";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        textView = findViewById(R.id.text_view);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl,
                null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    Log.d("JSON", "onCreate: " + jsonObject.get("email"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
                , error -> Log.d("JSON", "onCreate: Failed!"));
        queue.add(jsonArrayRequest);

        getString(queue);

    }


    private void getString(RequestQueue queue) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Log.d("Referral", "onCreate: " + response.substring(0,500)),
                error -> Log.d("Referral", "onCreate: This request is invalid"));
        queue.add(stringRequest);
    }

}