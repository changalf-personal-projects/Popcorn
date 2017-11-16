package com.example.android.popcorn.ui;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;

/**
 * Created by alfredchang on 2017-11-15.
 */

public class ToolbarTitleDisplay {

    public static void displayTitleUponCollapse(AppBarLayout appBarLayout,
                                                CollapsingToolbarLayout collapsingToolbarLayout) {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

            }
        });
    }
}
