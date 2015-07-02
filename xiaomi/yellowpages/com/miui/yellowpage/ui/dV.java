package com.miui.yellowpage.ui;

import com.miui.yellowpage.utils.G;

/* compiled from: BaseWebFragment */
class dV implements Runnable {
    private String mUrl;
    final /* synthetic */ bw this$0;

    dV(bw bwVar, String str) {
        this.this$0 = bwVar;
        this.mUrl = str;
    }

    public void run() {
        if (G.ac(this.this$0.mActivity)) {
            G.G(this.this$0.mActivity, this.mUrl);
        }
    }
}
