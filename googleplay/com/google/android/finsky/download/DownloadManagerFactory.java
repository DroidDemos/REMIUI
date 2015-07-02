package com.google.android.finsky.download;

import android.content.Context;

public class DownloadManagerFactory {
    public static DownloadManagerFacade getDownloadManager(Context context) {
        return new DownloadManagerLegacyImpl(context);
    }
}
