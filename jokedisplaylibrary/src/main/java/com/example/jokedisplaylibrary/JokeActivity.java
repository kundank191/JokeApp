package com.example.jokedisplaylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import java.util.ArrayList;

public class JokeActivity extends AppCompatActivity {

    public static String INTENT_KEY_JOKES = "1012";
    private JokeAdapter jokeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        ArrayList<String> jokes = intent.getStringArrayListExtra(INTENT_KEY_JOKES);

        RecyclerView recyclerView = findViewById(R.id.joke_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        jokeAdapter = new JokeAdapter(this,jokes);
        recyclerView.setAdapter(jokeAdapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

    }
}
