package com.google.android.play.onboard;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.play.utils.collections.Lists;
import java.util.Collection;
import java.util.List;

public class OnboardUtils {

    public interface Predicate<T> {
        boolean apply(T t);
    }

    public static Collection<View> getAllDescendants(ViewGroup root, Predicate<View> optPredicate) {
        List<View> matches = Lists.newArrayList();
        List<View> unvisited = Lists.newArrayList();
        unvisited.add(root);
        while (!unvisited.isEmpty()) {
            View child = (View) unvisited.remove(0);
            if (optPredicate == null || optPredicate.apply(child)) {
                matches.add(child);
            }
            if (child instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) child;
                int count = group.getChildCount();
                for (int i = 0; i < count; i++) {
                    unvisited.add(group.getChildAt(i));
                }
            }
        }
        return matches;
    }
}
