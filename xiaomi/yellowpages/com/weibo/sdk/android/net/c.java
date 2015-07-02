package com.weibo.sdk.android.net;

import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.d;

/* compiled from: AsyncWeiboRunner */
class c extends Thread {
    private final /* synthetic */ String nu;
    private final /* synthetic */ String nv;
    private final /* synthetic */ d nw;
    private final /* synthetic */ b nx;

    c(String str, String str2, d dVar, b bVar) {
        this.nu = str;
        this.nv = str2;
        this.nw = dVar;
        this.nx = bVar;
    }

    public void run() {
        try {
            this.nx.T(f.a(this.nu, this.nv, this.nw, this.nw.getValue("pic")));
        } catch (WeiboException e) {
            this.nx.a(e);
        }
    }
}
