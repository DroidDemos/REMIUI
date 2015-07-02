package com.google.android.finsky.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

public class ReverseHeroTransition extends Visibility {
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return null;
    }

    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        Animator translateY = ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f, (float) (-view.getHeight())});
        Animator alpha = ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.8f});
        AnimatorSet result = new AnimatorSet();
        result.playTogether(new Animator[]{alpha, translateY});
        return new NoPauseAnimator(result);
    }
}
