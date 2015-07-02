package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.c;

public class RechargeOrderListItem extends RelativeLayout {
    protected TextView ia;
    protected Context mContext;
    protected TextView vC;
    protected TextView vD;
    protected TextView vE;
    protected TextView vF;

    public RechargeOrderListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.vC = (TextView) findViewById(R.id.original_price);
        this.vD = (TextView) findViewById(R.id.phone_number);
        this.vE = (TextView) findViewById(R.id.actual_price);
        this.vF = (TextView) findViewById(R.id.status_description);
        this.ia = (TextView) findViewById(R.id.order_id);
    }

    public void a(c cVar) {
        this.vC.setText(this.mContext.getResources().getString(R.string.recharge_order_original_price, new Object[]{cVar.ad()}));
        this.vD.setText(cVar.getPhoneNumber());
        this.vE.setText(this.mContext.getResources().getString(R.string.recharge_order_actual_price, new Object[]{cVar.ae()}));
        this.vF.setText(cVar.ab());
        this.ia.setText(cVar.ac());
    }
}
