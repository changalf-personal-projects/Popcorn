package com.example.android.popcorn.model.singleton;


import com.example.android.popcorn.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton movies instance to be shared by all classes.  No class should need to create a new list of
 * movies because same movies will be referenced.
 */
public class PopularMoviesSingleton {

    private static List<Movie> singletonMovies;

    public PopularMoviesSingleton() {

    }

    public static List<Movie> getPopularMoviesSingleton() {
        if (singletonMovies == null) {
            singletonMovies = new ArrayList<>();
        }
        return singletonMovies;
    }

}
