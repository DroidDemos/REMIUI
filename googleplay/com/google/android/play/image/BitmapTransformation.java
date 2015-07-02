package com.google.android.play.image;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface BitmapTransformation {
    void drawFocusedOverlay(Canvas canvas, int i, int i2);

    void drawPressedOverlay(Canvas canvas, int i, int i2);

    int getTransformationInset(int i, int i2);

    Bitmap transform(Bitmap bitmap, int i, int i2);
}
