package com.google.android.finsky.utils;

public interface NotificationListener {
    boolean showAppAlert(String str, String str2, String str3, int i);

    boolean showAppNotification(String str, String str2, String str3);

    boolean showDocAlert(String str, String str2, String str3, String str4);
}
