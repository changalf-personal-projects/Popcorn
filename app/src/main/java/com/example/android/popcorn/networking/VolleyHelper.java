package com.example.android.popcorn.networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.model.Movie;

/**
 * Created by alfredchang on 2017-12-07.
 */

public class VolleyHelper {

    private final String LOG_TAG = VolleyHelper.class.getSimpleName();

    VolleyRequestHandler mVolleyReqHandler = null;
    Context mContext;

    public VolleyHelper(Context context, VolleyRequestHandler volleyReqHandler) {
        mContext = context;
        mVolleyReqHandler = volleyReqHandler;
    }

    public void fetchJsonId(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (mVolleyReqHandler != null) {
                            mVolleyReqHandler.onSuccessId(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mVolleyReqHandler != null) {
                    mVolleyReqHandler.onFail(error);
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
                        if (mVolleyReqHandler != null) {
                            mVolleyReqHandler.onSuccessDetails(response, movie);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mVolleyReqHandler != null) {
                    mVolleyReqHandler.onFail(error);
                }
            }
        });

        RequestQueueSingleton.getSingletonInstance(mContext).addToRequestQueue(stringRequest);
    }

    public void fetchJsonRecommendedIds(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (mVolleyReqHandler != null) {
                            Log.v(LOG_TAG, "Ids response: " + response);
                            mVolleyReqHandler.onSuccessRecommendedIds(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mVolleyReqHandler != null) {
                    mVolleyReqHandler.onFail(error);
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
                        if (mVolleyReqHandler != null) {
                            mVolleyReqHandler.onSuccessRecommendedDetails(response, movie);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mVolleyReqHandler != null) {
                    mVolleyReqHandler.onFail(error);
                }
            }
        });

        RequestQueueSingleton.getSingletonInstance(mContext).addToRequestQueue(stringRequest);
    }

    public void fetchJsonCastMember(String url, final Cast member) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (mVolleyReqHandler != null) {
                            mVolleyReqHandler.onSuccessCastMember(response, member);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mVolleyReqHandler != null) {
                    mVolleyReqHandler.onFail(error);
                }
            }
        });
        RequestQueueSingleton.getSingletonInstance(mContext).addToRequestQueue(stringRequest);
    }
}
