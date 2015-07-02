package com.google.android.wallet.instrumentmanager.ui.creditcard;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.android.wallet.instrumentmanager.config.G.images;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.ui.common.ExpDateChecker;
import com.google.android.wallet.instrumentmanager.ui.common.ExpMonthChecker;
import com.google.android.wallet.instrumentmanager.ui.common.ExpYearChecker;
import com.google.android.wallet.instrumentmanager.ui.common.FifeNetworkImageView;
import com.google.android.wallet.instrumentmanager.ui.common.FormEditText;
import com.google.android.wallet.instrumentmanager.ui.common.FormFragment;
import com.google.android.wallet.instrumentmanager.ui.common.Validatable;
import com.google.android.wallet.instrumentmanager.ui.common.WalletUiUtils;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.FormFieldMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CreditCardExpirationDateForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CreditCardExpirationDateFormValue;
import java.util.List;

public class CreditCardExpirationDateFragment extends FormFragment<CreditCardExpirationDateForm> implements OnClickListener, UiNode {
    TextView mCreditCardLabel;
    CvcChecker mCvcChecker;
    View mCvcHintImage;
    FormEditText mCvcText;
    ExpMonthChecker mExpMonthChecker;
    public FormEditText mExpMonthText;
    ExpYearChecker mExpYearChecker;
    FormEditText mExpYearText;
    private final InstrumentManagerUiElement mUiElement;

    public CreditCardExpirationDateFragment() {
        this.mUiElement = new InstrumentManagerUiElement(1651);
    }

    public static CreditCardExpirationDateFragment newInstance(CreditCardExpirationDateForm creditCardExpirationDateForm, int themeResourceId) {
        CreditCardExpirationDateFragment fragment = new CreditCardExpirationDateFragment();
        fragment.setArguments(FormFragment.createArgsForFormFragment(themeResourceId, creditCardExpirationDateForm, CreditCardExpirationDateFragment.class));
        return fragment;
    }

    public boolean validate() {
        return validate(true);
    }

    public boolean isValid() {
        return validate(false);
    }

    private boolean validate(boolean showErrorIfInvalid) {
        boolean valid = true;
        for (Validatable fieldToValidate : new Validatable[]{this.mExpMonthChecker, this.mExpYearChecker, this.mCvcChecker}) {
            if (showErrorIfInvalid) {
                if (fieldToValidate.validate() && valid) {
                    valid = true;
                } else {
                    valid = false;
                }
            } else if (!fieldToValidate.isValid()) {
                return false;
            }
        }
        return valid;
    }

