package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.log.MIPushDebugLog;
import com.xiaomi.push.log.MIPushLog2File;

public class Logger {
    private static boolean sDisablePushLog;
    private static LoggerInterface sUserLogger;

    static {
        sDisablePushLog = false;
        sUserLogger = null;
    }

    public static void setLogger(Context context, LoggerInterface newLogger) {
        sUserLogger = newLogger;
        setPushLog(context);
    }

    public static void disablePushFileLog(Context context) {
        sDisablePushLog = true;
        setPushLog(context);
    }

    public static void enablePushFileLog(Context context) {
        sDisablePushLog = false;
        setPushLog(context);
    }

    private static void setPushLog(Context context) {
        boolean shouldOpenUserLogger = sUserLogger != null;
        LoggerInterface pushLog2File = new MIPushLog2File(context);
        if (!sDisablePushLog && hasWritePermission(context) && shouldOpenUserLogger) {
            MyLog.setLogger(new MIPushDebugLog(sUserLogger, pushLog2File));
        } else if (!sDisablePushLog && hasWritePermission(context)) {
            MyLog.setLogger(pushLog2File);
        } else if (shouldOpenUserLogger) {
            MyLog.setLogger(sUserLogger);
        } else {
            MyLog.setLogger(new MIPushDebugLog(null, null));
        }
    }

    private static boolean hasWritePermission(Context context) {
        try {
            String[] permissionList = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (permissionList != null) {
                for (String permission : permissionList) {
                    if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(permission)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
}
