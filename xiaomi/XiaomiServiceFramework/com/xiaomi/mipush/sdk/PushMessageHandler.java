package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.mipush.sdk.MiPushClient.MiPushClientCallback;
import com.xiaomi.push.service.PushConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PushMessageHandler extends IntentService {
    public static final String ACTION_WAKEUP = "com.xiaomi.mipush.sdk.WAKEUP";
    private static List<MiPushClientCallback> sCallbacks;

    interface PushMessageInterface extends Serializable {
    }

    static {
        sCallbacks = new ArrayList();
    }

    protected static void addPushCallbackClass(MiPushClientCallback callback) {
        synchronized (sCallbacks) {
            if (!sCallbacks.contains(callback)) {
                sCallbacks.add(callback);
            }
        }
    }

    protected static void removePushCallbackClass(MiPushClientCallback callback) {
        synchronized (sCallbacks) {
            sCallbacks.remove(callback);
        }
    }

    protected static void removeAllPushCallbackClass() {
        synchronized (sCallbacks) {
            sCallbacks.clear();
        }
    }

    public PushMessageHandler() {
        super("mipush message handler");
    }

    public static boolean isCallbackEmpty() {
        return sCallbacks.isEmpty();
    }

    protected void onHandleIntent(Intent intent) {
        if (ACTION_WAKEUP.equals(intent.getAction())) {
            if (AppInfoHolder.getInstance(this).appRegistered()) {
                PushServiceClient.getInstance(this).awakePushService();
            }
        } else if (1 != PushMessageHelper.getPushMode(this)) {
            Intent pushMessageReceiverIntent = new Intent(PushConstants.MIPUSH_ACTION_NEW_MESSAGE);
            pushMessageReceiverIntent.setPackage(getPackageName());
            pushMessageReceiverIntent.putExtras(intent);
            sendBroadcast(pushMessageReceiverIntent);
        } else if (isCallbackEmpty()) {
            MyLog.e("receive a message before application calling initialize");
        } else {
            PushMessageInterface message = PushMessageProcessor.getInstance(this).proccessIntent(intent);
            if (message != null) {
                processMessageForCallback(this, message);
            }
        }
    }

    public static void processMessageForCallback(Context context, PushMessageInterface message) {
        String regID = null;
        if (message instanceof MiPushMessage) {
            onReceiveMessage(context, (MiPushMessage) message);
        } else if (message instanceof MiPushCommandMessage) {
            MiPushCommandMessage commandMessage = (MiPushCommandMessage) message;
            String command = commandMessage.getCommand();
            List<String> arguments;
            if (MiPushClient.COMMAND_REGISTER.equals(command)) {
                arguments = commandMessage.getCommandArguments();
                if (!(arguments == null || arguments.isEmpty())) {
                    regID = (String) arguments.get(0);
                }
                onInitializeResult(commandMessage.getResultCode(), commandMessage.getReason(), regID);
            } else if (MiPushClient.COMMAND_SET_ALIAS.equals(command) || MiPushClient.COMMAND_UNSET_ALIAS.equals(command) || MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command)) {
                onCommandResult(context, commandMessage.getCategory(), command, commandMessage.getResultCode(), commandMessage.getReason(), commandMessage.getCommandArguments());
            } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) {
                arguments = commandMessage.getCommandArguments();
                if (arguments == null || arguments.isEmpty()) {
                    topic = null;
                } else {
                    topic = (String) arguments.get(0);
                }
                onSubscribeResult(context, commandMessage.getCategory(), commandMessage.getResultCode(), commandMessage.getReason(), topic);
            } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) {
                arguments = commandMessage.getCommandArguments();
                if (arguments == null || arguments.isEmpty()) {
                    topic = null;
                } else {
                    topic = (String) arguments.get(0);
                }
                onUnsubscribeResult(context, commandMessage.getCategory(), commandMessage.getResultCode(), commandMessage.getReason(), topic);
            }
        }
    }

    public static void onReceiveMessage(Context context, MiPushMessage message) {
        synchronized (sCallbacks) {
            for (MiPushClientCallback callback : sCallbacks) {
                if (isCategoryMatch(message.getCategory(), callback.getCategory())) {
                    callback.onReceiveMessage(message.getContent(), message.getAlias(), message.getTopic(), message.isNotified());
                    callback.onReceiveMessage(message);
                }
            }
        }
    }

    public static void onInitializeResult(long resultCode, String reason, String regID) {
        synchronized (sCallbacks) {
            for (MiPushClientCallback callback : sCallbacks) {
                callback.onInitializeResult(resultCode, reason, regID);
            }
        }
    }

    protected static void onSubscribeResult(Context context, String category, long resultCode, String reason, String topic) {
        synchronized (sCallbacks) {
            for (MiPushClientCallback callback : sCallbacks) {
                if (isCategoryMatch(category, callback.getCategory())) {
                    callback.onSubscribeResult(resultCode, reason, topic);
                }
            }
        }
    }

    protected static void onUnsubscribeResult(Context context, String category, long resultCode, String reason, String topic) {
        synchronized (sCallbacks) {
            for (MiPushClientCallback callback : sCallbacks) {
                if (isCategoryMatch(category, callback.getCategory())) {
                    callback.onUnsubscribeResult(resultCode, reason, topic);
                }
            }
        }
    }

    protected static void onCommandResult(Context context, String category, String command, long resultCode, String reason, List<String> params) {
        synchronized (sCallbacks) {
            for (MiPushClientCallback callback : sCallbacks) {
                if (isCategoryMatch(category, callback.getCategory())) {
                    callback.onCommandResult(command, resultCode, reason, params);
                }
            }
        }
    }

    protected static boolean isCategoryMatch(String msgCategory, String callbackCategory) {
        return (TextUtils.isEmpty(msgCategory) && TextUtils.isEmpty(callbackCategory)) || TextUtils.equals(msgCategory, callbackCategory);
    }
}
