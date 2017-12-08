package com.example.android.popcorn.networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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

    public void getJsonResponse(String url) {
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

    public void getJsonResponse(String url, final int index) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (mVolleyReqHandler != null) {
                            mVolleyReqHandler.onSuccessDetails(response, index);
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
