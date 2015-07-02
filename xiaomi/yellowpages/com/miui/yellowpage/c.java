package com.miui.yellowpage;

import com.miui.yellowpage.base.utils.HostManager;

/* compiled from: YellowPageApp */
class c implements Runnable {
    final /* synthetic */ f CH;

    c(f fVar) {
        this.CH = fVar;
    }

    public void run() {
        HostManager.setLoginCookie(this.CH.DA);
    }
}
