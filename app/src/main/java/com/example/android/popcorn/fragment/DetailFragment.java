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

import com.android.volley.VolleyError;
import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.YoutubePlayerActivity;
import com.example.android.popcorn.activity.DetailActivity;
import com.example.android.popcorn.fragment.parsing.LoganCastMemberDetailTemplate;
import com.example.android.popcorn.fragment.parsing.LoganDetailsTemplate;
import com.example.android.popcorn.fragment.parsing.LoganIdTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.fragment.saving.DataSaver;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.model.Director;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.model.Producer;
import com.example.android.popcorn.model.Trailer;
import com.example.android.popcorn.networking.VolleyHelper;
import com.example.android.popcorn.networking.VolleyRequestHandler;
import com.example.android.popcorn.ui.recommendation_recyclerview.OnRecommendationClickListener;
import com.example.android.popcorn.ui.recommendation_recyclerview.RecommendationRecyclerViewAdapter;
import com.example.android.popcorn.ui.trailer_recyclerview.OnTrailerClickListener;
import com.example.android.popcorn.ui.trailer_recyclerview.TrailerRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.networking.UrlCreator.appendEndpoints;
import static com.example.android.popcorn.networking.UrlCreator.createCastMemberDetailUrl;
import static com.example.android.popcorn.networking.UrlCreator.createRecommendedMoviesUrl;
import static com.example.android.popcorn.networking.UrlCreator.createUrlWithAppendedResponse;
import static com.example.android.popcorn.ui.LayoutPropertiesInitializer.initImageViewProperties;
import static com.example.android.popcorn.ui.ViewPopulator.populateImageViewNoCrossfade;
import static com.example.android.popcorn.ui.ViewPopulator.populateStringListToTextView;
import static com.example.android.popcorn.ui.ViewPopulator.populateStringSetToTextView;
import static com.example.android.popcorn.ui.ViewPopulator.populateTextView;
import static com.example.android.popcorn.ui.ViewPopulator.populateTextViewWithSpaces;

/**
 * Created by alfredchang on 2017-09-27.
 */

public class DetailFragment extends Fragment implements OnTrailerClickListener, OnRecommendationClickListener {

    private final String LOG_TAG = DetailFragment.class.getSimpleName();

