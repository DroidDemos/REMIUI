package com.google.android.finsky.transition;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.util.ArrayMap;
import java.util.ArrayList;

public class NoPauseAnimator extends Animator {
    private final Animator mAnimator;
    private final ArrayMap<AnimatorListener, AnimatorListener> mListeners;

    private static class AnimatorListenerWrapper implements AnimatorListener {
        private final Animator mAnimator;
        private final AnimatorListener mListener;

        public AnimatorListenerWrapper(Animator animator, AnimatorListener listener) {
            this.mAnimator = animator;
            this.mListener = listener;
        }

        public void onAnimationStart(Animator animator) {
            this.mListener.onAnimationStart(this.mAnimator);
        }

        public void onAnimationEnd(Animator animator) {
            this.mListener.onAnimationEnd(this.mAnimator);
        }

        public void onAnimationCancel(Animator animator) {
            this.mListener.onAnimationCancel(this.mAnimator);
        }

        public void onAnimationRepeat(Animator animator) {
            this.mListener.onAnimationRepeat(this.mAnimator);
        }
    }

    public NoPauseAnimator(Animator animator) {
        this.mListeners = new ArrayMap();
        this.mAnimator = animator;
    }

    public void addListener(AnimatorListener listener) {
        AnimatorListener wrapper = new AnimatorListenerWrapper(this, listener);
        if (!this.mListeners.containsKey(listener)) {
            this.mListeners.put(listener, wrapper);
            this.mAnimator.addListener(wrapper);
        }
    }

    public void cancel() {
        this.mAnimator.cancel();
    }

    public void end() {
        this.mAnimator.end();
    }

    public long getDuration() {
        return this.mAnimator.getDuration();
    }

    public TimeInterpolator getInterpolator() {
        return this.mAnimator.getInterpolator();
    }

    public ArrayList<AnimatorListener> getListeners() {
        return new ArrayList(this.mListeners.keySet());
    }

    public long getStartDelay() {
        return this.mAnimator.getStartDelay();
    }

    public boolean isPaused() {
        return this.mAnimator.isPaused();
    }

    public boolean isRunning() {
        return this.mAnimator.isRunning();
    }

    public boolean isStarted() {
        return this.mAnimator.isStarted();
    }

    public void removeAllListeners() {
        this.mListeners.clear();
        this.mAnimator.removeAllListeners();
    }

    public void removeListener(AnimatorListener listener) {
        AnimatorListener wrapper = (AnimatorListener) this.mListeners.get(listener);
        if (wrapper != null) {
            this.mListeners.remove(listener);
            this.mAnimator.removeListener(wrapper);
        }
    }

    public Animator setDuration(long durationMS) {
        this.mAnimator.setDuration(durationMS);
        return this;
    }

    public void setInterpolator(TimeInterpolator timeInterpolator) {
        this.mAnimator.setInterpolator(timeInterpolator);
    }

    public void setStartDelay(long delayMS) {
        this.mAnimator.setStartDelay(delayMS);
    }

    public void setTarget(Object target) {
        this.mAnimator.setTarget(target);
    }

    public void setupEndValues() {
        this.mAnimator.setupEndValues();
    }

    public void setupStartValues() {
        this.mAnimator.setupStartValues();
    }

    public void start() {
        this.mAnimator.start();
    }
}
