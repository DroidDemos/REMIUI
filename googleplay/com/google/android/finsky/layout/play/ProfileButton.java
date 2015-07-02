package com.google.android.finsky.layout.play;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.vending.R;

public class ProfileButton extends TextView {
    private Drawable mIcon;
    private final int mIconTextGap;
    private int mOriginalLeftPadding;

    public ProfileButton(Context context) {
        this(context, null);
    }

    public ProfileButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIconTextGap = context.getResources().getDimensionPixelSize(R.dimen.play_profile_button_icon_gap);
        this.mOriginalLeftPadding = getPaddingLeft();
        setWillNotDraw(false);
    }

    public void configure(String text, int iconResourceId, int backgroundResourceId) {
        int paddingLeft = this.mOriginalLeftPadding;
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        setText(text);
        setBackgroundResource(backgroundResourceId);
        if (iconResourceId <= 0) {
            this.mIcon = null;
        } else {
            this.mIcon = getResources().getDrawable(iconResourceId);
            this.mIcon.setBounds(0, 0, this.mIcon.getIntrinsicWidth(), this.mIcon.getIntrinsicHeight());
        }
        if (this.mIcon != null) {
            paddingLeft += this.mIcon.getIntrinsicWidth() + this.mIconTextGap;
        }
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mIcon != null) {
            int iconY = (getHeight() - this.mIcon.getIntrinsicHeight()) / 2;
            int leftmostTextX = 0;
            Layout layout = getLayout();
            int scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            if (layout != null) {
                leftmostTextX = getWidth();
                int layoutLineCount = layout.getLineCount();
                for (int line = 0; line < layoutLineCount; line++) {
                    leftmostTextX = Math.min(leftmostTextX, (((int) layout.getPrimaryHorizontal(layout.getLineStart(line))) + paddingLeft) - scrollX);
                }
            }
            int iconWidth = this.mIcon.getIntrinsicWidth() + this.mIconTextGap;
            canvas.save();
            canvas.translate((float) ((scrollX + leftmostTextX) - iconWidth), (float) iconY);
            this.mIcon.draw(canvas);
            canvas.restore();
        }
    }
}
