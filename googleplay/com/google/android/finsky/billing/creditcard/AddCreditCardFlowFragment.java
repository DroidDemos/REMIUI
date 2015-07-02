package com.google.android.finsky.billing.creditcard;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.GetBillingCountriesAction;
import com.google.android.finsky.billing.InstrumentFlowFragment;
import com.google.android.finsky.billing.creditcard.CreditCardValidator.InputField;
import com.google.android.finsky.config.G;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.layout.AddCreditCardFields;
import com.google.android.finsky.layout.AddCreditCardFields.OnAllFieldsVisibleListener;
import com.google.android.finsky.layout.BillingAddress;
import com.google.android.finsky.layout.BillingAddress.BillingCountryChangeListener;
import com.google.android.finsky.layout.CreditCardNumberEditText;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.protos.ChallengeProto.InputValidationError;
import com.google.android.finsky.protos.CommonDevice.BillingAddressSpec;
import com.google.android.finsky.protos.CommonDevice.CreditCardInstrument;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import com.google.android.finsky.setup.SetupWizardNavBar;
import com.google.android.finsky.utils.ArrayUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Sets;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.layout.PlayActionButton;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AddCreditCardFlowFragment extends InstrumentFlowFragment implements OnClickListener, Listener, SidecarFragment.Listener, OnAllFieldsVisibleListener, PlayStoreUiElementNode {
    private String mAccountName;
    private BillingAddress mBillingAddress;
    private int mBillingUiMode;
    private Button mCancelButton;
    private String mCardholderName;
    private TextView mCcCvcField;
    private TextView mCcExpMonthField;
    private TextView mCcExpYearField;
    private CreditCardNumberEditText mCcNumberField;
    private List<Country> mCountries;
    private final PlayStoreUiElement mElement;
    private FinskyEventLog mEventLog;
    private AddCreditCardFields mFields;
    private int mLastErrorInstance;
    private ViewGroup mMainView;
    private Button mSaveButton;
    private Bundle mSavedInstanceState;
    private Country mSelectedCountry;
    private AddCreditCardSidecar mSidecar;
    private boolean mUiEnabled;
    private boolean mWidgetsInitialized;

    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$billing$creditcard$CreditCardValidator$InputField;

        static {
            $SwitchMap$com$google$android$finsky$billing$creditcard$CreditCardValidator$InputField = new int[InputField.values().length];
            try {
                $SwitchMap$com$google$android$finsky$billing$creditcard$CreditCardValidator$InputField[InputField.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$creditcard$CreditCardValidator$InputField[InputField.CVC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$creditcard$CreditCardValidator$InputField[InputField.EXP_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$creditcard$CreditCardValidator$InputField[InputField.EXP_YEAR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private class InstrumentAndCredentials {
        private String creditCardNumber;
        private String cvc;
        private Instrument instrument;

        private InstrumentAndCredentials(String creditCardNumber, String cvc, Instrument instrument) {
            this.creditCardNumber = creditCardNumber;
            this.cvc = cvc;
            this.instrument = instrument;
        }
    }

    public AddCreditCardFlowFragment() {
        this.mElement = FinskyEventLog.obtainPlayStoreUiElement(861);
    }

    public static AddCreditCardFlowFragment newInstance(String accountName, String cardholderName, int mode, boolean isLightTheme) {
        AddCreditCardFlowFragment result = new AddCreditCardFlowFragment();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putString("cardholder_name", cardholderName);
        args.putInt("ui_mode", mode);
        args.putBoolean("is_light_theme", isLightTheme);
        result.setArguments(args);
        return result;
    }

    public void onAttach(Activity activity) {
        if (activity instanceof PlayStoreUiElementNode) {
            super.onAttach(activity);
            return;
        }
        throw new IllegalStateException("Activity must implement PlayStoreUiElementNode.");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAccountName = getArguments().getString("authAccount");
        this.mCardholderName = getArguments().getString("cardholder_name");
        this.mBillingUiMode = getArguments().getInt("ui_mode");
        this.mEventLog = FinskyApp.get().getEventLogger(this.mAccountName);
        if (savedInstanceState != null) {
            this.mLastErrorInstance = savedInstanceState.getInt("last_error_instance");
        } else {
            this.mEventLog.logPathImpression(0, this);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        this.mSavedInstanceState = savedInstanceState;
        this.mMainView = (ViewGroup) inflater.inflate(this.mBillingUiMode == 0 ? R.layout.billing_addcreditcard_fragment : R.layout.setup_wizard_addcreditcard_fragment, container, false);
        this.mBillingAddress = (BillingAddress) this.mMainView.findViewById(R.id.billing_address);
        this.mBillingAddress.setNameInputHint(R.string.name_on_card);
        this.mBillingAddress.setBillingCountryChangeListener(new BillingCountryChangeListener() {
            public void onBillingCountryChanged(Country country) {
                AddCreditCardFlowFragment.this.mBillingAddress.setAddressSpec(country, AddCreditCardFlowFragment.this.getAddressSpec(country));
            }
        });
        SetupWizardNavBar setupWizardNavBar = SetupWizardUtils.getNavBarIfPossible(getActivity());
        if (setupWizardNavBar != null) {
            this.mSaveButton = setupWizardNavBar.getNextButton();
            this.mCancelButton = setupWizardNavBar.getBackButton();
        } else {
            this.mSaveButton = (Button) this.mMainView.findViewById(R.id.positive_button);
            this.mCancelButton = (Button) this.mMainView.findViewById(R.id.negative_button);
        }
        if (this.mSaveButton instanceof PlayActionButton) {
            ((PlayActionButton) this.mSaveButton).configure(3, (int) R.string.save, (OnClickListener) this);
        } else {
            this.mSaveButton.setOnClickListener(this);
            this.mSaveButton.setText(R.string.save);
        }
        updateSaveButtonState();
        if (this.mCancelButton instanceof PlayActionButton) {
            ((PlayActionButton) this.mCancelButton).configure(0, (int) R.string.cancel, (OnClickListener) this);
        } else {
            this.mCancelButton.setText(R.string.cancel);
            this.mCancelButton.setOnClickListener(this);
        }
        if (this.mBillingUiMode == 1) {
            this.mMainView.findViewById(R.id.header_space).setVisibility(8);
        }
        this.mFields = (AddCreditCardFields) this.mMainView.findViewById(R.id.addcreditcard_fields);
        this.mFields.setIsLightTheme(getArguments().getBoolean("is_light_theme"));
        this.mFields.setOnAllFieldsVisibleListener(this);
        this.mCcNumberField = (CreditCardNumberEditText) this.mMainView.findViewById(R.id.cc_box);
        this.mCcExpMonthField = (EditText) this.mMainView.findViewById(R.id.expiration_date_entry_1st);
        this.mCcExpYearField = (EditText) this.mMainView.findViewById(R.id.expiration_date_entry_2nd);
        this.mCcCvcField = (EditText) this.mMainView.findViewById(R.id.cvc_entry);
        ((ImageView) this.mMainView.findViewById(R.id.cvc_image)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (AddCreditCardFlowFragment.this.getFragmentManager().findFragmentByTag("cvc_popup") == null) {
                    Builder builder = new Builder();
                    builder.setLayoutId(R.layout.billing_addcreditcard_cvc_popup).setPositiveId(R.string.close);
                    builder.build().show(AddCreditCardFlowFragment.this.getFragmentManager(), "cvc_popup");
                }
            }
        });
        ((TextView) this.mMainView.findViewById(R.id.billing_addcreditcard_privacy_footer)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (AddCreditCardFlowFragment.this.getFragmentManager().findFragmentByTag("privacy_policy") == null) {
                    Builder builder = new Builder();
                    builder.setLayoutId(R.layout.billing_alertwebview).setPositiveId(R.string.close);
                    Bundle argumentsBundle = new Bundle();
                    argumentsBundle.putString("url_key", BillingUtils.replaceLanguageAndRegion((String) G.checkoutPrivacyPolicyUrl.get()));
                    builder.setViewConfiguration(argumentsBundle);
                    builder.build().show(AddCreditCardFlowFragment.this.getFragmentManager(), "privacy_policy");
                }
            }
        });
        this.mBillingAddress.setDefaultName(this.mCardholderName);
        this.mBillingAddress.setNameInputHint(R.string.name_on_card);
        this.mBillingAddress.setPhoneNumber(BillingUtils.getLine1Number(getActivity()));
        loadBillingCountries();
        return this.mMainView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            UiUtils.showKeyboard(getActivity(), this.mCcNumberField);
        }
    }

    public void onStart() {
        super.onStart();
        this.mSidecar = (AddCreditCardSidecar) getFragmentManager().findFragmentByTag("credit_card_sidecar");
        if (this.mSidecar == null) {
            this.mSidecar = AddCreditCardSidecar.newInstance(this.mAccountName);
            getFragmentManager().beginTransaction().add(this.mSidecar, "credit_card_sidecar").commit();
        }
    }

    public void onResume() {
        super.onResume();
        this.mHost.setHostTitle(R.string.add_credit_card);
        this.mSidecar.setListener(this);
    }

    public void onPause() {
        super.onPause();
        this.mSidecar.setListener(null);
    }

    private BillingAddressSpec getAddressSpec(Country country) {
        int i;
        BillingAddressSpec spec = new BillingAddressSpec();
        if (country.allowsReducedBillingAddress) {
            i = 3;
        } else {
            i = 1;
        }
        spec.billingAddressType = i;
        spec.hasBillingAddressType = true;
        return spec;
    }

    private void loadBillingCountries() {
        new GetBillingCountriesAction().run(this.mAccountName, new Runnable() {
            public void run() {
                AddCreditCardFlowFragment.this.onBillingCountriesLoaded();
            }
        });
    }

    private void onBillingCountriesLoaded() {
        if (isAdded()) {
            this.mCountries = BillingLocator.getBillingCountries();
            if (this.mCountries == null || this.mCountries.size() <= 0) {
                FinskyLog.d("BillingCountries not loaded.", new Object[0]);
                Builder builder = new Builder();
                builder.setMessageId(R.string.billing_countries_loading_failed).setPositiveId(R.string.network_retry).setNegativeId(R.string.cancel).setCallback(this, 1, null);
                builder.build().show(getFragmentManager(), "error");
                return;
            }
            setupBillingCountriesWidgets(this.mSavedInstanceState);
            updateSaveButtonState();
        }
    }

    private void clearErrorMessages() {
        this.mBillingAddress.clearErrorMessage();
        this.mCcNumberField.setError(null);
        this.mCcExpMonthField.setError(null);
        this.mCcExpYearField.setError(null);
        this.mCcCvcField.setError(null);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mBillingAddress != null) {
            this.mBillingAddress.saveInstanceState(outState);
        }
        outState.putInt("last_error_instance", this.mLastErrorInstance);
    }

    private void setupBillingCountriesWidgets(Bundle savedInstanceState) {
        if (this.mSelectedCountry == null) {
            this.mSelectedCountry = BillingUtils.findCountry(BillingUtils.getDefaultCountry(getActivity(), null), this.mCountries);
        }
        this.mBillingAddress.setBillingCountries(BillingLocator.getBillingCountries());
        this.mBillingAddress.setAddressSpec(this.mSelectedCountry, getAddressSpec(this.mSelectedCountry));
        if (savedInstanceState != null) {
            this.mBillingAddress.restoreInstanceState(savedInstanceState);
        }
        this.mWidgetsInitialized = true;
        if (this.mSidecar != null) {
            onStateChange(this.mSidecar);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.positive_button:
                this.mEventLog.logClickEvent(862, null, this);
                InstrumentAndCredentials instrumentAndCredentials = getCreditCardOrShowErrors();
                if (instrumentAndCredentials != null) {
                    this.mSidecar.saveCreditCard(instrumentAndCredentials.creditCardNumber, instrumentAndCredentials.cvc, instrumentAndCredentials.instrument);
                    return;
                }
                return;
            case R.id.negative_button:
                this.mEventLog.logClickEvent(863, null, this);
                cancel();
                return;
            default:
                return;
        }
    }

    public void displayErrors(final List<InputValidationError> inputValidationErrors) {
        clearErrorMessages();
        if (!inputValidationErrors.isEmpty()) {
            this.mEventLog.logPathImpression(0, 866, this);
            Runnable displayErrorsRunnable = new Runnable() {
                public void run() {
                    Collection<TextView> errorFields = Lists.newArrayList();
                    int numInputValidationErrors = inputValidationErrors.size();
                    for (int i = 0; i < numInputValidationErrors; i++) {
                        TextView textView = AddCreditCardFlowFragment.this.displayError((InputValidationError) inputValidationErrors.get(i));
                        if (textView != null) {
                            errorFields.add(textView);
                        }
                    }
                    TextView topMostErrorField = (TextView) BillingUtils.getTopMostView(AddCreditCardFlowFragment.this.mMainView, errorFields);
                    if (topMostErrorField != null) {
                        topMostErrorField.requestFocus();
                    }
                }
            };
            if (this.mFields.expandFields()) {
                this.mFields.postDelayed(displayErrorsRunnable, 500);
            } else {
                displayErrorsRunnable.run();
            }
        }
    }

    private TextView displayError(InputValidationError error) {
        String errorMessage = error.errorMessage;
        TextView textView = null;
        int textViewNameId = -1;
        switch (error.inputField) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                textView = this.mCcNumberField;
                textViewNameId = R.string.card_number;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                textView = this.mCcCvcField;
                textViewNameId = R.string.cvc_code;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                textView = this.mCcExpYearField;
                textViewNameId = R.string.name_expiration_year;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                textView = this.mCcExpMonthField;
                textViewNameId = R.string.name_expiration_month;
                break;
        }
        if (textView == null) {
            return this.mBillingAddress.displayError(error);
        }
        UiUtils.setErrorOnTextView(textView, getString(textViewNameId), errorMessage);
        return textView;
    }

    private InstrumentAndCredentials getCreditCardOrShowErrors() {
        String cardNumber = CreditCardNumberFilter.getNumbers(this.mCcNumberField.getText());
        String expMonth = this.mCcExpMonthField.getText().toString();
        String expYear = this.mCcExpYearField.getText().toString();
        String cvc = this.mCcCvcField.getText().toString();
        List<InputValidationError> validationErrors = Lists.newArrayList();
        Set<InputField> creditCardErrors = Sets.newHashSet();
        CreditCardType type = CreditCardValidator.validate(cardNumber, cvc, expMonth, expYear, 2000, creditCardErrors);
        creditCardInputErrorsToInputValidationErrors(creditCardErrors, validationErrors);
        validationErrors.addAll(this.mBillingAddress.getAddressValidationErrors());
        displayErrors(validationErrors);
        if (validationErrors.size() != 0) {
            return null;
        }
        Address billingAddress = this.mBillingAddress.getAddress();
        CreditCardInstrument creditCard = new CreditCardInstrument();
        creditCard.expirationMonth = Integer.parseInt(expMonth);
        creditCard.hasExpirationMonth = true;
        creditCard.expirationYear = Integer.parseInt(expYear) + 2000;
        creditCard.hasExpirationYear = true;
        creditCard.lastDigits = cardNumber.substring(cardNumber.length() - 4);
        creditCard.hasLastDigits = true;
        creditCard.type = type.protobufType;
        creditCard.hasType = true;
        Instrument instrument = new Instrument();
        instrument.billingAddress = billingAddress;
        instrument.creditCard = creditCard;
        return new InstrumentAndCredentials(cardNumber, cvc, instrument);
    }

    private void creditCardInputErrorsToInputValidationErrors(Set<InputField> errors, List<InputValidationError> validationErrors) {
        for (InputField error : errors) {
            switch (AnonymousClass6.$SwitchMap$com$google$android$finsky$billing$creditcard$CreditCardValidator$InputField[error.ordinal()]) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    validationErrors.add(BillingUtils.createInputValidationError(0, getString(R.string.invalid_credit_card_number)));
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    validationErrors.add(BillingUtils.createInputValidationError(1, getString(R.string.invalid_cvc)));
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    validationErrors.add(BillingUtils.createInputValidationError(3, getString(R.string.invalid_expiration_month)));
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                    validationErrors.add(BillingUtils.createInputValidationError(2, getString(R.string.invalid_expiration_year)));
                    break;
                default:
                    FinskyLog.w("Unhandled credit card input field error for: %s", ((InputField) i$.next()).name());
                    break;
            }
        }
    }

    public void onStateChange(SidecarFragment fragment) {
        switch (this.mSidecar.getState()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                this.mHost.hideProgress();
                enableUi(true);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mHost.showProgress(R.string.saving);
                this.mEventLog.logPathImpression(0, 213, this);
                enableUi(false);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mHost.hideProgress();
                finishWithUpdateInstrumentResponse(this.mSidecar.getResponse());
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mHost.hideProgress();
                enableUi(true);
                handleError();
                return;
            default:
                return;
        }
    }

    private void handleError() {
        if (this.mLastErrorInstance == this.mSidecar.getStateInstance()) {
            FinskyLog.d("Already handled error %d, ignoring.", Integer.valueOf(this.mLastErrorInstance));
            return;
        }
        this.mLastErrorInstance = this.mSidecar.getStateInstance();
        switch (this.mSidecar.getSubstate()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                ErrorDialog.show(getFragmentManager(), null, ErrorStrings.get(getActivity(), this.mSidecar.getVolleyError()), false);
                this.mEventLog.logPathImpression(0, 864, this);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                ErrorDialog.show(getFragmentManager(), null, ErrorStrings.get(getActivity(), this.mSidecar.getVolleyError()), false);
                this.mEventLog.logPathImpression(0, 864, this);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                ErrorDialog.show(getFragmentManager(), null, this.mSidecar.getErrorHtml(), false);
                this.mEventLog.logPathImpression(0, 864, this);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                Builder builder = new Builder();
                builder.setMessageHtml(this.mSidecar.getErrorHtml()).setPositiveId(R.string.change).setNegativeId(R.string.skip).setCallback(this, 2, null).setEventLog(865, null, -1, -1, AccountHandler.findAccount(this.mAccountName, FinskyApp.get()));
                builder.build().show(getFragmentManager(), "error_with_choice");
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                displayErrors(ArrayUtils.asList(this.mSidecar.getResponse().errorInputField));
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                fail(getActivity().getString(R.string.auth_required_error));
                return;
            default:
                FinskyLog.wtf("Unknown error code: %d", Integer.valueOf(this.mSidecar.getSubstate()));
                return;
        }
    }

    public void enableUi(boolean enabled) {
        if (this.mWidgetsInitialized) {
            this.mUiEnabled = enabled;
            if (this.mBillingAddress != null) {
                this.mBillingAddress.setEnabled(enabled);
                this.mCcNumberField.setEnabled(enabled);
                this.mCcCvcField.setEnabled(enabled);
                this.mCcExpMonthField.setEnabled(enabled);
                this.mCcExpYearField.setEnabled(enabled);
                updateSaveButtonState();
                this.mCancelButton.setEnabled(enabled);
            }
        }
    }

    public void onAllFieldsVisible() {
        updateSaveButtonState();
    }

    private void updateSaveButtonState() {
        Button button = this.mSaveButton;
        boolean z = this.mUiEnabled && this.mFields.areAllFieldsVisible();
        button.setEnabled(z);
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 1) {
            loadBillingCountries();
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        cancel();
    }

    public boolean canGoBack() {
        return this.mSidecar.getState() == 0;
    }

    public void back() {
        cancel();
    }

    protected void finishWithUpdateInstrumentResponse(UpdateInstrumentResponse response) {
        super.finishWithUpdateInstrumentResponse(response);
    }

    public void cancel() {
        super.cancel();
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return (PlayStoreUiElementNode) getActivity();
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        throw new UnsupportedOperationException("Unwanted children.");
    }
}
