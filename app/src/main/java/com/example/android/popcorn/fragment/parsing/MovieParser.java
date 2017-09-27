package com.example.android.popcorn.fragment.parsing;

import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;

/**
 * Created by alfredchang on 2017-09-21.
 */

public class MovieParser {

    private static final String LOG_TAG = MovieParser.class.getSimpleName();

    public static MovieLogan parseJsonData(String response) {
        MovieLogan movieLogan = null;
        try {
            movieLogan = LoganSquare.parse(response, MovieLogan.class);
        } catch (IOException e) {
            Log.e(LOG_TAG, "parseJsonData(): " + e);
        }
        return movieLogan;
    }
}
