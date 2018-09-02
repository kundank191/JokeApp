package com.udacity.gradle.builditbigger.backend;

import java.util.List;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private List<String> jokes;

    public List<String> getJokes() {
        return jokes;
    }

    public void setJokes(List<String> data) {
        jokes = data;
    }
}