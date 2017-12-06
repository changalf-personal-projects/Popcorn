package com.example.android.popcorn.model.singleton;

import com.example.android.popcorn.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredchang on 2017-12-05.
 */

public class TopMoviesSingleton {

    private static List<Movie> singletonMovies;

    public TopMoviesSingleton() {

    }

    public static List<Movie> getTopMoviesSingleton() {
        if (singletonMovies == null) {
            singletonMovies = new ArrayList<>();
        }
        return singletonMovies;
    }
}
