package com.example.android.popcorn.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.TextView;

/**
 * Created by alfredchang on 2017-11-05.
 */

public class ColourFiller {

    private boolean hasSwatch(Palette.Swatch swatch) {
        return swatch != null;
    }

    public static void colourWithDominantSwatch(View view, CollapsingToolbarLayout mToolbarLayout, Palette.Swatch swatch) {
        view.setBackgroundColor(swatch.getRgb());
        mToolbarLayout.setContentScrimColor(swatch.getRgb());
        mToolbarLayout.setStatusBarScrimColor(swatch.getRgb());
    }

    public static void colourText(TextView title, TextView rating, TextView runtime,
                                                  TextView release, TextView genres, Palette.Swatch swatch) {
        title.setTextColor(swatch.getRgb());
    }
}
