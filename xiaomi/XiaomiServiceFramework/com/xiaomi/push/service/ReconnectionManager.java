package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMPushService.ConnectJob;

public class ReconnectionManager {
    private static int MAX_RETRY_INTERVAL;
    private int attempts;
    private int curDelay;
    private long lastConnectTime;
    private XMPushService mPushService;

    static {
        MAX_RETRY_INTERVAL = 300;
    }

    public ReconnectionManager(XMPushService pushService) {
        this.attempts = 0;
        this.mPushService = pushService;
        this.curDelay = 10;
        this.lastConnectTime = 0;
    }

    public void onConnectSucceeded() {
        this.lastConnectTime = System.currentTimeMillis();
        this.mPushService.removeJobs(1);
        this.attempts = 0;
    }

    public void tryReconnect(boolean rightNow) {
        if (!this.mPushService.shouldReconnect()) {
            MyLog.v("should not reconnect as no client or network.");
        } else if (rightNow) {
            this.mPushService.removeJobs(1);
            r1 = this.mPushService;
            r3 = this.mPushService;
            r3.getClass();
            r1.executeJob(new ConnectJob());
            this.attempts++;
        } else if (!this.mPushService.hasJob(1)) {
            int delay = timeDelay();
            MyLog.warn("schedule reconnect in " + delay + "s");
            r1 = this.mPushService;
            r3 = this.mPushService;
            r3.getClass();
            r1.executeJobDelayed(new ConnectJob(), (long) (delay * 1000));
            this.attempts++;
        }
    }

    private int timeDelay() {
        int i = 40;
        if (this.attempts > 13) {
            return 300;
        }
        if (this.attempts > 7) {
            return 60;
        }
        if (this.attempts >= 1) {
            return 10;
        }
        if (this.lastConnectTime == 0) {
            return 0;
        }
        long interval = System.currentTimeMillis() - this.lastConnectTime;
        if (interval < 300000) {
            if (this.curDelay >= MAX_RETRY_INTERVAL) {
                return this.curDelay;
            }
            int delay = this.curDelay;
            this.curDelay = (int) (((double) this.curDelay) * 1.5d);
            return delay;
        } else if (interval < 900000) {
            if (this.curDelay < 40) {
                i = this.curDelay;
            }
            this.curDelay = i;
            return this.curDelay;
        } else if (interval < 1800000) {
            if (this.curDelay < 20) {
                i = this.curDelay;
            } else {
                i = 20;
            }
            this.curDelay = i;
            return this.curDelay;
        } else {
            this.curDelay = 10;
            return this.curDelay;
        }
    }
}
