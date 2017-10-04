package com.example.android.popcorn.fragment.parsing;

import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;

/**
 * Created by alfredchang on 2017-09-21.
 */

public class MovieParser {

    private static final String LOG_TAG = MovieParser.class.getSimpleName();

    // Parse the movie id first.
    public static LoganIdTemplate parseJsonIdData(String response) {
        return (LoganIdTemplate) parseData(response, LoganIdTemplate.class);
    }

    // Using movie id, get the rest of details.
    public static LoganDetailsTemplate parseJsonDetailsData(String response) {
        return (LoganDetailsTemplate) parseData(response, LoganDetailsTemplate.class);
    }

    // Using movie id and appending credits as response, get the cast details.
    public static LoganCastsTemplate parseJsonCastData(String response) {
        return (LoganCastsTemplate) parseData(response, LoganCastsTemplate.class);
    }

    // Using movie id, get the trailers for a movie.
    public static LoganTrailersTemplate parseJsonTrailersData(String response) {
        return (LoganTrailersTemplate) parseData(response, LoganTrailersTemplate.class);
    }

    private static MovieLogan parseData(String response, Class template) {
        MovieLogan movieLogan = null;
        try {
            movieLogan = (MovieLogan) LoganSquare.parse(response, template);
        } catch (IOException e) {
            Log.e(LOG_TAG, "parseJsonIdData(): " + e);
        }
        return movieLogan;
    }
}
