package com.google.android.finsky.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class ReverseContentTransition extends Visibility {
    private void captureContentHeight(TransitionValues transitionValues) {
        View originalView = transitionValues.view;
        View view = originalView;
        ScrollView scroller = null;
        while (view != null) {
            View parent = (View) view.getParent();
            if (parent instanceof ScrollView) {
                scroller = (ScrollView) parent;
                break;
            }
            view = parent;
        }
        if (scroller != null) {
            boolean isOriginalViewVisibleInScroll = false;
            int scrollHeight = 0;
            int scrollTop = scroller.getScrollY();
            int scrollBottom = scrollTop + scroller.getHeight();
            ViewGroup sectionStack = (ViewGroup) view;
            int sectionCount = sectionStack.getChildCount();
            for (int i = 0; i < sectionCount; i++) {
                View section = sectionStack.getChildAt(i);
                if (section.getVisibility() == 0) {
                    boolean isSectionVisibleInScroll = section.getBottom() > scrollTop && section.getTop() < scrollBottom;
                    if (isSectionVisibleInScroll) {
                        scrollHeight += section.getHeight();
                    }
                    if (originalView == section) {
                        isOriginalViewVisibleInScroll = isSectionVisibleInScroll;
                    }
                }
            }
            transitionValues.values.put("translate:y", Integer.valueOf(scrollHeight));
            transitionValues.values.put("participate", Boolean.valueOf(isOriginalViewVisibleInScroll));
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        captureContentHeight(transitionValues);
    }

    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return null;
    }

    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || !startValues.values.containsKey("translate:y") || !startValues.values.containsKey("participate")) {
            return null;
        }
        if (!((Boolean) startValues.values.get("participate")).booleanValue()) {
            return null;
        }
        int translateY = ((Integer) startValues.values.get("translate:y")).intValue();
        Animator translateAnimation = ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f, (float) translateY});
        Animator alpha = ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.8f});
        AnimatorSet result = new AnimatorSet();
        result.playTogether(new Animator[]{translateAnimation, alpha});
        return new NoPauseAnimator(result);
    }
}
