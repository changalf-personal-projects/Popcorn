package com.example.android.popcorn.ui.review_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popcorn.R;
import com.example.android.popcorn.model.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-17.
 */

public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ReviewViewHolder> {

    private Context mContext;
    private List<Review> mReviews;

    public ReviewRecyclerViewAdapter(List<Review> reviews) {
        mReviews = reviews;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int reviewLayoutId = R.layout.movie_review;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(reviewLayoutId, parent, false);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);

        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        Review review = mReviews.get(position);
        onBindAuthor(review, holder);
        onBindContent(review, holder);
    }

    private void onBindAuthor(Review review, ReviewViewHolder holder) {
        holder.mAuthor.setText(review.getAuthor());
    }

    private void onBindContent(Review review, ReviewViewHolder holder) {
        holder.mContent.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.author) TextView mAuthor;
        @BindView(R.id.content) TextView mContent;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
