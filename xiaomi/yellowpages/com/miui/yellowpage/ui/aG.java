package com.miui.yellowpage.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.miui.yellowpage.R;

/* compiled from: RechargeFragment */
class aG implements OnClickListener {
    final /* synthetic */ aL ie;

    aG(aL aLVar) {
        this.ie = aLVar;
    }

    public void onClick(View view) {
        Object obj = this.ie.qo.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            Toast.makeText(this.ie.mActivity, R.string.recharge_phone_number_empty, 1).show();
            this.ie.qo.requestFocus();
        } else if (this.ie.au(obj)) {
            this.ie.qG = this.ie.da();
            this.ie.mRequestLoader.a(this.ie.cY(), new Z(this.ie));
            this.ie.r(false);
        } else {
            Toast.makeText(this.ie.mActivity, R.string.recharge_phone_number_error, 1).show();
            this.ie.qo.selectAll();
        }
    }
}
