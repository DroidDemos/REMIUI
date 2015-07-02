package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.HmsMessageNotification;

public class HmsMessageNotificationListItem extends FrameLayout {
    private TextView so;
    private TextView sp;

    public HmsMessageNotificationListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.so = (TextView) findViewById(R.id.snippet);
        this.sp = (TextView) findViewById(R.id.unread_count);
    }

    public void a(HmsMessageNotification hmsMessageNotification) {
        this.so.setText(hmsMessageNotification.getSnippet());
        if (hmsMessageNotification.getUnreadCount() > 0) {
            this.sp.setVisibility(0);
            this.sp.setText(String.valueOf(hmsMessageNotification.getUnreadCount()));
        } else {
            this.sp.setVisibility(8);
        }
        setOnClickListener(new d(this, hmsMessageNotification));
    }
}
