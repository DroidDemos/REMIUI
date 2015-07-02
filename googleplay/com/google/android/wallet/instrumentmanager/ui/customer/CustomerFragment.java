package com.google.android.wallet.instrumentmanager.ui.customer;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.analytics.SimpleUiNode;
import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.common.address.AddressUtils;
import com.google.android.wallet.instrumentmanager.common.address.RegionCode;
import com.google.android.wallet.instrumentmanager.common.util.ArrayUtils;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.ui.address.AddressEntryFragment;
import com.google.android.wallet.instrumentmanager.ui.common.ClickSpan.OnClickListener;
import com.google.android.wallet.instrumentmanager.ui.common.Form;
import com.google.android.wallet.instrumentmanager.ui.common.FormFragment;
import com.google.android.wallet.instrumentmanager.ui.common.InfoMessageTextView;
import com.google.android.wallet.instrumentmanager.ui.common.RegionCodeSelector.OnRegionCodeSelectedListener;
import com.google.android.wallet.instrumentmanager.ui.common.RegionCodeView;
import com.google.android.wallet.instrumentmanager.ui.common.WalletUiUtils;
import com.google.android.wallet.instrumentmanager.ui.common.WebViewDialogFragment;
import com.google.android.wallet.instrumentmanager.ui.creditcard.AddCreditCardFragment;
import com.google.android.wallet.instrumentmanager.ui.creditcard.AddCreditCardFragment.OnAddCreditCardFragmentStateChangedListener;
import com.google.android.wallet.instrumentmanager.ui.tax.TaxInfoEntryFragment;
import com.google.android.wallet.instrumentmanager.ui.usernamepassword.UsernamePasswordFragment;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.FormFieldMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.components.customer.CustomerFormOuterClass.CustomerForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.customer.CustomerFormOuterClass.CustomerFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.InstrumentFormOuterClass.InstrumentFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.legal.LegalMessageOuterClass.LegalMessage;
import java.util.ArrayList;
import java.util.List;

public class CustomerFragment extends FormFragment<CustomerForm> implements UiNode, OnClickListener, OnRegionCodeSelectedListener, OnAddCreditCardFragmentStateChangedListener {
    private LegalMessage mLegalMessage;
    InfoMessageTextView mLegalMessageText;
    RegionCodeView mRegionCodeView;
    private int[] mRegionCodes;
    int mSelectedRegionCode;
    ArrayList<Form> mSubForms;
    private final InstrumentManagerUiElement mUiElement;

    public CustomerFragment() {
        this.mUiElement = new InstrumentManagerUiElement(1665);
        this.mSelectedRegionCode = 0;
        this.mSubForms = new ArrayList();
    }

    public static CustomerFragment newInstance(CustomerForm customerForm, int themeResourceId) {
        if (customerForm.legalCountrySelector == null || customerForm.legalAddressForm == null || ArrayUtils.contains(customerForm.legalAddressForm.readOnlyField, 1)) {
            CustomerFragment fragment = new CustomerFragment();
            fragment.setArguments(FormFragment.createArgsForFormFragment(themeResourceId, customerForm, CustomerFragment.class));
            return fragment;
        }
        throw new IllegalArgumentException("Customer form with both a legal country selector and a legal address form containing a country selector is not supported");
    }

    public boolean validate() {
        return validate(true);
    }

    public boolean isValid() {
        return validate(false);
    }

    private boolean validate(boolean showErrorIfInvalid) {
        boolean valid = true;
        int length = this.mSubForms.size();
        for (int i = 0; i < length; i++) {
            Form form = (Form) this.mSubForms.get(i);
            if (showErrorIfInvalid) {
                if (form.validate() && valid) {
                    valid = true;
                } else {
                    valid = false;
                }
            } else if (!form.isValid()) {
                return false;
            }
        }
        return valid;
    }

    public boolean focusOnFirstInvalidFormField() {
        int length = this.mSubForms.size();
        for (int i = 0; i < length; i++) {
            if (((Form) this.mSubForms.get(i)).focusOnFirstInvalidFormField()) {
                return true;
            }
        }
        return false;
    }

