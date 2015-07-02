package com.miui.yellowpage.ui;

import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.j;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.request.BaseResult.State;
import org.json.JSONObject;

/* compiled from: PaymentResultFragment */
class cS implements c {
    final /* synthetic */ aq fy;

    private cS(aq aqVar) {
        this.fy = aqVar;
    }

    public void onPreRequest(int i) {
        this.fy.b(2, null, this.fy.mActivity.getResources().getString(R.string.recharge_payment_result_progress_summary));
        this.fy.il.setEnabled(false);
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        aY aYVar = (aY) baseResult;
        if (i == 1) {
            if (aYVar.getState() != State.OK) {
                aYVar.title = this.fy.mActivity.getResources().getString(R.string.payment_unknown_status_title);
                aYVar.tI = this.fy.mActivity.getResources().getString(R.string.payment_unknown_status_summary);
            }
            this.fy.b(aYVar.tH, aYVar.title, aYVar.tI);
        }
        this.fy.il.setEnabled(true);
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        aY aYVar = (aY) baseResult;
        Log.d("PaymentResultFragment", "Order info " + obj);
        if (i == 1) {
            j f = j.f(new JSONObject((String) obj));
            if (f == null) {
                aYVar.tH = 2;
                aYVar.title = this.fy.mActivity.getResources().getString(R.string.payment_unknown_status_title);
                aYVar.tI = this.fy.mActivity.getResources().getString(R.string.payment_unknown_status_summary);
            } else {
                int dl = f.dl();
                if (dl == 2 || dl == 4 || dl == 3) {
                    aYVar.tH = 0;
                    aYVar.title = this.fy.mActivity.getResources().getString(R.string.recharge_payment_result_ok_title, new Object[]{f.di()});
                    aYVar.tI = this.fy.mActivity.getResources().getString(R.string.recharge_payment_result_ok_summary);
                } else {
                    aYVar.tH = 1;
                    aYVar.title = this.fy.mActivity.getResources().getString(R.string.recharge_payment_result_failed_title, new Object[]{f.di()});
                    aYVar.tI = this.fy.mActivity.getResources().getString(R.string.recharge_payment_result_failed_summary);
                }
            }
        }
        return aYVar;
    }
}
