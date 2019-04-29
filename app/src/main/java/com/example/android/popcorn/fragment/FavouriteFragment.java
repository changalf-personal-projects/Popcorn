package com.example.android.popcorn.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.activity.DetailActivity;
import com.example.android.popcorn.data.DbContract;
import com.example.android.popcorn.data.DbContract.SavedMoviesEntry;
import com.example.android.popcorn.data.DbHelper;
import com.example.android.popcorn.model.Director;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.model.Producer;
import com.example.android.popcorn.model.Trailer;
import com.example.android.popcorn.ui.saved_movie_recyclerview.OnMovieClickListener;
import com.example.android.popcorn.ui.saved_movie_recyclerview.SavedMoviesRVAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.data.DbHelper.getDbInstance;

/**
 * Created by alfredchang on 2017-09-16.
 */

public class FavouriteFragment extends Fragment implements OnMovieClickListener, OnLongClickListener {


    private final String LOG_TAG = PopularFragment.class.getSimpleName();
    private final int LAYOUT_COL_SPAN = 2;

    private List<Movie> mListOfSavedMovies;
    private DbHelper mDbHelper;
    private SQLiteDatabase mSqlDb;

    private Cursor mMovieCursor;
    private Cursor mTrailerCursor;
    SavedMoviesRVAdapter mRecyclerAdapter;

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

        mListOfSavedMovies = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), LAYOUT_COL_SPAN);
        mRecyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        mDbHelper = getDbInstance(getActivity().getApplicationContext());
        mSqlDb = mDbHelper.getReadableDatabase();
        mMovieCursor = getSavedMoviesTable();
        mTrailerCursor = getTrailersTable();

        initListOfSavedMovies();
        attachAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMovieCursor.close();
        mTrailerCursor.close();
        mSqlDb.close();
    }

    private void initListOfSavedMovies() {
        Movie savedMovie = new Movie();

        if (mMovieCursor != null && mMovieCursor.moveToFirst()) {
            do {
                // Fill in main details of movie, using all details added to content values in DetailActivity.java.
                savedMovie.setBackdropPath(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_BACKDROP_PATH)));
                savedMovie.setPosterPath(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_POSTER_PATH)));
                savedMovie.setTitle(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_TITLE)));
                savedMovie.setRating(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_RATING)));
                savedMovie.setGenres(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_GENRES)));
                savedMovie.setRuntime(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_RUNTIME)));
                savedMovie.setReleaseDate(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_RELEASE)));
                savedMovie.setTagline(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_TAGLINE)));
                savedMovie.setOverview(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_OVERVIEW)));
                savedMovie.setLanguages(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_LANGUAGES)));
                savedMovie.setBudget(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_BUDGET)));
                savedMovie.setRevenue(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_REVENUE)));
                savedMovie.setProductionCompanies(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_PROD_COMPANIES)));

                Director director = new Director();
                director.setProfilePath(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_DIRECTOR_PHOTO_PATH)));
                director.setName(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_DIRECTOR_NAME)));
                savedMovie.setDirector(director);

                Producer producer = new Producer();
                producer.setProfilePath(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_PRODUCER_PHOTO_PATH)));
                producer.setName(mMovieCursor.getString(mMovieCursor.getColumnIndex(SavedMoviesEntry.COLUMN_PRODUCER_NAME)));
                savedMovie.setProducer(producer);

                if (mTrailerCursor != null && mTrailerCursor.moveToFirst()) {
                    do {
                        Trailer trailer = new Trailer();
                        trailer.setKey(mTrailerCursor.getString(mTrailerCursor.getColumnIndex(DbContract.TrailersEntry.COLUMN_KEY)));
                        trailer.setTrailerDescription(mTrailerCursor.getString(mTrailerCursor.getColumnIndex(DbContract.TrailersEntry.COLUMN_DETAIL)));
                        savedMovie.setTrailers(trailer);
                    } while (mTrailerCursor.moveToNext());
                }

                mListOfSavedMovies.add(savedMovie);
            } while (mMovieCursor.moveToNext());
        }
    }

    private Cursor getSavedMoviesTable() {
        return mSqlDb.query(DbContract.SavedMoviesEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private Cursor getTrailersTable() {
        return mSqlDb.query(DbContract.TrailersEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
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

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    private SavedMoviesRVAdapter initRVAdapter() {
        return new SavedMoviesRVAdapter(mListOfSavedMovies, mMovieCursor, this);
    }

    private void onPullScreenDown() {
        mPullRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                attachAdapter();
                mPullRefreshLayout.setRefreshing(false);
            }
        });
    }

}
