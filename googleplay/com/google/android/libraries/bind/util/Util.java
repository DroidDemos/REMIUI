package com.google.android.libraries.bind.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources.NotFoundException;

public class Util {
    private static Context appContext;
    private static int memoryClass;

    static {
        memoryClass = -1;
    }

    public static String getResourceName(int resId) {
        if (appContext != null) {
            try {
                return appContext.getResources().getResourceEntryName(resId);
            } catch (NotFoundException e) {
            }
        }
        return Integer.toString(resId);
    }

    public static void checkPrecondition(boolean state) {
        if (!state) {
            throw new IllegalStateException();
        }
    }

    public static void checkPrecondition(boolean state, String message) {
        if (!state) {
            throw new IllegalStateException(message);
        }
    }

    public static boolean objectsEqual(Object a, Object b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }
    }

    public static int hashCode(Object... objects) {
        if (objects == null) {
            return 0;
        }
        int hashCode = 1;
        for (Object element : objects) {
            int elementHashCode;
            if (element == null) {
                elementHashCode = 0;
            } else {
                elementHashCode = element.hashCode();
            }
            hashCode = (hashCode * 31) + elementHashCode;
        }
        return hashCode;
    }

    public static int getMemoryClass() {
        if (memoryClass == -1) {
            ActivityManager activityManager = (ActivityManager) appContext.getSystemService("activity");
            if (activityManager != null) {
                memoryClass = activityManager.getMemoryClass();
            }
        }
        return memoryClass;
    }

    public static boolean isLowMemoryDevice() {
        return getMemoryClass() < 96;
    }
}
