package com.xiaomi.kenai.jbosh;

final class AttrRequests extends AbstractIntegerAttr {
    private AttrRequests(String val) throws BOSHException {
        super(val);
        checkMinValue(1);
    }

    static AttrRequests createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrRequests(str);
    }
}
