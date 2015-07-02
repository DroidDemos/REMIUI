package com.google.android.wallet.instrumentmanager.ui.address;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class AnimatedDynamicAddressFieldsLayout extends DynamicAddressFieldsLayout implements AnimatorListener, AnimatorUpdateListener {
    private ValueAnimator mActiveAnimator;
    private ValueAnimator mAnimator1;
    private ValueAnimator mAnimator2;
    private boolean mExpandCalledWhileContracting;
    private ArrayList<View> mNewFields;
    private final ArrayList<View> mPendingReplaceNewViews;
    private final ArrayList<View> mPendingReplaceOldViews;
    private int mState;

    public AnimatedDynamicAddressFieldsLayout(Context context) {
        this(context, null);
    }

    public AnimatedDynamicAddressFieldsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatedDynamicAddressFieldsLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mState = 1;
        this.mPendingReplaceOldViews = new ArrayList();
        this.mPendingReplaceNewViews = new ArrayList();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAnimator1 = ValueAnimator.ofFloat(new float[]{0.0f});
        this.mAnimator1.addUpdateListener(this);
        this.mAnimator1.addListener(this);
        this.mAnimator2 = ValueAnimator.ofFloat(new float[]{0.0f});
        this.mAnimator2.addUpdateListener(this);
        this.mAnimator2.addListener(this);
        this.mActiveAnimator = this.mAnimator1;
    }

    public void setFields(ArrayList<View> fields) {
        switch (this.mState) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                addViews(fields);
                this.mFieldContainer.setVisibility(8);
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                addViews(fields);
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
                this.mNewFields = fields;
                if (!this.mPendingReplaceOldViews.isEmpty()) {
                    this.mPendingReplaceOldViews.clear();
                    this.mPendingReplaceNewViews.clear();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void switchToShowingFields() {
        switch (this.mState) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mFieldContainer.setVisibility(0);
                this.mState = 4;
                this.mActiveAnimator.setFloatValues(new float[]{0.0f, 1.0f});
                this.mActiveAnimator.start();
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mExpandCalledWhileContracting = true;
                return;
            default:
                return;
        }
    }

    public void switchToShowingProgressBar() {
        switch (this.mState) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mState = 3;
                this.mProgressBar.setVisibility(0);
                this.mActiveAnimator.setFloatValues(new float[]{1.0f, 0.0f});
                this.mActiveAnimator.setStartDelay(200);
                this.mActiveAnimator.setCurrentPlayTime(0);
                this.mActiveAnimator.start();
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
                this.mState = 3;
                this.mActiveAnimator.reverse();
                return;
            default:
                return;
        }
    }

    private void switchAnimator() {
        if (this.mActiveAnimator == this.mAnimator1) {
            this.mActiveAnimator = this.mAnimator2;
        } else {
            this.mActiveAnimator = this.mAnimator1;
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        invokeOnHeightChanged((1.0f - ((Float) this.mActiveAnimator.getAnimatedValue()).floatValue()) * ((float) (getViewHeightAtTransitionStart() - h)));
    }

    private int getViewHeightAtTransitionStart() {
        return this.mProgressBar.getMeasuredHeight();
    }

    public void onAnimationUpdate(ValueAnimator animation) {
        float t = ((Float) animation.getAnimatedValue()).floatValue();
        int childCount = this.mFieldContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.mFieldContainer.getChildAt(i);
            child.setAlpha(t);
            child.setY(((float) child.getTop()) * t);
        }
        this.mProgressBar.setAlpha(1.0f - t);
        invokeOnHeightChanged((1.0f - t) * ((float) (getViewHeightAtTransitionStart() - getMeasuredHeight())));
    }

    public void onAnimationStart(Animator animation) {
        setFieldsLayerType(2);
    }

    public void onAnimationEnd(Animator animation) {
        setFieldsLayerType(0);
        this.mActiveAnimator.setStartDelay(0);
        if (this.mState == 4) {
            this.mState = 2;
            this.mProgressBar.setVisibility(4);
        } else if (this.mState == 3) {
            this.mState = 1;
            this.mFieldContainer.setVisibility(8);
        }
        if (this.mNewFields != null) {
            setFields(this.mNewFields);
            this.mNewFields = null;
        }
        int size = this.mPendingReplaceOldViews.size();
        for (int i = 0; i < size; i++) {
            replaceView((View) this.mPendingReplaceOldViews.get(i), (View) this.mPendingReplaceNewViews.get(i));
        }
        this.mPendingReplaceOldViews.clear();
        this.mPendingReplaceNewViews.clear();
        if (this.mExpandCalledWhileContracting && this.mState == 1) {
            this.mExpandCalledWhileContracting = false;
            switchAnimator();
            switchToShowingFields();
        }
    }

    public void onAnimationCancel(Animator animation) {
    }

    public void onAnimationRepeat(Animator animation) {
    }

    private void invokeOnHeightChanged(float heightOffset) {
        if (this.mOnHeightOffsetChangedListener != null) {
            this.mOnHeightOffsetChangedListener.onHeightOffsetChanged(heightOffset);
        }
    }

    private void setFieldsLayerType(int layerType) {
        int childCount = this.mFieldContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            this.mFieldContainer.getChildAt(i).setLayerType(layerType, null);
        }
    }

    public void replaceView(View oldView, View newView) {
        if (this.mState == 4 || this.mState == 3) {
            this.mPendingReplaceOldViews.add(oldView);
            this.mPendingReplaceNewViews.add(newView);
            return;
        }
        super.replaceView(oldView, newView);
    }
}
