package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPageGroupHeaderEntry;

public class YellowPageGroupHeaderItem extends FrameLayout {
    private TextView bQ;

    public YellowPageGroupHeaderItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.bQ = (TextView) findViewById(R.id.name);
    }

    public void a(YellowPageGroupHeaderEntry yellowPageGroupHeaderEntry) {
        this.bQ.setText(yellowPageGroupHeaderEntry.getName());
    }
}
