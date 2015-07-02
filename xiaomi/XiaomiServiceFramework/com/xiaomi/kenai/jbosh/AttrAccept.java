package com.xiaomi.kenai.jbosh;

final class AttrAccept extends AbstractAttr<String> {
    private final String[] encodings;

    private AttrAccept(String val) {
        super(val);
        this.encodings = val.split("[\\s,]+");
    }

    static AttrAccept createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrAccept(str);
    }

    boolean isAccepted(String name) {
        for (String str : this.encodings) {
            if (str.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
