package com.xiaomi.kenai.jbosh;

final class AttrWait extends AbstractIntegerAttr {
    private AttrWait(String val) throws BOSHException {
        super(val);
        checkMinValue(1);
    }

    static AttrWait createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrWait(str);
    }
}
