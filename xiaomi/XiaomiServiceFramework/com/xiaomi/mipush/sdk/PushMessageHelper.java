package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
import java.util.List;

public class PushMessageHelper {
    public static final String KEY_COMMAND = "key_command";
    public static final String KEY_MESSAGE = "key_message";
    public static final int MESSAGE_COMMAND = 3;
    public static final int MESSAGE_QUIT = 4;
    public static final int MESSAGE_RAW = 1;
    public static final int MESSAGE_SENDMESSAGE = 2;
    public static final String MESSAGE_TYPE = "message_type";
    public static final int PUSH_MODE_BROADCAST = 2;
    public static final int PUSH_MODE_CALLBACK = 1;
    private static int pushMode;

    static {
        pushMode = 0;
    }

    private static boolean isIntentAvailable(Context context, Intent intent) {
        try {
            List<ResolveInfo> resolveInfos = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            if (resolveInfos == null || resolveInfos.isEmpty()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    public static int getPushMode(Context context) {
        if (pushMode == 0) {
            if (isUseCallbackPushMode(context)) {
                setPushMode(PUSH_MODE_CALLBACK);
            } else {
                setPushMode(PUSH_MODE_BROADCAST);
            }
        }
        return pushMode;
    }

    private static void setPushMode(int newMode) {
        pushMode = newMode;
    }

    public static boolean isUseCallbackPushMode(Context context) {
        Intent intent = new Intent(PushConstants.MIPUSH_ACTION_NEW_MESSAGE);
        intent.setClassName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
        return isIntentAvailable(context, intent);
    }

    public static void sendPushMessageBroadcast(Context context, MiPushMessage pushMessage) {
        Intent intent = new Intent(PushConstants.MIPUSH_ACTION_NEW_MESSAGE);
        intent.setPackage(context.getPackageName());
        intent.putExtra(MESSAGE_TYPE, PUSH_MODE_BROADCAST);
        intent.putExtra(KEY_MESSAGE, pushMessage);
        context.sendBroadcast(intent);
    }

    public static void sendCommandMessageBroadcast(Context context, MiPushCommandMessage commandMessage) {
        Intent intent = new Intent(PushConstants.MIPUSH_ACTION_NEW_MESSAGE);
        intent.setPackage(context.getPackageName());
        intent.putExtra(MESSAGE_TYPE, MESSAGE_COMMAND);
        intent.putExtra(KEY_COMMAND, commandMessage);
        context.sendBroadcast(intent);
    }

    public static void sendQuitMessageBroadcast(Context context) {
        Intent intent = new Intent(PushConstants.MIPUSH_ACTION_NEW_MESSAGE);
        intent.setPackage(context.getPackageName());
        intent.putExtra(MESSAGE_TYPE, MESSAGE_QUIT);
        context.sendBroadcast(intent);
    }

    public static MiPushCommandMessage generateCommandMessage(String command, List<String> commandArguments, long resultCode, String reason, String category) {
        MiPushCommandMessage commandResult = new MiPushCommandMessage();
        commandResult.setCommand(command);
        commandResult.setCommandArguments(commandArguments);
        commandResult.setResultCode(resultCode);
        commandResult.setReason(reason);
        commandResult.setCategory(category);
        return commandResult;
    }

    public static MiPushMessage generateMessage(XmPushActionSendMessage msg, PushMetaInfo metaInfo, boolean hasNotified) {
        MiPushMessage message = new MiPushMessage();
        message.setMessageId(msg.getId());
        if (!TextUtils.isEmpty(msg.getAliasName())) {
            message.setMessageType(PUSH_MODE_CALLBACK);
            message.setAlias(msg.getAliasName());
        } else if (TextUtils.isEmpty(msg.getTopic())) {
            message.setMessageType(0);
        } else {
            message.setMessageType(PUSH_MODE_BROADCAST);
            message.setTopic(msg.getTopic());
        }
        message.setCategory(msg.getCategory());
        if (msg.getMessage() != null) {
            message.setContent(msg.getMessage().getPayload());
        }
        if (metaInfo != null) {
            if (TextUtils.isEmpty(message.getMessageId())) {
                message.setMessageId(metaInfo.getId());
            }
            if (TextUtils.isEmpty(message.getTopic())) {
                message.setTopic(metaInfo.getTopic());
            }
            message.setDescription(metaInfo.getDescription());
            message.setTitle(metaInfo.getTitle());
            message.setNotifyType(metaInfo.getNotifyType());
            message.setNotifyId(metaInfo.getNotifyId());
            message.setPassThrough(metaInfo.getPassThrough());
            message.setExtra(metaInfo.getExtra());
        }
        message.setNotified(hasNotified);
        return message;
    }
}
