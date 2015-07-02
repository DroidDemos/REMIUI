package com.miui.yellowpage;

import com.miui.yellowpage.base.utils.Cache;
import com.miui.yellowpage.base.utils.HostManager;

/* compiled from: YellowPageApp */
class d implements Runnable {
    final /* synthetic */ f CH;

    d(f fVar) {
        this.CH = fVar;
    }

    public void run() {
        HostManager.removeLoginCookie(this.CH.DA.getApplicationContext());
        Cache.clearUserRelatedItems(this.CH.DA.getApplicationContext());
    }
}
