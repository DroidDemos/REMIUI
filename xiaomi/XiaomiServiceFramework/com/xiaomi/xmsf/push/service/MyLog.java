package com.xiaomi.xmsf.push.service;

import com.xiaomi.push.service.PushServiceConstants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.apache.log4j.Logger;

public abstract class MyLog {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(PushServiceConstants.LOG_TAG);
    }

    public static void v(String msg) {
        LOGGER.debug(msg);
    }

    public static void w(String msg) {
        LOGGER.warn(msg);
    }

    public static void w(String tag, String msg) {
        Logger.getLogger(tag).warn(msg);
    }

    public static void w(String tag, String msg, Throwable t) {
        Logger.getLogger(tag).warn(msg, t);
    }

    public static void i(String msg) {
        LOGGER.info(msg);
    }

    public static void e(String msg, Throwable t) {
        LOGGER.error(msg, t);
    }

    public static void e(String msg) {
        LOGGER.error(msg);
    }

    public static void e(Throwable t) {
        LOGGER.error(t);
    }

    public static void printCallStack(String message) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        printWriter.println(message);
        printWriter.println(String.format("Current thread id (%s); thread name (%s)", new Object[]{Long.valueOf(Thread.currentThread().getId()), Thread.currentThread().getName()}));
        new Throwable("Call stack").printStackTrace(printWriter);
        v(result.toString());
    }
}
