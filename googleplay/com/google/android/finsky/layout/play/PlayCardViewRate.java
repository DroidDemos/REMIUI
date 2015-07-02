package com.google.android.finsky.layout.play;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayRatingBar.OnRatingChangeListener;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;
import com.google.android.finsky.utils.RateReviewHelper;
import com.google.android.finsky.utils.RateReviewHelper.RateReviewListener;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.layout.PlayCardLabelView;
import com.google.android.play.layout.PlayCardThumbnail;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardViewRate extends PlayCardViewBase implements OnRatingChangeListener {
    private PlayCardViewRateOverlay mContentOverlay;
    private PlayRatingBar mRateBar;
    private View mRateBarSeparator;
    private RateListener mRateListener;
    private int mState;

    public interface RateListener {
        void onRate(int i, boolean z);

        void onRateCleared();
    }

    public PlayCardViewRate(Context context) {
        this(context, null);
    }

    public PlayCardViewRate(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mState = 0;
    }

    public void setRateListener(RateListener rateListener) {
        this.mRateListener = rateListener;
    }

    public void setState(int state) {
        boolean z = false;
        if (this.mState != state) {
            this.mState = state;
            this.mContentOverlay.setVisibility(this.mState == 1 ? 0 : 8);
            PlayRatingBar playRatingBar = this.mRateBar;
            if (this.mState != 1) {
                z = true;
            }
            playRatingBar.setEnabled(z);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mRateBarSeparator = findViewById(R.id.rate_separator);
        this.mRateBar = (PlayRatingBar) findViewById(R.id.rate_bar);
        this.mContentOverlay = (PlayCardViewRateOverlay) findViewById(R.id.content_overlay);
        if (!DISABLE_NESTED_FOCUS_TRAVERSAL) {
            setNextFocusDownId(R.id.star3);
            this.mRateBar.findViewById(R.id.star3).setNextFocusUpId(R.id.play_card);
        }
    }

    public int getCardType() {
        return 13;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean isApp;
        int width;
        int height;
        int paddingLeft;
        int paddingRight;
        int paddingTop;
        int paddingBottom;
        int contentWidth;
        MarginLayoutParams thumbnailLp;
        MarginLayoutParams titleLp;
        MarginLayoutParams subtitleLp;
        MarginLayoutParams labelLp;
        MarginLayoutParams snippetLp;
        MarginLayoutParams rateBarLp;
        MarginLayoutParams rateBarSeparatorLp;
        int i;
        PlayRatingBar playRatingBar;
        int i2;
        int rateBlockHeight;
        int thumbnailHeight;
        float f;
        int thumbnailWidth;
        int textColumnWidth;
        int separatorWidth;
        PlayCardLabelView playCardLabelView;
        int labelWidth;
        int titleWidth;
        int subtitleWidth;
        int overlayHeight;
        if (this.mData != null) {
            if (((Document) this.mData).getBackend() == 3) {
                isApp = true;
                width = MeasureSpec.getSize(widthMeasureSpec);
                height = MeasureSpec.getSize(heightMeasureSpec);
                paddingLeft = getPaddingLeft();
                paddingRight = getPaddingRight();
                paddingTop = getPaddingTop();
                paddingBottom = getPaddingBottom();
                contentWidth = (width - paddingLeft) - paddingRight;
                thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
                titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
                subtitleLp = (MarginLayoutParams) this.mSubtitle.getLayoutParams();
                labelLp = (MarginLayoutParams) this.mLabel.getLayoutParams();
                snippetLp = (MarginLayoutParams) this.mSnippet1.getLayoutParams();
                rateBarLp = (MarginLayoutParams) this.mRateBar.getLayoutParams();
                rateBarSeparatorLp = (MarginLayoutParams) this.mRateBarSeparator.getLayoutParams();
                this.mRateBar.measure(MeasureSpec.makeMeasureSpec(contentWidth, 1073741824), 0);
                i = rateBarLp.topMargin;
                playRatingBar = this.mRateBar;
                i2 = rateBarLp.bottomMargin;
                rateBlockHeight = ((r0 + r0.getMeasuredHeight()) + r0) + rateBarSeparatorLp.bottomMargin;
                if (isApp) {
                    rateBlockHeight += rateBarSeparatorLp.height + rateBarSeparatorLp.topMargin;
                }
                thumbnailHeight = ((height - paddingTop) - paddingBottom) - rateBlockHeight;
                f = (float) ((((width - paddingLeft) - paddingRight) * 2) / 3);
                thumbnailWidth = (int) Math.min(((float) thumbnailHeight) / this.mThumbnailAspectRatio, r0);
                this.mThumbnail.measure(MeasureSpec.makeMeasureSpec(thumbnailWidth, 1073741824), MeasureSpec.makeMeasureSpec(thumbnailHeight, 1073741824));
                textColumnWidth = (contentWidth - thumbnailWidth) - thumbnailLp.rightMargin;
                if (this.mRateBar != null) {
                    if (isApp) {
                        i = rateBarSeparatorLp.rightMargin;
                        separatorWidth = (textColumnWidth - r0) - titleLp.leftMargin;
                    } else {
                        i = rateBarSeparatorLp.leftMargin;
                        separatorWidth = (contentWidth - r0) - rateBarSeparatorLp.rightMargin;
                    }
                    this.mRateBarSeparator.measure(MeasureSpec.makeMeasureSpec(separatorWidth, 1073741824), MeasureSpec.makeMeasureSpec(rateBarSeparatorLp.height, 1073741824));
                }
                this.mLabel.measure(MeasureSpec.makeMeasureSpec(contentWidth, Integer.MIN_VALUE), 0);
                playCardLabelView = this.mLabel;
                i2 = labelLp.leftMargin;
                labelWidth = (r0.getMeasuredWidth() + r0) + labelLp.rightMargin;
                this.mOverflow.measure(0, 0);
                i = titleLp.leftMargin;
                titleWidth = (textColumnWidth - r0) - titleLp.rightMargin;
                this.mTitle.measure(MeasureSpec.makeMeasureSpec(titleWidth, 1073741824), 0);
                i = subtitleLp.leftMargin;
                subtitleWidth = ((textColumnWidth - r0) - subtitleLp.rightMargin) - labelWidth;
                this.mSubtitle.measure(0, 0);
                if (this.mSubtitle.getMeasuredWidth() > subtitleWidth) {
                    this.mSubtitle.measure(MeasureSpec.makeMeasureSpec(subtitleWidth, 1073741824), 0);
                }
                if (this.mSnippet1.getVisibility() != 8) {
                    i = snippetLp.leftMargin;
                    int snippetWidth = (textColumnWidth - r0) - snippetLp.rightMargin;
                    this.mSnippet1.measure(MeasureSpec.makeMeasureSpec(snippetWidth, 1073741824), 1073741824);
                }
                if (this.mContentOverlay.getVisibility() != 8) {
                    overlayHeight = thumbnailHeight;
                    if (isApp && this.mRateBar != null) {
                        View view = this.mRateBarSeparator;
                        overlayHeight += r0.getMeasuredHeight() + rateBarSeparatorLp.topMargin;
                    }
                    this.mContentOverlay.measure(MeasureSpec.makeMeasureSpec(contentWidth, 1073741824), MeasureSpec.makeMeasureSpec(overlayHeight, 1073741824));
                }
                this.mLoadingIndicator.measure(0, 0);
                setMeasuredDimension(width, height);
            }
        }
        isApp = false;
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();
        contentWidth = (width - paddingLeft) - paddingRight;
        thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
        subtitleLp = (MarginLayoutParams) this.mSubtitle.getLayoutParams();
        labelLp = (MarginLayoutParams) this.mLabel.getLayoutParams();
        snippetLp = (MarginLayoutParams) this.mSnippet1.getLayoutParams();
        rateBarLp = (MarginLayoutParams) this.mRateBar.getLayoutParams();
        rateBarSeparatorLp = (MarginLayoutParams) this.mRateBarSeparator.getLayoutParams();
        this.mRateBar.measure(MeasureSpec.makeMeasureSpec(contentWidth, 1073741824), 0);
        i = rateBarLp.topMargin;
        playRatingBar = this.mRateBar;
        i2 = rateBarLp.bottomMargin;
        rateBlockHeight = ((r0 + r0.getMeasuredHeight()) + r0) + rateBarSeparatorLp.bottomMargin;
        if (isApp) {
            rateBlockHeight += rateBarSeparatorLp.height + rateBarSeparatorLp.topMargin;
        }
        thumbnailHeight = ((height - paddingTop) - paddingBottom) - rateBlockHeight;
        f = (float) ((((width - paddingLeft) - paddingRight) * 2) / 3);
        thumbnailWidth = (int) Math.min(((float) thumbnailHeight) / this.mThumbnailAspectRatio, r0);
        this.mThumbnail.measure(MeasureSpec.makeMeasureSpec(thumbnailWidth, 1073741824), MeasureSpec.makeMeasureSpec(thumbnailHeight, 1073741824));
        textColumnWidth = (contentWidth - thumbnailWidth) - thumbnailLp.rightMargin;
        if (this.mRateBar != null) {
            if (isApp) {
                i = rateBarSeparatorLp.rightMargin;
                separatorWidth = (textColumnWidth - r0) - titleLp.leftMargin;
            } else {
                i = rateBarSeparatorLp.leftMargin;
                separatorWidth = (contentWidth - r0) - rateBarSeparatorLp.rightMargin;
            }
            this.mRateBarSeparator.measure(MeasureSpec.makeMeasureSpec(separatorWidth, 1073741824), MeasureSpec.makeMeasureSpec(rateBarSeparatorLp.height, 1073741824));
        }
        this.mLabel.measure(MeasureSpec.makeMeasureSpec(contentWidth, Integer.MIN_VALUE), 0);
        playCardLabelView = this.mLabel;
        i2 = labelLp.leftMargin;
        labelWidth = (r0.getMeasuredWidth() + r0) + labelLp.rightMargin;
        this.mOverflow.measure(0, 0);
        i = titleLp.leftMargin;
        titleWidth = (textColumnWidth - r0) - titleLp.rightMargin;
        this.mTitle.measure(MeasureSpec.makeMeasureSpec(titleWidth, 1073741824), 0);
        i = subtitleLp.leftMargin;
        subtitleWidth = ((textColumnWidth - r0) - subtitleLp.rightMargin) - labelWidth;
        this.mSubtitle.measure(0, 0);
        if (this.mSubtitle.getMeasuredWidth() > subtitleWidth) {
            this.mSubtitle.measure(MeasureSpec.makeMeasureSpec(subtitleWidth, 1073741824), 0);
        }
        if (this.mSnippet1.getVisibility() != 8) {
            i = snippetLp.leftMargin;
            int snippetWidth2 = (textColumnWidth - r0) - snippetLp.rightMargin;
            this.mSnippet1.measure(MeasureSpec.makeMeasureSpec(snippetWidth2, 1073741824), 1073741824);
        }
        if (this.mContentOverlay.getVisibility() != 8) {
            overlayHeight = thumbnailHeight;
            View view2 = this.mRateBarSeparator;
            overlayHeight += r0.getMeasuredHeight() + rateBarSeparatorLp.topMargin;
            this.mContentOverlay.measure(MeasureSpec.makeMeasureSpec(contentWidth, 1073741824), MeasureSpec.makeMeasureSpec(overlayHeight, 1073741824));
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
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        MarginLayoutParams titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
        MarginLayoutParams overflowLp = (MarginLayoutParams) this.mOverflow.getLayoutParams();
        MarginLayoutParams labelLp = (MarginLayoutParams) this.mLabel.getLayoutParams();
        MarginLayoutParams snippetLp = (MarginLayoutParams) this.mSnippet1.getLayoutParams();
        if (this.mRateBar.getVisibility() != 8) {
            MarginLayoutParams rateBarSeparatorLp = (MarginLayoutParams) this.mRateBarSeparator.getLayoutParams();
            MarginLayoutParams rateBarContainerLp = (MarginLayoutParams) this.mRateBar.getLayoutParams();
            int rateBarContainerWidth = this.mRateBar.getMeasuredWidth();
            int rateBarContainerHeight = this.mRateBar.getMeasuredHeight();
            int rateBarContainerLeft = paddingLeft + ((((width - paddingLeft) - paddingRight) - rateBarContainerWidth) / 2);
            int rateBarContainerTop = ((height - paddingBottom) - rateBarContainerLp.bottomMargin) - rateBarContainerHeight;
            this.mRateBar.layout(rateBarContainerLeft, rateBarContainerTop, rateBarContainerLeft + rateBarContainerWidth, rateBarContainerTop + rateBarContainerHeight);
            int rateBarSeparatorHeight = this.mRateBarSeparator.getMeasuredHeight();
            int rateBarSeparatorRight = (width - paddingRight) - rateBarSeparatorLp.rightMargin;
            int i = rateBarContainerLp.topMargin;
            int rateBarSeparatorTop = ((rateBarContainerTop - r0) - rateBarSeparatorLp.bottomMargin) - rateBarSeparatorHeight;
            this.mRateBarSeparator.layout(rateBarSeparatorRight - this.mRateBarSeparator.getMeasuredWidth(), rateBarSeparatorTop, rateBarSeparatorRight, this.mRateBarSeparator.getMeasuredHeight() + rateBarSeparatorTop);
        }
        int thumbnailHeight = this.mThumbnail.getMeasuredHeight();
        int thumbnailWidth = this.mThumbnail.getMeasuredWidth();
        this.mThumbnail.layout(thumbnailLp.leftMargin + paddingLeft, thumbnailLp.topMargin + paddingTop, (thumbnailLp.leftMargin + paddingLeft) + thumbnailWidth, (thumbnailLp.topMargin + paddingTop) + thumbnailHeight);
        PlayCardThumbnail playCardThumbnail = this.mThumbnail;
        int textColumnLeft = r0.getRight() + thumbnailLp.rightMargin;
        int titleTop = paddingTop + titleLp.topMargin;
        int titleLeft = textColumnLeft + titleLp.leftMargin;
        int titleHeight = this.mTitle.getMeasuredHeight();
        this.mTitle.layout(titleLeft, titleTop, this.mTitle.getMeasuredWidth() + titleLeft, titleTop + titleHeight);
        int overflowTop = titleTop + overflowLp.topMargin;
        int overflowRight = (width - paddingRight) - overflowLp.rightMargin;
        this.mOverflow.layout(overflowRight - this.mOverflow.getMeasuredWidth(), overflowTop, overflowRight, this.mOverflow.getMeasuredHeight() + overflowTop);
        int labelHeight = this.mLabel.getMeasuredHeight();
        TextView textView = this.mTitle;
        int labelTop = r0.getBottom() + labelLp.topMargin;
        int labelRight = (width - paddingRight) - labelLp.rightMargin;
        this.mLabel.layout(labelRight - this.mLabel.getMeasuredWidth(), labelTop, labelRight, labelTop + labelHeight);
        int subtitleTop = (this.mLabel.getBaseline() + labelTop) - this.mSubtitle.getBaseline();
        this.mSubtitle.layout(titleLeft, subtitleTop, this.mSubtitle.getMeasuredWidth() + titleLeft, this.mSubtitle.getMeasuredHeight() + subtitleTop);
        if (this.mSnippet1.getVisibility() != 8) {
            playCardThumbnail = this.mThumbnail;
            int snippetBottom = r0.getBottom() - snippetLp.bottomMargin;
            int snippetLeft = titleLeft + snippetLp.leftMargin;
            this.mSnippet1.layout(snippetLeft, snippetBottom - this.mSnippet1.getMeasuredHeight(), this.mSnippet1.getMeasuredWidth() + snippetLeft, snippetBottom);
        }
        if (this.mContentOverlay.getVisibility() != 8) {
            this.mContentOverlay.layout(paddingLeft, paddingTop, this.mContentOverlay.getMeasuredWidth() + paddingLeft, this.mContentOverlay.getMeasuredHeight() + paddingTop);
        }
        int loadingWidth = this.mLoadingIndicator.getMeasuredWidth();
        int loadingLeft = paddingLeft + ((((width - paddingLeft) - paddingRight) - loadingWidth) / 2);
        int loadingTop = paddingTop + ((((height - paddingTop) - paddingBottom) - this.mLoadingIndicator.getMeasuredHeight()) / 2);
        this.mLoadingIndicator.layout(loadingLeft, loadingTop, this.mLoadingIndicator.getMeasuredWidth() + loadingLeft, this.mLoadingIndicator.getMeasuredHeight() + loadingTop);
    }

    public void bindLoading() {
        super.bindLoading();
        if (this.mRateBarSeparator != null) {
            this.mRateBarSeparator.setVisibility(8);
        }
        if (this.mRateBar != null) {
            this.mRateBar.setVisibility(8);
        }
    }

    public void setData(Object data, int backendId) {
        super.setData(data, backendId);
        Document doc = (Document) data;
        this.mRateBar.setVerticalPadding(R.dimen.rate_card_stars_vpadding);
        this.mRateBar.configure(0, this.mBackendId, this);
        this.mRateBar.setContentDescription(CorpusResourceUtils.getRateString(getResources(), doc.getDocumentType()));
    }

    public void onRatingChanged(PlayRatingBar ratingBar, int rating) {
        setRating((float) rating, false);
        FinskyApp.get().getEventLogger().logClickEvent(1208, null, (PlayStoreUiElementNode) getLoggingData());
        if (ratingBar.getContext() instanceof FragmentActivity) {
            Document doc = (Document) getData();
            RateReviewHelper.rateDocument(FinskyApp.get().getCurrentAccountName(), doc.getDocId(), doc.getDetailsUrl(), Math.round((float) rating), (FragmentActivity) ratingBar.getContext(), new RateReviewListener() {
                public void onRateReviewCommitted(int rating, String title, String content) {
                    PlayCardViewRate.this.setRating((float) rating, true);
                }

                public void onRateReviewFailed() {
                    PlayCardViewRate.this.clearRating();
                }
            }, 413);
            return;
        }
        FinskyLog.wtf("View context is not a fragment activity in Rate Card", new Object[0]);
    }

    private void setRating(float ratingFloat, boolean isCommitted) {
        int rating = Math.round(ratingFloat);
        if (rating > 0) {
            if (this.mRateListener != null) {
                this.mRateListener.onRate(rating, isCommitted);
            }
            this.mRateBar.setRating(rating);
            if (rating > 0) {
                UiUtils.sendAccessibilityEventWithText(getContext(), getResources().getQuantityString(R.plurals.content_description_rated, rating, new Object[]{Integer.valueOf(rating)}), this.mRateBar);
            }
            if (this.mState == 0) {
                setState(1);
                Animation fadeInAnimation = PlayAnimationUtils.getFadeInAnimation(getContext(), 100, null);
                this.mContentOverlay.setVisibility(0);
                this.mContentOverlay.configure(rating, ((Document) getData()).getBackend());
                this.mContentOverlay.startAnimation(fadeInAnimation);
            }
            invalidate();
        }
    }

    public void clearRating() {
        if (this.mRateListener != null) {
            this.mRateListener.onRateCleared();
        }
        this.mRateBar.setRating(0);
        if (this.mState == 1) {
            this.mContentOverlay.startAnimation(PlayAnimationUtils.getFadeOutAnimation(getContext(), 250, new AnimationListenerAdapter() {
                public void onAnimationEnd(Animation animation) {
                    PlayCardViewRate.this.setState(0);
                }
            }));
        }
        invalidate();
    }

    public void setVisibility(int visibility) {
        if (this.mState != 2 || visibility != 0) {
            super.setVisibility(visibility);
        }
    }
}
