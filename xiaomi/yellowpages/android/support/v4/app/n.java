package android.support.v4.app;

import android.view.View;

/* compiled from: Fragment */
class n implements w {
    final /* synthetic */ Fragment vN;

    n(Fragment fragment) {
        this.vN = fragment;
    }

    public View findViewById(int i) {
        if (this.vN.mView != null) {
            return this.vN.mView.findViewById(i);
        }
        throw new IllegalStateException("Fragment does not have a view");
    }
}
