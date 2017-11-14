package com.example.android.popcorn.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alfredchang on 2017-11-05.
 */

public class ColourFiller {

    public static boolean hasSwatch(Palette.Swatch swatch) {
        return swatch != null;
    }

    public static void colourWithSwatch(View view, CollapsingToolbarLayout mToolbarLayout, List<Palette.Swatch> swatches) {
        for (Palette.Swatch swatch: swatches) {
            if (hasSwatch(swatch)) {
                int colourFromRgb = swatch.getRgb();
                view.setBackgroundColor(colourFromRgb);
                mToolbarLayout.setContentScrimColor(colourFromRgb);
                mToolbarLayout.setStatusBarScrimColor(colourFromRgb);
            }
        }
    }

    public static void colourTextViewWithSwatch(TextView title, TextView rating, TextView runtime,
                                                  TextView release, TextView genres, List<Palette.Swatch> swatches) {
        for (Palette.Swatch swatch: swatches) {
            if (hasSwatch(swatch)) {
                int titleTextColour = swatch.getTitleTextColor();
                int bodyTextColour = swatch.getBodyTextColor();
                title.setTextColor(titleTextColour);
                rating.setTextColor(bodyTextColour);
                runtime.setTextColor(bodyTextColour);
                release.setTextColor(bodyTextColour);
                genres.setTextColor(bodyTextColour);
            }
        }
    }

    public static int getFirstAvailableSwatch(Palette palette) {
        int swatchRgb;

        if (hasSwatch(palette.getDarkVibrantSwatch())) {
            swatchRgb = palette.getDarkVibrantSwatch().getRgb();
        } else if (hasSwatch(palette.getVibrantSwatch())) {
            swatchRgb = palette.getVibrantSwatch().getRgb();
        } else if (hasSwatch(palette.getLightVibrantSwatch())) {
            swatchRgb = palette.getLightVibrantSwatch().getRgb();
        } else if (hasSwatch(palette.getDarkMutedSwatch())) {
            swatchRgb = palette.getDarkMutedSwatch().getRgb();
        } else if (hasSwatch(palette.getMutedSwatch())) {
            swatchRgb = palette.getMutedSwatch().getRgb();
        } else if (hasSwatch(palette.getLightMutedSwatch())) {
            swatchRgb = palette.getLightMutedSwatch().getRgb();
        } else {
            swatchRgb = palette.getDominantSwatch().getRgb();
        }

        return swatchRgb;
    }

    public static void colourTmdbLogo(ImageView tmdbLogo, int swatchRgb) {
        tmdbLogo.setColorFilter(swatchRgb);
    }

    public static void colourTabLayout(TabLayout tabLayout, int swatchRgb) {
        tabLayout.setBackgroundColor(swatchRgb);
    }
}
