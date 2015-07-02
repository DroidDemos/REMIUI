package com.xiaomi.kenai.jbosh;

import java.util.concurrent.TimeUnit;

final class AttrMaxPause extends AbstractIntegerAttr {
    private AttrMaxPause(String val) throws BOSHException {
        super(val);
        checkMinValue(1);
    }

    static AttrMaxPause createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrMaxPause(str);
    }

    public int getInMilliseconds() {
        return (int) TimeUnit.MILLISECONDS.convert((long) intValue(), TimeUnit.SECONDS);
    }
}
