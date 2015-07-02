package com.alipay.sdk.sys;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.data.MspConfig;
import com.alipay.sdk.util.BaseHelper;
import com.ta.utdid2.device.UTDevice;
import java.io.File;

public class GlobalContext {
    private static GlobalContext a;
    private static String e;
    private Context b;
    private String c;
    private int d;
    private MspConfig f;

    private GlobalContext() {
    }

    public static GlobalContext a() {
        if (a == null) {
            a = new GlobalContext();
        }
        return a;
    }

    public Context b() {
        return this.b;
    }

    public void a(Context context, MspConfig mspConfig) {
        this.b = context.getApplicationContext();
        this.f = mspConfig;
    }

    public MspConfig c() {
        return this.f;
    }

    public boolean d() {
        return false;
    }

    public int e() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public String f() {
        if (TextUtils.isEmpty(this.c)) {
            this.c = null;
        }
        return this.c;
    }

    public static boolean g() {
        String[] strArr = new String[]{"/system/xbin/", "/system/bin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < strArr.length) {
            try {
                File file = new File(strArr[i] + "su");
                if (file == null || !file.exists()) {
                    i++;
                } else {
                    String a = a(new String[]{"ls", "-l", r4});
                    if (TextUtils.isEmpty(a) || a.indexOf("root") == a.lastIndexOf("root")) {
                        return false;
                    }
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String[] r6) {
        /*
        r2 = "";
        r0 = 0;
        r1 = new java.lang.ProcessBuilder;	 Catch:{ Exception -> 0x003a, all -> 0x0043 }
        r1.<init>(r6);	 Catch:{ Exception -> 0x003a, all -> 0x0043 }
        r3 = 0;
        r1.redirectErrorStream(r3);	 Catch:{ Exception -> 0x003a, all -> 0x0043 }
        r1 = r1.start();	 Catch:{ Exception -> 0x003a, all -> 0x0043 }
        r3 = new java.io.DataOutputStream;	 Catch:{ Exception -> 0x0051, all -> 0x004f }
        r0 = r1.getOutputStream();	 Catch:{ Exception -> 0x0051, all -> 0x004f }
        r3.<init>(r0);	 Catch:{ Exception -> 0x0051, all -> 0x004f }
        r0 = new java.io.DataInputStream;	 Catch:{ Exception -> 0x0051, all -> 0x004f }
        r4 = r1.getInputStream();	 Catch:{ Exception -> 0x0051, all -> 0x004f }
        r0.<init>(r4);	 Catch:{ Exception -> 0x0051, all -> 0x004f }
        if (r0 == 0) goto L_0x0056;
    L_0x0024:
        if (r3 == 0) goto L_0x0056;
    L_0x0026:
        r2 = r0.readLine();	 Catch:{ Exception -> 0x0051, all -> 0x004f }
        r0 = r2;
    L_0x002b:
        r2 = "exit\n";
        r3.writeBytes(r2);	 Catch:{ Exception -> 0x0054, all -> 0x004f }
        r3.flush();	 Catch:{ Exception -> 0x0054, all -> 0x004f }
        r1.waitFor();	 Catch:{ Exception -> 0x0054, all -> 0x004f }
        r1.destroy();	 Catch:{ Exception -> 0x004b }
    L_0x0039:
        return r0;
    L_0x003a:
        r1 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x003d:
        r1.destroy();	 Catch:{ Exception -> 0x0041 }
        goto L_0x0039;
    L_0x0041:
        r1 = move-exception;
        goto L_0x0039;
    L_0x0043:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0047:
        r1.destroy();	 Catch:{ Exception -> 0x004d }
    L_0x004a:
        throw r0;
    L_0x004b:
        r1 = move-exception;
        goto L_0x0039;
    L_0x004d:
        r1 = move-exception;
        goto L_0x004a;
    L_0x004f:
        r0 = move-exception;
        goto L_0x0047;
    L_0x0051:
        r0 = move-exception;
        r0 = r2;
        goto L_0x003d;
    L_0x0054:
        r2 = move-exception;
        goto L_0x003d;
    L_0x0056:
        r0 = r2;
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.sys.GlobalContext.a(java.lang.String[]):java.lang.String");
    }

    public String b(int i) {
        return this.b.getString(i);
    }

    public float h() {
        return BaseHelper.c(this.b).density;
    }

    public static String i() {
        String a = BaseHelper.a(24);
        e = a;
        return a;
    }

    public static String j() {
        return e;
    }

    public String k() {
        String utdid = UTDevice.getUtdid(this.b);
        return (TextUtils.isEmpty(utdid) || !a(utdid)) ? ConfigConstant.WIRELESS_FILENAME : utdid;
    }

    private boolean a(String str) {
        char[] cArr = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/', '='};
        boolean z = false;
        for (char c : str.toCharArray()) {
            z = false;
            for (char c2 : cArr) {
                if (c == c2) {
                    z = true;
                }
            }
            if (!z) {
                break;
            }
        }
        return z;
    }
}
