package com.google.android.volley.guava;

import android.net.TrafficStats;
import android.os.SystemClock;
import android.util.EventLog;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

public class NetworkStatsEntity extends HttpEntityWrapper {
    private final long mProcessingStartTime;
    private final long mResponseLatency;
    private final long mStartRx;
    private final long mStartTx;
    private final String mUa;
    private final int mUid;

    private class NetworkStatsInputStream extends FilterInputStream {
        public NetworkStatsInputStream(InputStream wrapped) {
            super(wrapped);
        }

        public void close() throws IOException {
            long processingTime;
            long tx;
            long rx;
            try {
                super.close();
                processingTime = SystemClock.elapsedRealtime() - NetworkStatsEntity.this.mProcessingStartTime;
                tx = TrafficStats.getUidTxBytes(NetworkStatsEntity.this.mUid);
                rx = TrafficStats.getUidRxBytes(NetworkStatsEntity.this.mUid);
                EventLog.writeEvent(52001, new Object[]{NetworkStatsEntity.this.mUa, Long.valueOf(NetworkStatsEntity.this.mResponseLatency), Long.valueOf(processingTime), Long.valueOf(tx - NetworkStatsEntity.this.mStartTx), Long.valueOf(rx - NetworkStatsEntity.this.mStartRx)});
            } catch (Throwable th) {
                processingTime = SystemClock.elapsedRealtime() - NetworkStatsEntity.this.mProcessingStartTime;
                tx = TrafficStats.getUidTxBytes(NetworkStatsEntity.this.mUid);
                rx = TrafficStats.getUidRxBytes(NetworkStatsEntity.this.mUid);
                EventLog.writeEvent(52001, new Object[]{NetworkStatsEntity.this.mUa, Long.valueOf(NetworkStatsEntity.this.mResponseLatency), Long.valueOf(processingTime), Long.valueOf(tx - NetworkStatsEntity.this.mStartTx), Long.valueOf(rx - NetworkStatsEntity.this.mStartRx)});
            }
        }
    }

    public NetworkStatsEntity(HttpEntity orig, String ua, int uid, long startTx, long startRx, long responseLatency, long processingStartTime) {
        super(orig);
        this.mUa = ua;
        this.mUid = uid;
        this.mStartTx = startTx;
        this.mStartRx = startRx;
        this.mResponseLatency = responseLatency;
        this.mProcessingStartTime = processingStartTime;
    }

    public InputStream getContent() throws IOException {
        return new NetworkStatsInputStream(super.getContent());
    }
}
