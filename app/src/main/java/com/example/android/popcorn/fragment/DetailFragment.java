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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.YoutubePlayerActivity;
import com.example.android.popcorn.activity.IndividualCastDetailActivity;
import com.example.android.popcorn.activity.IndividualReviewActivity;
import com.example.android.popcorn.fragment.parsing.LoganCastMemberDetailTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.model.Review;
import com.example.android.popcorn.model.Trailer;
import com.example.android.popcorn.networking.RequestQueueSingleton;
import com.example.android.popcorn.ui.cast_recyclerview.OnCastMemberClickListener;
import com.example.android.popcorn.ui.review_recyclerview.OnReviewClickListener;
import com.example.android.popcorn.ui.trailer_recyclerview.OnTrailerClickListener;
import com.example.android.popcorn.ui.trailer_recyclerview.TrailerRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.networking.UrlCreator.createCastMemberDetailUrl;
import static com.example.android.popcorn.ui.ViewPopulator.populateTextView;

/**
 * Created by alfredchang on 2017-09-27.
 */

public class DetailFragment extends Fragment implements OnCastMemberClickListener, OnReviewClickListener, OnTrailerClickListener {

    private final String LOG_TAG = DetailFragment.class.getSimpleName();
    // Must pass a value to ViewPopulator.populateImageView(...), but don't want any crossfade time.
    private final int BACKDROP_CROSSFADE_TIME = 0;
    private final int POSTER_CROSSFADE_TIME = 700;
    private final int EMPTY = 0;
    private static final String EMPTY_STRING = "";
    public static final String NO_REVIEWS_MESSAGE = "No reviews posted yet.";

    private TrailerRecyclerViewAdapter mTrailerRecyclerAdapter;
    private Movie movie;

    @BindView(R.id.synopsis)
    TextView mSynopsis;
    @BindView(R.id.trailer_recycler_view)
    RecyclerView mTrailerRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_detail_main, container, false);
        ButterKnife.bind(this, rootView);

//        setupCastRecyclerView();
//        setupReviewRecyclerView();
        setupTrailerRecyclerView();

        movie = getParcelableMovie();

        setParcelableDetailsIntoViews(movie);
        fetchJsonCastMemberDetails(movie);
        onClickFavouriteButton();

        return rootView;
    }

//    private void setupCastRecyclerView() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
//                LinearLayoutManager.HORIZONTAL, false);
//        mCastRecyclerView.setLayoutManager(layoutManager);
//    }

//    private void setupReviewRecyclerView() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
//                LinearLayoutManager.VERTICAL, false);
//        mReviewRecyclerView.setNestedScrollingEnabled(false);
//        mReviewRecyclerView.setLayoutManager(layoutManager);
//    }

    private void setupTrailerRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        mTrailerRecyclerView.setLayoutManager(layoutManager);
    }

//    private void attachToCastAdapter(Movie movie) {
//        mCastRecyclerAdapter = new CastRecyclerViewAdapter(getActivity(), movie.getCast(), this);
//        mCastRecyclerView.setAdapter(mCastRecyclerAdapter);
//    }

//    private void attachToReviewAdapter(Movie movie) {
//        List<Review> reviews = movie.getReviews();
//
//        // Hacky way of printing message indicating no reviews posted yet.  Better solution is
//        // to switch layouts.  Doesn't always seem to work.
//        if (reviews.size() == EMPTY) {
//            Review emptyReview = new Review();
//            emptyReview.setAuthor(EMPTY_STRING);
//            // This string will be used to signal onBindViewHolder() that there are no reviews yet.
//            emptyReview.setContent(NO_REVIEWS_MESSAGE);
//            reviews.add(emptyReview);
//        }
//
//        mReviewRecyclerAdapter = new ReviewRecyclerViewAdapter(getActivity(), reviews, this);
////        mReviewRecyclerView.setAdapter(mReviewRecyclerAdapter);
//    }

    private void attachToTrailerAdapter(Movie movie) {
        mTrailerRecyclerAdapter = new TrailerRecyclerViewAdapter(getActivity(), movie.getTrailers(), this);
        mTrailerRecyclerView.setAdapter(mTrailerRecyclerAdapter);
    }

    // Display cast member details in a separate fragment.
    @Override
    public void onClick(Cast castMember) {
        Intent singleCastMemberDetailsIntent = new Intent(getActivity(), IndividualCastDetailActivity.class);
        singleCastMemberDetailsIntent.putExtra(Utilities.PARCELABLE_CAST_MEMBER_KEY, castMember);
        startActivity(singleCastMemberDetailsIntent);
    }

    // Display individual review in a separate fragment.
    @Override
    public void onClick(Review review) {
        Movie movie = getParcelableMovie();
        Intent readReviewIntent = new Intent(getActivity(), IndividualReviewActivity.class);
        readReviewIntent.putExtra(Utilities.PARCELABLE_MOVIE_KEY, movie);
        readReviewIntent.putExtra(Utilities.PARCELABLE_REVIEW_KEY, review);
        startActivity(readReviewIntent);
    }

    // Play trailer in a different app.
    @Override
    public void onClick(Trailer trailer) {
        Intent playerTrailerIntent = new Intent(getActivity(), YoutubePlayerActivity.class);
        playerTrailerIntent.putExtra(Utilities.PARCELABLE_TRAILER_KEY, trailer);
        playerTrailerIntent.putStringArrayListExtra(Utilities.PARCELABLE_TRAILER_IDS_KEY, (ArrayList<String>) movie.getTrailerIds());
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

    private void fetchJsonCastMemberDetails(Movie movie) {
        List<Cast> cast = movie.getCast();
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

    private Movie getParcelableMovie() {
        Intent movieIntent = getActivity().getIntent();
        Movie movie = movieIntent.getParcelableExtra(Utilities.PARCELABLE_MOVIE_KEY);
        return movie;
    }

    private void setParcelableDetailsIntoViews(Movie movie) {
        populateTextView(movie.getSynopsis(), mSynopsis);

//        attachToCastAdapter(movie);
//        attachToReviewAdapter(movie);
        attachToTrailerAdapter(movie);
    }
}
