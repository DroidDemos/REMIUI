package com.google.android.gms.car.support;

import android.content.Context;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

class k extends FrameLayout {
    public k(Context context) {
        super(context);
    }

    static ViewGroup wrap(View child) {
        ViewGroup kVar = new k(child.getContext());
        LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams != null) {
            kVar.setLayoutParams(layoutParams);
        }
        child.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        kVar.addView(child);
        return kVar;
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }
}
