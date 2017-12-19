package com.example.android.popcorn.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.activity.DetailActivity;
import com.example.android.popcorn.data.DbContract;
import com.example.android.popcorn.data.DbHelper;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.ui.saved_movie_recyclerview.OnMovieClickListener;
import com.example.android.popcorn.ui.saved_movie_recyclerview.SavedMoviesRVAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.model.singleton.SavedMoviesSingleton.getSavedMoviesSingleton;

/**
 * Created by alfredchang on 2017-09-16.
 */

public class FavouriteFragment extends Fragment implements OnMovieClickListener {


    private final String LOG_TAG = PopularFragment.class.getSimpleName();
    private final int LAYOUT_COL_SPAN = 2;
    private final int FIRST_GENRE = 0;

    private SQLiteDatabase mSqlDb;
    private DbHelper mDbHelper;

    SavedMoviesRVAdapter mRecyclerAdapter;
    Cursor mCursor;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.posters_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.pull_refresh_layout)
    SwipeRefreshLayout mPullRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        onPullScreenDown();

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), LAYOUT_COL_SPAN);
        mRecyclerView.setLayoutManager(layoutManager);

        mDbHelper = new DbHelper(getActivity());
        mSqlDb = mDbHelper.getWritableDatabase();
        mCursor = getAllSavedMovies();

        attachAdapter();

        return rootView;
    }

    private Cursor getAllSavedMovies() {
        return mSqlDb.query(
                DbContract.SavedMoviesEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public void addToDbTable(Movie movie) {
        List<ContentValues> listOfValues = new ArrayList<>();

        ContentValues cv = new ContentValues();
        cv.put(DbContract.SavedMoviesEntry.COLUMN_POSTER_PATH, movie.getPosterPath());
        cv.put(DbContract.SavedMoviesEntry.COLUMN_TITLE, movie.getTitle());
        cv.put(DbContract.SavedMoviesEntry.COLUMN_RATING, movie.getRating());
        cv.put(DbContract.SavedMoviesEntry.COLUMN_GENRES, movie.getGenres().get(FIRST_GENRE));
        listOfValues.add(cv);

        try {
            mSqlDb.beginTransaction();
            mSqlDb.insert(DbContract.SavedMoviesEntry.TABLE_NAME, null, cv);
            mSqlDb.setTransactionSuccessful();
        } catch(SQLException e) {
            Log.e(LOG_TAG, "Error in addToDbTable(...): " + e);
        } finally {
            mSqlDb.endTransaction();
        }
    }

    private void attachAdapter() {
        mRecyclerAdapter = initRVAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(Movie movie) {
        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
        detailIntent.putExtra(Utilities.PARCELABLE_MOVIE_KEY, movie);
        detailIntent.putExtra(Utilities.FROM, Utilities.PARENT);
        startActivity(detailIntent);
    }

    private SavedMoviesRVAdapter initRVAdapter() {
        return new SavedMoviesRVAdapter(getSavedMoviesSingleton(), mCursor, this);
    }

    private void onPullScreenDown() {
        mPullRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullRefreshLayout.setRefreshing(false);
            }
        });
    }
}
