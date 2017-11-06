package com.example.android.popcorn.ui;

import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by alfredchang on 2017-11-05.
 */

public class BackgroundColourFiller {

    public static void onFillBackground(View view, Palette.Swatch swatch) {
        view.setBackgroundColor(swatch.getRgb());
    }

    public static void onFillBackgroundWithHolder(RecyclerView.ViewHolder holder, Palette.Swatch swatch) {
        // TODO: Implement this method.
    }
}
