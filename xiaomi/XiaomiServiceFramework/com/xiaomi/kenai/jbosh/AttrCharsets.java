package com.xiaomi.kenai.jbosh;

final class AttrCharsets extends AbstractAttr<String> {
    private final String[] charsets;

    private AttrCharsets(String val) {
        super(val);
        this.charsets = val.split("\\ +");
    }

    static AttrCharsets createFromString(String str) {
        if (str == null) {
            return null;
        }
        return new AttrCharsets(str);
    }

    boolean isAccepted(String name) {
        for (String str : this.charsets) {
            if (str.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
