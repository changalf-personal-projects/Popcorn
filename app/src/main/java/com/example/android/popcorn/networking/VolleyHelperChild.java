package com.example.android.popcorn.networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.popcorn.model.Cast;

/**
 * Created by alfredchang on 2017-12-10.
 */

public class VolleyHelperChild {

    private final String LOG_TAG = VolleyHelperChild.class.getSimpleName();

    private VolleyRequestHandlerChild mVolleyReqHandlerChild = null;
    private Context mContext;

    public VolleyHelperChild(Context context, VolleyRequestHandlerChild volleyReqHandler) {
        mContext = context;
        mVolleyReqHandlerChild = volleyReqHandler;
    }

    public void fetchJsonCastMember(String url, final Cast member) {
        Log.v(LOG_TAG, "Is null? " + mVolleyReqHandlerChild);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.v(LOG_TAG, "Is null? " + mVolleyReqHandlerChild);
                        if (mVolleyReqHandlerChild != null) {
                            Log.v(LOG_TAG, "Cast member response: " + response);
                            mVolleyReqHandlerChild.onSuccessCastMember(response, member);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mVolleyReqHandlerChild != null) {
                    mVolleyReqHandlerChild.onFail(error);
                }
            }
        });
        RequestQueueSingleton.getSingletonInstance(mContext).addToRequestQueue(stringRequest);
    }
}
