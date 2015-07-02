package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: YellowPageGridModuleItem */
final class bO implements ImageProcessor {
    bO() {
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(BitmapFactory.scaleBitmap(bitmap, YellowPageGridModuleItem.bP, YellowPageGridModuleItem.bP), new CropOption(YellowPageGridModuleItem.bP / 2, YellowPageGridModuleItem.bP / 2, 0, 0));
    }
}
