package com.example.android.popcorn;

import com.example.android.popcorn.model.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by alfredchang on 2017-09-30.
 */

public class Utilities {

    private static final String LOG_TAG = Utilities.class.getSimpleName();

    public static final String PARCELABLE_MOVIE_KEY = "movie";
    public static final String PARCELABLE_CAST_KEY = "cast";
    public static final String PARCELABLE_CAST_MEMBER_KEY = "cast member";

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

    // TODO: Use Calendar instance instead.
    public static String formatDate(String releaseDate) {
        Date date = null;
        // Start with the original format of releaseDate (ie. 2016-12-10).
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Get a new Date object from releaseDate.
            date = originalFormat.parse(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // This is the format that we want the date to be.
        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM d, yyyy");
        // Turn date into a string.
        String formattedDate = newFormat.format(date);

        return formattedDate;
    }
}
