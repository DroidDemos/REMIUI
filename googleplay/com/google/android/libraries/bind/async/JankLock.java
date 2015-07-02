package com.google.android.libraries.bind.async;

import android.os.Build.VERSION;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class JankLock implements Executor {
    private static final boolean DISABLED;
    public static final JankLock global;
    private boolean isPaused;
    private final ReentrantLock pauseLock;
    private final DelayedRunnable resumeRunnable;
    private final Condition unpaused;

    static {
        DISABLED = VERSION.SDK_INT >= 21;
        global = new JankLock();
    }

    public JankLock() {
        this.pauseLock = new ReentrantLock();
        this.unpaused = this.pauseLock.newCondition();
        this.resumeRunnable = new DelayedRunnable(AsyncUtil.mainThreadHandler(), new Runnable() {
            public void run() {
                JankLock.this.resume();
            }
        });
    }

    public void blockUntilJankPermitted() {
        if (!DISABLED && !AsyncUtil.isMainThread()) {
            this.pauseLock.lock();
            while (this.isPaused) {
                try {
                    this.unpaused.awaitUninterruptibly();
                } finally {
                    this.pauseLock.unlock();
                }
            }
        }
    }

    public void pause() {
        if (!DISABLED) {
            AsyncUtil.checkMainThread();
            this.pauseLock.lock();
            try {
                this.isPaused = true;
            } finally {
                this.pauseLock.unlock();
            }
        }
    }

    public void resume() {
        if (!DISABLED) {
            AsyncUtil.checkMainThread();
            this.pauseLock.lock();
            try {
                this.isPaused = false;
                this.unpaused.signalAll();
            } finally {
                this.pauseLock.unlock();
            }
        }
    }

    public boolean isPaused() {
        if (DISABLED) {
            return false;
        }
        this.pauseLock.lock();
        try {
            boolean z = this.isPaused;
            return z;
        } finally {
            this.pauseLock.unlock();
        }
    }

    public void pauseTemporarily(long milliseconds) {
        if (!DISABLED) {
            pause();
            this.resumeRunnable.postDelayed(milliseconds, 1);
        }
    }

    public void executeOn(final Runnable runnable, final Executor executor, boolean executeOnSameThreadIfUnpaused) {
        final Executor blockingQueue = Queues.BIND_CPU;
        Runnable blockingRunnable = new Runnable() {
            final Runnable deliveringRunnable;
            final Runnable outerRunnable;

            public void run() {
                executor.execute(this.deliveringRunnable);
            }
        };
        if (isPaused()) {
            blockingQueue.execute(blockingRunnable);
        } else if (executeOnSameThreadIfUnpaused) {
            runnable.run();
        } else {
            blockingRunnable.run();
        }
    }

    public void execute(Runnable runnable) {
        executeOn(runnable, AsyncUtil.mainThreadExecutor(), false);
    }
}
