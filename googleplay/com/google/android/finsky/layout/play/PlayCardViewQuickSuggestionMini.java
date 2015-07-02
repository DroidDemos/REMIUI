package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.android.vending.R;
import com.google.android.finsky.layout.play.PlayCardRateAndSuggestClusterViewContent.PendingStateHandler;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.play.layout.PlayCardViewMini;

public class PlayCardViewQuickSuggestionMini extends PlayCardViewMini implements AnimationListener, PendingStateHandler {
    private final int mCardInset;
    private boolean mIsInPendingState;
    private View mPendingOverlay;

    public PlayCardViewQuickSuggestionMini(Context context) {
        this(context, null);
    }

    public PlayCardViewQuickSuggestionMini(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIsInPendingState = true;
        this.mCardInset = context.getResources().getDimensionPixelSize(R.dimen.play_card_inset);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mPendingOverlay = findViewById(R.id.pending_overlay);
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.mPendingOverlay.setVisibility(this.mIsInPendingState ? 0 : 8);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mPendingOverlay.setVisibility(0);
        this.mIsInPendingState = true;
    }

    protected void onDetachedFromWindow() {
        this.mPendingOverlay.setVisibility(8);
        this.mIsInPendingState = false;
        super.onDetachedFromWindow();
    }

    public void enterPendingStateIfNecessary(boolean animateChanges) {
        this.mLoadingIndicator.setVisibility(8);
        if (!this.mIsInPendingState) {
            this.mIsInPendingState = true;
            if (animateChanges) {
                Animation fadeInAnimation = PlayAnimationUtils.getFadeInAnimation(getContext(), 300, this);
                this.mPendingOverlay.setVisibility(0);
                this.mPendingOverlay.startAnimation(fadeInAnimation);
                return;
            }
            this.mPendingOverlay.setVisibility(0);
        }
    }

    public void exitPendingStateIfNecessary(boolean animateChanges) {
        if (this.mIsInPendingState) {
            this.mIsInPendingState = false;
            if (animateChanges) {
                this.mPendingOverlay.startAnimation(PlayAnimationUtils.getFadeOutAnimation(getContext(), 300, this));
                return;
            }
            this.mPendingOverlay.setVisibility(8);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mPendingOverlay.getVisibility() != 8) {
            this.mPendingOverlay.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth() - (this.mCardInset * 2), 1073741824), MeasureSpec.makeMeasureSpec(getMeasuredHeight() - (this.mCardInset * 2), 1073741824));
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.mPendingOverlay.getVisibility() != 8) {
            int overlayTop = this.mThumbnail.getTop();
            this.mPendingOverlay.layout(this.mCardInset, overlayTop, this.mCardInset + this.mPendingOverlay.getMeasuredWidth(), this.mPendingOverlay.getMeasuredHeight() + overlayTop);
        }
    }
}
