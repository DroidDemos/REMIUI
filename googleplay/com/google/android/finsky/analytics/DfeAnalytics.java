package com.google.android.finsky.analytics;

import android.os.Handler;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.Log.ClickLogEvent;
import com.google.android.finsky.protos.Log.LogRequest;
import com.google.android.finsky.protos.Log.LogResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.util.List;

public class DfeAnalytics implements Analytics {
    private static final boolean DFE_ADMOB_ENABLED;
    private static final int DISPATCH_PERIOD_MS;
    private static final int MAX_LOGS_BEFORE_FLUSH;
    private DfeApi mDfeApi;
    private final Handler mHandler;
    private final Runnable mLogFlusher;
    private List<ClickLogEvent> mPendingEvents;

    static {
        DISPATCH_PERIOD_MS = ((Integer) G.logsDispatchIntervalSeconds.get()).intValue() * 1000;
        MAX_LOGS_BEFORE_FLUSH = ((Integer) G.maxLogQueueSizeBeforeFlush.get()).intValue();
        DFE_ADMOB_ENABLED = ((Boolean) G.dfeLogsAdMobEnabled.get()).booleanValue();
    }

    public DfeAnalytics(Handler handler, DfeApi dfeApi) {
        this.mPendingEvents = Lists.newArrayList();
        this.mLogFlusher = new Runnable() {
            public void run() {
                DfeAnalytics.this.flushLogs();
            }
        };
        this.mHandler = handler;
        this.mDfeApi = dfeApi;
    }

    public void logAdMobPageView(String loggedUrl) {
        if (DFE_ADMOB_ENABLED) {
            if (FinskyLog.DEBUG) {
                FinskyLog.v("Logging *ADMOB* page view: loggedUrl=[%s]", loggedUrl);
            }
            ClickLogEvent event = new ClickLogEvent();
            event.eventTime = System.currentTimeMillis();
            event.hasEventTime = true;
            event.url = loggedUrl;
            event.hasUrl = true;
            this.mPendingEvents.add(event);
            scheduleFlush(true);
        }
    }

    private void scheduleFlush(boolean forceNow) {
        this.mHandler.removeCallbacks(this.mLogFlusher);
        if (forceNow || this.mPendingEvents.size() >= MAX_LOGS_BEFORE_FLUSH) {
            this.mHandler.post(this.mLogFlusher);
        } else {
            this.mHandler.postDelayed(this.mLogFlusher, (long) DISPATCH_PERIOD_MS);
        }
    }

    public void reset() {
        DfeApi oldApi = this.mDfeApi;
        this.mDfeApi = FinskyApp.get().getDfeApi();
        if (oldApi != null) {
            this.mPendingEvents.clear();
        } else {
            scheduleFlush(true);
        }
    }

    private void flushLogs() {
        if (this.mDfeApi != null) {
            final int currentLogCount = this.mPendingEvents.size();
            if (currentLogCount != 0) {
                LogRequest request = new LogRequest();
                request.clickEvent = (ClickLogEvent[]) this.mPendingEvents.toArray(new ClickLogEvent[0]);
                this.mPendingEvents.clear();
                this.mDfeApi.log(request, new Listener<LogResponse>() {
                    public void onResponse(LogResponse response) {
                        if (FinskyLog.DEBUG) {
                            FinskyLog.v("Logged %d analytics events successfully.", Integer.valueOf(currentLogCount));
                        }
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        FinskyLog.e("Failed to send %d events because of [%s]", Integer.valueOf(currentLogCount), error);
                    }
                });
            }
        }
    }
}
