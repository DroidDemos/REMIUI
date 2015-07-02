package com.google.android.finsky.layout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.layout.play.SingleLineContainer;

public class AuthChallengeDialogDocumentInfoLayout extends SingleLineContainer {
    private TextView mPriceView;
    private TextView mTitleView;

    public AuthChallengeDialogDocumentInfoLayout(Context context) {
        super(context);
    }

    public AuthChallengeDialogDocumentInfoLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitleView = (TextView) findViewById(R.id.item_title);
        this.mPriceView = (TextView) findViewById(R.id.item_price);
    }

    public void setDocumentInfo(String title, String price) {
        int i = 0;
        boolean showLayout = false;
        if (TextUtils.isEmpty(title)) {
            this.mTitleView.setVisibility(8);
        } else {
            this.mTitleView.setText(title);
            this.mTitleView.setVisibility(0);
            showLayout = true;
        }
        if (TextUtils.isEmpty(price)) {
            this.mPriceView.setVisibility(8);
        } else {
            this.mPriceView.setText(price);
            this.mPriceView.setVisibility(0);
            showLayout = true;
        }
        if (!showLayout) {
            i = 8;
        }
        setVisibility(i);
    }
}
