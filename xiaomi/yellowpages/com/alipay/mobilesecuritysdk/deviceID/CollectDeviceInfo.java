package com.alipay.mobilesecuritysdk.deviceID;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CollectDeviceInfo {
    private static CollectDeviceInfo collectSingleton;

    static {
        collectSingleton = new CollectDeviceInfo();
    }

    private CollectDeviceInfo() {
    }

    public static CollectDeviceInfo getInstance() {
        return collectSingleton;
    }

    public String getMacAddress(Context context) {
        String str = ConfigConstant.WIRELESS_FILENAME;
        return ((WifiManager) context.getSystemService(ConfigConstant.JSON_SECTION_WIFI)).getConnectionInfo().getMacAddress();
    }

    public String getCpuNum() {
        String str = ConfigConstant.WIRELESS_FILENAME;
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    str = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (IOException e) {
                    Log.i(DeviceIdModel.PRIVATE_NAME, "getCpuNum" + e.getLocalizedMessage());
                }
            }
        } catch (FileNotFoundException e2) {
        }
        if (str != null) {
            return str.substring(str.indexOf(58) + 1).trim();
        }
        return ConfigConstant.WIRELESS_FILENAME;
    }

    public String getSDKVer() {
        int i;
        try {
            i = VERSION.class.getField("SDK_INT").getInt(null);
        } catch (Exception e) {
            try {
                i = Integer.parseInt((String) VERSION.class.getField("SDK").get(null));
            } catch (Exception e2) {
                i = 2;
            }
        }
        return Integer.toString(i);
    }

    public String getOsVer() {
        return VERSION.RELEASE;
    }

    public String getPhoneModel() {
        return Build.MODEL;
    }

    public String getResolution(Context context) {
        return context.getResources().getDisplayMetrics().toString();
    }

    public String getNetworkType(Context context) {
        return Integer.toString(((TelephonyManager) context.getSystemService("phone")).getNetworkType());
    }

    public String getImei(Context context) {
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    return telephonyManager.getDeviceId();
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public String getImsi(Context context) {
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    return telephonyManager.getSubscriberId();
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public String getCpuFre() {
        int i = 2;
        String str = "/proc/cpuinfo";
        String str2 = ConfigConstant.WIRELESS_FILENAME;
        String[] strArr = new String[]{ConfigConstant.WIRELESS_FILENAME, ConfigConstant.WIRELESS_FILENAME};
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str), 8192);
            String[] split = bufferedReader.readLine().split("\\s+");
            while (i < split.length) {
                strArr[0] = strArr[0] + split[i] + " ";
                i++;
            }
            strArr[1] = strArr[1] + bufferedReader.readLine().split("\\s+")[2];
            bufferedReader.close();
            return strArr[1];
        } catch (IOException e) {
            return null;
        }
    }

    public long getTotalMemory() {
        long intValue;
        IOException e;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            intValue = (long) Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue();
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                e = e2;
                Log.i(DeviceIdModel.PRIVATE_NAME, "getTotalMemory" + e.getLocalizedMessage());
                return intValue;
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            intValue = 0;
            e = iOException;
            Log.i(DeviceIdModel.PRIVATE_NAME, "getTotalMemory" + e.getLocalizedMessage());
            return intValue;
        }
        return intValue;
    }

    public long getSDCardMemory() {
        long[] jArr = new long[2];
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                long blockSize = (long) statFs.getBlockSize();
                long availableBlocks = (long) statFs.getAvailableBlocks();
                jArr[0] = ((long) statFs.getBlockCount()) * blockSize;
                jArr[1] = availableBlocks * blockSize;
            }
        } catch (Exception e) {
        }
        return jArr[0];
    }

    public String getRomName() {
        return Build.DISPLAY;
    }

    public String getBluMac() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null || defaultAdapter.isEnabled()) {
                return defaultAdapter.getAddress();
            }
            return ConfigConstant.WIRELESS_FILENAME;
        } catch (Exception e) {
            return null;
        }
    }

    public String getDeviceMx(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return new StringBuilder(String.valueOf(Integer.toString(displayMetrics.widthPixels))).append("*").append(Integer.toString(displayMetrics.heightPixels)).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getBandVer() {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            Object newInstance = cls.newInstance();
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(newInstance, new Object[]{"gsm.version.baseband", "no message"});
        } catch (Exception e) {
            return null;
        }
    }

    public String getPackageName(Context context) {
        return context.getPackageName();
    }
}
