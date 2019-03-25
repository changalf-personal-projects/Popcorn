package com.example.android.popcorn.fragment.DialogFragment;

import com.example.android.popcorn.model.Movie;

import java.util.Comparator;

public class DialogComparator {

    public static Comparator<Movie> bestToWorstComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return 0;
        }
    };

    public static Comparator<Movie> worstToBestComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return 0;
        }
    };

    public static Comparator<Movie> nameCompparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return 0;
        }
    };

    public static Comparator<Movie> runtimeComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return 0;
        }
    };

}
