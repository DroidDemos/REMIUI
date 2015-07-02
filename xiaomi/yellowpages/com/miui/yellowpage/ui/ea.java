package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: ExpressCompanyListItem */
final class ea implements ImageProcessor {
    ea() {
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(bitmap, new CropOption(ExpressCompanyListItem.gO, ExpressCompanyListItem.gO, 0, 0));
    }
}
