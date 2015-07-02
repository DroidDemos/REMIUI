package com.miui.yellowpage.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import java.io.File;

/* compiled from: CallRecord */
final class u implements OnClickListener {
    final /* synthetic */ String GJ;
    final /* synthetic */ Context val$context;

    u(String str, Context context) {
        this.GJ = str;
        this.val$context = context;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setClassName("com.android.soundrecorder", "com.android.soundrecorder.SoundRecorder");
        intent.setData(Uri.fromFile(new File(this.GJ)));
        this.val$context.startActivity(intent);
    }
}
