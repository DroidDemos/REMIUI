package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.v;

public class RechargeLocalHistoryListItem extends RelativeLayout {
    private TextView bQ;
    private TextView gx;
    private ImageView gy;
    private c gz;

    public RechargeLocalHistoryListItem(Context context) {
        super(context);
    }

    public RechargeLocalHistoryListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RechargeLocalHistoryListItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.bQ = (TextView) findViewById(R.id.name);
        this.gx = (TextView) findViewById(R.id.phone);
        this.gy = (ImageView) findViewById(R.id.remove);
    }

    public void b(v vVar) {
        this.bQ.setText(vVar.getName());
        this.gx.setText(vVar.getPhone());
        if (vVar.im()) {
            this.gy.setVisibility(4);
        } else if (this.gz != null) {
            this.gy.setVisibility(0);
            this.gy.setOnClickListener(new ar(this, vVar));
        } else {
            this.gy.setVisibility(4);
        }
    }

    public void a(c cVar) {
        this.gz = cVar;
    }
}
