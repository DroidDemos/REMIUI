package com.xiaomi.channel.commonutils.logger;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug.MemoryInfo;
import android.os.Process;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MyLog {
    public static final int DEBUG = 1;
    public static final int ERROR = 4;
    public static final int FATAL = 5;
    public static final int INFO = 0;
    private static int LOG_LEVEL = 0;
    private static final Integer NEGATIVE_CODE;
    public static final int WARN = 2;
    private static LoggerInterface logger;
    private static final HashMap<Integer, String> mActionNames;
    private static AtomicInteger mCodeGenerator;
    private static final HashMap<Integer, Long> mStartTimes;

    static {
        LOG_LEVEL = WARN;
        logger = new DefaultAndroidLogger();
        mStartTimes = new HashMap();
        mActionNames = new HashMap();
        NEGATIVE_CODE = Integer.valueOf(-1);
        mCodeGenerator = new AtomicInteger(DEBUG);
    }

    public static void setLogger(LoggerInterface newLogger) {
        logger = newLogger;
    }

    public static void warn(String msg) {
        log((int) WARN, "[Process:" + Process.myPid() + "]" + "[Thread:" + Thread.currentThread().getId() + "] " + msg);
    }

    public static void info(String msg) {
        log((int) INFO, msg);
    }

    public static void v(String msg) {
        log((int) DEBUG, "[Process:" + Process.myPid() + "]" + "[Thread:" + Thread.currentThread().getId() + "] " + msg);
    }

    public static void v(Object[] msgs) {
        log((int) DEBUG, XMStringUtils.join(msgs, MiPushClient.ACCEPT_TIME_SEPARATOR));
    }

    public static void e(String msg, Throwable t) {
        log(ERROR, msg, t);
    }

    public static void e(Throwable t) {
        log((int) ERROR, t);
    }

    public static void e(String msg) {
        log((int) ERROR, msg);
    }

    public static void heapInfo(Context context, String msg) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        int[] pids = new int[DEBUG];
        pids[INFO] = Process.myPid();
        MemoryInfo[] memInfo = activityManager.getProcessMemoryInfo(pids);
        info("+++Heap Info+++" + msg + " total:" + memInfo[INFO].getTotalPss() + ", managed:" + memInfo[INFO].dalvikPss + ", native:" + memInfo[INFO].nativePss);
    }

    public static Integer ps(String action) {
        if (LOG_LEVEL > DEBUG) {
            return NEGATIVE_CODE;
        }
        Integer code = Integer.valueOf(mCodeGenerator.incrementAndGet());
        mStartTimes.put(code, Long.valueOf(System.currentTimeMillis()));
        mActionNames.put(code, action);
        logger.log(action + " starts");
        return code;
    }

    public static void pe(Integer code) {
        if (LOG_LEVEL <= DEBUG && mStartTimes.containsKey(code)) {
            long time = System.currentTimeMillis() - ((Long) mStartTimes.remove(code)).longValue();
            logger.log(((String) mActionNames.remove(code)) + " ends in " + time + " ms");
        }
    }

    public static void log(int level, String msg) {
        if (level >= LOG_LEVEL) {
            logger.log(msg);
        }
    }

    public static void log(int level, Throwable t) {
        if (level >= LOG_LEVEL) {
            logger.log("", t);
        }
    }

    public static void log(int level, String msg, Throwable t) {
        if (level >= LOG_LEVEL) {
            logger.log(msg, t);
        }
    }

    public static void setLogLevel(int level) {
        if (level < 0 || level > FATAL) {
            log((int) WARN, "set log level as " + level);
        }
        LOG_LEVEL = level;
    }

    public static int getLogLevel() {
        return LOG_LEVEL;
    }

    public static void printCallStack(String message) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        printWriter.println(message);
        Object[] objArr = new Object[WARN];
        objArr[INFO] = Long.valueOf(Thread.currentThread().getId());
        objArr[DEBUG] = Thread.currentThread().getName();
        printWriter.println(String.format("Current thread id (%s); thread name (%s)", objArr));
        new Throwable("Call stack").printStackTrace(printWriter);
        v(result.toString());
    }
}
