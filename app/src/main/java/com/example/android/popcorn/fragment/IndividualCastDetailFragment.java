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
import com.example.android.popcorn.ui.ViewBinder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alfredchang on 2017-10-08.
 */

public class IndividualCastDetailFragment extends Fragment {

    private final String LOG_TAG = IndividualCastDetailFragment.class.getSimpleName();

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
        ViewBinder.setProfilePicToView(getActivity(), castMember.getProfilePath(), mProfilePicture);
        ViewBinder.setTextToView(castMember.getName(), mName);
        ViewBinder.setDateToView(castMember.getBirthday(), mBirthday);
        ViewBinder.setDateToView(castMember.getDeathday(), mDeath);
        ViewBinder.setBirthPlaceToView(castMember.getBirthplace(), mPlaceOfBirth);
        ViewBinder.setBiographyToView(castMember.getBiography(), mBiography);
    }

    // Alt + enter if red lightbulb doesn't show up.
    private Cast getParcelableDetails() {
        Intent castMemberDetailsIntent = getActivity().getIntent();
        Cast castMember = castMemberDetailsIntent.getParcelableExtra(Utilities.PARCELABLE_CAST_MEMBER_KEY);
        return castMember;
    }
}
