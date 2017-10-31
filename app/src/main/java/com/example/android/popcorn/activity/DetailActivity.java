package com.example.android.popcorn.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.fragment.DetailFragment;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.ui.ViewPopulator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-09-27.
 */

// Extend AppCompatActivity for back button.
public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.backdrop_poster)
    ImageView mBackdrop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        Movie movie = getParcelableMovieDetails();
        populateBackdrop(movie);

        // Adjusting toolbar.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.transparent_layout));
//        }
//        Drawable backArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
//        backArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    new DetailFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_icon).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    private Movie getParcelableMovieDetails() {
        Intent intent = getIntent();
        return intent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);
    }

    private void populateBackdrop(Movie movie) {
        ViewPopulator.populateImageView(this, movie.getBackdropPath(), 300, mBackdrop);
    }
}
