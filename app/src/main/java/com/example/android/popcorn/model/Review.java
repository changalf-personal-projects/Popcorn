package com.example.android.popcorn.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alfredchang on 2017-09-28.
 */

public class Review implements Parcelable {

    // TODO: Use "append_to_response" in TMDB.

    private String author;
    private String content;

    public Review() {

    }

    protected Review(Parcel in) {
        author = in.readString();
        content = in.readString();
    }

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}
