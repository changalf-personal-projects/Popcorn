package com.example.android.popcorn.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.popcorn.data.DbContract.SavedMoviesEntry;
import com.example.android.popcorn.data.DbContract.TrailersEntry;
import com.example.android.popcorn.data.DbContract.CastEntry;
import com.example.android.popcorn.data.DbContract.ReviewsEntry;

/**
 * Created by alfredchang on 2017-12-17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "SavedMovies.db";
    public static final int DB_VERSION = 8;

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
                SavedMoviesEntry.TABLE_NAME + " (" +
                SavedMoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SavedMoviesEntry.COLUMN_POSTER_PATH + " TEXT, " +
                SavedMoviesEntry.COLUMN_TITLE + " TEXT NOT NULL UNIQUE, " +
                SavedMoviesEntry.COLUMN_RATING + " TEXT, " +
                SavedMoviesEntry.COLUMN_GENRES + " TEXT, " +
                SavedMoviesEntry.COLUMN_BACKDROP_PATH + " TEXT, " +
                SavedMoviesEntry.COLUMN_RUNTIME + " TEXT, " +
                SavedMoviesEntry.COLUMN_RELEASE + " TEXT, " +
                SavedMoviesEntry.COLUMN_TAGLINE + " TEXT, " +
                SavedMoviesEntry.COLUMN_OVERVIEW + " TEXT, " +
                SavedMoviesEntry.COLUMN_DIRECTOR_PHOTO_PATH + " TEXT, " +
                SavedMoviesEntry.COLUMN_DIRECTOR_NAME + " TEXT, " +
                SavedMoviesEntry.COLUMN_PRODUCER_PHOTO_PATH + " TEXT, " +
                SavedMoviesEntry.COLUMN_PRODUCER_NAME + " TEXT, " +
                SavedMoviesEntry.COLUMN_LANGUAGES + " TEXT, " +
                SavedMoviesEntry.COLUMN_BUDGET + " TEXT, " +
                SavedMoviesEntry.COLUMN_REVENUE + " TEXT, " +
                SavedMoviesEntry.COLUMN_PROD_COMPANIES + " TEXT" +
                "); ";

        final String SQL_CREATE_TRAILERS_TABLE = "CREATE TABLE " +
                TrailersEntry.TABLE_NAME + " (" +
                TrailersEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TrailersEntry.COLUMN_KEY + " TEXT NOT NULL, " +
                TrailersEntry.COLUMN_DETAIL + " TEXT" +
                "); ";

        final String SQL_CREATE_CAST_TABLE = "CREATE TABLE " +
                CastEntry.TABLE_NAME + " (" +
                CastEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CastEntry.COLUMN_NAME + " TEXT, " +
                CastEntry.COLUMN_PROFILE + " TEXT, " +
                CastEntry.COLUMN_CHARACTER + " TEXT, " +
                CastEntry.COLUMN_ID + " TEXT, " +
                CastEntry.COLUMN_BIRTHDAY + " TEXT, " +
                CastEntry.COLUMN_DEATHDAY + " TEXT, " +
                CastEntry.COLUMN_BIO + " TEXT, " +
                CastEntry.COLUMN_BIRTH_PLACE + " TEXT" +
                "); ";

        final String SQL_CREATE_REVIEWS_TABLE = "CREATE TABLE " +
                ReviewsEntry.TABLE_NAME + " (" +
                ReviewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ReviewsEntry.COLUMN_AUTHOR + " TEXT, " +
                ReviewsEntry.COLUMN_CONTENT + " TEXT" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_MAIN_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TRAILERS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CAST_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_REVIEWS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SavedMoviesEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TrailersEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CastEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReviewsEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
