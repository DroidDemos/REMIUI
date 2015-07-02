package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: ExpressInquiryFragment */
class cu implements ImageProcessor {
    final /* synthetic */ bS DE;

    cu(bS bSVar) {
        this.DE = bSVar;
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(bitmap, new CropOption(this.DE.AW, this.DE.AW, 0, 0));
    }
}
