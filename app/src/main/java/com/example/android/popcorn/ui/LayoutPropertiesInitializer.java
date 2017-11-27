package com.example.android.popcorn.ui;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alfredchang on 2017-11-26.
 */

public class LayoutPropertiesInitializer {

    public static DetailActivityLayoutProperties initImageViewProperties(Context context, String imagePath,
                                                                         ImageView image) {
        return new DetailActivityLayoutProperties(context, imagePath, image);
    }

    public static DetailActivityLayoutProperties initImageViewProperties(Context context, String imagePath,
                                                                         int crossfadeTime, ImageView image) {
        return new DetailActivityLayoutProperties(context, imagePath, crossfadeTime, image);
    }

    public static DetailActivityLayoutProperties initImageViewProperties(Context context, String imagePath, int crossfadeTime,
                                                                         ImageView image, ImageView background, ImageView tmdbLogo,
                                                                         TextView title, TextView rating, TextView runtime,
                                                                         TextView release, TextView genres, TabLayout tabLayout,
                                                                         CollapsingToolbarLayout collapseToolbar,
                                                                         FloatingActionButton favouriteButton) {
        return new DetailActivityLayoutProperties(context, imagePath, crossfadeTime, image, background, tmdbLogo, title, rating,
                runtime, release, genres, tabLayout, collapseToolbar, favouriteButton);
    }
}
