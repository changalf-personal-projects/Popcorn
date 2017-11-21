package com.example.android.popcorn.model.singleton;

import com.example.android.popcorn.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredchang on 2017-11-20.
 */

public class SearchedMoviesSingleton {

    private static List<Movie> singletonMovies;

    public SearchedMoviesSingleton() {

    }

    public static List<Movie> getSearchedMoviesSingleton() {
        if (singletonMovies == null) {
            singletonMovies = new ArrayList<>();
        }
        return singletonMovies;
    }

}
