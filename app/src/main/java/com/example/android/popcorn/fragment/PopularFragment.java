package com.example.android.popcorn.fragment;

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
import com.example.android.popcorn.R;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.networking.RequestQueueSingleton;
import com.example.android.popcorn.fragment.parsing.MovieLogan;
import com.example.android.popcorn.fragment.parsing.MovieParser;
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
    private final int NUM_COLUMNS_LAYOUT = 2;

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
        mRecyclerAdapter = new PosterRecyclerViewAdapter(mListOfMovies);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), NUM_COLUMNS_LAYOUT);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return rootView;
    }

    private void fetchJsonData() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key="
                + BuildConfig.MOVIE_DP_API_KEY + "&language=en-US&page=1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v(LOG_TAG, "Response: " + response);
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

    }
}
