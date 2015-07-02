package com.miui.yellowpage;

import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.base.utils.XiaomiAccount.LoginCallBack;

/* compiled from: YellowPageApp */
class f implements LoginCallBack {
    final /* synthetic */ e DA;

    f(e eVar) {
        this.DA = eVar;
    }

    public void onLoginSuccess() {
        Log.d("YellowPageApp", "onLoginSuccess");
        ThreadPool.execute(new c(this));
    }

    public void onLoginFailed() {
        Log.d("YellowPageApp", "onLoginFailed");
    }

    public void onInvalidToken() {
        Log.d("YellowPageApp", "onInvalidToken");
    }

    public void onLogout() {
        Log.d("YellowPageApp", "onLogout");
        ThreadPool.execute(new d(this));
    }
}
