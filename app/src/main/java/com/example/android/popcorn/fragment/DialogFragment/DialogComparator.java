package com.example.android.popcorn.fragment.DialogFragment;

import com.example.android.popcorn.model.Movie;

import java.util.Comparator;

public class DialogComparator {

    public static Comparator<Movie> BestToWorstComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return 0;
        }
    };

    public static Comparator<Movie> WorstToBestComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return 0;
        }
    };

    public static Comparator<Movie> NameCompparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return 0;
        }
    };

    public static Comparator<Movie> RuntimeComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return 0;
        }
    };

}
