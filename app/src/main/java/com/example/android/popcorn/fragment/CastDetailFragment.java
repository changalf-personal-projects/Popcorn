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
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.ui.cast_recyclerview.CastRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-06.
 */

public class CastDetailFragment extends Fragment {

    private CastRecyclerViewAdapter mRecyclerAdapter;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_cast_detail_main, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        List<Cast> castMembers = getParcelableDetails();
        attachToAdapter(castMembers);

        return rootView;
    }

    private List<Cast> getParcelableDetails() {
        Intent castDetailIntent = getActivity().getIntent();
        List<Cast> castMembers = castDetailIntent.getParcelableArrayListExtra(Utilities.PARCELABLE_CAST_KEY);
        return castMembers;
    }

    private void attachToAdapter(List<Cast> castMembers) {
        mRecyclerAdapter = new CastRecyclerViewAdapter(getActivity(), castMembers);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
}