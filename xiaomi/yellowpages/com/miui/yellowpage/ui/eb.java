package com.miui.yellowpage.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: ExpressCompanyListItem */
class eb implements OnClickListener {
    final /* synthetic */ ExpressCompanyListItem ML;

    eb(ExpressCompanyListItem expressCompanyListItem) {
        this.ML = expressCompanyListItem;
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag != null && (tag instanceof String)) {
            String str = (String) tag;
            if (!TextUtils.isEmpty(str)) {
                ExpressCompanyListItem.f(this.ML.mContext, str);
            }
        }
    }
}
