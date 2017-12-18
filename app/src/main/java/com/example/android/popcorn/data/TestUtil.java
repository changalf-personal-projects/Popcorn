package com.example.android.popcorn.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredchang on 2017-12-17.
 */

public class TestUtil {

    private static final String LOG_TAG = TestUtil.class.getSimpleName();

    public static void insertDummyData(SQLiteDatabase sqlDb) {
        List<ContentValues> dummyData = new ArrayList<>();

        if (sqlDb == null) {
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(DbContract.SavedMoviesEntry.COLUMN_POSTER_PATH, "");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_TITLE, "It");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_RATING, "10");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_GENRES, "Action");
        dummyData.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.SavedMoviesEntry.COLUMN_POSTER_PATH, "");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_TITLE, "Thor");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_RATING, "0");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_GENRES, "Comedy");
        dummyData.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.SavedMoviesEntry.COLUMN_POSTER_PATH, "");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_TITLE, "Dunkirk");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_RATING, "5");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_GENRES, "Romance");
        dummyData.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.SavedMoviesEntry.COLUMN_POSTER_PATH, "");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_TITLE, "Justice League");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_RATING, "11");
        cv.put(DbContract.SavedMoviesEntry.COLUMN_GENRES, "Horror");
        dummyData.add(cv);

        try {
            sqlDb.beginTransaction();
            sqlDb.delete(DbContract.SavedMoviesEntry.TABLE_NAME, null, null);

            for (ContentValues values: dummyData) {
                Log.v(LOG_TAG, "Movie title test: " + values.getAsString(DbContract.SavedMoviesEntry.COLUMN_TITLE));
                sqlDb.insert(DbContract.SavedMoviesEntry.TABLE_NAME, null, values);
            }

            sqlDb.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Error in insertDummyData(...): " + e);
        } finally {
            sqlDb.endTransaction();
        }
    }
}
