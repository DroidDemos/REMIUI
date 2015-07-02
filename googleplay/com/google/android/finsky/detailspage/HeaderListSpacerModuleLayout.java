package com.google.android.finsky.detailspage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoBottomDivider;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoTopDivider;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.StreamSpacer;

public class HeaderListSpacerModuleLayout extends FrameLayout implements NoBottomDivider, NoTopDivider, StreamSpacer {
    public HeaderListSpacerModuleLayout(Context context) {
        super(context);
    }

    public HeaderListSpacerModuleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void bind(int height) {
        LayoutParams layoutParams = getLayoutParams();
        if (layoutParams.height != height) {
            layoutParams.height = height;
            requestLayout();
        }
    }
}