    public boolean applyFormFieldMessage(FormFieldMessage formFieldMessage) {
        if (formFieldMessage.formFieldReference.formId.equals(((CustomerForm) this.mFormProto).id)) {
            int i = formFieldMessage.formFieldReference.fieldId;
            throw new IllegalArgumentException("Unknown FormFieldMessage fieldId: " + formFieldMessage.formFieldReference.fieldId);
        }
        int length = this.mSubForms.size();
        for (int i2 = 0; i2 < length; i2++) {
            if (((Form) this.mSubForms.get(i2)).applyFormFieldMessage(formFieldMessage)) {
                return true;
            }
        }
        return false;
    }

    public boolean handleErrorMessageDismissed(String formId, int errorType) {
        int length = this.mSubForms.size();
        for (int i = 0; i < length; i++) {
            if (((Form) this.mSubForms.get(i)).handleErrorMessageDismissed(formId, errorType)) {
                return true;
            }
        }
        return false;
    }

    public void doEnableUi() {
        if (this.mLegalMessageText != null) {
            boolean uiEnabled = isUiEnabled();
            if (this.mRegionCodeView != null) {
                this.mRegionCodeView.setEnabled(uiEnabled);
            }
            int length = this.mSubForms.size();
            for (int i = 0; i < length; i++) {
                ((Form) this.mSubForms.get(i)).enableUi(uiEnabled);
            }
            this.mLegalMessageText.setEnabled(uiEnabled);
        }
    }

