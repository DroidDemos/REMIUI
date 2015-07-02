package com.miui.yellowpage.activity;

import com.miui.yellowpage.base.model.action.SmsAction;
import com.miui.yellowpage.utils.C;

/* compiled from: MultiModuleIntentActivity */
class u implements C {
    final /* synthetic */ SmsAction mK;
    final /* synthetic */ MultiModuleIntentActivity mL;

    u(MultiModuleIntentActivity multiModuleIntentActivity, SmsAction smsAction) {
        this.mL = multiModuleIntentActivity;
        this.mK = smsAction;
    }

    public void v(int i) {
        this.mL.a(this.mK, i);
        this.mL.finish();
    }

    public void onCancel() {
        this.mL.finish();
    }
}
