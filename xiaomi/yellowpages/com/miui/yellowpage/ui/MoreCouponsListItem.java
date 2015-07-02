package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Coupon.More;

public class MoreCouponsListItem extends RelativeLayout {
    private Context mContext;
    private TextView rQ;

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.rQ = (TextView) findViewById(R.id.count);
    }

    public MoreCouponsListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void a(More more) {
        this.rQ.setText(this.mContext.getResources().getQuantityString(R.plurals.more_coupons_count, more.getCount(), new Object[]{Integer.valueOf(more.getCount())}));
    }
}
