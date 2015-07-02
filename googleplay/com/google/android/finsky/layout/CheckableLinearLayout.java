package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import com.android.vending.R;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private CheckedTextView mCheckedTextView;
    private final int mResId;

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CheckableLinearLayout);
        this.mResId = attributes.getResourceId(0, 0);
        attributes.recycle();
    }

    public boolean isChecked() {
        return this.mCheckedTextView != null ? this.mCheckedTextView.isChecked() : false;
    }

    public void setChecked(boolean checked) {
        if (this.mCheckedTextView != null) {
            this.mCheckedTextView.setChecked(checked);
        }
    }

    public void toggle() {
        if (this.mCheckedTextView != null) {
            this.mCheckedTextView.toggle();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.mResId != 0) {
            this.mCheckedTextView = (CheckedTextView) findViewById(this.mResId);
        }
    }
}
