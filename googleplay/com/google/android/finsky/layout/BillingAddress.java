package com.google.android.finsky.layout;

import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.i18n.addressinput.AddressField;
import com.android.i18n.addressinput.AddressProblemType;
import com.android.i18n.addressinput.AddressProblems;
import com.android.i18n.addressinput.AddressWidget;
import com.android.i18n.addressinput.FormOptions;
import com.android.i18n.addressinput.FormOptions.Builder;
import com.android.vending.R;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.AddressMetadataCacheManager;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.config.G;
import com.google.android.finsky.placesapi.AdrMicroformatParser;
import com.google.android.finsky.placesapi.PlaceAutocompletePrediction;
import com.google.android.finsky.placesapi.PlaceAutocompleteResponse;
import com.google.android.finsky.placesapi.PlaceDetailResponse;
import com.google.android.finsky.placesapi.PlacesService;
import com.google.android.finsky.placesapi.WhitelistedCountriesFlagParser;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.ChallengeProto.InputValidationError;
import com.google.android.finsky.protos.CommonDevice.BillingAddressSpec;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import com.google.android.finsky.utils.CachedLocationAccess;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.Utils;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

public class BillingAddress extends LinearLayout implements OnHeightOffsetChangedListener {
    private static String KEY_ADDRESS_SPEC;
    private static String KEY_SELECTED_COUNTRY;
    private AddressFieldsLayout mAddressPlaceholder;
    private BillingAddressSpec mAddressSpec;
    private AddressWidget mAddressWidget;
    private List<Country> mCountries;
    private BillingCountryChangeListener mCountryChangeListener;
    private Spinner mCountrySpinner;
    private boolean mCountrySpinnerSelectionSet;
    private EditText mEmailAddress;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mNameEntry;
    private OnHeightOffsetChangedListener mParentListener;
    private EditText mPhoneNumber;
    private Country mSelectedCountry;
    private AddressSuggestionProviderImpl mSuggestionProvider;
    private WhitelistedCountriesFlagParser mWhitelistedCountries;

    public interface BillingCountryChangeListener {
        void onBillingCountryChanged(Country country);
    }

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$i18n$addressinput$AddressField;

        static {
            $SwitchMap$com$android$i18n$addressinput$AddressField = new int[AddressField.values().length];
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADMIN_AREA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.LOCALITY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.STREET_ADDRESS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADDRESS_LINE_1.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADDRESS_LINE_2.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.DEPENDENT_LOCALITY.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.POSTAL_CODE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.COUNTRY.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    private class AddressSuggestionProviderImpl implements ErrorListener, Listener<PlaceDetailResponse>, AddressSuggestionProvider {
        private String mCountry;
        private Location mLocation;
        private PlacesService mPlacesService;
        private RequestQueue mRequestQueue;

        AddressSuggestionProviderImpl(PlacesService placesService, RequestQueue requestQueue, Location location) {
            this.mPlacesService = placesService;
            this.mRequestQueue = requestQueue;
            this.mLocation = location;
        }

        public void setCountry(String country) {
            this.mCountry = country;
        }

        public List<PlaceAutocompletePrediction> getSuggestions(CharSequence input) {
            if (input == null || this.mCountry == null) {
                return null;
            }
            RequestFuture<PlaceAutocompleteResponse> future = RequestFuture.newFuture();
            this.mRequestQueue.add(this.mPlacesService.createAutocompleteRequest(input.toString(), this.mLocation, this.mCountry, future, future));
            try {
                return ((PlaceAutocompleteResponse) future.get()).getPredictions();
            } catch (ExecutionException e) {
                return null;
            } catch (InterruptedException e2) {
                return null;
            }
        }

        public void onSuggestionAccepted(PlaceAutocompletePrediction suggestion) {
            this.mRequestQueue.add(this.mPlacesService.createPlaceDetailsRequest(suggestion.getReference(), this, this));
            BillingAddress.this.mAddressWidget.showUpperRightProgressBar();
        }

        public void onResponse(PlaceDetailResponse response) {
            BillingAddress.this.mAddressWidget.hideUpperRightProgressBar();
            BillingAddress.this.mAddressWidget.setAddressFromSuggestion(BillingUtils.addressDataFromInstrumentAddress(response.getAddress()));
        }

        public void onErrorResponse(VolleyError error) {
            BillingAddress.this.mAddressWidget.hideUpperRightProgressBar();
        }
    }

    public static class CountrySpinnerItem {
        final Country mCountry;

        public CountrySpinnerItem(Country country) {
            this.mCountry = country;
        }

        public String toString() {
            return this.mCountry.countryName;
        }
    }

    public interface InitializationStateListener {
    }

    static {
        KEY_ADDRESS_SPEC = "address_spec";
        KEY_SELECTED_COUNTRY = "selected_country";
    }

    public BillingAddress(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCountrySpinnerSelectionSet = false;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.billing_address_fields, this, true);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mNameEntry = (EditText) findViewById(R.id.name_entry);
        this.mFirstName = (EditText) findViewById(R.id.first_name);
        this.mLastName = (EditText) findViewById(R.id.last_name);
        this.mEmailAddress = (EditText) findViewById(R.id.email_address);
        this.mCountrySpinner = (Spinner) findViewById(R.id.country);
        this.mPhoneNumber = (EditText) findViewById(R.id.phone_number);
        this.mAddressPlaceholder = (AddressFieldsLayout) findViewById(R.id.address_widget);
        this.mSuggestionProvider = new AddressSuggestionProviderImpl(new PlacesService((String) G.placesApiKey.get(), Locale.getDefault().getLanguage(), new AdrMicroformatParser(getContext())), FinskyApp.get().getRequestQueue(), new CachedLocationAccess().getCachedLocation(getContext()));
        this.mWhitelistedCountries = new WhitelistedCountriesFlagParser(getContext());
    }

