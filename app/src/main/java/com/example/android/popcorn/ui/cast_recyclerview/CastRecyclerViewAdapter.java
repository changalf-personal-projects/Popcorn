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
import com.example.android.popcorn.ui.ViewPopulator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.ui.LayoutPropertiesInitializer.initImageViewProperties;

/**
 * Created by alfredchang on 2017-10-04.
 */

public class CastRecyclerViewAdapter extends RecyclerView.Adapter<CastRecyclerViewAdapter.CastViewHolder> {

    private final String LOG_TAG = CastRecyclerViewAdapter.class.getSimpleName();

    private Context mContext;
    private List<Cast> mCast;
    private OnCastMemberClickListener mClickListener;

    public CastRecyclerViewAdapter(Context context, List<Cast> cast, OnCastMemberClickListener clickListener) {
        // Context needs to be passed in constructor in order for Parcelable to work.
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
        onBindCharacterName(cast, holder);
    }

    @Override
    public int getItemCount() {
        return mCast.size();
    }

    private void onBindProfilePicture(Cast cast, CastViewHolder holder) {
//        if (cast.getProfilePath() != null) {
            ViewPopulator.populateImageViewNoCrossfade(initImageViewProperties(mContext, cast.getProfilePath(),
                    holder.mProfilePicture));
//        }
    }

    private void onBindMemberName(Cast cast, CastViewHolder holder) {
        holder.mMemberName.setText(cast.getName());
    }

    private void onBindCharacterName(Cast cast, CastViewHolder holder) {
        holder.mCharacterName.setText(cast.getCharacter());
    }

    public class CastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.cast_member_image) ImageView mProfilePicture;
        @BindView(R.id.cast_member_name) TextView mMemberName;
        @BindView(R.id.cast_member_character) TextView mCharacterName;

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
