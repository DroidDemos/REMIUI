package com.miui.yellowpage.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.miui.yellowpage.base.reference.RefMethods.IntentScope;

/* compiled from: ExpressCompanyListItem */
final class ed implements OnClickListener {
    final /* synthetic */ Context val$context;
    final /* synthetic */ String vs;

    ed(Context context, String str) {
        this.val$context = context;
        this.vs = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.val$context.startActivity(IntentScope.processIntentScope(this.val$context, new Intent("android.intent.action.CALL_PRIVILEGED", Uri.fromParts("tel", this.vs, null)), IntentScope.PACKAGE_NAME_PHONE));
    }
}
