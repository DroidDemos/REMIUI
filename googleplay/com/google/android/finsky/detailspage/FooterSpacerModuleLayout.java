package com.google.android.finsky.detailspage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoBottomDivider;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoTopDivider;

public class FooterSpacerModuleLayout extends FrameLayout implements NoBottomDivider, NoTopDivider {
    public FooterSpacerModuleLayout(Context context) {
        super(context);
    }

    public FooterSpacerModuleLayout(Context context, AttributeSet attributeSet) {
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
