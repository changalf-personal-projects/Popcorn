package com.example.android.popcorn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popcorn.MainActivity;
import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.fragment.CastFragment;
import com.example.android.popcorn.fragment.DetailFragment;
import com.example.android.popcorn.fragment.ReviewFragment;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.ui.DetailTabsPagerAdapter;
import com.example.android.popcorn.ui.TabTitles;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.android.popcorn.Utilities.PARENT_ACTIVITY;
import static com.example.android.popcorn.ui.LayoutPropertiesInitializer.initImageViewProperties;
import static com.example.android.popcorn.ui.ViewPopulator.populateCenterCropImageView;
import static com.example.android.popcorn.ui.ViewPopulator.populateDateToTextView;
import static com.example.android.popcorn.ui.ViewPopulator.populateImageViewWithCrossFade;
import static com.example.android.popcorn.ui.ViewPopulator.populateImageViewNoCrossfade;
import static com.example.android.popcorn.ui.ViewPopulator.populateRatingTextView;
import static com.example.android.popcorn.ui.ViewPopulator.populateRuntimeTextView;
import static com.example.android.popcorn.ui.ViewPopulator.populateStringListToTextView;
import static com.example.android.popcorn.ui.ViewPopulator.populateTextView;

/**
 * Created by alfredchang on 2017-09-27.
 */

// Extend AppCompatActivity for back button layout.
public class DetailActivity extends AppCompatActivity {

    private final int BACKDROP_CROSSFADE_TIME = 300;
    private final int POSTER_CROSSFADE_TIME = 500;
    private final int PAGES_TO_RETAIN = 2;
    private final String SAVED_MOVIE = "Saved to favourites!";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.backdrop_poster) ImageView mBackdrop;
    @BindView(R.id.movie_poster) ImageView mPoster;
    @BindView(R.id.poster_background) ImageView mPosterBackground;
    @BindView(R.id.app_bar) AppBarLayout mAppBarLayout;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.detail_tabs) TabLayout mTabLayout;
    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.tmdb_branding) ImageView tmdbBranding;
    @BindView(R.id.favourite_fab) FloatingActionButton favouriteButton;
    @BindView(R.id.avatar_poster) CircleImageView moviePosterAvatar;

    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.rating) TextView mRating;
    @BindView(R.id.runtime) TextView mRuntime;
    @BindView(R.id.release) TextView mRelease;
    @BindView(R.id.genres) TextView mGenres;

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
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
        return getLastParentActivityIntent();
    }

    @Override
    public Intent getSupportParentActivityIntent() {
        return getLastParentActivityIntent();
    }

    private Intent getLastParentActivityIntent() {
        Intent lastParentIntent;
        String parentName = getParentName();

        if (parentName == Utilities.MAIN_ACTIVITY_PARENT) {
            lastParentIntent = new Intent(this, MainActivity.class);
        } else {
            lastParentIntent = new Intent(this, SearchResultsActivity.class);
        }
        lastParentIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        return lastParentIntent;
    }

    private String getParentName() {
        return getIntent().getStringExtra(PARENT_ACTIVITY);
    }

    private Movie getParcelableMovieDetails() {
        Intent intent = getIntent();
        return intent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);
    }

    private void setParcelableDetailsIntoViews(Movie movie) {
        populateCenterCropImageView(initImageViewProperties(this,
                movie.getBackdropPath(), BACKDROP_CROSSFADE_TIME, mBackdrop));

        populateImageViewWithCrossFade(initImageViewProperties(this, movie.getPosterPath(),
                POSTER_CROSSFADE_TIME, mPoster, mPosterBackground, tmdbBranding, mTitle, mRating,
                mRuntime, mRelease, mGenres, mTabLayout, mCollapsingToolbarLayout, favouriteButton));

        populateImageViewNoCrossfade(initImageViewProperties(this, movie.getPosterPath(),
                moviePosterAvatar));

        populateTextView(movie.getTitle(), mTitle);
        populateRatingTextView(this, movie.getRating(), mRating);
        populateRuntimeTextView(this, movie.getRuntime(), mRuntime);
        populateDateToTextView(movie.getReleaseDate(), mRelease, "yyyy");
        populateStringListToTextView(movie.getGenres(), mGenres);
    }

    private void setupViewPager(ViewPager viewPager) {
        DetailTabsPagerAdapter pagerAdapter = new DetailTabsPagerAdapter(getSupportFragmentManager());
        addFragments(pagerAdapter);
        addFragmentTitles(pagerAdapter);
        viewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(PAGES_TO_RETAIN);
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
