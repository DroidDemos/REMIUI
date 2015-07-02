package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.webkit.WebView;
import com.miui.yellowpage.base.utils.BusinessStatistics;

/* compiled from: BaseWebFragment */
class bX implements OnClickListener {
    final /* synthetic */ WebView BN;
    final /* synthetic */ bi BO;
    final /* synthetic */ Intent cT;
    final /* synthetic */ String val$number;

    bX(bi biVar, Intent intent, String str, WebView webView) {
        this.BO = biVar;
        this.cT = intent;
        this.val$number = str;
        this.BN = webView;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.BO.this$0.startActivity(this.cT);
        BusinessStatistics.clickWebViewNumber(this.BO.this$0.mActivity, this.val$number, this.BN.getUrl(), "phone", this.BO.this$0.getStatisticsContext().getSourceModuleId());
    }
}
