package com.google.android.play.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class PlayUtils {
    private static final Pattern COMMA_PATTERN;
    private static final String[] EMPTY_ARRAY;

    static {
        COMMA_PATTERN = Pattern.compile(",");
        EMPTY_ARRAY = new String[0];
    }

    public static boolean isTv(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature("com.google.android.tv") || packageManager.hasSystemFeature("android.hardware.type.television");
    }

    public static boolean isTestDevice() {
        List<String> buildTags = Arrays.asList(commaUnpackStrings(Build.TAGS));
        return buildTags.contains("dev-keys") || buildTags.contains("test-keys");
    }

    private static String[] commaUnpackStrings(String stringsList) {
        if (stringsList == null || stringsList.length() == 0) {
            return EMPTY_ARRAY;
        }
        if (stringsList.indexOf(44) != -1) {
            return COMMA_PATTERN.split(stringsList);
        }
        return new String[]{stringsList};
    }

    public static boolean useLtr(Context mContext) {
        if (VERSION.SDK_INT >= 17 && mContext.getResources().getConfiguration().getLayoutDirection() != 0) {
            return false;
        }
        return true;
    }
}
