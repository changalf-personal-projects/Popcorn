package com.example.android.popcorn;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.android.popcorn.NullChecker.isNotNullDate;
import static com.example.android.popcorn.NullChecker.isNotNullString;

/**
 * Created by alfredchang on 2017-09-30.
 */

public class Utilities {

    private static final String LOG_TAG = Utilities.class.getSimpleName();

    // TODO: Use an enum for these parcelable strings.
    public static final String PARCELABLE_MOVIE_KEY = "movie";
    public static final String PARCELABLE_REVIEW_KEY = "review";
    public static final String PARCELABLE_CAST_MEMBER_KEY = "cast member";
    public static final String PARCELABLE_TRAILER_KEY = "trailers";
    public static final String PARCELABLE_TRAILER_IDS_KEY = "trailer ids";

    public static final String MAIN_ACTIVITY_PARENT = "main activity";
    public static final String SEARCH_ACTIVITY_PARENT = "search results activity";
    public static final String PARENT_ACTIVITY = "parent activity";
    public static final String SEARCH_KEY = "query";
    private static final String NOT_AVAILABLE = "N/A";
    private static final String DOLLAR_SIGN = "$";
    private static final int THREE_CHARS = 3;

    public static double roundToNearestTenth(String rating) {
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

    public static String formatString(String string) {
        String formattedGenres = NOT_AVAILABLE;

        if (isNotNullString(string)) {
            // For clearer purposes, these are two variables with same values.
            final int SINGLE_CHAR = 1;
            final int SECOND_CHAR = 1;
            final int SECOND_LAST_CHAR = string.length() - SINGLE_CHAR;
            formattedGenres = string.substring(SECOND_CHAR, SECOND_LAST_CHAR);
        }

        return formattedGenres;
    }

    public static String formatDate(String releaseDate) {
        Date date = null;
        String formattedDate = NOT_AVAILABLE;
        // Start with the original format of releaseDate (ie. 2016-12-10).
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Get a new Date object from releaseDate.
            Log.v(LOG_TAG, "Release date: " + releaseDate);
            if (isNotNullString(releaseDate)) {
                date = originalFormat.parse(releaseDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // This is the format that we want the date to be.
        // SimpleDateFormat newFormat = new SimpleDateFormat("MMMM yyyy");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy");
        // Turn date into a string.
        if (isNotNullDate(date)) {
            formattedDate = newFormat.format(date);
        }

        return formattedDate;
    }

    public static String formatWithSpaces(String stringValue) {
        if (moreThanThreeChars(stringValue)) {
            StringBuilder stringBuilder = new StringBuilder(stringValue);
            for (int i = stringValue.length() - 3; i >= stringValue.length() / 3; i -= 3) {
                stringBuilder.insert(i, " ");
            }
            return formatWithDollarSign(stringBuilder.toString());
        }
        return NOT_AVAILABLE;
    }

    public static String formatWithCommas(String stringValue) {
        if (moreThanThreeChars(stringValue)) {
            StringBuilder stringBuilder = new StringBuilder(stringValue);
            for (int i = stringValue.length() - 3; i >= stringValue.length() / 3; i -= 3) {
                stringBuilder.insert(i, ",");
            }
            return formatWithDollarSign(stringBuilder.toString());
        }
        return NOT_AVAILABLE;
    }

    public static String formatWithDollarSign(String value) {
        return DOLLAR_SIGN.concat(value);
    }

    public static boolean isNotNull(String param) {
        return param != null;
    }

    public static boolean canReceiveImplicitIntent(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List listOfReceivers = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return listOfReceivers.size() >= 1;
    }

    private static boolean moreThanThreeChars(String value) {
        return value.length() > 3;
    }
}
