package com.example.android.popcorn.fragment.DialogFragment;

import android.util.Log;

import com.example.android.popcorn.model.Movie;

import java.util.Comparator;

public class DialogComparator {

    private static final String LOG_TAG = DialogComparator.class.getSimpleName();

    public static Comparator<Movie> BestToWorstComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            Log.d(LOG_TAG, "First movie: " + movie.getTitle() + "  Second movie: " + t1.getTitle());
            Log.d(LOG_TAG, "Final number: " + (int) ((Double.parseDouble(movie.getRating()) - Double.parseDouble(t1.getRating())) * 10.0));
            return (int) ((Double.parseDouble(t1.getRating()) - Double.parseDouble(movie.getRating())) * 10.0);
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
