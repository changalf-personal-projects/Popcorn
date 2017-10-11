package com.example.android.popcorn;

import android.util.Log;

import com.example.android.popcorn.model.Movie;

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

    public static final String PARCELABLE_MOVIE_KEY = "movie";
    public static final String PARCELABLE_CAST_KEY = "cast";
    public static final String PARCELABLE_CAST_MEMBER_KEY = "cast member";

    private static final String NOT_AVAILABLE = "N/A";

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
        String formattedGenres = NOT_AVAILABLE;

        if (isNotNullString(genres)) {
            final int ONE_CHAR = 1;
            final int SECOND_CHAR = 1;
            final int SECOND_LAST_CHAR = genres.length() - ONE_CHAR;
            formattedGenres = genres.substring(SECOND_CHAR, SECOND_LAST_CHAR);
        }

        return formattedGenres;
    }

    // TODO: Use Calendar instance instead.
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
        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM d, yyyy");
        // Turn date into a string.
        if (isNotNullDate(date)) {
            formattedDate = newFormat.format(date);
        }

        return formattedDate;
    }
}
