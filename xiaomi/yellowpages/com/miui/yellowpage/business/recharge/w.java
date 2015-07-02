package com.miui.yellowpage.business.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.b;
import com.miui.yellowpage.model.i;
import com.miui.yellowpage.model.j;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONObject;

/* compiled from: RechargeFragment */
class w implements c {
    final /* synthetic */ D dw;

    private w(D d) {
        this.dw = d;
    }

    public void onPreRequest(int i) {
        if (i == 2) {
            this.dw.cV();
        }
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        F f = (F) baseResult;
        if (i == this.dw.da().hashCode()) {
            if (f.lC) {
                this.dw.qr.setText(this.dw.mActivity.getResources().getString(R.string.recharge_charge_value, new Object[]{f.lB}));
                this.dw.DM.lB = f.lB;
                return;
            }
            b C = this.dw.da();
            this.dw.qr.setText(this.dw.mActivity.getResources().getString(R.string.recharge_charge_range, new Object[]{C.aa(), C.Z()}));
            if (!TextUtils.isEmpty(f.message) && this.dw.qH) {
                Toast.makeText(this.dw.mActivity, f.message, 0).show();
            }
        } else if (i == 2) {
            if (TextUtils.isEmpty(f.lD)) {
                if (!TextUtils.isEmpty(f.message)) {
                    Toast.makeText(this.dw.mActivity, f.message, 0).show();
                }
                this.dw.r(true);
                return;
            }
            this.dw.mRequestLoader.a(this.dw.at(f.lD), new F(this.dw));
            this.dw.DM.lD = f.lD;
        } else if (i == 3) {
            if (f.nn != null && f.nn.isValid()) {
                this.dw.DM.nn = f.nn;
                Bundle bundle = new Bundle();
                bundle.putString("order_id", this.dw.DM.lD);
                bundle.putString("pay_type", this.dw.DM.nn.cC());
                bundle.putString("security_pay_key", this.dw.DM.nn.cB());
                Intent intent = new Intent(this.dw.mActivity, PaymentActivity.class);
                intent.putExtras(bundle);
                this.dw.mActivity.startActivity(intent);
            }
        } else if (i != 4) {
        } else {
            if (TextUtils.isEmpty(f.lB)) {
                this.dw.r(true);
                return;
            }
            this.dw.DM.lB = f.lB;
            this.dw.db();
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        F f = (F) baseResult;
        JSONObject jSONObject = new JSONObject((String) obj);
        if (i == this.dw.da().hashCode()) {
            f.lC = jSONObject.getBoolean("boolean");
            if (f.lC) {
                f.lB = jSONObject.optString("sellprice");
            } else {
                f.message = jSONObject.getString("message");
            }
        } else if (i == 2) {
            if (jSONObject.getBoolean("boolean")) {
                Object string = jSONObject.getString("order_id");
                if (!TextUtils.isEmpty(string)) {
                    f.lD = string;
                    Log.d("MiPayRechargeFragment", "The order id is " + f.lD);
                }
            } else {
                f.message = jSONObject.getString("message");
            }
        } else if (i == 3) {
            f.nn = i.e(jSONObject);
        } else if (i == 4) {
            j f2 = j.f(jSONObject);
            if (f2 != null) {
                f.lB = f2.dh();
            }
        }
        return f;
    }
}
