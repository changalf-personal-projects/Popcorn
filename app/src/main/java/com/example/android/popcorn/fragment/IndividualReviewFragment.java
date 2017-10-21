package com.example.android.popcorn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.model.Review;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-18.
 */

public class IndividualReviewFragment extends Fragment {

    @BindView(R.id.movie_poster) ImageView mMoviePoster;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_review_main, parent, false);
        ButterKnife.bind(this, rootView);

        Review review = getParcelableReview();

        return rootView;
    }

    private Review getParcelableReview() {
        Intent reviewIntent = getActivity().getIntent();
        Review review = reviewIntent.getParcelableExtra(Utilities.PARCELABLE_REVIEW_KEY);
        return review;
    }
}
