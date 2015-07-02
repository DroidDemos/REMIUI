package com.google.android.wallet.instrumentmanager.ui.common;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.wallet.instrumentmanager.R;
import java.util.GregorianCalendar;

public class ExpDateChecker implements Validatable {
    private final Context mContext;
    private final FormEditText mExpMonthText;
    private final FormEditText mExpYearText;
    private final GregorianCalendar mMaxDate;
    private final GregorianCalendar mMinDate;

    public ExpDateChecker(Context context, FormEditText expMonthText, FormEditText expYearText, int minMonth, int minYear, int maxMonth, int maxYear) {
        this.mContext = context;
        this.mExpMonthText = expMonthText;
        this.mExpYearText = expYearText;
        this.mMinDate = new GregorianCalendar(minYear, minMonth - 1, 1);
        this.mMaxDate = new GregorianCalendar(maxYear, maxMonth - 1, 1);
    }

    private int checkExpDate(CharSequence monthText, CharSequence yearText) {
        try {
            int month = Integer.parseInt(monthText.toString());
            int year = Integer.parseInt(yearText.toString());
            if (year < 100) {
                year += 2000;
            }
            if (month < 1 || month > 12) {
                return 2;
            }
            GregorianCalendar inputDate = new GregorianCalendar(year, month - 1, 1);
            if (inputDate.compareTo(this.mMinDate) < 0) {
                return -1;
            }
            if (inputDate.compareTo(this.mMaxDate) <= 0) {
                return 0;
            }
            return 1;
        } catch (NumberFormatException e) {
            return 2;
        }
    }

    public boolean isValid() {
        return checkExpDate(this.mExpMonthText.getText(), this.mExpYearText.getText()) == 0;
    }

    public boolean validate() {
        if (!TextUtils.isEmpty(this.mExpMonthText.getError()) || !TextUtils.isEmpty(this.mExpYearText.getError())) {
            return false;
        }
        switch (checkExpDate(this.mExpMonthText.getText(), this.mExpYearText.getText())) {
            case -1:
                this.mExpYearText.setError(this.mContext.getString(R.string.wallet_im_error_expired_credit_card));
                return false;
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return true;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mExpYearText.setError(this.mContext.getString(R.string.wallet_im_error_year_invalid));
                return false;
            default:
                return false;
        }
    }
}
