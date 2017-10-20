package com.example.android.popcorn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.ui.ViewPopulator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-08.
 */

public class IndividualCastDetailFragment extends Fragment {

    private final String LOG_TAG = IndividualCastDetailFragment.class.getSimpleName();

    private final String NOT_AVAILABLE = "N/A";
    private final String NO_BIOGRAPHY = "Biography unavailable";
    private final int PROFILE_PIC_CROSSFADE_TIME = 500;
    private final int PROFILE_PIC_WIDTH = 400;
    private final int PROFILE_PIC_HEIGHT = 400;
    private final boolean SHOULD_CIRCLE_CROP = true;

    @BindView(R.id.cast_member_profile_picture) ImageView mProfilePicture;
    @BindView(R.id.cast_member_name) TextView mName;
    @BindView(R.id.birthday) TextView mBirthday;
    @BindView(R.id.death) TextView mDeath;
    @BindView(R.id.place_of_birth) TextView mPlaceOfBirth;
    @BindView(R.id.biography_description) TextView mBiography;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_single_cast_member_main, container, false);
        ButterKnife.bind(this, rootView);

        Cast castMember = getParcelableDetails();
        setParcelabeDetailIntoViews(castMember);

        return rootView;
    }

    private void setParcelabeDetailIntoViews(Cast castMember) {
        ViewPopulator.populateCustomImageView(getActivity(), castMember.getProfilePath(), mProfilePicture,
                PROFILE_PIC_WIDTH, PROFILE_PIC_HEIGHT, PROFILE_PIC_CROSSFADE_TIME, SHOULD_CIRCLE_CROP);
        ViewPopulator.populateTextView(castMember.getName(), mName);
        ViewPopulator.populateDateToTextView(castMember.getBirthday(), mBirthday);
        ViewPopulator.populateDateToTextView(castMember.getDeathday(), mDeath);
        ViewPopulator.populateBirthPlaceToTextView(castMember.getBirthplace(), NOT_AVAILABLE, mPlaceOfBirth);
        ViewPopulator.populateBiographyToTextView(castMember.getBiography(), NO_BIOGRAPHY, mBiography);
    }

    // Alt + enter if red lightbulb doesn't show up.
    private Cast getParcelableDetails() {
        Intent castMemberDetailsIntent = getActivity().getIntent();
        Cast castMember = castMemberDetailsIntent.getParcelableExtra(Utilities.PARCELABLE_CAST_MEMBER_KEY);
        return castMember;
    }
}
