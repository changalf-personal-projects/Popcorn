package com.example.android.popcorn.fragment;

import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.networking.UriTerms;
import com.example.android.popcorn.networking.UrlCreator;
import com.example.android.popcorn.ui.poster_recyclerview.PosterRecyclerViewAdapter;

import java.util.List;

import static com.example.android.popcorn.model.singleton.PopularMoviesSingleton.getPopularMoviesSingleton;

/**
 * Created by alfredchang on 2017-09-16.
 */

public class PopularFragment extends ParentFragment {

    private final String LOG_TAG = PopularFragment.class.getSimpleName();

    @Override
    List<Movie> getSingletonList() {
        return getPopularMoviesSingleton();
    }

    @Override
    String createUrl() {
        return UrlCreator.createUrlWithCategory(UriTerms.POPULAR);
    }

    @Override
    PosterRecyclerViewAdapter initRecyclerViewAdapter() {
        return new PosterRecyclerViewAdapter(getPopularMoviesSingleton(), this, this);
    }

}
