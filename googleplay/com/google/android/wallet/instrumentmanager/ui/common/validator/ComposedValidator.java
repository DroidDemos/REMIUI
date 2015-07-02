package com.google.android.wallet.instrumentmanager.ui.common.validator;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ComposedValidator extends AbstractValidator {
    protected final ArrayList<AbstractValidator> mValidators;

    public ComposedValidator(CharSequence customErrorMessage, AbstractValidator... validators) {
        super(customErrorMessage);
        this.mValidators = new ArrayList(Arrays.asList(validators));
    }

    public void add(AbstractValidator validator) {
        if (validator != null) {
            this.mValidators.add(validator);
        }
    }

    public void remove(AbstractValidator validator) {
        if (validator != null) {
            this.mValidators.remove(validator);
        }
    }

    public boolean isEmpty() {
        return this.mValidators.isEmpty();
    }
}
