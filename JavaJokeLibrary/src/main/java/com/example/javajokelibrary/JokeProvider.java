package com.example.javajokelibrary;

import java.util.LinkedList;
import java.util.List;

public class JokeProvider {

    /**
     *
     * @return a list of Jokes
     */
    public static List<String> getJokes(){
        List<String> jokes = new LinkedList<>();
        jokes.add("Why is peter pan always flying \n Because he neverlands");
        jokes.add("What happens to a frog's car when it breaks down? \n It gets toad away");
        jokes.add("What did the duck say when he bought lipstick? \n Put it on my bill");
        jokes.add("When a dad drives past a graveyard: Did you know that's a popular cemetery? \n Yep, people are just dying to get in there");
        jokes.add("What do you call a belt made out of watches? \n A waist of time.");
        jokes.add("To understand what recursion is... \n You must first understand what recursion is");
        jokes.add("A ham sandwhich walks into a bar and orders a beer. The bartender says... \n I'm sorry, we don't serve food here");
        jokes.add("What's the best time to go to the dentist? \n Tooth hurty.");
        jokes.add("Why can't bicycles stand on their own? \n hey are two tired");
        return jokes;
    }
}
