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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-28.
 */

public class ReviewFragment extends Fragment implements OnReviewClickListener {

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
        attachReviewsAdapter();

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

    private void attachReviewsAdapter() {
        mReviewRecyclerAdapter = new ReviewRecyclerViewAdapter(getActivity(), mMovie.getReviews(), this);
        mReviewRecyclerView.setAdapter(mReviewRecyclerAdapter);
    }

    @Override
    public void onClick(Review review) {
        Intent singleReviewIntent = new Intent(getActivity(), IndividualReviewActivity.class);
        singleReviewIntent.putExtra(Utilities.PARCELABLE_REVIEW_KEY, review);
        startActivity(singleReviewIntent);
    }
}
