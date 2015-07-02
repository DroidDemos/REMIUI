package com.xiaomi.smack;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.CloudCoder;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.util.StringUtils;
import com.xiaomi.xmsf.push.service.Constants;
import java.util.HashMap;
import java.util.Map;

public class XMBinder {

    public class Bind extends Packet {
        public Bind(ClientLoginInfo info, String challenge, Connection connection) {
            Map<String, String> params = new HashMap();
            int connTryCount = connection.getConnTryTimes();
            params.put("challenge", challenge);
            params.put(PushServiceConstants.EXTRA_TOKEN, info.token);
            params.put("chid", info.chid);
            params.put(PushServiceConstants.EXTRA_FROM, info.userId);
            params.put(Constants.JSON_TAG_ID, getPacketID());
            params.put(PushServiceConstants.EXTRA_TO, PushServiceConstants.XMPP_SERVICE_NAME);
            if (info.kick) {
                params.put("kick", PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI);
            } else {
                params.put("kick", "0");
            }
            String pfStr = null;
            if (connection.getConnectTime() > 0) {
                pfStr = String.format("conn:%1$d,t:%2$d", new Object[]{Integer.valueOf(connTryCount), Long.valueOf(connection.getConnectTime())});
                params.put("pf", pfStr);
                connection.resetConnTryTimes();
                connection.resetConnectTime();
            }
            if (TextUtils.isEmpty(info.clientExtra)) {
                params.put("client_attrs", "");
            } else {
                params.put("client_attrs", info.clientExtra);
            }
            if (TextUtils.isEmpty(info.cloudExtra)) {
                params.put("cloud_attrs", "");
            } else {
                params.put("cloud_attrs", info.cloudExtra);
            }
            String sig = null;
            if (info.authMethod.equals("XIAOMI-PASS") || info.authMethod.equals("XMPUSH-PASS")) {
                sig = CloudCoder.generateSignature(info.authMethod, null, params, info.security);
            } else if (info.authMethod.equals("XIAOMI-SASL")) {
            }
            setChannelId(info.chid);
            setFrom(info.userId);
            setTo(PushServiceConstants.XMPP_SERVICE_NAME);
            CommonPacketExtension tokenExt = new CommonPacketExtension(PushServiceConstants.EXTRA_TOKEN, null, (String[]) null, (String[]) null);
            tokenExt.setText(info.token);
            addExtension(tokenExt);
            CommonPacketExtension kickExt = new CommonPacketExtension("kick", null, (String[]) null, (String[]) null);
            kickExt.setText(info.kick ? PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI : "0");
            addExtension(kickExt);
            CommonPacketExtension sigExt = new CommonPacketExtension("sig", null, (String[]) null, (String[]) null);
            sigExt.setText(sig);
            addExtension(sigExt);
            CommonPacketExtension methodExt = new CommonPacketExtension(PushServiceConstants.EXTRA_METHOD, null, (String[]) null, (String[]) null);
            if (TextUtils.isEmpty(info.authMethod)) {
                methodExt.setText("XIAOMI-SASL");
            } else {
                methodExt.setText(info.authMethod);
            }
            addExtension(methodExt);
            CommonPacketExtension cltAttrExt = new CommonPacketExtension("client_attrs", null, (String[]) null, (String[]) null);
            cltAttrExt.setText(info.clientExtra == null ? "" : StringUtils.escapeForXML(info.clientExtra));
            addExtension(cltAttrExt);
            CommonPacketExtension cldAttrExt = new CommonPacketExtension("cloud_attrs", null, (String[]) null, (String[]) null);
            cldAttrExt.setText(info.cloudExtra == null ? "" : StringUtils.escapeForXML(info.cloudExtra));
            addExtension(cldAttrExt);
            if (!TextUtils.isEmpty(pfStr)) {
                CommonPacketExtension connExt = new CommonPacketExtension("pf", null, (String[]) null, (String[]) null);
                connExt.setText(pfStr);
                addExtension(connExt);
            }
        }

        public String toXML() {
            StringBuilder buf = new StringBuilder();
            buf.append("<bind ");
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
                buf.append("chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\">");
            }
            if (getExtensions() != null) {
                for (CommonPacketExtension extension : getExtensions()) {
                    buf.append(extension.toXML());
                }
            }
            buf.append("</bind>");
            return buf.toString();
        }
    }

    public static class BindResult extends Packet {
        private Type type;

        public static class Type {
            public static final Type ERROR;
            public static final Type RESULT;
            private String value;

            static {
                RESULT = new Type("result");
                ERROR = new Type(Message.MSG_TYPE_ERROR);
            }

            public static Type fromString(String type) {
                if (type == null) {
                    return null;
                }
                type = type.toLowerCase();
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

        public void setType(Type type) {
            if (type == null) {
                this.type = Type.RESULT;
            } else {
                this.type = type;
            }
        }

        public Type getType() {
            return this.type;
        }

        public String toXML() {
            StringBuilder buf = new StringBuilder();
            buf.append("<bind ");
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
                buf.append(" chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\" ");
            }
            if (this.type == null) {
                buf.append("type=\"result\">");
            } else {
                buf.append("type=\"").append(getType()).append("\">");
            }
            if (getExtensions() != null) {
                for (CommonPacketExtension extension : getExtensions()) {
                    buf.append(extension.toXML());
                }
            }
            XMPPError error = getError();
            if (error != null) {
                buf.append(error.toXML());
            }
            buf.append("</bind>");
            return buf.toString();
        }
    }

    public void doBind(ClientLoginInfo info, String challenge, Connection connection) throws XMPPException {
        Bind bind = new Bind(info, challenge, connection);
        connection.sendPacket(bind);
        MyLog.warn("SMACK: bind id=" + bind.getPacketID());
    }
}
