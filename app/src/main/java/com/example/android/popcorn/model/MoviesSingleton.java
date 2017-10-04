package com.example.android.popcorn.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Singleton movies instance to be shared by all classes.  No class should need to create a new list of
 * movies because same movies will be referenced.
 */
public class MoviesSingleton {

    private static List<Movie> singletonMovies;

    public MoviesSingleton() {

    }

    public static List<Movie> getSingletonMovies() {
        if (singletonMovies == null) {
            singletonMovies = new ArrayList<>();
        }
        return singletonMovies;
    }



}
