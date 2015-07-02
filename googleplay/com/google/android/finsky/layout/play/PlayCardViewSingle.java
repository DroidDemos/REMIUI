package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardViewSingle extends PlayCardViewBase {
    public PlayCardViewSingle(Context context) {
        this(context, null);
    }

    public PlayCardViewSingle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getCardType() {
        return 8;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureThumbnailSpanningHeight(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
