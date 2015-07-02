package com.google.android.wallet.instrumentmanager.ui.common.validator;

import android.widget.TextView;
import java.util.Iterator;

public class AndValidator extends ComposedValidator {
    public AndValidator(AbstractValidator... validators) {
        super(null, validators);
    }

    public boolean isValid(TextView tv) {
        Iterator i$ = this.mValidators.iterator();
        while (i$.hasNext()) {
            AbstractValidator v = (AbstractValidator) i$.next();
            if (!v.isValid(tv)) {
                this.mErrorMessage = v.getErrorMessage();
                return false;
            }
        }
        return true;
    }
}
