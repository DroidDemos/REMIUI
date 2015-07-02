package com.google.android.wallet.instrumentmanager.ui.common;

import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.FormFieldMessage;

public interface Form extends Validatable {
    boolean applyFormFieldMessage(FormFieldMessage formFieldMessage);

    void enableUi(boolean z);

    boolean focusOnFirstInvalidFormField();

    boolean handleErrorMessageDismissed(String str, int i);

    boolean isReadyToSubmit();
}
