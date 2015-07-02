package com.xiaomi.smack.packet;

import android.os.Bundle;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.smack.util.StringUtils;

public class IQ extends Packet {
    private Type type;

    public static class Type {
        public static final Type ERROR;
        public static final Type GET;
        public static final Type RESULT;
        public static final Type SET;
        private String value;

        static {
            GET = new Type("get");
            SET = new Type("set");
            RESULT = new Type("result");
            ERROR = new Type(Message.MSG_TYPE_ERROR);
        }

        public static Type fromString(String type) {
            if (type == null) {
                return null;
            }
            type = type.toLowerCase();
            if (GET.toString().equals(type)) {
                return GET;
            }
            if (SET.toString().equals(type)) {
                return SET;
            }
            if (ERROR.toString().equals(type)) {
                return ERROR;
            }
            if (RESULT.toString().equals(type)) {
                return RESULT;
            }
            return null;
        }

        private Type(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public IQ() {
        this.type = Type.GET;
    }

    public IQ(Bundle bundle) {
        super(bundle);
        this.type = Type.GET;
        if (bundle.containsKey(PushConstants.EXTRA_IQ_TYPE)) {
            this.type = Type.fromString(bundle.getString(PushConstants.EXTRA_IQ_TYPE));
        }
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        if (type == null) {
            this.type = Type.GET;
        } else {
            this.type = type;
        }
    }

    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        if (this.type != null) {
            bundle.putString(PushConstants.EXTRA_IQ_TYPE, this.type.toString());
        }
        return bundle;
    }

    public String toXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<iq ");
        if (getPacketID() != null) {
            buf.append("id=\"" + getPacketID() + "\" ");
        }
        if (getTo() != null) {
            buf.append("to=\"").append(StringUtils.escapeForXML(getTo())).append("\" ");
        }
        if (getFrom() != null) {
            buf.append("from=\"").append(StringUtils.escapeForXML(getFrom())).append("\" ");
        }
        if (getChannelId() != null) {
            buf.append("chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\" ");
        }
        if (this.type == null) {
            buf.append("type=\"get\">");
        } else {
            buf.append("type=\"").append(getType()).append("\">");
        }
        String queryXML = getChildElementXML();
        if (queryXML != null) {
            buf.append(queryXML);
        }
        buf.append(getExtensionsXML());
        XMPPError error = getError();
        if (error != null) {
            buf.append(error.toXML());
        }
        buf.append("</iq>");
        return buf.toString();
    }

    public String getChildElementXML() {
        return null;
    }

    public static IQ createResultIQ(IQ request) {
        if (request.getType() == Type.GET || request.getType() == Type.SET) {
            IQ result = new IQ() {
                public String getChildElementXML() {
                    return null;
                }
            };
            result.setType(Type.RESULT);
            result.setPacketID(request.getPacketID());
            result.setFrom(request.getTo());
            result.setTo(request.getFrom());
            return result;
        }
        throw new IllegalArgumentException("IQ must be of type 'set' or 'get'. Original IQ: " + request.toXML());
    }

    public static IQ createErrorResponse(final IQ request, XMPPError error) {
        if (request.getType() == Type.GET || request.getType() == Type.SET) {
            IQ result = new IQ() {
                public String getChildElementXML() {
                    return request.getChildElementXML();
                }
            };
            result.setType(Type.ERROR);
            result.setPacketID(request.getPacketID());
            result.setFrom(request.getTo());
            result.setTo(request.getFrom());
            result.setError(error);
            return result;
        }
        throw new IllegalArgumentException("IQ must be of type 'set' or 'get'. Original IQ: " + request.toXML());
    }
}
