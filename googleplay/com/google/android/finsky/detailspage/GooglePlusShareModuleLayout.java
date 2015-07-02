package com.google.android.finsky.detailspage;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;

public class GooglePlusShareModuleLayout extends RelativeLayout {
    private boolean mHasBinded;
    private TextView mHeader;
    private ProgressBar mProgress;
    private TextView mSubHeader;

    public GooglePlusShareModuleLayout(Context context) {
        this(context, null);
    }

    public GooglePlusShareModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHeader = (TextView) findViewById(R.id.header);
        this.mSubHeader = (TextView) findViewById(R.id.subheader);
        this.mProgress = (ProgressBar) findViewById(R.id.loading_progress);
    }

    public void bind(String headerText, String subHeaderText, OnClickListener clickListener, boolean isRequestingSkyJamAuth) {
        if (isRequestingSkyJamAuth || (!isRequestingSkyJamAuth && this.mProgress.getVisibility() == 0)) {
            updateProgressBar(isRequestingSkyJamAuth);
            return;
        }
        if (!TextUtils.isEmpty(headerText)) {
            this.mHeader.setText(headerText);
        }
        if (!TextUtils.isEmpty(subHeaderText)) {
            this.mSubHeader.setText(subHeaderText);
        }
        if (clickListener != null) {
            setOnClickListener(clickListener);
        }
        this.mHasBinded = true;
    }

    public boolean hasBinded() {
        return this.mHasBinded;
    }

    private void updateProgressBar(boolean isRequestingSkyJamAuth) {
        this.mProgress.setVisibility(isRequestingSkyJamAuth ? 0 : 8);
    }
}
