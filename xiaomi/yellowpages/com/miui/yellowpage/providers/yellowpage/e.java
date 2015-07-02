package com.miui.yellowpage.providers.yellowpage;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.request.StreamRequest;
import com.miui.yellowpage.base.utils.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: ImageHandler */
public class e {
    public static File y(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new File(context.getCacheDir().getAbsolutePath() + File.separator + str);
    }

    public static boolean d(Context context, String str, String str2) {
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        FileOutputStream fileOutputStream;
        try {
            File y = y(context, str);
            fileOutputStream = new FileOutputStream(y);
            try {
                StreamRequest streamRequest = new StreamRequest(context, str2);
                streamRequest.setDecryptDownloadData(false);
                if (streamRequest.requestStream(fileOutputStream) == 0) {
                    Options options = new Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(y.getAbsolutePath(), options);
                    if (TextUtils.isEmpty(options.outMimeType) || !options.outMimeType.startsWith(MiniDefine.z)) {
                        Log.d("ImageHandler", "Invalid mime type [" + options.outMimeType + "]");
                        if (fileOutputStream == null) {
                            return false;
                        }
                        try {
                            fileOutputStream.close();
                            return false;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            return false;
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return true;
                } else if (fileOutputStream == null) {
                    return false;
                } else {
                    try {
                        fileOutputStream.close();
                        return false;
                    } catch (IOException e32) {
                        e32.printStackTrace();
                        return false;
                    }
                }
            } catch (FileNotFoundException e5) {
                e2 = e5;
                try {
                    e2.printStackTrace();
                    if (fileOutputStream != null) {
                        return false;
                    }
                    try {
                        fileOutputStream.close();
                        return false;
                    } catch (IOException e322) {
                        e322.printStackTrace();
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3222) {
                            e3222.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e3222 = e6;
                e3222.printStackTrace();
                if (fileOutputStream != null) {
                    return false;
                }
                try {
                    fileOutputStream.close();
                    return false;
                } catch (IOException e32222) {
                    e32222.printStackTrace();
                    return false;
                }
            }
        } catch (FileNotFoundException e7) {
            e2 = e7;
            fileOutputStream = null;
            e2.printStackTrace();
            if (fileOutputStream != null) {
                return false;
            }
            fileOutputStream.close();
            return false;
        } catch (IOException e8) {
            e32222 = e8;
            fileOutputStream = null;
            e32222.printStackTrace();
            if (fileOutputStream != null) {
                return false;
            }
            fileOutputStream.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }
}
