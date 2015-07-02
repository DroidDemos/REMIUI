package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.android.vending.R;

public class DetailsSummaryDynamic extends ViewGroup {
    private View mButtonContainer;
    private View mDownloadProgressPanel;
    private View mSummaryStatus;

    public DetailsSummaryDynamic(Context context) {
        this(context, null);
    }

    public DetailsSummaryDynamic(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mButtonContainer = findViewById(R.id.button_container);
        this.mDownloadProgressPanel = findViewById(R.id.download_progress_panel);
        this.mSummaryStatus = findViewById(R.id.summary_dynamic_status);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        this.mButtonContainer.measure(widthMeasureSpec, heightMeasureSpec);
        int width = this.mButtonContainer.getMeasuredWidth();
        int height = this.mButtonContainer.getMeasuredHeight();
        int zeroWidthSpec = MeasureSpec.makeMeasureSpec(0, 1073741824);
        if (this.mDownloadProgressPanel.getVisibility() == 0 && widthMode == 1073741824) {
            this.mDownloadProgressPanel.measure(widthMeasureSpec, heightMeasureSpec);
        } else {
            this.mDownloadProgressPanel.measure(zeroWidthSpec, heightMeasureSpec);
        }
        width = Math.max(width, this.mDownloadProgressPanel.getMeasuredWidth());
        height = Math.max(height, this.mDownloadProgressPanel.getMeasuredHeight());
        if (this.mSummaryStatus.getVisibility() == 0 && widthMode == 1073741824) {
            this.mSummaryStatus.measure(widthMeasureSpec, heightMeasureSpec);
        } else {
            this.mSummaryStatus.measure(zeroWidthSpec, heightMeasureSpec);
        }
        setMeasuredDimension(Math.max(width, this.mSummaryStatus.getMeasuredWidth()), Math.max(height, this.mSummaryStatus.getMeasuredHeight()));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
            }
        }
    }
}
