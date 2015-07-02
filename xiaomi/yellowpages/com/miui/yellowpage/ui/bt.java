package com.miui.yellowpage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.activity.PaymentActivity;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONObject;

/* compiled from: RechargeOrderDetailFragment */
public class bt implements c {
    final /* synthetic */ af dz;

    public bt(af afVar) {
        this.dz = afVar;
    }

    public void onPreRequest(int i) {
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        if (i == 0) {
            aK aKVar = (aK) baseResult;
            if (!TextUtils.isEmpty(aKVar.key)) {
                Bundle bundle = new Bundle();
                bundle.putString("order_id", aKVar.lD);
                bundle.putString("security_pay_key", aKVar.key);
                Intent intent = new Intent(this.dz.mActivity, PaymentActivity.class);
                intent.putExtras(bundle);
                this.dz.mActivity.startActivity(intent);
            }
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        String string = new JSONObject((String) obj).getString("security");
        aK aKVar = (aK) baseResult;
        aKVar.key = string;
        aKVar.lD = this.dz.mi.ac();
        aKVar.nm = this.dz.mi.di();
        return baseResult;
    }
}
