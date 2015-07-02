package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.a.a;
import com.miui.yellowpage.a.b;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.base.model.SearchResultServiceDataEntry;
import com.miui.yellowpage.base.model.SearchResultYellowPageDataEntry;
import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NearbyYellowPageFragment */
class P implements b, k {
    final /* synthetic */ db cg;

    private P(db dbVar) {
        this.cg = dbVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.cg.GD = new a(this.cg.mActivity, this.cg.Gv, "pn", 20, 0);
            this.cg.GD.a(this.cg.hE());
            this.cg.GD.a((b) this);
        }
        return this.cg.GD;
    }

    public void a(Loader loader, BaseResult baseResult) {
        this.cg.Gw.updateData(((S) baseResult).iV);
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        int i2 = 0;
        S s = (S) baseResult;
        if (obj != null && i == 0) {
            JSONObject jSONObject = new JSONObject((String) obj);
            s.iV = new ArrayList();
            if (jSONObject.has("service")) {
                JSONArray jSONArray = jSONObject.getJSONArray("service");
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    SearchResultServiceDataEntry fromJson = SearchResultServiceDataEntry.fromJson(this.cg.mActivity, jSONArray.getString(i3));
                    if (fromJson != null) {
                        s.iV.add(fromJson);
                    }
                }
            }
            if (jSONObject.has("yellowpage")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("yellowpage");
                while (i2 < jSONArray2.length()) {
                    SearchResultYellowPageDataEntry fromJson2 = SearchResultYellowPageDataEntry.fromJson(jSONArray2.getString(i2));
                    if (fromJson2 != null) {
                        s.iV.add(fromJson2);
                    }
                    i2++;
                }
            }
        }
        return s;
    }

    public BaseResult r() {
        return new S(this.cg);
    }

    public BaseResult a(int i, BaseResult baseResult, BaseResult baseResult2, boolean z) {
        baseResult = (S) baseResult;
        S s = (S) baseResult2;
        if (baseResult.iV == null) {
            return s;
        }
        if (s.iV == null) {
            return baseResult;
        }
        s.iV.addAll(0, baseResult.iV);
        return s;
    }

    public g s() {
        return this.cg.hh;
    }
}
