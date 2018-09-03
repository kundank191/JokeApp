package com.udacity.gradle.builditbigger.backend;

import java.util.ArrayList;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private ArrayList<String> jokes;

    public ArrayList<String> getJokes() {
        return jokes;
    }

    public void setJokes(ArrayList<String> data) {
        jokes = data;
    }
}