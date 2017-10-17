package com.example.android.popcorn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android.popcorn.IndividualCastDetailActivity;
import com.example.android.popcorn.R;
import com.example.android.popcorn.TrailerActivity;
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
import com.example.android.popcorn.ui.cast_recyclerview.CastRecyclerViewAdapter;
import com.example.android.popcorn.ui.cast_recyclerview.OnCastMemberClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.Utilities.convertDoubleToString;
import static com.example.android.popcorn.Utilities.formatDate;
import static com.example.android.popcorn.Utilities.formatGenres;
import static com.example.android.popcorn.Utilities.roundToNearestTenth;
import static com.example.android.popcorn.networking.UrlCreator.createImageUrl;
import static com.example.android.popcorn.networking.UrlCreator.createUrlWithAppendedResponse;

/**
 * Created by alfredchang on 2017-09-27.
 */

public class DetailFragment extends Fragment implements OnCastMemberClickListener {

    private final String LOG_TAG = DetailFragment.class.getSimpleName();
    private final int BACKDROP_CROSSFADE_TIME = 200;
    private final int THUMBNAIL_CROSSFADE_TIME = 350;
    private final int POSTER_CROSSFADE_TIME = 700;

    private List<Cast> mListOfCastMembers;
    private List<Trailer> mListOfTrailers;

    private CastRecyclerViewAdapter mCastRecyclerAdapter;

    @BindView(R.id.backdrop_poster)
    ImageView mBackdrop;
    @BindView(R.id.movie_poster)
    ImageView mPoster;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.rating)
    TextView mRating;
    @BindView(R.id.runtime)
    TextView mRuntime;
    @BindView(R.id.release)
    TextView mRelease;
    @BindView(R.id.genres)
    TextView mGenres;
    @BindView(R.id.synopsis)
    TextView mSynopsis;
    //    @BindView(R.id.cast_button) Button mCastButton;
    @BindView(R.id.trailer_button)
    Button mTrailerButton;
    @BindView(R.id.cast_recycler_view)
    RecyclerView mCastRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_detail_main, container, false);
        ButterKnife.bind(this, rootView);

        setupRecyclerView();

        mListOfCastMembers = new ArrayList<>();
        mListOfTrailers = new ArrayList<>();
        Movie movie = getParcelableDetails();

        fetchJsonCast(movie);
        attachToAdapter();
        setParcelableDetailsIntoViews(movie);
        fetchJsonTrailers(movie);
        onClickButtons();

        return rootView;
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        mCastRecyclerView.setLayoutManager(layoutManager);
    }

    private void attachToAdapter() {
        mCastRecyclerAdapter = new CastRecyclerViewAdapter(getActivity(), mListOfCastMembers, this);
        mCastRecyclerView.setAdapter(mCastRecyclerAdapter);
    }

    @Override
    public void onClick(Cast castMember) {
        Intent singleCastMemberDetailsIntent = new Intent(getActivity(), IndividualCastDetailActivity.class);
        singleCastMemberDetailsIntent.putExtra(Utilities.PARCELABLE_CAST_MEMBER_KEY, castMember);
        startActivity(singleCastMemberDetailsIntent);
    }

    private void onClickButtons() {
        onClickTrailerButton();
    }

    private void onClickTrailerButton() {
        mTrailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trailerIntent = new Intent(getActivity(), TrailerActivity.class);
                trailerIntent.putExtra(Utilities.PARCELABLE_TRAILER_KEY,
                        (ArrayList<? extends Parcelable>) mListOfTrailers);
                startActivity(trailerIntent);
            }
        });
    }

    private void fetchJsonCast(final Movie movie) {
        String url = createUrlWithAppendedResponse(movie.getId(), UriTerms.CREDITS);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LoganCastTemplate castLogan = MovieParser.parseJsonCastData(response);
                        saveMovieCastId(castLogan);
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
                        saveMovieTrailers(trailerLogan);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Response error (fetchJsonCast): " + error);
            }
        });

        RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void saveMovieCastId(LoganCastTemplate castLogan) {
        for (LoganCastTemplate.Credits.Cast result : castLogan.getCredits().getCast()) {
            String profilePath = result.getProfilePath();
            Cast cast = new Cast();
            cast.setName(result.getName());
            cast.setCharacter(result.getCharacter());
            cast.setId(result.getId());

            if (profilePath != null) {
                cast.setThumbnail(createImageUrl(profilePath, UriTerms.IMAGE_SIZE_W185));
                cast.setProfilePath(createImageUrl(profilePath, UriTerms.IMAGE_SIZE_W185));
            }

            mListOfCastMembers.add(cast);
        }
    }

    private void saveMovieTrailers(LoganTrailersTemplate trailerLogan) {
        for (LoganTrailersTemplate.Videos.Results result : trailerLogan.getVideos().getResults()) {
            Trailer trailer = new Trailer();
            trailer.setKey(result.getKey());
            mListOfTrailers.add(trailer);
        }
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

    private void setBackdrop(Movie movie) {
        GlideApp.with(getActivity()).load(movie.getBackdropPath())
                .transition(DrawableTransitionOptions.withCrossFade(BACKDROP_CROSSFADE_TIME))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(mBackdrop);
    }

    private void setPoster(Movie movie) {
        GlideApp.with(getActivity()).load(movie.getDetailPosterPath())
                .transition(DrawableTransitionOptions.withCrossFade(POSTER_CROSSFADE_TIME))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(mPoster);
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
        mRelease.setText(formatDate(movie.getReleaseDate()));
    }

    private void setGenres(Movie movie) {
        mGenres.setText(formatGenres(movie.getGenres().toString()));
    }

    private void setSynopsis(Movie movie) {
        mSynopsis.setText(movie.getSynopsis());
    }

//    private void setCast() {
//        for (int i = 0; i < mListOfCastMembers.size(); i++) {
//            String thumbnail = mListOfCastMembers.get(i).getThumbnail();
//            if (thumbnail != null) {
//                GlideApp.with(getActivity()).load(thumbnail)
//                        .transition(DrawableTransitionOptions.withCrossFade(THUMBNAIL_CROSSFADE_TIME))
//                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                        .into(m)
//            }
//        }
//    }
}
