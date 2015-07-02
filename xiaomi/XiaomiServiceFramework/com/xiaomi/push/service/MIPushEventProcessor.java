package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.mipush.sdk.PushMessageProcessor;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.XMPushService.Job;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.TrafficUtils;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionAckMessage;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmsf.push.service.Constants;
import com.xiaomi.xmsf.push.service.log.LogProvider;
import java.util.List;
import java.util.Map;

public class MIPushEventProcessor {
    public static final String EXTRA_PARAM_NOTIFY_FOREGROUND = "notify_foreground";

    public void processChannelOpenResult(Context context, ClientLoginInfo loginInfo, boolean succeeded, int reason, String msg) {
        if (!succeeded) {
            MIPushAccount pushAccount = MIPushAccountUtils.getMIPushAccount(context);
            if (pushAccount != null && "token-expired".equals(msg)) {
                try {
                    MIPushAccountUtils.register(context, pushAccount.appId, pushAccount.appToken, pushAccount.packageName);
                } catch (Throwable e) {
                    MyLog.e(e);
                } catch (Throwable e2) {
                    MyLog.e(e2);
                }
            }
        }
    }

    public void processNewPacket(XMPushService pushService, Packet packet, ClientLoginInfo loginInfo) {
        if (packet instanceof Message) {
            Message miMessage = (Message) packet;
            CommonPacketExtension extension = miMessage.getExtension("s");
            if (extension != null) {
                try {
                    processMIPushMessage(pushService, RC4Cryption.decrypt(RC4Cryption.generateKeyForRC4(loginInfo.security, miMessage.getPacketID()), extension.getText()), (long) TrafficUtils.getTrafficFlow(packet.toXML()));
                    return;
                } catch (Throwable e) {
                    MyLog.e(e);
                    return;
                }
            }
            return;
        }
        MyLog.warn("not a mipush message");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void processMIPushMessage(com.xiaomi.push.service.XMPushService r19, byte[] r20, long r21) {
        /*
        r9 = new com.xiaomi.xmpush.thrift.XmPushActionContainer;
        r9.<init>();
        r0 = r20;
        com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils.convertByteArrayToThriftObject(r9, r0);	 Catch:{ TException -> 0x0089 }
        r2 = r9.packageName;	 Catch:{ TException -> 0x0089 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ TException -> 0x0089 }
        if (r2 != 0) goto L_0x017f;
    L_0x0012:
        r12 = new android.content.Intent;	 Catch:{ TException -> 0x0089 }
        r2 = "com.xiaomi.mipush.RECEIVE_MESSAGE";
        r12.<init>(r2);	 Catch:{ TException -> 0x0089 }
        r2 = "mipush_payload";
        r0 = r20;
        r12.putExtra(r2, r0);	 Catch:{ TException -> 0x0089 }
        r2 = r9.packageName;	 Catch:{ TException -> 0x0089 }
        r12.setPackage(r2);	 Catch:{ TException -> 0x0089 }
        r3 = com.xiaomi.push.service.MIPushNotificationHelper.getTargetPackage(r9);	 Catch:{ TException -> 0x0089 }
        r6 = 1;
        r7 = java.lang.System.currentTimeMillis();	 Catch:{ TException -> 0x0089 }
        r2 = r19;
        r4 = r21;
        com.xiaomi.smack.util.TrafficUtils.distributionTraffic(r2, r3, r4, r6, r7);	 Catch:{ TException -> 0x0089 }
        r15 = r9.getMetaInfo();	 Catch:{ TException -> 0x0089 }
        r2 = com.xiaomi.xmpush.thrift.ActionType.SendMessage;	 Catch:{ TException -> 0x0089 }
        r4 = r9.getAction();	 Catch:{ TException -> 0x0089 }
        if (r2 != r4) goto L_0x0075;
    L_0x0041:
        r2 = com.xiaomi.push.service.MIPushAppInfo.getInstance(r19);	 Catch:{ TException -> 0x0089 }
        r4 = r9.packageName;	 Catch:{ TException -> 0x0089 }
        r2 = r2.isUnRegistered(r4);	 Catch:{ TException -> 0x0089 }
        if (r2 == 0) goto L_0x0075;
    L_0x004d:
        r16 = "";
        if (r15 == 0) goto L_0x0055;
    L_0x0051:
        r16 = r15.getId();	 Catch:{ TException -> 0x0089 }
    L_0x0055:
        r2 = new java.lang.StringBuilder;	 Catch:{ TException -> 0x0089 }
        r2.<init>();	 Catch:{ TException -> 0x0089 }
        r4 = "Drop a message for unregistered, msgid=";
        r2 = r2.append(r4);	 Catch:{ TException -> 0x0089 }
        r0 = r16;
        r2 = r2.append(r0);	 Catch:{ TException -> 0x0089 }
        r2 = r2.toString();	 Catch:{ TException -> 0x0089 }
        com.xiaomi.channel.commonutils.logger.MyLog.warn(r2);	 Catch:{ TException -> 0x0089 }
        r2 = r9.packageName;	 Catch:{ TException -> 0x0089 }
        r0 = r19;
        sendAppAbsentAck(r0, r9, r2);	 Catch:{ TException -> 0x0089 }
    L_0x0074:
        return;
    L_0x0075:
        r2 = isMIUIOldAdsSDKMessage(r9);	 Catch:{ TException -> 0x0089 }
        if (r2 == 0) goto L_0x008e;
    L_0x007b:
        r0 = r19;
        r2 = isMIUIPushSupported(r0, r3);	 Catch:{ TException -> 0x0089 }
        if (r2 == 0) goto L_0x008e;
    L_0x0083:
        r0 = r19;
        sendMIUIOldAdsAckMessage(r0, r9);	 Catch:{ TException -> 0x0089 }
        goto L_0x0074;
    L_0x0089:
        r10 = move-exception;
        com.xiaomi.channel.commonutils.logger.MyLog.e(r10);
        goto L_0x0074;
    L_0x008e:
        r2 = isMIUIPushMessage(r9);	 Catch:{ TException -> 0x0089 }
        if (r2 == 0) goto L_0x00a8;
    L_0x0094:
        r0 = r19;
        r2 = isMIUIPushSupported(r0, r3);	 Catch:{ TException -> 0x0089 }
        if (r2 != 0) goto L_0x00a8;
    L_0x009c:
        r2 = predefinedNotification(r9);	 Catch:{ TException -> 0x0089 }
        if (r2 != 0) goto L_0x00a8;
    L_0x00a2:
        r0 = r19;
        sendMIUINewAdsAckMessage(r0, r9);	 Catch:{ TException -> 0x0089 }
        goto L_0x0074;
    L_0x00a8:
        r0 = r19;
        r2 = isIntentAvailable(r0, r12);	 Catch:{ TException -> 0x0089 }
        if (r2 == 0) goto L_0x0170;
    L_0x00b0:
        r2 = com.xiaomi.xmpush.thrift.ActionType.Registration;	 Catch:{ TException -> 0x0089 }
        r4 = r9.getAction();	 Catch:{ TException -> 0x0089 }
        if (r2 != r4) goto L_0x00d3;
    L_0x00b8:
        r17 = r9.getPackageName();	 Catch:{ TException -> 0x0089 }
        r2 = "pref_registered_pkg_names";
        r4 = 0;
        r0 = r19;
        r18 = r0.getSharedPreferences(r2, r4);	 Catch:{ TException -> 0x0089 }
        r11 = r18.edit();	 Catch:{ TException -> 0x0089 }
        r2 = r9.appid;	 Catch:{ TException -> 0x0089 }
        r0 = r17;
        r11.putString(r0, r2);	 Catch:{ TException -> 0x0089 }
        r11.commit();	 Catch:{ TException -> 0x0089 }
    L_0x00d3:
        if (r15 == 0) goto L_0x0102;
    L_0x00d5:
        r2 = r15.getTitle();	 Catch:{ TException -> 0x0089 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ TException -> 0x0089 }
        if (r2 != 0) goto L_0x0102;
    L_0x00df:
        r2 = r15.getDescription();	 Catch:{ TException -> 0x0089 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ TException -> 0x0089 }
        if (r2 != 0) goto L_0x0102;
    L_0x00e9:
        r2 = r15.passThrough;	 Catch:{ TException -> 0x0089 }
        r4 = 1;
        if (r2 == r4) goto L_0x0102;
    L_0x00ee:
        r2 = r15.getExtra();	 Catch:{ TException -> 0x0089 }
        r2 = isNotifyForeground(r2);	 Catch:{ TException -> 0x0089 }
        if (r2 != 0) goto L_0x0126;
    L_0x00f8:
        r2 = r9.packageName;	 Catch:{ TException -> 0x0089 }
        r0 = r19;
        r2 = com.xiaomi.push.service.MIPushNotificationHelper.isApplicationForeground(r0, r2);	 Catch:{ TException -> 0x0089 }
        if (r2 == 0) goto L_0x0126;
    L_0x0102:
        r2 = r9.packageName;	 Catch:{ TException -> 0x0089 }
        r2 = com.xiaomi.push.service.ClientEventDispatcher.getReceiverPermission(r2);	 Catch:{ TException -> 0x0089 }
        r0 = r19;
        r0.sendBroadcast(r12, r2);	 Catch:{ TException -> 0x0089 }
    L_0x010d:
        r2 = r9.getAction();	 Catch:{ TException -> 0x0089 }
        r4 = com.xiaomi.xmpush.thrift.ActionType.UnRegistration;	 Catch:{ TException -> 0x0089 }
        if (r2 != r4) goto L_0x0074;
    L_0x0115:
        r2 = "com.xiaomi.xmsf";
        r4 = r19.getPackageName();	 Catch:{ TException -> 0x0089 }
        r2 = r2.equals(r4);	 Catch:{ TException -> 0x0089 }
        if (r2 != 0) goto L_0x0074;
    L_0x0121:
        r19.stopSelf();	 Catch:{ TException -> 0x0089 }
        goto L_0x0074;
    L_0x0126:
        r13 = 0;
        r14 = 0;
        if (r15 == 0) goto L_0x014a;
    L_0x012a:
        r2 = r15.extra;	 Catch:{ TException -> 0x0089 }
        if (r2 == 0) goto L_0x0138;
    L_0x012e:
        r2 = r15.extra;	 Catch:{ TException -> 0x0089 }
        r4 = "jobkey";
        r14 = r2.get(r4);	 Catch:{ TException -> 0x0089 }
        r14 = (java.lang.String) r14;	 Catch:{ TException -> 0x0089 }
    L_0x0138:
        r2 = android.text.TextUtils.isEmpty(r14);	 Catch:{ TException -> 0x0089 }
        if (r2 == 0) goto L_0x0142;
    L_0x013e:
        r14 = r15.getId();	 Catch:{ TException -> 0x0089 }
    L_0x0142:
        r2 = r9.packageName;	 Catch:{ TException -> 0x0089 }
        r0 = r19;
        r13 = com.xiaomi.push.service.MiPushMessageDuplicate.isDuplicateMessage(r0, r2, r14);	 Catch:{ TException -> 0x0089 }
    L_0x014a:
        if (r13 == 0) goto L_0x0168;
    L_0x014c:
        r2 = new java.lang.StringBuilder;	 Catch:{ TException -> 0x0089 }
        r2.<init>();	 Catch:{ TException -> 0x0089 }
        r4 = "drop a duplicate message, key=";
        r2 = r2.append(r4);	 Catch:{ TException -> 0x0089 }
        r2 = r2.append(r14);	 Catch:{ TException -> 0x0089 }
        r2 = r2.toString();	 Catch:{ TException -> 0x0089 }
        com.xiaomi.channel.commonutils.logger.MyLog.warn(r2);	 Catch:{ TException -> 0x0089 }
    L_0x0162:
        r0 = r19;
        sendAckMessage(r0, r9);	 Catch:{ TException -> 0x0089 }
        goto L_0x010d;
    L_0x0168:
        r0 = r19;
        r1 = r20;
        com.xiaomi.push.service.MIPushNotificationHelper.notifyPushMessage(r0, r9, r1);	 Catch:{ TException -> 0x0089 }
        goto L_0x0162;
    L_0x0170:
        r2 = new com.xiaomi.push.service.MIPushEventProcessor$1;	 Catch:{ TException -> 0x0089 }
        r4 = 4;
        r0 = r19;
        r2.<init>(r4, r0, r9);	 Catch:{ TException -> 0x0089 }
        r0 = r19;
        r0.executeJob(r2);	 Catch:{ TException -> 0x0089 }
        goto L_0x0074;
    L_0x017f:
        r2 = "receive a mipush message without package name";
        com.xiaomi.channel.commonutils.logger.MyLog.warn(r2);	 Catch:{ TException -> 0x0089 }
        goto L_0x0074;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.MIPushEventProcessor.processMIPushMessage(com.xiaomi.push.service.XMPushService, byte[], long):void");
    }

    private static boolean isMIUIPushMessage(XmPushActionContainer container) {
        return LogProvider.AUTHORITY.equals(container.packageName) && container.getMetaInfo() != null && container.getMetaInfo().getExtra() != null && container.getMetaInfo().getExtra().containsKey(Constants.EXTRA_KEY_PKGNAME);
    }

    private static boolean predefinedNotification(XmPushActionContainer container) {
        return container.getMetaInfo().getExtra().containsKey(PushMessageProcessor.EXTRA_PARAM_NOTIFY_EFFECT);
    }

    private static boolean isMIUIPushSupported(Context context, String packageName) {
        Intent notiIntent = new Intent(Constants.INTENT_ACTION_MIPUSH_MIUI_CLICK_MESSAGE);
        notiIntent.setPackage(packageName);
        Intent passThroughIntent = new Intent(Constants.INTENT_ACTION_MIPUSH_MIUI_RECEIVE_MESSAGE);
        passThroughIntent.setPackage(packageName);
        PackageManager pmgr = context.getPackageManager();
        try {
            List<ResolveInfo> brInfos = pmgr.queryBroadcastReceivers(passThroughIntent, 32);
            List<ResolveInfo> srInfos = pmgr.queryIntentServices(notiIntent, 32);
            if (brInfos.isEmpty() && srInfos.isEmpty()) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            MyLog.e(e);
            return false;
        }
    }

    private static boolean isMIUIOldAdsSDKMessage(XmPushActionContainer container) {
        if (container.getMetaInfo() == null || container.getMetaInfo().getExtra() == null) {
            return false;
        }
        return PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI.equals(container.getMetaInfo().getExtra().get("obslete_ads_message"));
    }

    public static boolean isNotifyForeground(Map<String, String> extra) {
        if (extra == null || !extra.containsKey(EXTRA_PARAM_NOTIFY_FOREGROUND)) {
            return true;
        }
        return PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI.equals((String) extra.get(EXTRA_PARAM_NOTIFY_FOREGROUND));
    }

    private static void sendAckMessage(final XMPushService pushService, final XmPushActionContainer container) {
        pushService.executeJob(new Job(4) {
            public void process() {
                try {
                    pushService.sendMIPushPacket(MIPushEventProcessor.constructAckMessage(pushService, container));
                } catch (Throwable e) {
                    MyLog.e(e);
                    pushService.disconnect(10, e);
                }
            }

            public String getDesc() {
                return "send ack message for message.";
            }
        });
    }

    private static void sendMIUIOldAdsAckMessage(final XMPushService pushService, final XmPushActionContainer container) {
        pushService.executeJob(new Job(4) {
            public void process() {
                try {
                    XmPushActionContainer ackContainer = MIPushEventProcessor.constructAckMessage(pushService, container);
                    PushMetaInfo metaInfo = new PushMetaInfo();
                    metaInfo.setId(container.getMetaInfo().getId());
                    metaInfo.setMessageTs(container.getMetaInfo().getMessageTs());
                    ackContainer.setMetaInfo(metaInfo);
                    ackContainer.getMetaInfo().putToExtra("message_obsleted", PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI);
                    pushService.sendMIPushPacket(ackContainer);
                } catch (Throwable e) {
                    MyLog.e(e);
                    pushService.disconnect(10, e);
                }
            }

            public String getDesc() {
                return "send ack message for obsleted message.";
            }
        });
    }

    private static void sendMIUINewAdsAckMessage(final XMPushService pushService, final XmPushActionContainer container) {
        pushService.executeJob(new Job(4) {
            public void process() {
                try {
                    XmPushActionContainer ackContainer = MIPushEventProcessor.constructAckMessage(pushService, container);
                    PushMetaInfo metaInfo = new PushMetaInfo();
                    metaInfo.setId(container.getMetaInfo().getId());
                    metaInfo.setMessageTs(container.getMetaInfo().getMessageTs());
                    ackContainer.setMetaInfo(metaInfo);
                    ackContainer.getMetaInfo().putToExtra("miui_message_unrecognized", PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI);
                    pushService.sendMIPushPacket(ackContainer);
                } catch (Throwable e) {
                    MyLog.e(e);
                    pushService.disconnect(10, e);
                }
            }

            public String getDesc() {
                return "send ack message for unrecognized new miui message.";
            }
        });
    }

    private static void sendAppAbsentAck(final XMPushService pushService, final XmPushActionContainer container, final String realTargetPackage) {
        pushService.executeJob(new Job(4) {
            public void process() {
                try {
                    XmPushActionContainer ackContainer = MIPushEventProcessor.constructAckMessage(pushService, container);
                    PushMetaInfo metaInfo = new PushMetaInfo();
                    metaInfo.setId(container.getMetaInfo().getId());
                    metaInfo.setMessageTs(container.getMetaInfo().getMessageTs());
                    ackContainer.setMetaInfo(metaInfo);
                    ackContainer.getMetaInfo().putToExtra("absent_target_package", realTargetPackage);
                    pushService.sendMIPushPacket(ackContainer);
                } catch (Throwable e) {
                    MyLog.e(e);
                    pushService.disconnect(10, e);
                }
            }

            public String getDesc() {
                return "send app absent ack message for message.";
            }
        });
    }

    private static XmPushActionContainer constructAckMessage(XMPushService pushService, XmPushActionContainer container) {
        XmPushActionAckMessage ackMessage = new XmPushActionAckMessage();
        ackMessage.setAppId(container.getAppid());
        PushMetaInfo metaInfo = container.getMetaInfo();
        if (metaInfo != null) {
            ackMessage.setId(metaInfo.getId());
            ackMessage.setMessageTs(metaInfo.getMessageTs());
            if (!TextUtils.isEmpty(metaInfo.getTopic())) {
                ackMessage.setTopic(metaInfo.getTopic());
            }
        }
        return pushService.generateRequestContainer(container.getPackageName(), container.getAppid(), ackMessage, ActionType.AckMessage);
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
}
