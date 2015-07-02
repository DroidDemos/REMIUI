package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.c;

public class RechargeOrderListPendingItem extends RechargeOrderListItem {
    private c hM;
    private OnClickListener hN;
    private Button hO;

    public RechargeOrderListPendingItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.hO = (Button) findViewById(R.id.pay_button);
    }

    public c aG() {
        return this.hM;
    }

    public void a(c cVar, OnClickListener onClickListener) {
        this.hM = cVar;
        this.hN = onClickListener;
        if (this.hN != null) {
            this.hO.setOnClickListener(this.hN);
        }
        super.a(cVar);
    }
}
