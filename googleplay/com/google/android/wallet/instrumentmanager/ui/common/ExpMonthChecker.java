package com.google.android.wallet.instrumentmanager.ui.common;

import android.view.View;
import android.view.View.OnFocusChangeListener;

public class ExpMonthChecker extends InputLengthCompletable implements OnFocusChangeListener, Validatable {
    private final Validatable mExpDateChecker;
    private final FormEditText mExpYearText;

    public ExpMonthChecker(FormEditText expMonthText, FormEditText expYearText, Validatable expDateChecker) {
        super(expMonthText, 2);
        this.mExpYearText = expYearText;
        this.mExpDateChecker = expDateChecker;
    }

    public boolean isComplete() {
        if (this.mFormEditText.getText().length() != 1) {
            return super.isComplete();
        }
        char ch = this.mFormEditText.getText().charAt(0);
        if (ch == '0' || ch == '1') {
            return false;
        }
        return true;
    }

    public boolean isValid() {
        return this.mFormEditText.isValid() && (!this.mExpYearText.isValid() || this.mExpDateChecker.isValid());
    }

    public boolean validate() {
        if (!this.mFormEditText.validate()) {
            return false;
        }
        if (!this.mExpYearText.isValid()) {
            return true;
        }
        if (this.mExpYearText.getError() != null) {
            this.mExpYearText.setError(null);
        }
        return this.mExpDateChecker.validate();
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus && this.mFormEditText.getText().length() == 1) {
            this.mFormEditText.setText("0" + this.mFormEditText.getText());
            this.mFormEditText.setSelection(this.mFormEditText.length());
        }
    }
}
