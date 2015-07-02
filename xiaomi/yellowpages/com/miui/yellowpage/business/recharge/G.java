package com.miui.yellowpage.business.recharge;

import android.content.Intent;
import android.os.Bundle;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.model.i;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONObject;

/* compiled from: RechargeOrderListFragment */
public class G implements c {
    final /* synthetic */ N eL;

    public G(N n) {
        this.eL = n;
    }

    public void onPreRequest(int i) {
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        if (i == 2) {
            t tVar = (t) baseResult;
            if (tVar.hasData()) {
                Bundle bundle = new Bundle();
                bundle.putString("order_id", tVar.lD);
                bundle.putString("security_pay_key", tVar.nn.cB());
                bundle.putString("pay_type", tVar.nn.cC());
                Intent intent = new Intent(this.eL.mActivity, PaymentActivity.class);
                intent.putExtras(bundle);
                this.eL.mActivity.startActivity(intent);
            }
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        t tVar = (t) baseResult;
        tVar.nn = i.e(new JSONObject((String) obj));
        return tVar;
    }
}
