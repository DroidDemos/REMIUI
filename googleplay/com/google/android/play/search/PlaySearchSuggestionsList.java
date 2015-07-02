package com.google.android.play.search;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import com.google.android.play.R;
import com.google.android.play.image.BitmapLoader;
import java.util.Collections;
import java.util.List;

public class PlaySearchSuggestionsList extends LinearLayout implements PlaySearchListener {
    private PlaySearchSuggestionAdapter mAdapter;
    private AdapterDataObserver mAdapterDataObserver;
    private PlaySearchController mController;
    private final float mDensity;
    private int mMaxUsableScreenHeight;
    private final int mOneSuggestionHeight;
    private RecyclerView mRecyclerView;
    private Animation mRecyclerViewAnimation;
    private int mScreenHeight;
    private final int mSuggestionsListBottomMargin;

    public PlaySearchSuggestionsList(Context context) {
        this(context, null);
    }

    public PlaySearchSuggestionsList(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources resources = context.getResources();
        this.mOneSuggestionHeight = resources.getDimensionPixelSize(R.dimen.play_search_one_suggestion_height);
        this.mSuggestionsListBottomMargin = resources.getDimensionPixelOffset(R.dimen.play_search_suggestions_list_bottom_margin);
        this.mDensity = getResources().getDisplayMetrics().density;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mRecyclerView = (RecyclerView) findViewById(R.id.suggestion_list_recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mAdapter = new PlaySearchSuggestionAdapter();
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapterDataObserver = new AdapterDataObserver() {
            public void onChanged() {
                PlaySearchSuggestionsList.this.updateVisibility();
            }
        };
        this.mAdapter.registerAdapterDataObserver(this.mAdapterDataObserver);
        final InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService("input_method");
        this.mRecyclerView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return false;
            }
        });
        this.mScreenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public void setBitmapLoader(BitmapLoader bitmapLoader) {
        this.mAdapter.setBitmapLoader(bitmapLoader);
    }

    public void setPlaySearchController(PlaySearchController playSearchController) {
        if (this.mController != null) {
            this.mController.removePlaySearchListener(this);
        }
        this.mController = playSearchController;
        this.mController.addPlaySearchListener(this);
        this.mAdapter.setPlaySearchController(playSearchController);
    }

    public void setSuggestions(List<PlaySearchSuggestionModel> suggestionModels) {
        this.mAdapter.updateData(suggestionModels);
    }

    private void updateVisibility() {
        if (this.mController != null) {
            int mode = this.mController.getMode();
            if (this.mAdapter.getItemCount() < 1 || mode == 1) {
                animateView(false);
            } else {
                animateView(true);
            }
        }
    }

    public void onModeChanged(int searchMode) {
        if (searchMode == 1) {
            clearAdapterData();
        } else {
            updateVisibility();
        }
    }

    public void onQueryChanged(String query, boolean canUpdateSuggestions) {
    }

    public void onSearch(String query) {
        clearAdapterData();
    }

    public void onSuggestionClicked(PlaySearchSuggestionModel model) {
        clearAdapterData();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mRecyclerViewAnimation != null) {
            this.mRecyclerViewAnimation.cancel();
            setVisibility(8);
        }
    }

    private void clearAdapterData() {
        this.mAdapter.updateData(Collections.emptyList());
    }

    private void animateView(final boolean show) {
        if (this.mRecyclerViewAnimation != null) {
            this.mRecyclerViewAnimation.cancel();
        }
        if (show) {
            setVisibility(0);
            if (this.mMaxUsableScreenHeight == 0) {
                return;
            }
        }
        final int currentHeight = this.mRecyclerView.getHeight();
        int targetHeight = 0;
        if (show) {
            targetHeight = Math.min(this.mMaxUsableScreenHeight, this.mAdapter.getItemCount() * this.mOneSuggestionHeight);
        }
        if (currentHeight != targetHeight) {
            final int distanceToCover = targetHeight - currentHeight;
            Animation animation = new Animation() {
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    PlaySearchSuggestionsList.this.mRecyclerView.getLayoutParams().height = currentHeight + ((int) (((float) distanceToCover) * interpolatedTime));
                    PlaySearchSuggestionsList.this.mRecyclerView.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            };
            animation.setAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (!show) {
                        PlaySearchSuggestionsList.this.setVisibility(8);
                    }
                    PlaySearchSuggestionsList.this.mRecyclerViewAnimation = null;
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            animation.setDuration((long) Math.max(50, Math.min(200, (int) (((float) distanceToCover) / this.mDensity))));
            this.mRecyclerViewAnimation = animation;
            this.mRecyclerView.startAnimation(animation);
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.mMaxUsableScreenHeight == 0) {
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            if (rect.top > 0) {
                this.mMaxUsableScreenHeight = ((this.mScreenHeight - rect.top) - this.mRecyclerView.getTop()) + this.mSuggestionsListBottomMargin;
            }
            updateVisibility();
        }
    }
}
