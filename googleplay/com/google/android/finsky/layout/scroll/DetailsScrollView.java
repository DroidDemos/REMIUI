package com.google.android.finsky.layout.scroll;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Animation;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.ObservableScrollView;
import com.google.android.finsky.layout.ObservableScrollView.ScrollListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;
import com.google.android.finsky.utils.PlayAnimationUtils.ScrollAnimation;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.image.FifeImageView.OnLoadedListener;

public class DetailsScrollView extends ObservableScrollView implements OnLoadedListener {
    private boolean mAutoStartExpandTransition;
    private ScrollAnimation mCollapserAnimation;
    private final int mDefaultHeroImageHeight;
    private int mDocBackend;
    private ScrollAnimation mExpanderAnimation;
    private HeroGraphicView mHeroGraphicView;
    private ViewGroup mParent;

    public DetailsScrollView(Context context) {
        this(context, null);
    }

    public DetailsScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDefaultHeroImageHeight = context.getResources().getDimensionPixelSize(R.dimen.hero_image_height);
    }

    public void bindDetailsHero(ViewGroup parent, Document doc, final HeroGraphicView heroGraphicView, BitmapLoader bitmapLoader, PlayStoreUiElementNode parentNode, boolean autoStartExpandTransition, boolean startInExpandedMode) {
        this.mParent = parent;
        this.mHeroGraphicView = heroGraphicView;
        this.mAutoStartExpandTransition = autoStartExpandTransition;
        this.mDocBackend = doc.getBackend();
        if (!startInExpandedMode) {
            heroGraphicView.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                public boolean onPreDraw() {
                    int imageWidth = heroGraphicView.getWidth();
                    DetailsScrollView.this.scrollTo(0, ((int) (((float) imageWidth) * heroGraphicView.getCurrentAspectRatio())) - DetailsScrollView.this.mDefaultHeroImageHeight);
                    heroGraphicView.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
        }
        heroGraphicView.bindDetailsDefault(doc, bitmapLoader, true, this, parentNode);
    }

    public void onLoaded(FifeImageView imageView, Bitmap bitmap) {
        this.mHeroGraphicView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int currScrollY = DetailsScrollView.this.getScrollY();
                DetailsScrollView.this.startAnimation(PlayAnimationUtils.getVerticalScrollByAnimation(DetailsScrollView.this, 0, 300, currScrollY > 0 ? -currScrollY : DetailsScrollView.this.mHeroGraphicView.getHeight() - DetailsScrollView.this.mDefaultHeroImageHeight, null));
            }
        });
        if (this.mAutoStartExpandTransition) {
            float aspectRatio = ((float) bitmap.getWidth()) / ((float) bitmap.getHeight());
            final int imageHeight = (int) (((float) imageView.getWidth()) / aspectRatio);
            this.mExpanderAnimation = PlayAnimationUtils.getVerticalScrollByAnimation(this, 1000, 300, -getScrollY(), new AnimationListenerAdapter() {
                public void onAnimationEnd(Animation animation) {
                    FinskyPreferences.lastAutomaticHeroSequenceOnDetailsTimeShown.get(DetailsScrollView.this.mDocBackend).put(Long.valueOf(System.currentTimeMillis()));
                    if (DetailsScrollView.this.getScrollY() == 0) {
                        DetailsScrollView.this.mCollapserAnimation = PlayAnimationUtils.getVerticalScrollByAnimation(DetailsScrollView.this, 1500, 200, imageHeight - DetailsScrollView.this.mDefaultHeroImageHeight, null);
                        DetailsScrollView.this.addOnScrollListener(new ScrollListener() {
                            public void onScroll(int left, int top) {
                                DetailsScrollView.this.cancelScrollAnimationIfWaiting(DetailsScrollView.this.mCollapserAnimation);
                                DetailsScrollView.this.removeOnScrollListener(this);
                            }
                        });
                        DetailsScrollView.this.mParent.startAnimation(DetailsScrollView.this.mCollapserAnimation);
                    }
                }
            });
            addOnScrollListener(new ScrollListener() {
                public void onScroll(int left, int top) {
                    DetailsScrollView.this.cancelScrollAnimationIfWaiting(DetailsScrollView.this.mExpanderAnimation);
                    DetailsScrollView.this.removeOnScrollListener(this);
                }
            });
            this.mParent.startAnimation(this.mExpanderAnimation);
        }
    }

    public void onLoadedAndFadedIn(FifeImageView imageView) {
    }

    private void cancelScrollAnimationIfWaiting(ScrollAnimation scrollAnimation) {
        if (!scrollAnimation.hasStartedScrolling() && !scrollAnimation.hasEnded()) {
            scrollAnimation.cancel();
        }
    }

    public void cancelScrollAnimationsIfRunning() {
        this.mAutoStartExpandTransition = false;
        if (!(this.mExpanderAnimation == null || this.mExpanderAnimation.hasEnded())) {
            this.mExpanderAnimation.cancel();
        }
        if (this.mCollapserAnimation != null && !this.mCollapserAnimation.hasEnded()) {
            this.mCollapserAnimation.cancel();
        }
    }
}
