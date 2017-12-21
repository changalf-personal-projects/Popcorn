package com.example.android.popcorn.model.singleton;

import com.example.android.popcorn.model.Movie;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alfredchang on 2017-12-17.
 */

public class SavedMoviesSingleton {

    private static Set<Movie> singletonMovies;

    public SavedMoviesSingleton() {

    }

    public static Set<Movie> getSavedMoviesSingleton() {
        if (singletonMovies == null) {
            singletonMovies = new HashSet<>();
        }
        return singletonMovies;
    }
}
