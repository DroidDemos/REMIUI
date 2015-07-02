package com.xiaomi.snslib;

import android.content.Context;
import android.widget.Toast;

/* compiled from: ShareToSNS */
final class b implements Runnable {
    final /* synthetic */ int mT;
    final /* synthetic */ Context val$context;

    b(Context context, int i) {
        this.val$context = context;
        this.mT = i;
    }

    public void run() {
        Toast.makeText(this.val$context, this.mT, 0).show();
    }
}