    public void setEnabled(boolean enabled) {
        this.mNameEntry.setEnabled(enabled);
        this.mFirstName.setEnabled(enabled);
        this.mLastName.setEnabled(enabled);
        this.mEmailAddress.setEnabled(enabled);
        this.mCountrySpinner.setEnabled(enabled);
        this.mAddressWidget.setEnabled(enabled);
        this.mPhoneNumber.setEnabled(enabled);
    }

    public void setInitializationStateListener(InitializationStateListener initializationStateListener) {
    }

    public void saveInstanceState(Bundle outState) {
        outState.putParcelable(KEY_ADDRESS_SPEC, ParcelableProto.forProto(this.mAddressSpec));
        outState.putParcelable(KEY_SELECTED_COUNTRY, ParcelableProto.forProto(this.mSelectedCountry));
        if (this.mAddressWidget != null) {
            this.mAddressWidget.saveInstanceState(outState);
        }
    }

    public void restoreInstanceState(Bundle inState) {
        BillingAddressSpec addressSpec = (BillingAddressSpec) ParcelableProto.getProtoFromBundle(inState, KEY_ADDRESS_SPEC);
        if (addressSpec != null) {
            this.mAddressSpec = addressSpec;
            this.mSelectedCountry = (Country) ParcelableProto.getProtoFromBundle(inState, KEY_SELECTED_COUNTRY);
            setAddressSpec(this.mSelectedCountry, this.mAddressSpec);
            this.mAddressWidget.restoreInstanceState(inState);
        }
    }

    public void setBillingCountryChangeListener(BillingCountryChangeListener listener) {
        this.mCountryChangeListener = listener;
    }

    public void setDefaultName(String name) {
        if (this.mNameEntry.getText().length() == 0) {
            this.mNameEntry.setText(name);
        }
    }

