package com.example.android.popcorn.ui;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

/**
 * Class that holds all methods that bind views.
 */
public class ViewBinder {

    public static void setImageToView(Context context, String imagePath, int crossfadeTime, ImageView view) {
        GlideApp.with(context).load(imagePath)
                .transition(DrawableTransitionOptions.withCrossFade(crossfadeTime))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(view);
    }
}
