package com.example.android.popcorn.ui.trailer_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.android.popcorn.R;
import com.example.android.popcorn.model.Trailer;
import com.example.android.popcorn.ui.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.networking.UrlCreator.createYoutubeThumbnailUrl;

/**
 * Created by alfredchang on 2017-10-22.
 */

public class TrailerRecyclerViewAdapter extends RecyclerView.Adapter<TrailerRecyclerViewAdapter.TrailerViewHolder> {

    private final String LOG_TAG = TrailerRecyclerViewAdapter.class.getSimpleName();

    private Context mContext;
    private List<Trailer> mTrailers;

    public TrailerRecyclerViewAdapter(Context context, List<Trailer> trailers) {
        mContext = context;
        mTrailers = trailers;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.movie_trailer, parent, false);
        TrailerViewHolder trailerViewHolder = new TrailerViewHolder(view);

        return trailerViewHolder;
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        Trailer trailer = mTrailers.get(position);
        onBindTrailerThumbnail(trailer, holder);
    }

    private void onBindTrailerThumbnail(Trailer trailer, TrailerViewHolder holder) {
        String thumbnailUrl = createYoutubeThumbnailUrl(trailer.getKey());
        GlideApp.with(mContext).load(thumbnailUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(holder.mTrailerThumbnail);
    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    public class TrailerViewHolder extends ViewHolder {

        @BindView(R.id.trailer_thumbnail)
        ImageView mTrailerThumbnail;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
