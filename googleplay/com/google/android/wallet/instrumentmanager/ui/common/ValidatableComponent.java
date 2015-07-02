package com.google.android.wallet.instrumentmanager.ui.common;

import com.google.android.wallet.instrumentmanager.ui.common.validator.AbstractValidator;

public interface ValidatableComponent extends Validatable {
    void addValidator(AbstractValidator abstractValidator);

    void removeValidator(AbstractValidator abstractValidator);
}
