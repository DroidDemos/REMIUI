package com.google.android.play.layout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.google.android.play.R;

public class PlayCardViewMini extends PlayCardViewBase {
    private int mCurrTitleMaxLines;
    private final int mLabelThreshold;
    private final int mTextContentHeight;
    private int mVerticalOverlap;

    public PlayCardViewMini(Context context) {
        this(context, null);
    }

    public PlayCardViewMini(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mTextContentHeight = res.getDimensionPixelSize(R.dimen.play_mini_card_content_height);
        this.mLabelThreshold = res.getDimensionPixelSize(R.dimen.play_mini_card_label_threshold);
        this.mCurrTitleMaxLines = 2;
    }

    public void setTitleMaxLines(int titleMaxLines) {
        if (this.mCurrTitleMaxLines != titleMaxLines) {
            this.mCurrTitleMaxLines = titleMaxLines;
            this.mTitle.setMaxLines(titleMaxLines);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height;
        boolean mToShowLabel;
        PlayCardLabelView playCardLabelView;
        int labelWidth;
        int ratingStarsWidth;
        int subtitleWidth;
        measureThumbnailSpanningWidth(widthMeasureSpec);
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int idealHeight = ((thumbnailLp.height + this.mTextContentHeight) + getPaddingTop()) + getPaddingBottom();
        if (heightSpecMode != 1073741824 || heightSpecSize <= 0) {
            height = idealHeight;
        } else {
            height = heightSpecSize;
        }
        int width = MeasureSpec.getSize(widthMeasureSpec);
        this.mVerticalOverlap = Math.max(0, (idealHeight - height) / 3);
        int contentWidth = (width - getPaddingLeft()) - getPaddingRight();
        this.mThumbnail.measure(MeasureSpec.makeMeasureSpec(contentWidth, 1073741824), MeasureSpec.makeMeasureSpec(thumbnailLp.height, 1073741824));
        MarginLayoutParams titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
        MarginLayoutParams subtitleLp = (MarginLayoutParams) this.mSubtitle.getLayoutParams();
        MarginLayoutParams ratingLp = (MarginLayoutParams) this.mRatingBar.getLayoutParams();
        MarginLayoutParams labelLp = (MarginLayoutParams) this.mLabel.getLayoutParams();
        int i = this.mBackendId;
        if (r0 == 3) {
            mToShowLabel = true;
        } else {
            mToShowLabel = this.mIsItemOwned || width >= this.mLabelThreshold;
        }
        if (mToShowLabel) {
            if (this.mLabel.getVisibility() != 8) {
                this.mLabel.measure(MeasureSpec.makeMeasureSpec(contentWidth, Integer.MIN_VALUE), 0);
                playCardLabelView = this.mLabel;
                labelWidth = (r0.getMeasuredWidth() + labelLp.leftMargin) + labelLp.rightMargin;
                this.mOverflow.measure(0, 0);
                this.mTitle.measure(MeasureSpec.makeMeasureSpec((contentWidth - titleLp.leftMargin) - titleLp.rightMargin, 1073741824), 0);
                if (this.mLoadingIndicator.getVisibility() != 0) {
                    if (this.mSubtitle.getVisibility() == 0) {
                        this.mRatingBar.measure(0, 0);
                        ratingStarsWidth = this.mRatingBar.getMeasuredWidth();
                        if (((ratingLp.leftMargin + ratingStarsWidth) + ratingLp.rightMargin) + labelWidth <= contentWidth) {
                            this.mRatingBar.setVisibility(4);
                        } else {
                            this.mRatingBar.setVisibility(0);
                        }
                    } else {
                        if (this.mTitle.getLineCount() != 1) {
                            if (this.mLabel.getVisibility() == 0) {
                                subtitleWidth = (contentWidth - subtitleLp.leftMargin) - labelWidth;
                                if (((double) subtitleWidth) >= 0.3d * ((double) contentWidth)) {
                                    this.mSubtitle.setVisibility(4);
                                } else {
                                    this.mSubtitle.setVisibility(0);
                                    this.mSubtitle.measure(MeasureSpec.makeMeasureSpec(subtitleWidth, 1073741824), 0);
                                }
                            }
                        }
                        subtitleWidth = (contentWidth - subtitleLp.leftMargin) - subtitleLp.rightMargin;
                        if (((double) subtitleWidth) >= 0.3d * ((double) contentWidth)) {
                            this.mSubtitle.setVisibility(0);
                            this.mSubtitle.measure(MeasureSpec.makeMeasureSpec(subtitleWidth, 1073741824), 0);
                        } else {
                            this.mSubtitle.setVisibility(4);
                        }
                    }
                }
                this.mLoadingIndicator.measure(0, 0);
                setMeasuredDimension(width, height);
            }
        }
        this.mLabel.measure(MeasureSpec.makeMeasureSpec(0, 1073741824), 0);
        playCardLabelView = this.mLabel;
        labelWidth = (r0.getMeasuredWidth() + labelLp.leftMargin) + labelLp.rightMargin;
        this.mOverflow.measure(0, 0);
        this.mTitle.measure(MeasureSpec.makeMeasureSpec((contentWidth - titleLp.leftMargin) - titleLp.rightMargin, 1073741824), 0);
        if (this.mLoadingIndicator.getVisibility() != 0) {
            if (this.mSubtitle.getVisibility() == 0) {
                if (this.mTitle.getLineCount() != 1) {
                    if (this.mLabel.getVisibility() == 0) {
                        subtitleWidth = (contentWidth - subtitleLp.leftMargin) - labelWidth;
                        if (((double) subtitleWidth) >= 0.3d * ((double) contentWidth)) {
                            this.mSubtitle.setVisibility(4);
                        } else {
                            this.mSubtitle.setVisibility(0);
                            this.mSubtitle.measure(MeasureSpec.makeMeasureSpec(subtitleWidth, 1073741824), 0);
                        }
                    }
                }
                subtitleWidth = (contentWidth - subtitleLp.leftMargin) - subtitleLp.rightMargin;
                if (((double) subtitleWidth) >= 0.3d * ((double) contentWidth)) {
                    this.mSubtitle.setVisibility(0);
                    this.mSubtitle.measure(MeasureSpec.makeMeasureSpec(subtitleWidth, 1073741824), 0);
                } else {
                    this.mSubtitle.setVisibility(4);
                }
            } else {
                this.mRatingBar.measure(0, 0);
                ratingStarsWidth = this.mRatingBar.getMeasuredWidth();
                if (((ratingLp.leftMargin + ratingStarsWidth) + ratingLp.rightMargin) + labelWidth <= contentWidth) {
                    this.mRatingBar.setVisibility(0);
                } else {
                    this.mRatingBar.setVisibility(4);
                }
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
        int thumbnailHeight = this.mThumbnail.getMeasuredHeight();
        this.mThumbnail.layout(paddingLeft, paddingTop, this.mThumbnail.getMeasuredWidth() + paddingLeft, paddingTop + thumbnailHeight);
        MarginLayoutParams titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
        MarginLayoutParams subtitleLp = (MarginLayoutParams) this.mSubtitle.getLayoutParams();
        MarginLayoutParams ratingLp = (MarginLayoutParams) this.mRatingBar.getLayoutParams();
        MarginLayoutParams labelLp = (MarginLayoutParams) this.mLabel.getLayoutParams();
        MarginLayoutParams overflowLp = (MarginLayoutParams) this.mOverflow.getLayoutParams();
        int i = titleLp.topMargin;
        int titleTop = ((paddingTop + thumbnailHeight) + r0) - this.mVerticalOverlap;
        int titleLeft = paddingLeft + titleLp.leftMargin;
        this.mTitle.layout(titleLeft, titleTop, this.mTitle.getMeasuredWidth() + titleLeft, this.mTitle.getMeasuredHeight() + titleTop);
        int overflowTop = titleTop + overflowLp.topMargin;
        int overflowRight = (width - paddingRight) - overflowLp.rightMargin;
        this.mOverflow.layout(overflowRight - this.mOverflow.getMeasuredWidth(), overflowTop, overflowRight, this.mOverflow.getMeasuredHeight() + overflowTop);
        i = labelLp.bottomMargin;
        int labelBottom = ((height - paddingBottom) - r0) + this.mVerticalOverlap;
        int labelRight = (width - paddingRight) - labelLp.rightMargin;
        this.mLabel.layout(labelRight - this.mLabel.getMeasuredWidth(), labelBottom - this.mLabel.getMeasuredHeight(), labelRight, labelBottom);
        if (this.mSubtitle.getVisibility() != 8) {
            int subtitleTop;
            if (this.mTitle.getLineCount() == 1) {
                TextView textView = this.mTitle;
                i = titleLp.bottomMargin;
                subtitleTop = ((r0.getMeasuredHeight() + titleTop) + r0) + subtitleLp.topMargin;
                this.mSubtitle.layout(titleLeft, subtitleTop, this.mSubtitle.getMeasuredWidth() + titleLeft, this.mSubtitle.getMeasuredHeight() + subtitleTop);
            } else {
                subtitleTop = ((labelBottom - this.mLabel.getMeasuredHeight()) + this.mLabel.getBaseline()) - this.mSubtitle.getBaseline();
                this.mSubtitle.layout(titleLeft, subtitleTop, this.mSubtitle.getMeasuredWidth() + titleLeft, this.mSubtitle.getMeasuredHeight() + subtitleTop);
            }
        }
        if (this.mRatingBar.getVisibility() != 8) {
            int ratingStarsTop = (this.mLabel.getTop() + this.mLabel.getBaseline()) - this.mRatingBar.getBaseline();
            int ratingStarsLeft = paddingLeft + ratingLp.leftMargin;
            this.mRatingBar.layout(ratingStarsLeft, ratingStarsTop, this.mRatingBar.getMeasuredWidth() + ratingStarsLeft, this.mRatingBar.getMeasuredHeight() + ratingStarsTop);
        }
        int loadingWidth = this.mLoadingIndicator.getMeasuredWidth();
        int loadingLeft = paddingLeft + ((((width - paddingLeft) - paddingRight) - loadingWidth) / 2);
        int loadingTop = paddingTop + ((((height - paddingTop) - paddingBottom) - this.mLoadingIndicator.getMeasuredHeight()) / 2);
        this.mLoadingIndicator.layout(loadingLeft, loadingTop, this.mLoadingIndicator.getMeasuredWidth() + loadingLeft, this.mLoadingIndicator.getMeasuredHeight() + loadingTop);
        recomputeOverflowAreaIfNeeded();
    }

    public int getCardType() {
        return 0;
    }
}
