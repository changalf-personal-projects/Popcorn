package com.example.android.popcorn.ui.review_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.popcorn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-17.
 */

public class ReviewRecyclerViewAdapter {

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.author) TextView author;
        @BindView(R.id.content) TextView content;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
