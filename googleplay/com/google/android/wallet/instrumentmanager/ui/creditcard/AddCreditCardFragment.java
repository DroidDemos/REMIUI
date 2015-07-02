package com.google.android.wallet.instrumentmanager.ui.creditcard;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ocr.CreditCardOcrIntentBuilder;
import com.google.android.gms.ocr.CreditCardOcrResult;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.analytics.SimpleUiNode;
import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.analytics.util.AnalyticsUtil;
import com.google.android.wallet.instrumentmanager.common.address.RegionCode;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.android.wallet.instrumentmanager.pub.analytics.CreditCardEntryAction;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.ui.address.AddressEntryFragment;
import com.google.android.wallet.instrumentmanager.ui.address.AddressEntryFragment.OnAddressFieldsShownListener;
import com.google.android.wallet.instrumentmanager.ui.address.DynamicAddressFieldsLayout.OnHeightOffsetChangedListener;
import com.google.android.wallet.instrumentmanager.ui.common.AutoAdvancedListener;
import com.google.android.wallet.instrumentmanager.ui.common.ClickSpan;
import com.google.android.wallet.instrumentmanager.ui.common.ExpDateChecker;
import com.google.android.wallet.instrumentmanager.ui.common.ExpMonthChecker;
import com.google.android.wallet.instrumentmanager.ui.common.ExpYearChecker;
import com.google.android.wallet.instrumentmanager.ui.common.FormEditText;
import com.google.android.wallet.instrumentmanager.ui.common.FormFragment;
import com.google.android.wallet.instrumentmanager.ui.common.InfoMessageTextView;
import com.google.android.wallet.instrumentmanager.ui.common.RegionCodeSelector.OnRegionCodeSelectedListener;
import com.google.android.wallet.instrumentmanager.ui.common.Validatable;
import com.google.android.wallet.instrumentmanager.ui.common.WalletUiUtils;
import com.google.android.wallet.instrumentmanager.ui.common.WebViewDialogFragment;
import com.google.android.wallet.instrumentmanager.ui.creditcard.CreditCardNumberEditText.OnCreditCardTypeChangedListener;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.FormFieldMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CardType;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CreditCardForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CreditCardFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.legal.LegalMessageOuterClass.LegalMessage;
import java.util.ArrayList;
import java.util.List;

public class AddCreditCardFragment extends FormFragment<CreditCardForm> implements OnClickListener, OnFocusChangeListener, UiNode, OnAddressFieldsShownListener, OnHeightOffsetChangedListener, ClickSpan.OnClickListener, OnRegionCodeSelectedListener, OnCreditCardTypeChangedListener {
    AddressEntryFragment mAddressEntryFragment;
    View mAddressEntryFragmentHolder;
    private boolean mAddressFieldsShown;
    View[] mAnimatedChildren;
    private CreditCardEntryAction mCreditCardEntryAction;
    CreditCardImagesView mCreditCardImagesView;
    TextView mCreditCardNumberConcealedText;
    CreditCardNumberEditText mCreditCardNumberText;
    private TextWatcher mCreditCardNumberTextWatcher;
    CvcChecker mCvcChecker;
    ImageView mCvcHintImage;
    FormEditText mCvcText;
    View mExpCvcLayout;
    private TextWatcher mExpDateTextWatcher;
    ExpMonthChecker mExpMonthChecker;
    FormEditText mExpMonthText;
    ExpYearChecker mExpYearChecker;
    FormEditText mExpYearText;
    View mExpandCreditCardIcon;
    private Intent mLaunchOcrIntent;
    private LegalMessage mLegalMessage;
    InfoMessageTextView mLegalMessageText;
    View mOcrIcon;
    private OnAddCreditCardFragmentStateChangedListener mOnStateChangedListener;
    RelativeLayout mRoot;
    int mSelectedRegionCode;
    private final InstrumentManagerUiElement mUiElement;
    private int mViewState;

    public interface OnAddCreditCardFragmentStateChangedListener {
        void animateViewsBelow(int i, int i2, long j);

        void showViewsBelow(boolean z, boolean z2, int i, int i2, long j);
    }

    public AddCreditCardFragment() {
        this.mViewState = 0;
        this.mUiElement = new InstrumentManagerUiElement(1650);
        this.mSelectedRegionCode = 0;
        this.mAddressFieldsShown = true;
    }

    public static AddCreditCardFragment newInstance(CreditCardForm creditCardForm, int themeResourceId) {
        AddCreditCardFragment fragment = new AddCreditCardFragment();
        fragment.setArguments(FormFragment.createArgsForFormFragment(themeResourceId, creditCardForm, AddCreditCardFragment.class));
        return fragment;
    }

