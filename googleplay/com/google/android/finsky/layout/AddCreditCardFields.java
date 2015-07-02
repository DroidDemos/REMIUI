package com.google.android.finsky.layout;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.billing.creditcard.CreditCardType;
import com.google.android.finsky.layout.CreditCardNumberEditText.OnCreditCardTypeChangedListener;
import com.google.android.finsky.layout.CreditCardNumberEditText.OnValidNumberEnteredListener;
import com.google.android.finsky.utils.UiUtils;

public abstract class AddCreditCardFields extends RelativeLayout implements OnCreditCardTypeChangedListener {
    protected static CreditCardType[] CREDIT_CARD_IMAGES_TYPE_ORDER;
    private static String KEY_ALL_FIELDS_VISIBLE;
    private static String KEY_CARD_TYPE;
    private static String KEY_PARENT_STATE;
    private boolean mAllFieldsVisible;
    private final Handler mAutoFocusHandler;
    protected BillingAddress mBillingAddress;
    protected ImageView[] mCreditCardImages;
    protected CreditCardType mCurrentCardType;
    protected EditText mCvcField;
    protected ImageView mCvcImage;
    protected CreditCardImagesAnimator mImagesAnimator;
    private boolean mIsLightTheme;
    protected EditText mMonthField;
    protected TextView mMonthYearSeparator;
    protected CreditCardNumberEditText mNumberField;
    private OnAllFieldsVisibleListener mOnAllFieldsVisibleListener;
    protected TextView mPrivacyFooter;
    protected EditText mYearField;

    public interface OnAllFieldsVisibleListener {
        void onAllFieldsVisible();
    }

    private class AutoAdvancer implements TextWatcher {
        private int mMaxLength;
        private final TextView mTextView;

