package com.google.android.play.search;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.google.android.play.R;

public class PlaySearchNavigationButton extends ImageView implements PlaySearchListener {
    private static final boolean IS_HONEYCOMB_OR_GREATER;
    private ArrowOrBurgerDrawable mArrowOrBurgerDrawable;
    private PlaySearchController mController;
    private int mCurrentMode;

    static {
        IS_HONEYCOMB_OR_GREATER = VERSION.SDK_INT >= 11;
    }

    public PlaySearchNavigationButton(Context context) {
        this(context, null);
    }

    public PlaySearchNavigationButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaySearchNavigationButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mArrowOrBurgerDrawable = new ArrowOrBurgerDrawable(context.getResources().getColor(R.color.play_search_plate_navigation_button_color), false);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        setImageDrawable(this.mArrowOrBurgerDrawable);
        this.mArrowOrBurgerDrawable.setAsBurger(true);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlaySearchNavigationButton.this.mController != null) {
                    PlaySearchNavigationButton.this.mController.onNavigationClicked(view);
                    if (PlaySearchNavigationButton.this.mCurrentMode == 2) {
                        PlaySearchNavigationButton.this.mController.setMode(1);
                    }
                }
            }
        });
        setMode(1);
    }

    public void setPlaySearchController(PlaySearchController playSearchController) {
        if (this.mController != null) {
            this.mController.removePlaySearchListener(this);
        }
        this.mController = playSearchController;
        this.mController.addPlaySearchListener(this);
    }

    public void onModeChanged(int searchMode) {
        if (searchMode == 1) {
            setMode(1);
        } else if (searchMode == 2) {
            setMode(2);
        }
    }

    public void onQueryChanged(String query, boolean canUpdateSuggestions) {
    }

    public void onSuggestionClicked(PlaySearchSuggestionModel model) {
    }

    public void onSearch(String query) {
    }

    private void setMode(int mode) {
        boolean showBurger = true;
        if (this.mCurrentMode != mode) {
            int descriptionResId;
            if (mode != 1) {
                showBurger = false;
            }
            if (IS_HONEYCOMB_OR_GREATER) {
                changeModeAnimated(showBurger);
            } else {
                this.mArrowOrBurgerDrawable.setAsBurger(showBurger);
            }
            if (showBurger) {
                descriptionResId = R.string.play_accessibility_search_plate_menu_button;
            } else {
                descriptionResId = R.string.play_accessibility_search_plate_back_button;
            }
            setContentDescription(getContext().getText(descriptionResId));
            this.mCurrentMode = mode;
        }
    }

    private void changeModeAnimated(boolean showBurger) {
        float finalValue;
        if (showBurger) {
            finalValue = 0.0f;
        } else {
            finalValue = 1.0f;
        }
        ValueAnimator arrowOrBurgerAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        arrowOrBurgerAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                PlaySearchNavigationButton.this.mArrowOrBurgerDrawable.setHowArrowIsTheBurger(((Float) animation.getAnimatedValue()).floatValue());
            }
        });
        arrowOrBurgerAnimator.setDuration(350);
        arrowOrBurgerAnimator.setInterpolator(BakedBezierInterpolator.INSTANCE);
        arrowOrBurgerAnimator.setFloatValues(new float[]{1.0f - finalValue, finalValue});
        arrowOrBurgerAnimator.start();
    }
}
