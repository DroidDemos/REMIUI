package com.miui.yellowpage.ui;

import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.cons.GlobalDefine;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONObject;

/* compiled from: ExpressInquiryProgressFragment */
class bv implements c {
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    private bv(ExpressInquiryProgressFragment expressInquiryProgressFragment) {
        this.mY = expressInquiryProgressFragment;
    }

    public void onPreRequest(int i) {
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        an anVar = (an) baseResult;
        if (this.mY.sN != null) {
            this.mY.sN.d(anVar.mResult);
        }
        if (!anVar.mResult) {
            if (TextUtils.isEmpty(anVar.mDescription)) {
                Log.e("ExpressProgressFragment", "Attach unknown err.");
            } else {
                Log.e("ExpressProgressFragment", "Attach err: " + anVar.mDescription);
            }
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        an anVar = (an) baseResult;
        if (obj != null) {
            JSONObject jSONObject = new JSONObject((String) obj);
            anVar.mResult = jSONObject.getBoolean(GlobalDefine.g);
            anVar.mDescription = jSONObject.optString("description");
        }
        return anVar;
    }
}
