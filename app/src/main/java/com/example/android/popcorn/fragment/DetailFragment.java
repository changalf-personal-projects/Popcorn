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

/**
 * Created by alfredchang on 2017-09-27.
 */

public class DetailFragment extends Fragment {

    private final String LOG_TAG = DetailFragment.class.getSimpleName();

    @BindView(R.id.backdrop_poster) ImageView mBackdrop;
    @BindView(R.id.movie_poster) ImageView mPoster;
    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.rating) TextView mRating;
    @BindView(R.id.genres) TextView mGenres;

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
        mRating.setText(movie.getRating());
    }

}
