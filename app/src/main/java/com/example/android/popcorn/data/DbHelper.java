package com.example.android.popcorn.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.popcorn.data.DbContract.SavedMoviesEntry;

/**
 * Created by alfredchang on 2017-12-17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "SavedMovies.db";
    public static final int DB_VERSION = 1;

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
        final String SQL_CREATE_TABLE = "CREATE TABLE " +
                SavedMoviesEntry.TABLE_NAME + " (" +
                SavedMoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SavedMoviesEntry.COLUMN_POSTER_PATH + " TEXT, " +
                SavedMoviesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                SavedMoviesEntry.COLUMN_RATING + " TEXT," +
                SavedMoviesEntry.COLUMN_GENRES + " TEXT" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SavedMoviesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
