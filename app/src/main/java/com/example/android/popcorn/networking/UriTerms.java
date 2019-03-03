package com.example.android.popcorn.networking;

/**
 * Created by alfredchang on 2017-09-20.
 */

public class UriTerms {

    // Endpoints.
    public static final String CREDITS = "credits";
    public static final String VIDEOS = "videos";
    public static final String REVIEWS = "reviews";
    public static final String YOUTUBE_V = "v";
    public static final String QUERY = "query";
    public static final String RECOMMENDATIONS = "recommendations";

    // Query terms.
    public static final String POPULAR = "popular";
    public static final String TOP = "top_rated";
    public static final String CURRENT = "now_playing";
    public static final String LANGUAGE = "en-US";
    public static final String PAGE = "1";

    // Image sizes.
    public static final String IMAGE_SIZE_W92 = "w92";
    public static final String IMAGE_SIZE_W185 = "w185";
    public static final String IMAGE_SIZE_W342 = "w342";
    public static final String IMAGE_SIZE_W500 = "w500";

    // Poster url stuff.
    public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String POSTER_SIZE_ORIGINAL = "original";
    public static final String TMDB_API_KEY = "api_key";
    public static final String TMDB_LANGUAGE = "language";
    public static final String TMDB_PAGE = "page";

    // Main urls.
    public static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String PERSON_BASE_URL = "https://api.themoviedb.org/3/person/";
    public static final String SEARCH_MOVIE_URL = "https://api.themoviedb.org/3/search/movie/";
    public static final String MOVIE_SHARE_BASE_URL = "https://www.themoviedb.org/movie/";
    public static final String YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch/";
    public static final String YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/";
    public static final String YOUTUBE_THUMBNAIL_HIGHRES = "mqdefault.jpg";

    // Normally, looking for the movie details means we have to first get the movie id and then use
    // that id to get the details.  But with "append_to_response", this can be done in one request.
    public static final String APPEND_TO_RESPONSE = "append_to_response";
}
