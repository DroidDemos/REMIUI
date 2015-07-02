package com.google.android.finsky.layout.play;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayCardViewRate.RateListener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.UiUtils.ClusterFadeOutListener;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardRateClusterView extends PlayCardClusterView implements PlayCardDismissListener {
    private ClusterFadeOutListener mClusterFadeOutListener;
    private TextView mEmptyDone;
    private boolean mRejectTouchEvents;

    public PlayCardRateClusterView(Context context) {
        this(context, null);
    }

    public PlayCardRateClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected int getPlayStoreUiElementType() {
        return 412;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mEmptyDone = (TextView) findViewById(R.id.empty_done);
    }

    public void setClusterFadeOutListener(ClusterFadeOutListener clusterFadeOutListener) {
        this.mClusterFadeOutListener = clusterFadeOutListener;
    }

    public void onDismissDocument(Document doc, PlayCardViewBase card) {
        this.mClientMutationCache.dismissRecommendation(doc.getDocId());
        fadeOutCard((PlayCardViewRate) card);
    }

    public static boolean isClusterDismissed(ClientMutationCache cache, Document clusterDoc) {
        for (int i = 0; i < clusterDoc.getChildCount(); i++) {
            Document childDoc = clusterDoc.getChildAt(i);
            if (childDoc != null && !cache.isDismissedRecommendation(childDoc.getDocId()) && cache.getCachedReview(childDoc.getDocId(), null) == null) {
                return false;
            }
        }
        return true;
    }

    public void createContent(PlayCardClusterMetadata metadata, ClientMutationCache clientMutationCache, DfeApi dfeApi, NavigationManager navigationManager, BitmapLoader bitmapLoader, PlayCardDismissListener dismissListener, PlayCardHeap cardHeap, PlayStoreUiElementNode parentNode) {
        super.createContent(metadata, clientMutationCache, dfeApi, navigationManager, bitmapLoader, this, cardHeap, parentNode);
        int cardCount = getCardChildCount();
        for (int i = 0; i < cardCount; i++) {
            final PlayCardViewRate rateCard = (PlayCardViewRate) getCardChildAt(i);
            rateCard.setRateListener(new RateListener() {
                public void onRate(int rating, boolean committed) {
                    PlayCardRateClusterView.this.mRejectTouchEvents = true;
                    if (committed) {
                        PlayCardRateClusterView.this.fadeOutCard(rateCard);
                    }
                }

                public void onRateCleared() {
                    PlayCardRateClusterView.this.mRejectTouchEvents = false;
                }
            });
            rateCard.setState(0);
        }
        if (!this.mContent.hasItemsToRate()) {
            transitionToEmptyState(false);
        }
        this.mRejectTouchEvents = false;
    }

    private void fadeOutCard(final PlayCardViewRate rateCard) {
        rateCard.startAnimation(PlayAnimationUtils.getFadeOutAnimation(getContext(), 150, new AnimationListenerAdapter() {
            public void onAnimationEnd(Animation animation) {
                rateCard.setVisibility(4);
                rateCard.setState(2);
                PlayCardRateClusterView.this.startSlideTransition(rateCard);
            }
        }));
    }

    private void startSlideTransition(final PlayCardViewRate rateCard) {
        final int cardIndex = this.mContent.getCardChildIndex(rateCard);
        final int fullSlideDistance = rateCard.getWidth();
        final int cardCount = getCardChildCount();
        final int[] cardOriginalLeftPositions = new int[cardCount];
        for (int i = 0; i < cardCount; i++) {
            cardOriginalLeftPositions[i] = getCardChildAt(i).getLeft();
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) fullSlideDistance, 0.0f) {
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int slideDistanceFromBeginning = (int) (((float) fullSlideDistance) * interpolatedTime);
                for (int i = cardIndex + 1; i < cardCount; i++) {
                    PlayCardViewBase cardChild = PlayCardRateClusterView.this.getCardChildAt(i);
                    cardChild.offsetLeftAndRight((cardOriginalLeftPositions[i] - slideDistanceFromBeginning) - cardChild.getLeft());
                }
            }
        };
        translateAnimation.setDuration(200);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    public void run() {
                        PlayCardRateClusterView.this.onSlideTransitionCompleted(rateCard);
                    }
                }, 50);
            }
        });
        this.mContent.startAnimation(translateAnimation);
    }

    private void onSlideTransitionCompleted(PlayCardViewRate rateCard) {
        this.mContent.removeView(rateCard);
        this.mContent.addView(rateCard);
        PlayCardRateClusterViewContent mRateContent = this.mContent;
        mRateContent.syncIndexMapping();
        if (mRateContent.hasItemsToRate()) {
            rateCard.setState(0);
            rateCard.clearRating();
            rateCard.setVisibility(0);
            int newTileIndex = getCardChildCount() - 1;
            this.mContent.bindCardAt(newTileIndex, mRateContent.tileIndexToDocumentIndex(newTileIndex), this);
        } else {
            transitionToEmptyState(true);
        }
        this.mRejectTouchEvents = false;
    }

    private void transitionToEmptyState(boolean toAnimate) {
        if (toAnimate) {
            this.mContent.startAnimation(PlayAnimationUtils.getFadeOutAnimation(getContext(), 250, new AnimationListenerAdapter() {
                public void onAnimationEnd(Animation animation) {
                    PlayCardRateClusterView.this.mContent.setVisibility(8);
                    if (PlayListView.ENABLE_ANIMATION) {
                        UiUtils.fadeOutCluster(PlayCardRateClusterView.this, PlayCardRateClusterView.this.mClusterFadeOutListener, 2500);
                    }
                }
            }));
            Animation fadeInAnimation = PlayAnimationUtils.getFadeInAnimation(getContext(), 250, null);
            this.mEmptyDone.setVisibility(0);
            UiUtils.sendAccessibilityEventWithText(getContext(), this.mEmptyDone.getText().toString(), this.mEmptyDone);
            this.mEmptyDone.startAnimation(fadeInAnimation);
            return;
        }
        this.mContent.setVisibility(4);
        this.mEmptyDone.setVisibility(0);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.mRejectTouchEvents || super.onInterceptTouchEvent(ev);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom();
        if (!(this.mHeader == null || this.mHeader.getVisibility() == 8)) {
            this.mHeader.measure(widthMeasureSpec, 0);
            height += this.mHeader.getMeasuredHeight();
        }
        this.mContent.measure(widthMeasureSpec, 0);
        height += this.mContent.getMeasuredHeight();
        this.mEmptyDone.measure(MeasureSpec.makeMeasureSpec(this.mContent.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(this.mContent.getMeasuredHeight(), Integer.MIN_VALUE));
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int y = getPaddingTop();
        if (!(this.mHeader == null || this.mHeader.getVisibility() == 8)) {
            this.mHeader.layout(0, y, width, this.mHeader.getMeasuredHeight() + y);
            y += this.mHeader.getMeasuredHeight();
        }
        int contentScrollerHeight = this.mContent.getMeasuredHeight();
        this.mContent.layout(0, y, width, y + contentScrollerHeight);
        int emptyDoneY = y + ((contentScrollerHeight - this.mEmptyDone.getMeasuredHeight()) / 2);
        this.mEmptyDone.layout(0, emptyDoneY, width, this.mEmptyDone.getMeasuredHeight() + emptyDoneY);
    }
}
