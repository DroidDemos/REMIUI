package com.miui.yellowpage.ui;

import android.content.Loader;
import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: CityPickerFragment */
class cH implements k {
    final /* synthetic */ bD ge;

    private cH(bD bDVar) {
        this.ge = bDVar;
    }

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 0) {
            this.ge.yr = new p(this.ge.getActivity(), this.ge.yp);
            this.ge.yr.a(this.ge.fX());
        }
        return this.ge.yr;
    }

    public void a(Loader loader, BaseResult baseResult) {
        cU cUVar = (cU) baseResult;
        if (cUVar != null && cUVar.hasData()) {
            Bundle bundle = new Bundle();
            bundle.putInt("picker_index_target", 2);
            bundle.putString("picker_recommend_presentation_text", this.ge.getString(R.string.navigation_search_all_cities));
            bundle.putString("picker_recommend_section_text", this.ge.getString(R.string.navigation_search_hot_cities));
            bundle.putStringArrayList("picker_recommend_presentation", cUVar.Gj);
            bundle.putStringArrayList("picker_presentation", cUVar.Gl);
            bundle.putStringArrayList("picker_recommend_backend_data", cUVar.Gk);
            bundle.putStringArrayList("picker_backend_data", cUVar.Gm);
            this.ge.yA = true;
            this.ge.a(this.ge.mRoot, bundle);
        }
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        cU cUVar = (cU) baseResult;
        if (i == 0 && obj != null) {
            ArrayList arrayList;
            JSONObject jSONObject = new JSONObject((String) obj);
            Log.d("CityPickerFragment", "cities length:" + jSONObject.length());
            if (jSONObject.has("hot")) {
                JSONArray jSONArray = jSONObject.getJSONArray("hot");
                arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                a(jSONArray, arrayList, arrayList2);
                cUVar.Gj = arrayList;
                cUVar.Gk = arrayList2;
            }
            if (jSONObject.has("cityList")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("cityList");
                ArrayList arrayList3 = new ArrayList();
                arrayList = new ArrayList();
                a(jSONArray2, arrayList3, arrayList);
                cUVar.Gl = arrayList3;
                cUVar.Gm = arrayList;
            }
        }
        return cUVar;
    }

    private void a(JSONArray jSONArray, ArrayList arrayList, ArrayList arrayList2) {
        Object arrayList3 = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            arrayList3.add(new Q(this.ge, jSONObject.getString("locName"), jSONObject.getString("locEngName"), jSONObject.getString("locShortName"), jSONObject.getLong("locId")));
        }
        Collections.sort(arrayList3);
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            Q q = (Q) it.next();
            arrayList.add(q.getName());
            arrayList2.add(q.aO());
        }
    }

    public BaseResult r() {
        return new cU(this.ge);
    }

    public g s() {
        return this.ge.yB;
    }
}
