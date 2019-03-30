package com.example.android.popcorn.ui;

import com.example.android.popcorn.fragment.DialogFragment.DialogComparator;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.ui.poster_recyclerview.PosterRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieSorter {

    private final String LOG_TAG = MovieSorter.class.getSimpleName();
    
    private PosterRecyclerViewAdapter mRecyclerViewAdapter;
    
    public MovieSorter(PosterRecyclerViewAdapter recyclerViewAdapter) {
        mRecyclerViewAdapter = recyclerViewAdapter;
    }

    public void sortMovies(int sortChoice, int tabIndex) {

    }

    public void sortByRating(List<Movie> movieSingleton) {
        List<Movie> listOfMovies = new ArrayList<>();
        listOfMovies.addAll(movieSingleton);
        Collections.sort(listOfMovies, DialogComparator.BestToWorstComparator);

        mRecyclerViewAdapter.renewDataAfterSort(listOfMovies);
    }

    public void sortByName(List<Movie> movieSingleton) {
        List<Movie> listOfMovies = new ArrayList<>();
        listOfMovies.addAll(movieSingleton);
        Collections.sort(listOfMovies, DialogComparator.NameComparator);

        mRecyclerViewAdapter.renewDataAfterSort(listOfMovies);
    }

    public void sortByRuntime(List<Movie> movieSingleton) {
        List<Movie> listOfMovies = new ArrayList<>();
        listOfMovies.addAll(movieSingleton);
        Collections.sort(listOfMovies, DialogComparator.LongestRuntimeComparator);

        mRecyclerViewAdapter.renewDataAfterSort(listOfMovies);
    }

    public void sortByRelease(List<Movie> movieSingleton) {
        List<Movie> listOfMovies = new ArrayList<>();
        listOfMovies.addAll(movieSingleton);
        Collections.sort(listOfMovies, DialogComparator.NewestReleaseComparator);

        mRecyclerViewAdapter.renewDataAfterSort(listOfMovies);
    }

    public void sortByRevenue(List<Movie> movieSingleton) {
        List<Movie> listOfMovies = new ArrayList<>();
        listOfMovies.addAll(movieSingleton);
        Collections.sort(listOfMovies, DialogComparator.HighestRevenueComparator);

        mRecyclerViewAdapter.renewDataAfterSort(listOfMovies);
    }

    public void sortByProfit(List<Movie> movieSingleton) {
        List<Movie> listOfMovies = new ArrayList<>();
        listOfMovies.addAll(movieSingleton);
        Collections.sort(listOfMovies, DialogComparator.HighestProfitComparator);

        mRecyclerViewAdapter.renewDataAfterSort(listOfMovies);
    }

}
