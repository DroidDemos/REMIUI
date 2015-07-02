package com.google.android.finsky.billing.giftcard.steps;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.GetBillingCountriesAction;
import com.google.android.finsky.billing.giftcard.RedeemCodeFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.layout.BillingAddress;
import com.google.android.finsky.layout.BillingAddress.BillingCountryChangeListener;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.ChallengeProto;
import com.google.android.finsky.protos.ChallengeProto.AddressChallenge;
import com.google.android.finsky.protos.ChallengeProto.FormCheckbox;
import com.google.android.finsky.protos.ChallengeProto.InputValidationError;
import com.google.android.finsky.protos.CommonDevice.BillingAddressSpec;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddressChallengeStep extends StepFragment<RedeemCodeFragment> {
    private BillingAddressSpec mAddressSpec;
    private BillingAddress mBillingAddress;
    private int mBillingUiMode;
    private AddressChallenge mChallenge;
    private List<Country> mCountries;
    private ViewGroup mMainView;
    private PlayStoreUiElement mPlayStoreUiElement;
    private Bundle mSavedInstanceState;

    public AddressChallengeStep() {
        this.mPlayStoreUiElement = FinskyEventLog.obtainPlayStoreUiElement(1104);
    }

    public static AddressChallengeStep newInstance(String accountName, AddressChallenge challenge, int mode) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putParcelable("ConfirmationStep.challenge", ParcelableProto.forProto(challenge));
        args.putInt("ui_mode", mode);
        AddressChallengeStep result = new AddressChallengeStep();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mChallenge = (AddressChallenge) ParcelableProto.getProtoFromBundle(getArguments(), "ConfirmationStep.challenge");
        this.mBillingUiMode = getArguments().getInt("ui_mode");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mSavedInstanceState = savedInstanceState;
        this.mMainView = (ViewGroup) inflater.inflate(this.mBillingUiMode == 0 ? R.layout.redeem_addresschallenge_step : R.layout.setup_wizard_redeem_address_step, container, false);
        this.mAddressSpec = new BillingAddressSpec();
        int oldLength = this.mChallenge.requiredField.length;
        this.mAddressSpec.requiredField = new int[oldLength];
        System.arraycopy(this.mChallenge.requiredField, 0, this.mAddressSpec.requiredField, 0, oldLength);
        if (!TextUtils.isEmpty(this.mChallenge.errorHtml) && savedInstanceState == null) {
            this.mMainView.post(new Runnable() {
                public void run() {
                    ErrorDialog.show(AddressChallengeStep.this.getFragmentManager(), null, AddressChallengeStep.this.mChallenge.errorHtml, false);
                }
            });
        }
        TextView title = (TextView) this.mMainView.findViewById(R.id.title);
        if (!TextUtils.isEmpty(this.mChallenge.title)) {
            if (title != null) {
                title.setText(this.mChallenge.title);
            }
            if (this.mBillingUiMode == 1) {
                getActivity().setTitle(this.mChallenge.title);
            }
        }
        TextView description = (TextView) this.mMainView.findViewById(R.id.description);
        if (TextUtils.isEmpty(this.mChallenge.descriptionHtml)) {
            description.setVisibility(8);
        } else {
            description.setText(Html.fromHtml(this.mChallenge.descriptionHtml));
            description.setLinkTextColor(description.getTextColors());
            description.setMovementMethod(LinkMovementMethod.getInstance());
        }
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        ViewGroup contentView = (ViewGroup) this.mMainView.findViewById(R.id.content);
        int baseIndex = contentView.indexOfChild(description) + 1;
        for (int i = 0; i < this.mChallenge.checkbox.length; i++) {
            FormCheckbox formCheckbox = this.mChallenge.checkbox[i];
            CheckBox checkBox = (CheckBox) layoutInflater.inflate(R.layout.billing_addresschallenge_checkbox, this.mMainView, false);
            checkBox.setText(formCheckbox.description);
            checkBox.setTag(formCheckbox);
            checkBox.setChecked(formCheckbox.checked);
            contentView.addView(checkBox, baseIndex + i);
        }
        this.mBillingAddress = (BillingAddress) this.mMainView.findViewById(R.id.billing_address);
        this.mBillingAddress.setBillingCountryChangeListener(new BillingCountryChangeListener() {
            public void onBillingCountryChanged(Country country) {
                AddressChallengeStep.this.mBillingAddress.setAddressSpec(country, AddressChallengeStep.this.mAddressSpec);
            }
        });
        if (this.mChallenge.supportedCountry.length > 0) {
            initializeCountriesFromChallenge();
        } else {
            loadBillingCountries();
        }
        return this.mMainView;
    }

    private void initializeCountriesFromChallenge() {
        this.mCountries = Lists.newArrayList(this.mChallenge.supportedCountry.length);
        for (ChallengeProto.Country inCountry : this.mChallenge.supportedCountry) {
            Country outCountry = new Country();
            outCountry.countryCode = inCountry.regionCode;
            outCountry.hasCountryCode = true;
            outCountry.countryName = inCountry.displayName;
            outCountry.hasCountryName = true;
            this.mCountries.add(outCountry);
        }
        setupWidgets(this.mSavedInstanceState);
    }

    private void loadBillingCountries() {
        new GetBillingCountriesAction().run(getArguments().getString("authAccount"), new Runnable() {
            public void run() {
                AddressChallengeStep.this.onBillingCountriesLoaded();
            }
        });
    }

    private void onBillingCountriesLoaded() {
        if (isAdded()) {
            this.mCountries = BillingLocator.getBillingCountries();
            if (this.mCountries == null || this.mCountries.size() <= 0) {
                FinskyLog.wtf("BillingCountries not loaded.", new Object[0]);
            } else {
                setupWidgets(this.mSavedInstanceState);
            }
        }
    }

    private void clearErrorMessages() {
        this.mBillingAddress.clearErrorMessage();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mBillingAddress != null) {
            this.mBillingAddress.saveInstanceState(outState);
        }
    }

    private void setupWidgets(Bundle savedInstanceState) {
        this.mBillingAddress.setBillingCountries(this.mCountries);
        if (savedInstanceState != null) {
            this.mBillingAddress.restoreInstanceState(savedInstanceState);
            return;
        }
        if (this.mChallenge.address == null || TextUtils.isEmpty(this.mChallenge.address.postalCountry)) {
            this.mBillingAddress.setAddressSpec(BillingUtils.findCountry(BillingUtils.getDefaultCountry(getActivity(), null), this.mCountries), this.mAddressSpec);
        } else {
            this.mBillingAddress.setAddressSpec(BillingUtils.findCountry(this.mChallenge.address.postalCountry, this.mCountries), this.mAddressSpec, this.mChallenge.address);
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                for (InputValidationError inputValidationError : AddressChallengeStep.this.mChallenge.errorInputField) {
                    AddressChallengeStep.this.mBillingAddress.displayError(inputValidationError);
                }
            }
        });
    }

    private void displayErrors(List<InputValidationError> inputValidationErrors) {
        clearErrorMessages();
        Collection<TextView> errorFields = Lists.newArrayList();
        for (InputValidationError error : inputValidationErrors) {
            TextView textView = displayError(error);
            if (textView != null) {
                errorFields.add(textView);
            }
        }
        TextView topMostErrorField = (TextView) BillingUtils.getTopMostView(this.mMainView, errorFields);
        if (topMostErrorField != null) {
            topMostErrorField.requestFocus();
        }
    }

    private TextView displayError(InputValidationError error) {
        return this.mBillingAddress.displayError(error);
    }

    private Address getAddressOrShowErrors() {
        List<InputValidationError> validationErrors = this.mBillingAddress.getAddressValidationErrors();
        displayErrors(validationErrors);
        if (validationErrors.size() == 0) {
            return this.mBillingAddress.getAddress();
        }
        return null;
    }

    private String[] getCheckedCheckboxIds() {
        ArrayList<String> checkedCheckboxIds = Lists.newArrayList();
        int checkboxCount = this.mChallenge.checkbox.length;
        for (int i = 0; i < checkboxCount; i++) {
            if (((CheckBox) this.mMainView.findViewWithTag(this.mChallenge.checkbox[i])).isChecked()) {
                checkedCheckboxIds.add(this.mChallenge.checkbox[i].id);
            }
        }
        return (String[]) checkedCheckboxIds.toArray(new String[checkedCheckboxIds.size()]);
    }

    public String getContinueButtonLabel(Resources resources) {
        return getString(R.string.continue_text);
    }

    public void onContinueButtonClicked() {
        logClick(1105);
        Address address = getAddressOrShowErrors();
        if (address != null) {
            ((RedeemCodeFragment) getMultiStepFragment()).addressChallenge(address, getCheckedCheckboxIds());
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mPlayStoreUiElement;
    }
}
