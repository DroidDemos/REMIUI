package com.weibo.sdk.android.a;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: BitmapHelper */
public final class c {
    public static boolean a(InputStream inputStream) {
        if (inputStream == null) {
            return false;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        BitmapFactory.decodeStream(inputStream, null, options);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (options.outHeight <= 0 || options.outWidth <= 0) {
            return false;
        }
        return true;
    }

    public static boolean ch(String str) {
        try {
            return a(new FileInputStream(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
