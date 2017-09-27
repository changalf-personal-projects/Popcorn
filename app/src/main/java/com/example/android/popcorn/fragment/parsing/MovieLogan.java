package com.example.android.popcorn.fragment.parsing;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Private fields are keys of wanted values.
 */

@JsonObject
public class MovieLogan {

    @JsonField
    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    @JsonObject
    public static class Results {

        @JsonField(name = "poster_path")
        private String posterPath;

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }
    }
}
