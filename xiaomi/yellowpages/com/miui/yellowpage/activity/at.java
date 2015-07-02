package com.miui.yellowpage.activity;

import android.os.Bundle;
import com.miui.yellowpage.model.ExpressOrder;
import com.miui.yellowpage.ui.dc;

/* compiled from: SendExpressActivity */
class at implements dc {
    final /* synthetic */ SendExpressActivity bG;

    private at(SendExpressActivity sendExpressActivity) {
        this.bG = sendExpressActivity;
    }

    public void c(ExpressOrder expressOrder) {
        this.bG.cV();
        Bundle bundle = new Bundle();
        bundle.putSerializable("extra_express_order", expressOrder);
        this.bG.showFragment("SendExpressResultFragment", null, bundle, true);
    }
}
