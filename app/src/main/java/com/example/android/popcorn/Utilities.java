package com.example.android.popcorn;

import com.example.android.popcorn.model.Movie;

import java.util.List;

/**
 * Created by alfredchang on 2017-09-30.
 */

public class Utilities {

    public static final String PARCELABLE_MOVIE_KEY = "movie";
    private static final String LOG_TAG = Utilities.class.getSimpleName();

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

    public static boolean hasAtLeastOneGenre(List<String> genres) {
        return genres.size() >= 1;
    }

    public static String convertDoubleToString(double rating) {
        return String.valueOf(rating);
    }

    public static String formatGenres(String genres) {
        final int SECOND_CHAR = 1;
        final int SECOND_LAST_CHAR = genres.length() - 1;

        return genres.substring(SECOND_CHAR, SECOND_LAST_CHAR);
    }
}
