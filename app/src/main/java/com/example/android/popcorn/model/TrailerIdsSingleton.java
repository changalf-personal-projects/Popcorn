package com.example.android.popcorn.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredchang on 2017-10-24.
 */

public class TrailerIdsSingleton {

    private static List<String> singletonTrailerIds;

    public TrailerIdsSingleton() {

    }

    public List<String> getSingletonTrailerIds() {
        if (singletonTrailerIds == null) {
            singletonTrailerIds = new ArrayList<String>();
        }
        return singletonTrailerIds;
    }
}
