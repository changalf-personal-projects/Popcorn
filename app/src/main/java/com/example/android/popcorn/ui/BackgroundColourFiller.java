package com.example.android.popcorn.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.view.View;

/**
 * Created by alfredchang on 2017-11-05.
 */

public class BackgroundColourFiller {

    public static void onColourDetailActivityLayout(View view, CollapsingToolbarLayout mToolbarLayout, Palette.Swatch swatch) {
        view.setBackgroundColor(swatch.getRgb());
        mToolbarLayout.setContentScrimColor(swatch.getRgb());
        mToolbarLayout.setStatusBarScrimColor(swatch.getRgb());
    }
}
