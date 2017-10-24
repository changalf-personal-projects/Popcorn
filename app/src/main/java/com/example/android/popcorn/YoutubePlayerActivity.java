package com.example.android.popcorn;

import android.content.Intent;

import com.example.android.popcorn.model.Trailer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;

/**
 * Created by alfredchang on 2017-10-23.
 */

public class YoutubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean shouldContinue) {
        Trailer trailer = getVideoIdFromParcelable();
        String trailerId = getTrailerIdFromTrailer(trailer);
        if (!shouldContinue) {
            youTubePlayer.loadVideo(trailerId);
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private Trailer getVideoIdFromParcelable() {
        Intent playTrailerIntent = getIntent();
        Trailer trailer = playTrailerIntent.getParcelableExtra(Utilities.PARCELABLE_TRAILER_KEY);
        return trailer;
    }

    private String getTrailerIdFromTrailer(Trailer trailer) {
        return trailer.getKey();
    }
}
