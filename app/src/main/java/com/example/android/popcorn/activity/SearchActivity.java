package com.example.android.popcorn.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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


    }

}
