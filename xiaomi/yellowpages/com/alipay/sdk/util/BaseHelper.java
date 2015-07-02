package com.alipay.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alipay.sdk.cons.MiniDefine;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseHelper {
    public static String a(String str) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date());
    }

    public static String a() {
        String b = b();
        int indexOf = b.indexOf("-");
        if (indexOf != -1) {
            b = b.substring(0, indexOf);
        }
        indexOf = b.indexOf("\n");
        if (indexOf != -1) {
            b = b.substring(0, indexOf);
        }
        return "Linux " + b;
    }

    public static String b() {
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

    public static String a(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String c() {
        return MiniDefine.aQ;
    }

    public static String b(Context context) {
        DisplayMetrics c = c(context);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c.widthPixels);
        stringBuilder.append("*");
        stringBuilder.append(c.heightPixels);
        return stringBuilder.toString();
    }

    public static DisplayMetrics c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(MiniDefine.L)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String d(Context context) {
        String str = "-1;-1";
        try {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
            if (gsmCellLocation != null) {
                int cid = gsmCellLocation.getCid();
                int lac = gsmCellLocation.getLac();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(lac);
                stringBuilder.append(";");
                stringBuilder.append(cid);
                return stringBuilder.toString();
            }
        } catch (Object e) {
            LogUtils.a(e);
        }
        return str;
    }

    public static String a(int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            switch (random.nextInt(3)) {
                case TransactionXMLFile.MODE_PRIVATE /*0*/:
                    stringBuffer.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 65.0d))));
                    break;
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    stringBuffer.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 97.0d))));
                    break;
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    stringBuffer.append(String.valueOf(new Random().nextInt(10)));
                    break;
                default:
                    break;
            }
        }
        return stringBuffer.toString();
    }

    public static void a(Context context, String str) {
        a("777", str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.parse("file://" + str), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void a(String str, String str2) {
        try {
            Runtime.getRuntime().exec("chmod " + str + " " + str2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
