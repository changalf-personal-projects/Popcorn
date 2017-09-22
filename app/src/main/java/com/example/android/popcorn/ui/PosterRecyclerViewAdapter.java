package com.example.android.popcorn.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.popcorn.MovieTerms;
import com.example.android.popcorn.R;
import com.example.android.popcorn.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-09-21.
 */

public class PosterRecyclerViewAdapter extends RecyclerView.Adapter<PosterRecyclerViewAdapter.PosterViewHolder> {

    private final String LOG_TAG = PosterRecyclerViewAdapter.class.getSimpleName();

    private Context mContext;
    private List<Movie> mListOfMovies;

    public PosterRecyclerViewAdapter(List<Movie> listOfMovies) {
        mListOfMovies = listOfMovies;
    }

    @Override
    public PosterRecyclerViewAdapter.PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        int movieLayoutId = R.layout.movie_poster;

        View view = layoutInflater.inflate(movieLayoutId, parent, false);
        PosterViewHolder posterViewHolder = new PosterViewHolder(view);

        return posterViewHolder;
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        Movie movie = mListOfMovies.get(position);
        if (hasPosterPath(movie)) {
            Glide.with(mContext).load(MovieTerms.POSTER_BASE_URL + MovieTerms.POSTER_SIZE
                    + movie.getPosterPath()).into(holder.mPoster);
        }
    }

    private boolean hasPosterPath(Movie movie) {
        return movie.getPosterPath() != null;
    }

    @Override
    public int getItemCount() {
        return mListOfMovies.size();
    }

    public class PosterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_poster) ImageView mPoster;

        public PosterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
