package com.example.android.popcorn.model;

/**
 * Created by alfredchang on 2017-09-28.
 */

public class Review {

    // TODO: Use "append_to_response" in TMDB.

    private String author;

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

    private String content;
}
