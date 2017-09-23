package com.example.android.popcorn.fragment.parsing;

import java.util.List;

/**
 * Used with LoganSquare.
 */


public class MovieJackson {

    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public static class Results {

        private String posterPath;

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }
    }
}
