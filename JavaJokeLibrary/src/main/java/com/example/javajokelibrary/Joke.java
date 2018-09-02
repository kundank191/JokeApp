package com.example.javajokelibrary;

import java.io.Serializable;

/**
 * Created by Kundan on 03-09-2018.
 * POJO for jokes
 */
public class Joke implements Serializable{

    private String jokeQuestion;
    private String jokeAnswer;

    public Joke(String jokeQuestion, String jokeAnswer) {
        this.jokeQuestion = jokeQuestion;
        this.jokeAnswer = jokeAnswer;
    }

    public String getJokeQuestion() {
        return jokeQuestion;
    }

    public void setJokeQuestion(String jokeQuestion) {
        this.jokeQuestion = jokeQuestion;
    }

    public String getJokeAnswer() {
        return jokeAnswer;
    }

    public void setJokeAnswer(String jokeAnswer) {
        this.jokeAnswer = jokeAnswer;
    }

}
