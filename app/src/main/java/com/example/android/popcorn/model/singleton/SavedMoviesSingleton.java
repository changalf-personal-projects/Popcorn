package com.example.android.popcorn.model.singleton;

import com.example.android.popcorn.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredchang on 2017-12-17.
 */

public class SavedMoviesSingleton {

    private static List<Movie> singletonMovies;

    public SavedMoviesSingleton() {

    }

    public static List<Movie> getSavedMoviesSingleton() {
        if (singletonMovies == null) {
            singletonMovies = new ArrayList<>();
        }
        return singletonMovies;
    }
}
