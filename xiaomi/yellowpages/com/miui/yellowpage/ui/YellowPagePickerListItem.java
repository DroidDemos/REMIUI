package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.R;

public class YellowPagePickerListItem extends LinearLayout {
    private TextView iK;
    private TextView iL;
    private String iM;
    private int iN;
    private String mData;

    public YellowPagePickerListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.iK = (TextView) findViewById(R.id.presentation);
        this.iL = (TextView) findViewById(R.id.section_header);
    }

    public void a(String str, String str2, int i, String str3) {
        this.iK.setText(str);
        this.iM = str;
        this.mData = str2;
        this.iN = i;
        if (TextUtils.isEmpty(str3)) {
            this.iL.setVisibility(8);
            return;
        }
        this.iL.setText(str3);
        this.iL.setVisibility(0);
    }

    public String getContent() {
        if (this.iN == 1) {
            return this.iM;
        }
        if (this.iN == 2) {
            return this.mData;
        }
        return ConfigConstant.WIRELESS_FILENAME;
    }
}
