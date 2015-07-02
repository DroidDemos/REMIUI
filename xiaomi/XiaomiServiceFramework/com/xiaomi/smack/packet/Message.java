package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.smack.util.StringUtils;

public class Message extends Packet {
    public static final String MSG_TYPE_CHAT = "chat";
    public static final String MSG_TYPE_ERROR = "error";
    public static final String MSG_TYPE_GROUPCHAT = "groupchat";
    public static final String MSG_TYPE_HEADLINE = "hearline";
    public static final String MSG_TYPE_NORMAL = "normal";
    public static final String MSG_TYPE_PPL = "ppl";
    private String fseq;
    private String language;
    private String mAppId;
    private String mBody;
    private String mBodyEncoding;
    private boolean mEncrypted;
    private String mSubject;
    private boolean mTransient;
    private String mseq;
    private String seq;
    private String status;
    private String thread;
    private String type;

    public Message() {
        this.type = null;
        this.thread = null;
        this.mTransient = false;
        this.seq = "";
        this.mseq = "";
        this.fseq = "";
        this.status = "";
        this.mEncrypted = false;
    }

    public Message(String to) {
        this.type = null;
        this.thread = null;
        this.mTransient = false;
        this.seq = "";
        this.mseq = "";
        this.fseq = "";
        this.status = "";
        this.mEncrypted = false;
        setTo(to);
    }

    public Message(String to, String type) {
        this.type = null;
        this.thread = null;
        this.mTransient = false;
        this.seq = "";
        this.mseq = "";
        this.fseq = "";
        this.status = "";
        this.mEncrypted = false;
        setTo(to);
        this.type = type;
    }

    public Message(Bundle bundle) {
        super(bundle);
        this.type = null;
        this.thread = null;
        this.mTransient = false;
        this.seq = "";
        this.mseq = "";
        this.fseq = "";
        this.status = "";
        this.mEncrypted = false;
        this.type = bundle.getString(PushConstants.EXTRA_MESSAGE_TYPE);
        this.language = bundle.getString(PushConstants.EXTRA_MESSAGE_LANGUAGE);
        this.thread = bundle.getString(PushConstants.EXTRA_MESSAGE_THREAD);
        this.mSubject = bundle.getString(PushConstants.EXTRA_MESSAGE_SUBJECT);
        this.mBody = bundle.getString(PushConstants.EXTRA_MESSAGE_BODY);
        this.mBodyEncoding = bundle.getString(PushConstants.EXTRA_BODY_ENCODE);
        this.mAppId = bundle.getString(PushConstants.EXTRA_MESSAGE_APPID);
        this.mTransient = bundle.getBoolean(PushConstants.EXTRA_MESSAGE_TRANSIENT, false);
        this.mEncrypted = bundle.getBoolean(PushConstants.EXTRA_MESSAGE_ENCRYPT, false);
        this.seq = bundle.getString(PushConstants.EXTRA_MESSAGE_SEQ);
        this.mseq = bundle.getString(PushConstants.EXTRA_MESSAGE_MSEQ);
        this.fseq = bundle.getString(PushConstants.EXTRA_MESSAGE_FSEQ);
        this.status = bundle.getString(PushConstants.EXTRA_MESSAGE_STATUS);
    }

    public String getType() {
        return this.type;
    }

