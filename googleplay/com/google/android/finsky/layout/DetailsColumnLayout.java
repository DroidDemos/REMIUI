package com.google.android.finsky.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import com.android.vending.R;
import com.google.android.finsky.activities.TextSectionStateListener;
import com.google.android.finsky.layout.actionbar.FinskySearchToolbar;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.scroll.GestureScrollView;
import com.google.android.finsky.layout.scroll.GestureScrollView.FlingToDismissListener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;
import com.google.android.finsky.utils.Sets;
import java.util.List;
import java.util.Set;

public class DetailsColumnLayout extends FrameLayout {
    public static final boolean IS_ICS_OR_GREATER;
    private View mCardTransitionTarget;
    private int mCurrentlyExpandedSectionId;
    protected DetailsInnerColumnLayout mDetailsContainer;
    protected DetailsExpandedContainer mDetailsExpandedContainer;
    protected DetailsExpandedFrame mDetailsExpandedFrame;
    protected GestureScrollView mDetailsExpandedScroller;
    protected ScrollView mDetailsScroller;
    private int mExpandedContainerTopPadding;
    private boolean mHasLeadingGap;
    private final int mLeadingHeroSectionId;
    private int mOriginalDistanceBottom;
    private int mOriginalDistanceTop;
    private Set<Integer> mSectionsAbove;
    private Set<Integer> mSectionsBelow;
    private TextSectionStateListener mTextSectionStateListener;

    static {
        IS_ICS_OR_GREATER = VERSION.SDK_INT >= 14;
    }

    public DetailsColumnLayout(Context context) {
        this(context, null);
    }

