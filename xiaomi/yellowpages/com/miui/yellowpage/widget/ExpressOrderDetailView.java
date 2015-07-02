package com.miui.yellowpage.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.model.ExpressOrder;
import com.miui.yellowpage.model.ExpressOrder.Cargo;

public class ExpressOrderDetailView extends LinearLayout {
    private TextView gx;
    private View hX;
    private View hY;
    private View hZ;
    private TextView ia;
    private TextView ib;
    private TextView ic;
    private TextView id;

    public ExpressOrderDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.express_order_detail_view, this, true);
        this.ia = (TextView) findViewById(R.id.express_order_id);
        this.ib = (TextView) findViewById(R.id.express_order_create_time);
        this.hZ = findViewById(R.id.sender_info);
        this.gx = (TextView) findViewById(R.id.phone);
        this.ic = (TextView) findViewById(R.id.sender_address);
        this.id = (TextView) findViewById(R.id.addressee);
        this.hX = findViewById(R.id.sender_placeholder);
        this.hY = findViewById(R.id.addressee_placeholder);
    }

    public void a(ExpressOrder expressOrder) {
        Cargo as = expressOrder.as();
        this.ib.setText(as.iM());
        this.ia.setText(as.iQ());
        ExpressAddress au = expressOrder.au();
        if (ExpressOrder.e(au)) {
            this.hZ.setVisibility(8);
            this.hX.setVisibility(0);
        } else {
            this.hZ.setVisibility(0);
            this.gx.setText(au.getPhone());
            this.ic.setText(au.eV() + " " + au.eQ());
            this.hX.setVisibility(8);
        }
        au = expressOrder.at();
        if (ExpressOrder.e(au)) {
            this.id.setVisibility(8);
            this.hY.setVisibility(0);
            return;
        }
        this.id.setVisibility(0);
        this.id.setText(au.eV());
        this.hY.setVisibility(8);
    }
}
