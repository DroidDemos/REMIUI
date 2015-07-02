package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import com.xiaomi.push.service.PushConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XMPPError {
    private List<CommonPacketExtension> applicationExtensions;
    private int code;
    private String condition;
    private String message;
    private String reason;
    private String type;

    public static class Condition {
        public static final Condition bad_request;
        public static final Condition conflict;
        public static final Condition feature_not_implemented;
        public static final Condition forbidden;
        public static final Condition gone;
        public static final Condition interna_server_error;
        public static final Condition item_not_found;
        public static final Condition jid_malformed;
        public static final Condition no_acceptable;
        public static final Condition not_allowed;
        public static final Condition not_authorized;
        public static final Condition payment_required;
        public static final Condition recipient_unavailable;
        public static final Condition redirect;
        public static final Condition registration_required;
        public static final Condition remote_server_error;
        public static final Condition remote_server_not_found;
        public static final Condition remote_server_timeout;
        public static final Condition request_timeout;
        public static final Condition resource_constraint;
        public static final Condition service_unavailable;
        public static final Condition subscription_required;
        public static final Condition undefined_condition;
        public static final Condition unexpected_request;
        private String value;

        static {
            interna_server_error = new Condition("internal-server-error");
            forbidden = new Condition("forbidden");
            bad_request = new Condition("bad-request");
            conflict = new Condition("conflict");
            feature_not_implemented = new Condition("feature-not-implemented");
            gone = new Condition("gone");
            item_not_found = new Condition("item-not-found");
            jid_malformed = new Condition("jid-malformed");
            no_acceptable = new Condition("not-acceptable");
            not_allowed = new Condition("not-allowed");
            not_authorized = new Condition("not-authorized");
            payment_required = new Condition("payment-required");
            recipient_unavailable = new Condition("recipient-unavailable");
            redirect = new Condition("redirect");
            registration_required = new Condition("registration-required");
            remote_server_error = new Condition("remote-server-error");
            remote_server_not_found = new Condition("remote-server-not-found");
            remote_server_timeout = new Condition("remote-server-timeout");
            resource_constraint = new Condition("resource-constraint");
            service_unavailable = new Condition("service-unavailable");
            subscription_required = new Condition("subscription-required");
            undefined_condition = new Condition("undefined-condition");
            unexpected_request = new Condition("unexpected-request");
            request_timeout = new Condition("request-timeout");
        }

        public Condition(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public XMPPError(Condition condition) {
        this.applicationExtensions = null;
        init(condition);
        this.message = null;
    }

    public XMPPError(Condition condition, String messageText) {
        this.applicationExtensions = null;
        init(condition);
        this.message = messageText;
    }

    public XMPPError(int code) {
        this.applicationExtensions = null;
        this.code = code;
        this.message = null;
    }

    public XMPPError(int code, String message) {
        this.applicationExtensions = null;
        this.code = code;
        this.message = message;
    }

    public XMPPError(int code, String type, String reason, String condition, String message, List<CommonPacketExtension> extension) {
        this.applicationExtensions = null;
        this.code = code;
        this.type = type;
        this.reason = reason;
        this.condition = condition;
        this.message = message;
        this.applicationExtensions = extension;
    }

    public XMPPError(Bundle bundle) {
        this.applicationExtensions = null;
        this.code = bundle.getInt(PushConstants.EXTRA_ERROR_CODE);
        if (bundle.containsKey(PushConstants.EXTRA_ERROR_TYPE)) {
            this.type = bundle.getString(PushConstants.EXTRA_ERROR_TYPE);
        }
        this.condition = bundle.getString(PushConstants.EXTRA_ERROR_CONDITION);
        this.reason = bundle.getString(PushConstants.EXTRA_ERROR_REASON);
        this.message = bundle.getString(PushConstants.EXTRA_ERROR_MESSAGE);
        Parcelable[] extBundles = bundle.getParcelableArray(PushConstants.EXTRA_EXTENSIONS);
        if (extBundles != null) {
            this.applicationExtensions = new ArrayList(extBundles.length);
            for (Parcelable b : extBundles) {
                CommonPacketExtension ext = CommonPacketExtension.parseFromBundle((Bundle) b);
                if (ext != null) {
                    this.applicationExtensions.add(ext);
                }
            }
        }
    }

    private void init(Condition condition) {
        this.condition = condition.value;
    }

    public String getCondition() {
        return this.condition;
    }

    public String getReason() {
        return this.reason;
    }

    public String getType() {
        return this.type;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (this.type != null) {
            bundle.putString(PushConstants.EXTRA_ERROR_TYPE, this.type);
        }
        bundle.putInt(PushConstants.EXTRA_ERROR_CODE, this.code);
        if (this.reason != null) {
            bundle.putString(PushConstants.EXTRA_ERROR_REASON, this.reason);
        }
        if (this.condition != null) {
            bundle.putString(PushConstants.EXTRA_ERROR_CONDITION, this.condition);
        }
        if (this.message != null) {
            bundle.putString(PushConstants.EXTRA_ERROR_MESSAGE, this.message);
        }
        if (this.applicationExtensions != null) {
            Bundle[] extBundle = new Bundle[this.applicationExtensions.size()];
            int i = 0;
            for (CommonPacketExtension ext : this.applicationExtensions) {
                Bundle subBundle = ext.toBundle();
                if (subBundle != null) {
                    int i2 = i + 1;
                    extBundle[i] = subBundle;
                    i = i2;
                }
            }
            bundle.putParcelableArray(PushConstants.EXTRA_EXTENSIONS, extBundle);
        }
        return bundle;
    }

    public String toXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<error code=\"").append(this.code).append("\"");
        if (this.type != null) {
            buf.append(" type=\"");
            buf.append(this.type);
            buf.append("\"");
        }
        if (this.reason != null) {
            buf.append(" reason=\"");
            buf.append(this.reason);
            buf.append("\"");
        }
        buf.append(">");
        if (this.condition != null) {
            buf.append("<").append(this.condition);
            buf.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if (this.message != null) {
            buf.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            buf.append(this.message);
            buf.append("</text>");
        }
        for (CommonPacketExtension element : getExtensions()) {
            buf.append(element.toXML());
        }
        buf.append("</error>");
        return buf.toString();
    }

    public String toString() {
        StringBuilder txt = new StringBuilder();
        if (this.condition != null) {
            txt.append(this.condition);
        }
        txt.append("(").append(this.code).append(")");
        if (this.message != null) {
            txt.append(" ").append(this.message);
        }
        return txt.toString();
    }

    public synchronized List<CommonPacketExtension> getExtensions() {
        List<CommonPacketExtension> emptyList;
        if (this.applicationExtensions == null) {
            emptyList = Collections.emptyList();
        } else {
            emptyList = Collections.unmodifiableList(this.applicationExtensions);
        }
        return emptyList;
    }

    public synchronized PacketExtension getExtension(String elementName, String namespace) {
        PacketExtension packetExtension;
        if (this.applicationExtensions == null || elementName == null || namespace == null) {
            packetExtension = null;
        } else {
            for (CommonPacketExtension ext : this.applicationExtensions) {
                if (elementName.equals(ext.getElementName()) && namespace.equals(ext.getNamespace())) {
                    break;
                }
            }
            packetExtension = null;
        }
        return packetExtension;
    }

    public synchronized void addExtension(CommonPacketExtension extension) {
        if (this.applicationExtensions == null) {
            this.applicationExtensions = new ArrayList();
        }
        this.applicationExtensions.add(extension);
    }

    public synchronized void setExtension(List<CommonPacketExtension> extension) {
        this.applicationExtensions = extension;
    }
}
