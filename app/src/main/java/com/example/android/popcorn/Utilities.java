package com.example.android.popcorn;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    public static final String PARCELABLE_REC_MOVIE_KEY = "recommended movie";
    public static final String PARCELABLE_REVIEW_KEY = "review";
    public static final String PARCELABLE_CAST_MEMBER_KEY = "cast member";
    public static final String PARCELABLE_TRAILER_KEY = "trailers";
    public static final String PARCELABLE_TRAILER_IDS_KEY = "trailer ids";

    public static final String MAIN_ACTIVITY_PARENT = "main activity";
    public static final String SEARCH_ACTIVITY_PARENT = "search results activity";
    public static final String PARENT_ACTIVITY = "parent activity";
    public static final String SEARCH_KEY = "query";
    public static final String NOT_AVAILABLE = "Not available";
    public static final String FROM = "from which fragment?";
    public static final int PARENT = 0;
    private static final String N_A = "N/A";
    private static final String DOLLAR_SIGN = "$";
    private static final String COMMA = ",";
    private static final String COMMA_SPACE = ", ";
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
        String formattedGenres = N_A;

        if (isNotNullString(string)) {
            // For clearer purposes, these are two variables with same values.
            final int SINGLE_CHAR = 1;
            final int SECOND_CHAR = 1;
            final int SECOND_LAST_CHAR = string.length() - SINGLE_CHAR;
            formattedGenres = string.substring(SECOND_CHAR, SECOND_LAST_CHAR);
        }

        return formattedGenres;
    }

    public static String convertListToString(List<String> listOfStrings) {
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < listOfStrings.size(); i++) {
            strBuilder.append(listOfStrings.get(i));

            if (i < listOfStrings.size() - 1) {
                strBuilder.append(COMMA_SPACE);
            }
        }

        return strBuilder.toString();
    }

    public static List<String> convertStringToList(String arg) {
        String[] arrayOfStrings = arg.split(COMMA);
        return Arrays.asList(arrayOfStrings);
    }

    public static String formatDate(String arg, String format) {
        Date date = null;
        String formattedDate = N_A;
        // Start with the original format of releaseDate (ie. 2016-12-10).
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Get a new Date object from releaseDate.
            if (isNotNullString(arg)) {
                date = originalFormat.parse(arg);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // This is the format that we want the date to be.
        // SimpleDateFormat newFormat = new SimpleDateFormat("MMMM yyyy");
        SimpleDateFormat newFormat = new SimpleDateFormat(format);
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
        return N_A;
    }

    public static String formatWithCommas(String stringValue) {
        if (moreThanThreeChars(stringValue)) {
            StringBuilder stringBuilder = new StringBuilder(stringValue);
            for (int i = stringValue.length() - 3; i >= 1; i -= 3) {
                stringBuilder.insert(i, COMMA);
            }
            return formatWithDollarSign(stringBuilder.toString());
        }
        return N_A;
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
        if (isNotNull(value)) {
            return value.length() > THREE_CHARS;
        }
        return false;
    }
}