    public DetailsColumnLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DetailsColumnLayout);
        this.mHasLeadingGap = attributes.getBoolean(0, false);
        this.mLeadingHeroSectionId = attributes.getResourceId(1, 0);
        attributes.recycle();
        if (IS_ICS_OR_GREATER) {
            this.mSectionsAbove = Sets.newHashSet();
            this.mSectionsBelow = Sets.newHashSet();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mDetailsScroller = (ScrollView) findViewById(R.id.details_scroller);
        this.mDetailsContainer = (DetailsInnerColumnLayout) this.mDetailsScroller.findViewById(R.id.details_container);
        this.mDetailsExpandedFrame = (DetailsExpandedFrame) findViewById(R.id.details_expanded_frame);
        this.mDetailsExpandedScroller = (GestureScrollView) this.mDetailsExpandedFrame.findViewById(R.id.details_expanded_scroller);
        this.mDetailsExpandedContainer = (DetailsExpandedContainer) this.mDetailsExpandedScroller.findViewById(R.id.details_expanded_container);
        this.mCardTransitionTarget = findViewById(R.id.card_transition_target);
    }

    public void enableActionBarOverlayScrolling() {
        this.mExpandedContainerTopPadding = FinskySearchToolbar.getToolbarHeight(getContext());
    }

    public void disableActionBarOverlayScrolling() {
        this.mExpandedContainerTopPadding = 0;
    }

    public void configure(TextSectionStateListener textSectionStateListener) {
        this.mTextSectionStateListener = textSectionStateListener;
        this.mDetailsExpandedScroller.setFlingToDismissListener(new FlingToDismissListener() {
            public void onFlingToDismiss() {
                DetailsColumnLayout.this.mTextSectionStateListener.onSectionCollapsed(DetailsColumnLayout.this.mCurrentlyExpandedSectionId);
            }
        });
    }

    public int getLeadingHeroSectionId() {
        return this.mLeadingHeroSectionId;
    }

    public View getCardTransitionTarget() {
        return this.mCardTransitionTarget;
    }

    public int getCurrentlyExpandedSectionId() {
        return this.mCurrentlyExpandedSectionId;
    }

    public void expandSection(int sectionLayoutId, NavigationManager navigationManager, int backendId, PlayStoreUiElementNode parentNode) {
        this.mCurrentlyExpandedSectionId = sectionLayoutId;
        DetailsTextSection section = (DetailsTextSection) this.mDetailsContainer.findViewById(sectionLayoutId);
        this.mDetailsExpandedContainer.populateFromSection(section.getId(), section.getExpandedData(), navigationManager, backendId, parentNode, this.mTextSectionStateListener);
        if (IS_ICS_OR_GREATER) {
            expandSectionIcs(section);
        } else {
            expandSectionPreIcs(section);
        }
    }

    public void collapseCurrentlyExpandedSection() {
        DetailsTextSection section = (DetailsTextSection) this.mDetailsContainer.findViewById(this.mCurrentlyExpandedSectionId);
        if (IS_ICS_OR_GREATER) {
            collapseCurrentlyExpandedSectionIcs(section);
        } else {
            collapseCurrentlyExpandedSectionPreIcs(section);
        }
        this.mCurrentlyExpandedSectionId = 0;
    }

    private void expandSectionPreIcs(DetailsTextSection section) {
        Animation fadeOutMainContent = PlayAnimationUtils.getFadeOutAnimation(getContext(), 75, new AnimationListenerAdapter() {
            public void onAnimationEnd(Animation animation) {
                DetailsColumnLayout.this.mDetailsScroller.setVisibility(8);
            }
        });
        fadeOutMainContent.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mDetailsExpandedFrame.setVisibility(0);
        this.mDetailsExpandedContainer.hideAllViews();
        int sectionTop = section.getTop();
        int sectionBottom = section.getBottom();
        int scrollerHeight = this.mDetailsScroller.getHeight();
        int scrollTop = this.mDetailsScroller.getScrollY();
        this.mOriginalDistanceTop = sectionTop - scrollTop;
        this.mOriginalDistanceBottom = (scrollTop + scrollerHeight) - sectionBottom;
        final int initialScrollerPaddingTop = getHeroVisibleHeight();
        TranslateAnimation expandPaddingsAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.mDetailsScroller.getHeight()) {
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int initialTitlePaddingTop = DetailsColumnLayout.this.mOriginalDistanceTop;
                DetailsColumnLayout.this.mDetailsExpandedContainer.setTopPaddingOnTopView(initialTitlePaddingTop - ((int) (((float) (initialTitlePaddingTop - DetailsColumnLayout.this.mExpandedContainerTopPadding)) * interpolatedTime)));
                DetailsColumnLayout.this.mDetailsExpandedScroller.setPadding(0, (int) (((float) initialScrollerPaddingTop) * (1.0f - interpolatedTime)), 0, 0);
            }
        };
        expandPaddingsAnimation.setDuration(300);
        expandPaddingsAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mDetailsContainer.setSeparatorsVisible(false);
        this.mDetailsScroller.startAnimation(fadeOutMainContent);
        this.mDetailsExpandedScroller.startAnimation(expandPaddingsAnimation);
        this.mDetailsExpandedContainer.fadeInContentPreIcs();
        this.mDetailsExpandedFrame.fadeInSideBarsPreIcs();
    }

    private void expandSectionIcs(DetailsTextSection section) {
        AnimatorSet expandSet = new AnimatorSet();
        List<Animator> animators = Lists.newArrayList();
        animators.add(PlayAnimationUtils.getFadeAnimator(section, 1.0f, 0.0f, 0, 75, null));
        int sectionTop = section.getTop();
        int sectionBottom = section.getBottom();
        int scrollerHeight = this.mDetailsScroller.getHeight();
        int scrollTop = this.mDetailsScroller.getScrollY();
        this.mOriginalDistanceTop = sectionTop - scrollTop;
        this.mOriginalDistanceBottom = (scrollTop + scrollerHeight) - sectionBottom;
        this.mSectionsAbove.clear();
        this.mSectionsBelow.clear();
        for (int i = 0; i < this.mDetailsContainer.getChildCount(); i++) {
            View child = this.mDetailsContainer.getChildAt(i);
            int childTop = child.getTop();
            if (childTop < sectionTop) {
                this.mSectionsAbove.add(Integer.valueOf(child.getId()));
            }
            if (childTop > sectionTop) {
                this.mSectionsBelow.add(Integer.valueOf(child.getId()));
            }
        }
        ValueAnimator translateAnimation = new ValueAnimator();
        translateAnimation.setFloatValues(new float[]{0.0f, (float) scrollerHeight});
        translateAnimation.setDuration(300);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                for (int i = 0; i < DetailsColumnLayout.this.mDetailsContainer.getChildCount(); i++) {
                    View child = DetailsColumnLayout.this.mDetailsContainer.getChildAt(i);
                    int childId = child.getId();
                    if (DetailsColumnLayout.this.mSectionsAbove.contains(Integer.valueOf(childId))) {
                        child.setTranslationY((float) (-((int) (((float) DetailsColumnLayout.this.mOriginalDistanceTop) * fraction))));
                    }
                    if (DetailsColumnLayout.this.mSectionsBelow.contains(Integer.valueOf(childId))) {
                        child.setTranslationY((float) ((int) (((float) DetailsColumnLayout.this.mOriginalDistanceBottom) * fraction)));
                    }
                }
            }
        });
        this.mDetailsContainer.blockLayoutRequests();
        translateAnimation.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                DetailsColumnLayout.this.mDetailsContainer.unblockLayoutRequests();
                DetailsColumnLayout.this.mDetailsContainer.requestLayout();
            }
        });
        animators.add(translateAnimation);
        this.mDetailsExpandedFrame.setVisibility(0);
        this.mDetailsExpandedFrame.setAlpha(1.0f);
        this.mDetailsExpandedFrame.setSideBarProportion(0.0f);
        this.mDetailsExpandedContainer.hideAllViews();
        int initialScrollerPaddingTop = getHeroVisibleHeight();
        ValueAnimator expandPaddingsAnimation = new ValueAnimator();
        expandPaddingsAnimation.setFloatValues(new float[]{0.0f, 1.0f});
        expandPaddingsAnimation.setDuration(300);
        expandPaddingsAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        final int i2 = initialScrollerPaddingTop;
        expandPaddingsAnimation.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                int initialTitlePaddingTop = DetailsColumnLayout.this.mOriginalDistanceTop;
                DetailsColumnLayout.this.mDetailsExpandedContainer.setTopPaddingOnTopView(initialTitlePaddingTop - ((int) (((float) (initialTitlePaddingTop - DetailsColumnLayout.this.mExpandedContainerTopPadding)) * fraction)));
                DetailsColumnLayout.this.mDetailsExpandedScroller.setPadding(0, (int) (((float) i2) * (1.0f - fraction)), 0, (int) (((float) Math.max(DetailsColumnLayout.this.mOriginalDistanceBottom, 0)) * (1.0f - fraction)));
            }
        });
        expandPaddingsAnimation.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                DetailsColumnLayout.this.mDetailsScroller.setVisibility(4);
            }
        });
        animators.add(expandPaddingsAnimation);
        ObjectAnimator sideBarsAnimator = ObjectAnimator.ofFloat(this.mDetailsExpandedFrame, "sideBarProportion", new float[]{0.0f, 1.0f});
        sideBarsAnimator.setStartDelay((long) 120);
        sideBarsAnimator.setDuration((long) 180);
        sideBarsAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        animators.add(sideBarsAnimator);
        this.mDetailsExpandedContainer.addFadeInAnimatorsIcs(animators);
        this.mDetailsContainer.setSeparatorsVisible(false);
        expandSet.playTogether(animators);
        expandSet.start();
    }

    private void collapseCurrentlyExpandedSectionPreIcs(DetailsTextSection section) {
        Animation fadeInMainContent = PlayAnimationUtils.getFadeInAnimation(getContext(), 75, 150, new AnimationListenerAdapter() {
            public void onAnimationStart(Animation animation) {
                DetailsColumnLayout.this.mDetailsScroller.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
                DetailsColumnLayout.this.mDetailsContainer.setSeparatorsVisible(true);
                DetailsColumnLayout.this.mDetailsExpandedFrame.setVisibility(8);
                DetailsColumnLayout.this.mDetailsExpandedContainer.resetContent();
            }
        });
        fadeInMainContent.setInterpolator(new AccelerateDecelerateInterpolator());
        final int initialScrollerPaddingTop = getHeroVisibleHeight();
        TranslateAnimation collapsePaddingsAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.mDetailsScroller.getHeight()) {
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                DetailsColumnLayout.this.mDetailsExpandedContainer.setTopPaddingOnTopView(DetailsColumnLayout.this.mExpandedContainerTopPadding + ((int) (((float) (DetailsColumnLayout.this.mOriginalDistanceTop - DetailsColumnLayout.this.mExpandedContainerTopPadding)) * interpolatedTime)));
                DetailsColumnLayout.this.mDetailsExpandedScroller.setPadding(0, (int) (((float) initialScrollerPaddingTop) * interpolatedTime), 0, 0);
            }
        };
        collapsePaddingsAnimation.setDuration(150);
        collapsePaddingsAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mDetailsExpandedContainer.fadeOutContentPreIcs();
        this.mDetailsExpandedScroller.startAnimation(collapsePaddingsAnimation);
        this.mDetailsScroller.startAnimation(fadeInMainContent);
        this.mDetailsExpandedFrame.fadeOutSideBarsPreIcs();
    }

    private void collapseCurrentlyExpandedSectionIcs(DetailsTextSection section) {
        AnimatorSet collapseSet = new AnimatorSet();
        List<Animator> animators = Lists.newArrayList();
        this.mDetailsScroller.setVisibility(0);
        int sectionTop = section.getTop();
        int extraScrollDistance = (sectionTop - this.mDetailsScroller.getScrollY()) - this.mOriginalDistanceTop;
        this.mDetailsScroller.scrollBy(0, extraScrollDistance);
        sectionTop -= extraScrollDistance;
        this.mDetailsContainer.blockLayoutRequests();
        int initialScrollerPaddingTop = getHeroVisibleHeight();
        this.mSectionsAbove.clear();
        this.mSectionsBelow.clear();
        for (int i = 0; i < this.mDetailsContainer.getChildCount(); i++) {
            View child = this.mDetailsContainer.getChildAt(i);
            int childTop = child.getTop();
            if (childTop < sectionTop) {
                this.mSectionsAbove.add(Integer.valueOf(child.getId()));
                child.setTranslationY((float) (-this.mOriginalDistanceTop));
            }
            if (childTop > sectionTop) {
                this.mSectionsBelow.add(Integer.valueOf(child.getId()));
                child.setTranslationY((float) this.mOriginalDistanceBottom);
            }
        }
        int scrollerHeight = this.mDetailsScroller.getHeight();
        ValueAnimator translateAnimation = new ValueAnimator();
        translateAnimation.setFloatValues(new float[]{0.0f, (float) scrollerHeight});
        translateAnimation.setDuration(300);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                for (int i = 0; i < DetailsColumnLayout.this.mDetailsContainer.getChildCount(); i++) {
                    View child = DetailsColumnLayout.this.mDetailsContainer.getChildAt(i);
                    int childId = child.getId();
                    if (DetailsColumnLayout.this.mSectionsAbove.contains(Integer.valueOf(childId))) {
                        child.setTranslationY((float) (((int) (((float) DetailsColumnLayout.this.mOriginalDistanceTop) * fraction)) - DetailsColumnLayout.this.mOriginalDistanceTop));
                    }
                    if (DetailsColumnLayout.this.mSectionsBelow.contains(Integer.valueOf(childId))) {
                        child.setTranslationY((float) (DetailsColumnLayout.this.mOriginalDistanceBottom - ((int) (((float) DetailsColumnLayout.this.mOriginalDistanceBottom) * fraction))));
                    }
                }
            }
        });
        translateAnimation.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                DetailsColumnLayout.this.mDetailsExpandedFrame.setVisibility(8);
                DetailsColumnLayout.this.mDetailsExpandedContainer.resetContent();
                DetailsColumnLayout.this.mDetailsContainer.setSeparatorsVisible(true);
                DetailsColumnLayout.this.mDetailsContainer.unblockLayoutRequests();
                DetailsColumnLayout.this.mDetailsContainer.requestLayout();
            }
        });
        animators.add(translateAnimation);
        ValueAnimator collapsePaddingsAnimation = new ValueAnimator();
        collapsePaddingsAnimation.setFloatValues(new float[]{0.0f, 1.0f});
        collapsePaddingsAnimation.setDuration(300);
        collapsePaddingsAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        final int i2 = initialScrollerPaddingTop;
        collapsePaddingsAnimation.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                DetailsColumnLayout.this.mDetailsExpandedContainer.setTopPaddingOnTopView(DetailsColumnLayout.this.mExpandedContainerTopPadding + ((int) (((float) (DetailsColumnLayout.this.mOriginalDistanceTop - DetailsColumnLayout.this.mExpandedContainerTopPadding)) * fraction)));
                DetailsColumnLayout.this.mDetailsExpandedScroller.setPadding(0, (int) (((float) i2) * fraction), 0, (int) (((float) Math.max(DetailsColumnLayout.this.mOriginalDistanceBottom, 0)) * fraction));
            }
        });
        animators.add(collapsePaddingsAnimation);
        ObjectAnimator sideBarsAnimator = ObjectAnimator.ofFloat(this.mDetailsExpandedFrame, "sideBarProportion", new float[]{1.0f, 0.0f});
        sideBarsAnimator.setDuration((long) 120);
        sideBarsAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        animators.add(sideBarsAnimator);
        this.mDetailsExpandedContainer.addFadeOutAnimatorsIcs(animators);
        animators.add(PlayAnimationUtils.getFadeAnimator(section, 0.0f, 1.0f, 75, 150, null));
        collapseSet.playTogether(animators);
        collapseSet.start();
    }

    private int getHeroVisibleHeight() {
        if (!this.mHasLeadingGap) {
            return 0;
        }
        int leadingHeroBottom = findViewById(this.mLeadingHeroSectionId).getBottom();
        int scrollTop = this.mDetailsScroller.getScrollY();
        if (leadingHeroBottom > scrollTop) {
            return leadingHeroBottom - scrollTop;
        }
        return 0;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mDetailsExpandedFrame.setScrollerWidth(MeasureSpec.getSize(widthMeasureSpec));
    }
}
