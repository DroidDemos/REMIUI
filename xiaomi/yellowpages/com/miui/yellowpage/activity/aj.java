package com.miui.yellowpage.activity;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.a.k;
import com.miui.yellowpage.base.model.Module;
import com.miui.yellowpage.request.BaseResult;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: ExpressInquiryActivity */
public abstract class aj implements k {
    private Context mContext;
    private ap zZ;

    public /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (BaseResult) obj);
    }

    public aj(Context context) {
        this.mContext = context;
    }

    public void a(ap apVar) {
        this.zZ = apVar;
    }

    public void a(Loader loader, BaseResult baseResult) {
        if (this.zZ != null) {
            this.zZ.a((y) baseResult);
        }
    }

    public void onLoaderReset(Loader loader) {
    }

    public BaseResult a(int i, Object obj, BaseResult baseResult, boolean z) {
        y yVar = (y) baseResult;
        if (obj != null) {
            Map linkedHashMap = new LinkedHashMap();
            JSONObject jSONObject = new JSONObject((String) obj);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                String string = jSONObject2.getString("pic");
                int optInt = jSONObject2.optInt("index");
                Intent intent = null;
                Module create = Module.create(this.mContext, jSONObject2);
                if (create != null) {
                    intent = Module.filterFirstValidResult(this.mContext, create.getActions());
                }
                linkedHashMap.put(str, new A(string, intent, optInt));
            }
            yVar.map = linkedHashMap;
        }
        return yVar;
    }

    public BaseResult r() {
        return new y();
    }

    public g s() {
        return null;
    }
}
