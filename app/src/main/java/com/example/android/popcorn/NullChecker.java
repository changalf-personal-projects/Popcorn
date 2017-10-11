package com.example.android.popcorn;

import java.util.Date;

/**
 * Class with static helper methods that check if any fetched json data happens to be null.  Formatting
 * null values will probably cause a NullPointerException.
 */
public class NullChecker {

    public static boolean isNullGenres(String genres) {
        return genres != null;
    }

    public static boolean isNullDate(Date date) {
        return date != null;
    }
}
