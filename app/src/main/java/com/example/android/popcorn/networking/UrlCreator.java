package com.example.android.popcorn.networking;

import android.net.Uri;

import com.example.android.popcorn.BuildConfig;
import com.example.android.popcorn.UriTerms;

/**
 * Created by alfredchang on 2017-09-26.
 */

public class UrlCreator {

    // Request for movie details.
    public static String createUrl(String path) {
        return Uri.parse(UriTerms.MOVIE_BASE_URL).buildUpon()
                .appendPath(path)
                .appendQueryParameter(UriTerms.TMDB_API_KEY, BuildConfig.MOVIE_DP_API_KEY)
                .appendQueryParameter(UriTerms.TMDB_LANGUAGE, UriTerms.LANGUAGE)
                .appendQueryParameter(UriTerms.TMDB_PAGE, UriTerms.PAGE)
                .build().toString();
    }

    public static String createImageUrl(String path, String size) {
        return UriTerms.POSTER_BASE_URL.concat(size).concat(path);
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
