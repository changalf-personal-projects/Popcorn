package com.example.android.popcorn.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.fragment.SearchResultsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-11-20.
 */

public class SearchResultsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        Fragment searchResultsFragment = saveQueryToBundle();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.search_results_container,
                    searchResultsFragment).commit();
        }
    }

    private Fragment saveQueryToBundle() {
        String query = getQueryFromIntent();
        Fragment searchResultsFragment = new SearchResultsFragment();
        Bundle bundle = new Bundle();

        // Coupled the search key because it will always have to be identical to work.
        bundle.putString(Utilities.SEARCH_KEY, query);
        searchResultsFragment.setArguments(bundle);

        return searchResultsFragment;
    }

    private String getQueryFromIntent() {
        return getIntent().getStringExtra(Utilities.SEARCH_KEY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Source: https://developer.android.com/training/search/setup.html.
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_icon).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Unused here.
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startSearch(String query) {
        Intent searchIntent = new Intent(this, SearchResultsActivity.class);
        searchIntent.putExtra(Utilities.SEARCH_KEY, query);
        startActivity(searchIntent);
    }

}
