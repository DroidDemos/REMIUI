package com.miui.yellowpage.activity;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: YellowPagePickerActivity */
class ar implements OnClickListener {
    final /* synthetic */ YellowPagePickerActivity Id;

    ar(YellowPagePickerActivity yellowPagePickerActivity) {
        this.Id = yellowPagePickerActivity;
    }

    public void onClick(View view) {
        this.Id.finish();
    }
}
