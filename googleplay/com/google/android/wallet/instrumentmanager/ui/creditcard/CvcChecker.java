package com.google.android.wallet.instrumentmanager.ui.creditcard;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.ui.common.Completable;
import com.google.android.wallet.instrumentmanager.ui.common.FormEditText;
import com.google.android.wallet.instrumentmanager.ui.common.Validatable;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CardType;

public class CvcChecker implements Completable, Validatable {
    private final CreditCardNumberEditText mCardNumberText;
    private final Context mContext;
    private final int mCvcLength;
    private final FormEditText mCvcText;
    private final int mMaxFieldLength;

    public CvcChecker(Context context, FormEditText cvcText, CreditCardNumberEditText cardNumberText, int maxFieldLength) {
        this.mContext = context;
        this.mCvcText = cvcText;
        this.mCardNumberText = cardNumberText;
        this.mCvcLength = -1;
        this.mMaxFieldLength = maxFieldLength;
    }

    public CvcChecker(Context context, FormEditText cvcText, int cvcLength) {
        this.mContext = context;
        this.mCvcText = cvcText;
        this.mCardNumberText = null;
        this.mCvcLength = cvcLength;
        this.mMaxFieldLength = cvcLength;
    }

    private boolean isLengthCorrect() {
        if (TextUtils.isEmpty(this.mCvcText.getText())) {
            return false;
        }
        if (this.mCvcLength == -1) {
            CardType cardType = this.mCardNumberText.getCardType();
            if (cardType == null || this.mCvcText.getText().length() != cardType.cvcLength) {
                return false;
            }
            return true;
        } else if (this.mCvcText.getText().length() != this.mCvcLength) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isValid() {
        return this.mCvcText.isValid() && isLengthCorrect();
    }

    public boolean validate() {
        if (!this.mCvcText.validate()) {
            return false;
        }
        if (isLengthCorrect()) {
            if (this.mCvcText.getError() != null) {
                this.mCvcText.setError(null);
            }
            return true;
        }
        this.mCvcText.setError(this.mContext.getString(R.string.wallet_im_error_cvc_invalid));
        return false;
    }

    public boolean isComplete() {
        return isLengthCorrect() || this.mCvcText.getText().length() == this.mMaxFieldLength;
    }
}
