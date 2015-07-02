package com.miui.yellowpage.ui;

import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.activity.aj;
import com.miui.yellowpage.activity.y;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.utils.n;

/* compiled from: ExpressInquiryProgressFragment */
class aD extends aj {
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public aD(ExpressInquiryProgressFragment expressInquiryProgressFragment, Context context) {
        this.mY = expressInquiryProgressFragment;
        super(context);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 2) {
            this.mY.sv = new p(this.mY.JP, this.mY.st);
            BaseRequest a = n.a(this.mY.JP, 2);
            a.setRequestServer(false);
            this.mY.sv.a(a);
        }
        return this.mY.sv;
    }

    public void a(Loader loader, BaseResult baseResult) {
        if (((y) baseResult).hasData()) {
            super.a(loader, baseResult);
            this.mY.sw.removeAllViews();
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            this.mY.sw.addView(this.mY.sx, layoutParams);
        }
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        baseResult = (y) baseResult;
        if (i == 2) {
            return (y) super.a(i, obj, baseResult, z);
        }
        return baseResult;
    }
}
