package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.util.TrafficUtils;
import com.xiaomi.xmsf.push.service.log.LogProvider;
import java.util.Collection;
import java.util.Iterator;

public class ClientEventDispatcher {
    private MIPushEventProcessor mPushEventProcessor;

    public ClientEventDispatcher() {
        this.mPushEventProcessor = new MIPushEventProcessor();
    }

    public void notifyServiceStarted(Context context) {
        Intent intent = new Intent();
        intent.setAction(PushConstants.ACTION_SERVICE_STARTED);
        context.sendBroadcast(intent);
    }

    public void notifyChannelOpenResult(Context context, ClientLoginInfo loginInfo, boolean succeeded, int reason, String msg) {
        if (PushConstants.MIPUSH_CHANNEL.equalsIgnoreCase(loginInfo.chid)) {
            this.mPushEventProcessor.processChannelOpenResult(context, loginInfo, succeeded, reason, msg);
            return;
        }
        Intent intent = new Intent();
        intent.setAction(PushConstants.ACTION_CHANNEL_OPENED);
        intent.setPackage(loginInfo.pkgName);
        intent.putExtra(PushConstants.EXTRA_SUCCEEDED, succeeded);
        if (!succeeded) {
            intent.putExtra(PushConstants.EXTRA_REASON, reason);
        }
        if (!TextUtils.isEmpty(msg)) {
            intent.putExtra(PushConstants.EXTRA_REASON_MSG, msg);
        }
        intent.putExtra(PushConstants.EXTRA_CHID, loginInfo.chid);
        intent.putExtra(PushConstants.EXTRA_USER_ID, loginInfo.userId);
        intent.putExtra(PushConstants.EXTRA_SESSION, loginInfo.session);
        sendBroadcast(context, intent, loginInfo.pkgName);
    }

    public void notifyChannelClosed(Context context, ClientLoginInfo info, int reason) {
        if (!PushConstants.MIPUSH_CHANNEL.equalsIgnoreCase(info.chid)) {
            Intent intent = new Intent();
            intent.setAction(PushConstants.ACTION_CHANNEL_CLOSED);
            intent.setPackage(info.pkgName);
            intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, info.chid);
            intent.putExtra(PushConstants.EXTRA_REASON, reason);
            intent.putExtra(PushConstants.EXTRA_USER_ID, info.userId);
            intent.putExtra(PushConstants.EXTRA_SESSION, info.session);
            sendBroadcast(context, intent, info.pkgName);
        }
    }

    public void notifyPacketArrival(XMPushService pushService, String chid, Packet packet) {
        ClientLoginInfo info = getClientLoginInfo(packet);
        if (info == null) {
            MyLog.e("error while notify channel closed! channel " + chid + " not registered");
        } else if (PushConstants.MIPUSH_CHANNEL.equalsIgnoreCase(chid)) {
            this.mPushEventProcessor.processNewPacket(pushService, packet, info);
        } else {
            String action;
            String pkgName = info.pkgName;
            if (packet instanceof Message) {
                action = PushConstants.ACTION_RECEIVE_NEW_MESSAGE;
            } else if (packet instanceof IQ) {
                action = PushConstants.ACTION_RECEIVE_NEW_IQ;
            } else if (packet instanceof Presence) {
                action = PushConstants.ACTION_RECEIVE_NEW_PRESENCE;
            } else {
                MyLog.e("unknown packet type, drop it");
                return;
            }
            Intent intent = new Intent();
            intent.setAction(action);
            intent.setPackage(pkgName);
            intent.putExtra(PushConstants.EXTRA_CHID, chid);
            intent.putExtra(PushConstants.EXTRA_PACKET, packet.toBundle());
            intent.putExtra(PushConstants.EXTRA_SESSION, info.session);
            intent.putExtra(PushConstants.EXTRA_SECURITY, info.security);
            sendBroadcast(pushService, intent, pkgName);
            TrafficUtils.distributionTraffic(pushService, pkgName, (long) TrafficUtils.getTrafficFlow(packet.toXML()), true, System.currentTimeMillis());
        }
    }

    private ClientLoginInfo getClientLoginInfo(Packet packet) {
        Collection<ClientLoginInfo> infos = PushClientsManager.getInstance().getAllClientLoginInfoByChid(packet.getChannelId());
        if (infos.isEmpty()) {
            return null;
        }
        Iterator<ClientLoginInfo> it = infos.iterator();
        if (infos.size() == 1) {
            return (ClientLoginInfo) it.next();
        }
        String from = packet.getFrom();
        String to = packet.getTo();
        while (it.hasNext()) {
            ClientLoginInfo cur = (ClientLoginInfo) it.next();
            if (!TextUtils.equals(from, cur.userId)) {
                if (TextUtils.equals(to, cur.userId)) {
                }
            }
            return cur;
        }
        return null;
    }

    public void notifyKickedByServer(Context context, ClientLoginInfo info, String type, String reason) {
        if (PushConstants.MIPUSH_CHANNEL.equalsIgnoreCase(info.chid)) {
            MyLog.e("mipush kicked by server");
            return;
        }
        String action = PushConstants.ACTION_KICKED_BY_SERVER;
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setPackage(info.pkgName);
        intent.putExtra(PushConstants.EXTRA_KICK_TYPE, type);
        intent.putExtra(PushConstants.EXTRA_KICK_REASON, reason);
        intent.putExtra(PushConstants.EXTRA_CHID, info.chid);
        intent.putExtra(PushConstants.EXTRA_USER_ID, info.userId);
        intent.putExtra(PushConstants.EXTRA_SESSION, info.session);
        sendBroadcast(context, intent, info.pkgName);
    }

    private static void sendBroadcast(Context context, Intent intent, String targetPackage) {
        if (LogProvider.AUTHORITY.equals(context.getPackageName())) {
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(intent, getReceiverPermission(targetPackage));
        }
    }

    public static String getReceiverPermission(String packageName) {
        return packageName + ".permission.MIPUSH_RECEIVE";
    }
}
