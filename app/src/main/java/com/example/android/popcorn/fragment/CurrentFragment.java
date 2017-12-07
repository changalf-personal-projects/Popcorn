package com.example.android.popcorn.fragment;

import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.networking.UriTerms;
import com.example.android.popcorn.networking.UrlCreator;
import com.example.android.popcorn.ui.poster_recyclerview.PosterRecyclerViewAdapter;

import java.util.List;

import static com.example.android.popcorn.model.singleton.CurrentMoviesSingleton.getCurrentMoviesSingleton;

/**
 * Created by alfredchang on 2017-09-16.
 */

public class CurrentFragment extends ParentFragment {

    @Override
    List<Movie> getSingletonList() {
        return getCurrentMoviesSingleton();
    }

    @Override
    String createUrl() {
        return UrlCreator.createUrl(UriTerms.CURRENT);
    }

    @Override
    PosterRecyclerViewAdapter initRVAdapter() {
        return new PosterRecyclerViewAdapter(getCurrentMoviesSingleton(), this);
    }
}
