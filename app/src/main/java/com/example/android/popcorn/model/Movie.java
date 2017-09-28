package com.example.android.popcorn.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredchang on 2017-09-21.
 */

public class Movie {

    private List<String> genres = new ArrayList<>();
    private String title;
    private String runtime;
    private String rating;
    private String synopsis;
    private String id;
    private String releaseDate;
    private String posterPath;
    private String backdropPath;

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

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
}
