package com.example.android.popcorn.fragment.parsing;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by alfredchang on 2017-10-17.
 */

@JsonObject
public class LoganReviewTemplate extends MovieLogan {

    @JsonField
    private Reviews reviews;

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    @JsonObject
    public static class Reviews {

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
            private String author;

            @JsonField
            private String content;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
