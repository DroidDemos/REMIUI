package com.xiaomi.kenai.jbosh;

final class AttrInactivity extends AbstractIntegerAttr {
    private AttrInactivity(String val) throws BOSHException {
        super(val);
        checkMinValue(0);
    }

    static AttrInactivity createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrInactivity(str);
    }
}
