package com.example.android.popcorn;

import com.example.android.popcorn.model.Movie;

import java.util.Date;

/**
 * Class with static helper methods that check if any fetched json data happens to be null.  Formatting
 * null values will probably cause a NullPointerException.
 */
public class NullChecker {

    public static boolean isNotNullPath(Movie movie) {
        return movie.getPosterPath() != null;
    }

    public static boolean isNotNullString(String input) {
        return input != null;
    }

    public static boolean isNotNullDate(Date date) {
        return date != null;
    }

    public static boolean isNotEmptyString(String input) {
        return input != "";
    }
}
