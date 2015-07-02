package com.miui.yellowpage.widget;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.request.BaseResult.State;

/* compiled from: LoadingProgressView */
class c implements OnClickListener {
    final /* synthetic */ State Ew;
    final /* synthetic */ LoadingProgressView Ex;

    c(LoadingProgressView loadingProgressView, State state) {
        this.Ex = loadingProgressView;
        this.Ew = state;
    }

    public void onClick(View view) {
        if (this.Ew == State.NETWORK_ERROR) {
            Intent intent = new Intent("android.settings.SETTINGS");
            intent.addFlags(268435456);
            this.Ex.getContext().startActivity(intent);
        } else if (this.Ex.lG != null) {
            this.Ex.lG.reload();
        }
    }
}
