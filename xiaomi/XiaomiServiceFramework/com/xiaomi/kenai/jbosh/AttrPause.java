package com.xiaomi.kenai.jbosh;

import java.util.concurrent.TimeUnit;

final class AttrPause extends AbstractIntegerAttr {
    private AttrPause(String val) throws BOSHException {
        super(val);
        checkMinValue(1);
    }

    static AttrPause createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrPause(str);
    }

    public int getInMilliseconds() {
        return (int) TimeUnit.MILLISECONDS.convert((long) intValue(), TimeUnit.SECONDS);
    }
}
