package com.miui.yellowpage.ui;

/* compiled from: OrderFragment */
class ao implements Runnable {
    final /* synthetic */ OrderFragment nh;

    ao(OrderFragment orderFragment) {
        this.nh = orderFragment;
    }

    public void run() {
        if (this.nh.mLoader != null && !this.nh.mLoader.iI()) {
            this.nh.mLoader.forceLoad();
        }
    }
}
