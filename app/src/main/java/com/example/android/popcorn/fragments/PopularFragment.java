package com.example.android.popcorn.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popcorn.R;

/**
 * Created by alfredchang on 2017-09-16.
 */

public class PopularFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    private void fetchJsonData() {

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
