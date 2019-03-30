package com.example.android.popcorn.fragment;

import com.example.android.popcorn.R;
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
    protected List<Movie> getSingletonList() {
        return getTopMoviesSingleton();
    }

    @Override
    protected String createUrl() {
        return UrlCreator.createUrlWithCategory(UriTerms.TOP);
    }

    @Override
    protected PosterRecyclerViewAdapter initRecyclerViewAdapter() {
        return new PosterRecyclerViewAdapter(getTopMoviesSingleton(), this, this);
    }

    @Override
    protected void sortMovies(int choice) {
        initializeMovieSorter();

        switch (choice) {
            case SORT_DEFAULT:
                setSortTitle(R.string.toolbar_sort_default);
                resetDefaultOrder();
                break;

            case SORT_TOP_RATED:
                setSortTitle(R.string.toolbar_sort_top);
                mMovieSorter.sortByRating(getTopMoviesSingleton());
                break;

            case SORT_NAME_ALPHABETICAL:
                setSortTitle(R.string.toolbar_sort_name);
                mMovieSorter.sortByName(getTopMoviesSingleton());
                break;

            case SORT_LONGEST_RUNTIME:
                setSortTitle(R.string.toolbar_sort_length);
                mMovieSorter.sortByRuntime(getTopMoviesSingleton());
                break;

            case SORT_NEWEST_RELEASE:
                setSortTitle(R.string.toolbar_sort_newest);
                mMovieSorter.sortByRelease(getTopMoviesSingleton());
                break;

            case SORT_HIGHEST_REVENUE:
                setSortTitle(R.string.toolbar_sort_revenue);
                mMovieSorter.sortByRevenue(getTopMoviesSingleton());
                break;

            case SORT_HIGHEST_PROFIT:
                setSortTitle(R.string.toolbar_sort_profit);
                mMovieSorter.sortByProfit(getTopMoviesSingleton());
                break;

            default:
                setSortTitle(R.string.toolbar_sort_default);
                resetDefaultOrder();
        }
    }

}
