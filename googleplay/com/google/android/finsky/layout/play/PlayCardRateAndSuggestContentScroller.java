package com.google.android.finsky.layout.play;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import com.android.vending.R;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardRateAndSuggestContentScroller extends ViewGroup {
    private PlayCardClusterViewContent mClusterContent;
    private final Handler mHideFirstCardHandler;
    private boolean mShouldHideFirstCard;

    public PlayCardRateAndSuggestContentScroller(Context context) {
        this(context, null);
    }

    public PlayCardRateAndSuggestContentScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHideFirstCardHandler = new Handler(Looper.myLooper());
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mClusterContent = (PlayCardClusterViewContent) findViewById(R.id.cluster_content);
    }

    public void scrollAwayRateCard() {
        if (this.mClusterContent.getCardChildCount() != 0) {
            final int scrollTargetX = this.mClusterContent.getCardChildAt(0).getWidth();
            if (VERSION.SDK_INT < 14) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) scrollTargetX, 0.0f) {
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        PlayCardRateAndSuggestContentScroller.this.scrollTo((int) (((float) scrollTargetX) * interpolatedTime), 0);
                    }
                };
                translateAnimation.setDuration(200);
                translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                startAnimation(translateAnimation);
                this.mClusterContent.getCardChildAt(0).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
                return;
            }
            ObjectAnimator animator = ObjectAnimator.ofInt(this, "scrollX", new int[]{scrollTargetX});
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(200);
            animator.start();
            this.mClusterContent.getCardChildAt(0).animate().alpha(0.0f);
        }
    }

    public void hideRateCard() {
        this.mShouldHideFirstCard = true;
        requestLayout();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        PlayCardClusterMetadata clusterMetadata = this.mClusterContent.getMetadata();
        if (clusterMetadata == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int paddings = this.mClusterContent.getCardContentHorizontalPadding() * 2;
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec) - paddings;
        this.mClusterContent.measure(MeasureSpec.makeMeasureSpec(paddings + ((int) (((float) clusterMetadata.getWidth()) * (((float) availableWidth) / ((float) clusterMetadata.getViewportWidth())))), 1073741824), 0);
        setMeasuredDimension(availableWidth, this.mClusterContent.getMeasuredHeight());
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        this.mClusterContent.layout(0, 0, this.mClusterContent.getMeasuredWidth(), this.mClusterContent.getMeasuredHeight());
        if (this.mShouldHideFirstCard) {
            this.mShouldHideFirstCard = false;
            this.mHideFirstCardHandler.post(new Runnable() {
                public void run() {
                    if (PlayCardRateAndSuggestContentScroller.this.mClusterContent.getCardChildCount() > 0) {
                        PlayCardViewBase firstCard = PlayCardRateAndSuggestContentScroller.this.mClusterContent.getCardChildAt(0);
                        PlayCardRateAndSuggestContentScroller.this.scrollTo(firstCard.getWidth(), 0);
                        firstCard.setVisibility(4);
                    }
                }
            });
        }
    }
}
