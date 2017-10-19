package com.example.android.popcorn.ui.review_recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private final String LOG_TAG = ReviewRecyclerViewAdapter.class.getSimpleName();

    private int VIEW_TYPE_WITH_REVIEWS = 0;
    private int VIEW_TYPE_WITHOUT_REVIEWS = 1;
    private final String NO_REVIEWS = "No reviews available.";

    private Context mContext;
    private List<Review> mReviews;
    private OnReviewClickListener mClickListener;

    public ReviewRecyclerViewAdapter(Context context, List<Review> reviews, OnReviewClickListener clickListener) {
        mContext = context;
        mReviews = reviews;
        mClickListener = clickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mReviews.size() == 0) {
            return VIEW_TYPE_WITHOUT_REVIEWS;
        } else {
            return VIEW_TYPE_WITH_REVIEWS;
        }
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReviewViewHolder reviewViewHolder = null;

        if (viewType == VIEW_TYPE_WITHOUT_REVIEWS) {
            reviewViewHolder = createViewHolder(R.layout.movie_review_empty, parent);
        }

        if (viewType == VIEW_TYPE_WITH_REVIEWS) {
            reviewViewHolder = createViewHolder(R.layout.movie_review, parent);
        }

        return reviewViewHolder;
    }

    private ReviewViewHolder createViewHolder(int layoutId, ViewGroup parent) {
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(layoutId, parent, false);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);

        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        int viewType = holder.getItemViewType();

        Log.v(LOG_TAG, "View type: " + viewType);

        if (viewType == VIEW_TYPE_WITHOUT_REVIEWS) {
            holder.mContent.setText(NO_REVIEWS);
        }

        if (viewType == VIEW_TYPE_WITH_REVIEWS) {
            Review review = mReviews.get(position);
            onBindAuthor(review, holder);
            onBindContent(review, holder);
        }
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

    public class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Nullable @BindView(R.id.author) TextView mAuthor;
        @Nullable @BindView(R.id.content) TextView mContent;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onClick(mReviews.get(getAdapterPosition()));
        }
    }
}
