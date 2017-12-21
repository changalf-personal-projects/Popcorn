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

public class FavouriteFragment extends Fragment implements OnMovieClickListener {


    private final String LOG_TAG = PopularFragment.class.getSimpleName();
    private final int LAYOUT_COL_SPAN = 2;

    private List<Movie> mListOfSavedMovies;
    private DbHelper mDbHelper;
    private SQLiteDatabase mSqlDb;
    private Cursor mCursor;
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
        mCursor = getSavedMoviesTable();

        initListOfSavedMovies();
        attachAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCursor.close();
        mSqlDb.close();
    }

    private void initListOfSavedMovies() {
        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                // Create new movie object.
                Movie savedMovie = new Movie();

                // Fill in main details of movie, using all details added to content values in DetailActivity.java.
                savedMovie.setBackdropPath(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_BACKDROP_PATH)));
                savedMovie.setPosterPath(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_POSTER_PATH)));
                savedMovie.setTitle(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_TITLE)));
                savedMovie.setRating(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_RATING)));
                savedMovie.setGenres(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_GENRES)));
                savedMovie.setRuntime(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_RUNTIME)));
                savedMovie.setReleaseDate(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_RELEASE)));
                savedMovie.setTagline(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_TAGLINE)));
                savedMovie.setOverview(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_OVERVIEW)));
                savedMovie.setLanguages(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_LANGUAGES)));
                savedMovie.setBudget(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_BUDGET)));
                savedMovie.setRevenue(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_REVENUE)));
                savedMovie.setProductionCompanies(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_PROD_COMPANIES)));

                Director director = new Director();
                director.setProfilePath(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_DIRECTOR_PHOTO_PATH)));
                director.setName(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_DIRECTOR_NAME)));
                savedMovie.setDirector(director);

                Producer producer = new Producer();
                producer.setProfilePath(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_PRODUCER_PHOTO_PATH)));
                producer.setName(mCursor.getString(mCursor.getColumnIndex(SavedMoviesEntry.COLUMN_PRODUCER_NAME)));
                savedMovie.setProducer(producer);

                // Add movie to list of saved movies.
                mListOfSavedMovies.add(savedMovie);
            } while (mCursor.moveToNext());
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
        return new SavedMoviesRVAdapter(mListOfSavedMovies, mCursor, this);
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
