package com.xiaomi.xmsf.push.service.log;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import miui.os.Build;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class ConfigureLog4J {
    public static final String FILE_NAME = "xmsf.log";
    private static final String FILE_PATTERN = "%d - [%p::%c] - %m%n";
    private static final String LOGCAT_PATTERN = "%d - [%p::%c] - %m%n";
    private static final Level LOG_LEVEL;
    private static final int MAX_FILE_SIZE = 1048576;
    private static final String TAG = "ConfigureLog4J";
    private static final AtomicBoolean mConfig;

    static {
        LOG_LEVEL = Level.DEBUG;
        mConfig = new AtomicBoolean();
    }

    public static void configure(Context context) {
        if (mConfig.getAndSet(true)) {
            Log.w(TAG, "config can only call once");
            return;
        }
        Logger root = Logger.getRootLogger();
        RollingFileAppender fileAppender = null;
        try {
            fileAppender = new RollingFileAppender(new PatternLayout(LOGCAT_PATTERN), context.getFileStreamPath(FILE_NAME).getAbsolutePath());
        } catch (IOException e) {
            Log.e(TAG, "failed to init file appender");
        }
        if (fileAppender != null) {
            fileAppender.setMaximumFileSize(1048576);
            fileAppender.setMaxBackupIndex(1);
            fileAppender.setImmediateFlush(true);
            if (!Build.IS_DEVELOPMENT_VERSION) {
                fileAppender.setThreshold(Level.WARN);
            }
            root.addAppender(fileAppender);
        }
        LogCatAppender logcatAppender = new LogCatAppender(new PatternLayout(LOGCAT_PATTERN));
        if (!Build.IS_DEVELOPMENT_VERSION) {
            logcatAppender.setThreshold(Level.WARN);
        }
        root.addAppender(logcatAppender);
        root.setLevel(LOG_LEVEL);
        Log.i(TAG, "log system inited");
    }
}
