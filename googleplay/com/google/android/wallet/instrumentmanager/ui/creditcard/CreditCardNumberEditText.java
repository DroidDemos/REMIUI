package com.google.android.wallet.instrumentmanager.ui.creditcard;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Pair;
import android.widget.TextView;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.android.wallet.instrumentmanager.ui.common.Completable;
import com.google.android.wallet.instrumentmanager.ui.common.FormEditText;
import com.google.android.wallet.instrumentmanager.ui.common.WalletUiUtils;
import com.google.android.wallet.instrumentmanager.ui.common.validator.AbstractValidator;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.BinRange;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CardType;
import java.util.Arrays;

public class CreditCardNumberEditText extends FormEditText implements Completable {
    private static final int[] DEFAULT_DIGIT_GROUPING;
    CardType[] mAllowedCardTypes;
    private String mCardNumber;
    private Pair<CardType, BinRange> mCardTypeAndBinRange;
    private final TextWatcher mCreditCardNumberWatcher;
    private BinRange mInvalidBin;
    BinRange[] mInvalidBins;
    OnCreditCardTypeChangedListener mOnCreditCardTypeChangedListener;
    private ColorStateList mOriginalTextColors;

    public interface OnCreditCardTypeChangedListener {
        void onCreditCardTypeChanged(CardType cardType);
    }

    static {
        DEFAULT_DIGIT_GROUPING = new int[]{4, 4, 4, 4};
    }

