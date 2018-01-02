package com.example.android.popcorn.data;

import android.provider.BaseColumns;

/**
 * Created by alfredchang on 2017-12-17.
 */

public class DbContract {

    // Contract class should not need to be instantiated because everything is static.
    private DbContract() {

    }

    // BaseColumns interface already has the static id field, so no need to create on in this class.
    public static class SavedMoviesEntry implements BaseColumns {

        // Column names can't have space between words.
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER_PATH = "poster";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_GENRES = "genres";
        public static final String COLUMN_BACKDROP_PATH = "backdrop";
        public static final String COLUMN_RUNTIME = "runtime";
        public static final String COLUMN_RELEASE = "release";
        public static final String COLUMN_TAGLINE = "tagline";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_DIRECTOR_PHOTO_PATH = "director_profile";
        public static final String COLUMN_DIRECTOR_NAME = "director_name";
        public static final String COLUMN_PRODUCER_PHOTO_PATH = "producer_profile";
        public static final String COLUMN_PRODUCER_NAME = "producer_name";
        public static final String COLUMN_LANGUAGES = "languages";
        public static final String COLUMN_BUDGET = "budget";
        public static final String COLUMN_REVENUE = "revenue";
        public static final String COLUMN_PROD_COMPANIES = "production_companies";
    }

    public static class TrailersEntry implements BaseColumns {

        public static final String TABLE_NAME = "trailers";
        public static final String COLUMN_KEY = "key";
        public static final String COLUMN_DETAIL = "description";
    }
}
