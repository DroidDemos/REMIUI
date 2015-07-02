package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMPushService.Job;
import com.xiaomi.smack.packet.Packet;

public class SendMessageJob extends Job {
    private Packet mPacket;
    private XMPushService pushService;

    public SendMessageJob(XMPushService pushService, Packet packet) {
        super(4);
        this.pushService = null;
        this.pushService = pushService;
        this.mPacket = packet;
    }

    public void process() {
        try {
            this.pushService.sendPacket(this.mPacket);
        } catch (Throwable e) {
            MyLog.e(e);
            this.pushService.disconnect(10, e);
        }
    }

    public String getDesc() {
        return "send a message.";
    }
}
