package com.xiaomi.kenai.jbosh;

final class AttrAck extends AbstractAttr<String> {
    private AttrAck(String val) throws BOSHException {
        super(val);
    }

    static AttrAck createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrAck(str);
    }
}
