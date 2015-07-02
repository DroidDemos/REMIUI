package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.model.g;
import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ExpressInquiryProgressFragment */
class cq implements k {
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    private cq(ExpressInquiryProgressFragment expressInquiryProgressFragment) {
        this.mY = expressInquiryProgressFragment;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            this.mY.mLoader = new p(this.mY.JP, this.mY.sr);
            this.mY.mLoader.a(this.mY.cy());
        }
        return this.mY.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        this.mY.ss = (di) baseResult;
        if (!baseResult.hasData()) {
            return;
        }
        if (this.mY.ss.HZ.size() == 1) {
            this.mY.dV();
            this.mY.sA = this.mY.ss.mState;
            if (!this.mY.sL.W() && TextUtils.isEmpty(this.mY.dh)) {
                this.mY.dh = this.mY.aL(this.mY.dg);
            }
            if (TextUtils.isEmpty(this.mY.dh)) {
                this.mY.dh = this.mY.getString(R.string.express_inquiry);
            }
            this.mY.JP.setTitle(this.mY.dh);
            this.mY.sM = new g(this.mY.dg, this.mY.dh, this.mY.iS, 0, this.mY.sA);
            this.mY.dO();
            ThreadPool.execute(new as(this));
            return;
        }
        this.mY.e(this.mY.ss.HZ);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        di diVar = (di) baseResult;
        if (1 == i && obj != null) {
            JSONObject jSONObject = new JSONObject((String) obj);
            diVar.HZ = c(jSONObject.getJSONArray("suggestion"));
            JSONObject jSONObject2 = null;
            if (diVar.HZ.size() == 1) {
                jSONObject2 = jSONObject.getJSONObject("info");
            }
            if (jSONObject2 != null) {
                if (!this.mY.sL.X()) {
                    this.mY.dg = jSONObject2.getString("com");
                }
                diVar.mStatus = jSONObject2.getInt(MiniDefine.b);
                diVar.HW = jSONObject2.optString("state");
                diVar.HX = jSONObject2.optJSONArray("details");
                diVar.iS = jSONObject2.optString("sn");
                diVar.mState = jSONObject2.optInt("state_nu");
                diVar.HY = jSONObject.optInt("trace");
            }
        }
        return diVar;
    }

    public BaseResult r() {
        return new di(this.mY);
    }

    public com.miui.yellowpage.a.g s() {
        return this.mY.mLoadingProgressView;
    }

    private List c(JSONArray jSONArray) {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }
}
