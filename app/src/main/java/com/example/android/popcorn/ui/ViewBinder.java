package com.example.android.popcorn.ui;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android.popcorn.R;

import java.util.List;

import static com.example.android.popcorn.EmptyChecker.isNotEmptyString;
import static com.example.android.popcorn.NullChecker.isNotNullString;
import static com.example.android.popcorn.Utilities.convertDoubleToString;
import static com.example.android.popcorn.Utilities.formatDate;
import static com.example.android.popcorn.Utilities.formatGenres;
import static com.example.android.popcorn.Utilities.roundToNearestTenth;

/**
 * Class that holds all methods that bind views.
 */
public class ViewBinder {

    // Individual cast member.
    private static final String NOT_AVAILABLE = "N/A";
    private static final String NO_BIOGRAPHY = "Biography unavailable";
    private static final int PROFILE_PIC_CROSSFADE_TIME = 500;
    private static final int PROFILE_PIC_DIMS = 400;

    public static void setImageToView(Context context, String imagePath, int crossFadeTime, ImageView view) {
        if (imagePath != null) {
            GlideApp.with(context).load(imagePath)
                    .transition(DrawableTransitionOptions.withCrossFade(crossFadeTime))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(view);
        }
    }

    public static void setProfilePicToView(Context context, String imagePath, ImageView view) {
        if (imagePath != null) {
            GlideApp.with(context).load(imagePath).circleCrop()
                    .override(PROFILE_PIC_DIMS, PROFILE_PIC_DIMS)
                    .transition(DrawableTransitionOptions.withCrossFade(PROFILE_PIC_CROSSFADE_TIME))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(view);
        }
    }

    public static void setTextToView(String content, TextView view) {
        view.setText(content);
    }

    public static void setRatingToView(Context context, String content, TextView view) {
        double rating = roundToNearestTenth(content);
        String ratingAsString = convertDoubleToString(rating);
        view.setText(context.getResources().getString(R.string.rating_out_of_ten, ratingAsString));
    }

    public static void setRuntimeToView(Context context, String content, TextView view) {
        view.setText(context.getResources().getString(R.string.runtime_plus_minutes, content));
    }

    public static void setReleaseToView(String content, TextView view) {
        view.setText(formatDate(content));
    }

    public static void setGenresToView(List<String> content, TextView view) {
        view.setText(formatGenres(content.toString()));
    }

    public static void setDateToView(String content, TextView view) {
        view.setText(formatDate(content));
    }

    public static void setBirthPlaceToView(String content, TextView view) {
        if (isNotNullString(content)) {
            view.setText(content);
        } else {
            view.setText(NOT_AVAILABLE);
        }
    }

    public static void setBiographyToView(String content, TextView view) {
        if (isNotNullString(content) && isNotEmptyString(content)) {
            view.setText(content);
        } else {
            view.setText(NO_BIOGRAPHY);
        }
    }
}
