package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardViewLarge extends PlayCardViewBase {
    public PlayCardViewLarge(Context context) {
        this(context, null);
    }

    public PlayCardViewLarge(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getCardType() {
        return 11;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureThumbnailSpanningWidth(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
