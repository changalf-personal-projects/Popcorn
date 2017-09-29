package com.example.android.popcorn.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popcorn.R;

/**
 * Created by alfredchang on 2017-09-27.
 */

public class DetailFragment extends Fragment {

    private final String LOG_TAG = DetailFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_detail_main, container, false);

        return rootView;
    }

}
