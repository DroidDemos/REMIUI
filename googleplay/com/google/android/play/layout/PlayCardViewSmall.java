package com.google.android.play.layout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.google.android.play.R;

public class PlayCardViewSmall extends PlayCardViewBase {
    private int mMode;
    protected View mRatingBadgeContainer;
    private final int mTextContentHeight;
    private int mVerticalBump;
    private final int mVerticalBumpLimit;

    public PlayCardViewSmall(Context context) {
        this(context, null);
    }

    public PlayCardViewSmall(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mMode = -1;
        Resources res = context.getResources();
        this.mTextContentHeight = res.getDimensionPixelSize(R.dimen.play_small_card_content_min_height);
        this.mVerticalBumpLimit = res.getDimensionPixelSize(R.dimen.play_card_extra_vspace);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mRatingBadgeContainer = findViewById(R.id.rating_badge_container);
    }

    public void setSnippetVisible(boolean isSnippetVisible) {
        int newMode;
        if (isSnippetVisible) {
            newMode = 1;
        } else {
            newMode = 0;
        }
        if (newMode != this.mMode) {
            boolean z;
            this.mMode = newMode;
            if (this.mMode == 0) {
                z = true;
            } else {
                z = false;
            }
            this.mSupportsSubtitleAndRating = z;
            if (this.mMode == 1) {
                this.mTitle.setSingleLine(true);
                return;
            }
            this.mTitle.setSingleLine(false);
            this.mTitle.setMaxLines(2);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height;
        MarginLayoutParams highlightLp;
        measureThumbnailSpanningWidth(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int contentWidth = (width - paddingLeft) - paddingRight;
        int highlightLabelSpace = 0;
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int idealHeight = ((thumbnailLp.height + paddingTop) + Math.max(this.mTextContentHeight, contentWidth / 2)) + paddingBottom;
        if (heightSpecMode != 1073741824 || heightSpecSize <= 0) {
            height = idealHeight;
        } else {
            height = heightSpecSize;
        }
        int i = thumbnailLp.leftMargin;
        int thumbnailWidth = (contentWidth - r0) - thumbnailLp.rightMargin;
        this.mThumbnail.measure(MeasureSpec.makeMeasureSpec(thumbnailWidth, 1073741824), MeasureSpec.makeMeasureSpec(thumbnailLp.height, 1073741824));
        MarginLayoutParams titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
        MarginLayoutParams subtitleLp = (MarginLayoutParams) this.mSubtitle.getLayoutParams();
        MarginLayoutParams labelLp = (MarginLayoutParams) this.mLabel.getLayoutParams();
        MarginLayoutParams ratingBadgeLp = (MarginLayoutParams) this.mRatingBadgeContainer.getLayoutParams();
        MarginLayoutParams snippetLp = (MarginLayoutParams) this.mSnippet2.getLayoutParams();
        if (this.mHighlightLabel != null) {
            highlightLp = (MarginLayoutParams) this.mHighlightLabel.getLayoutParams();
        } else {
            highlightLp = null;
        }
        this.mLabel.measure(MeasureSpec.makeMeasureSpec(contentWidth, Integer.MIN_VALUE), 0);
        PlayCardLabelView playCardLabelView = this.mLabel;
        int i2 = labelLp.leftMargin;
        int labelWidth = (r0.getMeasuredWidth() + r0) + labelLp.rightMargin;
        this.mOverflow.measure(0, 0);
        i = titleLp.leftMargin;
        int titleWidth = (contentWidth - r0) - titleLp.rightMargin;
        this.mTitle.measure(MeasureSpec.makeMeasureSpec(titleWidth, 1073741824), 0);
        if (this.mLoadingIndicator.getVisibility() != 0) {
            i2 = ratingBadgeLp.leftMargin;
            int ratingBadgeWidth = ((contentWidth - labelWidth) - r0) - ratingBadgeLp.rightMargin;
            this.mRatingBadgeContainer.measure(MeasureSpec.makeMeasureSpec(ratingBadgeWidth, Integer.MIN_VALUE), 0);
            if (this.mHighlightLabel != null) {
                if (this.mHighlightLabel.getVisibility() != 8) {
                    this.mHighlightLabel.measure(0, 0);
                    TextView textView = this.mHighlightLabel;
                    highlightLabelSpace = r0.getMeasuredWidth() + highlightLp.rightMargin;
                }
            }
            if (this.mSubtitle.getVisibility() != 8) {
                int subtitleAvailableWidth;
                if (this.mMode == 0) {
                    i = subtitleLp.leftMargin;
                    subtitleAvailableWidth = (contentWidth - r0) - subtitleLp.rightMargin;
                } else {
                    subtitleAvailableWidth = ((contentWidth - subtitleLp.leftMargin) - labelWidth) - highlightLabelSpace;
                }
                this.mSubtitle.measure(0, 0);
                if (this.mSubtitle.getMeasuredWidth() > subtitleAvailableWidth) {
                    this.mSubtitle.measure(MeasureSpec.makeMeasureSpec(subtitleAvailableWidth, 1073741824), 0);
                }
            }
            if (this.mSnippet2.getVisibility() != 8) {
                i = snippetLp.leftMargin;
                int snippetWidth = (contentWidth - r0) - snippetLp.rightMargin;
                this.mSnippet2.measure(MeasureSpec.makeMeasureSpec(snippetWidth, 1073741824), 1073741824);
            }
            i = titleLp.topMargin;
            TextView textView2 = this.mTitle;
            i2 = titleLp.bottomMargin;
            i2 = subtitleLp.topMargin;
            PlayTextView playTextView = this.mSubtitle;
            int textContentHeight = ((((r0 + r0.getMeasuredHeight()) + r0) + r0) + r0.getMeasuredHeight()) + subtitleLp.bottomMargin;
            if (this.mMode == 0) {
                i = labelLp.topMargin;
                PlayCardLabelView playCardLabelView2 = this.mLabel;
                textContentHeight += (r0 + r0.getMeasuredHeight()) + labelLp.bottomMargin;
            } else {
                if (this.mSnippet2.getVisibility() != 8) {
                    i = snippetLp.topMargin;
                    PlayCardSnippet playCardSnippet = this.mSnippet2;
                    textContentHeight += (r0 + r0.getMeasuredHeight()) + snippetLp.bottomMargin;
                }
            }
            int verticalGap = (((height - paddingTop) - paddingBottom) - thumbnailLp.height) - textContentHeight;
            if (verticalGap <= 0) {
                i = verticalGap / 2;
            } else {
                i = Math.min(verticalGap / 4, this.mVerticalBumpLimit);
            }
            this.mVerticalBump = i;
        } else {
            this.mVerticalBump = 0;
        }
        this.mLoadingIndicator.measure(0, 0);
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams highlightLabelLp;
        int labelTop;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth();
        int height = getHeight();
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        int thumbnailHeight = this.mThumbnail.getMeasuredHeight();
        this.mThumbnail.layout(thumbnailLp.leftMargin + paddingLeft, thumbnailLp.topMargin + paddingTop, (thumbnailLp.leftMargin + paddingLeft) + this.mThumbnail.getMeasuredWidth(), (thumbnailLp.topMargin + paddingTop) + thumbnailHeight);
        MarginLayoutParams titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
        MarginLayoutParams subtitleLp = (MarginLayoutParams) this.mSubtitle.getLayoutParams();
        MarginLayoutParams ratingBadgeLp = (MarginLayoutParams) this.mRatingBadgeContainer.getLayoutParams();
        MarginLayoutParams labelLp = (MarginLayoutParams) this.mLabel.getLayoutParams();
        MarginLayoutParams overflowLp = (MarginLayoutParams) this.mOverflow.getLayoutParams();
        MarginLayoutParams snippetLp = (MarginLayoutParams) this.mSnippet2.getLayoutParams();
        if (this.mHighlightLabel != null) {
            highlightLabelLp = (MarginLayoutParams) this.mHighlightLabel.getLayoutParams();
        } else {
            highlightLabelLp = null;
        }
        int i = titleLp.topMargin;
        int titleTop = ((paddingTop + thumbnailHeight) + r0) + this.mVerticalBump;
        int titleLeft = paddingLeft + titleLp.leftMargin;
        int titleHeight = this.mTitle.getMeasuredHeight();
        this.mTitle.layout(titleLeft, titleTop, this.mTitle.getMeasuredWidth() + titleLeft, titleTop + titleHeight);
        int overflowTop = titleTop + overflowLp.topMargin;
        int overflowRight = (width - paddingRight) - overflowLp.rightMargin;
        this.mOverflow.layout(overflowRight - this.mOverflow.getMeasuredWidth(), overflowTop, overflowRight, this.mOverflow.getMeasuredHeight() + overflowTop);
        int innerVerticalBump = Math.max(this.mVerticalBump, 0);
        int labelHeight = this.mLabel.getMeasuredHeight();
        if (this.mMode == 0) {
            i = labelLp.bottomMargin;
            labelTop = (((height - paddingBottom) - r0) - labelHeight) - this.mVerticalBump;
        } else {
            TextView textView = this.mTitle;
            labelTop = (r0.getBottom() + labelLp.topMargin) + innerVerticalBump;
        }
        int labelRight = (width - paddingRight) - labelLp.rightMargin;
        this.mLabel.layout(labelRight - this.mLabel.getMeasuredWidth(), labelTop, labelRight, labelTop + labelHeight);
        if (this.mSubtitle.getVisibility() != 8) {
            int subtitleTop;
            int highlightLabelSpace = 0;
            if (this.mMode == 0) {
                i = titleLp.bottomMargin;
                subtitleTop = (((titleTop + titleHeight) + r0) + subtitleLp.topMargin) + innerVerticalBump;
            } else {
                subtitleTop = (this.mLabel.getBaseline() + labelTop) - this.mSubtitle.getBaseline();
            }
            if (this.mHighlightLabel != null) {
                if (this.mHighlightLabel.getVisibility() != 8) {
                    int highlightTop = subtitleTop;
                    int highlightLeft = titleLeft;
                    this.mHighlightLabel.layout(highlightLeft, highlightTop, this.mHighlightLabel.getMeasuredWidth() + highlightLeft, this.mHighlightLabel.getMeasuredHeight() + highlightTop);
                    textView = this.mHighlightLabel;
                    highlightLabelSpace = r0.getMeasuredWidth() + highlightLabelLp.rightMargin;
                }
            }
            int subtitleLeft = highlightLabelSpace + titleLeft;
            this.mSubtitle.layout(subtitleLeft, subtitleTop, this.mSubtitle.getMeasuredWidth() + subtitleLeft, this.mSubtitle.getMeasuredHeight() + subtitleTop);
        }
        if (this.mRatingBadgeContainer.getVisibility() != 8) {
            int ratingBadgeTop = (this.mLabel.getBaseline() + labelTop) - this.mRatingBadgeContainer.getBaseline();
            int ratingBadgeLeft = paddingLeft + ratingBadgeLp.leftMargin;
            this.mRatingBadgeContainer.layout(ratingBadgeLeft, ratingBadgeTop, this.mRatingBadgeContainer.getMeasuredWidth() + ratingBadgeLeft, this.mRatingBadgeContainer.getMeasuredHeight() + ratingBadgeTop);
        }
        if (this.mSnippet2.getVisibility() != 8) {
            i = snippetLp.bottomMargin;
            int snippetBottom = ((height - paddingBottom) - r0) - this.mVerticalBump;
            int snippetLeft = paddingLeft + snippetLp.leftMargin;
            this.mSnippet2.layout(snippetLeft, snippetBottom - this.mSnippet2.getMeasuredHeight(), this.mSnippet2.getMeasuredWidth() + snippetLeft, snippetBottom);
        }
        int loadingWidth = this.mLoadingIndicator.getMeasuredWidth();
        int loadingLeft = paddingLeft + ((((width - paddingLeft) - paddingRight) - loadingWidth) / 2);
        int loadingTop = paddingTop + ((((height - paddingTop) - paddingBottom) - this.mLoadingIndicator.getMeasuredHeight()) / 2);
        this.mLoadingIndicator.layout(loadingLeft, loadingTop, this.mLoadingIndicator.getMeasuredWidth() + loadingLeft, this.mLoadingIndicator.getMeasuredHeight() + loadingTop);
        recomputeOverflowAreaIfNeeded();
    }

    public int getCardType() {
        return 1;
    }
}
