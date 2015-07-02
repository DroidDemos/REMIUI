package com.google.android.wallet.instrumentmanager.ui.address;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class DynamicAddressFieldsLayout extends FrameLayout {
    LinearLayout mFieldContainer;
    OnHeightOffsetChangedListener mOnHeightOffsetChangedListener;
    ProgressBar mProgressBar;

    public interface OnHeightOffsetChangedListener {
        void onHeightOffsetChanged(float f);
    }

    public DynamicAddressFieldsLayout(Context context) {
        this(context, null);
    }

    public DynamicAddressFieldsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicAddressFieldsLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.mFieldContainer = (LinearLayout) findViewById(R.id.dynamic_address_fields_container);
    }

    public void setFields(ArrayList<View> fields) {
        addViews(fields);
    }

    protected void addViews(ArrayList<View> fields) {
        this.mFieldContainer.removeAllViews();
        int length = fields.size();
        for (int i = 0; i < length; i++) {
            this.mFieldContainer.addView((View) fields.get(i), new LayoutParams(-1, -2));
        }
    }

    public void switchToShowingFields() {
        this.mProgressBar.setVisibility(4);
        this.mFieldContainer.setVisibility(0);
    }

    public void switchToShowingProgressBar() {
        this.mProgressBar.setVisibility(0);
        this.mFieldContainer.setVisibility(4);
    }

    public void replaceView(View oldView, View newView) {
        int oldViewIndex = this.mFieldContainer.indexOfChild(oldView);
        if (oldViewIndex < 0) {
            throw new IllegalArgumentException("oldView: " + oldView + " is not present in the fields container");
        } else if (this.mFieldContainer.indexOfChild(newView) >= 0) {
            throw new IllegalArgumentException("newView: " + newView + " is already present in the fields container");
        } else {
            this.mFieldContainer.removeViewAt(oldViewIndex);
            this.mFieldContainer.addView(newView, oldViewIndex);
        }
    }

    public void setOnHeightOffsetChangedListener(OnHeightOffsetChangedListener listener) {
        this.mOnHeightOffsetChangedListener = listener;
    }
}
