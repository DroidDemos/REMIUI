package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;
import com.google.android.play.layout.PlayTextView;

public class DecoratedTextView extends PlayTextView implements BitmapLoadedHandler {
    public DecoratedTextView(Context context) {
        super(context);
    }

    public DecoratedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void loadDecoration(BitmapLoader bitmapLoader, Image image, int imageDimension) {
        int requestSize = image.supportsFifeUrlOptions ? imageDimension : 0;
        Bitmap loadedBitmap = bitmapLoader.get(image.imageUrl, requestSize, requestSize, this).getBitmap();
        if (loadedBitmap != null) {
            setDecorationBitmap(loadedBitmap);
        }
    }

    public void onResponse(BitmapContainer bitmapContainer) {
        Bitmap response = bitmapContainer.getBitmap();
        if (response != null) {
            setDecorationBitmap(response);
        }
    }
}
