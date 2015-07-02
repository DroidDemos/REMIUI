package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;

public class DetailsTextLayout extends LinearLayout {
    private TextView mContentView;
    private int mCurrentMaxLines;
    private int mDefaultMaxLines;
    private MetricsListener mMetricsListener;
    private int mPrevWidth;

    public interface MetricsListener {
        void metricsAvailable(int i, int i2);
    }

    public DetailsTextLayout(Context context) {
        super(context);
    }

    public DetailsTextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mContentView = (TextView) findViewById(R.id.section_content);
    }

    public void setMetricsListener(MetricsListener metricsListener) {
        this.mMetricsListener = metricsListener;
    }

    public void setDefaultMaxLines(int defaultMaxLines) {
        this.mDefaultMaxLines = defaultMaxLines;
        setCurrentMaxLines(defaultMaxLines);
    }

    public void setCurrentMaxLines(int currentMaxLines) {
        this.mContentView.setMaxLines(currentMaxLines);
        this.mCurrentMaxLines = currentMaxLines;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.mMetricsListener != null) {
            int width = r - l;
            if (width > 0 && this.mPrevWidth != width) {
                this.mContentView.setMaxLines(Integer.MAX_VALUE);
                int widthSpec = MeasureSpec.makeMeasureSpec(this.mContentView.getMeasuredWidth(), 1073741824);
                this.mContentView.measure(widthSpec, 0);
                int fullHeight = this.mContentView.getMeasuredHeight();
                this.mContentView.setMaxLines(this.mDefaultMaxLines);
                this.mContentView.measure(widthSpec, 0);
                int truncatedHeight = this.mContentView.getMeasuredHeight();
                this.mContentView.setMaxLines(this.mCurrentMaxLines);
                this.mContentView.measure(widthSpec, 0);
                this.mMetricsListener.metricsAvailable(fullHeight, truncatedHeight);
                this.mPrevWidth = width;
            }
        }
    }
}
