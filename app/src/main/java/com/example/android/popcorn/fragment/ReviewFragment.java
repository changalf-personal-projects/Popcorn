package com.example.android.popcorn.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popcorn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-28.
 */

public class ReviewFragment extends Fragment {

    @BindView(R.id.review_recycler_view)
    RecyclerView mReviewRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_reviews_main, container, false);
        ButterKnife.bind(this, rootView);

        setupReviewRecyclerView();

        return rootView;
    }

    private void setupReviewRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mReviewRecyclerView.setNestedScrollingEnabled(false);
        mReviewRecyclerView.setLayoutManager(layoutManager);
    }
}
