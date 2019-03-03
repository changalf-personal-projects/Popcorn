package com.example.android.popcorn.networking;

import android.net.Uri;

import com.example.android.popcorn.BuildConfig;

import static com.example.android.popcorn.Utilities.isNotNull;

/**
 * Created by alfredchang on 2017-09-26.
 */

public class UrlCreator {

    private static final String LOG_TAG = UrlCreator.class.getSimpleName();
    private static final String COMMA = ",";

    public static String createUrl(String id) {
        return Uri.parse(UriTerms.MOVIE_SHARE_BASE_URL).buildUpon()
                .appendPath(id)
                .build().toString();
    }

    // Request for movie details.
    public static String createUrlWithCategory(String query) {
        return Uri.parse(UriTerms.MOVIE_BASE_URL).buildUpon()
                .appendPath(query)
                .appendQueryParameter(UriTerms.TMDB_API_KEY, BuildConfig.MOVIE_DP_API_KEY)
                .appendQueryParameter(UriTerms.TMDB_LANGUAGE, UriTerms.LANGUAGE)
                .appendQueryParameter(UriTerms.TMDB_PAGE, UriTerms.PAGE)
                .build().toString();
    }

    public static String createImageUrl(String path, String size) {
        String url = "";
        if (isNotNull(path)) {
            url = UriTerms.POSTER_BASE_URL.concat(size).concat(path);
        }
        return url;
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

    public static String createRecommendedMoviesUrl(String id) {
        return Uri.parse(UriTerms.MOVIE_BASE_URL).buildUpon()
                .appendPath(id)
                .appendPath(UriTerms.RECOMMENDATIONS)
                .appendQueryParameter(UriTerms.TMDB_API_KEY, BuildConfig.MOVIE_DP_API_KEY)
                .appendQueryParameter(UriTerms.TMDB_LANGUAGE, UriTerms.LANGUAGE)
                .build().toString();
    }

    public static String createSearchMovieUrl(String query) {
        return Uri.parse(UriTerms.SEARCH_MOVIE_URL).buildUpon()
                .appendQueryParameter(UriTerms.TMDB_API_KEY, BuildConfig.MOVIE_DP_API_KEY)
                .appendQueryParameter(UriTerms.QUERY, query)
                .build().toString();
    }

    // Method to create url that will fetch the trailer thumbnail from Youtube.
    public static String createYoutubeThumbnailUrl(String id) {
        return Uri.parse(UriTerms.YOUTUBE_THUMBNAIL_URL).buildUpon()
                .appendPath(id)
                .appendPath(UriTerms.YOUTUBE_THUMBNAIL_HIGHRES)
                .build().toString();
    }

    public static String appendEndpoints() {
        return UriTerms.CREDITS + COMMA
                + UriTerms.VIDEOS + COMMA
                + UriTerms.REVIEWS + COMMA
                + UriTerms.RECOMMENDATIONS;

    }
}
