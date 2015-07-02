package com.google.android.finsky.download;

import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;

public class Storage {
    public static boolean externalStorageAvailable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static long externalStorageAvailableSpace() {
        if (externalStorageAvailable()) {
            return partitionAvailable(Environment.getExternalStorageDirectory().getPath());
        }
        return -1;
    }

    public static long dataPartitionAvailableSpace() {
        return partitionAvailable(Environment.getDataDirectory().getPath());
    }

    private static long partitionAvailable(String path) {
        StatFs stats = new StatFs(path);
        if (VERSION.SDK_INT >= 18) {
            return stats.getAvailableBytes();
        }
        return ((long) stats.getBlockSize()) * ((long) stats.getAvailableBlocks());
    }
}
