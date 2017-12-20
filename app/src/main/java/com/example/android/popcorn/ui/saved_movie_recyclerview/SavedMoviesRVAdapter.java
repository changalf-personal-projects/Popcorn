package com.example.android.popcorn.ui.saved_movie_recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android.popcorn.R;
import com.example.android.popcorn.data.DbContract;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.ui.GlideApp;
import com.example.android.popcorn.ui.poster_recyclerview.PosterRecyclerViewAdapter;
import com.github.florent37.glidepalette.GlidePalette;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.Utilities.convertDoubleToString;
import static com.example.android.popcorn.Utilities.roundToNearestTenth;
import static com.example.android.popcorn.ui.ColourFiller.hasSwatch;

/**
 * Created by alfredchang on 2017-12-17.
 */

public class SavedMoviesRVAdapter extends RecyclerView.Adapter<SavedMoviesRVAdapter.PosterViewHolder> {

    private final String LOG_TAG = PosterRecyclerViewAdapter.class.getSimpleName();

    // Approximate dimensions.
    private final int POSTER_WIDTH = 700;
    private final int POSTER_HEIGHT = 800;
    private final int CROSSFADE_TIME = 800;
    private final int FIRST_GENRE = 0;
    private final String NO_GENRE = "No genre";

    private List<Movie> mSavedMovies;
    private Context mContext;
    private Cursor mCursor;
    private OnMovieClickListener mClickListener;

    public SavedMoviesRVAdapter(List<Movie> savedMovies, Cursor cursor, OnMovieClickListener clickListener) {
        mSavedMovies = savedMovies;
        mCursor = cursor;
        mClickListener = clickListener;
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Source: https://classroom.udacity.com/courses/ud851/lessons/
        // c81cb722-d20a-495a-83c6-6890a6142aac/concepts/ae70fe56-dbd3-446c-be43-b8da0f076ea6.
        mContext = parent.getContext();
        int movieLayoutId = R.layout.movie_poster;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(movieLayoutId, parent, false);
        PosterViewHolder posterViewHolder = new PosterViewHolder(view);

        return posterViewHolder;
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        String posterPath = mCursor.getString(mCursor.getColumnIndex(DbContract.SavedMoviesEntryMain.COLUMN_POSTER_PATH));
        String title = mCursor.getString(mCursor.getColumnIndex(DbContract.SavedMoviesEntryMain.COLUMN_TITLE));
        String rating = mCursor.getString(mCursor.getColumnIndex(DbContract.SavedMoviesEntryMain.COLUMN_RATING));
        String genres = mCursor.getString(mCursor.getColumnIndex(DbContract.SavedMoviesEntryMain.COLUMN_GENRES));

        onBindPoster(posterPath, holder);
        onBindTitle(title, holder);
        onBindRating(rating, holder);
        onBindGenres(genres, holder);
    }

    // The GlideApp.method(...) can't be moved to ViewPopulator.java because it requires a holder
    // parameter, which can't be used in other classes.
    private void onBindPoster(String path, final PosterViewHolder holder) {
        if (path != null) {
            GlideApp.with(mContext).load(path)
                    .listener(GlidePalette.with(path)
                            .intoCallBack(new GlidePalette.CallBack() {
                                @Override
                                public void onPaletteLoaded(Palette palette) {
                                    onBindColorToCardView(holder, palette.getSwatches());
                                }
                            })
                    )
                    .override(POSTER_WIDTH, POSTER_HEIGHT)
                    .placeholder(R.drawable.poster_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade(CROSSFADE_TIME))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(holder.mPoster);
        }
    }

    private void onBindColorToCardView(PosterViewHolder holder, List<Palette.Swatch> swatches) {
        for (Palette.Swatch swatch: swatches) {
            if (hasSwatch(swatch)) {
                holder.mLinearLayout.setBackgroundColor(swatch.getRgb());
                holder.mTitle.setTextColor(swatch.getTitleTextColor());
                holder.mRating.setTextColor(swatch.getBodyTextColor());
                holder.mGenres.setTextColor(swatch.getBodyTextColor());
            }
        }
    }

    private void onBindTitle(String title, PosterViewHolder holder) {
        holder.mTitle.setText(title);
    }

    private void onBindRating(String rating, PosterViewHolder holder) {
        double roundedRating = roundToNearestTenth(rating);
        String ratingAsString = convertDoubleToString(roundedRating);
        holder.mRating.setText(mContext.getResources().getString(R.string.rating_out_of_ten, ratingAsString));
    }

    // Actual type of genres value in table is string, not list of strings.  May need to change in contract class.
    // May need to serialize first.
    private void onBindGenres(String genre, PosterViewHolder holder) {
        if (genre != null) {
            holder.mGenres.setText(genre);
        } else {
            holder.mGenres.setText(NO_GENRE);
        }
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_poster)
        ImageView mPoster;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.rating)
        TextView mRating;
        @BindView(R.id.genres)
        TextView mGenres;
        @BindView(R.id.linear_layout)
        LinearLayout mLinearLayout;

        public PosterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onClick(mSavedMovies.get(getAdapterPosition()));
        }
    }
}
