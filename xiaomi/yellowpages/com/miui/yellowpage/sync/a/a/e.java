package com.miui.yellowpage.sync.a.a;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.utils.p;
import com.miui.yellowpage.utils.z;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import miui.os.Build;
import org.json.JSONObject;

/* compiled from: MessagingTemplate */
public class e extends d {
    public static String qT;
    private static String qU;
    private static String qV;
    private static String qW;
    private static String qX;
    private static String qY;

    static {
        qU = "content://msg-template/downloads";
        qV = "content://msg-template/allowed";
        qW = "no-update";
        qX = "diff";
        qY = "all";
        if (Build.IS_STABLE_VERSION) {
            qT = "http://open.mp.huangye.miui.com/sms/smartr/template";
        } else {
            qT = "http://trial.open.mp.huangye.miui.com/sms/smartr/template";
        }
    }

    protected boolean e(Context context, String str) {
        JSONObject jSONObject = new JSONObject(str);
        Log.d("MessagingTemplate", "pulled data in json is:" + jSONObject);
        a(context, jSONObject);
        return false;
    }

    protected JSONRequest m(Context context) {
        JSONRequest jSONRequest = new JSONRequest(context, qT);
        long D = D(context);
        Log.v("MessagingTemplate", " local version : " + D);
        jSONRequest.addParam("version", String.valueOf(D));
        return jSONRequest;
    }

    public boolean A(Context context) {
        boolean z;
        boolean z2 = false;
        Cursor query = context.getContentResolver().query(Uri.parse(qV), null, null, null, null);
        if (query != null) {
            try {
                z = query.moveToFirst() ? query.getInt(query.getColumnIndex("allowed")) == 1 : true;
                query.close();
            } catch (Throwable th) {
                query.close();
            }
        } else {
            z = true;
        }
        if (z) {
            long D = D(context);
            if (this.mVersion > D || this.mVersion == 0) {
                z2 = true;
            }
            Log.d("MessagingTemplate", "shouldPull? local version is " + D + " pushed version is " + this.mVersion);
        }
        return z2;
    }

    private void a(Context context, JSONObject jSONObject) {
        boolean z = false;
        String string = jSONObject.getString(MiniDefine.m);
        if (qW.equals(string)) {
            Log.d("MessagingTemplate", " no update needed! ");
            return;
        }
        boolean z2;
        if (qX.equals(string)) {
            z2 = true;
        } else if (qY.equals(string)) {
            z2 = false;
        } else {
            Log.e("MessagingTemplate", " unknown type!");
            return;
        }
        String string2 = jSONObject.getString("uri");
        long j = jSONObject.getLong("new_version");
        Log.v("MessagingTemplate", " uri and isIncremental " + string2 + " " + string);
        File file = new File(z.Cc, ".msgTemplate.tmp");
        int i = 0;
        while (i < 3 && !z) {
            try {
                Log.v("MessagingTemplate", " begin to download file: " + string2);
                z = Files.downLoadFile(context, string2, file.getAbsolutePath());
                i++;
                if (z) {
                    a(context, file);
                    context.getContentResolver().openOutputStream(Uri.parse(qU));
                    Log.v("MessagingTemplate", " download files success!");
                    Intent intent = new Intent("com.miui.yellowpage.MESSAGING_TEMPLATE_READY");
                    intent.putExtra("increment", z2);
                    intent.putExtra("version", j);
                    context.sendBroadcast(intent);
                }
            } catch (Throwable th) {
                Files.deleteFile(file);
            }
        }
        if (!z) {
            Log.e("MessagingTemplate", "failed to download file!");
        }
        Files.deleteFile(file);
    }

    private void a(Context context, File file) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        InputStream fileInputStream;
        try {
            byte[] bArr;
            fileInputStream = new FileInputStream(file);
            try {
                bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                fileOutputStream = (FileOutputStream) context.getContentResolver().openOutputStream(Uri.parse(qU));
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
            try {
                fileOutputStream.write(bArr);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileOutputStream2 = fileOutputStream;
                th = th4;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    public long D(Context context) {
        return p.M(context);
    }
}
