package com.miui.yellowpage.utils;

import android.content.Context;
import com.miui.yellowpage.base.reference.RefMethods.Licence;

/* compiled from: Ui */
final class l implements s {
    final /* synthetic */ Context val$context;

    l(Context context) {
        this.val$context = context;
    }

    public void onClick() {
        this.val$context.startActivity(Licence.getPrivacyIntent());
    }
}
