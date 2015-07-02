package com.miui.yellowpage;

import android.content.Context;
import com.miui.yellowpage.base.mipub.MiPubDeviceManager;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.base.utils.XiaomiAccount;
import com.miui.yellowpage.base.utils.XiaomiAccount.LoginCallBack;
import com.xiaomi.accountsdk.activate.ActivateManager;
import miui.external.h;

/* compiled from: YellowPageApp */
public class e extends h {
    private LoginCallBack CO;

    public e() {
        this.CO = new f(this);
    }

    public void onCreate() {
        super.onCreate();
        ActivateManager.setActivateServiceHostPackage("com.xiaomi.xmsf");
        init();
        XiaomiAccount.registerLoginCallBackListener(getApplicationContext(), this.CO);
    }

    private void init() {
        ThreadPool.execute(new g(this));
    }

    private void gR() {
        Context applicationContext = getApplicationContext();
        if (XiaomiAccount.hasLogin(applicationContext)) {
            MiPubDeviceManager.getInstance(applicationContext).isDeviceLogin();
        }
    }
}
