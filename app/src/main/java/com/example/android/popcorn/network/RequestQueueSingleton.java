package com.example.android.popcorn.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by alfredchang on 2017-09-21.
 */

public class RequestQueueSingleton {
    private static RequestQueueSingleton mSingletonQueue;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    public RequestQueueSingleton(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    // "Synchronized" makes this method thread-safe.
    public static synchronized RequestQueueSingleton getSingletonInstance(Context context) {
        if (mSingletonQueue == null) {
            mSingletonQueue = new RequestQueueSingleton(context);
        }
        return mSingletonQueue;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
