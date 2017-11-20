package com.example.android.popcorn.networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.fragment.parsing.LoganDetailsTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.model.Movie;

import java.util.List;

import static com.example.android.popcorn.fragment.parsing.DataSaver.saveMovieDetails;
import static com.example.android.popcorn.model.MoviesSingleton.getSingletonMovies;
import static com.example.android.popcorn.networking.UrlCreator.createUrl;

/**
 * Created by alfredchang on 2017-11-19.
 */

public class JsonFetcher {

    private final static String LOG_TAG = JsonFetcher.class.getSimpleName();

    public static void fetchJsonDetails(Context context) {
        List<Movie> movies = getSingletonMovies();

        for (int i = 0; i < movies.size(); i++) {
            String url = createUrl(movies.get(i).getId());
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

            RequestQueueSingleton.getSingletonInstance(context).addToRequestQueue(stringRequest);
        }
    }
}
