package com.example.android.popcorn.networking;

/**
 * Created by alfredchang on 2017-09-20.
 */

public class UriTerms {

    // Tab titles.
    public static final String POPULAR_MOVIES = "Popular";
    public static final String TOP_MOVIES = "Top";
    public static final String CURRENT_MOVIES = "Current";
    public static final String FAVOURITE_MOVIES = "Favourite";

    // Query terms.
    public static final String POPULAR = "popular";
    public static final String TOP = "top";
    public static final String CURRENT = "current";
    public static final String LANGUAGE = "en-US";
    public static final String PAGE = "1";
    public static final String CREDITS = "credits";
    public static final String VIDEOS = "videos";

    // Image sizes.
    public static final String IMAGE_SIZE_W92 = "w92";
    public static final String IMAGE_SIZE_W185 = "w185";
    public static final String IMAGE_SIZE_W250 = "w250";
    public static final String IMAGE_SIZE_W342 = "w342";
    public static final String POSTER_SIZE_W500 = "w500";

    // Poster url stuff.
    public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String POSTER_SIZE_ORIGINAL = "original";
    public static final String TMDB_API_KEY = "api_key";
    public static final String TMDB_LANGUAGE = "language";
    public static final String TMDB_PAGE = "page";

    // Main url to get movie id (plus queries).
    public static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";

    // Normally, looking for the movie details means we have to first get the movie id and then use
    // that id to get the details.  But with "append_to_response", this can be done in one request.
    public static final String APPEND_TO_RESPONSE = "append_to_response";
}
