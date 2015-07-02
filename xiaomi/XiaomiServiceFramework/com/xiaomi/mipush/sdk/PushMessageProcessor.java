package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.push.service.MIPushEventProcessor;
import com.xiaomi.push.service.MIPushNotificationHelper;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMessage;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionAckMessage;
import com.xiaomi.xmpush.thrift.XmPushActionCommandResult;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
import com.xiaomi.xmpush.thrift.XmPushActionSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import com.xiaomi.xmsf.push.service.Constants;
import com.xiaomi.xmsf.push.service.log.LogProvider;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.transport.TTransportException;

public class PushMessageProcessor {
    public static final String EXTRA_PARAM_INTENT_URI = "intent_uri";
    public static final String EXTRA_PARAM_NOTIFY_EFFECT = "notify_effect";
    public static final String EXTRA_PARAM_WEB_URI = "web_uri";
    private static final int MAX_MSG_CACHE_COUNT = 10;
    private static String NOTIFICATION_CLICK_DEFAULT = null;
    private static String NOTIFICATION_CLICK_INTENT = null;
    private static String NOTIFICATION_CLICK_WEB_PAGE = null;
    private static final String PREF_KEY_CACHED_MSGIDS = "pref_msg_ids";
    private static Object lock;
    private static Queue<String> mCachedMsgIds;
    private static PushMessageProcessor sInstance;
    private Context sAppContext;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$ActionType;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$ActionType = new int[ActionType.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.Registration.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.UnRegistration.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.SendMessage.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.Subscription.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.UnSubscription.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.Command.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$ActionType[ActionType.Notification.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    static {
        sInstance = null;
        NOTIFICATION_CLICK_DEFAULT = PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI;
        NOTIFICATION_CLICK_INTENT = PushConstants.MIPUSH_SDK_VERSION;
        NOTIFICATION_CLICK_WEB_PAGE = "3";
        lock = new Object();
    }

    public static PushMessageProcessor getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PushMessageProcessor(context);
        }
        return sInstance;
    }

    private PushMessageProcessor(Context context) {
        this.sAppContext = context.getApplicationContext();
        if (this.sAppContext == null) {
            this.sAppContext = context;
        }
    }

    public PushMessageInterface proccessIntent(Intent intent) {
        PushMessageInterface pushMessageInterface = null;
        String action = intent.getAction();
        MyLog.warn("receive an intent from server, action=" + action);
        byte[] msgBytes;
        XmPushActionContainer message;
        if (PushConstants.MIPUSH_ACTION_NEW_MESSAGE.equals(action)) {
            msgBytes = intent.getByteArrayExtra(PushConstants.MIPUSH_EXTRA_PAYLOAD);
            boolean hasNotified = intent.getBooleanExtra(MIPushNotificationHelper.FROM_NOTIFICATION, false);
            if (msgBytes == null) {
                MyLog.e("receiving an empty message, drop");
            } else {
                message = new XmPushActionContainer();
                try {
                    XmPushThriftSerializeUtils.convertByteArrayToThriftObject(message, msgBytes);
                    AppInfoHolder infoHolder = AppInfoHolder.getInstance(this.sAppContext);
                    if (!(message.getAction() != ActionType.SendMessage || message.getMetaInfo() == null || infoHolder.isPaused() || hasNotified)) {
                        ackMessage(message);
                    }
                    if (!infoHolder.appRegistered() && message.action != ActionType.Registration) {
                        MyLog.e("receive message without registration. need unregister or re-register!");
                    } else if (!infoHolder.appRegistered() || !infoHolder.invalidated()) {
                        pushMessageInterface = processMessage(message, hasNotified, msgBytes);
                    } else if (message.action == ActionType.UnRegistration) {
                        infoHolder.clear();
                        MiPushClient.clearExtras(this.sAppContext);
                        PushMessageHandler.removeAllPushCallbackClass();
                    } else {
                        MiPushClient.unregisterPush(this.sAppContext);
                    }
                } catch (Throwable e) {
                    MyLog.e(e);
                } catch (Throwable e2) {
                    MyLog.e(e2);
                }
            }
        } else if (PushConstants.MIPUSH_ACTION_ERROR.equals(action)) {
            pushMessageInterface = new MiPushCommandMessage();
            message = new XmPushActionContainer();
            try {
                msgBytes = intent.getByteArrayExtra(PushConstants.MIPUSH_EXTRA_PAYLOAD);
                if (msgBytes != null) {
                    XmPushThriftSerializeUtils.convertByteArrayToThriftObject(message, msgBytes);
                }
            } catch (TException e3) {
            }
            pushMessageInterface.setCommand(String.valueOf(message.getAction()));
            pushMessageInterface.setResultCode((long) intent.getIntExtra(PushConstants.MIPUSH_EXTRA_ERROR_CODE, 0));
            pushMessageInterface.setReason(intent.getStringExtra(PushConstants.MIPUSH_EXTRA_ERROR_MSG));
            MyLog.e("receive a error message. code = " + intent.getIntExtra(PushConstants.MIPUSH_EXTRA_ERROR_CODE, 0) + ", msg= " + intent.getStringExtra(PushConstants.MIPUSH_EXTRA_ERROR_MSG));
        }
        return pushMessageInterface;
    }

