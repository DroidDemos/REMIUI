package com.miui.yellowpage.ui;

import android.net.Uri;
import android.text.TextUtils;
import com.miui.yellowpage.base.utils.BusinessStatistics;

/* compiled from: BaseWebFragment */
class w implements Runnable {
    private String mUrl;
    final /* synthetic */ bw this$0;

    w(bw bwVar, String str) {
        this.this$0 = bwVar;
        this.mUrl = str;
    }

    public void run() {
        if (!TextUtils.isEmpty(this.mUrl)) {
            Uri parse = Uri.parse(this.mUrl);
            Object queryParameter = parse.getQueryParameter("ypref");
            Object queryParameter2 = parse.getQueryParameter("ypto");
            if (!TextUtils.isEmpty(queryParameter) && !TextUtils.isEmpty(queryParameter2)) {
                BusinessStatistics.uploadOrderRefInfo(this.this$0.mActivity, queryParameter, queryParameter2);
            }
        }
    }
}
