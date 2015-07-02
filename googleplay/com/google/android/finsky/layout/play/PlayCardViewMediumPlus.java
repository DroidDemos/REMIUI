package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardViewMediumPlus extends PlayCardViewBase {
    public PlayCardViewMediumPlus(Context context) {
        this(context, null);
    }

    public PlayCardViewMediumPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getCardType() {
        return 10;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        int thumbnailWidth = ((((availableWidth - paddingLeft) - getPaddingRight()) - thumbnailLp.leftMargin) - thumbnailLp.rightMargin) / 2;
        int thumbnailHeight = (int) (((float) thumbnailWidth) * this.mThumbnailAspectRatio);
        this.mThumbnail.getLayoutParams().width = thumbnailWidth;
        this.mThumbnail.getLayoutParams().height = thumbnailHeight;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
