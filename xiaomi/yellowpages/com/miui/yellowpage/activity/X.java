package com.miui.yellowpage.activity;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: YellowPageActivity */
class X implements OnClickListener {
    final /* synthetic */ YellowPageActivity uV;

    X(YellowPageActivity yellowPageActivity) {
        this.uV = yellowPageActivity;
    }

    public void onClick(View view) {
        this.uV.finish();
    }
}
