package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.PushClientsManager.ClientStatus;
import com.xiaomi.push.service.XMPushService.Job;
import java.util.Collection;

public class MIPushAppRegisterJob extends Job {
    private String appId;
    private String appToken;
    private String packageName;
    private byte[] payload;
    private XMPushService pushService;

    public MIPushAppRegisterJob(XMPushService pushService, String packageName, String appId, String appToken, byte[] payload) {
        super(9);
        this.pushService = pushService;
        this.packageName = packageName;
        this.payload = payload;
        this.appId = appId;
        this.appToken = appToken;
    }

    public void process() {
        MIPushAccount account = MIPushAccountUtils.getMIPushAccount(this.pushService);
        if (account == null) {
            try {
                account = MIPushAccountUtils.register(this.pushService, this.packageName, this.appId, this.appToken);
            } catch (Throwable e) {
                MyLog.e(e);
            } catch (Throwable e2) {
                MyLog.e(e2);
            }
        }
        if (account == null) {
            MyLog.e("no account for mipush");
            MIPushClientManager.notifyRegisterError(this.pushService, PushConstants.MIPUSH_ERROR_AUTHERICATION_ERROR, "no account.");
            return;
        }
        ClientLoginInfo loginInfo;
        Collection<ClientLoginInfo> infos = PushClientsManager.getInstance().getAllClientLoginInfoByChid(PushConstants.MIPUSH_CHANNEL);
        if (infos.isEmpty()) {
            loginInfo = account.toClientLoginInfo(this.pushService);
            this.pushService.prepareMIPushClientLoginInfo(loginInfo);
            PushClientsManager.getInstance().addActiveClient(loginInfo);
        } else {
            loginInfo = (ClientLoginInfo) infos.iterator().next();
        }
        if (this.pushService.isConnected()) {
            try {
                if (loginInfo.status == ClientStatus.binded) {
                    this.pushService.sendMIPushPacket(this.packageName, this.payload);
                    return;
                } else if (loginInfo.status == ClientStatus.unbind) {
                    XMPushService xMPushService = this.pushService;
                    XMPushService xMPushService2 = this.pushService;
                    xMPushService2.getClass();
                    xMPushService.executeJob(new BindJob(loginInfo));
                    return;
                } else {
                    return;
                }
            } catch (Throwable e22) {
                MyLog.e(e22);
                this.pushService.disconnect(10, e22);
                return;
            }
        }
        this.pushService.scheduleConnect(true);
    }

    public String getDesc() {
        return "register app";
    }
}
