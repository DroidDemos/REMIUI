package com.google.android.libraries.bind.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public interface BindingViewGroup extends DataView {

    public enum BlendMode {
        FADE_SOURCE_ONLY,
        SHOW_SOURCE_HIDE_DESTINATION
    }

    void blendCapturedBitmap(Bitmap bitmap, Rect rect, long j, BlendMode blendMode);

    boolean captureToBitmap(Bitmap bitmap, float f, float f2);

    boolean isOwnedByParent();

    void prepareForRecycling();

    void setMeasuredDimensionProxy(int i, int i2);

    void setOwnedByParent(boolean z);

    boolean startEditingIfPossible();

    void superDrawProxy(Canvas canvas);

    void updateBoundDataProxy(Data data);
}
