package com.miui.yellowpage.ui;

import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.activity.aj;
import com.miui.yellowpage.activity.y;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.utils.n;

/* compiled from: ExpressInquiryFragment */
class bs extends aj {
    final /* synthetic */ ExpressInquiryFragment hP;

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public bs(ExpressInquiryFragment expressInquiryFragment, Context context) {
        this.hP = expressInquiryFragment;
        super(context);
    }

    public void a(Loader loader, BaseResult baseResult) {
        if (((y) baseResult).hasData()) {
            super.a(loader, baseResult);
        }
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            boolean G = n.G(this.hP.JP);
            this.hP.mLoader = new p(this.hP.JP, this.hP.de);
            BaseRequest a = n.a(this.hP.JP, 1);
            a.setRequestServer(G);
            this.hP.mLoader.a(a);
        }
        return this.hP.mLoader;
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        BaseResult baseResult2 = (y) baseResult;
        if (i == 1) {
            baseResult2 = (y) super.a(i, obj, baseResult, z);
            if (!z) {
                n.F(this.hP.JP);
            }
        }
        return baseResult2;
    }
}
