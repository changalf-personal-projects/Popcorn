package com.example.android.popcorn.fragment;

import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.android.popcorn.R;
import com.example.android.popcorn.fragment.DialogFragment.DialogComparator;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.networking.UriTerms;
import com.example.android.popcorn.networking.UrlCreator;
import com.example.android.popcorn.ui.poster_recyclerview.PosterRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
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

    private PosterRecyclerViewAdapter initSortRecyclerViewAdapter(List<Movie> sortedListOfMovies) {
        return new PosterRecyclerViewAdapter(sortedListOfMovies, this, this);
    }

    @Override
    public void onClick(DialogFragment dialogFragment, int choice) {
        Log.d(LOG_TAG, "OnClick activated in PopularFragment.");
        sortMovies(choice, getPopularMoviesSingleton());
    }

    void sortMovies(int choice, List<Movie> listOfMovies) {
        switch (choice) {
            case SORT_TOP_RATED:
                sortTopRatedBasedOnTab(listOfMovies);

                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                TextView sortTitle = (TextView) toolbar.findViewById(R.id.sort_category);
                sortTitle.setText(R.string.toolbar_sort_top);

                break;

//            case SORT_NAME_ALPHABETICAL:
//                sortNameBasedOnTab(currentTabIndex);
//                break;
//
//            case SORT_LONGEST_RUNTIME:
//                sortLongestRuntimeBasedOnTab(currentTabIndex);
//                break;
//
//            case SORT_NEWEST_RELEASE:
//                sortNewestReleaseBasedOnTab(currentTabIndex);
//                break;
//
//            case SORT_HIGHEST_REVENUE:
//                sortHighestRevenueBasedOnTab(currentTabIndex);
//                break;
//
//            case SORT_HIGHEST_PROFIT:
//                sortHighestProfitBasedOnTab(currentTabIndex);
//                break;

            default:
                sortDefaultOrder();
        }
    }

    private void sortDefaultOrder() {
        initRecyclerViewAdapter();
    }

    void sortTopRatedBasedOnTab(List<Movie> movies) {
        List<Movie> listOfMovies = new ArrayList<>();
        listOfMovies.addAll(movies);
        Collections.sort(listOfMovies, DialogComparator.BestToWorstComparator);

        for (Movie movie : listOfMovies) {
            Log.d(LOG_TAG, "Movie title and rating: " + movie.getTitle() + " " + movie.getRating());
        }

        initSortRecyclerViewAdapter(listOfMovies);
    }

}
