package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.play.layout.PlayCardLabelView;
import com.google.android.play.layout.PlayCardThumbnail;
import com.google.android.play.layout.PlayCardViewBase;
import com.google.android.play.layout.PlayTextView;

public class PlayCardViewListingSmall extends PlayCardViewBase {
    private View mRatingBadge;

    public PlayCardViewListingSmall(Context context) {
        this(context, null);
    }

    public PlayCardViewListingSmall(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mRatingBadge = findViewById(R.id.rating_badge_container);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureThumbnailSpanningHeight(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int highlightLabelSpace = 0;
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        MarginLayoutParams titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
        MarginLayoutParams subtitleLp = (MarginLayoutParams) this.mSubtitle.getLayoutParams();
        MarginLayoutParams ratingBadgeLp = (MarginLayoutParams) this.mRatingBadge.getLayoutParams();
        MarginLayoutParams labelLp = (MarginLayoutParams) this.mLabel.getLayoutParams();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int contentHeightSpec = MeasureSpec.makeMeasureSpec((height - paddingTop) - paddingBottom, 1073741824);
        this.mThumbnail.measure(MeasureSpec.makeMeasureSpec(thumbnailLp.width, 1073741824), contentHeightSpec);
        this.mOverflow.measure(0, 0);
        int i = thumbnailLp.width;
        int thumbnailLeftPaddingSpace = (r0 + paddingLeft) + thumbnailLp.rightMargin;
        int textContentWidth = (width - thumbnailLeftPaddingSpace) - paddingRight;
        i = titleLp.leftMargin;
        int titleWidth = (textContentWidth - r0) - titleLp.rightMargin;
        this.mTitle.measure(MeasureSpec.makeMeasureSpec(titleWidth, 1073741824), 0);
        if (this.mHighlightLabel != null) {
            if (this.mHighlightLabel.getVisibility() != 8) {
                MarginLayoutParams adLp = (MarginLayoutParams) this.mHighlightLabel.getLayoutParams();
                this.mHighlightLabel.measure(0, 0);
                TextView textView = this.mHighlightLabel;
                highlightLabelSpace = r0.getMeasuredWidth() + adLp.rightMargin;
            }
        }
        int i2 = subtitleLp.leftMargin;
        int subtitleWidth = ((textContentWidth - highlightLabelSpace) - r0) - subtitleLp.rightMargin;
        this.mSubtitle.measure(MeasureSpec.makeMeasureSpec(subtitleWidth, Integer.MIN_VALUE), 0);
        this.mLabel.measure(MeasureSpec.makeMeasureSpec((width - paddingLeft) - paddingRight, Integer.MIN_VALUE), 0);
        i = ratingBadgeLp.leftMargin;
        int ratingBadgeWidth = (textContentWidth - r0) - ratingBadgeLp.rightMargin;
        this.mRatingBadge.measure(MeasureSpec.makeMeasureSpec(ratingBadgeWidth, 1073741824), 0);
        i = titleLp.topMargin;
        TextView textView2 = this.mTitle;
        i2 = titleLp.bottomMargin;
        i2 = subtitleLp.topMargin;
        PlayTextView playTextView = this.mSubtitle;
        i2 = subtitleLp.bottomMargin;
        if ((((((((r0 + paddingTop) + r0.getMeasuredHeight()) + r0) + r0) + r0.getMeasuredHeight()) + r0) + ratingBadgeLp.topMargin) + this.mRatingBadge.getMeasuredHeight() > ((getMeasuredHeight() - paddingBottom) - labelLp.bottomMargin) - this.mLabel.getMeasuredHeight()) {
            i2 = labelLp.leftMargin;
            PlayCardLabelView playCardLabelView = this.mLabel;
            int labelLeft = (((width - paddingRight) - r0) - r0.getMeasuredWidth()) - labelLp.rightMargin;
            i = thumbnailLp.leftMargin;
            PlayCardThumbnail playCardThumbnail = this.mThumbnail;
            i2 = thumbnailLp.rightMargin;
            int ratingBadgeLeft = (((r0 + paddingLeft) + r0.getMeasuredWidth()) + r0) + ratingBadgeLp.leftMargin;
            int ratingBadgeRight = ratingBadgeLeft + this.mRatingBadge.getMeasuredWidth();
            if (ratingBadgeLp.rightMargin + ratingBadgeRight > labelLeft) {
                int newRatingBadgeWidth = (labelLeft - ratingBadgeLeft) - ratingBadgeLp.rightMargin;
                this.mRatingBadge.measure(MeasureSpec.makeMeasureSpec(newRatingBadgeWidth, 1073741824), 0);
            }
        }
        if (this.mHighlightSubtitle != null) {
            if (this.mHighlightSubtitle.getVisibility() != 8) {
                MarginLayoutParams highlightLp = (MarginLayoutParams) this.mHighlightSubtitle.getLayoutParams();
                playCardLabelView = this.mLabel;
                i = highlightLp.leftMargin;
                int highlightWidth = ((((width - thumbnailLeftPaddingSpace) - paddingRight) - r0.getMeasuredWidth()) - r0) - highlightLp.rightMargin;
                this.mHighlightSubtitle.measure(MeasureSpec.makeMeasureSpec(highlightWidth, 1073741824), 0);
            }
        }
        this.mLoadingIndicator.measure(0, 0);
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth();
        int height = getHeight();
        int highlightLabelSpace = 0;
        int thumbnailHeight = this.mThumbnail.getMeasuredHeight();
        this.mThumbnail.layout(paddingLeft, paddingTop, this.mThumbnail.getMeasuredWidth() + paddingLeft, paddingTop + thumbnailHeight);
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        MarginLayoutParams titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
        MarginLayoutParams subtitleLp = (MarginLayoutParams) this.mSubtitle.getLayoutParams();
        MarginLayoutParams ratingBadgeLp = (MarginLayoutParams) this.mRatingBadge.getLayoutParams();
        MarginLayoutParams labelLp = (MarginLayoutParams) this.mLabel.getLayoutParams();
        MarginLayoutParams overflowLp = (MarginLayoutParams) this.mOverflow.getLayoutParams();
        int i = thumbnailLp.width;
        int thumbnailLeftPaddingSpace = (r0 + paddingLeft) + thumbnailLp.rightMargin;
        int titleTop = paddingTop + titleLp.topMargin;
        int titleLeft = thumbnailLeftPaddingSpace + titleLp.leftMargin;
        this.mTitle.layout(titleLeft, titleTop, this.mTitle.getMeasuredWidth() + titleLeft, this.mTitle.getMeasuredHeight() + titleTop);
        TextView textView = this.mTitle;
        int lastLineTop = (r0.getMeasuredHeight() + titleTop) + titleLp.bottomMargin;
        int overflowTop = titleTop + overflowLp.topMargin;
        int overflowRight = (width - paddingRight) - overflowLp.rightMargin;
        this.mOverflow.layout(overflowRight - this.mOverflow.getMeasuredWidth(), overflowTop, overflowRight, this.mOverflow.getMeasuredHeight() + overflowTop);
        if (this.mHighlightLabel != null) {
            if (this.mHighlightLabel.getVisibility() != 8) {
                MarginLayoutParams adLp = (MarginLayoutParams) this.mHighlightLabel.getLayoutParams();
                textView = this.mHighlightLabel;
                highlightLabelSpace = r0.getMeasuredWidth() + adLp.rightMargin;
                int adTop = lastLineTop + adLp.topMargin;
                int adLeft = thumbnailLeftPaddingSpace + adLp.leftMargin;
                this.mHighlightLabel.layout(adLeft, adTop, this.mHighlightLabel.getMeasuredWidth() + adLeft, this.mHighlightLabel.getMeasuredHeight() + adTop);
            }
        }
        if (this.mSubtitle.getVisibility() != 8) {
            int i2 = lastLineTop + subtitleLp.topMargin;
            if (highlightLabelSpace != 0) {
                i = this.mHighlightLabel.getPaddingTop();
            } else {
                i = 0;
            }
            int subtitleTop = i2 + i;
            int subtitleLeft = (highlightLabelSpace + thumbnailLeftPaddingSpace) + subtitleLp.leftMargin;
            this.mSubtitle.layout(subtitleLeft, subtitleTop, this.mSubtitle.getMeasuredWidth() + subtitleLeft, this.mSubtitle.getMeasuredHeight() + subtitleTop);
            PlayTextView playTextView = this.mSubtitle;
            lastLineTop = (r0.getMeasuredHeight() + subtitleTop) + subtitleLp.bottomMargin;
        }
        if (this.mHighlightSubtitle.getVisibility() != 8) {
            MarginLayoutParams highlightTextLp = (MarginLayoutParams) this.mHighlightSubtitle.getLayoutParams();
            int highlightSubtitleTop = lastLineTop + highlightTextLp.topMargin;
            int highlightSubtitleLeft = thumbnailLeftPaddingSpace + highlightTextLp.leftMargin;
            this.mHighlightSubtitle.layout(highlightSubtitleLeft, highlightSubtitleTop, this.mHighlightSubtitle.getMeasuredWidth() + highlightSubtitleLeft, this.mHighlightSubtitle.getMeasuredHeight() + highlightSubtitleTop);
            textView = this.mHighlightSubtitle;
            lastLineTop = (r0.getMeasuredHeight() + highlightSubtitleTop) + highlightTextLp.bottomMargin;
        }
        if (this.mRatingBadge.getVisibility() != 8) {
            int ratingBadgeTop = lastLineTop + ratingBadgeLp.topMargin;
            int ratingBadgeLeft = thumbnailLeftPaddingSpace + ratingBadgeLp.leftMargin;
            this.mRatingBadge.layout(ratingBadgeLeft, ratingBadgeTop, this.mRatingBadge.getMeasuredWidth() + ratingBadgeLeft, this.mRatingBadge.getMeasuredHeight() + ratingBadgeTop);
            View view = this.mRatingBadge;
            lastLineTop = (r0.getMeasuredHeight() + ratingBadgeTop) + ratingBadgeLp.bottomMargin;
        }
        int labelBottom = (height - paddingBottom) - labelLp.bottomMargin;
        int labelRight = (width - paddingRight) - labelLp.rightMargin;
        this.mLabel.layout(labelRight - this.mLabel.getMeasuredWidth(), labelBottom - this.mLabel.getMeasuredHeight(), labelRight, labelBottom);
        int loadingWidth = this.mLoadingIndicator.getMeasuredWidth();
        int loadingLeft = paddingLeft + ((((width - paddingLeft) - paddingRight) - loadingWidth) / 2);
        int loadingTop = paddingTop + ((((height - paddingTop) - paddingBottom) - this.mLoadingIndicator.getMeasuredHeight()) / 2);
        this.mLoadingIndicator.layout(loadingLeft, loadingTop, this.mLoadingIndicator.getMeasuredWidth() + loadingLeft, this.mLoadingIndicator.getMeasuredHeight() + loadingTop);
        recomputeOverflowAreaIfNeeded();
    }

    public int getCardType() {
        return 4;
    }
}
