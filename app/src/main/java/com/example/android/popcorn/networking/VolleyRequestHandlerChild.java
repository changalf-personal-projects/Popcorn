package com.example.android.popcorn.networking;

import com.android.volley.VolleyError;
import com.example.android.popcorn.model.Cast;

/**
 * Created by alfredchang on 2017-12-10.
 */

public interface VolleyRequestHandlerChild {

    void onSuccessCastMember(String response, Cast member);
    void onFail(VolleyError error);
}
