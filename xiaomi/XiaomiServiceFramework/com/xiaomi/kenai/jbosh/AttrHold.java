package com.xiaomi.kenai.jbosh;

final class AttrHold extends AbstractIntegerAttr {
    private AttrHold(String val) throws BOSHException {
        super(val);
        checkMinValue(0);
    }

    static AttrHold createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrHold(str);
    }
}
