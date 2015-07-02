package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.g;

public class InquiryHistoryListItem extends RelativeLayout {
    private TextView bR;
    private TextView cG;
    private TextView mTitle;
    private TextView vV;
    private g vW;
    private View vX;

    public InquiryHistoryListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.bR = (TextView) findViewById(R.id.description);
        this.vV = (TextView) findViewById(R.id.serial_number);
        this.vX = findViewById(R.id.status);
        this.cG = (TextView) findViewById(R.id.state);
        this.mTitle = (TextView) findViewById(R.id.title);
    }

    public void a(g gVar, boolean z) {
        int i;
        int i2 = 0;
        CharSequence aS = gVar.aS();
        if (TextUtils.isEmpty(aS)) {
            this.mTitle.setText(gVar.aQ());
            this.bR.setVisibility(8);
        } else {
            this.mTitle.setText(aS);
            this.bR.setVisibility(0);
            this.bR.setText(this.mContext.getString(R.string.phone_info_divider) + gVar.aQ());
        }
        this.vV.setText(gVar.getSerialNumber());
        this.vW = gVar;
        View view = this.vX;
        if (z) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
        TextView textView = this.cG;
        if (!gVar.aR()) {
            i2 = 8;
        }
        textView.setVisibility(i2);
    }

    public g fo() {
        return this.vW;
    }
}
