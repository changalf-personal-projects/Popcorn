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
import com.example.android.popcorn.activity.IndividualCastDetailActivity;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.ui.cast_recyclerview.CastRecyclerViewAdapter;
import com.example.android.popcorn.ui.cast_recyclerview.OnCastMemberClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-11-13.
 */

public class CastFragment extends Fragment implements OnCastMemberClickListener {

    private Movie mMovie;
    private CastRecyclerViewAdapter mCastRecyclerAdapter;

    @BindView(R.id.cast_recycler_view)
    RecyclerView mCastRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_cast_main, container, false);
        ButterKnife.bind(this, rootView);

        mMovie = getParcelableMovie();
        setupCastRecyclerView();
        attachToCastAdapter();

        return rootView;
    }

    private Movie getParcelableMovie() {
        Intent movieIntent = getActivity().getIntent();
        Movie movie = movieIntent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);
        return movie;
    }

    private void setupCastRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
        LinearLayoutManager.VERTICAL, false);
        mCastRecyclerView.setLayoutManager(layoutManager);
    }

    private void attachToCastAdapter() {
        mCastRecyclerAdapter = new CastRecyclerViewAdapter(getActivity(), mMovie.getCast(), this);
        mCastRecyclerView.setAdapter(mCastRecyclerAdapter);
    }

    @Override
    public void onClick(Cast castMember) {
        Intent singleCastMemberDetailsIntent = new Intent(getActivity(), IndividualCastDetailActivity.class);
        singleCastMemberDetailsIntent.putExtra(Utilities.PARCELABLE_CAST_MEMBER_KEY, castMember);
        startActivity(singleCastMemberDetailsIntent);
    }
}
