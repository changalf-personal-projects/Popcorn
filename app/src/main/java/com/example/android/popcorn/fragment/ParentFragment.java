package com.example.android.popcorn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.activity.DetailActivity;
import com.example.android.popcorn.dagger.component.FragmentComponent;
import com.example.android.popcorn.fragment.parsing.LoganCastTemplate;
import com.example.android.popcorn.fragment.parsing.LoganCreditsTemplate;
import com.example.android.popcorn.fragment.parsing.LoganDetailsTemplate;
import com.example.android.popcorn.fragment.parsing.LoganIdTemplate;
import com.example.android.popcorn.fragment.parsing.LoganReviewTemplate;
import com.example.android.popcorn.fragment.parsing.LoganTrailersTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.model.Director;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.model.Producer;
import com.example.android.popcorn.model.Review;
import com.example.android.popcorn.model.Trailer;
import com.example.android.popcorn.networking.UriTerms;
import com.example.android.popcorn.networking.UrlCreator;
import com.example.android.popcorn.networking.VolleyHelper;
import com.example.android.popcorn.networking.VolleyRequestHandler;
import com.example.android.popcorn.ui.poster_recyclerview.OnMovieClickListener;
import com.example.android.popcorn.ui.poster_recyclerview.PosterRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.networking.UrlCreator.createCreditsUrl;
import static com.example.android.popcorn.networking.UrlCreator.createImageUrl;
import static com.example.android.popcorn.networking.UrlCreator.createRecommendedMoviesUrl;
import static com.example.android.popcorn.networking.UrlCreator.createUrlWithAppendedResponse;

/**
 * Created by alfredchang on 2017-12-06.
 */

public abstract class ParentFragment extends Fragment implements OnMovieClickListener {

    private final String LOG_TAG = PopularFragment.class.getSimpleName();
    private final String ISO_ENGLISH = "en";
    private final String ENGLISH = "English";
    private final String DIRECTOR = "Director";
    private final String PRODUCER = "Producer";
    private final int LAYOUT_COL_SPAN = 2;

