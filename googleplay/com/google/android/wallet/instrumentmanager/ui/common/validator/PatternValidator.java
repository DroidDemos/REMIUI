package com.google.android.wallet.instrumentmanager.ui.common.validator;

import android.text.TextUtils;
import android.widget.TextView;
import java.util.regex.Pattern;

public class PatternValidator extends AbstractValidator {
    private final Pattern mPattern;

    public PatternValidator(CharSequence customErrorMessage, Pattern pattern) {
        super(customErrorMessage);
        if (pattern == null) {
            throw new IllegalArgumentException("pattern must not be null");
        }
        this.mPattern = pattern;
    }

    public boolean isValid(TextView tv) {
        return TextUtils.isEmpty(tv.getText()) || this.mPattern.matcher(tv.getText()).matches();
    }
}
