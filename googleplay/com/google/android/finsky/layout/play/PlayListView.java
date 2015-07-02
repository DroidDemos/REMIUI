package com.google.android.finsky.layout.play;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.UiUtils;
import java.util.Map;

public class PlayListView extends ListView {
    public static final boolean ENABLE_ANIMATION;
    private static final boolean ENABLE_LAYER_CALLS;
    private Interpolator mAlphaInterpolator;
    private boolean mAnimateChanges;
    private DataSetObserver mAnimateObserver;
    private Map<Object, RectF> mCapturedPositions;
    private int[] mTempLocation;
    private RectF mTempRect;
    private Interpolator mTranslationInterpolator;

    static {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 14) {
            z = true;
        } else {
            z = false;
        }
        ENABLE_ANIMATION = z;
        if (VERSION.SDK_INT < 16) {
            z2 = false;
        }
        ENABLE_LAYER_CALLS = z2;
    }

    public PlayListView(Context context) {
        this(context, null);
    }

    public PlayListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mAnimateChanges = true;
        if (ENABLE_ANIMATION) {
            this.mAnimateObserver = new DataSetObserver() {
                public void onChanged() {
                    PlayListView.this.captureCardPositions();
                }

                public void onInvalidated() {
                    PlayListView.this.captureCardPositions();
                }
            };
            this.mTranslationInterpolator = new AccelerateDecelerateInterpolator();
            this.mAlphaInterpolator = new LinearInterpolator();
            this.mCapturedPositions = Maps.newHashMap();
            this.mTempLocation = new int[2];
            this.mTempRect = new RectF();
        }
    }

    public void setAnimateChanges(boolean animateChanges) {
        if (ENABLE_ANIMATION && this.mAnimateChanges != animateChanges) {
            this.mAnimateChanges = animateChanges;
            ListAdapter adapter = getAdapter();
            if (adapter == null) {
                return;
            }
            if (animateChanges) {
                adapter.registerDataSetObserver(this.mAnimateObserver);
            } else {
                adapter.unregisterDataSetObserver(this.mAnimateObserver);
            }
        }
    }

    public void setAdapter(ListAdapter adapter) {
        ListAdapter oldAdapter = getAdapter();
        if (ENABLE_ANIMATION && this.mAnimateChanges && oldAdapter != null) {
            oldAdapter.unregisterDataSetObserver(this.mAnimateObserver);
        }
        super.setAdapter(adapter);
        if (ENABLE_ANIMATION && this.mAnimateChanges && adapter != null) {
            adapter.registerDataSetObserver(this.mAnimateObserver);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        animateIfNeeded();
    }

    private void captureCardPositions() {
        if (ENABLE_ANIMATION) {
            this.mCapturedPositions.clear();
            if (UiUtils.isVisibleOnScreen(this) && getChildCount() != 0) {
                capture(this);
            }
        }
    }

    private void capture(View view) {
        traverse(view, true);
    }

    private void traverse(View view, boolean capture) {
        if (view instanceof Identifiable) {
            String id = ((Identifiable) view).getIdentifier();
            if (capture) {
                view.getLocationInWindow(this.mTempLocation);
                this.mCapturedPositions.put(id, new RectF((float) this.mTempLocation[0], (float) this.mTempLocation[1], (float) (this.mTempLocation[0] + view.getWidth()), (float) (this.mTempLocation[1] + view.getHeight())));
                view.animate().cancel();
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                return;
            }
            RectF originalPosition = (RectF) this.mCapturedPositions.get(id);
            if (originalPosition != null) {
                view.getLocationInWindow(this.mTempLocation);
                this.mTempRect.set((float) this.mTempLocation[0], (float) this.mTempLocation[1], (float) (this.mTempLocation[0] + view.getWidth()), (float) (this.mTempLocation[1] + view.getHeight()));
                float tx = originalPosition.centerX() - this.mTempRect.centerX();
                float ty = originalPosition.centerY() - this.mTempRect.centerY();
                if (Math.abs(tx) > 5.0f || Math.abs(ty) > 5.0f) {
                    view.setTranslationX(tx);
                    view.setTranslationY(ty);
                    ViewPropertyAnimator animator = view.animate().translationX(0.0f).translationY(0.0f).setDuration(150).setInterpolator(this.mTranslationInterpolator).setStartDelay(0);
                    if (ENABLE_LAYER_CALLS) {
                        animator.withLayer();
                    }
                    ViewParent parent = view.getParent();
                    if (parent instanceof ViewGroup) {
                        disableClipChildren((ViewGroup) parent);
                        return;
                    }
                    return;
                }
                return;
            }
            view.setAlpha(0.0f);
            view.animate().alpha(1.0f).setStartDelay(150).setInterpolator(this.mAlphaInterpolator).setDuration(150);
            if (ENABLE_LAYER_CALLS) {
                view.animate().withLayer();
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                traverse(viewGroup.getChildAt(i), capture);
            }
        }
    }

    private void disableClipChildren(ViewGroup view) {
        ViewGroup viewGroup = view;
        viewGroup.setClipChildren(false);
        ViewParent parent = viewGroup.getParent();
        if ((parent instanceof ViewGroup) && view != this) {
            disableClipChildren((ViewGroup) parent);
        }
    }

    private void animateIfNeeded() {
        if (ENABLE_ANIMATION && this.mAnimateChanges && !this.mCapturedPositions.isEmpty()) {
            traverse(this, false);
            this.mCapturedPositions.clear();
        }
    }
}
