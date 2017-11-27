package com.example.android.popcorn.fragment.parsing;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by alfredchang on 2017-09-28.
 */

@JsonObject
public class LoganDetailsTemplate extends MovieLogan {

    @JsonField(name = "backdrop_path")
    private String backdropPath;

    @JsonField
    private String budget;

    @JsonField
    private List<Genre> genres;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @JsonObject
    public static class Genre {

        @JsonField
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonField(name = "original_title")
    private String title;

    @JsonField(name = "overview")
    private String overview;

    @JsonField(name = "production_companies")
    private List<ProductionCompany> productionCompanies;

    @JsonObject
    public static class ProductionCompany {

        @JsonField(name = "name")
        private String company;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }
    }

    @JsonField(name = "poster_path")
    private String posterPath;

    @JsonField(name = "release_date")
    private String release;

    @JsonField
    private String revenue;

    @JsonField
    private String runtime;

    @JsonField(name = "spoken_language")
    private List<Language> languages;

    @JsonObject
    public static class Language {

        @JsonField(name = "name")
        private String language;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }

    @JsonField
    private String tagline;

    @JsonField(name = "vote_average")
    private String voteAverage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }
}
