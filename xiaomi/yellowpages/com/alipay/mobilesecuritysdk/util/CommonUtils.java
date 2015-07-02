package com.alipay.mobilesecuritysdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

@SuppressLint({"SimpleDateFormat"})
public class CommonUtils {
    public static boolean outOfDate(long j, long j2, int i) {
        if (j2 > 0 && (System.currentTimeMillis() - j) / j2 < ((long) i)) {
            return false;
        }
        return true;
    }

    public static boolean isNetWorkActive(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1) {
            return true;
        }
        return false;
    }

    public static String convertDate2String(Date date) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    public static boolean isBlank(String str) {
        if (str != null) {
            int length = str.length();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!Character.isWhitespace(str.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    public static boolean IsEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static boolean isBlankCollection(List list) {
        if (list == null) {
            return true;
        }
        if (list.size() <= 0) {
            return true;
        }
        for (String isBlank : list) {
            if (!isBlank(isBlank)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsIgnoreCase(String str, String str2) {
        if (str == null) {
            return str2 == null;
        } else {
            return str.equalsIgnoreCase(str2);
        }
    }

    public static int string2int(String str) {
        int i = 0;
        try {
            if (!isBlank(str)) {
                i = Integer.parseInt(str);
            }
        } catch (Exception e) {
        }
        return i;
    }

    public static long string2long(String str) {
        long j = 0;
        try {
            if (!isBlank(str)) {
                j = Long.parseLong(str);
            }
        } catch (Exception e) {
        }
        return j;
    }

    public static String MD5(String str) {
        try {
            if (isBlank(str)) {
                return null;
            }
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static JSONObject GetJsonFromFile(String str) {
        return null;
    }

    public static String ReadFile(String str) {
        if (!new File(str).exists()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str), "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void WriteFile(String str, String str2) {
        Exception e;
        Throwable th;
        File file = new File(str);
        if (file != null) {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(file, false);
                try {
                    fileWriter.write(str2);
                    fileWriter.close();
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Log.d("ConfigNameEnum.CONFIGS.getValue()", e.getLocalizedMessage());
                        fileWriter.close();
                    } catch (Throwable th2) {
                        th = th2;
                        fileWriter.close();
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileWriter = null;
                Log.d("ConfigNameEnum.CONFIGS.getValue()", e.getLocalizedMessage());
                fileWriter.close();
            } catch (Throwable th3) {
                th = th3;
                fileWriter = null;
                fileWriter.close();
                throw th;
            }
        }
    }

    public static String textCompress(String str) {
        try {
            Object array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(str.length()).array();
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes("UTF-8"));
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            Object obj = new byte[(byteArrayOutputStream.toByteArray().length + 4)];
            System.arraycopy(array, 0, obj, 0, 4);
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, obj, 4, byteArrayOutputStream.toByteArray().length);
            return Base64.encodeToString(obj, 8);
        } catch (Exception e) {
            Log.i(ConfigConstant.LOG_TAG, e.getMessage());
            return ConfigConstant.WIRELESS_FILENAME;
        }
    }

    public static boolean GetSdCardFile() {
        String externalStorageState = Environment.getExternalStorageState();
        if (IsEmpty(externalStorageState) || ((!externalStorageState.equals("mounted") && !externalStorageState.equals("mounted_ro")) || Environment.getExternalStorageDirectory() == null)) {
            return false;
        }
        return true;
    }
}
