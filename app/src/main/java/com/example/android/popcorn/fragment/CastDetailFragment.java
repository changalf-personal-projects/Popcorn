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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.R;
import com.example.android.popcorn.SingleCastMemberDetailActivity;
import com.example.android.popcorn.Utilities;
import com.example.android.popcorn.fragment.parsing.LoganCastMemberDetailTemplate;
import com.example.android.popcorn.fragment.parsing.MovieParser;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.networking.RequestQueueSingleton;
import com.example.android.popcorn.ui.cast_recyclerview.CastRecyclerViewAdapter;
import com.example.android.popcorn.ui.cast_recyclerview.OnCastMemberClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popcorn.networking.UrlCreator.createUrl;

/**
 * Created by alfredchang on 2017-10-06.
 */

public class CastDetailFragment extends Fragment implements OnCastMemberClickListener {

    private final String LOG_TAG = CastDetailFragment.class.getSimpleName();

    private CastRecyclerViewAdapter mRecyclerAdapter;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_cast_detail_main, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        List<Cast> castMembers = getParcelableCastDetails();
        attachToAdapter(castMembers);

        return rootView;
    }

    private List<Cast> getParcelableCastDetails() {
        Intent castDetailIntent = getActivity().getIntent();
        List<Cast> castMembers = castDetailIntent.getParcelableArrayListExtra(Utilities.PARCELABLE_CAST_KEY);
        return castMembers;
    }

    private void attachToAdapter(List<Cast> castMembers) {
        mRecyclerAdapter = new CastRecyclerViewAdapter(getActivity(), castMembers, this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void onClick(Cast castMember) {
        Intent singleCastMemberDetailsIntent = new Intent(getActivity(), SingleCastMemberDetailActivity.class);
        fetchJsonMemberDetails(castMember);
        singleCastMemberDetailsIntent.putExtra(Utilities.PARCELABLE_CAST_MEMBER_KEY, castMember);
        startActivity(singleCastMemberDetailsIntent);
    }

    private void fetchJsonMemberDetails(Cast castMember) {
        String url = createUrl(castMember.getId());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LoganCastMemberDetailTemplate castMemberLogan = MovieParser.parseJsonCastMemberData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Response error (fetchJsonMemberDetails): " + error);
            }
        });

        RequestQueueSingleton.getSingletonInstance(getActivity()).addToRequestQueue(stringRequest);
    }
}
