package com.google.android.libraries.bind.async;

import android.os.Handler;
import android.os.Looper;
import com.google.android.libraries.bind.util.Util;
import java.util.concurrent.Executor;

public class AsyncUtil {
    private static Executor immediateExecutor;
    private static Executor mainThreadExecutor;
    private static final Handler mainThreadHandler;

    static {
        mainThreadHandler = new Handler(Looper.getMainLooper());
        mainThreadExecutor = new Executor() {
            public void execute(Runnable runnable) {
                AsyncUtil.mainThreadHandler.post(runnable);
            }
        };
        immediateExecutor = new Executor() {
            public void execute(Runnable command) {
                command.run();
            }
        };
    }

    public static void checkMainThread() {
        Util.checkPrecondition(isMainThread(), "Not on the main thread");
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static Handler mainThreadHandler() {
        return mainThreadHandler;
    }

    public static Executor mainThreadExecutor() {
        return mainThreadExecutor;
    }

    public static Executor immediateExecutor() {
        return immediateExecutor;
    }
}
