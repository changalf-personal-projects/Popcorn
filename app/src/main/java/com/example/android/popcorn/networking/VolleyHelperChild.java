package com.example.android.popcorn.networking;

import android.content.Context;

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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (mVolleyReqHandlerChild != null) {
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
