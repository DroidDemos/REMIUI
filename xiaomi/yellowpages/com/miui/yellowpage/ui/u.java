package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: BannerView */
class u implements ImageProcessor {
    final /* synthetic */ BannerView fI;

    u(BannerView bannerView) {
        this.fI = bannerView;
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(BitmapFactory.scaleBitmap(bitmap, BannerView.Io, BannerView.In), new CropOption(BannerView.Ip, BannerView.Ip, 0, 0));
    }
}
