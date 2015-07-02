package com.google.android.finsky.utils;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import com.android.vending.R;

public final class PlayAnimationUtils {

    public static class AnimationListenerAdapter implements AnimationListener {
        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public static class ScrollAnimation extends TranslateAnimation {
        private boolean mStartedScrolling;

        public ScrollAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
            super(fromXDelta, toXDelta, fromYDelta, toYDelta);
        }

        public void setStartedScrolling() {
            this.mStartedScrolling = true;
        }

        public boolean hasStartedScrolling() {
            return this.mStartedScrolling;
        }
    }

    public static void animateFadeIn(View view, long durationMs) {
        if (VERSION.SDK_INT >= 11) {
            getFadeAnimator(view, 0.0f, 1.0f, 0, durationMs, null).start();
        } else {
            view.startAnimation(getFadeInAnimation(view.getContext(), durationMs, null));
        }
    }

    public static Animation getFadeInAnimation(Context context, long durationMs, AnimationListener listener) {
        return getFadeInAnimation(context, 0, durationMs, listener);
    }

    public static Animation getFadeInAnimation(Context context, long initialDelayMs, long durationMs, AnimationListener listener) {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.play_fade_in);
        fadeInAnimation.setStartOffset(initialDelayMs);
        fadeInAnimation.setDuration(durationMs);
        if (listener != null) {
            fadeInAnimation.setAnimationListener(listener);
        }
        return fadeInAnimation;
    }

    public static Animation getFadeOutAnimation(Context context, long durationMs, AnimationListener listener) {
        return getFadeOutAnimation(context, 0, durationMs, listener);
    }

    public static Animation getFadeOutAnimation(Context context, long initialDelayMs, long durationMs, AnimationListener listener) {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        fadeOutAnimation.setStartOffset(initialDelayMs);
        fadeOutAnimation.setDuration(durationMs);
        if (listener != null) {
            fadeOutAnimation.setAnimationListener(listener);
        }
        return fadeOutAnimation;
    }

    public static ScrollAnimation getVerticalScrollByAnimation(ScrollView scrollView, long initialDelayMs, long durationMs, int verticalScrollAmount, AnimationListener listener) {
        final int i = verticalScrollAmount;
        final ScrollView scrollView2 = scrollView;
        ScrollAnimation scrollAnimation = new ScrollAnimation(0.0f, 0.0f, 0.0f, (float) verticalScrollAmount) {
            int currScrolledAmount;

            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime != 0.0f) {
                    setStartedScrolling();
                    int newScrolledAmount = (int) (((float) i) * interpolatedTime);
                    scrollView2.scrollBy(0, newScrolledAmount - this.currScrolledAmount);
                    this.currScrolledAmount = newScrolledAmount;
                }
            }
        };
        scrollAnimation.setStartOffset(initialDelayMs);
        scrollAnimation.setDuration(durationMs);
        scrollAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        if (listener != null) {
            scrollAnimation.setAnimationListener(listener);
        }
        return scrollAnimation;
    }

    public static Animator getFadeAnimator(View view, float alphaFrom, float alphaTo, int initialDelayMs, long durationMs, AnimatorListener listener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", new float[]{alphaFrom, alphaTo});
        animator.setStartDelay((long) initialDelayMs);
        animator.setDuration(durationMs);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (listener != null) {
            animator.addListener(listener);
        }
        return animator;
    }
}
