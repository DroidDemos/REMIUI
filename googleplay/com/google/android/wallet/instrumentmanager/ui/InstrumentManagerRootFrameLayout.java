package com.google.android.wallet.instrumentmanager.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.google.android.wallet.instrumentmanager.R;

public class InstrumentManagerRootFrameLayout extends FrameLayout {
    private int mMaxWidthPixels;

    public InstrumentManagerRootFrameLayout(Context context) {
        super(context);
    }

    public InstrumentManagerRootFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributes(context, attrs);
    }

    public InstrumentManagerRootFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        readAttributes(context, attrs);
    }

    public View focusSearch(View focused, int direction) {
        return FocusFinder.getInstance().findNextFocus(this, focused, direction);
    }

    private void readAttributes(Context context, AttributeSet attrs) {
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.WalletImMaxWidthView);
        this.mMaxWidthPixels = viewAttrs.getDimensionPixelSize(R.styleable.WalletImMaxWidthView_maxWidth, 0);
        viewAttrs.recycle();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidthPixels = MeasureSpec.getSize(widthMeasureSpec);
        if (this.mMaxWidthPixels > 0 && this.mMaxWidthPixels < measuredWidthPixels) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(this.mMaxWidthPixels, MeasureSpec.getMode(widthMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
