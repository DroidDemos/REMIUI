package com.google.android.play.layout;

import android.content.Context;
import android.util.AttributeSet;

public class PlayCardViewMedium extends PlayCardViewBase {
    public PlayCardViewMedium(Context context) {
        this(context, null);
    }

    public PlayCardViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureThumbnailSpanningHeight(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public int getCardType() {
        return 2;
    }
}
