package com.google.android.libraries.bind.widget;

import android.graphics.Rect;
import android.view.View;

public class WidgetUtil {
    private static int[] tempLocation;
    private static Rect tempRect;
    private static Rect tempRect2;

    static {
        tempRect = new Rect();
        tempRect2 = new Rect();
        tempLocation = new int[2];
    }

    public static boolean isVisibleOnScreen(View view) {
        return view.getGlobalVisibleRect(tempRect);
    }
}
