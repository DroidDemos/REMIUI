package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMPushService.Job;
import com.xiaomi.smack.packet.Message;

public class BatchSendMessageJob extends Job {
    private Message[] mMessages;
    private XMPushService pushService;

    public BatchSendMessageJob(XMPushService pushService, Message[] msgs) {
        super(4);
        this.pushService = null;
        this.pushService = pushService;
        this.mMessages = msgs;
    }

    public void process() {
        try {
            this.pushService.batchSendPacket(this.mMessages);
        } catch (Throwable e) {
            MyLog.e(e);
            this.pushService.disconnect(10, e);
        }
    }

    public String getDesc() {
        return "batch send message.";
    }
}
