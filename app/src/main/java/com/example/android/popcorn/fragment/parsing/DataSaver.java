package com.example.android.popcorn.fragment.parsing;

import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.networking.UriTerms;

import java.util.List;

import static com.example.android.popcorn.model.MoviesSingleton.getSingletonMovies;
import static com.example.android.popcorn.networking.UrlCreator.createImageUrl;

/**
 * This class saves all required movie info into an object's variables.
 */

public class DataSaver {

    public static void saveMovieId(LoganIdTemplate movieLogan) {
        List<Movie> movies = getSingletonMovies();

        for (LoganIdTemplate.Results result: movieLogan.getResults()) {
            Movie movie = new Movie();
            movie.setId(result.getId());
            movies.add(movie);
        }
    }

    public static void saveMovieDetails(LoganDetailsTemplate movieLogan, int index) {
        Movie movie = getSingletonMovies().get(index);

        // Saving all info to a movie object.
        for (LoganDetailsTemplate.Genre genre: movieLogan.getGenres()) {
            movie.setGenres(genre.getName());
        }
        movie.setTitle(movieLogan.getTitle());
        movie.setRuntime(movieLogan.getRuntime());
        movie.setRating(movieLogan.getVoteAverage());
        movie.setSynopsis(movieLogan.getSynopsis());
        movie.setReleaseDate(movieLogan.getRelease());
        movie.setPosterPath(createImageUrl(movieLogan.getPosterPath(), UriTerms.IMAGE_SIZE_W500));
        movie.setDetailPosterPath(createImageUrl(movieLogan.getPosterPath(), UriTerms.IMAGE_SIZE_W342));
        movie.setBackdropPath(createImageUrl(movieLogan.getBackdropPath(), UriTerms.POSTER_SIZE_ORIGINAL));

    }
}
