package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@fd
public final class gm {
    private static final ThreadFactory wv;
    private static final ExecutorService ww;

    static {
        wv = new ThreadFactory() {
            private final AtomicInteger wz;

            {
                this.wz = new AtomicInteger(1);
            }

            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "AdWorker #" + this.wz.getAndIncrement());
            }
        };
        ww = Executors.newFixedThreadPool(10, wv);
    }

    public static Future<Void> a(final Runnable runnable) {
        return submit(new Callable<Void>() {
            public /* synthetic */ Object call() throws Exception {
                return ds();
            }

            public Void ds() {
                runnable.run();
                return null;
            }
        });
    }

    public static <T> Future<T> submit(final Callable<T> callable) {
        try {
            return ww.submit(new Callable<T>() {
                public T call() throws Exception {
                    try {
                        Process.setThreadPriority(10);
                        return callable.call();
                    } catch (Throwable e) {
                        gf.e(e);
                        return null;
                    }
                }
            });
        } catch (Throwable e) {
            gw.w("Thread execution is rejected.", e);
            return new gp(null);
        }
    }
}
