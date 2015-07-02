package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import java.util.List;

public class AddressFieldsLayoutFroyo extends AddressFieldsLayout {
    private boolean mLoading;

    public AddressFieldsLayoutFroyo(Context context) {
        this(context, null);
    }

    public AddressFieldsLayoutFroyo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddressFieldsLayoutFroyo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mLoading = false;
    }

    public void showUpperRightProgressBar() {
        this.mUpperRightProgressBar.setVisibility(0);
    }

    public void hideUpperRightProgressBar() {
        this.mUpperRightProgressBar.setVisibility(8);
    }

    public void setFields(List<View> fields) {
        addViews(fields);
        if (this.mLoading) {
            this.mProgressBar.setVisibility(8);
            setChildrenViewVisibility(0);
            return;
        }
        this.mProgressBar.setVisibility(0);
        setChildrenViewVisibility(8);
    }

    public void showFields() {
        this.mProgressBar.setVisibility(8);
        setChildrenViewVisibility(0);
    }

    public void showProgressBar() {
        this.mProgressBar.setVisibility(0);
        setChildrenViewVisibility(8);
    }

    public void setOnHeightOffsetChangedListener(OnHeightOffsetChangedListener onHeightChangedListener) {
    }

    private void setChildrenViewVisibility(int visibility) {
        int childCount = this.mFieldContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            this.mFieldContainer.getChildAt(i).setVisibility(visibility);
        }
    }

    private void addViews(List<View> fields) {
        this.mFieldContainer.removeAllViews();
        for (View field : fields) {
            this.mFieldContainer.addView(field, new LayoutParams(-1, -2));
        }
    }
}
