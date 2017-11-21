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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.activity.DetailActivity;
import com.example.android.popcorn.dagger.component.FragmentComponent;
import com.example.android.popcorn.fragment.parsing.LoganCastTemplate;
import com.example.android.popcorn.fragment.parsing.LoganDetailsTemplate;
import com.example.android.popcorn.fragment.parsing.LoganIdTemplate;
import com.example.android.popcorn.fragment.parsing.LoganReviewTemplate;
import com.example.android.popcorn.fragment.parsing.LoganTrailersTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.model.Review;
import com.example.android.popcorn.model.Trailer;
import com.example.android.popcorn.networking.RequestQueueSingleton;
import com.example.android.popcorn.networking.UriTerms;
import com.example.android.popcorn.ui.poster_recyclerview.OnMovieClickListener;
import com.example.android.popcorn.ui.poster_recyclerview.PosterRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.model.MoviesSingleton.getSingletonMovies;
import static com.example.android.popcorn.networking.UrlCreator.createImageUrl;
import static com.example.android.popcorn.networking.UrlCreator.createUrl;
import static com.example.android.popcorn.networking.UrlCreator.createUrlWithAppendedResponse;

/**
 * Created by alfredchang on 2017-09-16.
 */

public class PopularFragment extends Fragment implements OnMovieClickListener {

    private final String LOG_TAG = PopularFragment.class.getSimpleName();
    private final int LAYOUT_COL_SPAN = 2;

    private FragmentComponent mFragmentComponent;
    private PosterRecyclerViewAdapter mRecyclerAdapter;
    private List<Movie> mListOfMovies;
    private List<Integer> mListOfRefreshColours = new ArrayList<>();

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.cast_recyclerview) RecyclerView mRecyclerView;
    @BindView(R.id.pull_refresh_layout) SwipeRefreshLayout mPullRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        mListOfMovies = getSingletonMovies();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), LAYOUT_COL_SPAN);
        mRecyclerView.setLayoutManager(layoutManager);

        fetchJsonId();
        getRefreshColours();
        onPullScreenDown();

        return rootView;
    }

    // Can't be moved into another class because onResponse() doesn't return anything.
    private void fetchJsonId() {
        String url = createUrl(UriTerms.POPULAR);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LoganIdTemplate movieLogan = MovieParser.parseJsonIdData(response);
                        saveMovieId(movieLogan);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Response error (fetchJsonId): " + error);
            }
        });

        RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void fetchJsonDetails() {

        for (int i = 0; i < mListOfMovies.size(); i++) {
            String url = createUrl(mListOfMovies.get(i).getId());
            final int index = i;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            LoganDetailsTemplate movieLogan = MovieParser.parseJsonDetailsData(response);
                            saveMovieDetails(movieLogan, index);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(LOG_TAG, "Response error (fetchJsonDetails): " + error);
                }
            });

            RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
        }
    }

    private void fetchJsonCast() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            final Movie movie = this.mListOfMovies.get(i);
            String url = createUrlWithAppendedResponse(movie.getId(), UriTerms.CREDITS);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            LoganCastTemplate castLogan = MovieParser.parseJsonCastData(response);
                            saveMovieCast(movie, castLogan);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(LOG_TAG, "Response error (fetchJsonCast): " + error);
                }
            });

            RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
        }
    }

    private void fetchJsonReviews() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            final Movie movie = this.mListOfMovies.get(i);
            String url = createUrlWithAppendedResponse(movie.getId(), UriTerms.REVIEWS);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            LoganReviewTemplate reviewLogan = MovieParser.parseJsonReviewsData(response);
                            saveMovieReview(movie, reviewLogan);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(LOG_TAG, "Response error (fetchJsonReviews): " + error);
                }
            });

            RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
        }
    }

    private void fetchJsonTrailers() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            final Movie movie = this.mListOfMovies.get(i);
            String url = createUrlWithAppendedResponse(movie.getId(), UriTerms.VIDEOS);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            LoganTrailersTemplate trailerLogan = MovieParser.parseJsonTrailersData(response);
                            saveMovieTrailers(movie, trailerLogan);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(LOG_TAG, "Response error (fetchJsonCast): " + error);
                }
            });

            RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
        }
    }

    private void saveMovieId(LoganIdTemplate movieLogan) {
        for (LoganIdTemplate.Results result: movieLogan.getResults()) {
            Movie movie = new Movie();
            movie.setId(result.getId());
            mListOfMovies.add(movie);
        }

        fetchJsonDetails();
        fetchJsonCast();
        fetchJsonReviews();
        fetchJsonTrailers();
    }

    private void saveMovieTrailers(Movie movie, LoganTrailersTemplate trailerLogan) {
        for (LoganTrailersTemplate.Videos.Results result : trailerLogan.getVideos().getResults()) {
            Trailer trailer = new Trailer();
            trailer.setKey(result.getKey());
            trailer.setTrailerDescription(result.getTrailerDescription());
            movie.setTrailerIds(result.getKey());
            movie.setTrailers(trailer);
        }
    }

    private void saveMovieCast(Movie movie, LoganCastTemplate castLogan) {
        for (LoganCastTemplate.Credits.Cast result : castLogan.getCredits().getCast()) {
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

    // AttachAdapter method needs to be done after all required info has been saved to mListOfMovies object.
    private void saveMovieDetails(LoganDetailsTemplate movieLogan, int index) {
        Movie movie = mListOfMovies.get(index);

        // Saving all info to a mListOfMovies object.
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
        attachAdapter();
    }

    private void saveMovieReview(Movie movie, LoganReviewTemplate reviewLogan) {
        for (LoganReviewTemplate.Reviews.Results result: reviewLogan.getReviews().getResults()) {
            Review review = new Review();
            review.setAuthor(result.getAuthor());
            review.setContent(result.getContent());
            movie.setReviews(review);
        }
    }

    private void attachAdapter() {
        mRecyclerAdapter = new PosterRecyclerViewAdapter(getSingletonMovies(), this);
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
}
