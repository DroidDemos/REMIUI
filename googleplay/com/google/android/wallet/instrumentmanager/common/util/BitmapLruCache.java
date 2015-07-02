package com.google.android.wallet.instrumentmanager.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageCache {
    public BitmapLruCache(Context context, int maxSizeDp) {
        super(getDefaultLruCacheSizeKb(context, maxSizeDp));
    }

    protected int sizeOf(String key, Bitmap value) {
        if (VERSION.SDK_INT >= 19) {
            return value.getAllocationByteCount() / 1024;
        }
        return (value.getRowBytes() * value.getHeight()) / 1024;
    }

    public Bitmap getBitmap(String url) {
        return (Bitmap) get(url);
    }

    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

    private static int getDefaultLruCacheSizeKb(Context context, int maxSizeDp) {
        int maxMemoryKb = ((ActivityManager) context.getSystemService("activity")).getMemoryClass() * 1024;
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
        return Math.min(maxMemoryKb / 8, (((int) (((double) maxSizeDp) * Math.pow(((double) metrics.densityDpi) / 160.0d, 2.0d))) * 4) / 1024);
    }
}
