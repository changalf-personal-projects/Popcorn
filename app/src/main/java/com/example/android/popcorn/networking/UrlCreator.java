package com.example.android.popcorn.networking;

import android.net.Uri;

import com.example.android.popcorn.BuildConfig;
import com.example.android.popcorn.MovieKeywords;

/**
 * Created by alfredchang on 2017-09-26.
 */

public class UrlCreator {

    // The initial request for the json data that includes movie id, which will allow us to further
    // get rest of info.
    public static String createUrl() {
        return Uri.parse(MovieKeywords.MOVIE_BASE_URL).buildUpon().appendPath(MovieKeywords.POPULAR)
                .appendQueryParameter(MovieKeywords.TMDB_API_KEY, BuildConfig.MOVIE_DP_API_KEY)
                .appendQueryParameter(MovieKeywords.TMDB_LANGUAGE, MovieKeywords.LANGUAGE)
                .appendQueryParameter(MovieKeywords.TMDB_PAGE, MovieKeywords.PAGE)
                .build().toString();
    }

    // Request for movie details.
    public static String createDetailUrl() {
        return "";
    }

    // Request for movie trailers.
    public static String createTrailerUrl() {
        return "";
    }

    // Request for movie reviews.
    public static String createReviewUrl() {
        return "";
    }
}
