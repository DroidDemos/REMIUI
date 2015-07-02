package com.google.android.play.analytics;

import android.util.Log;
import com.google.android.play.analytics.ClientAnalytics.LogEvent;
import com.google.android.play.analytics.ClientAnalytics.LogEventKeyValues;
import java.lang.reflect.Array;

public class ProtoCache {
    private static ProtoCache INSTANCE;
    private static final String TAG;
    private final ElementCache<LogEvent> mCacheLogEvent;
    private final ElementCache<LogEventKeyValues> mCacheLogEventKeyValues;

    private static class ElementCache<T> {
        private T[] mCache;
        Class<?> mClazz;
        private int mCount;
        private int mHighWater;
        private final int mLimit;

        public ElementCache(Class<?> clazz, int limit) {
            this.mLimit = limit;
            this.mCount = 0;
            this.mHighWater = 0;
            this.mCache = (Object[]) Array.newInstance(clazz, limit);
            this.mClazz = clazz;
        }

        public T obtain() {
            T result = null;
            synchronized (this) {
                if (this.mCount > 0) {
                    this.mCount--;
                    result = this.mCache[this.mCount];
                    this.mCache[this.mCount] = null;
                } else {
                    try {
                        result = this.mClazz.newInstance();
                    } catch (Exception e) {
                        Log.wtf(ProtoCache.TAG, "Exception from mClazz.newInstance ", e);
                    }
                }
            }
            return result;
        }

        public void recycle(T element) {
            synchronized (this) {
                if (this.mCount < this.mLimit) {
                    this.mCache[this.mCount] = element;
                    this.mCount++;
                    if (this.mCount > this.mHighWater) {
                        this.mHighWater = this.mCount;
                    }
                }
            }
        }
    }

    static {
        TAG = ProtoCache.class.getSimpleName();
        INSTANCE = null;
    }

    public static synchronized ProtoCache getInstance() {
        ProtoCache protoCache;
        synchronized (ProtoCache.class) {
            if (INSTANCE == null) {
                INSTANCE = new ProtoCache();
            }
            protoCache = INSTANCE;
        }
        return protoCache;
    }

    private ProtoCache() {
        this.mCacheLogEvent = new ElementCache(LogEvent.class, 60);
        this.mCacheLogEventKeyValues = new ElementCache(LogEventKeyValues.class, 50);
    }

    public LogEvent obtainEvent() {
        return (LogEvent) this.mCacheLogEvent.obtain();
    }

    public void recycle(LogEvent event) {
        LogEventKeyValues[] keyValues = event.value;
        for (LogEventKeyValues recycle : keyValues) {
            recycle(recycle);
        }
        event.clear();
        this.mCacheLogEvent.recycle(event);
    }

    public LogEventKeyValues obtainKeyValue() {
        return (LogEventKeyValues) this.mCacheLogEventKeyValues.obtain();
    }

    private void recycle(LogEventKeyValues keyValue) {
        keyValue.clear();
        this.mCacheLogEventKeyValues.recycle(keyValue);
    }
}
