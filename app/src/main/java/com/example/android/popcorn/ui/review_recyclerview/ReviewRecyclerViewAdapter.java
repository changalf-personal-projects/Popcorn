package com.example.android.popcorn.ui.review_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popcorn.R;
import com.example.android.popcorn.fragment.DetailFragment;
import com.example.android.popcorn.model.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-17.
 */

public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ReviewViewHolder> {

    private final String LOG_TAG = ReviewRecyclerViewAdapter.class.getSimpleName();

    private Context mContext;
    private List<Review> mReviews;
    private OnReviewClickListener mClickListener;

    public ReviewRecyclerViewAdapter(Context context, List<Review> reviews, OnReviewClickListener clickListener) {
        mContext = context;
        mReviews = reviews;
        mClickListener = clickListener;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = createViewHolder(R.layout.movie_review, parent);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);

        return reviewViewHolder;
    }

    private View createViewHolder(int layoutId, ViewGroup parent) {
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(layoutId, parent, false);

        return view;
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
        if (review.getContent().equals(DetailFragment.NO_REVIEWS_MESSAGE)) {
            holder.mContent.setGravity(Gravity.CENTER);
        }
        holder.mContent.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public class ReviewViewHolder extends ViewHolder implements View.OnClickListener {

//        @Nullable
        @BindView(R.id.author) TextView mAuthor;
//        @Nullable
        @BindView(R.id.content) TextView mContent;

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
