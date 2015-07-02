package com.xiaomi.activate;

public final class ActivateInfo {
    public volatile int autoActivateState;
    public volatile String hashedDeviceId;
    public volatile String hashedSimId;
    public volatile String host;
    public volatile String inputPhone;
    public volatile boolean insertedToServer;
    public volatile long nextAutoActivationTime;
    public volatile String phone;
    public volatile String pseudoSimId;
    public volatile String simPassToken;
    public volatile String simUserId;
    public volatile String traceId;
    public volatile String vkey1;
    public volatile String vkey2;
    public volatile String xiaomiPassToken;
    public volatile String xiaomiUserId;

    public static class AutoActivateState {
        public static int FAILED;
        public static int NONE;
        public static int RUNNING;

        static {
            NONE = 0;
            RUNNING = 1;
            FAILED = 2;
        }
    }

    public synchronized boolean isActivated() {
        boolean z;
        z = (this.hashedSimId == null || this.phone == null || this.simUserId == null || this.simPassToken == null) ? false : true;
        return z;
    }
}
