package com.example.android.popcorn;

import com.example.android.popcorn.model.Movie;

/**
 * Created by alfredchang on 2017-09-30.
 */

public class Utilities {

    public static boolean hasPosterPath(Movie movie) {
        return movie.getPosterPath() != null;
    }

    public static double roundToNearestTenth(Movie movie) {
        return Math.round(Double.parseDouble(movie.getRating()) * 10.0) / 10.0;

    }

}
