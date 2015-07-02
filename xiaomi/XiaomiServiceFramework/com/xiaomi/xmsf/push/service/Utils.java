package com.xiaomi.xmsf.push.service;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.xiaomi.push.service.PushConstants;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.transport.TTransportException;

public final class Utils {

    public enum NetState {
        NONE,
        Wifi,
        MN2G,
        MN3G,
        MN4G,
        OTHER
    }

    public static NetState getNetState(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            return NetState.NONE;
        }
        int type = networkInfo.getType();
        if (type == 1) {
            return NetState.Wifi;
        }
        if (type == 0) {
            return getMobileNetworkClass(((TelephonyManager) context.getSystemService("phone")).getNetworkType());
        }
        return NetState.OTHER;
    }

    private static NetState getMobileNetworkClass(int networkType) {
        switch (networkType) {
            case TTransportException.NOT_OPEN /*1*/:
            case TTransportException.ALREADY_OPEN /*2*/:
            case TTransportException.END_OF_FILE /*4*/:
            case TApplicationException.PROTOCOL_ERROR /*7*/:
            case PushConstants.ERROR_RESET /*11*/:
                return NetState.MN2G;
            case TTransportException.TIMED_OUT /*3*/:
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
            case TApplicationException.INTERNAL_ERROR /*6*/:
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
            case PushConstants.ERROR_READ_ERROR /*9*/:
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
            case PushConstants.ERROR_NO_CLIENT /*12*/:
            case PushConstants.ERROR_THREAD_BLOCK /*14*/:
            case PushConstants.ERROR_SERVICE_DESTROY /*15*/:
                return NetState.MN3G;
            case PushConstants.ERROR_SERVER_STREAM /*13*/:
                return NetState.MN4G;
            default:
                return NetState.OTHER;
        }
    }

    public static String getLocalIPAddress() {
        try {
            Enumeration<NetworkInterface> mEnumeration = NetworkInterface.getNetworkInterfaces();
            while (mEnumeration.hasMoreElements()) {
                Enumeration<InetAddress> enumIPAddr = ((NetworkInterface) mEnumeration.nextElement()).getInetAddresses();
                while (enumIPAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIPAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            MyLog.e("Fail to get Local IP address " + ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getIMEI(Context context) {
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        if (deviceId != null) {
            String tmp = deviceId.trim();
            if (tmp.length() > 0 && !tmp.equals("0")) {
                return deviceId;
            }
        }
        return null;
    }

    public static final String getXiaomiUserId(Context context) {
        Account[] accounts = AccountManager.get(context).getAccounts();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].type.equals("com.xiaomi")) {
                String name = accounts[i].name;
                if (!name.trim().isEmpty()) {
                    return name;
                }
            }
        }
        return null;
    }
}
