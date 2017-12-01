package com.example.android.popcorn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.YoutubePlayerActivity;
import com.example.android.popcorn.fragment.parsing.LoganCastMemberDetailTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.model.Trailer;
import com.example.android.popcorn.networking.RequestQueueSingleton;
import com.example.android.popcorn.ui.trailer_recyclerview.OnTrailerClickListener;
import com.example.android.popcorn.ui.trailer_recyclerview.TrailerRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.networking.UrlCreator.createCastMemberDetailUrl;
import static com.example.android.popcorn.ui.LayoutPropertiesInitializer.initImageViewProperties;
import static com.example.android.popcorn.ui.ViewPopulator.populateStringListToTextView;
import static com.example.android.popcorn.ui.ViewPopulator.populateImageViewNoCrossfade;
import static com.example.android.popcorn.ui.ViewPopulator.populateTextView;
import static com.example.android.popcorn.ui.ViewPopulator.populateTextViewWithSpaces;

/**
 * Created by alfredchang on 2017-09-27.
 */

public class DetailFragment extends Fragment implements OnTrailerClickListener {

    private final String LOG_TAG = DetailFragment.class.getSimpleName();
    private final int DIRECTOR_PICTURE_DIMS = 50;

    private TrailerRecyclerViewAdapter mTrailerRecyclerAdapter;
    private Movie mMovie;

    @BindView(R.id.tagline)
    TextView mTagline;
    @BindView(R.id.overview)
    TextView mSynopsis;
    @BindView(R.id.director_profile_picture)
    ImageView mDirectorPicture;
    @BindView(R.id.director_name)
    TextView mDirectorName;
    @BindView(R.id.producer_profile_picture)
    ImageView mProducerPicture;
    @BindView(R.id.producer_name)
    TextView mProducerName;
    @BindView(R.id.movie_languages)
    TextView mLanguages;
    @BindView(R.id.movie_budget)
    TextView mBudget;
    @BindView(R.id.movie_revenue)
    TextView mRevenue;
    @BindView(R.id.movie_prod_company)
    TextView mProductionCompanies;
    @BindView(R.id.trailer_recycler_view)
    RecyclerView mTrailerRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_detail_main, container, false);
        ButterKnife.bind(this, rootView);

        setupTrailerRecyclerView();
        getParcelableMovie();

        setParcelableDetailsIntoViews();
        fetchJsonCastMemberDetails();
        onClickFavouriteButton();

        return rootView;
    }

    private void setupTrailerRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        mTrailerRecyclerView.setLayoutManager(layoutManager);
    }

    private void attachToTrailerAdapter(Movie movie) {
        mTrailerRecyclerAdapter = new TrailerRecyclerViewAdapter(getActivity(), movie.getTrailers(), this);
        mTrailerRecyclerView.setAdapter(mTrailerRecyclerAdapter);
    }

    @Override
    public void onClick(Trailer trailer) {
        Intent playerTrailerIntent = new Intent(getActivity(), YoutubePlayerActivity.class);
        playerTrailerIntent.putExtra(Utilities.PARCELABLE_TRAILER_KEY, trailer);
        playerTrailerIntent.putStringArrayListExtra(Utilities.PARCELABLE_TRAILER_IDS_KEY, (ArrayList<String>) mMovie.getTrailerIds());
        startActivity(playerTrailerIntent);
    }

    private void onClickFavouriteButton() {
        // TODO: Button will reset to unliked if current fragment is destroyed.
//        mFavouriteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!mIsPressedFlag) {
//                    mFavouriteButton.setBackgroundResource(R.mipmap.ic_favourited);
//                    mIsPressedFlag = true;
//                } else {
//                    mFavouriteButton.setBackgroundResource(R.mipmap.ic_favourite);
//                    mIsPressedFlag = false;
//                }
//            }
//        });
    }

    private void fetchJsonCastMemberDetails() {
        List<Cast> cast = mMovie.getCast();
        for (int i = 0; i < cast.size(); i++) {
            final Cast castMember = cast.get(i);
            String url = createCastMemberDetailUrl(castMember.getId());

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            LoganCastMemberDetailTemplate castMemberLogan = MovieParser.parseJsonCastMemberData(response);
                            saveCastMemberDetails(castMember, castMemberLogan);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(LOG_TAG, "Response error (fetchJsonCastMemberDetails): " + error);
                }
            });

            RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
        }
    }

    private void saveCastMemberDetails(Cast castMember, LoganCastMemberDetailTemplate castMemberLogan) {
        castMember.setBirthday(castMemberLogan.getBirthday());
        castMember.setDeathday(castMemberLogan.getDeathDate());
        castMember.setBiography(castMemberLogan.getBiography());
        castMember.setBirthplace(castMemberLogan.getBirthPlace());
    }

    private void getParcelableMovie() {
        Intent movieIntent = getActivity().getIntent();
        mMovie = movieIntent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);
    }

    private void setParcelableDetailsIntoViews() {
        String tagline = mMovie.getTagline();

        if (hasTagline(tagline)) {
            mTagline.setVisibility(View.VISIBLE);
            populateTextView(mMovie.getTagline(), mTagline);
        }

        populateTextView(mMovie.getOverview(), mSynopsis);
        populateImageViewNoCrossfade(initImageViewProperties(getActivity(), mMovie.getDirector().getProfilePath(),
                mDirectorPicture));
        populateTextView(mMovie.getDirector().getName(), mDirectorName);
        populateImageViewNoCrossfade(initImageViewProperties(getActivity(), mMovie.getProducer().getProfilePath(),
                mProducerPicture));
        populateTextView(mMovie.getProducer().getName(), mProducerName);
        populateStringListToTextView(mMovie.getLanguages(), mLanguages);
        populateTextViewWithSpaces(mMovie.getBudget(), mBudget);
        populateTextViewWithSpaces(mMovie.getRevenue(), mRevenue);
        populateStringListToTextView(mMovie.getProductionCompanies(), mProductionCompanies);

        attachToTrailerAdapter(mMovie);
    }

    private boolean hasTagline(String tagline) {
        return !tagline.equals("");
    }
}
