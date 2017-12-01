package com.example.android.popcorn.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredchang on 2017-09-21.
 */

public class Movie implements Parcelable {

    private final int NO_FLAG = 0;

    private List<String> genres = new ArrayList<>();
    private List<String> productionCompanies = new ArrayList<>();
    private List<String> languages = new ArrayList<>();
    private String budget;
    private String title;
    private String runtime;
    private String rating;
    private String originalLanguage;
    private String overview;
    private String id;
    private String releaseDate;
    private String revenue;
    private String tagline;
    private String posterPath;
    private String detailPosterPath;
    private String backdropPath;
    private Director director;
    private Producer producer;

    private List<Cast> cast = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private List<Trailer> trailers = new ArrayList<>();
    private List<String> trailerIds = new ArrayList<String>();

    public Movie() {

    }

    protected Movie(Parcel in) {
        genres = in.createStringArrayList();
        productionCompanies = in.createStringArrayList();
        languages = in.createStringArrayList();
        budget = in.readString();
        title = in.readString();
        runtime = in.readString();
        rating = in.readString();
        originalLanguage = in.readString();
        overview = in.readString();
        id = in.readString();
        releaseDate = in.readString();
        revenue = in.readString();
        tagline = in.readString();
        posterPath = in.readString();
        detailPosterPath = in.readString();
        backdropPath = in.readString();
        director = in.readParcelable(Director.class.getClassLoader());
        producer = in.readParcelable(Producer.class.getClassLoader());
        in.readList(cast, Cast.class.getClassLoader());
        in.readList(reviews, Review.class.getClassLoader());
        in.readList(trailers, Trailer.class.getClassLoader());
        in.readList(trailerIds, String.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(genres);
        parcel.writeStringList(productionCompanies);
        parcel.writeStringList(languages);
        parcel.writeString(budget);
        parcel.writeString(title);
        parcel.writeString(runtime);
        parcel.writeString(rating);
        parcel.writeString(originalLanguage);
        parcel.writeString(overview);
        parcel.writeString(id);
        parcel.writeString(releaseDate);
        parcel.writeString(revenue);
        parcel.writeString(tagline);
        parcel.writeString(posterPath);
        parcel.writeString(detailPosterPath);
        parcel.writeString(backdropPath);
        parcel.writeParcelable(director, NO_FLAG);
        parcel.writeParcelable(producer, NO_FLAG);
        parcel.writeList(cast);
        parcel.writeList(reviews);
        parcel.writeList(trailers);
        parcel.writeList(trailerIds);
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

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(String genre) {
        genres.add(genre);
    }

    public List<String> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(String company) {
        productionCompanies.add(company);
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(String language) {
        languages.add(language);
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

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Review review) {
        reviews.add(review);
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(Trailer trailer) {
        trailers.add(trailer);
    }

    public List<String> getTrailerIds() {
        return trailerIds;
    }

    public void setTrailerIds(String id) {
        trailerIds.add(id);
    }

    // Method left alone because this class has no children.
    @Override
    public int describeContents() {
        return hashCode();
    }
}
