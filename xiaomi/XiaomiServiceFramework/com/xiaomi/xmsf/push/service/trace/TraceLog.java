package com.xiaomi.xmsf.push.service.trace;

import com.xiaomi.xmsf.push.service.data.BusinessMessage;

public class TraceLog {
    public long adId;
    public String content;
    public int showType;

    public TraceLog(BusinessMessage cell) {
        this.adId = cell.id;
        this.showType = cell.showType;
    }
}
