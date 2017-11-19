package com.example.android.popcorn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-23.
 */

public class YoutubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private final String LOG_TAG = YoutubePlayerActivity.class.getSimpleName();
    private final int REQUEST_CODE = 1;

    @BindView(R.id.youtube_player) YouTubePlayerView mYtPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        ButterKnife.bind(this);

        mYtPlayer.initialize(BuildConfig.YOUTUBE_DATA_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean shouldContinue) {
        List<String> trailerIds = getParcelableTrailerIds();

        if (!shouldContinue) {
            youTubePlayer.setFullscreen(true);
            youTubePlayer.loadVideos(trailerIds);
        }
    }

    // Source: https://www.sitepoint.com/using-the-youtube-api-to-embed-video-in-an-android-app/.
    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            Toast.makeText(this, youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private List<String> getParcelableTrailerIds() {
        Intent intent = getIntent();
        List<String> trailerIds = intent.getStringArrayListExtra(Utilities.PARCELABLE_TRAILER_IDS_KEY);
        return trailerIds;
    }
}
