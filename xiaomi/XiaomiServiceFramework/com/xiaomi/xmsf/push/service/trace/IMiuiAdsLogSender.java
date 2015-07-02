package com.xiaomi.xmsf.push.service.trace;

public interface IMiuiAdsLogSender {
    void clickTrace(TraceLog traceLog);

    void receiveTrace(TraceLog traceLog);

    void release();

    void removeTrace(TraceLog traceLog);
}
