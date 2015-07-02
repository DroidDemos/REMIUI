package com.miui.yellowpage.activity;

import android.webkit.WebView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.InternalWebEvent;
import com.miui.yellowpage.base.utils.WebViewConfigurator;

/* compiled from: BaseActivity */
class G implements Runnable {
    private aa th;
    final /* synthetic */ BaseActivity ti;

    public G(BaseActivity baseActivity, aa aaVar) {
        this.ti = baseActivity;
        this.th = aaVar;
    }

    public void run() {
        this.ti.mBannerView = (WebView) this.ti.findViewById(R.id.banner_view);
        WebViewConfigurator.configure(this.ti, this.ti.mBannerView);
        this.ti.mBannerView.addJavascriptInterface(new InternalWebEvent(this.ti, null, new d(this)), "WE");
        this.ti.mBannerView.setWebViewClient(new ak(this.ti, null));
        this.ti.mBannerView.loadUrl(this.th.getUrl());
    }
}
