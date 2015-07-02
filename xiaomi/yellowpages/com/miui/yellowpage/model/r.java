package com.miui.yellowpage.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: CallNote */
final class r implements OnClickListener {
    final /* synthetic */ Long Ai;
    final /* synthetic */ Context val$context;

    r(Long l, Context context) {
        this.Ai = l;
        this.val$context = context;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.putExtra("android.intent.extra.UID", this.Ai);
        intent.setType("vnd.android.cursor.item/call_note");
        this.val$context.startActivity(intent);
    }
}
