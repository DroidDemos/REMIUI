package com.xiaomi.xmsf.push.service.trace;

import com.xiaomi.xmsf.push.service.trace.TraceLogCache.CacheLine;

public interface IAdsTraceListener {
    void onAccountChanged();

    void onNetStateChanged();

    void onTraceTaskFinished(Integer num, CacheLine cacheLine);
}
