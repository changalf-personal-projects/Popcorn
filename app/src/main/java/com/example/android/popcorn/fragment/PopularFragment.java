package com.example.android.popcorn.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.BuildConfig;
import com.example.android.popcorn.MovieTerms;
import com.example.android.popcorn.R;
import com.example.android.popcorn.fragment.parsing.MovieLogan;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.networking.RequestQueueSingleton;
import com.example.android.popcorn.ui.PosterRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-09-16.
 */

public class PopularFragment extends Fragment {

    private final String LOG_TAG = PopularFragment.class.getSimpleName();
    private final int LAYOUT_COL_SPAN = 2;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private MovieParser mMovieParser = new MovieParser();
    private PosterRecyclerViewAdapter mRecyclerAdapter;
    private List<Movie> mListOfMovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        fetchJsonData();

        mListOfMovies = new ArrayList<>();
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), LAYOUT_COL_SPAN);
        mRecyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    private void fetchJsonData() {
        String url = createUrl();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MovieLogan movieJackson = mMovieParser.parseJsonData(response);
                        initMovie(movieJackson);
                        attachAdapter();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Respone error: " + error);
            }
        });

        RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void initMovie(MovieLogan movieLogan) {
        for (MovieLogan.Results result: movieLogan.getResults()) {
            Movie movie = new Movie();
            movie.setPosterPath(MovieTerms.POSTER_BASE_URL.concat(MovieTerms.POSTER_SIZE).concat(result.getPosterPath()));
            mListOfMovies.add(movie);
        }
    }

    private void attachAdapter() {
        mRecyclerAdapter = new PosterRecyclerViewAdapter(mListOfMovies);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    private String createUrl() {
        return Uri.parse(MovieTerms.MOVIE_BASE_URL).buildUpon().appendPath(MovieTerms.POPULAR)
                .appendQueryParameter(MovieTerms.TMDB_API_KEY, BuildConfig.MOVIE_DP_API_KEY)
                .appendQueryParameter(MovieTerms.TMDB_LANGUAGE, MovieTerms.LANGUAGE)
                .appendQueryParameter(MovieTerms.TMDB_PAGE, MovieTerms.PAGE)
                .build().toString();
    }
}
