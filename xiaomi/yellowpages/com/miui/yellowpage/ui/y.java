package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: ExpressInquiryProgressFragment */
class y implements ImageProcessor {
    final /* synthetic */ dm hq;

    y(dm dmVar) {
        this.hq = dmVar;
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(bitmap, new CropOption(this.hq.AW, this.hq.AW, 0, 0));
    }
}
