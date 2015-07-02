package com.miui.yellowpage.ui;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONObject;

/* compiled from: ExpressInquiryProgressFragment */
class at implements c {
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    private at(ExpressInquiryProgressFragment expressInquiryProgressFragment) {
        this.mY = expressInquiryProgressFragment;
    }

    public void onPreRequest(int i) {
        this.mY.sF.setEnabled(false);
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        bF bFVar = (bF) baseResult;
        this.mY.sF.setEnabled(true);
        if (bFVar.mResult) {
            this.mY.mLoader.reload();
            if (bFVar.mStatus == 1) {
                if (this.mY.sG == null) {
                    this.mY.sG = Toast.makeText(this.mY.JP, R.string.express_inquiry_trace_success, 0);
                }
                this.mY.sG.show();
                return;
            }
            if (this.mY.sH == null) {
                this.mY.sH = Toast.makeText(this.mY.JP, R.string.express_inquiry_untrace_success, 0);
            }
            this.mY.sH.show();
        } else if (!TextUtils.isEmpty(bFVar.mDescription)) {
            Log.e("ExpressProgressFragment", bFVar.mDescription);
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        bF bFVar = (bF) baseResult;
        if (obj != null) {
            JSONObject jSONObject = new JSONObject((String) obj);
            bFVar.mResult = jSONObject.getBoolean(GlobalDefine.g);
            bFVar.mStatus = jSONObject.getInt(MiniDefine.b);
            if (!bFVar.mResult) {
                bFVar.mDescription = jSONObject.getString("description");
            }
        }
        return bFVar;
    }
}
