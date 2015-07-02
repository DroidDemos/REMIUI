package com.miui.yellowpage.ui;

import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.ThreadPool;

/* compiled from: BaseWebFragment */
class cZ implements Runnable {
    final /* synthetic */ String nu;
    final /* synthetic */ bw this$0;

    cZ(bw bwVar, String str) {
        this.this$0 = bwVar;
        this.nu = str;
    }

    public void run() {
        ThreadPool.execute(new w(this.this$0, this.nu));
        if (Files.isLocalUri(this.nu)) {
            Log.d("BaseWebFragment", "local file url, do not monitor");
        } else {
            ThreadPool.execute(new dV(this.this$0, this.nu));
        }
    }
}
