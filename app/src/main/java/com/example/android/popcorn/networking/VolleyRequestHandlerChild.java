package com.example.android.popcorn.networking;

import com.android.volley.VolleyError;
import com.example.android.popcorn.model.Cast;
import com.example.android.popcorn.model.Movie;

/**
 * Created by alfredchang on 2017-12-10.
 */

public interface VolleyRequestHandlerChild {

    void onSuccessCastMember(String response, Cast member);
    void onSuccessRecommendedIds(String response);
    void onSuccessRecommendedDetails(String response, Movie movie);
    void onFail(VolleyError error);
}
