package com.google.android.finsky.billing.challenge;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.GetBillingCountriesAction;
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
import java.util.Collection;
import java.util.List;

public class AddressChallengeFragment extends Fragment implements OnClickListener, OnCheckedChangeListener, Listener {
    private BillingAddressSpec mAddressSpec;
    private BillingAddress mBillingAddress;
    private Button mCancelButton;
    private AddressChallenge mChallenge;
    private List<Country> mCountries;
    private AddressChallengeResultListener mListener;
    private ViewGroup mMainView;
    private Bundle mPreviousState;
    private Button mSaveButton;
    private Bundle mSavedInstanceState;

    public interface AddressChallengeResultListener {

        public enum Result {
            SUCCESS,
            FAILURE,
            CANCELED
        }

        void onAddressChallengeResult(Result result, Address address, boolean[] zArr);

        void onCountryChanged(String str, Bundle bundle);

        void onInitialized();

        void onInitializing();
    }

    public static AddressChallengeFragment newInstance(String account, AddressChallenge addressChallenge, Bundle previousState) {
        AddressChallengeFragment result = new AddressChallengeFragment();
        Bundle arguments = new Bundle();
        arguments.putString("authAccount", account);
        arguments.putParcelable("address_challenge", ParcelableProto.forProto(addressChallenge));
        result.setArguments(arguments);
        result.mPreviousState = previousState;
        return result;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        this.mSavedInstanceState = savedInstanceState;
        this.mMainView = (ViewGroup) inflater.inflate(R.layout.billing_addresschallenge_fragment, container, false);
        this.mChallenge = (AddressChallenge) ParcelableProto.getProtoFromBundle(getArguments(), "address_challenge");
        this.mAddressSpec = new BillingAddressSpec();
        int oldLength = this.mChallenge.requiredField.length;
        this.mAddressSpec.requiredField = new int[oldLength];
        System.arraycopy(this.mChallenge.requiredField, 0, this.mAddressSpec.requiredField, 0, oldLength);
        if (!TextUtils.isEmpty(this.mChallenge.errorHtml) && savedInstanceState == null) {
            this.mMainView.post(new Runnable() {
                public void run() {
                    ErrorDialog.show(AddressChallengeFragment.this.getFragmentManager(), null, AddressChallengeFragment.this.mChallenge.errorHtml, false);
                }
            });
        }
        TextView title = (TextView) this.mMainView.findViewById(R.id.challenge_title);
        if (TextUtils.isEmpty(this.mChallenge.title)) {
            title.setVisibility(8);
        } else {
            title.setText(this.mChallenge.title);
        }
        TextView description = (TextView) this.mMainView.findViewById(R.id.challenge_description);
        if (TextUtils.isEmpty(this.mChallenge.descriptionHtml)) {
            description.setVisibility(8);
        } else {
            description.setText(Html.fromHtml(this.mChallenge.descriptionHtml));
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
            if (savedInstanceState == null && this.mPreviousState == null) {
                checkBox.setChecked(formCheckbox.checked);
            } else if (this.mPreviousState != null) {
                checkBox.setChecked(this.mPreviousState.getBoolean("checkbox_" + i));
            } else {
                checkBox.setChecked(savedInstanceState.getBoolean("checkbox_" + i));
            }
            checkBox.setOnCheckedChangeListener(this);
            contentView.addView(checkBox, baseIndex + i);
        }
        this.mBillingAddress = (BillingAddress) this.mMainView.findViewById(R.id.billing_address);
        this.mBillingAddress.setBillingCountryChangeListener(new BillingCountryChangeListener() {
            public void onBillingCountryChanged(Country country) {
                AddressChallengeFragment.this.mBillingAddress.setAddressSpec(country, AddressChallengeFragment.this.mAddressSpec);
                if (AddressChallengeFragment.this.mListener != null) {
                    Bundle currentState = new Bundle();
                    AddressChallengeFragment.this.saveMyState(currentState);
                    AddressChallengeFragment.this.mListener.onCountryChanged(country.countryCode, currentState);
                }
            }
        });
        this.mSaveButton = (Button) this.mMainView.findViewById(R.id.positive_button);
        this.mSaveButton.setOnClickListener(this);
        this.mSaveButton.setEnabled(false);
        this.mSaveButton.setText(R.string.continue_text);
        this.mCancelButton = (Button) this.mMainView.findViewById(R.id.negative_button);
        this.mCancelButton.setOnClickListener(this);
        this.mCancelButton.setText(R.string.cancel);
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
        syncContinueButton();
        setupWidgets(this.mSavedInstanceState);
    }

    private void loadBillingCountries() {
        if (this.mListener != null) {
            this.mListener.onInitializing();
        }
        new GetBillingCountriesAction().run(getArguments().getString("authAccount"), new Runnable() {
            public void run() {
                AddressChallengeFragment.this.onBillingCountriesLoaded();
            }
        });
    }

    private void onBillingCountriesLoaded() {
        if (isAdded()) {
            this.mCountries = BillingLocator.getBillingCountries();
            if (this.mCountries == null || this.mCountries.size() <= 0) {
                FinskyLog.d("BillingCountries not loaded.", new Object[0]);
                Builder builder = new Builder();
                builder.setMessageId(R.string.billing_countries_loading_failed).setPositiveId(R.string.network_retry).setNegativeId(R.string.cancel).setCallback(this, 1, (Bundle) null);
                builder.build().show(getFragmentManager(), "error");
                return;
            }
            if (this.mListener != null) {
                this.mListener.onInitialized();
            }
            syncContinueButton();
            setupWidgets(this.mSavedInstanceState);
        }
    }

    private void clearErrorMessages() {
        this.mBillingAddress.clearErrorMessage();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveMyState(outState);
    }

    private void saveMyState(Bundle outState) {
        for (int i = 0; i < this.mChallenge.checkbox.length; i++) {
            outState.putBoolean("checkbox_" + i, ((CheckBox) this.mMainView.findViewWithTag(this.mChallenge.checkbox[i])).isChecked());
        }
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
                for (InputValidationError inputValidationError : AddressChallengeFragment.this.mChallenge.errorInputField) {
                    AddressChallengeFragment.this.mBillingAddress.displayError(inputValidationError);
                }
            }
        });
    }

    private void syncContinueButton() {
        boolean enabled = true;
        for (FormCheckbox formCheckbox : this.mChallenge.checkbox) {
            enabled = enabled && (!formCheckbox.required || ((CheckBox) this.mMainView.findViewWithTag(formCheckbox)).isChecked());
        }
        this.mSaveButton.setEnabled(enabled);
    }

    public void setOnResultListener(AddressChallengeResultListener listener) {
        this.mListener = listener;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.positive_button:
                Address address = getAddressOrShowErrors();
                if (address != null) {
                    this.mListener.onAddressChallengeResult(Result.SUCCESS, address, getCheckboxState());
                    return;
                }
                return;
            case R.id.negative_button:
                this.mListener.onAddressChallengeResult(Result.CANCELED, null, null);
                return;
            default:
                return;
        }
    }

    private boolean[] getCheckboxState() {
        int checkboxCount = this.mChallenge.checkbox.length;
        boolean[] result = new boolean[checkboxCount];
        for (int i = 0; i < checkboxCount; i++) {
            result[i] = ((CheckBox) this.mMainView.findViewWithTag(this.mChallenge.checkbox[i])).isChecked();
        }
        return result;
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

    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        syncContinueButton();
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        loadBillingCountries();
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        this.mListener.onAddressChallengeResult(Result.CANCELED, null, null);
    }
}
