package com.weibo.sdk.android.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.weibo.sdk.android.a;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class b {
    private static Bitmap a(String str, Options options) {
        FileInputStream fileInputStream;
        OutOfMemoryError e;
        Bitmap bitmap;
        FileInputStream fileInputStream2 = null;
        Options options2;
        if (options == null) {
            options2 = new Options();
            options2.inSampleSize = 1;
        } else {
            options2 = options;
        }
        int i = 0;
        Bitmap bitmap2 = null;
        while (i < 5) {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    bitmap2 = BitmapFactory.decodeStream(fileInputStream, null, options);
                    try {
                        fileInputStream.close();
                        break;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        break;
                    }
                } catch (OutOfMemoryError e3) {
                    e = e3;
                    bitmap = bitmap2;
                } catch (FileNotFoundException e4) {
                }
            } catch (OutOfMemoryError e5) {
                e = e5;
                fileInputStream = fileInputStream2;
                bitmap = bitmap2;
                e.printStackTrace();
                r0.inSampleSize *= 2;
                try {
                    fileInputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
                i++;
                bitmap2 = bitmap;
                fileInputStream2 = fileInputStream;
            } catch (FileNotFoundException e6) {
            }
        }
        return bitmap2;
    }

    private static void a(String str, int i, int i2) {
        if (i <= 0) {
            throw new IllegalArgumentException("size must be greater than 0!");
        } else if (!a.az(str)) {
            if (str == null) {
                str = "null";
            }
            throw new FileNotFoundException(str);
        } else if (c.ch(str)) {
            InputStream fileInputStream = new FileInputStream(str);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fileInputStream, null, options);
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int i3 = 0;
            while (true) {
                if ((options.outWidth >> i3) <= i && (options.outHeight >> i3) <= i) {
                    break;
                }
                i3++;
            }
            options.inSampleSize = (int) Math.pow(2.0d, (double) i3);
            options.inJustDecodeBounds = false;
            Bitmap a = a(str, options);
            if (a == null) {
                throw new IOException("Bitmap decode error!");
            }
            a.ay(str);
            a.aA(str);
            OutputStream fileOutputStream = new FileOutputStream(str);
            if (options == null || options.outMimeType == null || !options.outMimeType.contains("png")) {
                a.compress(CompressFormat.JPEG, i2, fileOutputStream);
            } else {
                a.compress(CompressFormat.PNG, i2, fileOutputStream);
            }
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.recycle();
        } else {
            throw new IOException(ConfigConstant.WIRELESS_FILENAME);
        }
    }

    public static boolean bd(String str) {
        try {
            if (!a.dX()) {
                a(str, 1024, 75);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
