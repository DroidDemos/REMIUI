package com.google.android.play.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.play.R;
import com.google.android.play.utils.Assertions;

public class PageIndicator extends LinearLayout {
    private int mSelectedPage;

    public PageIndicator(Context context) {
        this(context, null, 0);
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSelectedPage = -1;
        init();
    }

    public PageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mSelectedPage = -1;
        init();
    }

    protected void init() {
        setGravity(16);
    }

    public int getPageCount() {
        return getChildCount();
    }

    public void setPageCount(int newCount) {
        boolean z;
        if (newCount >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "numPages must be >=0");
        int currentCount = getChildCount();
        if (newCount < currentCount) {
            removeViews(newCount, currentCount - newCount);
        } else if (newCount > currentCount) {
            for (int i = currentCount; i < newCount; i++) {
                ImageView addDot = addDot();
                if (i == this.mSelectedPage) {
                    z = true;
                } else {
                    z = false;
                }
                setDotState(addDot, z);
            }
        }
        updateContentDescription();
    }

    public int getSelectedPage() {
        return this.mSelectedPage;
    }

    public void setSelectedPage(int page) {
        if (this.mSelectedPage != page) {
            int count = getChildCount();
            int i = 0;
            while (i < count) {
                setDotState((ImageView) getChildAt(i), i == page);
                i++;
            }
            this.mSelectedPage = page;
            updateContentDescription();
        }
    }

    private ImageView addDot() {
        ImageView view = new ImageView(getContext());
        view.setScaleType(ScaleType.CENTER);
        view.setImageResource(R.drawable.play_onboard_page_indicator_dot);
        LayoutParams params = new LayoutParams(-2, -2);
        int margin = getResources().getDimensionPixelSize(R.dimen.play_onboard__page_indicator_dot_diameter) / 2;
        params.gravity = 16;
        params.setMargins(margin, 0, margin, 0);
        addView(view, params);
        return view;
    }

    private ImageView setDotState(ImageView imageView, boolean selected) {
        ((GradientDrawable) imageView.getDrawable()).setColor(getResources().getColor(selected ? R.color.play_onboard__page_indicator_dot_active : R.color.play_onboard__page_indicator_dot_inactive));
        return imageView;
    }

    private void updateContentDescription() {
        setContentDescription(getResources().getString(R.string.play_content_description_page_indicator, new Object[]{Integer.valueOf(getSelectedPage() + 1), Integer.valueOf(getPageCount())}));
    }
}
