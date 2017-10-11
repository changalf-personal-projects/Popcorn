package com.example.android.popcorn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.ui.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.EmptyChecker.isNotEmptyString;
import static com.example.android.popcorn.NullChecker.isNotNullString;
import static com.example.android.popcorn.Utilities.formatDate;

/**
 * Created by alfredchang on 2017-10-08.
 */

public class IndividualCastDetailFragment extends Fragment {

    private final String LOG_TAG = IndividualCastDetailFragment.class.getSimpleName();
    private final String NOT_AVAILABLE = "N/A";
    private final String NO_BIOGRAPHY = "Biography unavailable";

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
        setProfilePicture(castMember);
        setName(castMember);
        setBirthday(castMember);
        setDeathday(castMember);
        setBirthplace(castMember);
        setBiography(castMember);
    }

    // Alt + enter if red lightbulb doesn't show up.
    private Cast getParcelableDetails() {
        Intent castMemberDetailsIntent = getActivity().getIntent();
        Cast castMember = castMemberDetailsIntent.getParcelableExtra(Utilities.PARCELABLE_CAST_MEMBER_KEY);
        return castMember;
    }

    private void setProfilePicture(Cast castMember) {
        String profilePath = castMember.getProfilePath();

        if (profilePath != null) {
            GlideApp.with(getActivity()).load(profilePath).circleCrop().into(mProfilePicture);
        }
    }

    private void setName(Cast castMember) {
        mName.setText(castMember.getName());
    }

    private void setBirthday(Cast castMember) {
        mBirthday.setText(formatDate(castMember.getBirthday()));
    }

    private void setDeathday(Cast castMember) {
        String death = castMember.getDeathday();
        if (isNotNullString(death)) {
            mDeath.setText(formatDate(death));
        } else {
            mDeath.setText(NOT_AVAILABLE);
        }
    }

    private void setBirthplace(Cast castMember) {
        String placeOfBirth = castMember.getBirthplace();
        if (isNotNullString(placeOfBirth)) {
            mPlaceOfBirth.setText(castMember.getBirthplace());
        } else {
            mPlaceOfBirth.setText(NOT_AVAILABLE);
        }
    }

    private void setBiography(Cast castMember) {
        String biography = castMember.getBiography();
        Log.v(LOG_TAG, "Biography: " + biography);
        if (isNotNullString(biography) && isNotEmptyString(biography)) {
            mBiography.setText(castMember.getBiography());
        } else {
            mBiography.setText(NO_BIOGRAPHY);
        }
    }
}
