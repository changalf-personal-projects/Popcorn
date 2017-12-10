package com.example.android.popcorn.fragment.saving;

import android.util.Log;

import com.example.android.popcorn.fragment.ParentFragment;
import com.example.android.popcorn.fragment.parsing.LoganDetailsTemplate;
import com.example.android.popcorn.fragment.parsing.LoganIdTemplate;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.model.Director;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.model.Producer;
import com.example.android.popcorn.model.Review;
import com.example.android.popcorn.model.Trailer;
import com.example.android.popcorn.networking.UriTerms;

import java.util.List;

import static com.example.android.popcorn.networking.UrlCreator.createImageUrl;

/**
 * Created by alfredchang on 2017-12-09.
 */

public class DataSaver {

    private final String LOG_TAG = DataSaver.class.getSimpleName();
    private final String ISO_ENGLISH = "en";
    private final String ENGLISH = "English";
    private final String DIRECTOR = "Director";
    private final String PRODUCER = "Producer";

    private ParentFragment mFragment;
    private List<Movie> mListOfMovies;

    public DataSaver(ParentFragment fragment, List<Movie> listOfMovies) {
        mFragment = fragment;
        mListOfMovies = listOfMovies;
    }

    public void saveMovieId(LoganIdTemplate movieLogan) {
        for (LoganIdTemplate.Results result: movieLogan.getResults()) {
            Movie movie = new Movie();
            movie.setId(result.getId());
            mListOfMovies.add(movie);
        }
        mFragment.fetchJsonDetails();
    }

    public void saveRecMovieId(Movie movie, LoganDetailsTemplate movieLogan) {
        for (LoganDetailsTemplate.Recommendations.Results result: movieLogan.getRecommendations().getResults()) {
            Movie recommendedMovie = new Movie();
            recommendedMovie.setId(result.getId());
            movie.setRecMovies(recommendedMovie);
        }

        mFragment.fetchRecJsonDetails(movie);
    }

    public void saveMovieTrailers(Movie movie, LoganDetailsTemplate trailerLogan) {
        for (LoganDetailsTemplate.Videos.Results result: trailerLogan.getVideos().getResults()) {
            Trailer trailer = new Trailer();
            trailer.setKey(result.getKey());
            trailer.setTrailerDescription(result.getTrailerDescription());
            movie.setTrailerIds(result.getKey());
            movie.setTrailers(trailer);
        }
    }

    public void saveMovieCast(Movie movie, LoganDetailsTemplate castLogan) {
        for (LoganDetailsTemplate.Credits.Cast result: castLogan.getCredits().getCast()) {
            Cast cast = new Cast();
            cast.setName(result.getName());
            cast.setCharacter(result.getCharacter());
            cast.setId(result.getId());

            String profilePath = result.getProfilePath();
            if (profilePath != null) {
                cast.setProfilePath(createImageUrl(profilePath, UriTerms.IMAGE_SIZE_W185));
            }

            movie.getCast().add(cast);
        }
    }

    public void saveMovieCrew(Movie movie, LoganDetailsTemplate creditsLogan) {
        for (LoganDetailsTemplate.Credits.Crew crewMember: creditsLogan.getCredits().getCrew()) {
            if (isDirector(crewMember)) {
                Director director = new Director();
                director.setName(crewMember.getName());
                director.setProfilePath(createImageUrl(crewMember.getProfilePath(), UriTerms.IMAGE_SIZE_W185));
                movie.setDirector(director);
            }
            if (isProducer(crewMember)) {
                Producer producer = new Producer();
                producer.setName(crewMember.getName());
                producer.setProfilePath(createImageUrl(crewMember.getProfilePath(), UriTerms.IMAGE_SIZE_W185));
                movie.setProducer(producer);
            }
        }
    }

