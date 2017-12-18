package com.example.android.popcorn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.activity.IndividualReviewActivity;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.model.Review;
import com.example.android.popcorn.ui.review_recyclerview.OnReviewClickListener;
import com.example.android.popcorn.ui.review_recyclerview.ReviewRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-28.
 */

public class ReviewFragment extends Fragment implements OnReviewClickListener {

    public static final String NO_REVIEWS_MESSAGE = "No reviews posted yet.";
    private final int EMPTY = 0;
    private static final String EMPTY_STRING = "";

    private Movie mMovie;
    private ReviewRecyclerViewAdapter mReviewRecyclerAdapter;

    @BindView(R.id.review_recycler_view)
    RecyclerView mReviewRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_reviews_main, container, false);
        ButterKnife.bind(this, rootView);

        getParcelableMovie();
        setupReviewRecyclerView();
        attachToReviewsAdapter();

        return rootView;
    }

    private void setupReviewRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mReviewRecyclerView.setNestedScrollingEnabled(false);
        mReviewRecyclerView.setLayoutManager(layoutManager);
    }

    private void getParcelableMovie() {
        Intent movieIntent = getActivity().getIntent();
        mMovie = movieIntent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);
    }

    private void attachToReviewsAdapter() {
        List<Review> reviews = mMovie.getReviews();

        if (reviews.size() == EMPTY) {
            Review emptyReview = new Review();
            emptyReview.setAuthor(EMPTY_STRING);
            // This string will be used to signal onBindViewHolder() that there are no reviews yet.
            emptyReview.setContent(NO_REVIEWS_MESSAGE);
            reviews.add(emptyReview);
        }

        mReviewRecyclerAdapter = new ReviewRecyclerViewAdapter(getActivity(), mMovie.getReviews(), this);
        mReviewRecyclerView.setAdapter(mReviewRecyclerAdapter);
    }

    @Override
    public void onClick(Review review) {
        Intent singleReviewIntent = new Intent(getActivity(), IndividualReviewActivity.class);
        singleReviewIntent.putExtra(Utilities.PARCELABLE_MOVIE_KEY, mMovie);
        singleReviewIntent.putExtra(Utilities.PARCELABLE_REVIEW_KEY, review);
        startActivity(singleReviewIntent);
    }
}
