package org.apache.log4j;

import com.google.android.finsky.utils.FinskyLog;

public class Logger {
    private final String mTag;

    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz);
    }

    public Logger(Class<?> tag) {
        this.mTag = tag.getSimpleName();
    }

    public void debug(Object message) {
        if (FinskyLog.DEBUG) {
            FinskyLog.v(this.mTag + ": " + message, new Object[0]);
        }
    }
}