    private PushMessageInterface processMessage(XmPushActionContainer container, boolean hasNotified, byte[] decryptedContent) {
        MalformedURLException e;
        try {
            TBase message = PushContainerHelper.getResponseMessageBodyFromContainer(this.sAppContext, container);
            if (message == null) {
                MyLog.e("receiving an un-recognized message. " + container.action);
                return null;
            }
            MyLog.v("receive a message." + message);
            ActionType action = container.getAction();
            MyLog.warn("processing a message, action=" + action);
            List<String> cmdArgs;
            switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$ActionType[action.ordinal()]) {
                case TTransportException.NOT_OPEN /*1*/:
                    XmPushActionRegistrationResult regResult = (XmPushActionRegistrationResult) message;
                    if (regResult.errorCode == 0) {
                        AppInfoHolder.getInstance(this.sAppContext).putRegIDAndSecret(regResult.regId, regResult.regSecret);
                    }
                    cmdArgs = null;
                    if (!TextUtils.isEmpty(regResult.regId)) {
                        cmdArgs = new ArrayList();
                        cmdArgs.add(regResult.regId);
                    }
                    PushMessageInterface commandMessage = PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_REGISTER, cmdArgs, regResult.errorCode, regResult.reason, null);
                    PushServiceClient.getInstance(this.sAppContext).processPendRequest();
                    return commandMessage;
                case TTransportException.ALREADY_OPEN /*2*/:
                    if (((XmPushActionUnRegistrationResult) message).errorCode == 0) {
                        AppInfoHolder.getInstance(this.sAppContext).clear();
                        MiPushClient.clearExtras(this.sAppContext);
                    }
                    PushMessageHandler.removeAllPushCallbackClass();
                    break;
                case TTransportException.TIMED_OUT /*3*/:
                    if (AppInfoHolder.getInstance(this.sAppContext).isPaused()) {
                        MyLog.warn("receive a message in pause state. drop it");
                        return null;
                    }
                    XmPushActionSendMessage sendMsg = (XmPushActionSendMessage) message;
                    PushMessage actualMsg = sendMsg.getMessage();
                    if (actualMsg == null) {
                        MyLog.e("receive an empty message without push content, drop it");
                        return null;
                    }
                    if (hasNotified) {
                        MiPushClient.reportMessageClicked(this.sAppContext, actualMsg.getId());
                    }
                    if (!TextUtils.isEmpty(sendMsg.getAliasName()) && MiPushClient.aliasSetTime(this.sAppContext, sendMsg.getAliasName()) < 0) {
                        MiPushClient.addAlias(this.sAppContext, sendMsg.getAliasName());
                    } else if (!TextUtils.isEmpty(sendMsg.getTopic()) && MiPushClient.topicSubscribedTime(this.sAppContext, sendMsg.getTopic()) < 0) {
                        MiPushClient.addTopic(this.sAppContext, sendMsg.getTopic());
                    }
                    MiPushMessage pushMessage = null;
                    String key = null;
                    if (!(container.metaInfo == null || container.metaInfo.getExtra() == null)) {
                        key = (String) container.metaInfo.extra.get("jobkey");
                    }
                    if (TextUtils.isEmpty(key)) {
                        key = actualMsg.getId();
                    }
                    if (hasNotified || !isDuplicateMessage(this.sAppContext, key)) {
                        pushMessage = PushMessageHelper.generateMessage(sendMsg, container.getMetaInfo(), hasNotified);
                        if (pushMessage.getPassThrough() == 0 && !hasNotified && MIPushEventProcessor.isNotifyForeground(pushMessage.getExtra())) {
                            MIPushNotificationHelper.notifyPushMessage(this.sAppContext, container, decryptedContent);
                            return null;
                        }
                        MyLog.warn("receive a message, msgid=" + actualMsg.getId());
                        if (hasNotified && pushMessage.getExtra() != null && pushMessage.getExtra().containsKey(EXTRA_PARAM_NOTIFY_EFFECT)) {
                            Map<String, String> extra = pushMessage.getExtra();
                            String typeId = (String) extra.get(EXTRA_PARAM_NOTIFY_EFFECT);
                            String customizedPkg = null;
                            if (LogProvider.AUTHORITY.equals(this.sAppContext.getPackageName())) {
                                customizedPkg = (String) extra.get(Constants.EXTRA_KEY_PKGNAME);
                            }
                            Intent intent = null;
                            if (NOTIFICATION_CLICK_DEFAULT.equals(typeId)) {
                                try {
                                    PackageManager packageManager = this.sAppContext.getPackageManager();
                                    if (TextUtils.isEmpty(customizedPkg)) {
                                        customizedPkg = this.sAppContext.getPackageName();
                                    }
                                    intent = packageManager.getLaunchIntentForPackage(customizedPkg);
                                } catch (Exception e2) {
                                    MyLog.e("Cause: " + e2.getMessage());
                                }
                            } else if (NOTIFICATION_CLICK_INTENT.equals(typeId)) {
                                String intentStr = (String) extra.get(EXTRA_PARAM_INTENT_URI);
                                if (intentStr != null) {
                                    try {
                                        intent = Intent.parseUri(intentStr, 1);
                                        if (TextUtils.isEmpty(customizedPkg)) {
                                            customizedPkg = this.sAppContext.getPackageName();
                                        }
                                        intent.setPackage(customizedPkg);
                                    } catch (URISyntaxException e3) {
                                        MyLog.e("Cause: " + e3.getMessage());
                                    }
                                }
                            } else if (NOTIFICATION_CLICK_WEB_PAGE.equals(typeId)) {
                                String uri = (String) extra.get(EXTRA_PARAM_WEB_URI);
                                if (uri != null) {
                                    String tmp = uri.trim().toLowerCase();
                                    if (!tmp.startsWith("http://")) {
                                        if (!tmp.startsWith("https://")) {
                                            tmp = "http://" + tmp;
                                        }
                                    }
                                    try {
                                        String protocol = new URL(tmp).getProtocol();
                                        if ("http".equals(protocol) || "https".equals(protocol)) {
                                            Intent intent2 = new Intent("android.intent.action.VIEW");
                                            try {
                                                intent2.setData(Uri.parse(tmp));
                                                intent = intent2;
                                            } catch (MalformedURLException e4) {
                                                e = e4;
                                                intent = intent2;
                                                MyLog.e("Cause: " + e.getMessage());
                                                if (intent != null) {
                                                    if (!typeId.equals(NOTIFICATION_CLICK_WEB_PAGE)) {
                                                        intent.putExtra(PushMessageHelper.KEY_MESSAGE, pushMessage);
                                                    }
                                                    intent.addFlags(268435456);
                                                    try {
                                                        if (this.sAppContext.getPackageManager().resolveActivity(intent, 65536) != null) {
                                                            this.sAppContext.startActivity(intent);
                                                        }
                                                    } catch (Exception e22) {
                                                        MyLog.e("Cause: " + e22.getMessage());
                                                    }
                                                }
                                                return null;
                                            }
                                        }
                                    } catch (MalformedURLException e5) {
                                        e = e5;
                                        MyLog.e("Cause: " + e.getMessage());
                                        if (intent != null) {
                                            if (typeId.equals(NOTIFICATION_CLICK_WEB_PAGE)) {
                                                intent.putExtra(PushMessageHelper.KEY_MESSAGE, pushMessage);
                                            }
                                            intent.addFlags(268435456);
                                            if (this.sAppContext.getPackageManager().resolveActivity(intent, 65536) != null) {
                                                this.sAppContext.startActivity(intent);
                                            }
                                        }
                                        return null;
                                    }
                                }
                            }
                            if (intent != null) {
                                if (typeId.equals(NOTIFICATION_CLICK_WEB_PAGE)) {
                                    intent.putExtra(PushMessageHelper.KEY_MESSAGE, pushMessage);
                                }
                                intent.addFlags(268435456);
                                if (this.sAppContext.getPackageManager().resolveActivity(intent, 65536) != null) {
                                    this.sAppContext.startActivity(intent);
                                }
                            }
                            return null;
                        }
                    }
                    MyLog.warn("drop a duplicate message, key=" + key);
                    if (container.getMetaInfo() == null && !hasNotified) {
                        ackMessage(sendMsg);
                    }
                    return pushMessage;
                case TTransportException.END_OF_FILE /*4*/:
                    XmPushActionSubscriptionResult subResult = (XmPushActionSubscriptionResult) message;
                    if (subResult.errorCode == 0) {
                        MiPushClient.addTopic(this.sAppContext, subResult.getTopic());
                    }
                    cmdArgs = null;
                    if (!TextUtils.isEmpty(subResult.getTopic())) {
                        cmdArgs = new ArrayList();
                        cmdArgs.add(subResult.getTopic());
                    }
                    return PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_SUBSCRIBE_TOPIC, cmdArgs, subResult.errorCode, subResult.reason, subResult.getCategory());
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    XmPushActionUnSubscriptionResult unsubResult = (XmPushActionUnSubscriptionResult) message;
                    if (unsubResult.errorCode == 0) {
                        MiPushClient.removeTopic(this.sAppContext, unsubResult.getTopic());
                    }
                    cmdArgs = null;
                    if (!TextUtils.isEmpty(unsubResult.getTopic())) {
                        cmdArgs = new ArrayList();
                        cmdArgs.add(unsubResult.getTopic());
                    }
                    return PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC, cmdArgs, unsubResult.errorCode, unsubResult.reason, unsubResult.getCategory());
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    XmPushActionCommandResult commandResult = (XmPushActionCommandResult) message;
                    String configName = commandResult.getCmdName();
                    cmdArgs = commandResult.getCmdArgs();
                    if (commandResult.errorCode == 0) {
                        if (TextUtils.equals(configName, MiPushClient.COMMAND_SET_ACCEPT_TIME) && cmdArgs != null && cmdArgs.size() > 1) {
                            MiPushClient.addAcceptTime(this.sAppContext, (String) cmdArgs.get(0), (String) cmdArgs.get(1));
                            if ("00:00".equals(cmdArgs.get(0)) && "00:00".equals(cmdArgs.get(1))) {
                                AppInfoHolder.getInstance(this.sAppContext).setPaused(true);
                            } else {
                                AppInfoHolder.getInstance(this.sAppContext).setPaused(false);
                            }
                        } else if (TextUtils.equals(configName, MiPushClient.COMMAND_SET_ALIAS) && cmdArgs != null && cmdArgs.size() > 0) {
                            MiPushClient.addAlias(this.sAppContext, (String) cmdArgs.get(0));
                        } else if (TextUtils.equals(configName, MiPushClient.COMMAND_UNSET_ALIAS) && cmdArgs != null && cmdArgs.size() > 0) {
                            MiPushClient.removeAlias(this.sAppContext, (String) cmdArgs.get(0));
                        }
                    }
                    return PushMessageHelper.generateCommandMessage(configName, cmdArgs, commandResult.errorCode, commandResult.reason, commandResult.getCategory());
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    if ("registration id expired".equalsIgnoreCase(((XmPushActionNotification) message).type)) {
                        MiPushClient.reInitialize(this.sAppContext);
                        break;
                    }
                    break;
            }
            return null;
        } catch (Throwable e6) {
            MyLog.e(e6);
            MyLog.e("receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    private void ackMessage(XmPushActionContainer container) {
        PushMetaInfo metaInfo = container.getMetaInfo();
        XmPushActionAckMessage ackMessage = new XmPushActionAckMessage();
        ackMessage.setAppId(container.getAppid());
        ackMessage.setId(metaInfo.getId());
        ackMessage.setMessageTs(metaInfo.getMessageTs());
        if (!TextUtils.isEmpty(metaInfo.getTopic())) {
            ackMessage.setTopic(metaInfo.getTopic());
        }
        PushServiceClient.getInstance(this.sAppContext).sendMessage(ackMessage, ActionType.AckMessage, false);
    }

    private void ackMessage(XmPushActionSendMessage sendMsg) {
        XmPushActionAckMessage ackMessage = new XmPushActionAckMessage();
        ackMessage.setAppId(sendMsg.getAppId());
        ackMessage.setId(sendMsg.getId());
        ackMessage.setMessageTs(sendMsg.getMessage().getCreateAt());
        if (!TextUtils.isEmpty(sendMsg.getTopic())) {
            ackMessage.setTopic(sendMsg.getTopic());
        }
        if (!TextUtils.isEmpty(sendMsg.getAliasName())) {
            ackMessage.setAliasName(sendMsg.getAliasName());
        }
        PushServiceClient.getInstance(this.sAppContext).sendMessage(ackMessage, ActionType.AckMessage);
    }

    private static boolean isDuplicateMessage(Context context, String msgId) {
        boolean z;
        synchronized (lock) {
            SharedPreferences sp = AppInfoHolder.getInstance(context).getSharedPreferences();
            if (mCachedMsgIds == null) {
                String[] ids = sp.getString(PREF_KEY_CACHED_MSGIDS, "").split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                mCachedMsgIds = new LinkedList();
                for (String id : ids) {
                    mCachedMsgIds.add(id);
                }
            }
            if (mCachedMsgIds.contains(msgId)) {
                z = true;
            } else {
                mCachedMsgIds.add(msgId);
                if (mCachedMsgIds.size() > MAX_MSG_CACHE_COUNT) {
                    mCachedMsgIds.poll();
                }
                String newMsgIds = XMStringUtils.join(mCachedMsgIds, MiPushClient.ACCEPT_TIME_SEPARATOR);
                Editor ed = sp.edit();
                ed.putString(PREF_KEY_CACHED_MSGIDS, newMsgIds);
                ed.commit();
                z = false;
            }
        }
        return z;
    }
}
