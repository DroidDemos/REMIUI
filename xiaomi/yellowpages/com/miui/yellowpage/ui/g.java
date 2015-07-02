package com.miui.yellowpage.ui;

import android.graphics.Bitmap;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: ShopRecommendationListItem */
final class g implements ImageProcessor {
    g() {
    }

    public Bitmap processImage(Bitmap bitmap) {
        return BitmapFactory.cropBitmap(BitmapFactory.scaleBitmap(bitmap, ShopRecommendationListItem.bP, ShopRecommendationListItem.bP), new CropOption(ShopRecommendationListItem.kt, ShopRecommendationListItem.kt, 0, 0));
    }
}
