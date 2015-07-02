package com.google.android.finsky.download.obb;

import android.os.Environment;
import java.io.File;

public class ObbFactory {
    private static File sObbMasterDirectory;

    public static Obb create(boolean isPatch, String packageName, int versionCode, String url, long size, int state) {
        return new ObbImpl(isPatch, packageName, versionCode, url, size, state);
    }

    public static void initialize() {
        sObbMasterDirectory = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "obb");
    }

    public static File getParentDirectory(String packageName) {
        return new File(sObbMasterDirectory, packageName);
    }
}
