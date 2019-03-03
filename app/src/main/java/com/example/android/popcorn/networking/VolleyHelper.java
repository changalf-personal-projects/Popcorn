package com.example.android.popcorn.networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.popcorn.model.Movie;

/**
 * Created by alfredchang on 2017-12-07.
 */

public class VolleyHelper {

    private final String LOG_TAG = VolleyHelper.class.getSimpleName();

    private final int TIMEOUT_DURATION = 5000;
    private final int TIMEOUT_RETRIES = 3;
    private final int TIMEOUT_BACKOFF = 0;

    private VolleyRequestHandler mVolleyReqHandler;
    private RequestQueue mRequestQueue;
    private Context mContext;

    public VolleyHelper(Context context, VolleyRequestHandler volleyReqHandler) {
        mContext = context;
        mVolleyReqHandler = volleyReqHandler;
        mRequestQueue = Volley.newRequestQueue(context);
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

//        stringRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return TIMEOUT_DURATION;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return TIMEOUT_RETRIES;
//            }
//
//            @Override
//            public void retry(VolleyError error) {
//                Toast.makeText(mContext, "Tryout", Toast.LENGTH_SHORT).show();
//            }
//        });
//        mRequestQueue.add(stringRequest);

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

//        stringRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return TIMEOUT_DURATION;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return TIMEOUT_RETRIES;
//            }
//
//            @Override
//            public void retry(VolleyError error) {
//                Toast.makeText(mContext, "Tryout", Toast.LENGTH_SHORT).show();
//            }
//        });
//        mRequestQueue.add(stringRequest);

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

//        stringRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return TIMEOUT_DURATION;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return TIMEOUT_RETRIES;
//            }
//
//            @Override
//            public void retry(VolleyError error) {
//                Toast.makeText(mContext, "Tryout", Toast.LENGTH_SHORT).show();
//            }
//        });
//        mRequestQueue.add(stringRequest);

        RequestQueueSingleton.getSingletonInstance(mContext).addToRequestQueue(stringRequest);
    }
}
