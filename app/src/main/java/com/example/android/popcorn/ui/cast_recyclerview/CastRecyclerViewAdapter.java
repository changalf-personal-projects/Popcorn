package com.example.android.popcorn.ui.cast_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alfredchang on 2017-10-04.
 */

public class CastRecyclerViewAdapter extends RecyclerView.Adapter<CastRecyclerViewAdapter.CastViewHolder> {

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {

        public CastViewHolder(View itemView) {
            super(itemView);
        }
    }
}
