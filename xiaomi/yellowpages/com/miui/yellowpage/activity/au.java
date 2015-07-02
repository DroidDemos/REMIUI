package com.miui.yellowpage.activity;

import android.os.Bundle;
import com.miui.yellowpage.model.ExpressOrder;
import com.miui.yellowpage.ui.SendExpressFragment.OnCreateOrderListener;

/* compiled from: SendExpressActivity */
class au implements OnCreateOrderListener {
    final /* synthetic */ SendExpressActivity bG;

    private au(SendExpressActivity sendExpressActivity) {
        this.bG = sendExpressActivity;
    }

    public void onCreateOrder(String str, ExpressOrder expressOrder) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("extra_express_order", expressOrder);
        bundle.putString("extra_express_order_company_logo_url", str);
        this.bG.showFragment("SendExpressConfirmFragment", null, bundle, true);
    }
}
