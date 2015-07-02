package com.miui.yellowpage.business.recharge;

import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.j;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.request.BaseResult.State;
import org.json.JSONObject;

/* compiled from: PaymentResultFragment */
class r implements c {
    final /* synthetic */ C cX;

    private r(C c) {
        this.cX = c;
    }

    public void onPreRequest(int i) {
        this.cX.b(2, null, this.cX.mActivity.getResources().getString(R.string.recharge_payment_result_progress_summary));
        this.cX.il.setEnabled(false);
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        v vVar = (v) baseResult;
        if (i == 1) {
            if (vVar.getState() != State.OK) {
                vVar.title = this.cX.mActivity.getResources().getString(R.string.payment_unknown_status_title);
                vVar.tI = this.cX.mActivity.getResources().getString(R.string.payment_unknown_status_summary);
            }
            this.cX.b(vVar.tH, vVar.title, vVar.tI);
        }
        this.cX.il.setEnabled(true);
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        v vVar = (v) baseResult;
        Log.d("MiPayPaymentResultFragment", "Order info " + obj);
        if (i == 1) {
            j f = j.f(new JSONObject((String) obj));
            if (f == null) {
                vVar.tH = 2;
                vVar.title = this.cX.mActivity.getResources().getString(R.string.payment_unknown_status_title);
                vVar.tI = this.cX.mActivity.getResources().getString(R.string.payment_unknown_status_summary);
            } else {
                int dl = f.dl();
                if (dl == 2 || dl == 4 || dl == 3) {
                    vVar.tH = 0;
                    vVar.title = this.cX.mActivity.getResources().getString(R.string.recharge_payment_result_ok_title, new Object[]{f.di()});
                    vVar.tI = this.cX.mActivity.getResources().getString(R.string.recharge_payment_result_ok_summary);
                } else {
                    vVar.tH = 1;
                    vVar.title = this.cX.mActivity.getResources().getString(R.string.recharge_payment_result_failed_title, new Object[]{f.di()});
                    vVar.tI = this.cX.mActivity.getResources().getString(R.string.recharge_payment_result_failed_summary);
                }
            }
        }
        return vVar;
    }
}
