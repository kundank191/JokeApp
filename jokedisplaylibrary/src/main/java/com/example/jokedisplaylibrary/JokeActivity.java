package com.example.jokedisplaylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class JokeActivity extends AppCompatActivity {

    public static String INTENT_KEY_JOKES = "1012";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        ArrayList<String> jokes = intent.getStringArrayListExtra(INTENT_KEY_JOKES);

        TextView jokeTV = findViewById(R.id.joke_tv);
        jokeTV.setText(jokes.toString());

    }
}
