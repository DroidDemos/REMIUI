package com.xiaomi.kenai.jbosh;

final class AttrSessionID extends AbstractAttr<String> {
    private AttrSessionID(String val) {
        super(val);
    }

    static AttrSessionID createFromString(String str) {
        return new AttrSessionID(str);
    }
}
