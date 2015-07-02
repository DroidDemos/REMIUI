package com.alipay.sdk.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.io.File;

public class DeviceInfo {
    public static final String a = "com.alipay.android.app";
    public static final String b = "com.eg.android.AlipayGphone";
    public static final String c = "com.eg.android.AlipayGphoneRC";
    public static final String d = "android";
    static DeviceInfo e = null;
    private static final String f = "00:00:00:00:00:00";
    private String g;
    private String h;
    private String i;
    private String j;

    static {
        e = null;
    }

    public static DeviceInfo a(Context context) {
        if (e == null) {
            e = new DeviceInfo(context);
        }
        return e;
    }

    public String a() {
        return this.i;
    }

    private DeviceInfo(Context context) {
        try {
            this.i = context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionName;
        } catch (Object e) {
            LogUtils.a(e);
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            b(telephonyManager.getDeviceId());
            a(telephonyManager.getSubscriberId());
        } catch (Object e2) {
            LogUtils.a(e2);
        }
        try {
            this.j = ((WifiManager) context.getSystemService(ConfigConstant.JSON_SECTION_WIFI)).getConnectionInfo().getMacAddress();
            if (TextUtils.isEmpty(this.j)) {
                this.j = f;
            }
        } catch (Object e22) {
            LogUtils.a(e22);
            if (TextUtils.isEmpty(this.j)) {
                this.j = f;
            }
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.j)) {
                this.j = f;
            }
        }
    }

    public String b() {
        if (TextUtils.isEmpty(this.g)) {
            this.g = "000000000000000";
        }
        return this.g;
    }

    public String c() {
        if (TextUtils.isEmpty(this.h)) {
            this.h = "000000000000000";
        }
        return this.h;
    }

    public void a(String str) {
        if (str != null) {
            str = (str + "000000000000000").substring(0, 15);
        }
        this.g = str;
    }

    public void b(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            int i = 0;
            while (i < bytes.length) {
                if (bytes[i] < (byte) 48 || bytes[i] > (byte) 57) {
                    bytes[i] = (byte) 48;
                }
                i++;
            }
            str = (new String(bytes) + "000000000000000").substring(0, 15);
        }
        this.h = str;
    }

    public String d() {
        String str = c() + "|";
        Object b = b();
        if (TextUtils.isEmpty(b)) {
            return str + "000000000000000";
        }
        return str + b;
    }

    public String e() {
        return this.j;
    }

    public static NetConnectionType b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
            return NetConnectionType.a(activeNetworkInfo.getSubtype());
        }
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return NetConnectionType.NONE;
        }
        return NetConnectionType.WIFI;
    }

    public static boolean c(Context context) {
        if (!f()) {
            return true;
        }
        try {
            context.getPackageManager().getPackageGids(b);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean f() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return false;
        }
        StatFs statFs = new StatFs(new File(Environment.getExternalStorageDirectory().getPath()).getPath());
        if (((double) (((long) statFs.getBlockSize()) * (((long) statFs.getAvailableBlocks()) - 4))) > 3.3554432E7d) {
            return true;
        }
        return false;
    }

    public static String d(Context context) {
        return a(context).d().substring(0, 8);
    }

    public static String g() {
        return "android " + VERSION.RELEASE;
    }
}
