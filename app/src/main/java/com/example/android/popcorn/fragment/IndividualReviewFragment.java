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
import com.example.android.popcorn.model.Review;
import com.example.android.popcorn.ui.ViewPopulator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-18.
 */

public class IndividualReviewFragment extends Fragment {

    private final int POSTER_CROSSFADE_TIME = 300;

    @BindView(R.id.movie_poster) ImageView mMoviePoster;
    @BindView(R.id.review_author) TextView mReviewAuthor;
    @BindView(R.id.review_content) TextView mReviewContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_single_review_main, parent, false);
        ButterKnife.bind(this, rootView);

        Intent intent = getActivity().getIntent();
        Movie movie = getParcelableMovie(intent);
        Review review = getParcelableReview(intent);

        setParcelableDetailsIntoViews(movie, review);

        return rootView;
    }

    private Movie getParcelableMovie(Intent intent) {
        Movie movie = intent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);
        return movie;
    }

    private Review getParcelableReview(Intent intent) {
        Review review = intent.getParcelableExtra(Utilities.PARCELABLE_REVIEW_KEY);
        return review;
    }

    private void setParcelableDetailsIntoViews(Movie movie, Review review) {
        ViewPopulator.populateImageView(getActivity(), movie.getDetailPosterPath(), POSTER_CROSSFADE_TIME, mMoviePoster);
        ViewPopulator.populateTextView(review.getAuthor(), mReviewAuthor);
        ViewPopulator.populateTextView(review.getContent(), mReviewContent);
    }
}
