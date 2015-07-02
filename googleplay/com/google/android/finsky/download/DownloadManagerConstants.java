package com.google.android.finsky.download;

public final class DownloadManagerConstants {
    public static boolean isStatusSuccess(int status) {
        return status >= 200 && status < 300;
    }

    public static boolean isStatusCompleted(int status) {
        return (status >= 200 && status < 300) || (status >= 400 && status < 600);
    }
}
