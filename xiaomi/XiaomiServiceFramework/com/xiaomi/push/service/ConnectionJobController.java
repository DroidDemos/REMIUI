package com.xiaomi.push.service;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMPushService.Job;
import java.util.ArrayList;
import java.util.List;

public class ConnectionJobController extends HandlerThread {
    private volatile boolean executing;
    private volatile long lastJob;
    private volatile Handler mHandler;
    private List<Pair<Job, Long>> mPendingJob;

    public ConnectionJobController(String name) {
        super(name);
        this.lastJob = 0;
        this.executing = false;
        this.mPendingJob = new ArrayList();
    }

    protected void onLooperPrepared() {
        this.mHandler = new Handler(getLooper()) {
            public void handleMessage(Message m) {
                ConnectionJobController.this.executing = true;
                ConnectionJobController.this.lastJob = System.currentTimeMillis();
                if (m.obj instanceof Job) {
                    ((Job) m.obj).run();
                }
                ConnectionJobController.this.executing = false;
            }
        };
        synchronized (this.mPendingJob) {
            for (Pair<Job, Long> jobDes : this.mPendingJob) {
                MyLog.warn("executing the pending job.");
                executeJobDelayed((Job) jobDes.first, ((Long) jobDes.second).longValue());
            }
            this.mPendingJob.clear();
        }
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public void removeJobs(int jobType) {
        if (this.mHandler != null) {
            this.mHandler.removeMessages(jobType);
        }
    }

    public void removeAllJobs() {
        for (int type = 1; type < 15; type++) {
            removeJobs(type);
        }
    }

    public void removeJobs(int jobType, Object o) {
        if (this.mHandler != null) {
            this.mHandler.removeMessages(jobType, o);
        }
    }

    public boolean hasJob(int jobType) {
        if (this.mHandler != null) {
            return this.mHandler.hasMessages(jobType);
        }
        return false;
    }

    public boolean hasJob(int jobType, Object o) {
        if (this.mHandler != null) {
            return this.mHandler.hasMessages(jobType, o);
        }
        return false;
    }

    public void executeJobDelayed(Job job, long delay) {
        synchronized (this.mPendingJob) {
            if (this.mHandler != null) {
                Message m = Message.obtain();
                m.what = job.type;
                m.obj = job;
                this.mHandler.sendMessageDelayed(m, delay);
            } else {
                MyLog.warn("the job is pended, the controller is not ready.");
                this.mPendingJob.add(new Pair(job, Long.valueOf(delay)));
            }
        }
    }

    public boolean isBlocked() {
        return this.executing && System.currentTimeMillis() - this.lastJob > 600000;
    }
}
