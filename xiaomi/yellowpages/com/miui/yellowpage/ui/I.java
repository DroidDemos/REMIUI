package com.miui.yellowpage.ui;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.b;

/* compiled from: RechargeFragment */
class I implements OnCheckedChangeListener {
    final /* synthetic */ aL ie;

    I(aL aLVar) {
        this.ie = aLVar;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i >= 0 && i < this.ie.qw.DO.size()) {
            this.ie.C(i);
            b bVar = (b) this.ie.qw.DO.get(i);
            this.ie.qr.setText(this.ie.mActivity.getResources().getString(R.string.recharge_charge_range, new Object[]{bVar.aa(), bVar.Z()}));
            if (this.ie.au(this.ie.qo.getText().toString())) {
                this.ie.mRequestLoader.a(this.ie.cX(), new Z(this.ie));
            }
        }
    }
}