    public boolean isReadyToSubmit() {
        int length = this.mSubForms.size();
        for (int i = 0; i < length; i++) {
            if (!((Form) this.mSubForms.get(i)).isReadyToSubmit()) {
                return false;
            }
        }
        return true;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mSelectedRegionCode = savedInstanceState.getInt("selectedRegionCode", 0);
            if (this.mSelectedRegionCode != 0) {
                this.mLegalMessage = PaymentUtils.findLegalMessageByCountry(((CustomerForm) this.mFormProto).legalMessages, RegionCode.toCountryCode(this.mSelectedRegionCode));
            }
        }
    }

    protected View onCreateThemedView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_customer, null, false);
        this.mLegalMessageText = (InfoMessageTextView) content.findViewById(R.id.customer_legal_message_text);
        this.mLegalMessageText.setParentUiNode(this);
        this.mLegalMessageText.setUrlClickListener(this);
        if (((CustomerForm) this.mFormProto).legalCountrySelector == null && ((CustomerForm) this.mFormProto).legalAddressForm == null) {
            setLegalMessage(((CustomerForm) this.mFormProto).legalMessages == null ? null : ((CustomerForm) this.mFormProto).legalMessages.defaultMessage);
        }
        if (((CustomerForm) this.mFormProto).legalCountrySelector != null) {
            if (savedInstanceState != null) {
                this.mRegionCodes = savedInstanceState.getIntArray("regionCodes");
            } else if (((CustomerForm) this.mFormProto).legalCountrySelector.allowedCountryCode.length <= 0) {
                throw new IllegalArgumentException("LegalCountrySelector's allowed country codes cannot be empty");
            } else {
                this.mRegionCodes = AddressUtils.scrubAndSortRegionCodes(AddressUtils.stringArrayToRegionCodeArray(((CustomerForm) this.mFormProto).legalCountrySelector.allowedCountryCode));
            }
            this.mRegionCodeView = (RegionCodeView) content.findViewById(R.id.legal_country_selector);
            this.mRegionCodeView.setVisibility(0);
            this.mRegionCodeView.setRegionCodeSelectedListener(this);
            this.mRegionCodeView.setRegionCodes(this.mRegionCodes);
            this.mRegionCodeView.setSelectedRegionCode(RegionCode.toRegionCode(((CustomerForm) this.mFormProto).legalCountrySelector.initialCountryCode));
        }
        if (((CustomerForm) this.mFormProto).taxInfoForm.length > 0) {
            content.findViewById(R.id.tax_info_fragment_holder).setVisibility(0);
            TaxInfoEntryFragment fragment = (TaxInfoEntryFragment) getChildFragmentManager().findFragmentById(R.id.tax_info_fragment_holder);
            if (fragment == null) {
                fragment = TaxInfoEntryFragment.newInstance(((CustomerForm) this.mFormProto).taxInfoForm, ((CustomerForm) this.mFormProto).initialTaxInfoForm, getThemeResourceId());
                getChildFragmentManager().beginTransaction().add(R.id.tax_info_fragment_holder, (Fragment) fragment).commit();
            }
            this.mSubForms.add(fragment);
        }
        if (((CustomerForm) this.mFormProto).legalAddressForm != null) {
            content.findViewById(R.id.legal_address_entry_fragment_holder).setVisibility(0);
            AddressEntryFragment fragment2 = (AddressEntryFragment) getChildFragmentManager().findFragmentById(R.id.legal_address_entry_fragment_holder);
            if (fragment2 == null) {
                fragment2 = AddressEntryFragment.newInstance(((CustomerForm) this.mFormProto).legalAddressForm, getThemeResourceId());
                getChildFragmentManager().beginTransaction().add(R.id.legal_address_entry_fragment_holder, (Fragment) fragment2).commit();
            }
            fragment2.setOnRegionCodeSelectedListener(this);
            this.mSubForms.add(fragment2);
        }
        if (((CustomerForm) this.mFormProto).instrumentForm != null) {
            Form instrumentForm;
            content.findViewById(R.id.instrument_form_fragment_holder).setVisibility(0);
            if (((CustomerForm) this.mFormProto).instrumentForm.creditCard != null) {
                AddCreditCardFragment fragment3 = (AddCreditCardFragment) getChildFragmentManager().findFragmentById(R.id.instrument_form_fragment_holder);
                if (fragment3 == null) {
                    fragment3 = AddCreditCardFragment.newInstance(((CustomerForm) this.mFormProto).instrumentForm.creditCard, getThemeResourceId());
                    getChildFragmentManager().beginTransaction().add(R.id.instrument_form_fragment_holder, (Fragment) fragment3).commit();
                }
                fragment3.setOnStateChangedListener(this);
                instrumentForm = fragment3;
            } else if (((CustomerForm) this.mFormProto).instrumentForm.usernamePassword != null) {
                content.findViewById(R.id.instrument_form_fragment_holder).setVisibility(0);
                UsernamePasswordFragment fragment4 = (UsernamePasswordFragment) getChildFragmentManager().findFragmentById(R.id.instrument_form_fragment_holder);
                if (fragment4 == null) {
                    fragment4 = UsernamePasswordFragment.newInstance(((CustomerForm) this.mFormProto).instrumentForm.usernamePassword, getThemeResourceId());
                    getChildFragmentManager().beginTransaction().add(R.id.instrument_form_fragment_holder, (Fragment) fragment4).commit();
                }
                Object instrumentForm2 = fragment4;
            } else {
                throw new IllegalArgumentException("Instrument form did not contain a recognized subform.");
            }
            this.mSubForms.add(instrumentForm);
        }
        doEnableUi();
        return content;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("regionCodes", this.mRegionCodes);
        outState.putInt("selectedRegionCode", this.mSelectedRegionCode);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment instrumentFormFragment = getChildFragmentManager().findFragmentById(R.id.instrument_form_fragment_holder);
        if (instrumentFormFragment instanceof AddCreditCardFragment) {
            instrumentFormFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        notifyFormEvent(6, Bundle.EMPTY);
    }

    public void onRegionCodeSelected(int regionCode, int senderId) {
        if (this.mSelectedRegionCode != regionCode) {
            this.mSelectedRegionCode = regionCode;
            String country = RegionCode.toCountryCode(regionCode);
            if (!(((CustomerForm) this.mFormProto).legalCountrySelector == null || senderId != this.mRegionCodeView.getId() || ((CustomerForm) this.mFormProto).legalCountrySelector.initialCountryCode.equals(country))) {
                Bundle eventDetails = new Bundle();
                eventDetails.putString("FormEventListener.EXTRA_FORM_ID", ((CustomerForm) this.mFormProto).id);
                eventDetails.putInt("FormEventListener.EXTRA_FIELD_ID", 1);
                notifyFormEvent(3, eventDetails);
            }
            setLegalMessage(PaymentUtils.findLegalMessageByCountry(((CustomerForm) this.mFormProto).legalMessages, country));
        }
    }

    public CustomerFormValue getCustomerFormValue() {
        CustomerFormValue customer = new CustomerFormValue();
        if (this.mRegionCodeView != null) {
            customer.legalCountryCode = RegionCode.toCountryCode(this.mRegionCodeView.getSelectedRegionCode());
        }
        if (this.mLegalMessage != null) {
            customer.legalDocData = this.mLegalMessage.opaqueData;
        }
        int length = this.mSubForms.size();
        for (int i = 0; i < length; i++) {
            Form form = (Form) this.mSubForms.get(i);
            if (form instanceof TaxInfoEntryFragment) {
                customer.taxInfo = ((TaxInfoEntryFragment) form).getTaxInfoFormValue();
            } else if (form instanceof AddressEntryFragment) {
                customer.legalAddress = ((AddressEntryFragment) form).getAddressFormValue();
            } else if (form instanceof AddCreditCardFragment) {
                customer.instrument = new InstrumentFormValue();
                customer.instrument.creditCard = ((AddCreditCardFragment) form).getCreditCardFormValue();
            } else if (form instanceof UsernamePasswordFragment) {
                customer.instrument = new InstrumentFormValue();
                customer.instrument.usernamePassword = ((UsernamePasswordFragment) form).getUsernamePasswordFormValue();
            } else {
                throw new IllegalStateException("Form " + form + " is not supported as a subform");
            }
        }
        return customer;
    }

    public void onClick(View view, String url) {
        if (view == this.mLegalMessageText && getFragmentManager().findFragmentByTag("tagTosWebViewDialog") == null) {
            WebViewDialogFragment.newInstance(url, getThemeResourceId()).show(getFragmentManager(), "tagTosWebViewDialog");
        }
    }

    public InstrumentManagerUiElement getUiElement() {
        return this.mUiElement;
    }

    public List<UiNode> getChildren() {
        ArrayList<UiNode> children = new ArrayList();
        if (this.mRegionCodeView != null) {
            children.add(new SimpleUiNode(1668, this));
        }
        int length = this.mSubForms.size();
        for (int i = 0; i < length; i++) {
            Form form = (Form) this.mSubForms.get(i);
            if (form instanceof UiNode) {
                children.add((UiNode) form);
            }
        }
        if (this.mLegalMessage != null) {
            children.add(this.mLegalMessageText);
        }
        return children;
    }

    public void showViewsBelow(boolean show, boolean animate, int startDeltaY, int endDeltaY, long delayMs) {
        if (animate) {
            if (VERSION.SDK_INT >= 14) {
                this.mLegalMessageText.animate().setStartDelay(delayMs);
            }
            if (show) {
                WalletUiUtils.animateViewAppearing(this.mLegalMessageText, startDeltaY, endDeltaY);
            } else {
                WalletUiUtils.animateViewDisappearingToGone(this.mLegalMessageText, startDeltaY, endDeltaY);
            }
            if (VERSION.SDK_INT >= 14) {
                this.mLegalMessageText.animate().setStartDelay(0);
                return;
            }
            return;
        }
        this.mLegalMessageText.setVisibility(show ? 0 : 8);
    }

    public void animateViewsBelow(int startDeltaY, int endDeltaY, long delayMs) {
        if (VERSION.SDK_INT >= 14) {
            this.mLegalMessageText.setTranslationY((float) startDeltaY);
            this.mLegalMessageText.animate().translationY((float) endDeltaY).setStartDelay(delayMs).start();
            this.mLegalMessageText.animate().setStartDelay(0);
        }
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

    private void setLegalMessage(LegalMessage message) {
        this.mLegalMessage = message;
        this.mLegalMessageText.setInfoMessage(message == null ? null : message.messageText);
        notifyFormEvent(6, Bundle.EMPTY);
    }
}
