package com.xiaomi.kenai.jbosh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;

public final class BOSHClientConnEvent extends EventObject {
    private static final long serialVersionUID = 1;
    private final Throwable cause;
    private final boolean connected;
    private final List<ComposableBody> requests;

    private BOSHClientConnEvent(BOSHClient source, boolean cConnected, List<ComposableBody> cRequests, Throwable cCause) {
        super(source);
        this.connected = cConnected;
        this.cause = cCause;
        if (this.connected) {
            if (cCause != null) {
                throw new IllegalStateException("Cannot be connected and have a cause");
            } else if (cRequests != null && cRequests.size() > 0) {
                throw new IllegalStateException("Cannot be connected and have outstanding requests");
            }
        }
        if (cRequests == null) {
            this.requests = Collections.emptyList();
        } else {
            this.requests = Collections.unmodifiableList(new ArrayList(cRequests));
        }
    }

    static BOSHClientConnEvent createConnectionEstablishedEvent(BOSHClient source) {
        return new BOSHClientConnEvent(source, true, null, null);
    }

    static BOSHClientConnEvent createConnectionClosedEvent(BOSHClient source) {
        return new BOSHClientConnEvent(source, false, null, null);
    }

    static BOSHClientConnEvent createConnectionClosedOnErrorEvent(BOSHClient source, List<ComposableBody> outstanding, Throwable cause) {
        return new BOSHClientConnEvent(source, false, outstanding, cause);
    }

    public BOSHClient getBOSHClient() {
        return (BOSHClient) getSource();
    }

    public boolean isConnected() {
        return this.connected;
    }

    public boolean isError() {
        return this.cause != null;
    }

    public Throwable getCause() {
        return this.cause;
    }

    public List<ComposableBody> getOutstandingRequests() {
        return this.requests;
    }
}