    private FragmentComponent mFragmentComponent;
    private VolleyRequestHandler mVolleyReqHandler;
    private VolleyHelper mVolleyHelper;
    private List<Movie> mListOfMovies;
    private List<Integer> mListOfRefreshColours = new ArrayList<>();
    PosterRecyclerViewAdapter mRecyclerAdapter;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.posters_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.pull_refresh_layout)
    SwipeRefreshLayout mPullRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        getRefreshColours();
        onPullScreenDown();

        mListOfMovies = getSingletonList();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), LAYOUT_COL_SPAN);
        mRecyclerView.setLayoutManager(layoutManager);

        initVolleyHandler();
        initVolleyHelper();
        fetchJsonId();
        fetchJsonDetails();

        // Call rest of fetchJson methods here in order.

        return rootView;
    }

    private void initVolleyHelper() {
        mVolleyHelper = new VolleyHelper(getActivity(), mVolleyReqHandler);
    }

    // Source: https://stackoverflow.com/questions/35628142/how-to-make-separate-class-for-volley-
    // library-and-call-all-method-of-volley-from.
    private void initVolleyHandler() {
        mVolleyReqHandler = new VolleyRequestHandler() {
            @Override
            public void onSuccessId(String response) {
                LoganIdTemplate loganId = MovieParser.parseJsonIdData(response);
                saveMovieId(loganId);
            }

            @Override
            public void onSuccessDetails(String response, Movie movie) {
                LoganDetailsTemplate loganDetails = MovieParser.parseJsonDetailsData(response);
                saveMovieDetails(movie, loganDetails);
            }

            @Override
            public void onSuccessTrailers(String response, Movie movie) {
                LoganTrailersTemplate loganTrailer = MovieParser.parseJsonTrailersData(response);
                saveMovieTrailers(movie, loganTrailer);
            }

            @Override
            public void onSuccessCrew(String response, Movie movie) {
                LoganCreditsTemplate loganCrew = MovieParser.parseJsonCreditsData(response);
                saveMovieCredits(movie, loganCrew);
            }

            @Override
            public void onSuccessCredits(String response, Movie movie) {
                LoganCastTemplate loganCast = MovieParser.parseJsonCastData(response);
                saveMovieCredits(movie, loganCast);
            }

            @Override
            public void onSuccessReviews(String response, Movie movie) {
                LoganReviewTemplate loganReviews = MovieParser.parseJsonReviewsData(response);
                saveMovieReviews(movie, loganReviews);
            }

            @Override
            public void onSuccessRecommendedId(String response, int index) {
                LoganIdTemplate loganId = MovieParser.parseJsonIdData(response);
                saveRecMovieId(loganId, index);
            }

            @Override
            public void onSuccessRecommendedDetails(String response, Movie movie) {
                LoganDetailsTemplate loganDetails = MovieParser.parseJsonDetailsData(response);
                saveRecMovieDetails(loganDetails, movie);
            }

            @Override
            public void onFail(VolleyError error) {
                Log.e(LOG_TAG, "initVolleyHandler() error: " + error);
            }
        };
    }

    private void fetchJsonId() {
        String url = createUrl();
        mVolleyHelper.fetchJsonId(url);
    }

    private void fetchJsonDetails() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            String url = UrlCreator.createUrl(mListOfMovies.get(i).getId());
            Movie movie = mListOfMovies.get(i);
            mVolleyHelper.fetchJsonDetails(url, movie);
        }
    }

    private void fetchJsonTrailers() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            Movie movie = mListOfMovies.get(i);
            String url = createUrlWithAppendedResponse(movie.getId(), UriTerms.VIDEOS);
            mVolleyHelper.fetchJsonTrailers(url, movie);
        }
    }

    private void fetchJsonReviews() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            Movie movie = mListOfMovies.get(i);
            String url = createUrlWithAppendedResponse(movie.getId(), UriTerms.REVIEWS);
            mVolleyHelper.fetchJsonReviews(url, movie);
        }
    }

    private void fetchJsonCredits() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            Movie movie = mListOfMovies.get(i);
            String url = createUrlWithAppendedResponse(movie.getId(), UriTerms.CREDITS);
            mVolleyHelper.fetchJsonCredits(url, movie);
        }
    }

    private void fetchJsonCrew() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            Movie movie = mListOfMovies.get(i);
            String url = createCreditsUrl(movie.getId());
            mVolleyHelper.fetchJsonCrew(url, movie);
        }
    }

    private void fetchJsonRecId() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            Movie movie = mListOfMovies.get(i);
            String url = createRecommendedMoviesUrl(movie.getId());
            mVolleyHelper.fetchJsonRecommendedId(url, i);
        }
    }

    private void fetchRecJsonDetails() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            Movie movie = mListOfMovies.get(i);
            for (int j = 0; j < mListOfMovies.get(i).getRecMovies().size(); j++) {
                Movie recMovie = movie.getRecMovies().get(j);
                String url = UrlCreator.createUrl(recMovie.getId());
                mVolleyHelper.fetchJsonRecommendedDetails(url, recMovie);
            }
        }
    }

    private void saveMovieId(LoganIdTemplate movieLogan) {
        for (LoganIdTemplate.Results result: movieLogan.getResults()) {
            Movie movie = new Movie();
            movie.setId(result.getId());
            mListOfMovies.add(movie);
        }
        fetchData();
    }

    private void saveRecMovieId(LoganIdTemplate movieLogan, int position) {
        for (LoganIdTemplate.Results result: movieLogan.getResults()) {
            Movie movie = new Movie();
            movie.setId(result.getId());
            mListOfMovies.get(position).setRecMovies(movie);
        }

        fetchRecJsonDetails();
    }

    private void fetchData() {
        fetchJsonDetails();
        fetchJsonCredits();
        fetchJsonCrew();
        fetchJsonReviews();
        fetchJsonTrailers();
        fetchJsonRecId();
    }

    private void saveMovieTrailers(Movie movie, LoganTrailersTemplate trailerLogan) {
        for (LoganTrailersTemplate.Videos.Results result: trailerLogan.getVideos().getResults()) {
            Trailer trailer = new Trailer();
            trailer.setKey(result.getKey());
            trailer.setTrailerDescription(result.getTrailerDescription());
            movie.setTrailerIds(result.getKey());
            movie.setTrailers(trailer);
        }
    }

    private void saveMovieCredits(Movie movie, LoganCastTemplate castLogan) {
        for (LoganCastTemplate.Credits.Cast result: castLogan.getCredits().getCast()) {
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

    private void saveMovieCredits(Movie movie, LoganCreditsTemplate creditsLogan) {
        for (LoganCreditsTemplate.Crew crewMember: creditsLogan.getCrew()) {
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
    private void saveMovieDetails(Movie movie, LoganDetailsTemplate movieLogan) {
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

        attachAdapter();
    }

    private void saveRecMovieDetails(LoganDetailsTemplate movieLogan, Movie movie) {
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

    private void saveMovieReviews(Movie movie, LoganReviewTemplate reviewLogan) {
        for (LoganReviewTemplate.Reviews.Results result: reviewLogan.getReviews().getResults()) {
            Review review = new Review();
            review.setAuthor(result.getAuthor());
            review.setContent(result.getContent());
            movie.setReviews(review);
        }
    }

    private void attachAdapter() {
        mRecyclerAdapter = initRVAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(Movie movie) {
        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
        detailIntent.putExtra(Utilities.PARCELABLE_MOVIE_KEY, movie);
        startActivity(detailIntent);
    }

    private void onPullScreenDown() {
        configureWheelColours();
        mPullRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRecyclerAdapter.clearData();
                fetchJsonId();
                mRecyclerAdapter.renewData(mListOfMovies);
                mPullRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getRefreshColours() {
        mListOfRefreshColours.add(android.R.color.holo_red_dark);
        mListOfRefreshColours.add(android.R.color.holo_green_dark);
        mListOfRefreshColours.add(android.R.color.holo_blue_dark);
        mListOfRefreshColours.add(android.R.color.holo_orange_dark);
    }

    private void configureWheelColours() {
        for (int colour: mListOfRefreshColours) {
            mPullRefreshLayout.setColorSchemeResources(colour);
        }
    }

    private boolean isDirector(LoganCreditsTemplate.Crew crewMember) {
        if (crewMember.getJob().equals(DIRECTOR)) {
            return true;
        }
        return false;
    }

    private boolean isProducer(LoganCreditsTemplate.Crew crewMember) {
        if (crewMember.getJob().equals(PRODUCER)) {
            return true;
        }
        return false;
    }

    abstract List<Movie> getSingletonList();

    abstract String createUrl();

    abstract PosterRecyclerViewAdapter initRVAdapter();
}
