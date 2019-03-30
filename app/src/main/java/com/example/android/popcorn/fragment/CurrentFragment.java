package com.example.android.popcorn.fragment;

import com.example.android.popcorn.R;
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
    protected List<Movie> getSingletonList() {
        return getCurrentMoviesSingleton();
    }

    @Override
    protected String createUrl() {
        return UrlCreator.createUrlWithCategory(UriTerms.CURRENT);
    }

    @Override
    protected PosterRecyclerViewAdapter initRecyclerViewAdapter() {
        return new PosterRecyclerViewAdapter(getCurrentMoviesSingleton(), this, this);
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
                mMovieSorter.sortByRating(getCurrentMoviesSingleton());
                break;

            case SORT_NAME_ALPHABETICAL:
                setSortTitle(R.string.toolbar_sort_name);
                mMovieSorter.sortByName(getCurrentMoviesSingleton());
                break;

            case SORT_LONGEST_RUNTIME:
                setSortTitle(R.string.toolbar_sort_length);
                mMovieSorter.sortByRuntime(getCurrentMoviesSingleton());
                break;

            case SORT_NEWEST_RELEASE:
                setSortTitle(R.string.toolbar_sort_newest);
                mMovieSorter.sortByRelease(getCurrentMoviesSingleton());
                break;

            case SORT_HIGHEST_REVENUE:
                setSortTitle(R.string.toolbar_sort_revenue);
                mMovieSorter.sortByRevenue(getCurrentMoviesSingleton());
                break;

            case SORT_HIGHEST_PROFIT:
                setSortTitle(R.string.toolbar_sort_profit);
                mMovieSorter.sortByProfit(getCurrentMoviesSingleton());
                break;

            default:
                setSortTitle(R.string.toolbar_sort_default);
                resetDefaultOrder();
        }
    }

}
