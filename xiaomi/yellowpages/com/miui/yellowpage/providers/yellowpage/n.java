package com.miui.yellowpage.providers.yellowpage;

import com.miui.yellowpage.base.utils.Files;

/* compiled from: YellowPageDatabaseHelper */
class n implements Runnable {
    final /* synthetic */ q Ah;

    n(q qVar) {
        this.Ah = qVar;
    }

    public void run() {
        Files.copyWebAssetDirectory(this.Ah.mContext.getAssets(), "thumbnails", this.Ah.mContext.getCacheDir().getAbsolutePath());
    }
}
