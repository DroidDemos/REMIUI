package com.miui.yellowpage.utils;

import android.net.Uri;

/* compiled from: WebUploadHandler */
class a implements Runnable {
    final /* synthetic */ b cA;
    final /* synthetic */ String cz;

    a(b bVar, String str) {
        this.cA = bVar;
        this.cz = str;
    }

    public void run() {
        this.cA.fx.GL.onReceiveValue(Uri.parse(this.cz));
    }
}
