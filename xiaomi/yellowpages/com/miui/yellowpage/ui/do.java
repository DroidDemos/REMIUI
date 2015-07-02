package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.miui.yellowpage.base.reference.RefMethods.IntentScope;
import com.miui.yellowpage.model.ExpressOrder.Cargo;

/* compiled from: ExpressOrderItemFragment */
class do implements OnClickListener {
    final /* synthetic */ Cargo Im;
    final /* synthetic */ ExpressOrderItemFragment hJ;
    final /* synthetic */ String val$number;

    do(ExpressOrderItemFragment expressOrderItemFragment, String str, Cargo cargo) {
        this.hJ = expressOrderItemFragment;
        this.val$number = str;
        this.Im = cargo;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.hJ.mActivity.startActivity(IntentScope.processIntentScope(this.hJ.mActivity, new Intent("android.intent.action.CALL_PRIVILEGED", Uri.fromParts("tel", this.val$number, null)), IntentScope.PACKAGE_NAME_PHONE));
        if (this.hJ.a(this.Im.iO(), this.Im.iU(), this.Im.iR())) {
            this.hJ.C();
        }
    }
}
