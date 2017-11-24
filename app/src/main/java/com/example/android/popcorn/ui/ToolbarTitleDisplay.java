package com.example.android.popcorn.ui;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * ImageView for movie poster when toolbar is collapsed and profile pictures of cast members.
 */

public class ToolbarTitleDisplay extends CoordinatorLayout.Behavior<CircleImageView> {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, CircleImageView child,
                                   View dependency) {
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, CircleImageView avatar,
                                          View dependency) {
        return true;
    }

    private void modifyAvatarDependingOnDependencyState(CircleImageView avatar, View dependency) {

    }

}
