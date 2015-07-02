package com.miui.yellowpage;

import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.sync.c;
import com.miui.yellowpage.utils.u;

/* compiled from: YellowPageApp */
class g implements Runnable {
    final /* synthetic */ e DA;

    g(e eVar) {
        this.DA = eVar;
    }

    public void run() {
        HostManager.setLoginCookie(this.DA);
        this.DA.gR();
        c.x(this.DA);
        u.x(this.DA);
    }
}
