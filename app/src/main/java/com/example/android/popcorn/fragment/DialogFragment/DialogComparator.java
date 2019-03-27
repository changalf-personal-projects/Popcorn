package com.example.android.popcorn.fragment.DialogFragment;

import com.example.android.popcorn.model.Movie;

import java.util.Comparator;

public class DialogComparator {

    private static final String LOG_TAG = DialogComparator.class.getSimpleName();

    public static Comparator<Movie> BestToWorstComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return (int) ((Double.parseDouble(t1.getRating()) - Double.parseDouble(movie.getRating())) * 10.0);
        }
    };

    public static Comparator<Movie> NameComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return movie.getTitle().compareTo(t1.getTitle());
        }
    };

    public static Comparator<Movie> LongestRuntimeComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return (int) (Double.parseDouble(t1.getRuntime()) - Double.parseDouble(movie.getRuntime()));
        }
    };

    public static Comparator<Movie> NewestReleaseComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return t1.getReleaseDate().compareTo(movie.getReleaseDate());
        }
    };

    public static Comparator<Movie> HighestRevenueComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return Integer.parseInt(t1.getRevenue()) - Integer.parseInt(movie.getRevenue());
        }
    };

    public static Comparator<Movie> HighestProfitComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            int t1Profit = Integer.parseInt(t1.getRevenue()) - Integer.parseInt(t1.getBudget());
            int movieProfit = Integer.parseInt(movie.getRevenue()) - Integer.parseInt(movie.getBudget());

            return t1Profit - movieProfit;
        }
    };

}
