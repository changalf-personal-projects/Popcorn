package com.example.android.popcorn;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.model.Trailer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.model.MoviesSingleton.getSingletonMovies;

/**
 * Created by alfredchang on 2017-10-23.
 */

public class YoutubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener,
        YouTubePlayer.OnFullscreenListener {

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
        int timePaused = 0;
        List<Movie> movies = getSingletonMovies();
        List<String> trailerIds = getTrailerIdsFromMovies(movies);

        if (!shouldContinue) {
            youTubePlayer.setFullscreen(true);
            youTubePlayer.setOnFullscreenListener(this);
            youTubePlayer.loadVideos(trailerIds);
        }

        // If shouldContinue == true and isPlaying(), then loadVideo(id, timePaused) to continue playing
        // video.
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

    @Override
    public void onFullscreen(boolean b) {

    }

    private List<String> getTrailerIdsFromMovies(List<Movie> movies) {
        List<String> ids = new ArrayList<>();

        for (Movie movie: movies) {
            for (Trailer trailer: movie.getTrailers()) {
                // TODO: This will only play the trailers of the first movie in list of movies.
                ids.add(trailer.getKey());
            }
        }

        return ids;
    }
}
