package com.example.android.popcorn.ui.trailer_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popcorn.model.Trailer;

import java.util.List;

/**
 * Created by alfredchang on 2017-10-22.
 */

public class TrailerRecyclerViewAdapter extends RecyclerView.Adapter<TrailerRecyclerViewAdapter.TrailerViewHolder> {

    private Context mContext;
    private List<Trailer> mTrailers;

    public TrailerRecyclerViewAdapter(Context context, List<Trailer> trailers) {
        mContext = context;
        mTrailers = trailers;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    public class TrailerViewHolder extends ViewHolder {

        public TrailerViewHolder(View itemView) {
            super(itemView);
        }

    }
}
