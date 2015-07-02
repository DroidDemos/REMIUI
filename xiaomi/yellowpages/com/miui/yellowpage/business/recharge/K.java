package com.miui.yellowpage.business.recharge;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.b;

/* compiled from: RechargeFragment */
class K implements OnCheckedChangeListener {
    final /* synthetic */ D dw;

    K(D d) {
        this.dw = d;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i >= 0 && i < this.dw.DL.DO.size()) {
            this.dw.C(i);
            b bVar = (b) this.dw.DL.DO.get(i);
            this.dw.qr.setText(this.dw.mActivity.getResources().getString(R.string.recharge_charge_range, new Object[]{bVar.aa(), bVar.Z()}));
            if (this.dw.au(this.dw.qo.getText().toString())) {
                this.dw.mRequestLoader.a(this.dw.cX(), new F(this.dw));
            }
        }
    }
}
