package com.example.android.popcorn.networking;

import android.net.Uri;

import com.example.android.popcorn.BuildConfig;
import com.example.android.popcorn.UriUtils;

/**
 * Created by alfredchang on 2017-09-26.
 */

public class UrlCreator {

    // The initial request for the json data that includes movie id, which will allow us to further
    // get rest of info.
    public static String createUrl() {
        return Uri.parse(UriUtils.MOVIE_BASE_URL).buildUpon()
                .appendPath(UriUtils.POPULAR)
                .appendQueryParameter(UriUtils.TMDB_API_KEY, BuildConfig.MOVIE_DP_API_KEY)
                .appendQueryParameter(UriUtils.TMDB_LANGUAGE, UriUtils.LANGUAGE)
                .appendQueryParameter(UriUtils.TMDB_PAGE, UriUtils.PAGE)
                .build().toString();
    }

    // Request for movie details.
    public static String createDetailUrl(String id) {
        return Uri.parse(UriUtils.MOVIE_BASE_URL).buildUpon()
                .appendPath(id)
                .appendQueryParameter(UriUtils.TMDB_API_KEY, BuildConfig.MOVIE_DP_API_KEY)
                .appendQueryParameter(UriUtils.TMDB_LANGUAGE, UriUtils.LANGUAGE)
                .appendQueryParameter(UriUtils.TMDB_PAGE, UriUtils.PAGE)
                .build().toString();
    }

    public static String createPosterUrl(String path) {
        return UriUtils.POSTER_BASE_URL.concat(UriUtils.POSTER_SIZE).concat(path);
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
