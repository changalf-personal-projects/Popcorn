package com.example.android.popcorn.ui;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A class to reduce the number of parameters passed in ViewPopulator.populateImageViewCustomSize(...) methods.
 */

public class DetailActivityLayoutProperties {

    private int mWidth;
    private int mHeight;
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
    private FloatingActionButton mFavouriteButton;

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

    public DetailActivityLayoutProperties(Context context, String imagePath, ImageView image,
                                          int width, int height, int crossfadeTime) {
        mContext = context;
        mImagePath = imagePath;
        mImage = image;
        mWidth = width;
        mHeight = height;
        mCrossfadeTime = crossfadeTime;
    }

    public DetailActivityLayoutProperties(Context context, String imagePath, int crossfadeTime,
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
        mFavouriteButton = favouriteButton;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getCrossFadeTime() {
        return mCrossfadeTime;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public Context getContext() {
        return mContext;
    }

    public ImageView getImage() {
        return mImage;
    }

    public ImageView getBackground() {
        return mBackground;
    }

    public ImageView getTmdbLogo() {
        return mTmdbLogo;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public TextView getRating() {
        return mRating;
    }

    public TextView getRuntime() {
        return mRuntime;
    }

    public TextView getRelease() {
        return mRelease;
    }

    public TextView getGenres() {
        return mGenres;
    }

    public TabLayout getTabLayout() {
        return mTabLayout;
    }

    public CollapsingToolbarLayout getCollapseToolbar() {
        return mCollapseToolbar;
    }

    public FloatingActionButton getfavouriteButton() {
        return mFavouriteButton;
    }
}