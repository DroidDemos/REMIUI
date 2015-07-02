package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;
import com.android.vending.R;
import com.google.android.finsky.billing.creditcard.CreditCardType;
import com.google.android.finsky.utils.UiUtils;

public class CreditCardNumberEditText extends EditText {
    private CreditCardType mCurrentType;
    private OnCreditCardTypeChangedListener mOnCreditCardTypeChangedListener;
    private OnValidNumberEnteredListener mOnNumberEnteredListener;
    private ColorStateList mOriginalTextColors;

    public interface OnValidNumberEnteredListener {
        void onNumberEntered();
    }

    public interface OnCreditCardTypeChangedListener {
        void onCreditCardTypeChanged(CreditCardType creditCardType, CreditCardType creditCardType2);
    }

    private class NumberFormatter implements TextWatcher {
        private NumberFormatter() {
        }

        public void afterTextChanged(Editable s) {
            String input = s.toString();
            CreditCardType newType = CreditCardType.getTypeForPrefix(CreditCardType.normalizeNumber(input));
            CreditCardType formatType = newType != null ? newType : CreditCardType.MC;
            String normalizedLenghtLimited = formatType.limitLength(CreditCardType.normalizeNumber(input));
            String formatted = formatType.formatNumber(normalizedLenghtLimited);
            if (!formatted.equals(input)) {
                s.replace(0, s.length(), formatted);
            }
            if (CreditCardNumberEditText.this.mCurrentType != newType) {
                CreditCardType oldType = CreditCardNumberEditText.this.mCurrentType;
                CreditCardNumberEditText.this.mCurrentType = newType;
                if (CreditCardNumberEditText.this.mOnCreditCardTypeChangedListener != null) {
                    CreditCardNumberEditText.this.mOnCreditCardTypeChangedListener.onCreditCardTypeChanged(oldType, newType);
                }
            }
            if (normalizedLenghtLimited.length() != formatType.length) {
                CreditCardNumberEditText.this.setTextColor(CreditCardNumberEditText.this.mOriginalTextColors);
            } else if (!formatType.isValidNumber(normalizedLenghtLimited)) {
                CreditCardNumberEditText.this.setTextColor(CreditCardNumberEditText.this.getResources().getColor(R.color.credit_card_invalid_text_color));
                UiUtils.playShakeAnimationIfPossible(CreditCardNumberEditText.this.getContext(), CreditCardNumberEditText.this);
                UiUtils.sendAccessibilityEventWithText(CreditCardNumberEditText.this.getContext(), CreditCardNumberEditText.this.getResources().getString(R.string.accessibility_event_form_field_error, new Object[]{res.getString(R.string.card_number), CreditCardNumberEditText.this.getResources().getString(R.string.accessibility_event_invalid_card_number)}), CreditCardNumberEditText.this);
            } else if (CreditCardNumberEditText.this.mOnNumberEnteredListener != null) {
                CreditCardNumberEditText.this.mOnNumberEnteredListener.onNumberEntered();
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    public CreditCardNumberEditText(Context context) {
        this(context, null);
    }

    public CreditCardNumberEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CreditCardNumberEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCurrentType = null;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        setKeyListener(DigitsKeyListener.getInstance("0123456789 "));
        addTextChangedListener(new NumberFormatter());
        this.mOriginalTextColors = getTextColors();
    }

    public void setOnCreditCardTypeChangedListener(OnCreditCardTypeChangedListener onCreditCardTypeChangedListener) {
        this.mOnCreditCardTypeChangedListener = onCreditCardTypeChangedListener;
    }

    public void setOnNumberEnteredListener(OnValidNumberEnteredListener onNumberEnteredListener) {
        this.mOnNumberEnteredListener = onNumberEnteredListener;
    }

    public CreditCardType getCardType() {
        return this.mCurrentType;
    }
}
