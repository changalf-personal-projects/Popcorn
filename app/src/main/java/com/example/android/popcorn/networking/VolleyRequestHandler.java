package com.example.android.popcorn.networking;

import com.android.volley.VolleyError;

/**
 * Created by alfredchang on 2017-12-07.
 */

public interface VolleyRequestHandler {

    public void onSuccess(String response);
    public void onFail(VolleyError error);

}
