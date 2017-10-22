package com.example.android.popcorn.networking;

import android.net.Uri;

import com.example.android.popcorn.BuildConfig;

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

    public static String createUrlWithAppendedResponse(String id, String parameterToAppend) {
        return Uri.parse(UriTerms.MOVIE_BASE_URL).buildUpon()
                .appendPath(id)
                .appendQueryParameter(UriTerms.TMDB_API_KEY, BuildConfig.MOVIE_DP_API_KEY)
                .appendQueryParameter(UriTerms.APPEND_TO_RESPONSE, parameterToAppend)
                .build().toString();
    }

    public static String createCastMemberDetailUrl(String id) {
        return Uri.parse(UriTerms.PERSON_BASE_URL).buildUpon()
                .appendPath(id)
                .appendQueryParameter(UriTerms.TMDB_API_KEY, BuildConfig.MOVIE_DP_API_KEY)
                .appendQueryParameter(UriTerms.TMDB_LANGUAGE, UriTerms.LANGUAGE)
                .build().toString();
    }

    // Method to create url that wil fetch the trailer thumbnail from Youtube.
    public static String createYoutubeThumbnailUrl(String id) {
        return Uri.parse(UriTerms.YOUTUBE_THUMBNAIL_URL).buildUpon()
                .appendPath(id)
                .appendPath(UriTerms.YOUTUBE_THUMBNAIL_RESOLUTION)
                .build().toString();
    }
}
