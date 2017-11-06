package com.example.android.popcorn.ui;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android.popcorn.R;
import com.github.florent37.glidepalette.GlidePalette;

import java.util.List;

import static com.example.android.popcorn.NullChecker.isNotEmptyString;
import static com.example.android.popcorn.NullChecker.isNotNullString;
import static com.example.android.popcorn.Utilities.convertDoubleToString;
import static com.example.android.popcorn.Utilities.formatDate;
import static com.example.android.popcorn.Utilities.formatGenres;
import static com.example.android.popcorn.Utilities.roundToNearestTenth;
import static com.example.android.popcorn.ui.BackgroundColourFiller.onColourDetailActivityLayout;

/**
 * Class that holds all methods that populate views.
 */
public class ViewPopulator {

    private final static String LOG_TAG = ViewPopulator.class.getSimpleName();

    // Overloaded method 1: Populate any normal image view.
    public static void populateImageView(Context context, String imagePath, int crossfadeTime, ImageView view) {
        if (imagePath != null) {
            GlideApp.with(context).load(imagePath)
                    .transition(DrawableTransitionOptions.withCrossFade(crossfadeTime))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(view);
        }
    }

    // Overloaded method 2: Populate a circle-cropped image.
    public static void populateImageView(Context context, String imagePath, ImageView view,
                                         int width, int height, int crossfadeTime) {
        if (imagePath != null) {
            GlideApp.with(context).load(imagePath).circleCrop()
                    .override(width, height)
                    .transition(DrawableTransitionOptions.withCrossFade(crossfadeTime))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(view);
        }
    }

    // Overloaded method 3: Populate an imageview and colour the background.
    public static void populateImageView(Context context, String imagePath, int crossfadeTime,
                                         final ImageView view, final ImageView background,
                                         final CollapsingToolbarLayout toolbarLayout) {
        GlideApp.with(context).load(imagePath)
                .listener(GlidePalette.with(imagePath)
                        .intoCallBack(new GlidePalette.CallBack() {
                            @Override
                            public void onPaletteLoaded(Palette palette) {
                                onColourDetailActivityLayout(background, toolbarLayout, palette.getDominantSwatch());
                            }
                        })
                )
                .transition(DrawableTransitionOptions.withCrossFade(crossfadeTime))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(view);
    }

    public static void populateCenterCropImageView(Context context, String imagePath, int crossfadeTime,
                                                   ImageView view) {
        GlideApp.with(context).load(imagePath)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(crossfadeTime))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(view);
    }

    public static void populateTextView(String content, TextView view) {
        view.setText(content);
    }

    public static void populateRatingTextView(Context context, String content, TextView view) {
        double rating = roundToNearestTenth(content);
        String ratingAsString = convertDoubleToString(rating);
        view.setText(context.getResources().getString(R.string.rating_out_of_ten, ratingAsString));
    }

    public static void populateRuntimeTextView(Context context, String content, TextView view) {
        view.setText(context.getResources().getString(R.string.runtime_plus_minutes, content));
    }

    public static void populateGenresToTextView(List<String> content, TextView view) {
        view.setText(formatGenres(content.toString()));
    }

    public static void populateDateToTextView(String content, TextView view) {
        view.setText(formatDate(content));
    }

    public static void populateBirthPlaceToTextView(String content, String message, TextView view) {
        if (isNotNullString(content)) {
            view.setText(content);
        } else {
            view.setText(message);
        }
    }

    public static void populateBiographyToTextView(String content, String message, TextView view) {
        if (isNotNullString(content) && isNotEmptyString(content)) {
            view.setText(content);
        } else {
            view.setText(message);
        }
    }
}
