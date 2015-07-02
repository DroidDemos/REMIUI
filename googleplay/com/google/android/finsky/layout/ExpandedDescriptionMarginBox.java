package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.android.vending.R;
import com.google.android.finsky.utils.UiUtils;

public class ExpandedDescriptionMarginBox extends FrameLayout {
    private DetailsExpandedFrame mDetailsExpandedFrame;
    private final boolean mUseWideLayout;

    public ExpandedDescriptionMarginBox(Context context) {
        this(context, null);
    }

    public ExpandedDescriptionMarginBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUseWideLayout = context.getResources().getBoolean(R.bool.use_wide_details_layout);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mDetailsExpandedFrame = (DetailsExpandedFrame) findViewById(R.id.details_expanded_frame);
        this.mDetailsExpandedFrame.setVisibility(0);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int contentColumnWidth;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (this.mUseWideLayout) {
            contentColumnWidth = UiUtils.getGridColumnContentWidth(getResources());
        } else {
            contentColumnWidth = width;
        }
        this.mDetailsExpandedFrame.setScrollerWidth(Math.min(width, contentColumnWidth));
        this.mDetailsExpandedFrame.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, 1073741824));
        setMeasuredDimension(width, height);
    }
}