    public boolean validate() {
        boolean result = validate(true);
        boolean shouldSendCreditCardEvent = false;
        if (!(this.mCreditCardEntryAction.panValidationErrorOccurred || TextUtils.isEmpty(this.mCreditCardNumberText.getError()))) {
            this.mCreditCardEntryAction.panValidationErrorOccurred = true;
            shouldSendCreditCardEvent = true;
        }
        if (!(this.mCreditCardEntryAction.expDateValidationErrorOccurred || (TextUtils.isEmpty(this.mExpMonthText.getError()) && TextUtils.isEmpty(this.mExpYearText.getError())))) {
            this.mCreditCardEntryAction.expDateValidationErrorOccurred = true;
            shouldSendCreditCardEvent = true;
        }
        if (shouldSendCreditCardEvent) {
            sendCreditCardEntryActionBackgroundEvent();
        }
        return result;
    }

    public boolean isValid() {
        return validate(false);
    }

    private boolean validate(boolean showErrorIfInvalid) {
        boolean valid = true;
        for (Validatable fieldToValidate : new Validatable[]{this.mCreditCardNumberText, this.mExpMonthChecker, this.mExpYearChecker, this.mCvcChecker, this.mAddressEntryFragment}) {
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
        if (VERSION.SDK_INT >= 14 && showErrorIfInvalid && !valid) {
            transitionToState(5, false);
        }
        return valid;
    }

    public boolean focusOnFirstInvalidFormField() {
        FormEditText[] arr$ = new FormEditText[]{this.mCreditCardNumberText, this.mExpMonthText, this.mExpYearText, this.mCvcText};
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
        return this.mAddressEntryFragment.focusOnFirstInvalidFormField();
    }

    public boolean applyFormFieldMessage(FormFieldMessage formFieldMessage) {
        if (!formFieldMessage.formFieldReference.formId.equals(((CreditCardForm) this.mFormProto).id)) {
            return this.mAddressEntryFragment.applyFormFieldMessage(formFieldMessage);
        }
        switch (formFieldMessage.formFieldReference.fieldId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mCreditCardNumberText.setError(formFieldMessage.message);
                if (!this.mCreditCardEntryAction.panValidationErrorOccurred) {
                    this.mCreditCardEntryAction.panValidationErrorOccurred = true;
                    sendCreditCardEntryActionBackgroundEvent();
                    break;
                }
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mCvcText.setError(formFieldMessage.message);
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mExpMonthText.setError(formFieldMessage.message);
                if (!this.mCreditCardEntryAction.expDateValidationErrorOccurred) {
                    this.mCreditCardEntryAction.expDateValidationErrorOccurred = true;
                    sendCreditCardEntryActionBackgroundEvent();
                    break;
                }
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                this.mExpYearText.setError(formFieldMessage.message);
                if (!this.mCreditCardEntryAction.expDateValidationErrorOccurred) {
                    this.mCreditCardEntryAction.expDateValidationErrorOccurred = true;
                    sendCreditCardEntryActionBackgroundEvent();
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown FormFieldMessage fieldId: " + formFieldMessage.formFieldReference.fieldId);
        }
        if (VERSION.SDK_INT >= 14) {
            transitionToState(5, false);
        }
        return true;
    }

    public boolean handleErrorMessageDismissed(String formId, int errorType) {
        return this.mAddressEntryFragment.handleErrorMessageDismissed(formId, errorType);
    }

    public void doEnableUi() {
        if (this.mCreditCardNumberText != null) {
            boolean uiEnabled = isUiEnabled();
            this.mCreditCardNumberText.setEnabled(uiEnabled);
            this.mOcrIcon.setEnabled(uiEnabled);
            this.mExpMonthText.setEnabled(uiEnabled);
            this.mExpYearText.setEnabled(uiEnabled);
            this.mCvcText.setEnabled(uiEnabled);
            this.mExpandCreditCardIcon.setEnabled(uiEnabled);
            this.mAddressEntryFragment.enableUi(uiEnabled);
            this.mCvcHintImage.setEnabled(uiEnabled);
            this.mLegalMessageText.setEnabled(uiEnabled);
        }
    }

    public boolean isReadyToSubmit() {
        if (this.mAddressEntryFragment == null || !this.mAddressEntryFragment.isReadyToSubmit()) {
            return false;
        }
        switch (this.mViewState) {
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return true;
            default:
                return false;
        }
    }

    private static int getStateAfterEnteringCardNumberCompleted() {
        return VERSION.SDK_INT < 14 ? 3 : 2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mSelectedRegionCode = savedInstanceState.getInt("selectedRegionCode", 0);
            if (this.mSelectedRegionCode != 0) {
                this.mLegalMessage = PaymentUtils.findLegalMessageByCountry(((CreditCardForm) this.mFormProto).legalMessages, RegionCode.toCountryCode(this.mSelectedRegionCode));
            }
        }
        if (((CreditCardForm) this.mFormProto).allowCameraInput) {
            TypedArray attrs = getThemedContext().obtainStyledAttributes(new int[]{R.attr.ccOcrTheme});
            int ccOcrTheme = attrs.getInt(0, 0);
            attrs.recycle();
            this.mLaunchOcrIntent = new CreditCardOcrIntentBuilder(getActivity()).setTheme(ccOcrTheme).build();
        }
        if (savedInstanceState != null) {
            this.mCreditCardEntryAction = (CreditCardEntryAction) savedInstanceState.getParcelable("creditCardEntryAction");
        }
        if (this.mCreditCardEntryAction == null) {
            this.mCreditCardEntryAction = new CreditCardEntryAction();
            this.mCreditCardEntryAction.panOcrEnabled = isOcrEnabled();
        }
    }

    protected View onCreateThemedView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_add_credit_card, null, false);
        this.mRoot = (RelativeLayout) content.findViewById(R.id.credit_card_root);
        if (!TextUtils.isEmpty(((CreditCardForm) this.mFormProto).title)) {
            TextView titleText = (TextView) this.mRoot.findViewById(R.id.add_credit_card_title);
            titleText.setText(((CreditCardForm) this.mFormProto).title);
            titleText.setVisibility(0);
        }
        this.mCreditCardImagesView = (CreditCardImagesView) content.findViewById(R.id.credit_card_images);
        this.mCreditCardImagesView.setCardTypes(((CreditCardForm) this.mFormProto).allowedCardType);
        this.mCreditCardNumberConcealedText = (TextView) content.findViewById(R.id.card_number_concealed);
        this.mCreditCardNumberText = (CreditCardNumberEditText) content.findViewById(R.id.card_number);
        this.mExpCvcLayout = content.findViewById(R.id.exp_date_and_cvc);
        this.mExpMonthText = (FormEditText) content.findViewById(R.id.exp_month);
        this.mExpYearText = (FormEditText) content.findViewById(R.id.exp_year);
        this.mCvcText = (FormEditText) content.findViewById(R.id.cvc);
        this.mCvcText.setFilters(new InputFilter[]{new LengthFilter(4)});
        this.mCvcHintImage = (ImageView) content.findViewById(R.id.cvc_hint);
        this.mCvcHintImage.setOnClickListener(this);
        this.mOcrIcon = content.findViewById(R.id.ocr_icon);
        this.mOcrIcon.setOnClickListener(this);
        this.mExpandCreditCardIcon = content.findViewById(R.id.expand_icon);
        this.mExpandCreditCardIcon.setOnClickListener(this);
        this.mLegalMessageText = (InfoMessageTextView) content.findViewById(R.id.add_card_legal_message_text);
        this.mLegalMessageText.setParentUiNode(this);
        this.mLegalMessageText.setUrlClickListener(this);
        this.mCvcChecker = new CvcChecker(getActivity(), this.mCvcText, this.mCreditCardNumberText, 4);
        ExpDateChecker expDateChecker = new ExpDateChecker(getActivity(), this.mExpMonthText, this.mExpYearText, ((CreditCardForm) this.mFormProto).minExpirationMonth, ((CreditCardForm) this.mFormProto).minExpirationYear, ((CreditCardForm) this.mFormProto).maxExpirationMonth, ((CreditCardForm) this.mFormProto).maxExpirationYear);
        this.mExpMonthChecker = new ExpMonthChecker(this.mExpMonthText, this.mExpYearText, expDateChecker);
        this.mExpYearChecker = new ExpYearChecker(this.mExpYearText, expDateChecker);
        this.mCvcText.enableAutoAdvance(this.mCvcChecker, this.mCvcChecker, true);
        this.mExpMonthText.enableAutoAdvance(this.mExpMonthChecker, this.mExpMonthChecker, true);
        this.mExpYearText.enableAutoAdvance(this.mExpYearChecker, this.mExpYearChecker, true);
        this.mCreditCardNumberText.enableAutoAdvance(this.mCreditCardNumberText, this.mCreditCardNumberText, false);
        this.mCvcText.setOnOutOfFocusValidatable(this.mCvcChecker);
        this.mExpMonthText.setOnOutOfFocusValidatable(this.mExpMonthChecker);
        this.mExpYearText.setOnOutOfFocusValidatable(this.mExpYearChecker);
        this.mExpMonthText.setOnFocusChangeListener(this.mExpMonthChecker);
        this.mCvcText.setAutoAdvancedListener(new AutoAdvancedListener() {
            public void onAutoAdvanced() {
                AddCreditCardFragment.this.transitionToState(4, true);
            }
        });
        this.mCreditCardNumberText.setOnCreditCardTypeChangedListener(this);
        this.mCreditCardNumberText.setAllowedCardTypes(((CreditCardForm) this.mFormProto).allowedCardType);
        this.mCreditCardNumberText.setInvalidBins(((CreditCardForm) this.mFormProto).invalidBin);
        this.mAddressEntryFragmentHolder = content.findViewById(R.id.address_fragment_holder);
        this.mAddressEntryFragment = (AddressEntryFragment) getChildFragmentManager().findFragmentById(R.id.address_fragment_holder);
        if (this.mAddressEntryFragment == null) {
            this.mAddressEntryFragment = AddressEntryFragment.newInstance(this.mFormProto != null ? ((CreditCardForm) this.mFormProto).billingAddress : null, getThemeResourceId());
            getChildFragmentManager().beginTransaction().add(R.id.address_fragment_holder, this.mAddressEntryFragment).commit();
        }
        this.mAddressEntryFragment.setOnRegionCodeSelectedListener(this);
        this.mAddressEntryFragment.setOnHeightOffsetChangedListener(this);
        this.mAddressEntryFragment.setOnAddressFieldsShownListener(this);
        doEnableUi();
        this.mAnimatedChildren = new View[]{this.mRoot, this.mCreditCardImagesView, this.mCreditCardNumberText, this.mCreditCardNumberConcealedText, this.mExpandCreditCardIcon, this.mOcrIcon, this.mExpCvcLayout, this.mAddressEntryFragmentHolder, this.mLegalMessageText};
        int state = 1;
        if (savedInstanceState != null) {
            state = savedInstanceState.getInt("viewState", 3);
        }
        transitionToState(state, false);
        return content;
    }

    private void switchToShowingAddress(boolean animate) {
        if (!animate) {
            this.mAddressEntryFragmentHolder.setVisibility(0);
            this.mLegalMessageText.setVisibility(0);
            if (this.mOnStateChangedListener != null) {
                this.mOnStateChangedListener.showViewsBelow(true, false, 0, 0, 0);
            }
            if (VERSION.SDK_INT >= 14) {
                this.mExpandCreditCardIcon.setVisibility(0);
                this.mCreditCardNumberText.setVisibility(8);
                this.mOcrIcon.setVisibility(8);
                this.mCreditCardNumberConcealedText.setVisibility(0);
                this.mCreditCardImagesView.switchToOneCardMode();
                this.mExpCvcLayout.setVisibility(8);
            }
        } else if (VERSION.SDK_INT < 14) {
            WalletUiUtils.animateViewAppearing(this.mAddressEntryFragmentHolder, 0, 0);
            WalletUiUtils.animateViewAppearing(this.mLegalMessageText, 0, 0);
            if (this.mOnStateChangedListener != null) {
                this.mOnStateChangedListener.showViewsBelow(true, true, 0, 0, 0);
            }
        } else {
            setAnimationDelay(150);
            int creditCardImagesHeight = WalletUiUtils.getViewHeightWithMargins(this.mCreditCardImagesView);
            int creditCardNumberHeight = WalletUiUtils.getViewHeightWithMargins(this.mCreditCardNumberText);
            int expCvcHeight = WalletUiUtils.getViewHeightWithMargins(this.mExpCvcLayout);
            this.mCreditCardImagesView.switchToOneCardMode();
            WalletUiUtils.animateViewDisappearingToGone(this.mCreditCardNumberText, 0, -creditCardImagesHeight);
            if (this.mOcrIcon.getVisibility() == 0) {
                WalletUiUtils.animateViewDisappearingToGone(this.mOcrIcon, 0, -creditCardImagesHeight);
            }
            WalletUiUtils.animateViewAppearing(this.mCreditCardNumberConcealedText, creditCardImagesHeight, 0);
            WalletUiUtils.animateViewAppearing(this.mExpandCreditCardIcon, creditCardImagesHeight, 0);
            LayoutParams expCvcLayoutParams = (LayoutParams) this.mExpCvcLayout.getLayoutParams();
            expCvcLayoutParams.addRule(3, 0);
            expCvcLayoutParams.addRule(10);
            WalletUiUtils.animateViewDisappearingToGone(this.mExpCvcLayout, creditCardImagesHeight + creditCardNumberHeight, 0);
            ((LayoutParams) this.mAddressEntryFragmentHolder.getLayoutParams()).addRule(3, this.mCreditCardImagesView.getId());
            int addressAndLegalStartDeltaY = creditCardNumberHeight + expCvcHeight;
            WalletUiUtils.animateViewAppearing(this.mAddressEntryFragmentHolder, addressAndLegalStartDeltaY, 0);
            WalletUiUtils.animateViewAppearing(this.mLegalMessageText, addressAndLegalStartDeltaY, 0);
            if (this.mOnStateChangedListener != null) {
                this.mOnStateChangedListener.showViewsBelow(true, true, addressAndLegalStartDeltaY, 0, 150);
            }
            setAnimationDelay(0);
        }
    }

    private void switchToExpandedState(boolean animate) {
        if (VERSION.SDK_INT < 14) {
            throw new IllegalStateException("Can not switch ui to expanded state for api level: " + VERSION.SDK_INT);
        }
        updateRelativeLayoutParamsToShowExpandedCardView();
        if (animate) {
            setAnimationDelay(150);
            WalletUiUtils.animateViewDisappearingToGone(this.mCreditCardNumberConcealedText, 0, 0);
            WalletUiUtils.animateViewDisappearingToGone(this.mExpandCreditCardIcon, 0, 0);
            WalletUiUtils.animateViewAppearing(this.mCreditCardNumberText, 0, 0);
            if (isOcrEnabled()) {
                WalletUiUtils.animateViewAppearing(this.mOcrIcon, 0, 0);
            }
        } else {
            this.mCreditCardNumberConcealedText.setVisibility(8);
            this.mExpandCreditCardIcon.setVisibility(8);
            this.mCreditCardNumberText.setVisibility(0);
            this.mCreditCardNumberText.setTranslationY(0.0f);
            this.mCreditCardNumberText.setAlpha(1.0f);
            if (isOcrEnabled()) {
                this.mOcrIcon.setVisibility(0);
                this.mOcrIcon.setTranslationY(0.0f);
                this.mOcrIcon.setAlpha(1.0f);
            }
        }
        LayoutParams expCvcLayoutParams = (LayoutParams) this.mExpCvcLayout.getLayoutParams();
        expCvcLayoutParams.addRule(10, 0);
        expCvcLayoutParams.addRule(3, this.mCreditCardNumberText.getId());
        int deltaY = -WalletUiUtils.getViewHeightWithMargins(this.mCreditCardNumberConcealedText);
        if (animate) {
            WalletUiUtils.animateViewAppearing(this.mExpCvcLayout, deltaY, 0);
        } else {
            this.mExpCvcLayout.setVisibility(0);
            this.mExpCvcLayout.setTranslationY(0.0f);
            this.mExpCvcLayout.setAlpha(1.0f);
        }
        ((LayoutParams) this.mAddressEntryFragmentHolder.getLayoutParams()).addRule(3, this.mExpCvcLayout.getId());
        if (animate) {
            this.mAddressEntryFragmentHolder.setTranslationY((float) deltaY);
            this.mAddressEntryFragmentHolder.animate().translationY(0.0f).start();
            this.mLegalMessageText.setTranslationY((float) deltaY);
            this.mLegalMessageText.animate().translationY(0.0f).start();
            if (this.mOnStateChangedListener != null) {
                this.mOnStateChangedListener.animateViewsBelow(deltaY, 0, 150);
            }
            setAnimationDelay(0);
        }
    }

    private void setAnimationDelay(long startDelay) {
        if (VERSION.SDK_INT >= 14) {
            for (View animate : this.mAnimatedChildren) {
                animate.animate().setStartDelay(startDelay);
            }
        }
    }

    private void updateRelativeLayoutParamsToShowExpandedCardView() {
        LayoutParams cardNumberParams = (LayoutParams) this.mCreditCardNumberText.getLayoutParams();
        cardNumberParams.addRule(3, R.id.add_credit_card_title);
        cardNumberParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.wallet_im_credit_card_number_collapsed_left_margin);
        this.mCreditCardNumberText.setLayoutParams(cardNumberParams);
        if (isOcrEnabled()) {
            LayoutParams ocrParams = (LayoutParams) this.mOcrIcon.getLayoutParams();
            ocrParams.addRule(3, R.id.add_credit_card_title);
            this.mOcrIcon.setLayoutParams(ocrParams);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("viewState", this.mViewState);
        outState.putInt("selectedRegionCode", this.mSelectedRegionCode);
        outState.putParcelable("creditCardEntryAction", this.mCreditCardEntryAction);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.expand_icon) {
            transitionToState(5, true);
        } else if (view.getId() == R.id.ocr_icon) {
            AnalyticsUtil.createAndSendClickEvent(this, 1652);
            Fragment current = this;
            while (current.getParentFragment() != null) {
                current = current.getParentFragment();
            }
            AnalyticsUtil.createAndSendImpressionEvent(new SimpleUiNode(1653, null));
            current.startActivityForResult(this.mLaunchOcrIntent, 500);
            enableUi(false);
        } else if (view.getId() == R.id.cvc_hint && getFragmentManager().findFragmentByTag("tagCvcInfoDialog") == null) {
            CvcInfoDialogFragment.newInstance(getThemeResourceId()).show(getFragmentManager(), "tagCvcInfoDialog");
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            transitionToState(3, true);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 500) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        enableUi(true);
        CreditCardOcrResult ocrResult = CreditCardOcrResult.fromIntent(data);
        boolean validCcNumber = false;
        boolean validExpMonth = false;
        boolean validExpYear = false;
        if (ocrResult != null) {
            int newState = -1;
            String ccNumber = ocrResult.getCardNumber();
            int expMonth = ocrResult.getExpMonth();
            int expYear = ocrResult.getExpYear() % 100;
            validCcNumber = (TextUtils.isEmpty(ccNumber) || this.mCreditCardNumberText == null) ? false : true;
            validExpMonth = expMonth > 0 && expMonth < 13 && this.mExpMonthText != null;
            validExpYear = expYear >= 0 && this.mExpYearText != null;
            if (validCcNumber) {
                if (this.mViewState == 1) {
                    newState = getStateAfterEnteringCardNumberCompleted();
                }
                if (validExpMonth && validExpYear) {
                    newState = 3;
                }
                transitionToState(newState, false);
                this.mCreditCardNumberText.requestFocus();
                this.mCreditCardNumberText.removeTextChangedListener(this.mCreditCardNumberTextWatcher);
                this.mCreditCardNumberText.setText(ccNumber);
                this.mCreditCardNumberText.addTextChangedListener(this.mCreditCardNumberTextWatcher);
                if (validExpMonth && validExpYear) {
                    this.mExpMonthText.removeTextChangedListener(this.mExpDateTextWatcher);
                    this.mExpYearText.removeTextChangedListener(this.mExpDateTextWatcher);
                    this.mExpMonthText.setText(Integer.toString(expMonth));
                    this.mExpYearText.requestFocus();
                    this.mExpYearText.setText(Integer.toString(expYear));
                    this.mExpMonthText.addTextChangedListener(this.mExpDateTextWatcher);
                    this.mExpYearText.addTextChangedListener(this.mExpDateTextWatcher);
                }
            }
        }
        if (this.mCreditCardEntryAction.numOcrAttempts < 0) {
            this.mCreditCardEntryAction.numOcrAttempts = 1;
        } else {
            CreditCardEntryAction creditCardEntryAction = this.mCreditCardEntryAction;
            creditCardEntryAction.numOcrAttempts++;
        }
        this.mCreditCardEntryAction.ocrExitReason = ocrResultCodeToExitReason(resultCode);
        this.mCreditCardEntryAction.panRecognizedByOcr = validCcNumber;
        if (validCcNumber) {
            this.mCreditCardEntryAction.panEntryType = 2;
        }
        if (data != null) {
            this.mCreditCardEntryAction.expDateOcrEnabled = data.getBooleanExtra("com.google.android.gms.ocr.EXP_DATE_RECOGNITION_ENABLED", false);
        }
        CreditCardEntryAction creditCardEntryAction2 = this.mCreditCardEntryAction;
        boolean z = validExpMonth && validExpYear;
        creditCardEntryAction2.expDateRecognizedByOcr = z;
        if (validExpMonth && validExpYear) {
            this.mCreditCardEntryAction.expDateEntryType = 2;
        }
        sendCreditCardEntryActionBackgroundEvent();
    }

    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        notifyFormEvent(6, Bundle.EMPTY);
        this.mCreditCardNumberTextWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (AddCreditCardFragment.this.mCreditCardEntryAction.panEntryType != 1) {
                    AddCreditCardFragment.this.mCreditCardEntryAction.panEntryType = 1;
                    AddCreditCardFragment.this.sendCreditCardEntryActionBackgroundEvent();
                }
            }

            public void afterTextChanged(Editable s) {
            }
        };
        this.mCreditCardNumberText.addTextChangedListener(this.mCreditCardNumberTextWatcher);
        this.mExpDateTextWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (AddCreditCardFragment.this.mCreditCardEntryAction.expDateEntryType != 1) {
                    AddCreditCardFragment.this.mCreditCardEntryAction.expDateEntryType = 1;
                    AddCreditCardFragment.this.sendCreditCardEntryActionBackgroundEvent();
                }
            }

            public void afterTextChanged(Editable s) {
            }
        };
        this.mExpMonthText.addTextChangedListener(this.mExpDateTextWatcher);
        this.mExpYearText.addTextChangedListener(this.mExpDateTextWatcher);
    }

    private void transitionToState(int newState, boolean allowAnimation) {
        if (newState > this.mViewState) {
            if (this.mViewState == 0) {
                switch (newState) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        if (isOcrEnabled()) {
                            this.mOcrIcon.setVisibility(0);
                        }
                        this.mExpCvcLayout.setVisibility(4);
                        this.mCreditCardNumberText.setNextFocusDownId(-1);
                        this.mCreditCardNumberText.setAutoAdvancedListener(new AutoAdvancedListener() {
                            public void onAutoAdvanced() {
                                if (AddCreditCardFragment.this.mViewState == 1) {
                                    AddCreditCardFragment.this.transitionToState(AddCreditCardFragment.getStateAfterEnteringCardNumberCompleted(), true);
                                }
                            }
                        });
                        if (this.mOnStateChangedListener != null) {
                            this.mOnStateChangedListener.showViewsBelow(false, false, 0, 0, 0);
                            break;
                        }
                        break;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        if (isOcrEnabled()) {
                            this.mOcrIcon.setVisibility(0);
                        }
                        this.mCvcText.setOnFocusChangeListener(this);
                        this.mCvcHintImage.setVisibility(8);
                        if (this.mOnStateChangedListener != null) {
                            this.mOnStateChangedListener.showViewsBelow(false, false, 0, 0, 0);
                            break;
                        }
                        break;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        if (isOcrEnabled()) {
                            this.mOcrIcon.setVisibility(0);
                        }
                        if (this.mOnStateChangedListener != null) {
                            this.mOnStateChangedListener.showViewsBelow(false, false, 0, 0, 0);
                            break;
                        }
                        break;
                    case R.styleable.WalletImFormEditText_required /*4*/:
                        if (VERSION.SDK_INT < 14 && isOcrEnabled()) {
                            this.mOcrIcon.setVisibility(0);
                        }
                        switchToShowingAddress(false);
                        break;
                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                        this.mAddressEntryFragmentHolder.setVisibility(0);
                        this.mLegalMessageText.setVisibility(0);
                        switchToExpandedState(false);
                        if (this.mOnStateChangedListener != null) {
                            this.mOnStateChangedListener.showViewsBelow(true, false, 0, 0, 0);
                            break;
                        }
                        break;
                }
            } else if (this.mViewState == 1 && (newState == 3 || newState == 2)) {
                if (newState == 2) {
                    this.mCvcHintImage.setVisibility(8);
                    this.mCvcText.setOnFocusChangeListener(this);
                }
                if (allowAnimation) {
                    WalletUiUtils.animateViewAppearing(this.mExpCvcLayout, -this.mCreditCardNumberText.getHeight(), 0);
                } else {
                    this.mExpCvcLayout.setVisibility(0);
                }
                this.mCreditCardNumberText.setNextFocusDownId(R.id.exp_month);
            } else if (this.mViewState == 4) {
                if (newState == 5) {
                    switchToExpandedState(allowAnimation);
                }
            } else if (newState == 4) {
                this.mCreditCardNumberConcealedText.setText(this.mCreditCardNumberText.getConcealedCardNumber());
                switchToShowingAddress(true);
            } else if (this.mViewState == 2 && newState == 3 && this.mCvcHintImage.getVisibility() != 0) {
                WalletUiUtils.animateViewAppearing(this.mCvcHintImage, 0, 0);
            }
            this.mViewState = newState;
            setCvcTextNextFocusDown();
            notifyFormEvent(1, Bundle.EMPTY);
        }
    }

    private boolean isOcrEnabled() {
        return this.mLaunchOcrIntent != null;
    }

    public void onCreditCardTypeChanged(CardType newType) {
        this.mCreditCardImagesView.setCreditCardType(newType);
        int cvcLength = newType != null ? newType.cvcLength : 4;
        this.mCvcText.setFilters(new InputFilter[]{new LengthFilter(cvcLength)});
        if (!TextUtils.isEmpty(this.mCvcText.getText())) {
            this.mCvcChecker.validate();
        }
    }

    public CreditCardFormValue getCreditCardFormValue() {
        int expMonth;
        int expYear;
        String ccNumber = this.mCreditCardNumberText.getCardNumber();
        String cvc = PaymentUtils.removeNonNumericDigits(this.mCvcText.getText().toString());
        String lastFourDigits = ccNumber.substring(Math.max(0, ccNumber.length() - 4));
        CardType cardType = this.mCreditCardNumberText.getCardType();
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
        CreditCardFormValue creditCard = new CreditCardFormValue();
        creditCard.expirationMonth = expMonth;
        creditCard.expirationYear = expYear;
        if (cardType != null) {
            creditCard.typeToken = cardType.typeToken;
        }
        creditCard.lastFourDigits = lastFourDigits;
        creditCard.cardNumber = ccNumber;
        creditCard.cvc = cvc;
        creditCard.billingAddress = this.mAddressEntryFragment.getAddressFormValue();
        if (this.mLegalMessage != null) {
            creditCard.legalDocData = this.mLegalMessage.opaqueData;
        }
        return creditCard;
    }

    public void onRegionCodeSelected(int regionCode, int senderId) {
        if (this.mSelectedRegionCode != regionCode) {
            this.mSelectedRegionCode = regionCode;
            this.mLegalMessage = PaymentUtils.findLegalMessageByCountry(((CreditCardForm) this.mFormProto).legalMessages, RegionCode.toCountryCode(regionCode));
            this.mLegalMessageText.setInfoMessage(this.mLegalMessage == null ? null : this.mLegalMessage.messageText);
            notifyFormEvent(6, Bundle.EMPTY);
        }
    }

    public void onHeightOffsetChanged(float heightOffset) {
        this.mLegalMessageText.setTranslationY(this.mAddressEntryFragmentHolder.getTranslationY() + heightOffset);
    }

    public InstrumentManagerUiElement getUiElement() {
        return this.mUiElement;
    }

    public List<UiNode> getChildren() {
        ArrayList<UiNode> children = new ArrayList();
        if (isOcrEnabled()) {
            children.add(new SimpleUiNode(1652, this));
        }
        if (this.mLegalMessage != null) {
            children.add(this.mLegalMessageText);
        }
        return children;
    }

    public boolean shouldShowButtonBarExpandButton() {
        return !this.mLegalMessageText.isExpanded();
    }

    public void onButtonBarExpandButtonClicked() {
        this.mLegalMessageText.expand(true);
    }

    public String getButtonBarExpandButtonText() {
        return this.mLegalMessageText.getExpandLabel();
    }

    public void onClick(View view, String url) {
        if (view == this.mLegalMessageText && getFragmentManager().findFragmentByTag("tagTosWebViewDialog") == null) {
            WebViewDialogFragment.newInstance(url, getThemeResourceId()).show(getFragmentManager(), "tagTosWebViewDialog");
        }
    }

    public void setOnStateChangedListener(OnAddCreditCardFragmentStateChangedListener listener) {
        this.mOnStateChangedListener = listener;
    }

    private void sendCreditCardEntryActionBackgroundEvent() {
        Bundle eventDetails = new Bundle();
        eventDetails.putInt("FormEventListener.EXTRA_BACKGROUND_EVENT_TYPE", 770);
        eventDetails.putParcelable("FormEventListener.EXTRA_BACKGROUND_EVENT_DATA", this.mCreditCardEntryAction);
        notifyFormEvent(7, eventDetails);
    }

    private static int ocrResultCodeToExitReason(int resultCode) {
        switch (resultCode) {
            case -1:
                return 1;
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case 10007:
                return 3;
            case 10001:
            case 10004:
                return 2;
            case 10003:
            case 10005:
            case 10006:
            case 10009:
                return 4;
            default:
                return 0;
        }
    }

    public void onAddressFieldsShown(boolean shown) {
        this.mAddressFieldsShown = shown;
        setCvcTextNextFocusDown();
    }

    private void setCvcTextNextFocusDown() {
        FormEditText formEditText = this.mCvcText;
        int i = (this.mViewState < 4 || !this.mAddressFieldsShown) ? -1 : R.id.address_field_recipient;
        formEditText.setNextFocusDownId(i);
    }
}
