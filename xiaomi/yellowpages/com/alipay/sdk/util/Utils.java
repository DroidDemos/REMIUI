package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.alipay.sdk.cons.MiniDefine;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    static final String a = "com.alipay.android.app";
    static final String b = "com.eg.android.AlipayGphone";
    private static final String c = "7.0.0";
    private static final String d = "5.0.0";

    public static String a(byte[] bArr) {
        try {
            String obj = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey().toString();
            if (obj.indexOf("modulus") != -1) {
                return obj.substring(obj.indexOf("modulus") + 8, obj.lastIndexOf(",")).trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] a(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(64)) {
            if (packageInfo.packageName.equals(str)) {
                return packageInfo.signatures[0].toByteArray();
            }
        }
        return null;
    }

    public static boolean a(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo(a, 128) == null) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(b, 128);
            if (packageInfo != null && b(packageInfo.versionName, c) >= 0) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            if (b(context.getPackageManager().getPackageInfo(a, 128).versionName, d) >= 0) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static NetConnectionType d(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
            return NetConnectionType.a(activeNetworkInfo.getSubtype());
        }
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return NetConnectionType.NONE;
        }
        return NetConnectionType.WIFI;
    }

    public static boolean e(Context context) {
        switch (d(context).a()) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
            case Base64.CRLF /*4*/:
                return true;
            default:
                return false;
        }
    }

    public static String a(String str) {
        String str2 = "charset=";
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return null;
        }
        int length = str2.length() + indexOf;
        int indexOf2 = str.indexOf(";", length);
        if (indexOf2 != -1) {
            return str.substring(length, indexOf2);
        }
        return str.substring(length);
    }

    public static String b(String str) {
        int indexOf = str.indexOf(";");
        if (indexOf > 0) {
            return str.substring(0, indexOf);
        }
        return str;
    }

    public static void a(String str, String str2) {
        try {
            Runtime.getRuntime().exec("chmod " + str + " " + str2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String f(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }

    public static String g(Context context) {
        String l = l(context);
        String h = h(context);
        String i = i(context);
        return " (" + l + ";" + h + ";" + i + ";" + ";" + j(context) + ")" + "(sdk android)";
    }

    private static String l(Context context) {
        return "Android " + VERSION.RELEASE;
    }

    public static String h(Context context) {
        String a = a();
        int indexOf = a.indexOf("-");
        if (indexOf != -1) {
            a = a.substring(0, indexOf);
        }
        indexOf = a.indexOf("\n");
        if (indexOf != -1) {
            a = a.substring(0, indexOf);
        }
        return "Linux " + a;
    }

    public static String a() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            CharSequence readLine = bufferedReader.readLine();
            bufferedReader.close();
            String str = "\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)";
            Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
            if (!matcher.matches()) {
                return "Unavailable";
            }
            if (matcher.groupCount() < 4) {
                return "Unavailable";
            }
            return new StringBuilder(matcher.group(1)).append("\n").append(matcher.group(2)).append(" ").append(matcher.group(3)).append("\n").append(matcher.group(4)).toString();
        } catch (IOException e) {
            return "Unavailable";
        } catch (Throwable th) {
            bufferedReader.close();
        }
    }

    public static String i(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String j(Context context) {
        DisplayMetrics k = k(context);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(k.widthPixels);
        stringBuilder.append("*");
        stringBuilder.append(k.heightPixels);
        return stringBuilder.toString();
    }

    public static DisplayMetrics k(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(MiniDefine.L)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            InputStream open = context.getAssets().open(str);
            File file = new File(str2);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    open.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static PackageInfo b(Context context, String str) {
        return context.getPackageManager().getPackageArchiveInfo(str, 128);
    }

    public static void a(Activity activity, String str) {
        a("777", str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.parse("file://" + str), "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }

    public static boolean c(Context context, String str) {
        try {
            if (context.getPackageManager().getPackageArchiveInfo(str, 1) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static int b(String str, String str2) {
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        arrayList.addAll(Arrays.asList(str.split("\\.")));
        arrayList2.addAll(Arrays.asList(str2.split("\\.")));
        int max = Math.max(arrayList.size(), arrayList2.size());
        while (arrayList.size() < max) {
            arrayList.add(Profile.devicever);
        }
        while (arrayList2.size() < max) {
            arrayList2.add(Profile.devicever);
        }
        for (int i = 0; i < max; i++) {
            if (Integer.parseInt((String) arrayList.get(i)) != Integer.parseInt((String) arrayList2.get(i))) {
                return Integer.parseInt((String) arrayList.get(i)) - Integer.parseInt((String) arrayList2.get(i));
            }
        }
        return 0;
    }

    public static boolean c(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }
}
