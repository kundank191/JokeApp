package com.example.javajokelibrary;

import java.util.LinkedList;
import java.util.List;

public class JokeProvider {

    /**
     *
     * @return a list of Jokes
     */
    public List<Joke> getJokes(){
        List<Joke> jokes = new LinkedList<>();
        jokes.add(new Joke("",""));
        return jokes;
    }
}
