package com.miui.yellowpage.ui;

import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.cons.GlobalDefine;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONObject;

/* compiled from: ExpressOrderItemFragment */
class D implements c {
    final /* synthetic */ ExpressOrderItemFragment hJ;

    private D(ExpressOrderItemFragment expressOrderItemFragment) {
        this.hJ = expressOrderItemFragment;
    }

    public void onPreRequest(int i) {
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        A a = (A) baseResult;
        if (a.mResult) {
            this.hJ.cQ = true;
            this.hJ.D();
        } else if (!TextUtils.isEmpty(a.mDescription)) {
            Toast.makeText(this.hJ.mActivity, a.mDescription, 0).show();
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        A a = (A) baseResult;
        if (obj != null) {
            JSONObject jSONObject = new JSONObject((String) obj);
            a.mResult = jSONObject.getBoolean(GlobalDefine.g);
            a.mDescription = jSONObject.optString("description");
        }
        return a;
    }
}
