package com.miui.yellowpage.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import java.util.regex.Matcher;

/* compiled from: ExpressInquiryFragment */
class ba implements TextWatcher {
    final /* synthetic */ ExpressInquiryFragment hP;

    private ba(ExpressInquiryFragment expressInquiryFragment) {
        this.hP = expressInquiryFragment;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        Matcher matcher = ExpressInquiryFragment.cY.matcher(editable);
        this.hP.dj = true;
        while (matcher.find()) {
            this.hP.dj = false;
            editable.setSpan(new ForegroundColorSpan(-65536), matcher.start(), matcher.end(), 33);
        }
        if (!this.hP.dj || TextUtils.isEmpty(editable)) {
            this.hP.da.setEnabled(false);
        } else {
            this.hP.da.setEnabled(true);
        }
    }
}
