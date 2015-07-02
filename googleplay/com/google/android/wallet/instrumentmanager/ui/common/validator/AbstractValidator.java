package com.google.android.wallet.instrumentmanager.ui.common.validator;

import android.widget.TextView;

public abstract class AbstractValidator {
    protected CharSequence mErrorMessage;

    public abstract boolean isValid(TextView textView);

    protected AbstractValidator(CharSequence customErrorMessage) {
        this.mErrorMessage = customErrorMessage;
    }

    protected AbstractValidator() {
        this(null);
    }

    public CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }
}
