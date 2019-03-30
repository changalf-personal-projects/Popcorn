package com.example.android.popcorn.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.android.popcorn.R;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.activity.DetailActivity;
import com.example.android.popcorn.fragment.DialogFragment.SortByDialogFragment;
import com.example.android.popcorn.fragment.parsing.LoganDetailsTemplate;
import com.example.android.popcorn.fragment.parsing.LoganIdTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.fragment.saving.DataSaver;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.networking.VolleyHelper;
import com.example.android.popcorn.networking.VolleyRequestHandler;
import com.example.android.popcorn.ui.MovieSorter;
import com.example.android.popcorn.ui.poster_recyclerview.OnMovieClickListener;
import com.example.android.popcorn.ui.poster_recyclerview.OnMovieLongClickListener;
import com.example.android.popcorn.ui.poster_recyclerview.PosterRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.networking.UrlCreator.appendEndpoints;
import static com.example.android.popcorn.networking.UrlCreator.createUrlWithAppendedResponse;

/**
 * Created by alfredchang on 2017-12-06.
 */

public abstract class ParentFragment extends Fragment implements OnMovieClickListener,
        OnMovieLongClickListener {

    private final String LOG_TAG = PopularFragment.class.getSimpleName();
    private final String SORT_CHOICE = "sort choice";
    private final int DEFAULT_SORT_CHOICE = 0;
    private final int DIALOG_FRAGMENT = 1;
    private final int LAYOUT_COL_SPAN = 2;

    protected final int SORT_DEFAULT = 0;
    protected final int SORT_TOP_RATED = 1;
    protected final int SORT_NAME_ALPHABETICAL = 2;
    protected final int SORT_LONGEST_RUNTIME = 3;
    protected final int SORT_NEWEST_RELEASE = 4;
    protected final int SORT_HIGHEST_REVENUE = 5;
    protected final int SORT_HIGHEST_PROFIT = 6;

    private int tabIndex = 0;

    private VolleyRequestHandler mVolleyReqHandler;
    private VolleyHelper mVolleyHelper;
    private PosterRecyclerViewAdapter mRecyclerAdapter;
    private MovieSorter mMovieSorter;
    private List<Movie> mListOfMovies;
    private List<Integer> mListOfRefreshColours = new ArrayList<>();
    private DataSaver mDataSaver;
    private View mRootView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.posters_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.pull_refresh_layout)
    SwipeRefreshLayout mPullRefreshLayout;
    @BindView(R.id.frame_layout)
    FrameLayout mFrameLayout;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, mRootView);

        getRefreshColours();
        onPullScreenDown();

        mListOfMovies = getSingletonList();
        mDataSaver = new DataSaver(this, mListOfMovies);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), LAYOUT_COL_SPAN);
        mRecyclerView.setLayoutManager(layoutManager);

        initVolleyHandler();
        initVolleyHelper();
        fetchJsonId();

        tabIndex = mTabLayout.getSelectedTabPosition();

        return mRootView;
    }

    private void initVolleyHelper() {
        mVolleyHelper = new VolleyHelper(getActivity(), mVolleyReqHandler);
    }

    // Source: https://stackoverflow.com/questions/35628142/how-to-make-separate-class-for-volley-
    // library-and-call-all-method-of-volley-from.
    private void initVolleyHandler() {
        mVolleyReqHandler = new VolleyRequestHandler() {

            @Override
            public void onSuccessId(String response) {
                LoganIdTemplate loganId = MovieParser.parseJsonIdData(response);
                mDataSaver.saveMovieId(loganId);
            }

            @Override
            public void onSuccessDetails(String response, Movie movie) {
                LoganDetailsTemplate loganDetails = MovieParser.parseJsonDetailsData(response);
                mDataSaver.saveMovieDetails(movie, loganDetails);
                // Can't call mDataSaver.saveAllData(...) because attachAdapter needs to come in between.
                attachAdapter();
                mDataSaver.saveMovieCast(movie, loganDetails);
                mDataSaver.saveMovieCrew(movie, loganDetails);
                mDataSaver.saveMovieTrailers(movie, loganDetails);
                mDataSaver.saveMovieReviews(movie, loganDetails);
                mDataSaver.saveRecMovieId(movie, loganDetails);
            }

            @Override
            public void onSuccessRecommendedDetails(String response, Movie movie) {
                LoganDetailsTemplate loganDetails = MovieParser.parseJsonDetailsData(response);
                mDataSaver.saveMovieDetails(movie, loganDetails);
                mDataSaver.saveMovieCast(movie, loganDetails);
                mDataSaver.saveMovieCrew(movie, loganDetails);
                mDataSaver.saveMovieTrailers(movie, loganDetails);

                // Screen blacks out.
//                mDataSaver.saveMovieReviews(movie, loganDetails);
            }

            @Override
            public void onFail(VolleyError error) {
                Log.e(LOG_TAG, "initVolleyHandler() error: " + error);
            }
        };
    }

    private void fetchJsonId() {
        String url = createUrl();
        mVolleyHelper.fetchJsonId(url);
    }

    public void fetchJsonDetails() {
        for (int i = 0; i < mListOfMovies.size(); i++) {
            String url = createUrlWithAppendedResponse(mListOfMovies.get(i).getId(), appendEndpoints());
            Movie movie = mListOfMovies.get(i);
            mVolleyHelper.fetchJsonDetails(url, movie);
        }
    }

    public void fetchJsonRecommendedDetails(Movie movie) {
        for (int i = 0; i < movie.getRecMovies().size(); i++) {
            Movie recMovie = movie.getRecMovies().get(i);
            String url = createUrlWithAppendedResponse(recMovie.getId(), appendEndpoints());
            mVolleyHelper.fetchJsonRecommendedDetails(url, recMovie);
        }
    }

    private void attachAdapter() {
        mRecyclerAdapter = initRecyclerViewAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(Movie movie) {
        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
        detailIntent.putExtra(Utilities.PARCELABLE_MOVIE_KEY, movie);
        detailIntent.putExtra(Utilities.FROM, Utilities.PARENT);
        startActivity(detailIntent);
    }

    // Source: https://android--code.blogspot.com/2016/01/android-popup-window-example.html.
    @Override
    public void onLongClick(Movie movie) {
        LayoutInflater inflater = (LayoutInflater) mRootView.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.on_long_click_movie_details, null);
        popupView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.translation_top_to_bottom));

        View movieRatingPopup = popupView.findViewById(R.id.movie_rating_popup);
        View movieOverviewPopup = popupView.findViewById(R.id.movie_overview_popup);
        ((TextView) movieRatingPopup).setText(movie.getRating());
        ((TextView) movieOverviewPopup).setText(movie.getOverview());

        final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View exitButton = popupView.findViewById(R.id.movie_rating_exit_icon);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        if (VERSION.SDK_INT >= 21) {
            popupWindow.setElevation(5.0f);
        }

        popupWindow.showAtLocation(mFrameLayout, Gravity.CENTER, 0, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_sort:
                displayDialog();
                break;

            case R.id.action_settings:

            case R.id.action_feedback:

            case R.id.action_about:

            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            sortMovies(data.getIntExtra(SORT_CHOICE, DEFAULT_SORT_CHOICE));
        } else {
            Log.d(LOG_TAG, "Print result code: " + resultCode);
        }
    }

    private void initializeMovieSorter() {
        if (mMovieSorter == null) {
            mMovieSorter = new MovieSorter(mRecyclerAdapter);
        }
    }

    private void resetDefaultOrder() {
        configureWheelColours();
        refreshScreen();
    }

    private void sortMovies(int choice) {
        initializeMovieSorter();

        switch (choice) {
            case SORT_DEFAULT:
                setSortTitle(R.string.toolbar_sort_default);
                resetDefaultOrder();
                break;

            case SORT_TOP_RATED:
                setSortTitle(R.string.toolbar_sort_top);
                mMovieSorter.sortByRating();
                break;

            case SORT_NAME_ALPHABETICAL:
                setSortTitle(R.string.toolbar_sort_name);
                mMovieSorter.sortByName();
                break;

            case SORT_LONGEST_RUNTIME:
                setSortTitle(R.string.toolbar_sort_length);
                mMovieSorter.sortByRuntime();
                break;

            case SORT_NEWEST_RELEASE:
                setSortTitle(R.string.toolbar_sort_newest);
                mMovieSorter.sortByRelease();
                break;

            case SORT_HIGHEST_REVENUE:
                setSortTitle(R.string.toolbar_sort_revenue);
                mMovieSorter.sortByRevenue();
                break;

            case SORT_HIGHEST_PROFIT:
                setSortTitle(R.string.toolbar_sort_profit);
                mMovieSorter.sortByProfit();
                break;

            default:
                setSortTitle(R.string.toolbar_sort_default);
                resetDefaultOrder();
        }
    }

    private void setSortTitle(int category) {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView sortTitle = (TextView) toolbar.findViewById(R.id.sort_category);
        sortTitle.setText(category);
    }

    protected void displayDialog() {
        DialogFragment dialogFragment = SortByDialogFragment.newInstance(123);
        dialogFragment.setTargetFragment(this, DIALOG_FRAGMENT);
        dialogFragment.show(getFragmentManager().beginTransaction(), "dialog");
    }

    private void onPullScreenDown() {
        configureWheelColours();
        mPullRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshScreen();
            }
        });
    }

    private void refreshScreen() {
        mRecyclerAdapter.clearData();
        fetchJsonId();
        mRecyclerAdapter.renewData(mListOfMovies);
        mPullRefreshLayout.setRefreshing(false);
    }

    private void getRefreshColours() {
        mListOfRefreshColours.add(android.R.color.holo_red_dark);
        mListOfRefreshColours.add(android.R.color.holo_green_dark);
        mListOfRefreshColours.add(android.R.color.holo_blue_dark);
        mListOfRefreshColours.add(android.R.color.holo_orange_dark);
    }

    private void configureWheelColours() {
        for (int colour : mListOfRefreshColours) {
            mPullRefreshLayout.setColorSchemeResources(colour);
        }
    }

    abstract List<Movie> getSingletonList();

    abstract String createUrl();

    abstract PosterRecyclerViewAdapter initRecyclerViewAdapter();

}