    public CreditCardNumberEditText(Context context) {
        super(context);
        this.mCardNumber = "";
        this.mCreditCardNumberWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CardType oldCardType = CreditCardNumberEditText.this.getCardType();
                CreditCardNumberEditText.this.updateCardNumber(CreditCardNumberEditText.this.getText().toString());
                CardType newCardType = CreditCardNumberEditText.this.getCardType();
                if (CreditCardNumberEditText.this.mOnCreditCardTypeChangedListener != null && !PaymentUtils.equals(oldCardType, newCardType)) {
                    CreditCardNumberEditText.this.mOnCreditCardTypeChangedListener.onCreditCardTypeChanged(newCardType);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                String formatted = CreditCardNumberEditText.this.getFormattedCreditCardNumber(CreditCardNumberEditText.this.mCardNumber);
                if (!formatted.equals(s.toString())) {
                    s.replace(0, s.length(), formatted);
                }
            }
        };
        initializeViewAndListeners(context);
    }

    public CreditCardNumberEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCardNumber = "";
        this.mCreditCardNumberWatcher = /* anonymous class already generated */;
        initializeViewAndListeners(context);
    }

    public CreditCardNumberEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCardNumber = "";
        this.mCreditCardNumberWatcher = /* anonymous class already generated */;
        initializeViewAndListeners(context);
    }

    public void setOnCreditCardTypeChangedListener(OnCreditCardTypeChangedListener listener) {
        this.mOnCreditCardTypeChangedListener = listener;
    }

    public void setAllowedCardTypes(CardType[] cardTypes) {
        this.mAllowedCardTypes = cardTypes;
    }

    public void setInvalidBins(BinRange[] invalidBins) {
        this.mInvalidBins = invalidBins;
    }

    public String getCardNumber() {
        return this.mCardNumber;
    }

    public String getConcealedCardNumber() {
        int fullCardNumberLength = getCompleteCardNumberLength();
        int concealCount = Math.min(this.mCardNumber.length(), fullCardNumberLength - 4);
        char[] stars = new char[concealCount];
        Arrays.fill(stars, '\u2022');
        StringBuilder concealed = new StringBuilder(fullCardNumberLength).append(stars);
        if (concealCount < this.mCardNumber.length()) {
            concealed.append(this.mCardNumber.substring(concealCount));
        }
        return getFormattedCreditCardNumber(concealed.toString());
    }

    public CardType getCardType() {
        return this.mCardTypeAndBinRange != null ? (CardType) this.mCardTypeAndBinRange.first : null;
    }

    public boolean isComplete() {
        return this.mCardNumber.length() == getCompleteCardNumberLength();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        setOriginalTextColors();
    }

    private void initializeViewAndListeners(Context context) {
        setKeyListener(DigitsKeyListener.getInstance("1234567890 "));
        setSingleLine();
        setOriginalTextColors();
        addTextChangedListenerToFront(this.mCreditCardNumberWatcher);
        addOnTextChangeValidator(new AbstractValidator() {
            public boolean isValid(TextView view) {
                return CreditCardNumberEditText.this.mInvalidBin == null;
            }

            public String getErrorMessage() {
                return CreditCardNumberEditText.this.mInvalidBin.errorMessage;
            }
        });
        addValidator(new AbstractValidator(context.getString(R.string.wallet_im_error_creditcard_number_invalid)) {
            public boolean isValid(TextView view) {
                return CreditCardNumberEditText.this.mCardTypeAndBinRange != null && CreditCardNumberEditText.this.isComplete() && CreditCardNumberEditText.this.isLuhnChecksumValid();
            }
        });
    }

    private void setOriginalTextColors() {
        this.mOriginalTextColors = getTextColors();
    }

    private void updateCardNumber(String newCardNumber) {
        String normalizedCardNumber = PaymentUtils.removeNonNumericDigits(newCardNumber);
        if (!((this.mCardTypeAndBinRange == null && this.mInvalidBin == null) || normalizedCardNumber.startsWith(this.mCardNumber))) {
            this.mCardTypeAndBinRange = null;
            this.mInvalidBin = null;
        }
        if (this.mCardTypeAndBinRange == null && this.mAllowedCardTypes != null) {
            for (CardType cardType : this.mAllowedCardTypes) {
                BinRange bin = findFirstMatchingBin(cardType.binRange, normalizedCardNumber);
                if (bin != null) {
                    this.mCardTypeAndBinRange = Pair.create(cardType, bin);
                    break;
                }
            }
        }
        if (this.mInvalidBin == null) {
            this.mInvalidBin = findFirstMatchingBin(this.mInvalidBins, normalizedCardNumber);
        }
        int cardNumberLength = getCompleteCardNumberLength();
        if (normalizedCardNumber.length() > cardNumberLength) {
            normalizedCardNumber = normalizedCardNumber.substring(0, cardNumberLength);
        }
        this.mCardNumber = normalizedCardNumber;
        if (!isComplete() || isValid()) {
            setTextColor(this.mOriginalTextColors);
            return;
        }
        setTextColor(getResources().getColor(R.color.wallet_im_credit_card_invalid_text_color));
        WalletUiUtils.playShakeAnimationIfPossible(getContext(), this, 3);
    }

    private static BinRange findFirstMatchingBin(BinRange[] binRanges, String cardNumber) {
        if (binRanges == null) {
            return null;
        }
        int ccNumberLength = cardNumber.length();
        for (BinRange binRange : binRanges) {
            int length = binRange.start.length();
            if (ccNumberLength >= length) {
                String prefix = cardNumber.substring(0, length);
                if (prefix.compareTo(binRange.start) >= 0 && prefix.compareTo(binRange.end) <= 0) {
                    return binRange;
                }
            }
        }
        return null;
    }

    private boolean isLuhnChecksumValid() {
        int sum = 0;
        boolean doubled = false;
        for (int i = this.mCardNumber.length() - 1; i >= 0; i--) {
            int addend;
            int digit = Integer.parseInt(this.mCardNumber.substring(i, i + 1));
            if (doubled) {
                addend = digit * 2;
                if (addend > 9) {
                    addend -= 9;
                }
            } else {
                addend = digit;
            }
            sum += addend;
            if (doubled) {
                doubled = false;
            } else {
                doubled = true;
            }
        }
        if (sum % 10 == 0) {
            return true;
        }
        return false;
    }

    private String getFormattedCreditCardNumber(String input) {
        int[] digitGrouping = this.mCardTypeAndBinRange != null ? ((BinRange) this.mCardTypeAndBinRange.second).digitGrouping : DEFAULT_DIGIT_GROUPING;
        StringBuilder sb = new StringBuilder((getCompleteCardNumberLength() + digitGrouping.length) - 1);
        int group = 0;
        int groupElement = 0;
        int length = input.length();
        for (int i = 0; i < length; i++) {
            if (digitGrouping[group] == groupElement) {
                sb.append(' ');
                groupElement = 0;
                group++;
            }
            groupElement++;
            sb.append(input.charAt(i));
        }
        return sb.toString();
    }

    private int getCompleteCardNumberLength() {
        return this.mCardTypeAndBinRange != null ? ((BinRange) this.mCardTypeAndBinRange.second).cardNumberLength : 16;
    }
}
