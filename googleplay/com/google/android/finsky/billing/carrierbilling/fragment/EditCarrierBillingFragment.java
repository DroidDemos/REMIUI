package com.google.android.finsky.billing.carrierbilling.fragment;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.i18n.addressinput.AddressData;
import com.android.i18n.addressinput.AddressField;
import com.android.i18n.addressinput.AddressWidget;
import com.android.i18n.addressinput.AddressWidget.Listener;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.AddressMetadataCacheManager;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.BillingUtils.AddressMode;
import com.google.android.finsky.billing.carrierbilling.PhoneCarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.PhoneCarrierBillingUtils.AddressInputField;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo.Builder;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.InstrumentActivity;
import com.google.android.finsky.fragments.LoggingFragment;
import com.google.android.finsky.layout.AddressFieldsLayout;
import com.google.android.finsky.setup.SetupWizardNavBar;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.layout.PlayActionButton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class EditCarrierBillingFragment extends LoggingFragment implements OnClickListener {
    private AddressMode mAddressMode;
    private AddressWidget mAddressWidget;
    private int mBillingUiMode;
    private Button mCancelButton;
    private ViewGroup mEditSection;
    private EditCarrierBillingResultListener mListener;
    private TextView mNameView;
    private EditText mPhoneNumberEditView;
    private Button mSaveButton;

    public interface EditCarrierBillingResultListener {
        void onEditCarrierBillingResult(SubscriberInfo subscriberInfo);
    }

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField;

        static {
            $SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField = new int[AddressInputField.values().length];
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField[AddressInputField.ADDR_ADDRESS1.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField[AddressInputField.ADDR_COUNTRY_CODE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField[AddressInputField.ADDR_ADDRESS2.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField[AddressInputField.ADDR_CITY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField[AddressInputField.PERSON_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField[AddressInputField.ADDR_POSTAL_CODE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField[AddressInputField.ADDR_STATE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField[AddressInputField.ADDR_PHONE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public static EditCarrierBillingFragment newInstance(String accountName, AddressMode addressMode, SubscriberInfo prefillAddress, ArrayList<Integer> displayErrorList, int mode) {
        EditCarrierBillingFragment fragment = new EditCarrierBillingFragment();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putParcelable("prefill_address", prefillAddress);
        args.putString("type", addressMode.name());
        args.putIntegerArrayList("highlight_address", displayErrorList);
        args.putInt("ui_mode", mode);
        fragment.setArguments(args);
        return fragment;
    }

    protected int getPlayStoreUiElementType() {
        return this.mBillingUiMode == 0 ? 845 : 896;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        this.mBillingUiMode = getArguments().getInt("ui_mode");
        View v = inflater.inflate(this.mBillingUiMode == 0 ? R.layout.billing_info_edit : R.layout.setup_wizard_billing_info_edit, container, false);
        ViewGroup view = (ViewGroup) v.findViewById(R.id.edit_section);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        initViews(view);
        SetupWizardNavBar setupWizardNavBar = ((InstrumentActivity) getActivity()).getSetupWizardNavBar();
        if (setupWizardNavBar != null) {
            this.mSaveButton = setupWizardNavBar.getNextButton();
            this.mCancelButton = setupWizardNavBar.getBackButton();
        } else {
            this.mSaveButton = (Button) v.findViewById(R.id.positive_button);
            this.mCancelButton = (Button) v.findViewById(R.id.negative_button);
        }
        if (this.mSaveButton instanceof PlayActionButton) {
            ((PlayActionButton) this.mSaveButton).configure(3, (int) R.string.save, (OnClickListener) this);
        } else {
            this.mSaveButton.setOnClickListener(this);
            this.mSaveButton.setText(R.string.save);
        }
        if (this.mCancelButton instanceof PlayActionButton) {
            ((PlayActionButton) this.mCancelButton).configure(0, (int) R.string.cancel, (OnClickListener) this);
        } else {
            this.mCancelButton.setOnClickListener(this);
            this.mCancelButton.setText(R.string.cancel);
        }
        this.mAddressMode = AddressMode.valueOf(args.getString("type"));
        SubscriberInfo prefillAddress = (SubscriberInfo) args.getParcelable("prefill_address");
        if (prefillAddress != null) {
            setupAddressEditView(view, prefillAddress);
        } else {
            setupAddressEditView(view);
        }
        return v;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mAddressWidget.saveInstanceState(outState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            this.mAddressWidget.restoreInstanceState(savedInstanceState);
        }
    }

    private void initViews(ViewGroup view) {
        this.mEditSection = view;
        this.mNameView = (EditText) view.findViewById(R.id.name);
        this.mPhoneNumberEditView = (EditText) view.findViewById(R.id.phone_number_edit);
    }

    public void setOnResultListener(EditCarrierBillingResultListener listener) {
        this.mListener = listener;
    }

    private Collection<AddressInputField> getFormErrors(ArrayList<Integer> highlightField) {
        Collection<AddressInputField> errors = new ArrayList();
        Iterator i$ = highlightField.iterator();
        while (i$.hasNext()) {
            switch (((Integer) i$.next()).intValue()) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                    errors.add(AddressInputField.PERSON_NAME);
                    continue;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    errors.add(AddressInputField.ADDR_ADDRESS2);
                    continue;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    errors.add(AddressInputField.ADDR_CITY);
                    continue;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    errors.add(AddressInputField.ADDR_STATE);
                    continue;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    errors.add(AddressInputField.ADDR_POSTAL_CODE);
                    continue;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    errors.add(AddressInputField.ADDR_COUNTRY_CODE);
                    continue;
                case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    errors.add(AddressInputField.ADDR_PHONE);
                    continue;
                case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    FinskyLog.d("Input error ADDR_WHOLE_ADDRESS. Displaying at ADDRESS_LINE_1.", new Object[0]);
                    break;
                default:
                    FinskyLog.w("InputValidationError that can't be displayed: type=%d", (Integer) i$.next());
                    continue;
            }
            errors.add(AddressInputField.ADDR_ADDRESS1);
        }
        return errors;
    }

    private void setupAddressEditView(View view, SubscriberInfo prefillAddress) {
        showNameView(prefillAddress.getName());
        if (PhoneCarrierBillingUtils.isPhoneNumberRequired(this.mAddressMode, BillingLocator.getCarrierBillingStorage())) {
            String phoneNumber = prefillAddress.getIdentifier();
            if (Utils.isEmptyOrSpaces(phoneNumber)) {
                phoneNumber = PhoneNumberUtils.formatNumber(BillingLocator.getLine1NumberFromTelephony());
            }
            showPhoneNumberEditView(phoneNumber);
        }
        showAddressEditView(view, PhoneCarrierBillingUtils.subscriberInfoToAddressData(prefillAddress));
    }

    private void setupAddressEditView(View view) {
        if (PhoneCarrierBillingUtils.isPhoneNumberRequired(this.mAddressMode, BillingLocator.getCarrierBillingStorage())) {
            showPhoneNumberEditView(PhoneNumberUtils.formatNumber(BillingLocator.getLine1NumberFromTelephony()));
        }
        showAddressEditView(view, null);
    }

    private void showNameView(String name) {
        this.mNameView.setText(name);
    }

    private void showAddressEditView(View view, AddressData addressData) {
        this.mAddressWidget = new AddressWidget(getActivity(), (AddressFieldsLayout) view.findViewById(R.id.address_widget), BillingUtils.getAddressFormOptions(this.mAddressMode), new AddressMetadataCacheManager(FinskyApp.get().getCache()), addressData == null ? null : addressData.getPostalCountry());
        this.mAddressWidget.setListener(new Listener() {
            public void onInitialized() {
                ArrayList<Integer> displayErrorList = EditCarrierBillingFragment.this.getArguments().getIntegerArrayList("highlight_address");
                if (displayErrorList != null) {
                    EditCarrierBillingFragment.this.displayErrors(EditCarrierBillingFragment.this.getFormErrors(displayErrorList));
                }
            }
        });
        this.mAddressWidget.renderFormWithSavedAddress(addressData);
    }

    private void showPhoneNumberEditView(String phoneNumber) {
        this.mPhoneNumberEditView.setVisibility(0);
        if (!Utils.isEmptyOrSpaces(phoneNumber)) {
            this.mPhoneNumberEditView.setText(phoneNumber);
        }
    }

    private void displayErrors(Collection<AddressInputField> errorFields) {
        View topMostView = null;
        int topMostTop = 0;
        for (AddressInputField errorField : errorFields) {
            View currentView = null;
            switch (AnonymousClass2.$SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField[errorField.ordinal()]) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    currentView = this.mAddressWidget.getViewForField(AddressField.ADDRESS_LINE_1);
                    if (currentView != null) {
                        setTextViewError((TextView) currentView, this.mAddressWidget.getNameForField(AddressField.ADDRESS_LINE_1), R.string.invalid_address);
                        break;
                    }
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    currentView = this.mAddressWidget.getViewForField(AddressField.ADDRESS_LINE_2);
                    if (currentView != null) {
                        setTextViewError((TextView) currentView, this.mAddressWidget.getNameForField(AddressField.ADDRESS_LINE_2), R.string.invalid_address);
                        break;
                    }
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                    currentView = this.mAddressWidget.getViewForField(AddressField.LOCALITY);
                    if (currentView != null) {
                        setTextViewError((TextView) currentView, this.mAddressWidget.getNameForField(AddressField.LOCALITY), R.string.invalid_city);
                        break;
                    }
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    currentView = this.mNameView;
                    setTextViewError((TextView) currentView, getString(R.string.name_label), R.string.invalid_name);
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    currentView = this.mAddressWidget.getViewForField(AddressField.POSTAL_CODE);
                    if (currentView != null) {
                        this.mAddressWidget.displayErrorMessageForInvalidEntryIn(AddressField.POSTAL_CODE);
                        break;
                    }
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    currentView = this.mAddressWidget.getViewForField(AddressField.ADMIN_AREA);
                    if (currentView != null) {
                        this.mAddressWidget.displayErrorMessageForInvalidEntryIn(AddressField.ADMIN_AREA);
                        break;
                    }
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    if (this.mPhoneNumberEditView.getVisibility() == 0) {
                        currentView = this.mPhoneNumberEditView;
                        setTextViewError((TextView) currentView, getString(R.string.phone_number), R.string.invalid_phone);
                        break;
                    }
                    break;
                default:
                    continue;
            }
            if (topMostView == null) {
                topMostView = currentView;
                topMostTop = BillingUtils.getViewOffsetToChild(this.mEditSection, currentView);
            } else if (currentView != null) {
                int currentTop = BillingUtils.getViewOffsetToChild(this.mEditSection, currentView);
                if (currentTop < topMostTop) {
                    topMostView = currentView;
                    topMostTop = currentTop;
                }
            }
        }
        if (topMostView != null) {
            topMostView.requestFocus();
        }
    }

    private void setTextViewError(TextView textView, String textViewName, int errorMessageResId) {
        UiUtils.setErrorOnTextView(textView, textViewName, getString(errorMessageResId));
    }

    private SubscriberInfo getReturnAddress() {
        AddressData addressData = this.mAddressWidget.getAddressData();
        Builder builder = new Builder().setName(this.mNameView.getText().toString()).setPostalCode(addressData.getPostalCode()).setCountry(addressData.getPostalCountry());
        if (PhoneCarrierBillingUtils.isPhoneNumberRequired(this.mAddressMode, BillingLocator.getCarrierBillingStorage())) {
            builder.setIdentifier(getPhoneNumber());
        }
        if (this.mAddressMode == AddressMode.FULL_ADDRESS) {
            builder.setAddress1(addressData.getAddressLine1()).setAddress2(addressData.getAddressLine2()).setCity(addressData.getLocality()).setState(addressData.getAdministrativeArea());
        }
        return builder.build();
    }

    private String getPhoneNumber() {
        return this.mPhoneNumberEditView.getText().toString();
    }

    public void onClick(View v) {
        if (v == this.mSaveButton) {
            Collection<AddressInputField> errors = PhoneCarrierBillingUtils.getErrors(this.mNameView.getText().toString(), getPhoneNumber(), this.mAddressWidget.getAddressProblems(), this.mAddressMode);
            logClickEvent(846);
            if (errors.isEmpty()) {
                UiUtils.hideKeyboard(getActivity(), this.mEditSection);
                this.mListener.onEditCarrierBillingResult(getReturnAddress());
                return;
            }
            displayErrors(errors);
        } else if (v == this.mCancelButton) {
            logClickEvent(847);
            this.mListener.onEditCarrierBillingResult(null);
        }
    }
}
