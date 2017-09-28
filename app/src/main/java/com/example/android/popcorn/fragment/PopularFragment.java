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
import com.example.android.popcorn.R;
import com.example.android.popcorn.dagger.component.FragmentComponent;
import com.example.android.popcorn.fragment.parsing.LoganDetailsTemplate;
import com.example.android.popcorn.fragment.parsing.LoganIdTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.networking.RequestQueueSingleton;
import com.example.android.popcorn.ui.PosterRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.networking.UrlCreator.createDetailUrl;
import static com.example.android.popcorn.networking.UrlCreator.createUrl;

/**
 * Created by alfredchang on 2017-09-16.
 */

public class PopularFragment extends Fragment {

    private final String LOG_TAG = PopularFragment.class.getSimpleName();
    private final int LAYOUT_COL_SPAN = 2;

    private FragmentComponent mFragmentComponent;
    private PosterRecyclerViewAdapter mRecyclerAdapter;
    private List<Movie> mListOfMovies;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        mListOfMovies = new ArrayList<>();
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), LAYOUT_COL_SPAN);
        mRecyclerView.setLayoutManager(layoutManager);

        fetchJsonId();
        fetchJsonDetails();

        return rootView;
    }

    // Can't be moved into another class because onResponse() doesn't return anything.
    private void fetchJsonId() {
        String url = createUrl();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LoganIdTemplate movieLogan = MovieParser.parseJsonData(response);
                        saveMovieId(movieLogan);
                        attachAdapter();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Respone error (fetchJsonId): " + error);
            }
        });

        RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void fetchJsonDetails() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            String url = createDetailUrl(mListOfMovies.get(i).getId());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            LoganDetailsTemplate movieLogan = MovieParser.parseJsonDetailsData(response);
                            Log.v(LOG_TAG, "Response: " + movieLogan);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(LOG_TAG, "Respone error (fetchJsonDetails): " + error);
                }
            });

            RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
        }
    }

    private void saveMovieId(LoganIdTemplate movieLogan) {
        for (LoganIdTemplate.Results result: movieLogan.getResults()) {
            Movie movie = new Movie();
//            movie.setPosterPath(UriUtils.POSTER_BASE_URL.concat(UriUtils.POSTER_SIZE).concat(result.getPosterPath()));
            movie.setId(result.getId());
            mListOfMovies.add(movie);
        }
    }

    private void attachAdapter() {
        mRecyclerAdapter = new PosterRecyclerViewAdapter(mListOfMovies);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
}
