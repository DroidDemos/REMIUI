package com.xiaomi.mipush.sdk;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class MiPushMessage implements PushMessageInterface {
    private static final String KEY_ALIAS = "alias";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DESC = "description";
    private static final String KEY_EXTRA = "extra";
    private static final String KEY_MESSAGE_ID = "messageId";
    private static final String KEY_MESSAGE_TYPE = "messageType";
    private static final String KEY_NOTIFIED = "isNotified";
    private static final String KEY_NOTIFY_ID = "notifyId";
    private static final String KEY_NOTIFY_TYPE = "notifyType";
    private static final String KEY_PASS_THROUGH = "passThrough";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TOPIC = "topic";
    public static final int MESSAGE_TYPE_ALIAS = 1;
    public static final int MESSAGE_TYPE_REG = 0;
    public static final int MESSAGE_TYPE_TOPIC = 2;
    private static final long serialVersionUID = 1;
    private String alias;
    private String category;
    private String content;
    private String description;
    private HashMap<String, String> extra;
    private boolean isNotified;
    private String messageId;
    private int messageType;
    private int notifyId;
    private int notifyType;
    private int passThrough;
    private String title;
    private String topic;

    public MiPushMessage() {
        this.extra = new HashMap();
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getNotifyType() {
        return this.notifyType;
    }

    public void setNotifyType(int notifyType) {
        this.notifyType = notifyType;
    }

    public int getNotifyId() {
        return this.notifyId;
    }

    public void setNotifyId(int notifyId) {
        this.notifyId = notifyId;
    }

    public boolean isNotified() {
        return this.isNotified;
    }

    public void setNotified(boolean isNotified) {
        this.isNotified = isNotified;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPassThrough() {
        return this.passThrough;
    }

    public void setPassThrough(int passThrough) {
        this.passThrough = passThrough;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra.clear();
        if (extra != null) {
            this.extra.putAll(extra);
        }
    }

    public String toString() {
        return "messageId={" + this.messageId + "},passThrough={" + this.passThrough + "},alias={" + this.alias + "},topic={" + this.topic + "},content={" + this.content + "},description={" + this.description + "},title={" + this.title + "},isNotified={" + this.isNotified + "},notifyId={" + this.notifyId + "},notifyType={" + this.notifyType + "}, category={" + this.category + "}, extra={" + this.extra + "}";
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE_ID, this.messageId);
        bundle.putInt(KEY_PASS_THROUGH, this.passThrough);
        bundle.putInt(KEY_MESSAGE_TYPE, this.messageType);
        if (!TextUtils.isEmpty(this.alias)) {
            bundle.putString(KEY_ALIAS, this.alias);
        }
        if (!TextUtils.isEmpty(this.topic)) {
            bundle.putString(KEY_TOPIC, this.topic);
        }
        bundle.putString(KEY_CONTENT, this.content);
        if (!TextUtils.isEmpty(this.description)) {
            bundle.putString(KEY_DESC, this.description);
        }
        if (!TextUtils.isEmpty(this.title)) {
            bundle.putString(KEY_TITLE, this.title);
        }
        bundle.putBoolean(KEY_NOTIFIED, this.isNotified);
        bundle.putInt(KEY_NOTIFY_ID, this.notifyId);
        bundle.putInt(KEY_NOTIFY_TYPE, this.notifyType);
        if (!TextUtils.isEmpty(this.category)) {
            bundle.putString(KEY_CATEGORY, this.category);
        }
        if (this.extra != null) {
            bundle.putSerializable(KEY_EXTRA, this.extra);
        }
        return bundle;
    }

    public static MiPushMessage fromBundle(Bundle bundle) {
        MiPushMessage message = new MiPushMessage();
        message.messageId = bundle.getString(KEY_MESSAGE_ID);
        message.messageType = bundle.getInt(KEY_MESSAGE_TYPE);
        message.passThrough = bundle.getInt(KEY_PASS_THROUGH);
        message.alias = bundle.getString(KEY_ALIAS);
        message.topic = bundle.getString(KEY_TOPIC);
        message.content = bundle.getString(KEY_CONTENT);
        message.description = bundle.getString(KEY_DESC);
        message.title = bundle.getString(KEY_TITLE);
        message.isNotified = bundle.getBoolean(KEY_NOTIFIED);
        message.notifyId = bundle.getInt(KEY_NOTIFY_ID);
        message.notifyType = bundle.getInt(KEY_NOTIFY_TYPE);
        message.category = bundle.getString(KEY_CATEGORY);
        message.extra = (HashMap) bundle.getSerializable(KEY_EXTRA);
        return message;
    }
}
