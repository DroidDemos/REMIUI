package com.xiaomi.kenai.jbosh;

import java.util.EventObject;

public final class BOSHMessageEvent extends EventObject {
    private static final long serialVersionUID = 1;
    private final AbstractBody body;

    private BOSHMessageEvent(Object source, AbstractBody cBody) {
        super(source);
        if (cBody == null) {
            throw new IllegalArgumentException("message body may not be null");
        }
        this.body = cBody;
    }

    static BOSHMessageEvent createRequestSentEvent(BOSHClient source, AbstractBody body) {
        return new BOSHMessageEvent(source, body);
    }

    static BOSHMessageEvent createResponseReceivedEvent(BOSHClient source, AbstractBody body) {
        return new BOSHMessageEvent(source, body);
    }

    public AbstractBody getBody() {
        return this.body;
    }
}
