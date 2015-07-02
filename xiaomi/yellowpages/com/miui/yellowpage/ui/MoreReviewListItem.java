package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Review.Summary;

public class MoreReviewListItem extends LinearLayout {
    private TextView va;

    public MoreReviewListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.va = (TextView) findViewById(R.id.review_count);
    }

    public void a(Summary summary) {
        this.va.setText(getContext().getResources().getQuantityString(R.plurals.review_count, summary.getCount(), new Object[]{Integer.valueOf(summary.getCount())}));
    }
}
