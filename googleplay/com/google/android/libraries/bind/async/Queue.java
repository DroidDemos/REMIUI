package com.google.android.libraries.bind.async;

import android.os.Process;
import com.google.android.libraries.bind.logging.Logd;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Executor {
    private static final Logd LOGD;
    protected static final List<Queue> queues;
    protected Executor executor;
    public final Executor fallbackIfMain;
    private final String name;
    public final int poolSize;
    protected final ThreadGroup threadGroup;

    static {
        LOGD = Logd.get(Queues.class);
        queues = new ArrayList();
    }

    public Queue(String name, int poolSize, boolean jankLocked) {
        this.fallbackIfMain = new Executor() {
            public void execute(Runnable runnable) {
                if (AsyncUtil.isMainThread()) {
                    Queue.this.execute(runnable);
                } else {
                    runnable.run();
                }
            }
        };
        this.name = name;
        queues.add(this);
        this.poolSize = poolSize;
        this.threadGroup = new ThreadGroup(name);
        this.executor = makeExecutor(jankLocked);
    }

    public Queue(String name, Executor executor) {
        this.fallbackIfMain = /* anonymous class already generated */;
        this.name = name;
        queues.add(this);
        this.poolSize = 1;
        this.threadGroup = new ThreadGroup(name);
        this.executor = executor;
    }

    public void execute(Runnable runnable) {
        this.executor.execute(runnable);
    }

    private ThreadPoolExecutor makeExecutor(boolean jankLocked) {
        final boolean z = jankLocked;
        return new ThreadPoolExecutor(this.poolSize, this.poolSize, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
            private final AtomicInteger createdCount;

            {
                this.createdCount = new AtomicInteger(1);
            }

            public Thread newThread(final Runnable r) {
                Thread thread = new Thread(Queue.this.threadGroup, new Runnable() {
                    public void run() {
                        Process.setThreadPriority(10);
                        r.run();
                    }
                }, Queue.this + " #" + this.createdCount.getAndIncrement());
                thread.setPriority(1);
                return thread;
            }
        }) {
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                if (z) {
                    JankLock.global.blockUntilJankPermitted();
                }
            }
        };
    }

    public String toString() {
        return this.name;
    }
}
