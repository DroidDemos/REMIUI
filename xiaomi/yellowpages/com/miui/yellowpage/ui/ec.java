package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: CouponListItem */
class ec implements ImageProcessor {
    private ec() {
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(bitmap, new CropOption(CouponListItem.kt, CouponListItem.kt, 0, 0));
    }
}
