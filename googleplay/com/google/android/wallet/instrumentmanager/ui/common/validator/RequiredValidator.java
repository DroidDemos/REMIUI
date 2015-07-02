package com.google.android.wallet.instrumentmanager.ui.common.validator;

import android.text.TextUtils;
import android.widget.TextView;

public class RequiredValidator extends AbstractValidator {
    public RequiredValidator(CharSequence customErrorMessage) {
        super(customErrorMessage);
    }

    public boolean isValid(TextView tv) {
        CharSequence text = tv.getText();
        return text != null && TextUtils.getTrimmedLength(text) > 0;
    }
}
