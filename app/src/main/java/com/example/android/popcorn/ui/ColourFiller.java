package com.example.android.popcorn.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alfredchang on 2017-11-05.
 */

public class ColourFiller {

    private boolean hasSwatch(Palette.Swatch swatch) {
        return swatch != null;
    }

    public static void colourWithDominantSwatch(View view, CollapsingToolbarLayout mToolbarLayout, List<Palette.Swatch> swatches) {
        for (Palette.Swatch swatch: swatches) {
            if (swatch != null) {
                view.setBackgroundColor(swatch.getRgb());
                mToolbarLayout.setContentScrimColor(swatch.getRgb());
                mToolbarLayout.setStatusBarScrimColor(swatch.getRgb());
            }
        }
    }

    public static void colourText(TextView title, TextView rating, TextView runtime,
                                                  TextView release, TextView genres, Palette.Swatch swatch) {
        title.setTextColor(swatch.getRgb());
    }
}
