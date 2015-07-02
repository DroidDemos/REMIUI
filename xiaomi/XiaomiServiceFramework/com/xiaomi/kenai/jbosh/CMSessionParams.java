package com.xiaomi.kenai.jbosh;

final class CMSessionParams {
    private final AttrAccept accept;
    private final AttrAck ack;
    private final boolean ackingRequests;
    private final AttrCharsets charsets;
    private final AttrHold hold;
    private final AttrInactivity inactivity;
    private final AttrMaxPause maxPause;
    private final AttrPolling polling;
    private final AttrRequests requests;
    private final AttrSessionID sid;
    private final AttrVersion ver;
    private final AttrWait wait;

    private CMSessionParams(AttrSessionID aSid, AttrWait aWait, AttrVersion aVer, AttrPolling aPolling, AttrInactivity aInactivity, AttrRequests aRequests, AttrHold aHold, AttrAccept aAccept, AttrMaxPause aMaxPause, AttrAck aAck, AttrCharsets aCharsets, boolean amAckingRequests) {
        this.sid = aSid;
        this.wait = aWait;
        this.ver = aVer;
        this.polling = aPolling;
        this.inactivity = aInactivity;
        this.requests = aRequests;
        this.hold = aHold;
        this.accept = aAccept;
        this.maxPause = aMaxPause;
        this.ack = aAck;
        this.charsets = aCharsets;
        this.ackingRequests = amAckingRequests;
    }

    static CMSessionParams fromSessionInit(AbstractBody req, AbstractBody resp) throws BOSHException, InterruptedException {
        AttrAck aAck = AttrAck.createFromString(resp.getAttribute(Attributes.ACK));
        boolean acking = aAck != null && ((String) aAck.getValue()).equals(req.getAttribute(Attributes.RID));
        return new CMSessionParams(AttrSessionID.createFromString(getRequiredAttribute(resp, Attributes.SID)), AttrWait.createFromString(getRequiredAttribute(resp, Attributes.WAIT)), AttrVersion.createFromString(resp.getAttribute(Attributes.VER)), AttrPolling.createFromString(resp.getAttribute(Attributes.POLLING)), AttrInactivity.createFromString(resp.getAttribute(Attributes.INACTIVITY)), AttrRequests.createFromString(resp.getAttribute(Attributes.REQUESTS)), AttrHold.createFromString(resp.getAttribute(Attributes.HOLD)), AttrAccept.createFromString(resp.getAttribute(Attributes.ACCEPT)), AttrMaxPause.createFromString(resp.getAttribute(Attributes.MAXPAUSE)), aAck, AttrCharsets.createFromString(resp.getAttribute(Attributes.CHARSETS)), acking);
    }

    private static String getRequiredAttribute(AbstractBody body, BodyQName name) throws BOSHException {
        String attrStr = body.getAttribute(name);
        if (attrStr != null) {
            return attrStr;
        }
        throw new BOSHException("Connection Manager session creation response did not include required '" + name.getLocalPart() + "' attribute");
    }

    AttrSessionID getSessionID() {
        return this.sid;
    }

    AttrWait getWait() {
        return this.wait;
    }

    AttrVersion getVersion() {
        return this.ver;
    }

    AttrPolling getPollingInterval() {
        return this.polling;
    }

    AttrInactivity getInactivityPeriod() {
        return this.inactivity;
    }

    AttrRequests getRequests() {
        return this.requests;
    }

    AttrHold getHold() {
        return this.hold;
    }

    AttrAccept getAccept() {
        return this.accept;
    }

    AttrMaxPause getMaxPause() {
        return this.maxPause;
    }

    AttrAck getAck() {
        return this.ack;
    }

    AttrCharsets getCharsets() {
        return this.charsets;
    }

    boolean isAckingRequests() {
        return this.ackingRequests;
    }
}
