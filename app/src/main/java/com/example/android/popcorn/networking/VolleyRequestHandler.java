package com.example.android.popcorn.networking;

import com.android.volley.VolleyError;

/**
 * Created by alfredchang on 2017-12-07.
 */

public interface VolleyRequestHandler {

    void onSuccessId(String response);
    void onSuccessDetails(String response, int index);
    void onFail(VolleyError error);

}
