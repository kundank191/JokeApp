package com.example.javajokelibrary;

import java.util.LinkedList;
import java.util.List;

public class JokeProvider {

    /**
     *
     * @return a list of Jokes
     */
    public static List<Joke> getJokes(){
        List<Joke> jokes = new LinkedList<>();
        jokes.add(new Joke("Why is peter pan always flying?","Because he neverlands"));
        jokes.add(new Joke("What happens to a frog's car when it breaks down?","It gets toad away"));
        jokes.add(new Joke("What did the duck say when he bought lipstick?","Put it on my bill"));
        jokes.add(new Joke("When a dad drives past a graveyard: Did you know that's a popular cemetery?","Yep, people are just dying to get in there"));
        jokes.add(new Joke("What do you call a belt made out of watches?","A waist of time."));
        jokes.add(new Joke("To understand what recursion is...","You must first understand what recursion is"));
        jokes.add(new Joke("A ham sandwhich walks into a bar and orders a beer. The bartender says...","I'm sorry, we don't serve food here"));
        jokes.add(new Joke("What's the best time to go to the dentist?","Tooth hurty."));
        jokes.add(new Joke("Why can't bicycles stand on their own?","hey are two tired"));
        return jokes;
    }
}
