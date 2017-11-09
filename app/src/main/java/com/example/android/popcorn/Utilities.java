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

    private static final String NOT_AVAILABLE = "N/A";

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

    public static String formatGenres(String genres) {
        String formattedGenres = NOT_AVAILABLE;

        if (isNotNullString(genres)) {
            final int ONE_CHAR = 1;
            final int SECOND_CHAR = 1;
            final int SECOND_LAST_CHAR = genres.length() - ONE_CHAR;
            formattedGenres = genres.substring(SECOND_CHAR, SECOND_LAST_CHAR);
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
//        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM yyyy");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy");
        // Turn date into a string.
        if (isNotNullDate(date)) {
            formattedDate = newFormat.format(date);
        }

        return formattedDate;
    }

    public static boolean canReceiveImplicitIntent(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List listOfReceivers = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return listOfReceivers.size() >= 1;
    }
}
