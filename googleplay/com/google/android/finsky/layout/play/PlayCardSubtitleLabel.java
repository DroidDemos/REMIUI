package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;
import com.android.vending.R;

public class PlayCardSubtitleLabel extends RelativeLayout {
    protected View mLabel;
    protected View mSubtitle;

    public PlayCardSubtitleLabel(Context context) {
        this(context, null);
    }

    public PlayCardSubtitleLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSubtitle = findViewById(R.id.li_subtitle);
        this.mLabel = findViewById(R.id.li_label);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        this.mLabel.measure(MeasureSpec.makeMeasureSpec((width - getPaddingLeft()) - getPaddingRight(), Integer.MIN_VALUE), 0);
        this.mSubtitle.measure(0, 0);
        MarginLayoutParams subtitleLp = (MarginLayoutParams) this.mSubtitle.getLayoutParams();
        MarginLayoutParams labelLp = (MarginLayoutParams) this.mLabel.getLayoutParams();
        this.mSubtitle.measure(MeasureSpec.makeMeasureSpec(Math.min(this.mSubtitle.getMeasuredWidth(), ((((width - subtitleLp.leftMargin) - subtitleLp.rightMargin) - this.mLabel.getMeasuredWidth()) - labelLp.leftMargin) - labelLp.rightMargin), 1073741824), 0);
        setMeasuredDimension(width, this.mSubtitle.getMeasuredHeight());
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        MarginLayoutParams subtitleLp = (MarginLayoutParams) this.mSubtitle.getLayoutParams();
        this.mSubtitle.layout(subtitleLp.leftMargin, 0, subtitleLp.leftMargin + this.mSubtitle.getMeasuredWidth(), this.mSubtitle.getMeasuredHeight());
        int labelTop = this.mSubtitle.getBaseline() - this.mLabel.getBaseline();
        int labelRight = width - ((MarginLayoutParams) this.mLabel.getLayoutParams()).rightMargin;
        this.mLabel.layout(labelRight - this.mLabel.getMeasuredWidth(), labelTop, labelRight, this.mLabel.getMeasuredHeight() + labelTop);
    }
}
