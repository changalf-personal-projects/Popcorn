package com.example.android.popcorn.ui;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Customizinig the animation states for a circular ImageView.  The view will be used when toolbar
 * collapses.
 */

public class ToolbarTitleDisplay extends CoordinatorLayout.Behavior<CircleImageView> {

    private final int X_POS = 20;
    private final int Y_POS = 20;

    @Override
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, CircleImageView child,
                                   View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, CircleImageView avatar,
                                          View dependency) {
        modifyAvatarDependingOnDependencyState(avatar, dependency);
        return true;
    }

    private void modifyAvatarDependingOnDependencyState(CircleImageView avatar, View dependency) {
        avatar.setX(X_POS);
        avatar.setY(Y_POS);
    }

}
