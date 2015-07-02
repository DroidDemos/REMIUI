package com.xiaomi.kenai.jbosh;

import java.util.concurrent.TimeUnit;

final class AttrPolling extends AbstractIntegerAttr {
    private AttrPolling(String str) throws BOSHException {
        super(str);
        checkMinValue(0);
    }

    static AttrPolling createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrPolling(str);
    }

    public int getInMilliseconds() {
        return (int) TimeUnit.MILLISECONDS.convert((long) intValue(), TimeUnit.SECONDS);
    }
}