    private TrailerRecyclerViewAdapter mTrailerRecyclerAdapter;
    private RecommendationRecyclerViewAdapter mRecRecyclerAdapter;
    private VolleyRequestHandler mVolleyReqHandler;
    private VolleyHelper mVolleyHelper;
    private DataSaver mDataSaver;
    private Movie mMovie;
    private Set<String> languageSet = new HashSet<>();

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
    @BindView(R.id.recommendation_recycler_view)
    RecyclerView mRecRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_detail_main, container, false);
        ButterKnife.bind(this, rootView);

        setupTrailerRecyclerView();
        setupRecMoviesRecyclerView();
        getParcelableMovie();

        initDataSaver();
        initVolleyHandler();
        initVolleyHelper();
        getLanguages();

        setParcelableDetailsIntoViews();
        fetchJsonCastMemberDetails();
        fetchJsonRecommendedIds();

        onClickFavouriteButton();

        return rootView;
    }

    private void getLanguages() {
        languageSet.addAll(mMovie.getLanguages());
    }

    private void initDataSaver() {
        mDataSaver = new DataSaver(this);
    }

    private void initVolleyHelper() {
        mVolleyHelper = new VolleyHelper(getActivity(), mVolleyReqHandler);
    }

    private void initVolleyHandler() {
        mVolleyReqHandler = new VolleyRequestHandler() {
            @Override
            public void onSuccessId(String response) {
                // Unused.
            }

            @Override
            public void onSuccessDetails(String response, Movie movie) {
                // Unused.
            }

            @Override
            public void onSuccessRecommendedIds(String response) {
                LoganIdTemplate loganIds = MovieParser.parseJsonIdData(response);
                mDataSaver.saveRecMovieIdInDetail(mMovie, loganIds);
            }

            @Override
            public void onSuccessRecommendedDetails(String response, Movie movie) {
                LoganDetailsTemplate loganDetails = MovieParser.parseJsonDetailsData(response);
                mDataSaver.saveMovieDetails(movie, loganDetails);
                mDataSaver.saveMovieCast(movie, loganDetails);
                mDataSaver.saveMovieCrew(movie, loganDetails);
                mDataSaver.saveMovieTrailers(movie, loganDetails);
            }

            @Override
            public void onSuccessCastMember(String response, Cast member) {
                LoganCastMemberDetailTemplate castMemberLogan = MovieParser.parseJsonCastMemberData(response);
                saveCastMemberDetails(member, castMemberLogan);
            }

            @Override
            public void onFail(VolleyError error) {
                Log.e(LOG_TAG, "initVolleyHandler() error: " + error.networkResponse.statusCode);
            }
        };
    }

    private void setupTrailerRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        mTrailerRecyclerView.setLayoutManager(layoutManager);
    }

    private void setupRecMoviesRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        mRecRecyclerView.setLayoutManager(layoutManager);
    }

    private void attachToTrailerAdapter(Movie movie) {
        mTrailerRecyclerAdapter = new TrailerRecyclerViewAdapter(getActivity(), movie.getTrailers(), this);
        mTrailerRecyclerView.setAdapter(mTrailerRecyclerAdapter);
    }

    private void attachToRecommendationAdapter(Movie movie) {
        mRecRecyclerAdapter = new RecommendationRecyclerViewAdapter(getActivity(), movie.getRecMovies(), this);
        mRecRecyclerView.setAdapter(mRecRecyclerAdapter);
    }

    @Override
    public void onClick(Trailer trailer) {
        Intent playerTrailerIntent = new Intent(getActivity(), YoutubePlayerActivity.class);
        playerTrailerIntent.putExtra(Utilities.PARCELABLE_TRAILER_KEY, trailer);
        playerTrailerIntent.putStringArrayListExtra(Utilities.PARCELABLE_TRAILER_IDS_KEY, (ArrayList<String>) mMovie.getTrailerIds());
        startActivity(playerTrailerIntent);
    }

    @Override
    public void onClick(Movie movie) {
        Intent recommendedIntent = new Intent(getActivity(), DetailActivity.class);
        recommendedIntent.putExtra(Utilities.PARCELABLE_MOVIE_KEY, movie);
        startActivity(recommendedIntent);
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

    private void fetchJsonRecommendedIds() {
        String url = createRecommendedMoviesUrl(mMovie.getId());
        mVolleyHelper.fetchJsonRecommendedIds(url);
    }

    private void fetchJsonCastMemberDetails() {
        List<Cast> cast = mMovie.getCast();
        for (int i = 0; i < cast.size(); i++) {
            Cast castMember = cast.get(i);
            String url = createCastMemberDetailUrl(castMember.getId());
            mVolleyHelper.fetchJsonCastMember(url, castMember);
        }
    }

    public void fetchJsonRecommendedDetails(Movie movie) {
        List<Movie> recommendedMovies = movie.getRecMovies();
        for (int i = 0; i < recommendedMovies.size(); i++) {
            String url = createUrlWithAppendedResponse(recommendedMovies.get(i).getId(), appendEndpoints());
            mVolleyHelper.fetchJsonRecommendedDetails(url, recommendedMovies.get(i));
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
        Log.v(LOG_TAG, "Movie title: " + mMovie.getTitle());
        Log.v(LOG_TAG, "Movie id: " + mMovie.getId());
    }

    private void setParcelableDetailsIntoViews() {
        String tagline = mMovie.getTagline();
        Director director = mMovie.getDirector();
        Producer producer = mMovie.getProducer();

        if (hasTagline(tagline)) {
            mTagline.setVisibility(View.VISIBLE);
            populateTextView(mMovie.getTagline(), mTagline);
        }

        if (!hasDirector(director)) {
            mMovie.setDirector(new Director());
        }

        if (!hasProducer(producer)) {
            mMovie.setProducer(new Producer());
        }

        populateTextView(mMovie.getOverview(), mSynopsis);
        populateImageViewNoCrossfade(initImageViewProperties(getActivity(), mMovie.getDirector().getProfilePath(),
                mDirectorPicture));
        populateTextView(mMovie.getDirector().getName(), mDirectorName);
        populateImageViewNoCrossfade(initImageViewProperties(getActivity(), mMovie.getProducer().getProfilePath(),
                mProducerPicture));
        populateTextView(mMovie.getProducer().getName(), mProducerName);
        populateStringSetToTextView(languageSet, mLanguages);
        populateTextViewWithSpaces(mMovie.getBudget(), mBudget);
        populateTextViewWithSpaces(mMovie.getRevenue(), mRevenue);
        populateStringListToTextView(mMovie.getProductionCompanies(), mProductionCompanies);

        attachToTrailerAdapter(mMovie);

        if (mMovie.getTrailers() == null) {
            mTrailerRecyclerView.setVisibility(View.GONE);
        } else {
            attachToTrailerAdapter(mMovie);
        }

        attachToRecommendationAdapter(mMovie);
    }

    private boolean hasTagline(String tagline) {
        return tagline != null && !tagline.equals("");
    }

    private boolean hasDirector(Director director) {
        return director != null;
    }

    private boolean hasProducer(Producer producer) {
        return producer != null;
    }
}
