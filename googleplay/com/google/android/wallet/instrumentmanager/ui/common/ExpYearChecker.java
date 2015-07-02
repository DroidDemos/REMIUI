package com.google.android.wallet.instrumentmanager.ui.common;

public class ExpYearChecker extends InputLengthCompletable implements Validatable {
    private final Validatable mExpDateChecker;

    public ExpYearChecker(FormEditText expYearText, Validatable expDateChecker) {
        super(expYearText, 2);
        this.mExpDateChecker = expDateChecker;
    }

    public boolean isValid() {
        return this.mFormEditText.isValid() && this.mExpDateChecker.isValid();
    }

    public boolean validate() {
        boolean valid = this.mFormEditText.validate() && this.mExpDateChecker.validate();
        if (valid && this.mFormEditText.getError() != null) {
            this.mFormEditText.setError(null);
        }
        return valid;
    }
}
