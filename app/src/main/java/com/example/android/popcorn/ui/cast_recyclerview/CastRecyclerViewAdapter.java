package com.example.android.popcorn.ui.cast_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popcorn.R;
import com.example.android.popcorn.model.Cast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-04.
 */

public class CastRecyclerViewAdapter extends RecyclerView.Adapter<CastRecyclerViewAdapter.CastViewHolder> {

    private Context mContext;
    private List<Cast> mCast;

    public CastRecyclerViewAdapter(Context context, List<Cast> cast) {
        mContext = context;
        mCast = cast;
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cast_member_image) ImageView mProfilePicture;
        @BindView(R.id.cast_member_name) TextView mMemberName;

        public CastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
