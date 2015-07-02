package com.google.android.finsky.layout;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class AddressFieldsLayoutHoneycomb extends AddressFieldsLayout implements AnimatorListener, AnimatorUpdateListener, OnFocusChangeListener {
    private static String KEY_PARENT_STATE;
    private static String KEY_SUPPORT_SHOWING_ONE_FIELD;
    private ValueAnimator mAnimator;
    private ValueAnimator mAnimator1;
    private ValueAnimator mAnimator2;
    private boolean mExpandCalledWhileContracting;
    private CharSequence mFirstFieldHint;
    private List<View> mNewFields;
    private OnHeightOffsetChangedListener mOnHeightChangedListener;
    private State mState;
    private boolean mSupportShowingOneField;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State;

        static {
            $SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State = new int[State.values().length];
            try {
                $SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[State.SHOWING_ALL_FIELDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[State.SHOWING_ONE_FIELD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[State.TRANSITION_TO_PROGRESS_BAR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[State.TRANSITION_TO_ALL_FIELDS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[State.TRANSITION_TO_ONE_FIELD.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[State.TRANSITION_ONE_FIELD_ALL_FIELDS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[State.SHOWING_PROGRESS_BAR.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    private enum State {
        SHOWING_PROGRESS_BAR,
        SHOWING_ALL_FIELDS,
        SHOWING_ONE_FIELD,
        TRANSITION_TO_PROGRESS_BAR,
        TRANSITION_TO_ALL_FIELDS,
        TRANSITION_TO_ONE_FIELD,
        TRANSITION_ONE_FIELD_ALL_FIELDS
    }

    static {
        KEY_SUPPORT_SHOWING_ONE_FIELD = "support_showing_one_field";
        KEY_PARENT_STATE = "parent_instance_state";
    }

    public AddressFieldsLayoutHoneycomb(Context context) {
        this(context, null);
    }

    public AddressFieldsLayoutHoneycomb(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddressFieldsLayoutHoneycomb(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mState = State.SHOWING_PROGRESS_BAR;
        this.mSupportShowingOneField = true;
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PARENT_STATE, super.onSaveInstanceState());
        bundle.putBoolean(KEY_SUPPORT_SHOWING_ONE_FIELD, this.mSupportShowingOneField);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mSupportShowingOneField = bundle.getBoolean(KEY_SUPPORT_SHOWING_ONE_FIELD);
            super.onRestoreInstanceState(bundle.getParcelable(KEY_PARENT_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAnimator1 = ValueAnimator.ofFloat(new float[]{0.0f});
        this.mAnimator1.addUpdateListener(this);
        this.mAnimator1.addListener(this);
        this.mAnimator2 = ValueAnimator.ofFloat(new float[]{0.0f});
        this.mAnimator2.addUpdateListener(this);
        this.mAnimator2.addListener(this);
        this.mAnimator = this.mAnimator1;
    }

    public void showUpperRightProgressBar() {
        this.mUpperRightProgressBar.setVisibility(0);
        this.mUpperRightProgressBar.setAlpha(0.0f);
        this.mUpperRightProgressBar.animate().alpha(1.0f);
    }

    public void hideUpperRightProgressBar() {
        this.mUpperRightProgressBar.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                AddressFieldsLayoutHoneycomb.this.mUpperRightProgressBar.setVisibility(8);
                AddressFieldsLayoutHoneycomb.this.mUpperRightProgressBar.animate().setListener(null);
            }
        });
    }

    public void setFields(List<View> fields) {
        boolean oneFieldModePossible;
        if (this.mSupportShowingOneField && fields.size() > 1 && (fields.get(0) instanceof EditText)) {
            oneFieldModePossible = true;
        } else {
            oneFieldModePossible = false;
        }
        switch (AnonymousClass2.$SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[this.mState.ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                addViews(fields);
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                addViews(fields);
                makeOnlyFirstFieldVisible();
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                this.mNewFields = fields;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                addViews(fields);
                setChildrenViewVisibility(8);
                break;
            default:
                FinskyLog.wtf("enum %s", this.mState);
                break;
        }
        if (oneFieldModePossible) {
            EditText firstField = (EditText) fields.get(0);
            this.mFirstFieldHint = firstField.getHint();
            firstField.setHint(com.android.vending.R.string.address);
            firstField.setOnFocusChangeListener(this);
        } else if (this.mSupportShowingOneField) {
            this.mSupportShowingOneField = false;
        }
    }

    public void showFields() {
        switch (AnonymousClass2.$SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[this.mState.ordinal()]) {
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mExpandCalledWhileContracting = true;
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                if (this.mSupportShowingOneField) {
                    makeOnlyFirstFieldVisible();
                    this.mState = State.TRANSITION_TO_ONE_FIELD;
                } else {
                    setChildrenViewVisibility(0);
                    this.mState = State.TRANSITION_TO_ALL_FIELDS;
                }
                this.mAnimator.setFloatValues(new float[]{0.0f, 1.0f});
                this.mAnimator.start();
                return;
            default:
                return;
        }
    }

    public void showProgressBar() {
        switch (AnonymousClass2.$SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[this.mState.ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mState = State.TRANSITION_TO_PROGRESS_BAR;
                this.mProgressBar.setVisibility(0);
                this.mAnimator.setFloatValues(new float[]{1.0f, 0.0f});
                this.mAnimator.setStartDelay(200);
                this.mAnimator.setCurrentPlayTime(0);
                this.mAnimator.start();
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                this.mState = State.TRANSITION_TO_PROGRESS_BAR;
                this.mAnimator.reverse();
                return;
            default:
                return;
        }
    }

    public void disableOneFieldMode() {
        this.mSupportShowingOneField = false;
        switch (AnonymousClass2.$SwitchMap$com$google$android$finsky$layout$AddressFieldsLayoutHoneycomb$State[this.mState.ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                setChildrenViewVisibility(0);
                return;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                this.mAnimator.end();
                this.mState = State.SHOWING_ALL_FIELDS;
                setChildrenViewVisibility(0);
                return;
            default:
                return;
        }
    }

    public void setOnHeightOffsetChangedListener(OnHeightOffsetChangedListener onHeightChangedListener) {
        this.mOnHeightChangedListener = onHeightChangedListener;
    }

    private void invokeOnHeightChanged(float heightOffset) {
        if (this.mOnHeightChangedListener != null) {
            this.mOnHeightChangedListener.onHeightOffsetChanged(heightOffset);
        }
    }

    private void makeOnlyFirstFieldVisible() {
        setChildrenViewVisibility(8);
        if (this.mFieldContainer.getChildCount() > 0) {
            this.mFieldContainer.getChildAt(0).setVisibility(0);
        }
    }

    private void setChildrenViewVisibility(int visibility) {
        int childCount = this.mFieldContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            this.mFieldContainer.getChildAt(i).setVisibility(visibility);
        }
    }

    private void addViews(List<View> fields) {
        this.mFieldContainer.removeAllViews();
        for (View field : fields) {
            field.setLayerType(2, null);
            this.mFieldContainer.addView(field, new LayoutParams(-1, -2));
        }
    }

    private void switchAnimator() {
        if (this.mAnimator == this.mAnimator1) {
            this.mAnimator = this.mAnimator2;
        } else {
            this.mAnimator = this.mAnimator1;
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        invokeOnHeightChanged((1.0f - ((Float) this.mAnimator.getAnimatedValue()).floatValue()) * ((float) (getViewHeightAtTransitionStart() - h)));
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        if (this.mState == State.SHOWING_ONE_FIELD || this.mState == State.TRANSITION_TO_ONE_FIELD) {
            v.setOnFocusChangeListener(null);
            this.mSupportShowingOneField = false;
            this.mState = State.TRANSITION_ONE_FIELD_ALL_FIELDS;
            setChildrenViewVisibility(0);
            this.mAnimator.setFloatValues(new float[]{0.0f, 1.0f});
            this.mAnimator.setStartDelay(200);
            this.mAnimator.setCurrentPlayTime(0);
            this.mAnimator.start();
            ((EditText) v).setHint(this.mFirstFieldHint);
            onAnimationUpdate(this.mAnimator);
        }
    }

    private int getViewHeightAtTransitionStart() {
        if (this.mState != State.TRANSITION_ONE_FIELD_ALL_FIELDS || this.mFieldContainer.getChildCount() <= 0) {
            return this.mProgressBar.getMeasuredHeight();
        }
        return this.mFieldContainer.getChildAt(0).getHeight();
    }

    public void onAnimationUpdate(ValueAnimator animation) {
        float t = ((Float) animation.getAnimatedValue()).floatValue();
        int childCount = this.mFieldContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.mFieldContainer.getChildAt(i);
            if (i != 0 || this.mState != State.TRANSITION_ONE_FIELD_ALL_FIELDS) {
                child.setAlpha(t);
            }
            child.setY(((float) child.getTop()) * t);
        }
        if (this.mState != State.TRANSITION_ONE_FIELD_ALL_FIELDS || this.mFieldContainer.getChildCount() <= 0) {
            this.mProgressBar.setAlpha(1.0f - t);
        } else {
            this.mProgressBar.setAlpha(0.0f);
        }
        invokeOnHeightChanged((1.0f - t) * ((float) (getViewHeightAtTransitionStart() - getMeasuredHeight())));
    }

    public void onAnimationStart(Animator animation) {
    }

    public void onAnimationEnd(Animator animation) {
        this.mAnimator.setStartDelay(0);
        if (this.mState == State.TRANSITION_TO_ALL_FIELDS) {
            this.mState = State.SHOWING_ALL_FIELDS;
            this.mProgressBar.setVisibility(4);
        } else if (this.mState == State.TRANSITION_TO_ONE_FIELD) {
            this.mState = State.SHOWING_ONE_FIELD;
            this.mProgressBar.setVisibility(4);
        } else if (this.mState == State.TRANSITION_TO_PROGRESS_BAR) {
            this.mState = State.SHOWING_PROGRESS_BAR;
            setChildrenViewVisibility(8);
        } else if (this.mState == State.TRANSITION_ONE_FIELD_ALL_FIELDS) {
            this.mState = State.SHOWING_ALL_FIELDS;
        }
        if (this.mNewFields != null) {
            setFields(this.mNewFields);
            this.mNewFields = null;
        }
        if (this.mExpandCalledWhileContracting && this.mState == State.SHOWING_PROGRESS_BAR && this.mExpandCalledWhileContracting) {
            this.mExpandCalledWhileContracting = false;
            switchAnimator();
            showFields();
        }
    }

    public void onAnimationCancel(Animator animation) {
    }

    public void onAnimationRepeat(Animator animation) {
    }
}
