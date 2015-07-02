package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.channel.commonutils.misc.DateTimeHelper;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.push.service.MIPushNotificationHelper;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.push.service.receivers.PingReceiver;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.XmPushActionCommand;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistration;
import com.xiaomi.xmpush.thrift.XmPushActionSubscription;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistration;
import com.xiaomi.xmpush.thrift.XmPushActionUnSubscription;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public abstract class MiPushClient {
    private static final String ACCEPT_TIME = "accept_time";
    public static final String ACCEPT_TIME_SEPARATOR = ",";
    public static final String COMMAND_REGISTER = "register";
    public static final String COMMAND_SET_ACCEPT_TIME = "accept-time";
    public static final String COMMAND_SET_ALIAS = "set-alias";
    public static String COMMAND_SUBSCRIBE_TOPIC = null;
    public static final String COMMAND_UNSET_ALIAS = "unset-alias";
    public static String COMMAND_UNSUBSCRIBE_TOPIC = null;
    private static final String LAST_WAKEUP = "wake_up";
    private static final String PREFIX_ALIAS = "alias_";
    private static final String PREFIX_TOPIC = "topic_";
    private static final String PREF_EXTRA = "mipush_extra";
    private static Context sContext;
    private static long sCurMsgId;

    public static class IllegalManifestException extends Exception {
        private static final long serialVersionUID = 1;
        private PackageItemInfo mInfo;

        public IllegalManifestException(String message, PackageItemInfo component) {
            super(message);
            this.mInfo = component;
        }

        public PackageItemInfo getInfo() {
            return this.mInfo;
        }
    }

    @Deprecated
    public static abstract class MiPushClientCallback {
        private String category;

        protected String getCategory() {
            return this.category;
        }

        protected void setCategory(String category) {
            this.category = category;
        }

        public void onReceiveMessage(String content, String alias, String topic, boolean hasNotified) {
        }

        public void onReceiveMessage(MiPushMessage message) {
        }

        public void onInitializeResult(long resultCode, String reason, String regID) {
        }

        public void onSubscribeResult(long resultCode, String reason, String topic) {
        }

        public void onUnsubscribeResult(long resultCode, String reason, String topic) {
        }

        public void onCommandResult(String command, long resultCode, String reason, List<String> list) {
        }
    }

    static {
        COMMAND_SUBSCRIBE_TOPIC = "subscribe-topic";
        COMMAND_UNSUBSCRIBE_TOPIC = "unsubscibe-topic";
        if (BuildSettings.IsDebugBuild || BuildSettings.IsTestBuild || BuildSettings.IsLogableBuild || BuildSettings.IsRCBuild) {
            MyLog.setLogLevel(0);
        }
        sCurMsgId = System.currentTimeMillis();
    }

    public static boolean shouldUseMIUIPush(Context context) {
        return PushServiceClient.getInstance(context).shouldUseMIUIPush();
    }

    public static void registerPush(Context context, String appID, String appToken) {
        initialize(context, appID, appToken, null);
    }

    @Deprecated
    public static void initialize(Context context, String appID, String appToken, MiPushClientCallback callback) {
        checkNotNull(context, "context");
        checkNotNull(appID, "appID");
        checkNotNull(appToken, "appToken");
        try {
            sContext = context.getApplicationContext();
            if (sContext == null) {
                sContext = context;
            }
            if (callback != null) {
                PushMessageHandler.addPushCallbackClass(callback);
            }
            boolean isEvnChanage = AppInfoHolder.getInstance(sContext).getEnvType() != Constants.getEnvType();
            if (isEvnChanage || !AppInfoHolder.getInstance(sContext).appRegistered(appID, appToken) || AppInfoHolder.getInstance(sContext).invalidated()) {
                AppInfoHolder.getInstance(sContext).clear();
                AppInfoHolder.getInstance(sContext).setEnvType(Constants.getEnvType());
                AppInfoHolder.getInstance(sContext).putAppIDAndToken(appID, appToken);
                clearExtras(sContext);
                XmPushActionRegistration regMessage = new XmPushActionRegistration();
                regMessage.setId(generatePacketID());
                regMessage.setAppId(appID);
                regMessage.setToken(appToken);
                regMessage.setPackageName(context.getPackageName());
                regMessage.setDeviceId(XMStringUtils.generateRandomString(6));
                regMessage.setAppVersion(AppInfoHolder.getVersionName(context, context.getPackageName()));
                PushServiceClient.getInstance(sContext).register(regMessage, isEvnChanage);
            } else {
                if (1 == PushMessageHelper.getPushMode(context)) {
                    checkNotNull(callback, "callback");
                    callback.onInitializeResult(0, null, AppInfoHolder.getInstance(context).getRegID());
                } else {
                    List<String> cmdArgs = new ArrayList();
                    cmdArgs.add(AppInfoHolder.getInstance(context).getRegID());
                    PushMessageHelper.sendCommandMessageBroadcast(sContext, PushMessageHelper.generateCommandMessage(COMMAND_REGISTER, cmdArgs, 0, null, null));
                }
                PushServiceClient.getInstance(context).awakePushService();
            }
            awakePushServices(sContext);
        } catch (Throwable e) {
            MyLog.e(e);
        }
    }

    private static void awakePushServices(final Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF_EXTRA, 0);
        if (System.currentTimeMillis() - DateTimeHelper.sDayInMilliseconds >= sp.getLong(LAST_WAKEUP, 0)) {
            sp.edit().putLong(LAST_WAKEUP, System.currentTimeMillis()).commit();
            new Thread(new Runnable() {
                public void run() {
                    if (!MiPushClient.shouldUseMIUIPush(context) && 1 == AppInfoHolder.getInstance(context).getEnvType()) {
                        try {
                            List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(4);
                            if (packageInfos != null) {
                                for (PackageInfo info : packageInfos) {
                                    ServiceInfo[] serviceInfos = info.services;
                                    if (serviceInfos != null) {
                                        for (ServiceInfo serviceInfo : serviceInfos) {
                                            if (serviceInfo.exported && serviceInfo.enabled && "com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) && !context.getPackageName().equals(serviceInfo.packageName)) {
                                                try {
                                                    Thread.sleep(((long) ((Math.random() * 300.0d) + 60.0d)) * 1000);
                                                } catch (InterruptedException e) {
                                                }
                                                Intent serviceIntent = new Intent();
                                                serviceIntent.setClassName(serviceInfo.packageName, serviceInfo.name);
                                                serviceIntent.setAction(PushMessageHandler.ACTION_WAKEUP);
                                                context.startService(serviceIntent);
                                            }
                                        }
                                        continue;
                                    }
                                }
                            }
                        } catch (Throwable th) {
                        }
                    }
                }
            }).start();
        }
    }

    public static List<String> getAllAlias(Context context) {
        List<String> aliasList = new ArrayList();
        for (String key : context.getSharedPreferences(PREF_EXTRA, 0).getAll().keySet()) {
            if (key.startsWith(PREFIX_ALIAS)) {
                aliasList.add(key.substring(PREFIX_ALIAS.length()));
            }
        }
        return aliasList;
    }

    public static List<String> getAllTopic(Context context) {
        List<String> topicList = new ArrayList();
        for (String key : context.getSharedPreferences(PREF_EXTRA, 0).getAll().keySet()) {
            if (key.startsWith(PREFIX_TOPIC)) {
                topicList.add(key.substring(PREFIX_TOPIC.length()));
            }
        }
        return topicList;
    }

    protected static void clearExtras(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF_EXTRA, 0);
        long wakeTime = sp.getLong(LAST_WAKEUP, 0);
        Editor editor = sp.edit();
        editor.clear();
        if (wakeTime > 0) {
            editor.putLong(LAST_WAKEUP, wakeTime);
        }
        editor.commit();
    }

    static void reInitialize(Context context) {
        if (AppInfoHolder.getInstance(context).appRegistered()) {
            String appId = AppInfoHolder.getInstance(context).getAppID();
            String appToken = AppInfoHolder.getInstance(context).getAppToken();
            AppInfoHolder.getInstance(context).clear();
            AppInfoHolder.getInstance(context).putAppIDAndToken(appId, appToken);
            XmPushActionRegistration regMessage = new XmPushActionRegistration();
            regMessage.setId(generatePacketID());
            regMessage.setAppId(appId);
            regMessage.setToken(appToken);
            regMessage.setPackageName(context.getPackageName());
            regMessage.setAppVersion(AppInfoHolder.getVersionName(context, context.getPackageName()));
            PushServiceClient.getInstance(context).register(regMessage, false);
        }
    }

    public static void reportMessageClicked(Context context, String msgid) {
        if (AppInfoHolder.getInstance(context).checkAppInfo()) {
            XmPushActionNotification notification = new XmPushActionNotification();
            notification.setAppId(AppInfoHolder.getInstance(context).getAppID());
            notification.setType("bar:click");
            notification.setId(msgid);
            notification.setRequireAck(false);
            PushServiceClient.getInstance(context).sendMessage(notification, ActionType.Notification, false);
        }
    }

    public static void unregisterPush(Context context) {
        if (AppInfoHolder.getInstance(context).checkAppInfo()) {
            XmPushActionUnRegistration unregMessage = new XmPushActionUnRegistration();
            unregMessage.setId(generatePacketID());
            unregMessage.setAppId(AppInfoHolder.getInstance(context).getAppID());
            unregMessage.setRegId(AppInfoHolder.getInstance(context).getRegID());
            unregMessage.setToken(AppInfoHolder.getInstance(context).getAppToken());
            unregMessage.setPackageName(context.getPackageName());
            PushServiceClient.getInstance(context).unregister(unregMessage);
            PushMessageHandler.removeAllPushCallbackClass();
            AppInfoHolder.getInstance(context).invalidate();
            clearExtras(context);
        }
    }

    public static void setAlias(Context context, String alias, String category) {
        setCommand(context, COMMAND_SET_ALIAS, alias, category);
    }

    public static void unsetAlias(Context context, String alias, String category) {
        setCommand(context, COMMAND_UNSET_ALIAS, alias, category);
    }

    public static void subscribe(Context context, String topic, String category) {
        if (!TextUtils.isEmpty(AppInfoHolder.getInstance(context).getAppID())) {
            if (System.currentTimeMillis() - topicSubscribedTime(context, topic) > DateTimeHelper.sDayInMilliseconds) {
                XmPushActionSubscription subscribeMessage = new XmPushActionSubscription();
                subscribeMessage.setId(generatePacketID());
                subscribeMessage.setAppId(AppInfoHolder.getInstance(context).getAppID());
                subscribeMessage.setTopic(topic);
                subscribeMessage.setPackageName(context.getPackageName());
                subscribeMessage.setCategory(category);
                PushServiceClient.getInstance(context).sendMessage(subscribeMessage, ActionType.Subscription);
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.onSubscribeResult(context, category, 0, null, topic);
            } else {
                List<String> cmdArgs = new ArrayList();
                cmdArgs.add(topic);
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SUBSCRIBE_TOPIC, cmdArgs, 0, null, null));
            }
        }
    }

    public static void unsubscribe(Context context, String topic, String category) {
        if (!AppInfoHolder.getInstance(context).checkAppInfo()) {
            return;
        }
        if (topicSubscribedTime(context, topic) < 0) {
            MyLog.warn("Don't cancel subscribe for " + topic + " is unsubscribed");
            return;
        }
        XmPushActionUnSubscription unsubscribeMessage = new XmPushActionUnSubscription();
        unsubscribeMessage.setId(generatePacketID());
        unsubscribeMessage.setAppId(AppInfoHolder.getInstance(context).getAppID());
        unsubscribeMessage.setTopic(topic);
        unsubscribeMessage.setPackageName(context.getPackageName());
        unsubscribeMessage.setCategory(category);
        PushServiceClient.getInstance(context).sendMessage(unsubscribeMessage, ActionType.UnSubscription);
    }

    public static void pausePush(Context context, String category) {
        setAcceptTime(context, 0, 0, 0, 0, category);
    }

    public static void resumePush(Context context, String category) {
        setAcceptTime(context, 0, 0, 23, 59, category);
    }

    public static void clearNotification(Context context, int notifyId) {
        if (shouldUseMIUIPush(context)) {
            PushServiceClient.getInstance(context).clearNotification(notifyId);
        } else {
            MIPushNotificationHelper.clearNotification(context, context.getPackageName(), notifyId);
        }
    }

    public static void clearNotification(Context context) {
        if (shouldUseMIUIPush(context)) {
            PushServiceClient.getInstance(context).clearNotification(-1);
        } else {
            MIPushNotificationHelper.clearNotification(context, context.getPackageName());
        }
    }

    protected static void setCommand(Context context, String command, String argument, String category) {
        ArrayList arguments = new ArrayList();
        if (!TextUtils.isEmpty(argument)) {
            arguments.add(argument);
        }
        if (!COMMAND_SET_ALIAS.equalsIgnoreCase(command) || System.currentTimeMillis() - aliasSetTime(context, argument) >= DateTimeHelper.sDayInMilliseconds) {
            if (!COMMAND_UNSET_ALIAS.equalsIgnoreCase(command) || aliasSetTime(context, argument) >= 0) {
                setCommand(context, command, arguments, category);
            } else {
                MyLog.warn("Don't cancel alias for " + arguments + " is unseted");
            }
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.onCommandResult(context, category, command, 0, null, arguments);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SET_ALIAS, arguments, 0, null, null));
        }
    }

    protected static void setCommand(Context context, String command, ArrayList<String> arguments, String category) {
        if (!TextUtils.isEmpty(AppInfoHolder.getInstance(context).getAppID())) {
            XmPushActionCommand setConfigMessage = new XmPushActionCommand();
            setConfigMessage.setId(generatePacketID());
            setConfigMessage.setAppId(AppInfoHolder.getInstance(context).getAppID());
            setConfigMessage.setCmdName(command);
            Iterator i$ = arguments.iterator();
            while (i$.hasNext()) {
                setConfigMessage.addToCmdArgs((String) i$.next());
            }
            setConfigMessage.setCategory(category);
            setConfigMessage.setPackageName(context.getPackageName());
            PushServiceClient.getInstance(context).sendMessage(setConfigMessage, ActionType.Command);
        }
    }

    public static void setAcceptTime(Context context, int startHour, int startMin, int endHour, int endMin, String category) {
        if (startHour < 0 || startHour >= 24 || endHour < 0 || endHour >= 24 || startMin < 0 || startMin >= 60 || endMin < 0 || endMin >= 60) {
            throw new IllegalArgumentException("the input parameter is not valid.");
        }
        long standDiff = (long) (((TimeZone.getTimeZone("GMT+08").getRawOffset() - TimeZone.getDefault().getRawOffset()) / 1000) / 60);
        long startTime = ((((long) ((startHour * 60) + startMin)) + standDiff) + DateTimeHelper.sDayInMinutes) % DateTimeHelper.sDayInMinutes;
        long endTime = ((((long) ((endHour * 60) + endMin)) + standDiff) + DateTimeHelper.sDayInMinutes) % DateTimeHelper.sDayInMinutes;
        ArrayList arguments = new ArrayList();
        arguments.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(startTime / 60), Long.valueOf(startTime % 60)}));
        arguments.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(endTime / 60), Long.valueOf(endTime % 60)}));
        if (!acceptTimeSet(context, (String) arguments.get(0), (String) arguments.get(1))) {
            setCommand(context, COMMAND_SET_ACCEPT_TIME, arguments, category);
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.onCommandResult(context, category, COMMAND_SET_ACCEPT_TIME, 0, null, arguments);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SET_ACCEPT_TIME, arguments, 0, null, null));
        }
    }

    private static void checkPermissions(Context context, PackageInfo pkgInfo) throws IllegalManifestException {
        Set<String> requiredPermsSet = new HashSet();
        requiredPermsSet.addAll(Arrays.asList(new String[]{"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", context.getPackageName() + ".permission.MIPUSH_RECEIVE", "android.permission.ACCESS_WIFI_STATE", "android.permission.READ_PHONE_STATE", "android.permission.GET_TASKS", "android.permission.VIBRATE"}));
        boolean isDefined = false;
        if (pkgInfo.permissions != null) {
            for (PermissionInfo pinfo : pkgInfo.permissions) {
                if (selfDefinedPerm.equals(pinfo.name)) {
                    isDefined = true;
                    break;
                }
            }
        }
        if (isDefined) {
            if (pkgInfo.requestedPermissions != null) {
                for (String permName : pkgInfo.requestedPermissions) {
                    if (!TextUtils.isEmpty(permName) && requiredPermsSet.contains(permName)) {
                        requiredPermsSet.remove(permName);
                        if (requiredPermsSet.isEmpty()) {
                            break;
                        }
                    }
                }
            }
            if (!requiredPermsSet.isEmpty()) {
                throw new IllegalManifestException(String.format("<use-permission android:name=\"%1$s\" /> is missing.", new Object[]{requiredPermsSet.iterator().next()}), null);
            }
            return;
        }
        throw new IllegalManifestException(String.format("<permission android:name=\"%1$s\" /> is undefined.", new Object[]{selfDefinedPerm}), null);
    }

    private static void checkServices(Context context, PackageInfo pkgInfo) throws IllegalManifestException {
        Map<String, Boolean[]> requiredServicesMap = new HashMap();
        requiredServicesMap.put(XMPushService.class.getCanonicalName(), new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(false)});
        requiredServicesMap.put(PushMessageHandler.class.getCanonicalName(), new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(true)});
        requiredServicesMap.put(MessageHandleService.class.getCanonicalName(), new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(false)});
        if (pkgInfo.services != null) {
            for (ServiceInfo info : pkgInfo.services) {
                if (!TextUtils.isEmpty(info.name) && requiredServicesMap.containsKey(info.name)) {
                    Boolean[] ba = (Boolean[]) requiredServicesMap.remove(info.name);
                    if (ba[0].booleanValue() != info.enabled) {
                        throw new IllegalManifestException(String.format("Wrong attribute: %n    <service android:name=\"%1$s\" .../> android:enabled should be %<b.", new Object[]{info.name, ba[0]}), info);
                    } else if (ba[1].booleanValue() != info.exported) {
                        throw new IllegalManifestException(String.format("Wrong attribute: %n    <service android:name=\"%1$s\" .../> android:exported should be %<b.", new Object[]{info.name, ba[1]}), info);
                    } else if (requiredServicesMap.isEmpty()) {
                        break;
                    }
                }
            }
        }
        if (!requiredServicesMap.isEmpty()) {
            throw new IllegalManifestException(String.format("<service android:name=\"%1$s\" /> is missing or disabled.", new Object[]{requiredServicesMap.keySet().iterator().next()}), null);
        }
    }

    private static void findAndCheckReceiverInfo(PackageManager manager, Intent intent, Class<?> cls, Boolean[] properties) throws IllegalManifestException {
        boolean isFound = false;
        for (ResolveInfo r : manager.queryBroadcastReceivers(intent, 16384)) {
            ActivityInfo info = r.activityInfo;
            if (info != null && cls.getCanonicalName().equals(info.name)) {
                if (properties[0].booleanValue() != info.enabled) {
                    throw new IllegalManifestException(String.format("Wrong attribute: %n    <receiver android:name=\"%1$s\" .../> android:enabled should be %<b.", new Object[]{info.name, properties[0]}), info);
                } else if (properties[1].booleanValue() != info.exported) {
                    throw new IllegalManifestException(String.format("Wrong attribute: %n    <receiver android:name=\"%1$s\" .../> android:exported should be %<b.", new Object[]{info.name, properties[1]}), info);
                } else {
                    isFound = true;
                    if (!isFound) {
                        throw new IllegalManifestException(String.format("<receiver android:name=\"%1$s\" /> is missing or disabled.", new Object[]{cls.getCanonicalName()}), null);
                    }
                }
            }
        }
        if (!isFound) {
            throw new IllegalManifestException(String.format("<receiver android:name=\"%1$s\" /> is missing or disabled.", new Object[]{cls.getCanonicalName()}), null);
        }
    }

    private static void checkReceivers(Context context) throws IllegalManifestException {
        PackageManager pkgManager = context.getPackageManager();
        String pkgname = context.getPackageName();
        Intent intent = new Intent("android.net.conn.CONNECTIVITY_CHANGE");
        intent.setPackage(pkgname);
        findAndCheckReceiverInfo(pkgManager, intent, NetworkStatusReceiver.class, new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(true)});
        intent = new Intent(PushConstants.ACTION_PING_TIMER);
        intent.setPackage(pkgname);
        findAndCheckReceiverInfo(pkgManager, intent, PingReceiver.class, new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(false)});
        intent = new Intent(PushConstants.MIPUSH_ACTION_NEW_MESSAGE);
        intent.setPackage(pkgname);
        boolean isFound = false;
        for (ResolveInfo r : pkgManager.queryBroadcastReceivers(intent, 16384)) {
            ActivityInfo info = r.activityInfo;
            if (info != null) {
                try {
                    if (!TextUtils.isEmpty(info.name) && PushMessageReceiver.class.isAssignableFrom(Class.forName(info.name)) && info.enabled) {
                        isFound = true;
                        if (isFound) {
                            break;
                        }
                    }
                } catch (Throwable e) {
                    MyLog.e(e);
                }
            }
            isFound = false;
            if (isFound) {
                break;
            }
        }
        if (!isFound) {
            throw new IllegalManifestException("Receiver: none of the subclasses of PushMessageReceiver is enabled or defined.", null);
        }
    }

    public static void checkManifest(Context context) throws IllegalManifestException {
        try {
            PackageInfo pkgInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4100);
            checkReceivers(context);
            checkServices(context, pkgInfo);
            checkPermissions(context, pkgInfo);
        } catch (Throwable e) {
            MyLog.e(e);
        }
    }

    protected static synchronized String generatePacketID() {
        String id;
        synchronized (MiPushClient.class) {
            id = XMStringUtils.generateRandomString(4) + sCurMsgId;
            sCurMsgId++;
        }
        return id;
    }

    private static void checkNotNull(Object obj, String paramName) {
        if (obj == null) {
            throw new IllegalArgumentException("param " + paramName + " is not nullable");
        }
    }

    static synchronized void addAlias(Context context, String alias) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong(PREFIX_ALIAS + alias, System.currentTimeMillis()).commit();
        }
    }

    static synchronized void removeAlias(Context context, String alias) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().remove(PREFIX_ALIAS + alias).commit();
        }
    }

    static synchronized void addTopic(Context context, String topic) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong(PREFIX_TOPIC + topic, System.currentTimeMillis()).commit();
        }
    }

    static synchronized void removeTopic(Context context, String topic) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().remove(PREFIX_TOPIC + topic).commit();
        }
    }

    static synchronized void addAcceptTime(Context context, String startTime, String endTime) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putString(ACCEPT_TIME, startTime + ACCEPT_TIME_SEPARATOR + endTime).commit();
        }
    }

    static synchronized void removeAcceptTime(Context context) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().remove(ACCEPT_TIME).commit();
        }
    }

    public static long topicSubscribedTime(Context context, String topic) {
        return context.getSharedPreferences(PREF_EXTRA, 0).getLong(PREFIX_TOPIC + topic, -1);
    }

    public static long aliasSetTime(Context context, String alias) {
        return context.getSharedPreferences(PREF_EXTRA, 0).getLong(PREFIX_ALIAS + alias, -1);
    }

    private static boolean acceptTimeSet(Context context, String startTime, String endTime) {
        return TextUtils.equals(context.getSharedPreferences(PREF_EXTRA, 0).getString(ACCEPT_TIME, ""), startTime + ACCEPT_TIME_SEPARATOR + endTime);
    }
}
