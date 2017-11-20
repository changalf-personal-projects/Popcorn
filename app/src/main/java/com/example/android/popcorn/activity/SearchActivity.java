package com.example.android.popcorn.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.fragment.parsing.LoganIdTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.networking.RequestQueueSingleton;

import static com.example.android.popcorn.fragment.parsing.DataSaver.saveMovieId;
import static com.example.android.popcorn.networking.UrlCreator.createSearchMovieUrl;

/**
 * Created by alfredchang on 2017-10-02.
 */

public class SearchActivity extends AppCompatActivity {

    private final String LOG_TAG = SearchActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());

        Log.v(LOG_TAG, "In search activity!");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.v(LOG_TAG, "Search query string: " + query);

            searchMovie(query);
        }
    }

    private void searchMovie(String query) {
        String url = createSearchMovieUrl(query);
        Log.v(LOG_TAG, "Url string: " + url);

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
                Log.e(LOG_TAG, "Response error (searchMovie): " + error);
            }
        });

        RequestQueueSingleton.getSingletonInstance(this).addToRequestQueue(stringRequest);
    }

}
