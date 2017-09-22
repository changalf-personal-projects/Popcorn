package com.example.android.popcorn.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.android.popcorn.network.RequestQueueSingleton;

/**
 * Created by alfredchang on 2017-09-16.
 */

public class PopularFragment extends Fragment {

    private final String LOG_TAG = PopularFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    private void fetchJsonData() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key="
                + BuildConfig.MOVIE_DP_API_KEY + "&language=en-US&page=1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v(LOG_TAG, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Respone error: " + error);
            }
        });

        RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    // Use Volley instead?
    private class FetchMoviesTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
}
