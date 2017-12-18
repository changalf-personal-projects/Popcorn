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
    }
}
