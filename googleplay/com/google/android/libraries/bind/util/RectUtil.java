package com.google.android.libraries.bind.util;

import android.graphics.Rect;
import android.graphics.RectF;

public class RectUtil {
    public static Rect round(RectF inRect, Rect outRect) {
        outRect.left = Math.round(inRect.left);
        outRect.top = Math.round(inRect.top);
        outRect.right = Math.round(inRect.right);
        outRect.bottom = Math.round(inRect.bottom);
        return outRect;
    }
}
