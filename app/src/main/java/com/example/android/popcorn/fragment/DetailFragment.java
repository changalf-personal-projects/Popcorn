package com.example.android.popcorn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.CastDetailActivity;
import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.fragment.parsing.LoganCastTemplate;
import com.example.android.popcorn.fragment.parsing.LoganTrailersTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.model.Trailer;
import com.example.android.popcorn.networking.RequestQueueSingleton;
import com.example.android.popcorn.networking.UriTerms;
import com.example.android.popcorn.ui.GlideApp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.Utilities.convertDoubleToString;
import static com.example.android.popcorn.Utilities.formatGenres;
import static com.example.android.popcorn.Utilities.roundToNearestTenth;
import static com.example.android.popcorn.networking.UrlCreator.createImageUrl;
import static com.example.android.popcorn.networking.UrlCreator.createUrlWithAppendedResponse;

/**
 * Created by alfredchang on 2017-09-27.
 */

public class DetailFragment extends Fragment {

    private final String LOG_TAG = DetailFragment.class.getSimpleName();

    @BindView(R.id.backdrop_poster) ImageView mBackdrop;
    @BindView(R.id.movie_poster) ImageView mPoster;
    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.rating) TextView mRating;
    @BindView(R.id.runtime) TextView mRuntime;
    @BindView(R.id.release) TextView mRelease;
    @BindView(R.id.genres) TextView mGenres;
    @BindView(R.id.synopsis) TextView mSynopsis;
    @BindView(R.id.cast_button) Button mCastButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_detail_main, container, false);
        ButterKnife.bind(this, rootView);

        Movie movie = getParcelableDetails();

        // TODO: Movie object is null after pressing back from CastDetailFragment.
        Log.v(LOG_TAG, "Movie is null? " + movie);

        setParcelableDetailsIntoViews(movie);
        fetchJsonCast(movie);
        fetchJsonTrailers(movie);
        onClickButtons(movie);

        return rootView;
    }

    private void onClickButtons(Movie movie) {
        onClickCastButton(movie);
    }

    private void onClickCastButton(final Movie movie) {
        mCastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent castDetailIntent = new Intent(getActivity(), CastDetailActivity.class);
                castDetailIntent.putExtra(Utilities.PARCELABLE_MOVIE_KEY, movie);
                castDetailIntent.putParcelableArrayListExtra(Utilities.PARCELABLE_CAST_KEY,
                        (ArrayList<? extends Parcelable>) movie.getCast());
                startActivity(castDetailIntent);
            }
        });
    }

    private Movie getParcelableDetails() {
        Intent detailIntent = getActivity().getIntent();
        Movie movie = detailIntent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);
        return movie;
    }

    private void setParcelableDetailsIntoViews(Movie movie) {
        setBackdrop(movie);
        setPoster(movie);
        setTitle(movie);
        setRating(movie);
        setRuntime(movie);
        setRelease(movie);
        setGenres(movie);
        setSynopsis(movie);
    }

    private void fetchJsonCast(final Movie movie) {
        String url = createUrlWithAppendedResponse(movie.getId(), UriTerms.CREDITS);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LoganCastTemplate castLogan = MovieParser.parseJsonCastData(response);
                        Log.v(LOG_TAG, "Cast id: " + response);
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

    private void fetchJsonTrailers(final Movie movie) {
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

    private void saveMovieCast(Movie movie, LoganCastTemplate castLogan) {
        for (LoganCastTemplate.Credits.Cast result: castLogan.getCredits().getCast()) {
            String profilePath = result.getProfilePath();
            Cast cast = new Cast();
            cast.setName(result.getName());
            cast.setCharacter(result.getCharacter());
            cast.setId(result.getId());

            if (profilePath != null) {
                cast.setProfilePath(createImageUrl(profilePath, UriTerms.CAST_PROFILE_PICTURE_SIZE));
            }

            movie.setCast(cast);
        }
    }

    private void saveMovieTrailers(Movie movie, LoganTrailersTemplate trailerLogan) {
        for (LoganTrailersTemplate.Videos.Results result: trailerLogan.getVideos().getResults()) {
            Trailer trailer = new Trailer();
            trailer.setKey(result.getKey());
            movie.setTrailers(trailer);
        }
    }

    private void setBackdrop(Movie movie) {
        GlideApp.with(getContext()).load(movie.getBackdropPath()).into(mBackdrop);
    }

    private void setPoster(Movie movie) {
        GlideApp.with(getContext()).load(movie.getDetailPosterPath()).into(mPoster);
    }

    private void setTitle(Movie movie) {
        mTitle.setText(movie.getTitle());
    }

    private void setRating(Movie movie) {
        double rating = roundToNearestTenth(movie);
        String ratingAsString = convertDoubleToString(rating);
        mRating.setText(getActivity().getResources().getString(R.string.rating_out_of_ten, ratingAsString));
    }

    private void setRuntime(Movie movie) {
        mRuntime.setText(getActivity().getResources().getString(R.string.runtime_plus_minutes, movie.getRuntime()));
    }

    private void setRelease(Movie movie) {
        mRelease.setText(movie.getReleaseDate());
    }

    private void setGenres(Movie movie) {
        mGenres.setText(formatGenres(movie.getGenres().toString()));
    }

    private void setSynopsis(Movie movie) {
        mSynopsis.setText(movie.getSynopsis());
    }
}
