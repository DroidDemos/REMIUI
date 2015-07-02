package com.miui.yellowpage.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

/* compiled from: Ui */
final class h implements OnClickListener {
    final /* synthetic */ Context val$context;
    final /* synthetic */ String val$number;

    h(String str, Context context) {
        this.val$number = str;
        this.val$context = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.intent.action.REMOVE_BLACKLIST");
        intent.putExtra("numbers", new String[]{this.val$number});
        this.val$context.startActivity(intent);
    }
}
