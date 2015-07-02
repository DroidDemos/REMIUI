package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.android.vending.R;

public class ErrorFooter extends IdentifiableViewGroup {
    private TextView mErrorMessage;
    private Button mRetryButton;

    public ErrorFooter(Context context) {
        this(context, null);
    }

    public ErrorFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mErrorMessage = (TextView) findViewById(R.id.error_message);
        this.mRetryButton = (Button) findViewById(R.id.retry_button);
    }

    public void configure(String errorText, OnClickListener retryListener) {
        this.mErrorMessage.setText(errorText);
        this.mRetryButton.setText(R.string.network_retry);
        this.mRetryButton.setOnClickListener(retryListener);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int contentWidth = (width - getPaddingLeft()) - getPaddingRight();
        this.mRetryButton.measure(MeasureSpec.makeMeasureSpec(contentWidth / 2, Integer.MIN_VALUE), 0);
        this.mErrorMessage.measure(MeasureSpec.makeMeasureSpec(contentWidth - this.mRetryButton.getMeasuredWidth(), Integer.MIN_VALUE), 0);
        setMeasuredDimension(width, (getPaddingTop() + getPaddingBottom()) + Math.max(this.mErrorMessage.getMeasuredHeight(), this.mRetryButton.getMeasuredHeight()));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int retryButtonWidth = this.mRetryButton.getMeasuredWidth();
        int retryButtonHeight = this.mRetryButton.getMeasuredHeight();
        int errorMessageWidth = this.mErrorMessage.getMeasuredWidth();
        int errorMessageHeight = this.mErrorMessage.getMeasuredHeight();
        int retryButtonTop = paddingTop + ((((height - paddingTop) - paddingBottom) - retryButtonHeight) / 2);
        this.mRetryButton.layout((width - paddingRight) - retryButtonWidth, retryButtonTop, width - paddingRight, retryButtonTop + retryButtonHeight);
        int errorMessageTop = paddingTop + ((((height - paddingTop) - paddingBottom) - errorMessageHeight) / 2);
        int errorMessageLeft = paddingLeft + (((((width - paddingLeft) - paddingRight) - retryButtonWidth) - errorMessageWidth) / 2);
        this.mErrorMessage.layout(errorMessageLeft, errorMessageTop, errorMessageLeft + errorMessageWidth, errorMessageTop + errorMessageHeight);
    }
}
