package com.miui.yellowpage.ui;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/* compiled from: BaseWebFragment */
class be implements DownloadListener {
    final /* synthetic */ bw this$0;

    private be(bw bwVar) {
        this.this$0 = bwVar;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }
}
