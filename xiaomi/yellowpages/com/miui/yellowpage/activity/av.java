package com.miui.yellowpage.activity;

/* compiled from: NearbyYellowPageActivity */
class av implements O {
    final /* synthetic */ NearbyYellowPageActivity KR;

    av(NearbyYellowPageActivity nearbyYellowPageActivity) {
        this.KR = nearbyYellowPageActivity;
    }

    public void w(boolean z) {
        if (z) {
            this.KR.ro.reload();
        } else {
            this.KR.finish();
        }
    }
}
