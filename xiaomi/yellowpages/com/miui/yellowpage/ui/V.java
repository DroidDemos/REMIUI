package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: SendExpressConfirmFragment */
class V implements ImageProcessor {
    final /* synthetic */ U jp;

    V(U u) {
        this.jp = u;
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(BitmapFactory.scaleBitmap(bitmap, U.jj, U.jj), new CropOption(U.jj, U.jj, 0, 0));
    }
}