    public boolean focusOnFirstInvalidFormField() {
        FormEditText[] arr$ = new FormEditText[]{this.mExpMonthText, this.mExpYearText, this.mCvcText};
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            FormEditText fieldToValidate = arr$[i$];
            if (TextUtils.isEmpty(fieldToValidate.getError())) {
                i$++;
            } else {
                WalletUiUtils.requestFocusAndAnnounceError(fieldToValidate);
                return true;
            }
        }
        return false;
    }

    public boolean applyFormFieldMessage(FormFieldMessage formFieldMessage) {
        if (!formFieldMessage.formFieldReference.formId.equals(((CreditCardExpirationDateForm) this.mFormProto).id)) {
            return false;
        }
        switch (formFieldMessage.formFieldReference.fieldId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mCvcText.setError(formFieldMessage.message);
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mExpMonthText.setError(formFieldMessage.message);
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mExpYearText.setError(formFieldMessage.message);
                break;
            default:
                throw new IllegalArgumentException("Unknown FormFieldMessage fieldId: " + formFieldMessage.formFieldReference.fieldId);
        }
        return true;
    }

    protected void doEnableUi() {
        if (this.mExpMonthText != null) {
            boolean uiEnabled = isUiEnabled();
            this.mExpMonthText.setEnabled(uiEnabled);
            this.mExpYearText.setEnabled(uiEnabled);
            this.mCvcText.setEnabled(uiEnabled);
            this.mCvcHintImage.setEnabled(uiEnabled);
        }
    }

    public boolean isReadyToSubmit() {
        return true;
    }

    protected View onCreateThemedView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_credit_card_expiration_date, null, false);
        this.mCreditCardLabel = (TextView) content.findViewById(R.id.credit_card_label);
        this.mCreditCardLabel.setText(((CreditCardExpirationDateForm) this.mFormProto).cardLabel);
        FifeNetworkImageView cardImage = (FifeNetworkImageView) content.findViewById(R.id.card_logo);
        if (PaymentUtils.isEmbeddedImageUri(((CreditCardExpirationDateForm) this.mFormProto).icon.imageUri)) {
            cardImage.setDefaultImageResId(PaymentUtils.embeddedImageUriToDrawableResourceId(((CreditCardExpirationDateForm) this.mFormProto).icon.imageUri));
        } else {
            cardImage.setFifeImageUrl(((CreditCardExpirationDateForm) this.mFormProto).icon.imageUri, PaymentUtils.getImageLoader(getActivity().getApplicationContext()), ((Boolean) images.useWebPForFife.get()).booleanValue());
            cardImage.setDefaultImageResId(R.drawable.wallet_im_card_general);
        }
        if (!TextUtils.isEmpty(((CreditCardExpirationDateForm) this.mFormProto).icon.altText)) {
            cardImage.setContentDescription(((CreditCardExpirationDateForm) this.mFormProto).icon.altText);
        }
        this.mExpMonthText = (FormEditText) content.findViewById(R.id.exp_month);
        this.mExpYearText = (FormEditText) content.findViewById(R.id.exp_year);
        this.mCvcText = (FormEditText) content.findViewById(R.id.cvc);
        this.mCvcText.setFilters(new InputFilter[]{new LengthFilter(((CreditCardExpirationDateForm) this.mFormProto).cvcLength)});
        this.mCvcHintImage = content.findViewById(R.id.cvc_hint);
        this.mCvcHintImage.setOnClickListener(this);
        this.mCvcChecker = new CvcChecker(getActivity(), this.mCvcText, ((CreditCardExpirationDateForm) this.mFormProto).cvcLength);
        ExpDateChecker expDateChecker = new ExpDateChecker(getActivity(), this.mExpMonthText, this.mExpYearText, ((CreditCardExpirationDateForm) this.mFormProto).minMonth, ((CreditCardExpirationDateForm) this.mFormProto).minYear, ((CreditCardExpirationDateForm) this.mFormProto).maxMonth, ((CreditCardExpirationDateForm) this.mFormProto).maxYear);
        this.mExpMonthChecker = new ExpMonthChecker(this.mExpMonthText, this.mExpYearText, expDateChecker);
        this.mExpYearChecker = new ExpYearChecker(this.mExpYearText, expDateChecker);
        this.mCvcText.enableAutoAdvance(this.mCvcChecker, this.mCvcChecker, true);
        this.mExpMonthText.enableAutoAdvance(this.mExpMonthChecker, this.mExpMonthChecker, false);
        this.mExpYearText.enableAutoAdvance(this.mExpYearChecker, this.mExpYearChecker, true);
        this.mCvcText.setOnOutOfFocusValidatable(this.mCvcChecker);
        this.mExpMonthText.setOnOutOfFocusValidatable(this.mExpMonthChecker);
        this.mExpYearText.setOnOutOfFocusValidatable(this.mExpYearChecker);
        this.mExpMonthText.setOnFocusChangeListener(this.mExpMonthChecker);
        doEnableUi();
        return content;
    }

    public CreditCardExpirationDateFormValue getCreditCardExpirationDateFormValue() {
        int expMonth;
        int expYear;
        String cvc = PaymentUtils.removeNonNumericDigits(this.mCvcText.getText().toString());
        try {
            expMonth = Integer.parseInt(this.mExpMonthText.getText().toString());
        } catch (NumberFormatException e) {
            expMonth = 0;
        }
        try {
            expYear = Integer.parseInt(this.mExpYearText.getText().toString()) + 2000;
        } catch (NumberFormatException e2) {
            expYear = 0;
        }
        CreditCardExpirationDateFormValue expDateFormValue = new CreditCardExpirationDateFormValue();
        expDateFormValue.newMonth = expMonth;
        expDateFormValue.newYear = expYear;
        expDateFormValue.cvc = cvc;
        return expDateFormValue;
    }

    public void onClick(View v) {
        if (v == this.mCvcHintImage && getFragmentManager().findFragmentByTag("CvcInfoDialog") == null) {
            CvcInfoDialogFragment.newInstance(getThemeResourceId()).show(getFragmentManager(), "CvcInfoDialog");
        }
    }

    public InstrumentManagerUiElement getUiElement() {
        return this.mUiElement;
    }

    public List<UiNode> getChildren() {
        return null;
    }
}
