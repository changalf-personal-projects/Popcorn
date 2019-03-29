package com.example.android.popcorn.fragment;

import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.networking.UriTerms;
import com.example.android.popcorn.networking.UrlCreator;
import com.example.android.popcorn.ui.poster_recyclerview.PosterRecyclerViewAdapter;

import java.util.List;

import static com.example.android.popcorn.model.singleton.TopMoviesSingleton.getTopMoviesSingleton;

/**
 * Created by alfredchang on 2017-09-16.
 */

public class TopFragment extends ParentFragment {

    @Override
    List<Movie> getSingletonList() {
        return getTopMoviesSingleton();
    }

    @Override
    String createUrl() {
        return UrlCreator.createUrlWithCategory(UriTerms.TOP);
    }

    @Override
    PosterRecyclerViewAdapter initRecyclerViewAdapter() {
        return new PosterRecyclerViewAdapter(getTopMoviesSingleton(), this, this);
    }

}
