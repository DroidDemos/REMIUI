package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.FlowOfPackages.Packages;

public class FlowOfPackagesItem extends LinearLayout {
    private RadioButton Kg;
    private TextView fJ;
    private TextView mTitle;

    public FlowOfPackagesItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitle = (TextView) findViewById(R.id.title);
        this.fJ = (TextView) findViewById(R.id.subtitle);
        this.Kg = (RadioButton) findViewById(R.id.button);
    }

    public void a(Packages packages, int i, int i2) {
        this.mTitle.setText(packages.getTitle());
        CharSequence subTitle = packages.getSubTitle();
        if (TextUtils.isEmpty(subTitle)) {
            this.fJ.setVisibility(8);
            return;
        }
        this.fJ.setVisibility(0);
        this.fJ.setText(subTitle);
    }

    public void E(boolean z) {
        this.Kg.setChecked(z);
    }
}
