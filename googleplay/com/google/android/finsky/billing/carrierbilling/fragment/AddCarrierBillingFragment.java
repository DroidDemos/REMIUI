package com.google.android.finsky.billing.carrierbilling.fragment;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.android.i18n.addressinput.AddressData;
import com.android.i18n.addressinput.AddressData.Builder;
import com.android.i18n.addressinput.AddressWidget;
import com.android.vending.R;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.InstrumentActivity;
import com.google.android.finsky.config.PurchaseAuth;
import com.google.android.finsky.fragments.LoggingFragment;
import com.google.android.finsky.setup.SetupWizardNavBar;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.layout.PlayActionButton;

public class AddCarrierBillingFragment extends LoggingFragment implements OnClickListener {
    private Button mAcceptButton;
    private int mBillingUiMode;
    private Button mDeclineButton;
    private ImageButton mEditAddressButton;
    private AddCarrierBillingResultListener mListener;
    private SetupWizardNavBar mSetupWizardNavBar;
    private RadioGroup mSetupWizardTosSelection;
    private String mTosUrl;

    public interface AddCarrierBillingResultListener {

        public enum AddResult {
            SUCCESS,
            CANCELED,
            EDIT_ADDRESS
        }

        void onAddCarrierBillingResult(AddResult addResult);
    }

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type;