    public void setNameInputHint(int nameHintId) {
        this.mNameEntry.setHint(nameHintId);
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber) && TextUtils.isEmpty(this.mPhoneNumber.getText())) {
            this.mPhoneNumber.setText(PhoneNumberUtils.formatNumber(phoneNumber));
        }
    }

    public void setBillingCountries(List<Country> countries) {
        this.mCountries = countries;
        this.mCountrySpinner = (Spinner) findViewById(R.id.country);
        this.mCountrySpinner.setPrompt(getResources().getText(R.string.select_location));
        this.mCountrySpinner.setOnItemSelectedListener(null);
        ArrayAdapter<CountrySpinnerItem> countrySpinnerAdapter = new ArrayAdapter(getContext(), 17367048);
        countrySpinnerAdapter.setDropDownViewResource(17367049);
        for (Country country : this.mCountries) {
            countrySpinnerAdapter.add(new CountrySpinnerItem(country));
        }
        this.mCountrySpinner.setAdapter(countrySpinnerAdapter);
        this.mCountrySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Country country = (Country) BillingAddress.this.mCountries.get(position);
                if ((BillingAddress.this.mSelectedCountry == null || !BillingAddress.this.mSelectedCountry.countryCode.equals(country.countryCode)) && BillingAddress.this.mCountryChangeListener != null) {
                    BillingAddress.this.mCountryChangeListener.onBillingCountryChanged(country);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                onItemSelected(parent, null, 0, 0);
            }
        });
    }

    public void setAddressSpec(Country selectedCountry, BillingAddressSpec spec) {
        setAddressSpec(selectedCountry, spec, null);
    }

    public void setAddressSpec(Country selectedCountry, BillingAddressSpec spec, Address savedAddress) {
        if (!this.mCountrySpinnerSelectionSet) {
            int selectedPosition = -1;
            int position = 0;
            for (Country country : this.mCountries) {
                if (selectedCountry.countryCode.equals(country.countryCode)) {
                    selectedPosition = position;
                }
                position++;
            }
            if (selectedPosition >= 0) {
                this.mCountrySpinner.setSelection(selectedPosition);
                this.mCountrySpinnerSelectionSet = true;
            }
            this.mCountrySpinner.setVisibility(0);
        }
        this.mSelectedCountry = selectedCountry;
        this.mAddressSpec = spec;
        if (spec.requiredField.length == 0) {
            spec.requiredField = populatedRequiredFieldsFromAddressType(spec.billingAddressType);
        }
        FormOptions addressFormOptions = optionsFromInputFieldList(spec.requiredField);
        boolean hideAddrPhone = true;
        boolean hideAddrPostalCountry = true;
        for (int i : spec.requiredField) {
            switch (i) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    hideAddrPostalCountry = false;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    hideAddrPhone = false;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    this.mNameEntry.setVisibility(8);
                    this.mFirstName.setVisibility(0);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    this.mNameEntry.setVisibility(8);
                    this.mLastName.setVisibility(0);
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    this.mEmailAddress.setVisibility(0);
                    break;
                default:
                    break;
            }
        }
        if (hideAddrPhone) {
            this.mPhoneNumber.setVisibility(8);
        }
        if (hideAddrPostalCountry) {
            this.mCountrySpinner.setVisibility(8);
        }
        if (this.mAddressWidget == null) {
            this.mAddressWidget = new AddressWidget(getContext(), this.mAddressPlaceholder, addressFormOptions, new AddressMetadataCacheManager(FinskyApp.get().getCache()), this.mSelectedCountry.countryCode);
            this.mAddressWidget.renderForm();
        }
        if (savedAddress != null) {
            if (!TextUtils.isEmpty(savedAddress.name)) {
                this.mNameEntry.setText(savedAddress.name);
            }
            if (!TextUtils.isEmpty(savedAddress.firstName)) {
                this.mFirstName.setText(savedAddress.firstName);
            }
            if (!TextUtils.isEmpty(savedAddress.lastName)) {
                this.mLastName.setText(savedAddress.lastName);
            }
            if (!TextUtils.isEmpty(savedAddress.email)) {
                this.mEmailAddress.setText(savedAddress.email);
            }
            if (!TextUtils.isEmpty(savedAddress.phoneNumber)) {
                this.mPhoneNumber.setText(savedAddress.phoneNumber);
            }
            this.mAddressWidget.renderFormWithSavedAddress(BillingUtils.addressDataFromInstrumentAddress(savedAddress));
        }
        this.mAddressWidget.setFormOptions(addressFormOptions);
        this.mAddressWidget.updateWidgetOnCountryChange(this.mSelectedCountry.countryCode);
        this.mAddressPlaceholder.setOnHeightOffsetChangedListener(this);
        if (this.mWhitelistedCountries.isCountryEnabled(this.mSelectedCountry.countryCode)) {
            this.mSuggestionProvider.setCountry(this.mSelectedCountry.countryCode);
            this.mAddressWidget.setSuggestionProvider(this.mSuggestionProvider);
            return;
        }
        this.mAddressWidget.setSuggestionProvider(null);
    }

    private static int[] populatedRequiredFieldsFromAddressType(int billingAddressType) {
        if (billingAddressType == 1) {
            return new int[]{4, 10, 9, 5, 6, 8, 7, 12};
        }
        int i;
        boolean phoneNumRequired = ((Boolean) G.reducedBillingAddressRequiresPhonenumber.get()).booleanValue();
        if (phoneNumRequired) {
            i = 4;
        } else {
            i = 3;
        }
        int[] fields = new int[i];
        fields[0] = 4;
        fields[1] = 10;
        fields[2] = 9;
        if (!phoneNumRequired) {
            return fields;
        }
        fields[3] = 12;
        return fields;
    }

    private static FormOptions optionsFromInputFieldList(int[] requiredFields) {
        Builder b = new Builder();
        b.hide(AddressField.COUNTRY).hide(AddressField.RECIPIENT).hide(AddressField.ORGANIZATION);
        for (AddressField field : AddressField.values()) {
            boolean hideField = true;
            int addressEnumValue = addressFieldToAddressEnum(field);
            if (addressEnumValue != -1) {
                for (int i : requiredFields) {
                    if (i == addressEnumValue) {
                        hideField = false;
                        break;
                    }
                }
            }
            if (hideField) {
                b.hide(field);
            }
        }
        return b.build();
    }

    private static int addressFieldToAddressEnum(AddressField addressField) {
        switch (AnonymousClass2.$SwitchMap$com$android$i18n$addressinput$AddressField[addressField.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return 8;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return 7;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                return 5;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return 6;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return 11;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return 9;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return 10;
            default:
                return -1;
        }
    }

    public void clearErrorMessage() {
        this.mNameEntry.setError(null);
        this.mFirstName.setError(null);
        this.mLastName.setError(null);
        this.mPhoneNumber.setError(null);
        this.mEmailAddress.setError(null);
        this.mAddressWidget.clearErrorMessage();
    }

    public TextView displayError(InputValidationError error) {
        Context context = getContext();
        String errorMessage = error.errorMessage;
        TextView textView = null;
        AddressField addressField = null;
        switch (error.inputField) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                textView = this.mNameEntry;
                UiUtils.setErrorOnTextView(this.mNameEntry, context.getString(R.string.name), errorMessage);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                addressField = AddressField.ADDRESS_LINE_2;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                addressField = AddressField.LOCALITY;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                addressField = AddressField.ADMIN_AREA;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                addressField = AddressField.POSTAL_CODE;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                addressField = AddressField.COUNTRY;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomControls /*11*/:
                addressField = AddressField.DEPENDENT_LOCALITY;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomGestures /*12*/:
                textView = this.mPhoneNumber;
                UiUtils.setErrorOnTextView(this.mPhoneNumber, context.getString(R.string.phone_number), errorMessage);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_useViewLifecycle /*13*/:
                FinskyLog.d("Input error ADDR_WHOLE_ADDRESS. Displaying at ADDRESS_LINE_1.", new Object[0]);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiMapToolbar /*15*/:
                textView = this.mFirstName;
                UiUtils.setErrorOnTextView(this.mFirstName, context.getString(R.string.first_name), errorMessage);
                break;
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                textView = this.mLastName;
                UiUtils.setErrorOnTextView(this.mLastName, context.getString(R.string.last_name), errorMessage);
                break;
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                textView = this.mEmailAddress;
                UiUtils.setErrorOnTextView(this.mEmailAddress, context.getString(R.string.email_address), errorMessage);
                break;
            default:
                FinskyLog.d("InputValidationError that can't be displayed: type=%d, message=%s", Integer.valueOf(error.inputField), error.errorMessage);
                break;
        }
        addressField = AddressField.ADDRESS_LINE_1;
        if (addressField == null) {
            return textView;
        }
        if (this.mAddressWidget.getViewForField(addressField) != null) {
            this.mAddressWidget.displayErrorMessageForInvalidEntryIn(addressField);
            return textView;
        }
        textView = this.mNameEntry;
        UiUtils.setErrorOnTextView(this.mNameEntry, context.getString(R.string.name), errorMessage);
        return textView;
    }

    private void addressProblemsToInputValidationErrors(AddressProblems addressProblems, List<InputValidationError> validationErrors) {
        for (Entry<AddressField, AddressProblemType> entry : addressProblems.getProblems().entrySet()) {
            int field;
            switch (AnonymousClass2.$SwitchMap$com$android$i18n$addressinput$AddressField[((AddressField) entry.getKey()).ordinal()]) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    field = 8;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    field = 7;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                    field = 5;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    field = 6;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    field = 11;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    field = 9;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    field = 10;
                    break;
                default:
                    field = 13;
                    FinskyLog.w("No equivalent for address widget field: %s", ((Entry) i$.next()).getKey());
                    break;
            }
            validationErrors.add(BillingUtils.createInputValidationError(field));
        }
    }

    public List<InputValidationError> getAddressValidationErrors() {
        List<InputValidationError> validationErrors = Lists.newArrayList();
        addressProblemsToInputValidationErrors(this.mAddressWidget.getAddressProblems(), validationErrors);
        if (this.mNameEntry.getVisibility() == 0 && Utils.isEmptyOrSpaces(this.mNameEntry.getText())) {
            validationErrors.add(BillingUtils.createInputValidationError(4, getContext().getString(R.string.invalid_name)));
        }
        if (this.mFirstName.getVisibility() == 0 && Utils.isEmptyOrSpaces(this.mFirstName.getText())) {
            validationErrors.add(BillingUtils.createInputValidationError(15, getContext().getString(R.string.invalid_name)));
        }
        if (this.mLastName.getVisibility() == 0 && Utils.isEmptyOrSpaces(this.mLastName.getText())) {
            validationErrors.add(BillingUtils.createInputValidationError(16, getContext().getString(R.string.invalid_name)));
        }
        if (this.mPhoneNumber.getVisibility() == 0 && Utils.isEmptyOrSpaces(this.mPhoneNumber.getText())) {
            validationErrors.add(BillingUtils.createInputValidationError(12, getContext().getString(R.string.invalid_phone)));
        }
        if (this.mEmailAddress.getVisibility() == 0 && !validateEmailAddress(this.mEmailAddress.getText())) {
            validationErrors.add(BillingUtils.createInputValidationError(17, getContext().getString(R.string.invalid_email)));
        }
        return validationErrors;
    }

    public Address getAddress() {
        Address billingAddress = BillingUtils.instrumentAddressFromAddressData(this.mAddressWidget.getAddressData(), this.mAddressSpec.requiredField);
        billingAddress.deprecatedIsReduced = isInReducedAddressMode();
        billingAddress.hasDeprecatedIsReduced = true;
        if (this.mPhoneNumber.getVisibility() == 0) {
            billingAddress.phoneNumber = this.mPhoneNumber.getText().toString();
            billingAddress.hasPhoneNumber = true;
        }
        if (this.mNameEntry.getVisibility() == 0) {
            billingAddress.name = this.mNameEntry.getText().toString();
            billingAddress.hasName = true;
        }
        if (this.mFirstName.getVisibility() == 0) {
            billingAddress.firstName = this.mFirstName.getText().toString();
            billingAddress.hasFirstName = true;
        }
        if (this.mLastName.getVisibility() == 0) {
            billingAddress.lastName = this.mLastName.getText().toString();
            billingAddress.hasLastName = true;
        }
        if (this.mEmailAddress.getVisibility() == 0) {
            billingAddress.email = this.mEmailAddress.getText().toString();
            billingAddress.hasEmail = true;
        }
        return billingAddress;
    }

    private static boolean validateEmailAddress(CharSequence emailAddress) {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }

    private boolean isInReducedAddressMode() {
        return this.mAddressSpec.billingAddressType != 1;
    }

    public void setOnHeightOffsetChangedListener(OnHeightOffsetChangedListener listener) {
        this.mParentListener = listener;
    }

    public void onHeightOffsetChanged(float heightOffset) {
        if (VERSION.SDK_INT >= 11) {
            onHeightOffsetChangedHoneycomb(heightOffset);
        }
        if (this.mParentListener != null) {
            this.mParentListener.onHeightOffsetChanged(heightOffset);
        }
    }

    private void onHeightOffsetChangedHoneycomb(float heightOffset) {
        this.mPhoneNumber.setTranslationY(heightOffset);
        this.mEmailAddress.setTranslationY(heightOffset);
    }
}
