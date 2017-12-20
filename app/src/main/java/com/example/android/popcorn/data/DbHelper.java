package com.example.android.popcorn.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.popcorn.data.DbContract.SavedMoviesEntryMain;
import com.example.android.popcorn.data.DbContract.SavedMoviesEntryDetails;

/**
 * Created by alfredchang on 2017-12-17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "SavedMovies.db";
    public static final int DB_VERSION = 2;

    private static DbHelper mInstance;

    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized DbHelper getDbInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MAIN_TABLE = "CREATE TABLE " +
                SavedMoviesEntryMain.TABLE_NAME + " (" +
                SavedMoviesEntryMain._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SavedMoviesEntryMain.COLUMN_POSTER_PATH + " TEXT, " +
                SavedMoviesEntryMain.COLUMN_TITLE + " TEXT NOT NULL UNIQUE, " +
                SavedMoviesEntryMain.COLUMN_RATING + " TEXT, " +
                SavedMoviesEntryMain.COLUMN_GENRES + " TEXT" +
                "); ";

        final String SQL_CREATE_DETAIL_TABLE = "CREATE TABLE " +
                SavedMoviesEntryDetails.TABLE_NAME + " (" +
                SavedMoviesEntryDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SavedMoviesEntryDetails.COLUMN_BACKDROP_PATH + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_POSTER_PATH + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_TITLE + " TEXT NOT NULL UNIQUE, " +
                SavedMoviesEntryDetails.COLUMN_RATING + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_RUNTIME + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_RELEASE + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_GENRES + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_TAGLINE + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_OVERVIEW + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_DIRECTOR_PHOTO_PATH + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_DIRECTOR_NAME + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_PRODUCER_PHOTO_PATH + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_PRODUCER_NAME + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_LANGUAGES + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_BUDGET + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_REVENUE + " TEXT, " +
                SavedMoviesEntryDetails.COLUMN_PROD_COMPANIES + " TEXT" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_MAIN_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SavedMoviesEntryMain.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SavedMoviesEntryDetails.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
