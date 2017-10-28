package com.example.android.popcorn.fragment.parsing;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by alfredchang on 2017-10-04.
 */

@JsonObject
public class LoganTrailersTemplate extends MovieLogan {

    @JsonField
    private Videos videos;

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    @JsonObject
    public static class Videos {

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

            @JsonField
            private String key;

            @JsonField (name = "name")
            private String trailerDescription;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getTrailerDescription() {
                return trailerDescription;
            }

            public void setTrailerDescription(String trailerDescription) {
                this.trailerDescription = trailerDescription;
            }
        }
    }
}
