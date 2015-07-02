package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: YellowPageTitleListItem */
final class ci implements ImageProcessor {
    ci() {
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(BitmapFactory.scaleBitmap(bitmap, YellowPageTitleListItem.bP, YellowPageTitleListItem.bP), new CropOption(YellowPageTitleListItem.kt, YellowPageTitleListItem.kt, 0, 0));
    }
}
