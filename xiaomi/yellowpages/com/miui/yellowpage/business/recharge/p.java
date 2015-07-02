package com.miui.yellowpage.business.recharge;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.miui.yellowpage.R;

/* compiled from: RechargeFragment */
class p implements OnClickListener {
    final /* synthetic */ D dw;

    p(D d) {
        this.dw = d;
    }

    public void onClick(View view) {
        Object obj = this.dw.qo.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            Toast.makeText(this.dw.mActivity, R.string.recharge_phone_number_empty, 1).show();
            this.dw.qo.requestFocus();
        } else if (this.dw.au(obj)) {
            this.dw.qG = this.dw.da();
            this.dw.mRequestLoader.a(this.dw.cY(), new F(this.dw));
            this.dw.r(false);
        } else {
            Toast.makeText(this.dw.mActivity, R.string.recharge_phone_number_error, 1).show();
            this.dw.qo.selectAll();
        }
    }
}
