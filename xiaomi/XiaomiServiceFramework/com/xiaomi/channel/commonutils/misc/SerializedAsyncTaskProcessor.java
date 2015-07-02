package com.xiaomi.channel.commonutils.misc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SerializedAsyncTaskProcessor {
    private static final int MSG_AFTER_EXECUTE = 1;
    private static final int MSG_BEFORE_EXECUTE = 0;
    private volatile SerializedAsyncTask mCurrentTask;
    private final boolean mIsDaemon;
    private Handler mMainThreadHandler;
    private ProcessPackageThread mProcessThread;
    private volatile boolean threadQuit;

    private class ProcessPackageThread extends Thread {
        private static final String THREAD_NAME = "PackageProcessor";
        private final LinkedBlockingQueue<SerializedAsyncTask> mTasks;

        public ProcessPackageThread() {
            super(THREAD_NAME);
            this.mTasks = new LinkedBlockingQueue();
        }

        public void insertTask(SerializedAsyncTask task) {
            this.mTasks.add(task);
        }

        public void run() {
            while (!SerializedAsyncTaskProcessor.this.threadQuit) {
                try {
                    SerializedAsyncTaskProcessor.this.mCurrentTask = (SerializedAsyncTask) this.mTasks.poll(1, TimeUnit.SECONDS);
                    if (SerializedAsyncTaskProcessor.this.mCurrentTask != null) {
                        SerializedAsyncTaskProcessor.this.mMainThreadHandler.sendMessage(SerializedAsyncTaskProcessor.this.mMainThreadHandler.obtainMessage(0, SerializedAsyncTaskProcessor.this.mCurrentTask));
                        SerializedAsyncTaskProcessor.this.mCurrentTask.process();
                        SerializedAsyncTaskProcessor.this.mMainThreadHandler.sendMessage(SerializedAsyncTaskProcessor.this.mMainThreadHandler.obtainMessage(SerializedAsyncTaskProcessor.MSG_AFTER_EXECUTE, SerializedAsyncTaskProcessor.this.mCurrentTask));
                    }
                } catch (Throwable e) {
                    MyLog.e(e);
                }
            }
        }
    }

    public static abstract class SerializedAsyncTask {
        public abstract void process();

        public void preProcess() {
        }

        public void postProcess() {
        }
    }

    public SerializedAsyncTaskProcessor() {
        this(false);
    }

    public SerializedAsyncTaskProcessor(boolean isDaemon) {
        this.mMainThreadHandler = null;
        this.threadQuit = false;
        this.mMainThreadHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                SerializedAsyncTask task = msg.obj;
                if (msg.what == 0) {
                    task.preProcess();
                } else if (msg.what == SerializedAsyncTaskProcessor.MSG_AFTER_EXECUTE) {
                    task.postProcess();
                }
                super.handleMessage(msg);
            }
        };
        this.mIsDaemon = isDaemon;
    }

    public synchronized void addNewTask(SerializedAsyncTask task) {
        if (this.mProcessThread == null) {
            this.mProcessThread = new ProcessPackageThread();
            this.mProcessThread.setDaemon(this.mIsDaemon);
            this.mProcessThread.start();
        }
        this.mProcessThread.insertTask(task);
    }

    public void clearTask() {
        if (this.mProcessThread != null) {
            this.mProcessThread.mTasks.clear();
        }
    }

    public void addNewTaskWithDelayed(final SerializedAsyncTask task, long delay) {
        this.mMainThreadHandler.postDelayed(new Runnable() {
            public void run() {
                SerializedAsyncTaskProcessor.this.addNewTask(task);
            }
        }, delay);
    }

    public SerializedAsyncTask getCurrentTask() {
        return this.mCurrentTask;
    }

    public void destroy() {
        this.threadQuit = true;
    }
}
