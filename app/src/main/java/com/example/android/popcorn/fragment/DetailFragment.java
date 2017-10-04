package com.example.android.popcorn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.ui.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.Utilities.convertDoubleToString;
import static com.example.android.popcorn.Utilities.formatDate;
import static com.example.android.popcorn.Utilities.formatGenres;
import static com.example.android.popcorn.Utilities.roundToNearestTenth;

/**
 * Created by alfredchang on 2017-09-27.
 */

public class DetailFragment extends Fragment {

    private final String LOG_TAG = DetailFragment.class.getSimpleName();

    @BindView(R.id.backdrop_poster) ImageView mBackdrop;
    @BindView(R.id.movie_poster) ImageView mPoster;
    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.rating) TextView mRating;
    @BindView(R.id.runtime) TextView mRuntime;
    @BindView(R.id.release) TextView mRelease;
    @BindView(R.id.genres) TextView mGenres;
    @BindView(R.id.cast) TextView mCast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_detail_main, container, false);
        ButterKnife.bind(this, rootView);

        retrieveParcelable();

        return rootView;
    }

    private void retrieveParcelable() {
        Intent detailIntent = getActivity().getIntent();
        Movie movie = detailIntent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);

        setBackdrop(movie);
        setPoster(movie);
        setTitle(movie);
        setRating(movie);
        setRuntime(movie);
        setRelease(movie);
        setGenres(movie);
    }

    private void setBackdrop(Movie movie) {
        GlideApp.with(getContext()).load(movie.getBackdropPath()).into(mBackdrop);
    }

    private void setPoster(Movie movie) {
        GlideApp.with(getContext()).load(movie.getDetailPosterPath()).into(mPoster);
    }

    private void setTitle(Movie movie) {
        mTitle.setText(movie.getTitle());
    }

    private void setRating(Movie movie) {
        double rating = roundToNearestTenth(movie);
        String ratingAsString = convertDoubleToString(rating);
        mRating.setText(getActivity().getResources().getString(R.string.rating_out_of_ten, ratingAsString));
    }

    private void setRuntime(Movie movie) {
        mRuntime.setText(getActivity().getResources().getString(R.string.runtime_plus_minutes, movie.getRuntime()));
    }

    private void setRelease(Movie movie) {
        mRelease.setText(movie.getReleaseDate());
    }

    private void setGenres(Movie movie) {
        mGenres.setText(formatGenres(movie.getGenres().toString()));
    }

    private void setCast(Movie movie) {

    }

}
