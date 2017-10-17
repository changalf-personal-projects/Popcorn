package com.example.android.popcorn.ui.cast_recyclerview;

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
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.ui.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-04.
 */

public class CastRecyclerViewAdapter extends RecyclerView.Adapter<CastRecyclerViewAdapter.CastViewHolder> {

    private final String LOG_TAG = CastRecyclerViewAdapter.class.getSimpleName();
    private final int CROSSFADE_TIME = 250;

    private Context mContext;
    private List<Cast> mCast;
    private OnCastMemberClickListener mClickListener;

    public CastRecyclerViewAdapter(Context context, List<Cast> cast, OnCastMemberClickListener clickListener) {
        mContext = context;
        mCast = cast;
        mClickListener = clickListener;
    }

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int castLayoutId = R.layout.movie_cast;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(castLayoutId, parent, false);
        CastViewHolder castViewHolder = new CastViewHolder(view);

        return castViewHolder;
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {
        Cast cast = mCast.get(position);
        onBindProfilePicture(cast, holder);
        onBindMemberName(cast, holder);
    }

    @Override
    public int getItemCount() {
        return mCast.size();
    }

    private void onBindProfilePicture(Cast cast, CastViewHolder holder) {
        if (cast.getProfilePath() != null) {
            GlideApp.with(mContext).load(cast.getThumbnail())
                    .transition(DrawableTransitionOptions.withCrossFade(CROSSFADE_TIME))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(holder.mProfilePicture);
        }
    }

    private void onBindMemberName(Cast cast, CastViewHolder holder) {
        holder.mMemberName.setText(cast.getName());
    }

    public class CastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.cast_member_image) ImageView mProfilePicture;
        @BindView(R.id.cast_member_as_character) TextView mMemberName;

        public CastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onClick(mCast.get(getAdapterPosition()));
        }
    }
}
