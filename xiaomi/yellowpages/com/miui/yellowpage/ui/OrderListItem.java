package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.reference.RefMethods.DateUtils;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.o;

public class OrderListItem extends RelativeLayout {
    private TextView Ar;
    private TextView As;
    private TextView At;
    private TextView md;
    private TextView mf;

    public OrderListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.Ar = (TextView) findViewById(R.id.provider_name);
        this.As = (TextView) findViewById(R.id.order_title);
        this.At = (TextView) findViewById(R.id.order_short_info);
        this.mf = (TextView) findViewById(R.id.order_date);
        this.md = (TextView) findViewById(R.id.order_status);
    }

    public void a(o oVar) {
        this.As.setText(oVar.gf());
        this.Ar.setText(oVar.getProviderName());
        this.At.setText(oVar.gg());
        Log.d("OrderListItem", "Current time in millis " + System.currentTimeMillis() + " order time " + oVar.gj() + " orderId " + oVar.ac());
        this.mf.setText(DateUtils.getFormattedTime(this.mContext, oVar.gj()));
        this.md.setText(getResources().getString(R.string.order_status, new Object[]{oVar.gh()}));
    }
}