        static {
            $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type = new int[Type.values().length];
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type[Type.ADDRESS_SNIPPET.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type[Type.ADDRESS_SNIPPET_AND_TOS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type[Type.FULL_ADDRESS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type[Type.FULL_ADDRESS_AND_TOS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type[Type.MINIMAL_ADDRESS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type[Type.MINIMAL_ADDRESS_AND_TOS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type[Type.TOS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum Type {
        ADDRESS_SNIPPET,
        ADDRESS_SNIPPET_AND_TOS,
        FULL_ADDRESS,
        FULL_ADDRESS_AND_TOS,
        MINIMAL_ADDRESS,
        MINIMAL_ADDRESS_AND_TOS,
        TOS
    }

    public static AddCarrierBillingFragment newInstance(Type type, SubscriberInfo editedAddress, String tosUrl, String snippet, String carrierName, String accountName, int mode) {
        AddCarrierBillingFragment fragment = new AddCarrierBillingFragment();
        Bundle args = new Bundle();
        args.putString("type", type.name());
        args.putParcelable("prefill_address", editedAddress);
        args.putString("prefill_snippet", snippet);
        args.putString("tos_url", tosUrl);
        args.putString("carrier_name", carrierName);
        args.putString("authAccount", accountName);
        args.putInt("ui_mode", mode);
        fragment.setArguments(args);
        return fragment;
    }

    protected int getPlayStoreUiElementType() {
        return this.mBillingUiMode == 0 ? 843 : 895;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        setTosUrl(args.getString("tos_url"));
        this.mBillingUiMode = args.getInt("ui_mode");
        View v = inflater.inflate(this.mBillingUiMode == 0 ? R.layout.carrier_tos_and_address : R.layout.setup_wizard_carrier_tos_and_address, container, false);
        this.mSetupWizardNavBar = ((InstrumentActivity) getActivity()).getSetupWizardNavBar();
        if (this.mSetupWizardNavBar != null) {
            this.mSetupWizardTosSelection = (RadioGroup) v.findViewById(R.id.tos_radio_group);
            this.mSetupWizardNavBar.getNextButton().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (AddCarrierBillingFragment.this.mSetupWizardTosSelection.getVisibility() == 8 || AddCarrierBillingFragment.this.mSetupWizardTosSelection.getCheckedRadioButtonId() == R.id.tos_accept) {
                        AddCarrierBillingFragment.this.handleTosAccepted();
                    } else {
                        AddCarrierBillingFragment.this.handleTosDeclined();
                    }
                }
            });
        } else {
            this.mAcceptButton = (Button) v.findViewById(R.id.positive_button);
            this.mDeclineButton = (Button) v.findViewById(R.id.negative_button);
            if (this.mAcceptButton instanceof PlayActionButton) {
                ((PlayActionButton) this.mAcceptButton).configure(3, (int) R.string.accept, (OnClickListener) this);
            } else {
                this.mAcceptButton.setOnClickListener(this);
                this.mAcceptButton.setText(R.string.accept);
            }
            if (this.mDeclineButton instanceof PlayActionButton) {
                ((PlayActionButton) this.mDeclineButton).configure(0, (int) R.string.decline, (OnClickListener) this);
            } else {
                this.mDeclineButton.setOnClickListener(this);
                this.mDeclineButton.setText(R.string.decline);
            }
        }
        this.mEditAddressButton = (ImageButton) v.findViewById(R.id.address_edit_button);
        this.mEditAddressButton.setOnClickListener(this);
        setUpViewForType(v, Type.valueOf(args.getString("type")), (SubscriberInfo) args.getParcelable("prefill_address"), args.getString("prefill_snippet"), args.getString("carrier_name"), args.getString("authAccount"));
        return v;
    }

    private void setTosUrl(String tosUrl) {
        if (!TextUtils.isEmpty(tosUrl)) {
            String localeReplacement = getString(R.string.tos_locale_replacement);
            if (!TextUtils.isEmpty(localeReplacement)) {
                tosUrl = tosUrl.replace("%locale%", localeReplacement);
            }
            this.mTosUrl = BillingUtils.replaceLocale(tosUrl);
        }
    }

    public void setOnResultListener(AddCarrierBillingResultListener listener) {
        this.mListener = listener;
    }

    private void setUpViewForType(View view, Type type, SubscriberInfo prefillAddress, String snippet, String carrierName, String accountName) {
        switch (AnonymousClass2.$SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type[type.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                showAddressSection(view, true);
                setAddressToSnippet(view, snippet, carrierName);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                showAddressSection(view, true);
                setAddressToSnippet(view, snippet, carrierName);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                showAddressSection(view, true);
                setAddressToFull(view, prefillAddress);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                showAddressSection(view, true);
                setAddressToFull(view, prefillAddress);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                showAddressSection(view, true);
                setAddressToMinimalAddress(view, prefillAddress);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                showAddressSection(view, true);
                setAddressToMinimalAddress(view, prefillAddress);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                showAddressSection(view, false);
                break;
            default:
                FinskyLog.d("Unexpected type " + type, new Object[0]);
                break;
        }
        TextView tosFooter = (TextView) view.findViewById(R.id.tos_footer);
        if (this.mTosUrl != null) {
            tosFooter.setText(Html.fromHtml(getString(R.string.dcb_tos_acceptance_footer_html, getString(R.string.accept), this.mTosUrl, carrierName)));
            tosFooter.setMovementMethod(LinkMovementMethod.getInstance());
            tosFooter.setLinkTextColor(tosFooter.getTextColors());
            tosFooter.setVisibility(0);
            if (this.mSetupWizardTosSelection != null) {
                this.mSetupWizardTosSelection.setVisibility(0);
            }
        } else {
            tosFooter.setVisibility(8);
            if (this.mSetupWizardTosSelection != null) {
                this.mSetupWizardTosSelection.setVisibility(8);
            }
        }
        ((TextView) view.findViewById(R.id.addinstrument_introduction)).setText(getString(R.string.add_carrier_billing_introduction, carrierName));
        TextView passwordInfo = (TextView) view.findViewById(R.id.addinstrument_password_info);
        boolean showPasswordProtectOffMessage = true;
        if (TextUtils.isEmpty(accountName)) {
            FinskyLog.wtf("Should have accountName available.", new Object[0]);
        } else {
            showPasswordProtectOffMessage = PurchaseAuth.getPurchaseAuthType(accountName) != 2;
        }
        if (showPasswordProtectOffMessage) {
            passwordInfo.setText(R.string.password_protect_off);
        } else {
            passwordInfo.setText(R.string.password_protect_on);
        }
    }

    private void setAddressToSnippet(View view, String snippet, String carrierName) {
        ((TextView) view.findViewById(R.id.billing_information_description)).setText(getString(R.string.billing_information_snippet_description, carrierName));
        ((TextView) view.findViewById(R.id.address_display)).setText(snippet);
        if (CarrierBillingUtils.isDcb30(BillingLocator.getCarrierBillingStorage())) {
            showPhoneNumber((TextView) view.findViewById(R.id.phone_number_display), BillingLocator.getLine1NumberFromTelephony());
            return;
        }
        ((TextView) view.findViewById(R.id.phone_number_display)).setVisibility(8);
    }

    private void setAddressToMinimalAddress(View view, SubscriberInfo subscriberInfo) {
        ((TextView) view.findViewById(R.id.billing_information_description)).setText(getString(R.string.billing_information_description));
        showPhoneNumber((TextView) view.findViewById(R.id.phone_number_display), subscriberInfo.getIdentifier());
    }

    private void setAddressToFull(View view, SubscriberInfo editedAddress) {
        SubscriberInfo subscriberInfo;
        CarrierBillingStorage billingStorage = BillingLocator.getCarrierBillingStorage();
        ((TextView) view.findViewById(R.id.billing_information_description)).setText(getString(R.string.billing_information_description));
        if (editedAddress != null) {
            subscriberInfo = editedAddress;
        } else {
            subscriberInfo = billingStorage.getProvisioning().getSubscriberInfo();
        }
        AddressData addressData = new Builder().setRecipient(subscriberInfo.getName()).setAddressLine1(subscriberInfo.getAddress1()).setAddressLine2(subscriberInfo.getAddress2()).setLocality(subscriberInfo.getCity()).setAdminArea(subscriberInfo.getState()).setPostalCode(subscriberInfo.getPostalCode()).setCountry(subscriberInfo.getCountry()).build();
        TextView addressView = (TextView) view.findViewById(R.id.address_display);
        if (TextUtils.isEmpty(addressData.getRecipient()) && TextUtils.isEmpty(addressData.getAddressLine1()) && TextUtils.isEmpty(addressData.getAddressLine2()) && TextUtils.isEmpty(addressData.getLocality()) && TextUtils.isEmpty(addressData.getAdministrativeArea()) && TextUtils.isEmpty(addressData.getPostalCode()) && TextUtils.isEmpty(addressData.getPostalCountry())) {
            addressView.setVisibility(8);
        } else {
            addressView.setVisibility(0);
            addressView.setText(TextUtils.join("\n", AddressWidget.getFullEnvelopeAddress(addressData, getActivity().getBaseContext())));
        }
        String phoneNumber = subscriberInfo.getIdentifier();
        if (Utils.isEmptyOrSpaces(phoneNumber)) {
            phoneNumber = PhoneNumberUtils.formatNumber(BillingLocator.getLine1NumberFromTelephony());
        }
        showPhoneNumber((TextView) view.findViewById(R.id.phone_number_display), phoneNumber);
    }

    private void showPhoneNumber(TextView phoneNumberView, String phoneNumber) {
        if (Utils.isEmptyOrSpaces(phoneNumber)) {
            phoneNumberView.setVisibility(8);
            return;
        }
        phoneNumberView.setVisibility(0);
        phoneNumberView.setText(phoneNumber);
    }

    private void showAddressSection(View view, boolean visible) {
        int visibility = visible ? 0 : 8;
        view.findViewById(R.id.billing_information_description).setVisibility(visibility);
        view.findViewById(R.id.address_edit_button).setVisibility(visibility);
        view.findViewById(R.id.address_display).setVisibility(visibility);
        view.findViewById(R.id.phone_number_display).setVisibility(visibility);
    }

    public void enableUi(boolean enabled) {
        if (this.mSetupWizardNavBar != null || this.mAcceptButton != null) {
            if (this.mSetupWizardNavBar != null) {
                this.mSetupWizardNavBar.getNextButton().setEnabled(enabled);
                this.mSetupWizardNavBar.getBackButton().setEnabled(enabled);
            } else {
                this.mAcceptButton.setEnabled(enabled);
                this.mDeclineButton.setEnabled(enabled);
            }
            this.mEditAddressButton.setEnabled(enabled);
        }
    }

    public void onClick(View v) {
        if (v == this.mAcceptButton) {
            handleTosAccepted();
        } else if (v == this.mDeclineButton) {
            handleTosDeclined();
        } else if (v == this.mEditAddressButton) {
            logClickEvent(844);
            this.mListener.onAddCarrierBillingResult(AddResult.EDIT_ADDRESS);
        }
    }

    private void handleTosDeclined() {
        logClickEvent(849);
        this.mListener.onAddCarrierBillingResult(AddResult.CANCELED);
    }

    private void handleTosAccepted() {
        logClickEvent(848);
        this.mListener.onAddCarrierBillingResult(AddResult.SUCCESS);
    }
}
