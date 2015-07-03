package com.xiaomi.account.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class SnsUtils {
    private static final String TAG = "SnsUtils";

    public static Bitmap getUserAvatarByAbsPath(Context context, String absPath) {
        InputStream inStream;
        OutputStream outStream;
        Bitmap bm = null;
        try {
            inStream = context.openFileInput(absPath);
            outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int len = inStream.read(buffer);
                if (len == -1) {
                    break;
                }
                outStream.write(buffer, 0, len);
            }
            closeQuiely(outStream);
            closeQuiely(inStream);
            byte[] data = outStream.toByteArray();
            if (data != null) {
                bm = BitmapFactory.decodeByteArray(data, 0, data.length);
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File is not found");
        } catch (IOException e2) {
            Log.e(TAG, "Read data error");
        } catch (Throwable th) {
            closeQuiely(outStream);
            closeQuiely(inStream);
        }
        return bm;
    }

    public static String saveUserAvatarByUrl(Context context, String imageUrl, String filePath) {
        String userAvatarAbsPath = null;
        try {
            Bitmap bm = BitmapFactory.decodeStream(new URL(imageUrl).openStream());
            if (bm == null) {
                return null;
            }
            OutputStream outputStream = null;
            userAvatarAbsPath = filePath + "_" + Uri.parse(imageUrl).getLastPathSegment();
            try {
                outputStream = context.openFileOutput(userAvatarAbsPath, 0);
                bm.compress(CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "File is not found");
                return userAvatarAbsPath;
            } catch (IOException e2) {
                Log.e(TAG, "Save file exception");
            } finally {
                closeQuiely(outputStream);
            }
            return userAvatarAbsPath;
        } catch (Exception e3) {
            Log.e(TAG, "Get data exception");
        }
    }

    private static void closeQuiely(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    private static void closeQuiely(OutputStream os) {
        if (os != null) {
            try {
                os.flush();
            } catch (IOException e) {
            }
            try {
                os.close();
            } catch (IOException e2) {
            }
        }
    }
}
