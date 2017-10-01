package com.example.android.popcorn;

import com.example.android.popcorn.model.Movie;

import java.util.List;

/**
 * Created by alfredchang on 2017-09-30.
 */

public class Utilities {
    private static final String LOG_TAG = Utilities.class.getSimpleName();
    private static final int MAX_GENRES_ALLOWED = 2;

    public static boolean hasPosterPath(Movie movie) {
        return movie.getPosterPath() != null;
    }

    public static double roundToNearestTenth(Movie movie) {
        String rating = movie.getRating();
        if (rating == null) {
            return 0.0;
        } else {
            return Math.round(Double.parseDouble(rating) * 10.0) / 10.0;
        }
    }

    public static String convertDoubleToString(double rating) {
        return String.valueOf(rating);
    }

    public static boolean hasAtLeastOneGenre(List<String> genres) {
        return genres.size() >= 1;
    }
}
