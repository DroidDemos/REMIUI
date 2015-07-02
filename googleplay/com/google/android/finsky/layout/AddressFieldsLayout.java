package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.android.vending.R;
import java.util.List;

public abstract class AddressFieldsLayout extends FrameLayout {
    protected LinearLayout mFieldContainer;
    protected ProgressBar mProgressBar;
    protected ProgressBar mUpperRightProgressBar;

    public abstract void hideUpperRightProgressBar();

    public abstract void setFields(List<View> list);

    public abstract void setOnHeightOffsetChangedListener(OnHeightOffsetChangedListener onHeightOffsetChangedListener);

    public abstract void showFields();

    public abstract void showProgressBar();

    public abstract void showUpperRightProgressBar();

    public AddressFieldsLayout(Context context) {
        this(context, null);
    }

    public AddressFieldsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddressFieldsLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mProgressBar = (ProgressBar) findViewById(R.id.address_progress_bar);
        this.mFieldContainer = (LinearLayout) findViewById(R.id.address_inner_layout);
        this.mUpperRightProgressBar = (ProgressBar) findViewById(R.id.upper_right_progress_bar);
    }

    public void disableOneFieldMode() {
    }
}
