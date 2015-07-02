package com.miui.yellowpage.ui;

import android.text.TextUtils;
import com.miui.yellowpage.request.BaseResult.State;

/* compiled from: NearbyYellowPageFragment */
class av implements Runnable {
    final /* synthetic */ de og;

    av(de deVar) {
        this.og = deVar;
    }

    public void run() {
        if (!this.og.cg.isAdded()) {
            return;
        }
        if (this.og.cg.Gx == null || TextUtils.isEmpty(this.og.cg.Gy)) {
            this.og.cg.hh.onStartLoading(false);
            this.og.cg.hh.a(false, State.LOCATION_ERROR);
        } else if (this.og.cg.GD != null) {
            this.og.cg.GD.forceLoad();
        } else {
            this.og.cg.getLoaderManager().initLoader(0, null, this.og.cg.Gv);
        }
    }
}
