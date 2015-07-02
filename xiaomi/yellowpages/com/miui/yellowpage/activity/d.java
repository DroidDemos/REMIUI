package com.miui.yellowpage.activity;

import com.miui.yellowpage.base.utils.BaseWebEvent.CurrentUrlSource;

/* compiled from: BaseActivity */
class d implements CurrentUrlSource {
    final /* synthetic */ G gZ;

    d(G g) {
        this.gZ = g;
    }

    public String getCurrentUrl() {
        return this.gZ.ti.mBannerView.getUrl();
    }
}
