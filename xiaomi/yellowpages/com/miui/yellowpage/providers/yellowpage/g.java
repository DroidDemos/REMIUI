package com.miui.yellowpage.providers.yellowpage;

import android.content.Intent;
import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.utils.I;

/* compiled from: YellowPageDatabaseHelper */
class g implements Runnable {
    final /* synthetic */ q Ah;

    g(q qVar) {
        this.Ah = qVar;
    }

    public void run() {
        Log.d("YellowPageDatabaseHelper", "import web res");
        Files.copyWebAssetDirectory(this.Ah.mContext.getAssets(), "webview", I.ab(this.Ah.mContext));
        this.Ah.mContext.sendBroadcast(new Intent("com.miui.yellowpage.web_page_resources_updated"));
    }
}
