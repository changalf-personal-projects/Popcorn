package com.example.android.popcorn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android.popcorn.model.Trailer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;

/**
 * Created by alfredchang on 2017-10-23.
 */

public class YoutubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private final String LOG_TAG = YoutubePlayerActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean shouldContinue) {
        Trailer trailer = getVideoIdFromParcelable();
        String trailerId = getTrailerIdFromTrailer(trailer);
        Log.v(LOG_TAG, "Trailer id: " + trailerId);
        if (!shouldContinue) {
            Toast.makeText(this, "Playing video...", Toast.LENGTH_SHORT).show();
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
