package com.google.android.finsky.utils;

import android.os.HandlerThread;
import android.os.Process;
import java.util.concurrent.ThreadFactory;

public class BackgroundThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        return createThread(r);
    }

    public static Thread createThread(Runnable r) {
        return new Thread(wrap(r));
    }

    public static Thread createThread(String name, Runnable r) {
        return new Thread(wrap(r), name);
    }

    private static Runnable wrap(final Runnable r) {
        return new Runnable() {
            public void run() {
                Process.setThreadPriority(10);
                r.run();
            }
        };
    }

    public static HandlerThread createHandlerThread(String name) {
        return new HandlerThread(name, 10);
    }
}
