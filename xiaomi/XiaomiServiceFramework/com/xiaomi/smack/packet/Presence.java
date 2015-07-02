package com.xiaomi.smack.packet;

import android.os.Bundle;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.smack.util.StringUtils;

public class Presence extends Packet {
    private Mode mode;
    private int priority;
    private String status;
    private Type type;

    public enum Mode {
        chat,
        available,
        away,
        xa,
        dnd
    }

    public enum Type {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe
    }

    public Presence(Type type) {
        this.type = Type.available;
        this.status = null;
        this.priority = Integer.MIN_VALUE;
        this.mode = null;
        setType(type);
    }

    public Presence(Type type, String status, int priority, Mode mode) {
        this.type = Type.available;
        this.status = null;
        this.priority = Integer.MIN_VALUE;
        this.mode = null;
        setType(type);
        setStatus(status);
        setPriority(priority);
        setMode(mode);
    }

    public Presence(Bundle b) {
        super(b);
        this.type = Type.available;
        this.status = null;
        this.priority = Integer.MIN_VALUE;
        this.mode = null;
        if (b.containsKey(PushConstants.EXTRA_PRES_TYPE)) {
            this.type = Type.valueOf(b.getString(PushConstants.EXTRA_PRES_TYPE));
        }
        if (b.containsKey(PushConstants.EXTRA_PRES_STATUS)) {
            this.status = b.getString(PushConstants.EXTRA_PRES_STATUS);
        }
        if (b.containsKey(PushConstants.EXTRA_PRES_PRIORITY)) {
            this.priority = b.getInt(PushConstants.EXTRA_PRES_PRIORITY);
        }
        if (b.containsKey(PushConstants.EXTRA_PRES_MODE)) {
            this.mode = Mode.valueOf(b.getString(PushConstants.EXTRA_PRES_MODE));
        }
    }

    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        if (this.type != null) {
            bundle.putString(PushConstants.EXTRA_PRES_TYPE, this.type.toString());
        }
        if (this.status != null) {
            bundle.putString(PushConstants.EXTRA_PRES_STATUS, this.status);
        }
        if (this.priority != Integer.MIN_VALUE) {
            bundle.putInt(PushConstants.EXTRA_PRES_PRIORITY, this.priority);
        }
        if (!(this.mode == null || this.mode == Mode.available)) {
            bundle.putString(PushConstants.EXTRA_PRES_MODE, this.mode.toString());
        }
        return bundle;
    }

    public boolean isAvailable() {
        return this.type == Type.available;
    }

    public boolean isAway() {
        return this.type == Type.available && (this.mode == Mode.away || this.mode == Mode.xa || this.mode == Mode.dnd);
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        if (type == null) {
            throw new NullPointerException("Type cannot be null");
        }
        this.type = type;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        if (priority < -128 || priority > 128) {
            throw new IllegalArgumentException("Priority value " + priority + " is not valid. Valid range is -128 through 128.");
        }
        this.priority = priority;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String toXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<presence");
        if (getXmlns() != null) {
            buf.append(" xmlns=\"").append(getXmlns()).append("\"");
        }
        if (getPacketID() != null) {
            buf.append(" id=\"").append(getPacketID()).append("\"");
        }
        if (getTo() != null) {
            buf.append(" to=\"").append(StringUtils.escapeForXML(getTo())).append("\"");
        }
        if (getFrom() != null) {
            buf.append(" from=\"").append(StringUtils.escapeForXML(getFrom())).append("\"");
        }
        if (getChannelId() != null) {
            buf.append(" chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\"");
        }
        if (this.type != null) {
            buf.append(" type=\"").append(this.type).append("\"");
        }
        buf.append(">");
        if (this.status != null) {
            buf.append("<status>").append(StringUtils.escapeForXML(this.status)).append("</status>");
        }
        if (this.priority != Integer.MIN_VALUE) {
            buf.append("<priority>").append(this.priority).append("</priority>");
        }
        if (!(this.mode == null || this.mode == Mode.available)) {
            buf.append("<show>").append(this.mode).append("</show>");
        }
        buf.append(getExtensionsXML());
        XMPPError error = getError();
        if (error != null) {
            buf.append(error.toXML());
        }
        buf.append("</presence>");
        return buf.toString();
    }
}
