package com.example.android.popcorn.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredchang on 2017-09-21.
 */

public class Movie implements Parcelable {

    private List<String> genres = new ArrayList<>();
    private String title;
    private String runtime;
    private String rating;
    private String synopsis;
    private String id;
    private String releaseDate;
    private String posterPath;
    private String detailPosterPath;
    private String backdropPath;

    private List<Trailer> trailers = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    public Movie() {

    }

    protected Movie(Parcel in) {
        genres = in.createStringArrayList();
        title = in.readString();
        runtime = in.readString();
        rating = in.readString();
        synopsis = in.readString();
        id = in.readString();
        releaseDate = in.readString();
        posterPath = in.readString();
        detailPosterPath = in.readString();
        backdropPath = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(String genre) {
        genres.add(genre);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getDetailPosterPath() {
        return detailPosterPath;
    }

    public void setDetailPosterPath(String detailPosterPath) {
        this.detailPosterPath = detailPosterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(Trailer trailer) {
        trailers.add(trailer);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Review review) {
        reviews.add(review);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(genres);
        parcel.writeString(title);
        parcel.writeString(runtime);
        parcel.writeString(rating);
        parcel.writeString(synopsis);
        parcel.writeString(id);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeString(detailPosterPath);
        parcel.writeString(backdropPath);
    }

    // Method left alone because this class has no children.
    @Override
    public int describeContents() {
        return hashCode();
    }
}
