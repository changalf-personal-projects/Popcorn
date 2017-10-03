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
import com.example.android.popcorn.model.Movie;

import butterknife.BindView;

/**
 * Created by alfredchang on 2017-09-27.
 */

public class DetailFragment extends Fragment {

    private final String LOG_TAG = DetailFragment.class.getSimpleName();

    @BindView(R.id.backdrop_poster) ImageView mBackdrop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_detail_main, container, false);

        fetchParcelable();

        return rootView;
    }

    private void fetchParcelable() {
        Intent detailIntent = getActivity().getIntent();
        Movie movie = detailIntent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);
    }

}
