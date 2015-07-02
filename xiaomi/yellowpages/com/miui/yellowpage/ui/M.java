package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: YellowPageListModuleItem */
class M implements ImageProcessor {
    final /* synthetic */ YellowPageListModuleItem iv;

    private M(YellowPageListModuleItem yellowPageListModuleItem) {
        this.iv = yellowPageListModuleItem;
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(bitmap, new CropOption(YellowPageListModuleItem.fQ, YellowPageListModuleItem.fQ, 0, 0));
    }
}