        public AutoAdvancer(TextView textView, int maxLength) {
            this.mTextView = textView;
            this.mMaxLength = maxLength;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.length() >= this.mMaxLength) {
                if (UiUtils.isAccessibilityEnabled(this.mTextView.getContext())) {
                    AddCreditCardFields.this.mAutoFocusHandler.postDelayed(new Runnable() {
                        public void run() {
                            AddCreditCardFields.focusNext(AutoAdvancer.this.mTextView);
                        }
                    }, 750);
                } else {
                    AddCreditCardFields.focusNext(this.mTextView);
                }
            } else if (editable.length() != 0) {
            } else {
                if (UiUtils.isAccessibilityEnabled(this.mTextView.getContext())) {
                    AddCreditCardFields.this.mAutoFocusHandler.postDelayed(new Runnable() {
                        public void run() {
                            AddCreditCardFields.focusPrevious(AutoAdvancer.this.mTextView);
                        }
                    }, 750);
                } else {
                    AddCreditCardFields.focusPrevious(this.mTextView);
                }
            }
        }
    }

    private class CvcTextWatcher implements TextWatcher {
        private CvcTextWatcher() {
        }

        public void afterTextChanged(Editable s) {
            if (s.length() >= getCurrentCvcLength()) {
                AddCreditCardFields.this.onCvcEntered();
            } else if (s.length() == 0) {
                AddCreditCardFields.focusPrevious(AddCreditCardFields.this.mCvcField);
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        private int getCurrentCvcLength() {
            int cvcLength = CreditCardType.getMaxCvcLength();
            if (AddCreditCardFields.this.mCurrentCardType != null) {
                return AddCreditCardFields.this.mCurrentCardType.cvcLength;
            }
            return cvcLength;
        }
    }

    protected abstract CreditCardImagesAnimator createCreditCardImagesAnimator();

    public abstract boolean expandFields();

    protected abstract void onCvcEntered();

    protected abstract void onCvcFocused();

    protected abstract void onNumberEntered();

    protected abstract void updateIconsFromTheme(boolean z);

    static {
        KEY_PARENT_STATE = "parent_instance_state";
        KEY_ALL_FIELDS_VISIBLE = "all_fields_visible";
        KEY_CARD_TYPE = "card_type";
        CREDIT_CARD_IMAGES_TYPE_ORDER = new CreditCardType[]{CreditCardType.VISA, CreditCardType.MC, CreditCardType.AMEX, CreditCardType.DISCOVER, CreditCardType.JCB};
    }

    public AddCreditCardFields(Context context) {
        this(context, null);
    }

    public AddCreditCardFields(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIsLightTheme = true;
        this.mAutoFocusHandler = new Handler(Looper.myLooper());
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mNumberField = (CreditCardNumberEditText) findViewById(R.id.cc_box);
        this.mMonthField = (EditText) findViewById(R.id.expiration_date_entry_1st);
        this.mMonthYearSeparator = (TextView) findViewById(R.id.expiration_date_separator);
        this.mYearField = (EditText) findViewById(R.id.expiration_date_entry_2nd);
        this.mCvcField = (EditText) findViewById(R.id.cvc_entry);
        this.mBillingAddress = (BillingAddress) findViewById(R.id.billing_address);
        this.mCvcImage = (ImageView) findViewById(R.id.cvc_image);
        this.mCreditCardImages = new ImageView[]{(ImageView) findViewById(R.id.visa_logo), (ImageView) findViewById(R.id.mastercard_logo), (ImageView) findViewById(R.id.amex_logo), (ImageView) findViewById(R.id.discover_logo), (ImageView) findViewById(R.id.jcb_logo)};
        this.mPrivacyFooter = (TextView) findViewById(R.id.billing_addcreditcard_privacy_footer);
        this.mImagesAnimator = createCreditCardImagesAnimator();
        this.mNumberField.setOnNumberEnteredListener(new OnValidNumberEnteredListener() {
            public void onNumberEntered() {
                AddCreditCardFields.this.onNumberEntered();
                if (UiUtils.isAccessibilityEnabled(AddCreditCardFields.this.mMonthField.getContext())) {
                    AddCreditCardFields.this.mAutoFocusHandler.postDelayed(new Runnable() {
                        public void run() {
                            AddCreditCardFields.this.mMonthField.requestFocus();
                        }
                    }, 750);
                } else {
                    AddCreditCardFields.this.mMonthField.requestFocus();
                }
            }
        });
        this.mNumberField.setOnCreditCardTypeChangedListener(this);
        this.mMonthField.addTextChangedListener(new AutoAdvancer(this.mMonthField, 2));
        this.mYearField.addTextChangedListener(new AutoAdvancer(this.mYearField, 2));
        this.mCvcField.addTextChangedListener(new CvcTextWatcher());
        this.mCvcField.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AddCreditCardFields.this.onCvcFocused();
                }
            }
        });
        updateIconsFromTheme(this.mIsLightTheme);
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PARENT_STATE, super.onSaveInstanceState());
        bundle.putBoolean(KEY_ALL_FIELDS_VISIBLE, this.mAllFieldsVisible);
        bundle.putInt(KEY_CARD_TYPE, this.mCurrentCardType != null ? this.mCurrentCardType.ordinal() : -1);
        bundle.putBoolean("is_light_theme", this.mIsLightTheme);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mAllFieldsVisible = bundle.getBoolean(KEY_ALL_FIELDS_VISIBLE);
            int cardTypeInt = bundle.getInt(KEY_CARD_TYPE);
            this.mCurrentCardType = cardTypeInt == -1 ? null : CreditCardType.values()[cardTypeInt];
            this.mIsLightTheme = bundle.getBoolean("ui_mode");
            if (this.mAllFieldsVisible) {
                onAllFieldsVisible();
            }
            super.onRestoreInstanceState(bundle.getParcelable(KEY_PARENT_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void setOnAllFieldsVisibleListener(OnAllFieldsVisibleListener onAllFieldsVisibleListener) {
        this.mOnAllFieldsVisibleListener = onAllFieldsVisibleListener;
    }

    public void setIsLightTheme(boolean isLightTheme) {
        this.mIsLightTheme = isLightTheme;
        updateIconsFromTheme(isLightTheme);
    }

    public boolean areAllFieldsVisible() {
        return this.mAllFieldsVisible;
    }

    protected static void focusNext(View v) {
        View nextView = VERSION.SDK_INT >= 14 ? v.focusSearch(2) : v.focusSearch(130);
        if (nextView != null) {
            nextView.requestFocus();
        }
    }

    protected static void focusPrevious(View v) {
        View previousView = VERSION.SDK_INT >= 14 ? v.focusSearch(1) : v.focusSearch(33);
        if (previousView != null) {
            previousView.requestFocus();
        }
    }

    protected void onAllFieldsVisible() {
        this.mAllFieldsVisible = true;
        if (this.mOnAllFieldsVisibleListener != null) {
            this.mOnAllFieldsVisibleListener.onAllFieldsVisible();
        }
    }

    public void onCreditCardTypeChanged(CreditCardType oldType, CreditCardType newType) {
        this.mCurrentCardType = newType;
        this.mImagesAnimator.animateToType(newType);
    }
}
