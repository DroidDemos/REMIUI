package com.miui.yellowpage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.activity.PaymentActivity;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.b;
import com.miui.yellowpage.model.j;
import com.miui.yellowpage.request.BaseResult;
import org.json.JSONObject;

/* compiled from: RechargeFragment */
class ce implements c {
    final /* synthetic */ aL ie;

    private ce(aL aLVar) {
        this.ie = aLVar;
    }

    public void onPreRequest(int i) {
        if (i == 2) {
            this.ie.cV();
        }
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        Z z = (Z) baseResult;
        if (i == this.ie.da().hashCode()) {
            if (z.lC) {
                this.ie.qr.setText(this.ie.mActivity.getResources().getString(R.string.recharge_charge_value, new Object[]{z.lB}));
                this.ie.qx.lB = z.lB;
                return;
            }
            b u = this.ie.da();
            this.ie.qr.setText(this.ie.mActivity.getResources().getString(R.string.recharge_charge_range, new Object[]{u.aa(), u.Z()}));
            if (!TextUtils.isEmpty(z.message) && this.ie.qH) {
                Toast.makeText(this.ie.mActivity, z.message, 0).show();
            }
        } else if (i == 2) {
            if (TextUtils.isEmpty(z.lD)) {
                if (!TextUtils.isEmpty(z.message)) {
                    Toast.makeText(this.ie.mActivity, z.message, 0).show();
                }
                this.ie.r(true);
                return;
            }
            this.ie.mRequestLoader.a(this.ie.at(z.lD), new Z(this.ie));
            this.ie.qx.lD = z.lD;
        } else if (i == 3) {
            if (!TextUtils.isEmpty(z.lE)) {
                this.ie.qx.lE = z.lE;
                Bundle bundle = new Bundle();
                bundle.putString("order_id", this.ie.qx.lD);
                bundle.putString("security_pay_key", this.ie.qx.lE);
                Intent intent = new Intent(this.ie.mActivity, PaymentActivity.class);
                intent.putExtras(bundle);
                this.ie.mActivity.startActivity(intent);
            }
        } else if (i != 4) {
        } else {
            if (TextUtils.isEmpty(z.lB)) {
                this.ie.r(true);
                return;
            }
            this.ie.qx.lB = z.lB;
            this.ie.db();
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        Z z = (Z) baseResult;
        JSONObject jSONObject = new JSONObject((String) obj);
        if (i == this.ie.da().hashCode()) {
            z.lC = jSONObject.getBoolean("boolean");
            if (z.lC) {
                z.lB = jSONObject.optString("sellprice");
            } else {
                z.message = jSONObject.getString("message");
            }
        } else if (i == 2) {
            if (jSONObject.getBoolean("boolean")) {
                r0 = jSONObject.getString("order_id");
                if (!TextUtils.isEmpty(r0)) {
                    z.lD = r0;
                    Log.d("RechargeFragment", "The order id is " + z.lD);
                }
            } else {
                z.message = jSONObject.getString("message");
            }
        } else if (i == 3) {
            r0 = jSONObject.getString("security");
            if (!TextUtils.isEmpty(r0)) {
                z.lE = r0;
                Log.d("RechargeFragment", "Get security pay key success");
            }
        } else if (i == 4) {
            j f = j.f(jSONObject);
            if (f != null) {
                z.lB = f.dh();
            }
        }
        return z;
    }
}
