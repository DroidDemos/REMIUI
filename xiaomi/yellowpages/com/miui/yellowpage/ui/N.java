package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: SearchResultItem */
class N implements ImageProcessor {
    final /* synthetic */ du iG;

    N(du duVar) {
        this.iG = duVar;
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(BitmapFactory.scaleBitmap(bitmap, du.bP, du.bP), new CropOption(du.Ip, du.Ip, 0, 0));
    }
}
