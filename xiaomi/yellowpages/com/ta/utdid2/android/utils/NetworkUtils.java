package com.ta.utdid2.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;

public class NetworkUtils {
    public static final String DEFAULT_WIFI_ADDRESS = "00-00-00-00-00-00";
    public static final String WIFI = "Wi-Fi";

    public static boolean isConnectInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isAvailable();
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static String[] getNetworkState(Context context) {
        String[] strArr = new String[]{"Unknown", "Unknown"};
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
                strArr[0] = "Unknown";
                return strArr;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                strArr[0] = "Unknown";
                return strArr;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || networkInfo.getState() != State.CONNECTED) {
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2 != null && networkInfo2.getState() == State.CONNECTED) {
                    strArr[0] = "2G/3G";
                    strArr[1] = networkInfo2.getSubtypeName();
                    return strArr;
                }
                return strArr;
            }
            strArr[0] = WIFI;
            return strArr;
        } catch (Exception e) {
        }
    }

    public static String getWifiAddress(Context context) {
        if (context == null) {
            return DEFAULT_WIFI_ADDRESS;
        }
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(ConfigConstant.JSON_SECTION_WIFI)).getConnectionInfo();
        if (connectionInfo == null) {
            return DEFAULT_WIFI_ADDRESS;
        }
        String macAddress = connectionInfo.getMacAddress();
        if (StringUtils.isEmpty(macAddress)) {
            return DEFAULT_WIFI_ADDRESS;
        }
        return macAddress;
    }

    private static String _convertIntToIp(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static String getWifiIpAddress(Context context) {
        if (context != null) {
            try {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService(ConfigConstant.JSON_SECTION_WIFI)).getConnectionInfo();
                if (connectionInfo != null) {
                    return _convertIntToIp(connectionInfo.getIpAddress());
                }
                return null;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static boolean isWifi(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (getNetworkState(context)[0].equals(WIFI)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
