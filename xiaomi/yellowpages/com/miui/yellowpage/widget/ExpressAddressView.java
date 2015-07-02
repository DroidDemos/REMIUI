package com.miui.yellowpage.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.ExpressAddress;

public class ExpressAddressView extends LinearLayout {
    private TextView Jg;
    private TextView bQ;
    private TextView gx;
    private TextView hd;

    public ExpressAddressView(Context context) {
        super(context);
    }

    public ExpressAddressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.express_address, this, true);
        this.bQ = (TextView) findViewById(R.id.name);
        this.gx = (TextView) findViewById(R.id.phone);
        this.hd = (TextView) findViewById(R.id.area);
        this.Jg = (TextView) findViewById(R.id.detailed_address);
    }

    public void a(ExpressAddress expressAddress, boolean z) {
        CharSequence name;
        CharSequence phone;
        CharSequence eV;
        CharSequence charSequence = null;
        this.bQ.setVisibility(0);
        this.gx.setVisibility(0);
        this.hd.setVisibility(0);
        this.Jg.setVisibility(0);
        if (expressAddress != null) {
            name = expressAddress.getName();
            phone = expressAddress.getPhone();
            eV = expressAddress.eV();
            charSequence = expressAddress.eQ();
        } else {
            eV = null;
            phone = null;
            name = null;
        }
        this.bQ.setText(name);
        this.gx.setText(phone);
        this.hd.setText(eV);
        this.Jg.setText(charSequence);
        if (z) {
            if (TextUtils.isEmpty(name)) {
                this.bQ.setVisibility(8);
            }
            if (TextUtils.isEmpty(phone)) {
                this.gx.setVisibility(8);
            }
            if (TextUtils.isEmpty(eV)) {
                this.hd.setVisibility(8);
            }
            if (TextUtils.isEmpty(charSequence)) {
                this.Jg.setVisibility(8);
            }
        }
    }

    public void h(ExpressAddress expressAddress) {
        a(expressAddress, false);
    }
}