    // AttachAdapter method needs to be done after all required info has been saved to mListOfMovies object.
    public void saveMovieDetails(Movie movie, LoganDetailsTemplate movieLogan) {
        for (LoganDetailsTemplate.Genre genre: movieLogan.getGenres()) {
            movie.setGenres(genre.getName());
        }

        for (LoganDetailsTemplate.ProductionCompany company: movieLogan.getProductionCompanies()) {
            movie.setProductionCompanies(company.getName());
        }

        movie.setBudget(movieLogan.getBudget());
        movie.setTitle(movieLogan.getTitle());
        movie.setRuntime(movieLogan.getRuntime());
        movie.setRating(movieLogan.getVoteAverage());

        // This ordering of for-loop after setting original language allows original language to be
        // displayed first in languages.
        if (movieLogan.getOriginalLanguage().equals(ISO_ENGLISH)) {
            movie.setLanguages(ENGLISH);
        }

        for (LoganDetailsTemplate.Language language: movieLogan.getLanguages()) {
            movie.setLanguages(language.getLanguage());
        }

        movie.setOverview(movieLogan.getOverview());
        movie.setReleaseDate(movieLogan.getRelease());
        movie.setRevenue(movieLogan.getRevenue());
        movie.setTagline(movieLogan.getTagline());
        movie.setPosterPath(createImageUrl(movieLogan.getPosterPath(), UriTerms.IMAGE_SIZE_W500));
        movie.setDetailPosterPath(createImageUrl(movieLogan.getPosterPath(), UriTerms.IMAGE_SIZE_W342));
        movie.setBackdropPath(createImageUrl(movieLogan.getBackdropPath(), UriTerms.POSTER_SIZE_ORIGINAL));
    }

    public void saveRecMovieDetails(LoganDetailsTemplate movieLogan, Movie movie) {
//        Movie movie = mListOfMovies.get(movieIndex).getRecMovies().get(recMovieIndex);

//        for (LoganDetailsTemplate.Genre genre: movieLogan.getGenres()) {
//            movie.setGenres(genre.getName());
//        }
//
//        for (LoganDetailsTemplate.ProductionCompany company: movieLogan.getProductionCompanies()) {
//            movie.setProductionCompanies(company.getName());
//        }
//
//        movie.setBudget(movieLogan.getBudget());
        movie.setTitle(movieLogan.getTitle());
//        movie.setRuntime(movieLogan.getRuntime());
//        movie.setRating(movieLogan.getVoteAverage());

        // This ordering of for-loop after setting original language allows original language to be
        // displayed first in languages.
//        if (movieLogan.getOriginalLanguage().equals(ISO_ENGLISH)) {
//            movie.setLanguages(ENGLISH);
//        }
//
//        for (LoganDetailsTemplate.Language language: movieLogan.getLanguages()) {
//            movie.setLanguages(language.getLanguage());
//        }
//
//        movie.setOverview(movieLogan.getOverview());
        movie.setReleaseDate(movieLogan.getRelease());
//        movie.setRevenue(movieLogan.getRevenue());
//        movie.setTagline(movieLogan.getTagline());
//        movie.setPosterPath(createImageUrl(movieLogan.getPosterPath(), UriTerms.IMAGE_SIZE_W500));
        movie.setDetailPosterPath(createImageUrl(movieLogan.getPosterPath(), UriTerms.IMAGE_SIZE_W342));
//        movie.setBackdropPath(createImageUrl(movieLogan.getBackdropPath(), UriTerms.POSTER_SIZE_ORIGINAL));

    }

    public void saveMovieReviews(Movie movie, LoganDetailsTemplate reviewLogan) {
        Log.v(LOG_TAG, "Movie name: " + movie);
        Log.v(LOG_TAG, "Parsed response: " + reviewLogan.getReviews());

        for (LoganDetailsTemplate.Reviews.Results result: reviewLogan.getReviews().getResults()) {
            Review review = new Review();
            review.setAuthor(result.getAuthor());
            review.setContent(result.getContent());
            movie.setReviews(review);
        }
    }

    public void saveAllData(Movie movie, LoganDetailsTemplate loganDetails) {
        saveMovieDetails(movie, loganDetails);
        saveMovieCast(movie, loganDetails);
        saveMovieCrew(movie, loganDetails);
        saveMovieTrailers(movie, loganDetails);
        saveMovieReviews(movie, loganDetails);
        saveRecMovieId(movie, loganDetails);
    }

    private boolean isDirector(LoganDetailsTemplate.Credits.Crew crewMember) {
        if (crewMember.getJob().equals(DIRECTOR)) {
            return true;
        }
        return false;
    }

    private boolean isProducer(LoganDetailsTemplate.Credits.Crew crewMember) {
        if (crewMember.getJob().equals(PRODUCER)) {
            return true;
        }
        return false;
    }
}
