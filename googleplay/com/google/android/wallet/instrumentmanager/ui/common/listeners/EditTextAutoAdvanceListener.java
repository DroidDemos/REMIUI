package com.google.android.wallet.instrumentmanager.ui.common.listeners;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.google.android.wallet.instrumentmanager.common.util.AndroidUtils;
import com.google.android.wallet.instrumentmanager.ui.common.AutoAdvancedListener;
import com.google.android.wallet.instrumentmanager.ui.common.Completable;
import com.google.android.wallet.instrumentmanager.ui.common.Validatable;

public class EditTextAutoAdvanceListener implements TextWatcher {
    private AutoAdvancedListener mAutoAdvancedListener;
    private final Handler mAutoFocusHandler;
    private final boolean mAutoRetreat;
    private final Completable mCompletable;
    private final EditText mEditText;
    private final Validatable mValidatable;

    public EditTextAutoAdvanceListener(EditText editText, Completable completable, Validatable validatable, boolean autoRetreat) {
        this.mEditText = editText;
        this.mCompletable = completable;
        this.mValidatable = validatable;
        this.mAutoRetreat = autoRetreat;
        this.mAutoFocusHandler = new Handler();
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!this.mEditText.isFocused()) {
            return;
        }
        if (count <= 0 || !this.mCompletable.isComplete()) {
            if (this.mAutoRetreat && TextUtils.isEmpty(s)) {
                View previousView = this.mEditText.focusSearch(33);
                if (previousView != null) {
                    previousView.requestFocus();
                }
            }
        } else if (this.mValidatable != null && this.mValidatable.validate()) {
            if (this.mAutoAdvancedListener != null) {
                this.mAutoAdvancedListener.onAutoAdvanced();
            }
            final View nextView = this.mEditText.focusSearch(130);
            if (nextView == null) {
                return;
            }
            if (AndroidUtils.isAccessibilityEnabled(this.mEditText.getContext())) {
                this.mAutoFocusHandler.postDelayed(new Runnable() {
                    public void run() {
                        nextView.requestFocus();
                    }
                }, 750);
            } else {
                nextView.requestFocus();
            }
        }
    }

    public void afterTextChanged(Editable s) {
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void setAutoAdvancedListener(AutoAdvancedListener listener) {
        this.mAutoAdvancedListener = listener;
    }

    public AutoAdvancedListener getAutoAdvancedListener() {
        return this.mAutoAdvancedListener;
    }
}
