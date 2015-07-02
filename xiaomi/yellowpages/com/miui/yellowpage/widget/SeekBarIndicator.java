package com.miui.yellowpage.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a;

public class SeekBarIndicator extends LinearLayout {
    private final int ok;

    public SeekBarIndicator(Context context) {
        super(context);
        this.ok = context.getResources().getDimensionPixelSize(R.dimen.seekbar_indicator_default_point_margin);
    }

    public SeekBarIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SeekBarIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.dJ, i, 0);
        this.ok = obtainStyledAttributes.getDimensionPixelSize(0, context.getResources().getDimensionPixelSize(R.dimen.seekbar_indicator_default_point_margin));
        obtainStyledAttributes.recycle();
    }

    public View co() {
        View imageView = new ImageView(getContext());
        imageView.setScaleType(ScaleType.CENTER);
        imageView.setImageResource(R.drawable.screen_view_seek_point_selector);
        return imageView;
    }

    public void cp() {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
        layoutParams.leftMargin = getChildCount() > 0 ? this.ok : 0;
        addView(co(), getChildCount(), layoutParams);
    }

    public void w(int i) {
        while (i > getChildCount()) {
            cp();
        }
        while (i < getChildCount()) {
            cq();
        }
    }

    public void cq() {
        int childCount = getChildCount();
        if (childCount > 0) {
            removeViewAt(childCount - 1);
        }
    }

    public void x(int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            boolean z;
            View childAt = getChildAt(i2);
            if (i2 == i) {
                z = true;
            } else {
                z = false;
            }
            childAt.setSelected(z);
        }
    }
}
