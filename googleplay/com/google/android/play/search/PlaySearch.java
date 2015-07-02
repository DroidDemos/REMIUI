package com.google.android.play.search;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v7.view.CollapsibleActionView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import com.google.android.play.R;
import com.google.android.play.image.BitmapLoader;
import java.util.List;

public class PlaySearch extends FrameLayout implements CollapsibleActionView, PlaySearchListener {
    private static final boolean CAN_USE_RIPPLE_ANIMATION;
    private PlaySearchController mController;
    private PlaySearchListener mListener;
    private View mOverlay;
    private Point mRevealCenter;
    private View mSearchContainer;
    private PlaySearchPlate mSearchPlate;
    private PlaySearchSuggestionsList mSuggestionsList;

    static {
        CAN_USE_RIPPLE_ANIMATION = VERSION.SDK_INT >= 21;
    }

    public PlaySearch(Context context) {
        this(context, null);
    }

    public PlaySearch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mSearchPlate = (PlaySearchPlate) findViewById(R.id.play_search_plate);
        this.mSuggestionsList = (PlaySearchSuggestionsList) findViewById(R.id.play_search_suggestions_list);
        this.mOverlay = findViewById(R.id.play_search_overlay);
        this.mSearchContainer = findViewById(R.id.play_search_container);
        this.mOverlay.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlaySearch.this.switchToMode(1);
            }
        });
        this.mController = new PlaySearchController();
        this.mController.addPlaySearchListener(this);
        this.mSearchPlate.setPlaySearchController(this.mController);
        this.mSuggestionsList.setPlaySearchController(this.mController);
    }

    public void configure(BitmapLoader bitmapLoader) {
        this.mSuggestionsList.setBitmapLoader(bitmapLoader);
    }

    public void setListener(PlaySearchListener listener) {
        this.mListener = listener;
    }

    public void switchToMode(int searchMode) {
        this.mController.setMode(searchMode);
    }

    public void setSuggestions(List<PlaySearchSuggestionModel> suggestionModels) {
        this.mSuggestionsList.setSuggestions(suggestionModels);
    }

    public int getMode() {
        return this.mController.getMode();
    }

    public void setOnNavButtonClickListener(OnClickListener listener) {
        this.mController.setOnNavButtonClickListener(listener);
    }

    public void onQueryChanged(String query, boolean canUpdateSuggestions) {
        if (this.mListener != null) {
            this.mListener.onQueryChanged(query, canUpdateSuggestions);
        }
    }

    public void onModeChanged(int searchMode) {
        if (this.mListener != null) {
            this.mListener.onModeChanged(searchMode);
        }
        updateOverlayVisibility(searchMode == 2);
    }

    public void onSearch(String query) {
        if (this.mListener != null) {
            this.mListener.onSearch(query);
        }
    }

    public void onSuggestionClicked(PlaySearchSuggestionModel model) {
        if (this.mListener != null) {
            this.mListener.onSuggestionClicked(model);
        }
    }

    public void onActionViewExpanded() {
        setVisibility(0);
        if (CAN_USE_RIPPLE_ANIMATION) {
            animateExpansion();
        } else {
            switchToMode(2);
        }
    }

    public void onActionViewCollapsed() {
        switchToMode(1);
    }

    public void collapseWithAnimation(final Runnable callback) {
        if (CAN_USE_RIPPLE_ANIMATION) {
            animateCollapse(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (callback != null) {
                        callback.run();
                    }
                }
            });
            return;
        }
        setVisibility(4);
        if (callback != null) {
            callback.run();
        }
    }

    public void setRevealCenter(Point center) {
        this.mRevealCenter = new Point(center);
    }

    public void setSearchBoxPadding(int left, int top, int right, int bottom) {
        if (this.mSearchContainer != null) {
            this.mSearchContainer.setPadding(left, top, right, bottom);
        }
    }

    private void animateExpansion() {
        View parent = (View) getParent();
        if (parent != null) {
            Point center = getRevealCenter();
            Animator anim = ViewAnimationUtils.createCircularReveal(this.mSearchContainer, center.x, center.y, 0.0f, (float) parent.getWidth());
            anim.setDuration(300);
            anim.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    PlaySearch.this.switchToMode(2);
                }
            });
            anim.start();
        }
    }

    private void animateCollapse(AnimatorListener listener) {
        View parent = (View) getParent();
        if (parent != null) {
            Point center = getRevealCenter();
            Animator anim = ViewAnimationUtils.createCircularReveal(this.mSearchContainer, center.x, center.y, (float) parent.getWidth(), 0.0f);
            anim.setDuration(300);
            anim.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    PlaySearch.this.setVisibility(4);
                    PlaySearch.this.mOverlay.setVisibility(8);
                }
            });
            if (listener != null) {
                anim.addListener(listener);
            }
            anim.start();
        }
    }

    private Point getRevealCenter() {
        if (this.mRevealCenter != null) {
            Point center = new Point(this.mRevealCenter);
            Rect r = new Rect();
            Point offset = new Point();
            getGlobalVisibleRect(r, offset);
            center.offset(-offset.x, -offset.y);
            return center;
        }
        View parent = (View) getParent();
        return new Point(parent.getRight() - (parent.getHeight() / 2), (parent.getTop() + parent.getBottom()) / 2);
    }

    private void updateOverlayVisibility(final boolean show) {
        float toAlpha = 1.0f;
        if (this.mOverlay != null) {
            float fromAlpha;
            if (show) {
                this.mOverlay.setVisibility(0);
            } else if (this.mOverlay.getVisibility() == 8) {
                return;
            }
            if (show) {
                fromAlpha = 0.0f;
            } else {
                fromAlpha = 1.0f;
            }
            if (!show) {
                toAlpha = 0.0f;
            }
            AlphaAnimation animation = new AlphaAnimation(fromAlpha, toAlpha);
            animation.setDuration(300);
            animation.setAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (!show) {
                        PlaySearch.this.mOverlay.setVisibility(8);
                    }
                }
            });
            this.mOverlay.startAnimation(animation);
        }
    }
}
