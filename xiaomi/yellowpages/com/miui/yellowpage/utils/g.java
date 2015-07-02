package com.miui.yellowpage.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

/* compiled from: Ui */
final class g implements OnClickListener {
    final /* synthetic */ Context val$context;
    final /* synthetic */ String val$number;

    g(String str, Context context) {
        this.val$number = str;
        this.val$context = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.intent.action.ADD_FIREWALL");
        intent.setType("vnd.android.cursor.item/firewall-blacklist");
        intent.putExtra("numbers", new String[]{this.val$number});
        this.val$context.startActivity(intent);
    }
}
