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
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_POSTER_PATH, "");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_TITLE, "It");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_RATING, "10");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_GENRES, "Action");
        dummyData.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_POSTER_PATH, "");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_TITLE, "Thor");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_RATING, "0");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_GENRES, "Comedy");
        dummyData.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_POSTER_PATH, "");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_TITLE, "Dunkirk");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_RATING, "5");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_GENRES, "Romance");
        dummyData.add(cv);

        cv = new ContentValues();
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_POSTER_PATH, "");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_TITLE, "Justice League");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_RATING, "11");
        cv.put(DbContract.SavedMoviesEntryMain.COLUMN_GENRES, "Horror");
        dummyData.add(cv);

        try {
            sqlDb.beginTransaction();
            sqlDb.delete(DbContract.SavedMoviesEntryMain.TABLE_NAME, null, null);

            for (ContentValues values: dummyData) {
                sqlDb.insert(DbContract.SavedMoviesEntryMain.TABLE_NAME, null, values);
            }

            sqlDb.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Error in insertDummyData(...): " + e);
        } finally {
            sqlDb.endTransaction();
        }
    }
}
