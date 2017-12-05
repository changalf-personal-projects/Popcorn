package com.example.android.popcorn.ui;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.popcorn.R;
import com.github.florent37.glidepalette.GlidePalette;

import java.util.List;
import java.util.Set;

import static com.example.android.popcorn.NullChecker.isNotEmptyString;
import static com.example.android.popcorn.NullChecker.isNotNullString;
import static com.example.android.popcorn.Utilities.NOT_AVAILABLE;
import static com.example.android.popcorn.Utilities.convertDoubleToString;
import static com.example.android.popcorn.Utilities.formatDate;
import static com.example.android.popcorn.Utilities.formatString;
import static com.example.android.popcorn.Utilities.formatWithCommas;
import static com.example.android.popcorn.Utilities.roundToNearestTenth;
import static com.example.android.popcorn.ui.ColourFiller.colourFavouriteButton;
import static com.example.android.popcorn.ui.ColourFiller.colourTabLayout;
import static com.example.android.popcorn.ui.ColourFiller.colourTextViewWithSwatch;
import static com.example.android.popcorn.ui.ColourFiller.colourTmdbLogo;
import static com.example.android.popcorn.ui.ColourFiller.colourWithSwatch;
import static com.example.android.popcorn.ui.ColourFiller.getFirstAvailableSwatch;

/**
 * Class that holds all methods that populate views.
 */
public class ViewPopulator {

    private final static String LOG_TAG = ViewPopulator.class.getSimpleName();

    public static void populateImageView(DetailActivityLayoutProperties layoutProperties) {
        Context context = layoutProperties.getContext();
        String imagePath = layoutProperties.getImagePath();
        int crossFadeTime = layoutProperties.getCrossFadeTime();
        ImageView view = layoutProperties.getImage();

        GlideApp.with(context).load(imagePath)
                .placeholder(R.drawable.poster_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade(crossFadeTime))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(view);
    }

    public static void populateImageViewCustomSize(DetailActivityLayoutProperties layoutProperties) {
        Context context = layoutProperties.getContext();
        String imagePath = layoutProperties.getImagePath();
        int width = layoutProperties.getWidth();
        int height = layoutProperties.getHeight();
        int crossFadeTime = layoutProperties.getCrossFadeTime();
        ImageView view = layoutProperties.getImage();

            GlideApp.with(context).load(imagePath).circleCrop()
                    .override(width, height)
                    .placeholder(R.drawable.circle_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade(crossFadeTime))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(view);
    }

    public static void populateImageViewWithCrossFade(DetailActivityLayoutProperties layoutProperties) {
        Context context = layoutProperties.getContext();
        String imagePath = layoutProperties.getImagePath();
        ImageView view = layoutProperties.getImage();

        int crossfadeTime = layoutProperties.getCrossFadeTime();

        final TextView title = layoutProperties.getTitle();
        final TextView rating = layoutProperties.getRating();
        final TextView runtime = layoutProperties.getRuntime();
        final TextView release = layoutProperties.getRelease();
        final TextView genres = layoutProperties.getGenres();
        final ImageView logo = layoutProperties.getTmdbLogo();
        final ImageView background = layoutProperties.getBackground();
        final TabLayout tabLayout = layoutProperties.getTabLayout();
        final CollapsingToolbarLayout toolbarLayout = layoutProperties.getCollapseToolbar();
        final FloatingActionButton favouriteButton = layoutProperties.getfavouriteButton();

            GlideApp.with(context).load(imagePath)
                    .placeholder(R.drawable.poster_placeholder)
                    .listener(GlidePalette.with(layoutProperties.getImagePath())
                            .intoCallBack(new GlidePalette.CallBack() {
                                @Override
                                public void onPaletteLoaded(Palette palette) {
                                    List<Palette.Swatch> swatches = palette.getSwatches();
                                    colourWithSwatch(background, toolbarLayout, swatches);
                                    colourTextViewWithSwatch(title, rating, runtime, release, genres, swatches);
                                    int swatchRgb = getFirstAvailableSwatch(palette);
                                    colourTmdbLogo(logo, swatchRgb);
                                    colourTabLayout(tabLayout, swatchRgb);
                                    colourFavouriteButton(favouriteButton, swatchRgb);
                                }
                            })
                    )
                    .transition(DrawableTransitionOptions.withCrossFade(crossfadeTime))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(view);
    }

    public static void populateImageViewNoCrossfade(DetailActivityLayoutProperties layoutProperties) {
        Context context = layoutProperties.getContext();
        String imagePath = layoutProperties.getImagePath();
        ImageView view = layoutProperties.getImage();

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.color.grey);

        GlideApp.with(context).load(imagePath)
                .placeholder(R.drawable.circle_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(view);
    }

    // Currently used for backdrops.
    public static void populateCenterCropImageView(DetailActivityLayoutProperties layoutProperties) {
        Context context = layoutProperties.getContext();
        String imagePath = layoutProperties.getImagePath();
        ImageView view = layoutProperties.getImage();
        int crossfadeTime = layoutProperties.getCrossFadeTime();

        GlideApp.with(context).load(imagePath)
                .centerCrop()
                .placeholder(R.drawable.backdrop_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade(crossfadeTime))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(view);
    }

    public static void populateTextView(String content, TextView view) {
        if (hasContent(content)) {
            view.setText(content);
        } else {
            view.setText(NOT_AVAILABLE);
        }
    }

    public static void populateTextViewWithSpaces(String content, TextView view) {
        view.setText(formatWithCommas(content));
    }

    public static void populateRatingTextView(Context context, String content, TextView view) {
        double rating = roundToNearestTenth(content);
        String ratingAsString = convertDoubleToString(rating);
        view.setText(context.getResources().getString(R.string.rating_out_of_ten, ratingAsString));
    }

    public static void populateRuntimeTextView(Context context, String content, TextView view) {
        view.setText(context.getResources().getString(R.string.runtime, content));
    }

    public static void populateStringListToTextView(List<String> content, TextView view) {
        view.setText(formatString(content.toString()));
    }

    public static void populateStringSetToTextView(Set<String> content, TextView view) {
        view.setText(formatString(content.toString()));
    }

    public static void populateDateToTextView(String content, TextView view, String format) {
        view.setText(formatDate(content, format));
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

    private static void setPlaceholder(ImageView view) {
        view.setColorFilter(R.color.grey);
    }

    private static boolean hasContent(String content) {
        return content != null;
    }
}
