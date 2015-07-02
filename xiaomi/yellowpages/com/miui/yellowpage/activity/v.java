package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.widget.ArrayAdapter;
import com.miui.yellowpage.base.model.Module.Action;
import com.miui.yellowpage.base.model.action.WebViewAction;

/* compiled from: MultiModuleIntentActivity */
class v implements OnClickListener {
    final /* synthetic */ MultiModuleIntentActivity mL;
    final /* synthetic */ ArrayAdapter mM;

    v(MultiModuleIntentActivity multiModuleIntentActivity, ArrayAdapter arrayAdapter) {
        this.mL = multiModuleIntentActivity;
        this.mM = arrayAdapter;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Action action = (Action) this.mM.getItem(i);
        Intent toIntent = action.toIntent();
        if (action instanceof WebViewAction) {
            this.mL.startActivity(toIntent);
        } else {
            this.mL.startService(toIntent);
        }
        this.mL.a(action);
        this.mL.finish();
    }
}
