package com.example.android.popcorn.ui;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A class to reduce the number of parameters passed in ViewPopulator.populateImageView(...) methods.
 */

public class DetailActivityLayoutProperties {

    private int mCrossfadeTime;
    private String mImagePath;
    private Context mContext;

    private ImageView mImage;
    private ImageView mBackground;
    private ImageView mTmdbLogo;

    private TextView mTitle;
    private TextView mRating;
    private TextView mRuntime;
    private TextView mRelease;
    private TextView mGenres;

    private TabLayout mTabLayout;
    private CollapsingToolbarLayout mCollapseToolbar;
    private FloatingActionButton mfavouriteButton;

    public DetailActivityLayoutProperties(Context context, String imagePath, ImageView image) {
        mContext = context;
        mImagePath = imagePath;
        mImage = image;
    }

    public DetailActivityLayoutProperties(Context context, String imagePath, int crossfadeTime,
                                          ImageView image) {
        mContext = context;
        mImagePath = imagePath;
        mCrossfadeTime = crossfadeTime;
        mImage = image;
    }

    public DetailActivityLayoutProperties (int crossfadeTime, String imagePath, Context context,
                                           ImageView image, ImageView background, ImageView tmdbLogo,
                                           TextView title, TextView rating, TextView runtime,
                                           TextView release, TextView genres, TabLayout tabLayout,
                                           CollapsingToolbarLayout collapseToolbar,
                                           FloatingActionButton favouriteButton) {
        mCrossfadeTime = crossfadeTime;
        mImagePath = imagePath;
        mContext = context;
        mImage = image;
        mBackground = background;
        mTmdbLogo = tmdbLogo;
        mTitle = title;
        mRating = rating;
        mRuntime = runtime;
        mRelease = release;
        mGenres = genres;
        mTabLayout = tabLayout;
        mCollapseToolbar = collapseToolbar;
        mfavouriteButton = favouriteButton;

    }

    public void setmCrossfadeTime(int mCrossfadeTime) {
        this.mCrossfadeTime = mCrossfadeTime;
    }

    public void setmImagePath(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setmImage(ImageView mImage) {
        this.mImage = mImage;
    }

    public void setmBackground(ImageView mBackground) {
        this.mBackground = mBackground;
    }

    public void setmTmdbLogo(ImageView mTmdbLogo) {
        this.mTmdbLogo = mTmdbLogo;
    }

    public void setmTitle(TextView mTitle) {
        this.mTitle = mTitle;
    }

    public void setmRating(TextView mRating) {
        this.mRating = mRating;
    }

    public void setmRuntime(TextView mRuntime) {
        this.mRuntime = mRuntime;
    }

    public void setmRelease(TextView mRelease) {
        this.mRelease = mRelease;
    }

    public void setmGenres(TextView mGenres) {
        this.mGenres = mGenres;
    }

    public void setmTabLayout(TabLayout mTabLayout) {
        this.mTabLayout = mTabLayout;
    }

    public void setmCollapseToolbar(CollapsingToolbarLayout mCollapseToolbar) {
        this.mCollapseToolbar = mCollapseToolbar;
    }

    public void setMfavouriteButton(FloatingActionButton mfavouriteButton) {
        this.mfavouriteButton = mfavouriteButton;
    }
}
