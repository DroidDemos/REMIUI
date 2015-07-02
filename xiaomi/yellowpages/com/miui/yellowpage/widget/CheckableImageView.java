package com.miui.yellowpage.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;

public class CheckableImageView extends LinearLayout implements Checkable {
    private static final int[] rn;
    private boolean mChecked;
    private TextView rm;

    static {
        rn = new int[]{16842912};
    }

    public CheckableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, rn);
        }
        return onCreateDrawableState;
    }

    public void toggle() {
        setChecked(!this.mChecked);
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void setChecked(boolean z) {
        setSelected(z);
        if (this.mChecked != z) {
            this.mChecked = z;
            refreshDrawableState();
        }
        if (this.rm != null) {
            this.rm.setSelected(z);
        }
    }

    public void setLabel(CharSequence charSequence) {
        if (this.rm == null) {
            this.rm = (TextView) findViewById(R.id.label);
            if (this.rm == null) {
                return;
            }
        }
        this.rm.setText(charSequence);
    }
}
