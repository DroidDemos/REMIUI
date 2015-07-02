package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.finsky.utils.PlayCardImageTypeSequence;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayEditorialAppCardView extends PlayCardViewBase implements PlayCardImageTypeSequence {
    public PlayEditorialAppCardView(Context context) {
        this(context, null);
    }

    public PlayEditorialAppCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getCardType() {
        return 5;
    }

    public int[] getImageTypePreference() {
        return PlayCardImageTypeSequence.PROMO_IMAGE_TYPES;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.mThumbnailAspectRatio = 0.48828125f;
        measureThumbnailSpanningWidth(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
