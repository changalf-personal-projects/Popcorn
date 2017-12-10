package com.example.android.popcorn.networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.model.Movie;

/**
 * Created by alfredchang on 2017-12-07.
 */

public class VolleyHelperParent {

    private final String LOG_TAG = VolleyHelperParent.class.getSimpleName();

    VolleyRequestHandlerParent mVolleyReqHandlerParent = null;
    Context mContext;

    public VolleyHelperParent(Context context, VolleyRequestHandlerParent volleyReqHandler) {
        mContext = context;
        mVolleyReqHandlerParent = volleyReqHandler;
    }

    public void fetchJsonId(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (mVolleyReqHandlerParent != null) {
                            mVolleyReqHandlerParent.onSuccessId(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mVolleyReqHandlerParent != null) {
                    mVolleyReqHandlerParent.onFail(error);
                }
            }
        });

        RequestQueueSingleton.getSingletonInstance(mContext).addToRequestQueue(stringRequest);
    }

    public void fetchJsonDetails(String url, final Movie movie) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (mVolleyReqHandlerParent != null) {
                            mVolleyReqHandlerParent.onSuccessDetails(response, movie);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mVolleyReqHandlerParent != null) {
                    mVolleyReqHandlerParent.onFail(error);
                }
            }
        });

        RequestQueueSingleton.getSingletonInstance(mContext).addToRequestQueue(stringRequest);
    }

    public void fetchJsonRecommendedDetails(String url, final Movie movie) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (mVolleyReqHandlerParent != null) {
                            mVolleyReqHandlerParent.onSuccessRecommendedDetails(response, movie);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mVolleyReqHandlerParent != null) {
                    mVolleyReqHandlerParent.onFail(error);
                }
            }
        });

        RequestQueueSingleton.getSingletonInstance(mContext).addToRequestQueue(stringRequest);
    }
}
