package com.ta.utdid2.android.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class SystemUtils {
    public static String getCpuInfo() {
        FileNotFoundException fileNotFoundException;
        String str;
        FileNotFoundException fileNotFoundException2;
        String str2 = null;
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    str2 = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (IOException e) {
                    try {
                        Log.e("Could not read from file /proc/cpuinfo", e.toString());
                    } catch (FileNotFoundException e2) {
                        fileNotFoundException = e2;
                        str = str2;
                        fileNotFoundException2 = fileNotFoundException;
                        Log.e("BaseParameter-Could not open file /proc/cpuinfo", fileNotFoundException2.toString());
                        str2 = str;
                        if (str2 != null) {
                            return str2.substring(str2.indexOf(58) + 1).trim();
                        }
                        return ConfigConstant.WIRELESS_FILENAME;
                    }
                }
            }
        } catch (FileNotFoundException e22) {
            fileNotFoundException = e22;
            str = str2;
            fileNotFoundException2 = fileNotFoundException;
            Log.e("BaseParameter-Could not open file /proc/cpuinfo", fileNotFoundException2.toString());
            str2 = str;
            if (str2 != null) {
                return ConfigConstant.WIRELESS_FILENAME;
            }
            return str2.substring(str2.indexOf(58) + 1).trim();
        }
        if (str2 != null) {
            return str2.substring(str2.indexOf(58) + 1).trim();
        }
        return ConfigConstant.WIRELESS_FILENAME;
    }

    public static int getSystemVersion() {
        try {
            return VERSION.class.getField("SDK_INT").getInt(null);
        } catch (Exception e) {
            try {
                return Integer.parseInt((String) VERSION.class.getField("SDK").get(null));
            } catch (Exception e2) {
                e2.printStackTrace();
                return 2;
            }
        }
    }

    public static File getRootFolder(String str) {
        if (Environment.getExternalStorageDirectory() == null) {
            return null;
        }
        File file = new File(String.format("%s%s%s", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath(), File.separator, str}));
        if (file == null || file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    public static String getAppLabel(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            if (!(packageManager == null || packageName == null)) {
                return packageManager.getApplicationLabel(packageManager.getPackageInfo(packageName, 1).applicationInfo).toString();
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
