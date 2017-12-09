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

    @JsonField(name = "original_language")
    private String originalLanguage;

    @JsonField(name = "original_title")
    private String title;

    @JsonField
    private String overview;

    @JsonField(name = "production_companies")
    private List<ProductionCompany> productionCompanies;

    @JsonObject
    public static class ProductionCompany {

        @JsonField
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

    @JsonField(name = "spoken_languages")
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

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

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

    @JsonField
    private Credits credits;

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    @JsonObject
    public static class Credits {

        @JsonField
        private List<Cast> cast;

        public List<Cast> getCast() {
            return cast;
        }

        public void setCast(List<Cast> cast) {
            this.cast = cast;
        }

        @JsonObject
        public static class Cast {

            @JsonField
            private String name;

            @JsonField(name = "profile_path")
            private String profilePath;

            @JsonField
            private String character;

            @JsonField
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProfilePath() {
                return profilePath;
            }

            public void setProfilePath(String profilePath) {
                this.profilePath = profilePath;
            }

            public String getCharacter() {
                return character;
            }

            public void setCharacter(String character) {
                this.character = character;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        @JsonField
        private List<Crew> crew;

        @JsonObject
        public static class Crew {

            @JsonField
            private String job;

            @JsonField
            private String name;

            @JsonField(name = "profile_path")
            private String profilePath;

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProfilePath() {
                return profilePath;
            }

            public void setProfilePath(String profilePath) {
                this.profilePath = profilePath;
            }
        }

        public List<Crew> getCrew() {
            return crew;
        }

        public void setCrew(List<Crew> crew) {
            this.crew = crew;
        }
    }

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

    @JsonField
    private Recommendations recommendations;

    public Recommendations getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Recommendations recommendations) {
        this.recommendations = recommendations;
    }

    @JsonObject
    public static class Recommendations {

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
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
