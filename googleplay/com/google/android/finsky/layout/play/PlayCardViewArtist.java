package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardViewArtist extends PlayCardViewBase {
    public PlayCardViewArtist(Context context) {
        this(context, null);
    }

    public PlayCardViewArtist(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getCardType() {
        return 7;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        this.mThumbnail.getLayoutParams().height = (int) (this.mThumbnailAspectRatio * ((float) ((((availableWidth - paddingLeft) - getPaddingRight()) - thumbnailLp.leftMargin) - thumbnailLp.rightMargin)));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
