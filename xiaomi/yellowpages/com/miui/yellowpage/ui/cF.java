package com.miui.yellowpage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.activity.PaymentActivity;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONObject;

/* compiled from: RechargeOrderListFragment */
public class cF implements c {
    final /* synthetic */ dR lL;

    public cF(dR dRVar) {
        this.lL = dRVar;
    }

    public void onPreRequest(int i) {
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        if (i == 2) {
            bb bbVar = (bb) baseResult;
            if (!TextUtils.isEmpty(bbVar.key)) {
                Bundle bundle = new Bundle();
                bundle.putString("order_id", bbVar.lD);
                bundle.putString("security_pay_key", bbVar.key);
                Intent intent = new Intent(this.lL.mActivity, PaymentActivity.class);
                intent.putExtras(bundle);
                this.lL.mActivity.startActivity(intent);
            }
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        ((bb) baseResult).key = new JSONObject((String) obj).getString("security");
        return baseResult;
    }
}
