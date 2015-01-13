package net.trippedout.android.animation;

import android.view.animation.Animation;

/**
 * Easy overrides so that code stays clean, like SimpleGestureListener etc
 */
public class SimpleAnimationListener implements Animation.AnimationListener {
    @Override
    public void onAnimationStart(Animation animation) { }
    @Override
    public void onAnimationEnd(Animation animation) { }
    @Override
    public void onAnimationRepeat(Animation animation) { }
}
