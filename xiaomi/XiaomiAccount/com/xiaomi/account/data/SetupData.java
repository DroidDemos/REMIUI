package com.xiaomi.account.data;

import java.util.ArrayList;

public class SetupData {
    private static SetupData ourInstance;
    private volatile ArrayList<DeviceInfo> mDeviceList;
    private volatile boolean mUserVerified;

    static {
        ourInstance = new SetupData();
    }

    public static synchronized SetupData getInstance() {
        SetupData setupData;
        synchronized (SetupData.class) {
            setupData = ourInstance;
        }
        return setupData;
    }

    private SetupData() {
    }

    public static void setUserVerified(boolean verified) {
        getInstance().mUserVerified = verified;
    }

    public static boolean isUserVerified() {
        return getInstance().mUserVerified;
    }

    public static void setDeviceList(ArrayList<DeviceInfo> devicesList) {
        getInstance().mDeviceList = devicesList;
    }

    public static ArrayList<DeviceInfo> getDeviceList() {
        return getInstance().mDeviceList;
    }
}