    public void setIsTransient(boolean isTransient) {
        this.mTransient = isTransient;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public void setAppId(String appId) {
        this.mAppId = appId;
    }

    public String getSeq() {
        return this.seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getMSeq() {
        return this.mseq;
    }

    public void setMSeq(String mseq) {
        this.mseq = mseq;
    }

    public String getFSeq() {
        return this.fseq;
    }

    public void setFSeq(String fseq) {
        this.fseq = fseq;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEncrypted(boolean encrypted) {
        this.mEncrypted = encrypted;
    }

    public boolean getEncrypted() {
        return this.mEncrypted;
    }

    public String getSubject() {
        return this.mSubject;
    }

    public void setSubject(String subject) {
        this.mSubject = subject;
    }

    public String getBody() {
        return this.mBody;
    }

    public String getBodyEncoding() {
        return this.mBodyEncoding;
    }

    public void setBody(String body) {
        this.mBody = body;
    }

    public void setBody(String body, String encoding) {
        this.mBody = body;
        this.mBodyEncoding = encoding;
    }

    public String getThread() {
        return this.thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        if (!TextUtils.isEmpty(this.type)) {
            bundle.putString(PushConstants.EXTRA_MESSAGE_TYPE, this.type);
        }
        if (this.language != null) {
            bundle.putString(PushConstants.EXTRA_MESSAGE_LANGUAGE, this.language);
        }
        if (this.mSubject != null) {
            bundle.putString(PushConstants.EXTRA_MESSAGE_SUBJECT, this.mSubject);
        }
        if (this.mBody != null) {
            bundle.putString(PushConstants.EXTRA_MESSAGE_BODY, this.mBody);
        }
        if (!TextUtils.isEmpty(this.mBodyEncoding)) {
            bundle.putString(PushConstants.EXTRA_BODY_ENCODE, this.mBodyEncoding);
        }
        if (this.thread != null) {
            bundle.putString(PushConstants.EXTRA_MESSAGE_THREAD, this.thread);
        }
        if (this.mAppId != null) {
            bundle.putString(PushConstants.EXTRA_MESSAGE_APPID, this.mAppId);
        }
        if (this.mTransient) {
            bundle.putBoolean(PushConstants.EXTRA_MESSAGE_TRANSIENT, true);
        }
        if (!TextUtils.isEmpty(this.seq)) {
            bundle.putString(PushConstants.EXTRA_MESSAGE_SEQ, this.seq);
        }
        if (!TextUtils.isEmpty(this.mseq)) {
            bundle.putString(PushConstants.EXTRA_MESSAGE_MSEQ, this.mseq);
        }
        if (!TextUtils.isEmpty(this.fseq)) {
            bundle.putString(PushConstants.EXTRA_MESSAGE_FSEQ, this.fseq);
        }
        if (this.mEncrypted) {
            bundle.putBoolean(PushConstants.EXTRA_MESSAGE_ENCRYPT, true);
        }
        if (!TextUtils.isEmpty(this.status)) {
            bundle.putString(PushConstants.EXTRA_MESSAGE_STATUS, this.status);
        }
        return bundle;
    }

    public String toXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<message");
        if (getXmlns() != null) {
            buf.append(" xmlns=\"").append(getXmlns()).append("\"");
        }
        if (this.language != null) {
            buf.append(" xml:lang=\"").append(getLanguage()).append("\"");
        }
        if (getPacketID() != null) {
            buf.append(" id=\"").append(getPacketID()).append("\"");
        }
        if (getTo() != null) {
            buf.append(" to=\"").append(StringUtils.escapeForXML(getTo())).append("\"");
        }
        if (!TextUtils.isEmpty(getSeq())) {
            buf.append(" seq=\"").append(getSeq()).append("\"");
        }
        if (!TextUtils.isEmpty(getMSeq())) {
            buf.append(" mseq=\"").append(getMSeq()).append("\"");
        }
        if (!TextUtils.isEmpty(getFSeq())) {
            buf.append(" fseq=\"").append(getFSeq()).append("\"");
        }
        if (!TextUtils.isEmpty(getStatus())) {
            buf.append(" status=\"").append(getStatus()).append("\"");
        }
        if (getFrom() != null) {
            buf.append(" from=\"").append(StringUtils.escapeForXML(getFrom())).append("\"");
        }
        if (getChannelId() != null) {
            buf.append(" chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\"");
        }
        if (this.mTransient) {
            buf.append(" transient=\"true\"");
        }
        if (!TextUtils.isEmpty(this.mAppId)) {
            buf.append(" appid=\"").append(getAppId()).append("\"");
        }
        if (!TextUtils.isEmpty(this.type)) {
            buf.append(" type=\"").append(this.type).append("\"");
        }
        if (this.mEncrypted) {
            buf.append(" s=\"1\"");
        }
        buf.append(">");
        if (this.mSubject != null) {
            buf.append("<subject>").append(StringUtils.escapeForXML(this.mSubject));
            buf.append("</subject>");
        }
        if (this.mBody != null) {
            buf.append("<body");
            if (!TextUtils.isEmpty(this.mBodyEncoding)) {
                buf.append(" encode=\"").append(this.mBodyEncoding).append("\"");
            }
            buf.append(">").append(StringUtils.escapeForXML(this.mBody)).append("</body>");
        }
        if (this.thread != null) {
            buf.append("<thread>").append(this.thread).append("</thread>");
        }
        if (MSG_TYPE_ERROR.equalsIgnoreCase(this.type)) {
            XMPPError error = getError();
            if (error != null) {
                buf.append(error.toXML());
            }
        }
        buf.append(getExtensionsXML());
        buf.append("</message>");
        return buf.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        if (!super.equals(message)) {
            return false;
        }
        if (this.mBody != null) {
            if (!this.mBody.equals(message.mBody)) {
                return false;
            }
        } else if (message.mBody != null) {
            return false;
        }
        if (this.language != null) {
            if (!this.language.equals(message.language)) {
                return false;
            }
        } else if (message.language != null) {
            return false;
        }
        if (this.mSubject != null) {
            if (!this.mSubject.equals(message.mSubject)) {
                return false;
            }
        } else if (message.mSubject != null) {
            return false;
        }
        if (this.thread != null) {
            if (!this.thread.equals(message.thread)) {
                return false;
            }
        } else if (message.thread != null) {
            return false;
        }
        if (this.type != message.type) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.type != null) {
            result = this.type.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.mBody != null) {
            hashCode = this.mBody.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.thread != null) {
            hashCode = this.thread.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.language != null) {
            hashCode = this.language.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.mSubject != null) {
            i = this.mSubject.hashCode();
        }
        return hashCode + i;
    }
}
