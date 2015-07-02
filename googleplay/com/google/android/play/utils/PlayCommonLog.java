package com.google.android.play.utils;

import android.util.Log;
import java.util.IllegalFormatException;
import java.util.Locale;

public class PlayCommonLog {
    public static final boolean DEBUG;
    private static String TAG;
    private static long sStartTime;

    static {
        TAG = "PlayCommon";
        DEBUG = Log.isLoggable(TAG, 2);
        sStartTime = System.currentTimeMillis();
    }

    public static void v(String format, Object... args) {
        Log.v(TAG, buildMessage(format, args));
    }

    public static void d(String format, Object... args) {
        Log.d(TAG, buildMessage(format, args));
    }

    public static void w(String format, Object... args) {
        Log.w(TAG, buildMessage(format, args));
    }

    public static void e(String format, Object... args) {
        Log.e(TAG, buildMessage(format, args));
    }

    public static void wtf(String format, Object... args) {
        Log.e(TAG, buildMessage(format, args));
        Log.wtf(TAG, buildMessage(format, args));
    }

    public static void logTiming(String msg, Object... args) {
        if (Log.isLoggable(TAG, 2)) {
            if (args != null) {
                msg = String.format(Locale.US, msg, args);
            }
            v("%4dms: %s", Long.valueOf(System.currentTimeMillis() - sStartTime), msg);
        }
    }

    private static String buildMessage(String format, Object... args) {
        String msg;
        if (args == null) {
            msg = format;
        } else {
            try {
                msg = String.format(Locale.US, format, args);
            } catch (IllegalFormatException e) {
                wtf("IllegalFormatException: formatString='%s' numArgs=%d", format, Integer.valueOf(args.length));
                msg = format + " (An error occurred while formatting the message.)";
            }
        }
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        for (int i = 2; i < trace.length; i++) {
            String callingClass = trace[i].getClassName();
            if (!callingClass.equals(PlayCommonLog.class.getName())) {
                callingClass = callingClass.substring(callingClass.lastIndexOf(46) + 1);
                caller = callingClass.substring(callingClass.lastIndexOf(36) + 1) + "." + trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), caller, msg});
    }
}
