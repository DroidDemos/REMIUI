package com.google.android.finsky.transition;

import android.animation.Animator;
import android.transition.Fade;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class DetailsExitTransition extends Fade {
    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
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
            int scrollTop = scroller.getScrollY();
            int scrollBottom = scrollTop + scroller.getHeight();
            ViewGroup sectionStack = (ViewGroup) view;
            int sectionCount = sectionStack.getChildCount();
            for (int i = 0; i < sectionCount; i++) {
                View section = sectionStack.getChildAt(i);
                if (originalView == section) {
                    isOriginalViewVisibleInScroll = section.getVisibility() == 0 && section.getBottom() > scrollTop && section.getTop() < scrollBottom;
                    transitionValues.values.put("participate", Boolean.valueOf(isOriginalViewVisibleInScroll));
                }
            }
            transitionValues.values.put("participate", Boolean.valueOf(isOriginalViewVisibleInScroll));
        }
    }

    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || !startValues.values.containsKey("participate")) {
            return null;
        }
        if (((Boolean) startValues.values.get("participate")).booleanValue()) {
            return super.onDisappear(sceneRoot, view, startValues, endValues);
        }
        return null;
    }
}
