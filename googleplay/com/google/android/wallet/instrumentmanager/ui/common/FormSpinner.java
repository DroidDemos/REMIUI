package com.google.android.wallet.instrumentmanager.ui.common;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.wallet.instrumentmanager.R;

public class FormSpinner extends Spinner implements OnItemSelectedListener, Validatable {
    public static final Invalidator DEFAULT_INVALIDATOR;
    private Invalidator mInvalidator;
    private String mLabel;
    private OnItemSelectedListener mOnItemSelectedListener;
    private boolean mPotentialErrorOnConfigChange;
    private boolean mRequired;
    private boolean mViewLaidOutOnceAfterAdapterSet;

    public interface Invalidator {
        void clearError(FormSpinner formSpinner, View view);

        CharSequence getError(View view);

        void setError(FormSpinner formSpinner, View view, CharSequence charSequence);
    }

    static {
        DEFAULT_INVALIDATOR = new Invalidator() {
            public void setError(FormSpinner spinner, View selectedView, CharSequence error) {
                if (selectedView instanceof TextView) {
                    ((TextView) selectedView).setError(error);
                } else {
                    Log.w("FormSpinner", "Cannot set error on view: " + selectedView);
                }
            }

            public void clearError(FormSpinner spinner, View selectedView) {
                if (selectedView instanceof TextView) {
                    ((TextView) selectedView).setError(null);
                } else {
                    Log.w("FormSpinner", "Cannot clear error on view: " + selectedView);
                }
            }

            public CharSequence getError(View selectedView) {
                if (selectedView instanceof TextView) {
                    return ((TextView) selectedView).getError();
                }
                return null;
            }
        };
    }

    public FormSpinner(Context context) {
        super(context);
        this.mRequired = true;
        this.mInvalidator = DEFAULT_INVALIDATOR;
        this.mViewLaidOutOnceAfterAdapterSet = false;
        initializeListener();
    }

    public FormSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRequired = true;
        this.mInvalidator = DEFAULT_INVALIDATOR;
        this.mViewLaidOutOnceAfterAdapterSet = false;
        initializeListener();
    }

    public FormSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mRequired = true;
        this.mInvalidator = DEFAULT_INVALIDATOR;
        this.mViewLaidOutOnceAfterAdapterSet = false;
        initializeListener();
    }

    private void initializeListener() {
        super.setOnItemSelectedListener(this);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    public boolean isRequired() {
        return this.mRequired;
    }

    public void setRequired(boolean required) {
        this.mRequired = required;
    }

    public void setLabel(String label) {
        this.mLabel = label;
    }

    public Invalidator getInvalidator() {
        return this.mInvalidator;
    }

    public void setInvalidator(Invalidator invalidator) {
        if (invalidator == null) {
            invalidator = DEFAULT_INVALIDATOR;
        }
        this.mInvalidator = invalidator;
    }

    public boolean validate() {
        if (getAdapter() == null || getAdapter().isEmpty()) {
            throw new IllegalStateException("Must set non-empty adapter before validating");
        }
        this.mPotentialErrorOnConfigChange = true;
        View selectedView = getSelectedView();
        if (isValid()) {
            this.mInvalidator.clearError(this, selectedView);
            return true;
        }
        this.mInvalidator.setError(this, selectedView, getContext().getString(R.string.wallet_im_error_field_must_not_be_empty));
        return false;
    }

    public boolean isValid() {
        if (!isRequired() || getVisibility() != 0) {
            return true;
        }
        Adapter adapter = getAdapter();
        if (adapter == null) {
            return false;
        }
        int selectedItemPosition = getSelectedItemPosition();
        if (selectedItemPosition != -1) {
            return adapter instanceof ListAdapter ? ((ListAdapter) adapter).isEnabled(selectedItemPosition) : true;
        } else {
            return false;
        }
    }

    public Parcelable onSaveInstanceState() {
        Bundle outState = new Bundle();
        outState.putParcelable("superSavedInstanceState", super.onSaveInstanceState());
        outState.putBoolean("potentialErrorOnConfigChange", this.mPotentialErrorOnConfigChange);
        return outState;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle inState = (Bundle) state;
            super.onRestoreInstanceState(inState.getParcelable("superSavedInstanceState"));
            this.mPotentialErrorOnConfigChange = inState.getBoolean("potentialErrorOnConfigChange");
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!this.mViewLaidOutOnceAfterAdapterSet && getAdapter() != null) {
            this.mViewLaidOutOnceAfterAdapterSet = true;
            if (this.mPotentialErrorOnConfigChange) {
                validate();
            }
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (isValid()) {
            validate();
        }
        if (this.mOnItemSelectedListener != null) {
            this.mOnItemSelectedListener.onItemSelected(parent, view, position, id);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        if (isValid()) {
            validate();
        }
        if (this.mOnItemSelectedListener != null) {
            this.mOnItemSelectedListener.onNothingSelected(parent);
        }
    }

    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused && this.mInvalidator.getError(getSelectedView()) != null) {
            announceError();
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    public void announceError() {
        WalletUiUtils.announceForAccessibility(this, getErrorTextForAccessibility());
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.dispatchPopulateAccessibilityEvent(event);
        if (VERSION.SDK_INT < 16 && event.getEventType() == 8 && this.mInvalidator.getError(getSelectedView()) != null) {
            event.getText().add(getErrorTextForAccessibility());
        }
        return true;
    }

    String getErrorTextForAccessibility() {
        return getResources().getString(R.string.wallet_im_accessibility_event_form_field_error, new Object[]{this.mLabel, this.mInvalidator.getError(getSelectedView())});
    }
}
