package com.google.android.gms.internal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public final class jq {
    public static Bitmap a(Bitmap bitmap) {
        int i = 0;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width >= height) {
            i = (width / 2) - (height / 2);
            width = 0;
        } else {
            int i2 = width;
            width = (height / 2) - (width / 2);
            height = i2;
        }
        Bitmap createBitmap = Bitmap.createBitmap(height, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        paint.setColor(-16777216);
        canvas.drawCircle((float) (height / 2), (float) (height / 2), (float) (height / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (float) i, (float) width, paint);
        return createBitmap;
    }

    private static Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Drawable a(Resources resources, Drawable drawable) {
        return new BitmapDrawable(resources, a(a(drawable)));
    }
}
