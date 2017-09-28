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
    public static LoganIdTemplate parseJsonData(String response) {
        LoganIdTemplate movieLogan = null;
        try {
            movieLogan = LoganSquare.parse(response, LoganIdTemplate.class);
        } catch (IOException e) {
            Log.e(LOG_TAG, "parseJsonData(): " + e);
        }
        return movieLogan;
    }

    // Using movie id, get the rest of details.
    public static LoganDetailsTemplate parseJsonDetailsData(String response) {
        LoganDetailsTemplate movieLogan = null;
        try {
            movieLogan = LoganSquare.parse(response, LoganDetailsTemplate.class);
        } catch (IOException e) {
            Log.e(LOG_TAG, "parseJsonData(): " + e);
        }
        return movieLogan;
    }
}
