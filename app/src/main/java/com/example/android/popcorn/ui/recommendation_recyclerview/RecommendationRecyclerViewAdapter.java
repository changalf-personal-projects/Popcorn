package com.example.android.popcorn.ui.recommendation_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.android.popcorn.R;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.ui.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.Utilities.formatDate;

/**
 * Created by alfredchang on 2017-12-05.
 */

public class RecommendationRecyclerViewAdapter extends RecyclerView.Adapter<RecommendationRecyclerViewAdapter.RecommendationViewHolder> {

    private final String LOG_TAG = RecommendationRecyclerViewAdapter.class.getSimpleName();
    private final int POSTER_WIDTH = 300;
    private final int POSTER_HEIGHT = 400;
    private final int CROSSFADE_TIME = 400;

    private Context mContext;
    private List<Movie> mMovies;
    private OnRecommendationClickListener mClickListener;

    public RecommendationRecyclerViewAdapter(Context context, List<Movie> movies, OnRecommendationClickListener clickListener) {
        mContext = context;
        mMovies = movies;
        mClickListener = clickListener;
    }

    @Override
    public RecommendationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int recLayoutId = R.layout.movie_recomendation;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(recLayoutId, parent, false);
        RecommendationViewHolder recViewHolder = new RecommendationViewHolder(view);

        return recViewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendationViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        onBindPoster(movie, holder);
        onBindTitle(movie, holder);
        onBindRelease(movie, holder);
    }

    private void onBindPoster(Movie movie, RecommendationViewHolder holder) {
        GlideApp.with(mContext).load(movie.getDetailPosterPath())
                .placeholder(R.drawable.rec_poster_placeholder)
                .override(POSTER_WIDTH, POSTER_HEIGHT)
                .transition(DrawableTransitionOptions.withCrossFade(CROSSFADE_TIME))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(holder.mRecPoster);
    }

    private void onBindTitle(Movie movie, RecommendationViewHolder holder) {
        holder.mRecPosterTitle.setText(movie.getTitle());
    }

    private void onBindRelease(Movie movie, RecommendationViewHolder holder) {
        holder.mRecPosterRelease.setText(formatDate(movie.getReleaseDate(), "yyyy"));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class RecommendationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.recommended_poster)
        ImageView mRecPoster;
        @BindView(R.id.recommended_poster_title)
        TextView mRecPosterTitle;
        @BindView(R.id.recommended_poster_release)
        TextView mRecPosterRelease;

        public RecommendationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onClick(mMovies.get(getAdapterPosition()));
        }
    }
}
