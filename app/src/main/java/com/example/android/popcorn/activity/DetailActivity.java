package com.example.android.popcorn.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.fragment.CastFragment;
import com.example.android.popcorn.fragment.DetailFragment;
import com.example.android.popcorn.fragment.ReviewFragment;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.ui.DetailTabsPagerAdapter;
import com.example.android.popcorn.ui.TabTitles;
import com.example.android.popcorn.ui.ViewPopulator;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.ui.ToolbarTitleDisplay.displayTitleUponCollapse;

/**
 * Created by alfredchang on 2017-09-27.
 */

// Extend AppCompatActivity for back button_layout.
public class DetailActivity extends AppCompatActivity {

    private final int BACKDROP_CROSSFADE_TIME = 300;
    private final int POSTER_CROSSFADE_TIME = 500;
    private final String SAVED_MOVIE = "Saved to favourites!";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.backdrop_poster)
    ImageView mBackdrop;
    @BindView(R.id.movie_poster)
    ImageView mPoster;
    @BindView(R.id.poster_background)
    ImageView mPosterBackground;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.detail_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tmdb_branding)
    ImageView tmdbBranding;
    @BindView(R.id.favourite_fab)
    FloatingActionButton favouriteButton;

    // Movie info.
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.rating)
    TextView mRating;
    @BindView(R.id.runtime)
    TextView mRuntime;
    @BindView(R.id.release)
    TextView mRelease;
    @BindView(R.id.genres)
    TextView mGenres;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        Movie movie = getParcelableMovieDetails();
        setParcelableDetailsIntoViews(movie);

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        displayTitleUponCollapse(mAppBarLayout, mCollapsingToolbarLayout);
        initFab();
    }

    private void initFab() {
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favouriteButton.setImageResource(R.mipmap.ic_favourited);
                Toast.makeText(getBaseContext(), SAVED_MOVIE, Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public Intent getParentActivityIntent() {
        return null;
    }

    @Override
    public Intent getSupportParentActivityIntent() {
        return null;
    }

    private Intent getLastParentActivityIntent() {
        return null;
    }

    private Movie getParcelableMovieDetails() {
        Intent intent = getIntent();
        return intent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);
    }

    private void setParcelableDetailsIntoViews(Movie movie) {
        ViewPopulator.populateCenterCropImageView(this, movie.getBackdropPath(), BACKDROP_CROSSFADE_TIME,
                mBackdrop);
        ViewPopulator.populateImageView(this, movie.getPosterPath(), POSTER_CROSSFADE_TIME, mPoster, mPosterBackground,
                mCollapsingToolbarLayout, mTitle, mRating, mRuntime, mRelease, mGenres, mTabLayout, tmdbBranding, favouriteButton);
        ViewPopulator.populateTextView(movie.getTitle(), mTitle);
        ViewPopulator.populateRatingTextView(this, movie.getRating(), mRating);
        ViewPopulator.populateRuntimeTextView(this, movie.getRuntime(), mRuntime);
        ViewPopulator.populateDateToTextView(movie.getReleaseDate(), mRelease);
        ViewPopulator.populateGenresToTextView(movie.getGenres(), mGenres);
    }

    private void setupViewPager(ViewPager viewPager) {
        DetailTabsPagerAdapter pagerAdapter = new DetailTabsPagerAdapter(getSupportFragmentManager());
        addFragments(pagerAdapter);
        addFragmentTitles(pagerAdapter);
        viewPager.setAdapter(pagerAdapter);
    }

    private void addFragments(DetailTabsPagerAdapter pagerAdapter) {
        pagerAdapter.addFragment(new DetailFragment());
        pagerAdapter.addFragment(new CastFragment());
        pagerAdapter.addFragment(new ReviewFragment());
    }

    private void addFragmentTitles(DetailTabsPagerAdapter pagerAdapter) {
        pagerAdapter.addFragmentTitle(TabTitles.DETAILS);
        pagerAdapter.addFragmentTitle(TabTitles.CAST);
        pagerAdapter.addFragmentTitle(TabTitles.REVIEWS);
    }
}
