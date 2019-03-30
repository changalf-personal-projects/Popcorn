package com.example.android.popcorn.fragment;

import com.example.android.popcorn.R;
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
    protected List<Movie> getSingletonList() {
        return getPopularMoviesSingleton();
    }

    @Override
    protected String createUrl() {
        return UrlCreator.createUrlWithCategory(UriTerms.POPULAR);
    }

    @Override
    protected PosterRecyclerViewAdapter initRecyclerViewAdapter() {
        return new PosterRecyclerViewAdapter(getPopularMoviesSingleton(), this, this);
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
                mMovieSorter.sortByRating(getPopularMoviesSingleton());
                break;

            case SORT_NAME_ALPHABETICAL:
                setSortTitle(R.string.toolbar_sort_name);
                mMovieSorter.sortByName(getPopularMoviesSingleton());
                break;

            case SORT_LONGEST_RUNTIME:
                setSortTitle(R.string.toolbar_sort_length);
                mMovieSorter.sortByRuntime(getPopularMoviesSingleton());
                break;

            case SORT_NEWEST_RELEASE:
                setSortTitle(R.string.toolbar_sort_newest);
                mMovieSorter.sortByRelease(getPopularMoviesSingleton());
                break;

            case SORT_HIGHEST_REVENUE:
                setSortTitle(R.string.toolbar_sort_revenue);
                mMovieSorter.sortByRevenue(getPopularMoviesSingleton());
                break;

            case SORT_HIGHEST_PROFIT:
                setSortTitle(R.string.toolbar_sort_profit);
                mMovieSorter.sortByProfit(getPopularMoviesSingleton());
                break;

            default:
                setSortTitle(R.string.toolbar_sort_default);
                resetDefaultOrder();
        }
    }

}
