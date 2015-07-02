package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.utils.ExtendedData;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.utils.e;
import java.util.ArrayList;
import java.util.Iterator;
import miui.yellowpage.YellowPagePhone;
import org.json.JSONObject;

/* compiled from: YellowPageFragment */
class cN implements k {
    final /* synthetic */ Y iu;

    private cN(Y y) {
        this.iu = y;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            this.iu.mLoader = new p(this.iu.mActivity, this.iu.lc);
            this.iu.mLoader.g(this.iu.bk());
        }
        return this.iu.mLoader;
    }

    public void a(Loader loader, BaseResult baseResult) {
        ca caVar = (ca) baseResult;
        if (caVar.Cd != null) {
            if (caVar.Cf != null) {
                if (caVar.Cd.getPhones() == null) {
                    caVar.Cd.setPhones(new ArrayList());
                }
                Iterator it = caVar.Cf.iterator();
                while (it.hasNext()) {
                    YellowPagePhone yellowPagePhone = (YellowPagePhone) it.next();
                    if (caVar.Cd.getPhoneInfo(this.iu.mActivity, yellowPagePhone.getNumber()) == null) {
                        caVar.Cd.getPhones().add(yellowPagePhone);
                    }
                }
            }
            this.iu.lf = caVar.Cd.getMiId();
        }
        this.iu.lh = caVar.Cd;
        this.iu.li = caVar.Ce;
        new L(this.iu).execute(new Void[0]);
        if (this.iu.lb != null) {
            this.iu.lb.b(caVar.Cd);
        }
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        ca caVar = (ca) baseResult;
        if (!(obj == null || TextUtils.isEmpty((String) obj))) {
            String str = (String) obj;
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("yp")) {
                caVar.Cd = YellowPage.fromJson(jSONObject.getString("yp"));
            } else {
                caVar.Cd = YellowPage.fromJson(str);
            }
        }
        if (caVar.Cd != null) {
            this.iu.ld = caVar.Cd.getId();
            if (caVar.Cf == null) {
                caVar.Cf = e.c(this.iu.mActivity, this.iu.ld);
            }
            caVar.Ce = ExtendedData.getYellowPageExtendedData(this.iu.mActivity, this.iu.ld, !z);
        }
        return caVar;
    }

    public BaseResult r() {
        return new ca(this.iu);
    }

    public g s() {
        return this.iu.ll ? null : this.iu.mLoadingProgressView;
    }
}
