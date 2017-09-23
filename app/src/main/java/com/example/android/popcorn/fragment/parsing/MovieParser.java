package com.example.android.popcorn.fragment.parsing;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by alfredchang on 2017-09-21.
 */

public class MovieParser {

    private final String LOG_TAG = MovieParser.class.getSimpleName();

    ObjectMapper objMapper = new ObjectMapper();

    public MovieJackson parseJsonData(String response) {
        MovieJackson movieJackson = null;
        try {
            movieJackson = objMapper.readValue(response, MovieJackson.class);
        } catch (IOException e) {
            Log.e(LOG_TAG, "parseJsonData(): " + e);
        }
        return movieJackson;
    }
}
