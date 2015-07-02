package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.android.vending.R;

public class DetailsInnerColumnLayout extends DetailsSectionStack {
    private boolean mBlockLayout;
    private boolean mExpandCardSections;
    private final int mExtraCardSectionWidth;
    private int mSideMargin;

    public DetailsInnerColumnLayout(Context context) {
        this(context, null);
    }

    public DetailsInnerColumnLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSideMargin = -1;
        this.mExtraCardSectionWidth = context.getResources().getDimensionPixelSize(R.dimen.play_card_inset) * 2;
    }

    public void blockLayoutRequests() {
        this.mBlockLayout = true;
    }

    public void unblockLayoutRequests() {
        this.mBlockLayout = false;
    }

    public void requestLayout() {
        if (!this.mBlockLayout) {
            super.requestLayout();
        }
    }

    public void setSideMargin(int sideMargin) {
        if (this.mSideMargin != sideMargin) {
            this.mSideMargin = sideMargin;
            updateSectionMargins();
        }
    }

    public void setExpandCardSections(boolean expandCardSections) {
        if (this.mExpandCardSections != expandCardSections) {
            this.mExpandCardSections = expandCardSections;
            requestLayout();
        }
    }

    public int getSideMargin() {
        return this.mSideMargin;
    }

    private void updateSectionMargins() {
        ScreenshotGallery screenshotGallery = (ScreenshotGallery) findViewById(R.id.screenshots_panel);
        if (screenshotGallery != null) {
            Resources res = getResources();
            screenshotGallery.setMargins(Math.max(this.mSideMargin - res.getDimensionPixelSize(R.dimen.screenshots_leading_peek), 0), res.getDimensionPixelOffset(R.dimen.screenshots_spacing));
            LayoutParams stripBackgroundLp = (LayoutParams) screenshotGallery.findViewById(R.id.strip_background).getLayoutParams();
            stripBackgroundLp.leftMargin = this.mSideMargin;
            stripBackgroundLp.rightMargin = this.mSideMargin;
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        int mainContentWidth = width - (this.mSideMargin * 2);
        int cardSectionWidth = mainContentWidth;
        if (this.mExpandCardSections) {
            cardSectionWidth += this.mExtraCardSectionWidth;
        }
        int visibleChildrenSoFar = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                visibleChildrenSoFar++;
                LinearLayout.LayoutParams childLp = (LinearLayout.LayoutParams) child.getLayoutParams();
                int childHeightSpec = getChildMeasureSpec(0, 0, childLp.height);
                if (child.getId() == R.id.screenshots_panel) {
                    child.measure(widthMeasureSpec, childHeightSpec);
                } else if (child instanceof DetailsPackSection) {
                    child.measure(MeasureSpec.makeMeasureSpec((cardSectionWidth - childLp.leftMargin) - childLp.rightMargin, 1073741824), childHeightSpec);
                } else {
                    child.measure(MeasureSpec.makeMeasureSpec((mainContentWidth - childLp.leftMargin) - childLp.rightMargin, 1073741824), childHeightSpec);
                }
                if ((child instanceof DetailsPeekingSection) && visibleChildrenSoFar > 1) {
                    height -= ((DetailsPeekingSection) child).getTopPeekAmount();
                }
                height += child.getMeasuredHeight();
            }
        }
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int childCount = getChildCount();
        int visibleChildrenSoFar = 0;
        int y = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                visibleChildrenSoFar++;
                if ((child instanceof DetailsPeekingSection) && visibleChildrenSoFar > 1) {
                    y -= ((DetailsPeekingSection) child).getTopPeekAmount();
                }
                int childHeight = child.getMeasuredHeight();
                int childWidth = child.getMeasuredWidth();
                LinearLayout.LayoutParams childLp = (LinearLayout.LayoutParams) child.getLayoutParams();
                int childX = childLp.leftMargin + ((((width - childWidth) - childLp.leftMargin) - childLp.rightMargin) / 2);
                child.layout(childX, y, childX + childWidth, y + childHeight);
                y += childHeight;
            }
        }
    }
}
