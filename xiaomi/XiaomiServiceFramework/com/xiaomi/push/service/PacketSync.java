package com.xiaomi.push.service;

import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.PushClientsManager.ClientStatus;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.XMBinder.BindResult;
import com.xiaomi.smack.XMBinder.BindResult.Type;
import com.xiaomi.smack.XMPPConnection;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.xmsf.push.service.Constants;

public class PacketSync {
    private XMPushService mService;

    public interface PacketReceiveHandler extends Parcelable {
        boolean Handle(Packet packet);
    }

    PacketSync(XMPushService service) {
        this.mService = service;
    }

    public void onPacketReceive(Packet packet) {
        String chid;
        String userId;
        ClientLoginInfo info;
        if (packet instanceof BindResult) {
            BindResult br = (BindResult) packet;
            Type type = br.getType();
            chid = br.getChannelId();
            userId = br.getTo();
            if (!TextUtils.isEmpty(chid)) {
                info = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(chid, userId);
                if (info == null) {
                    return;
                }
                if (type == Type.RESULT) {
                    info.setStatus(ClientStatus.binded, 1, 0, null, null);
                    MyLog.warn("SMACK: channel bind succeeded, chid=" + chid);
                    return;
                }
                XMPPError error = br.getError();
                MyLog.warn("SMACK: channel bind failed, error=" + error.toXML());
                if (error != null) {
                    if ("auth".equals(error.getType())) {
                        info.setStatus(ClientStatus.unbind, 1, 5, error.getReason(), error.getType());
                        PushClientsManager.getInstance().deactivateClient(chid, userId);
                    } else if ("cancel".equals(error.getType())) {
                        info.setStatus(ClientStatus.unbind, 1, 7, error.getReason(), error.getType());
                        PushClientsManager.getInstance().deactivateClient(chid, userId);
                    } else if ("wait".equals(error.getType())) {
                        this.mService.scheduleRebindChannel(info);
                        info.setStatus(ClientStatus.unbind, 1, 7, error.getReason(), error.getType());
                    }
                    MyLog.warn("SMACK: channel bind failed, chid=" + chid + " reason=" + error.getReason());
                    return;
                }
                return;
            }
            return;
        }
        chid = packet.getChannelId();
        if (TextUtils.isEmpty(chid)) {
            chid = PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI;
            packet.setChannelId(chid);
        }
        if (!chid.equals("0")) {
            if (packet instanceof IQ) {
                CommonPacketExtension ext = packet.getExtension("kick");
                if (ext != null) {
                    userId = packet.getTo();
                    String type2 = ext.getAttributeValue(Constants.JSON_TAG_ADSTYPE);
                    String reason = ext.getAttributeValue("reason");
                    MyLog.warn("kicked by server, chid=" + chid + " userid=" + userId + " type=" + type2 + " reason=" + reason);
                    if ("wait".equals(type2)) {
                        info = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(chid, userId);
                        if (info != null) {
                            this.mService.scheduleRebindChannel(info);
                            info.setStatus(ClientStatus.unbind, 3, 0, reason, type2);
                            return;
                        }
                        return;
                    }
                    this.mService.closeChannel(chid, userId, 3, reason, type2);
                    PushClientsManager.getInstance().deactivateClient(chid, userId);
                    return;
                }
            } else if (packet instanceof Message) {
                Message message = (Message) packet;
                if ("redir".equals(message.getType())) {
                    CommonPacketExtension exten = message.getExtension("hosts");
                    if (exten != null) {
                        processRedirectMessage(exten);
                        return;
                    }
                    return;
                }
            }
            this.mService.getClientEventDispatcher().notifyPacketArrival(this.mService, chid, packet);
        } else if ((packet instanceof IQ) && "0".equals(packet.getPacketID()) && "result".equals(((IQ) packet).getType().toString())) {
            Connection cConn = this.mService.getCurrentConnection();
            if (cConn instanceof XMPPConnection) {
                ((XMPPConnection) cConn).updateLastReceived();
            }
        }
    }

    private void processRedirectMessage(CommonPacketExtension exten) {
        String hosts = exten.getText();
        if (!TextUtils.isEmpty(hosts)) {
            String[] splitedHosts = hosts.split(";");
            Fallback fb = HostManager.getInstance().getFallbacksByHost(ConnectionConfiguration.getXmppServerHost());
            if (fb != null && splitedHosts.length > 0) {
                fb.addPreferredHost(splitedHosts);
                this.mService.disconnect(20, null);
                this.mService.scheduleConnect(true);
            }
        }
    }
}
