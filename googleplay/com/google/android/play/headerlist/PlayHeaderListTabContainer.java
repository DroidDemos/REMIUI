package com.google.android.play.headerlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.play.R;

public class PlayHeaderListTabContainer extends LinearLayout {
    private static int[] sNextLeftRight;
    private static int[] sSelectedLeftRight;
    private int mIndexForSelection;
    private boolean mPaddingValidForTabs;
    private final Paint mSelectedUnderlinePaint;
    private final int mSelectedUnderlineThickness;
    private float mSelectionOffset;
    private int mStripWidth;
    private boolean mUseFloatingTabPadding;

    static {
        sSelectedLeftRight = new int[2];
        sNextLeftRight = new int[2];
    }

    public PlayHeaderListTabContainer(Context context) {
        this(context, null);
    }

    public PlayHeaderListTabContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        this.mSelectedUnderlineThickness = context.getResources().getDimensionPixelSize(R.dimen.play_header_list_tab_strip_selected_underline_height);
        this.mSelectedUnderlinePaint = new Paint();
    }

    public void setSelectedIndicatorColor(int color) {
        this.mSelectedUnderlinePaint.setColor(color);
        invalidate();
    }

    void onPageScrolled(int position, float positionOffset) {
        this.mIndexForSelection = position;
        this.mSelectionOffset = positionOffset;
        invalidate();
    }

    void onPageSelected(int position) {
        this.mIndexForSelection = position;
        this.mSelectionOffset = 0.0f;
        invalidate();
    }

    void setStripWidth(int width) {
        if (this.mStripWidth != width || !this.mPaddingValidForTabs) {
            this.mStripWidth = width;
            updatePadding();
        }
    }

    void invalidateTabPadding() {
        this.mPaddingValidForTabs = false;
    }

    private void updatePadding() {
        int leftPadding = 0;
        int rightPadding = 0;
        if (this.mUseFloatingTabPadding) {
            leftPadding = getResources().getDimensionPixelSize(R.dimen.play_header_list_tab_floating_padding);
            rightPadding = leftPadding;
        } else {
            int halfWidth = this.mStripWidth / 2;
            View firstChild = getChildAt(0);
            if (firstChild != null) {
                firstChild.measure(-2, -2);
                leftPadding = halfWidth - (firstChild.getMeasuredWidth() / 2);
            }
            View lastChild = getChildAt(getChildCount() - 1);
            if (lastChild != null) {
                lastChild.measure(-2, -2);
                rightPadding = halfWidth - (lastChild.getMeasuredWidth() / 2);
            }
        }
        setPadding(leftPadding, 0, rightPadding, 0);
        this.mPaddingValidForTabs = true;
    }

    public void setUseFloatingTabPadding(boolean useFloatingTabPadding) {
        if (this.mUseFloatingTabPadding != useFloatingTabPadding) {
            this.mUseFloatingTabPadding = useFloatingTabPadding;
            updatePadding();
        }
    }

    protected void onDraw(Canvas canvas) {
        if (getChildCount() > 0 && this.mIndexForSelection < getChildCount()) {
            getUnderlineEdges(getChildAt(this.mIndexForSelection), sSelectedLeftRight);
            if (this.mSelectionOffset > 0.0f && this.mIndexForSelection < getChildCount() - 1) {
                getUnderlineEdges(getChildAt(this.mIndexForSelection + 1), sNextLeftRight);
                sSelectedLeftRight[0] = (int) ((this.mSelectionOffset * ((float) sNextLeftRight[0])) + ((1.0f - this.mSelectionOffset) * ((float) sSelectedLeftRight[0])));
                sSelectedLeftRight[1] = (int) ((this.mSelectionOffset * ((float) sNextLeftRight[1])) + ((1.0f - this.mSelectionOffset) * ((float) sSelectedLeftRight[1])));
            }
            int height = getHeight();
            canvas.drawRect((float) sSelectedLeftRight[0], (float) (height - this.mSelectedUnderlineThickness), (float) sSelectedLeftRight[1], (float) height, this.mSelectedUnderlinePaint);
        }
    }

    private static void getUnderlineEdges(View view, int[] outLeftRight) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            CharSequence text = textView.getText();
            if (text == null || text.length() == 0) {
                outLeftRight[0] = (textView.getLeft() + textView.getRight()) / 2;
                outLeftRight[1] = outLeftRight[0];
                return;
            }
            outLeftRight[0] = textView.getLeft();
            outLeftRight[1] = textView.getRight();
            return;
        }
        outLeftRight[0] = view.getLeft();
        outLeftRight[1] = view.getRight();
    }
}
