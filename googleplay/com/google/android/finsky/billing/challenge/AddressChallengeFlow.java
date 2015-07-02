package com.google.android.finsky.billing.challenge;

import android.os.Bundle;
import android.util.Base64;
import com.android.vending.R;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.challenge.AddressChallengeFragment.AddressChallengeResultListener;
import com.google.android.finsky.billing.challenge.AddressChallengeFragment.AddressChallengeResultListener.Result;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.ChallengeProto.AddressChallenge;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.protobuf.nano.MessageNano;
import java.util.ArrayList;

public class AddressChallengeFlow extends BillingFlow implements AddressChallengeResultListener {
    private AddressChallenge mAddressChallenge;
    private AddressChallengeFragment mAddressChallengeFragment;
    private boolean mFinishOnSwitchCountry;
    private int mResultFormat;

    public AddressChallengeFlow(BillingFlowContext billingFlowContext, BillingFlowListener listener, AddressChallenge addressChallenge, Bundle parameters) {
        boolean z = false;
        super(billingFlowContext, listener, parameters);
        this.mResultFormat = 0;
        this.mAddressChallenge = addressChallenge;
        if (parameters != null && parameters.getBoolean("AddressChallengeFlow.finishOnSwitchCountry")) {
            z = true;
        }
        this.mFinishOnSwitchCountry = z;
        if (parameters != null && parameters.containsKey("AddressChallengeFlow.resultFormat")) {
            this.mResultFormat = parameters.getInt("AddressChallengeFlow.resultFormat");
        }
    }

    public void start() {
        this.mAddressChallengeFragment = AddressChallengeFragment.newInstance(this.mParameters.getString("authAccount"), this.mAddressChallenge, this.mParameters.getBundle("AddressChallengeFlow.previousState"));
        this.mAddressChallengeFragment.setOnResultListener(this);
        this.mBillingFlowContext.showFragment(this.mAddressChallengeFragment, null, false);
    }

    public void resumeFromSavedState(Bundle bundle) {
        if (bundle.containsKey("address_widget")) {
            this.mAddressChallengeFragment = (AddressChallengeFragment) this.mBillingFlowContext.restoreFragment(bundle, "address_widget");
            if (this.mAddressChallengeFragment != null) {
                this.mAddressChallengeFragment.setOnResultListener(this);
            }
        }
    }

    public void saveState(Bundle bundle) {
        if (this.mAddressChallengeFragment != null) {
            this.mBillingFlowContext.persistFragment(bundle, "address_widget", this.mAddressChallengeFragment);
        }
    }

    public void onAddressChallengeResult(Result result, Address address, boolean[] checkboxStates) {
        if (result == Result.CANCELED) {
            cancel();
        } else if (result == Result.SUCCESS) {
            Bundle challengeResponse = new Bundle();
            if (this.mResultFormat == 0) {
                challengeResponse.putString(this.mAddressChallenge.responseAddressParam, Base64.encodeToString(MessageNano.toByteArray(address), 8));
            } else if (this.mResultFormat == 1) {
                challengeResponse.putParcelable("AddressChallengeFlow.address", ParcelableProto.forProto(address));
            }
            StringBuilder checkboxesString = new StringBuilder();
            ArrayList<String> checkedCheckboxIds = Lists.newArrayList();
            for (int i = 0; i < checkboxStates.length; i++) {
                if (i > 0) {
                    checkboxesString.append(',');
                }
                checkboxesString.append(String.valueOf(checkboxStates[i]));
                if (checkboxStates[i]) {
                    checkedCheckboxIds.add(this.mAddressChallenge.checkbox[i].id);
                }
            }
            if (this.mResultFormat == 0) {
                challengeResponse.putString(this.mAddressChallenge.responseCheckboxesParam, checkboxesString.toString());
            } else {
                challengeResponse.putStringArrayList("AddressChallengeFlow.checkedCheckboxes", checkedCheckboxIds);
            }
            finish(challengeResponse);
        } else if (result == Result.FAILURE) {
            fail(null);
        }
    }

    public void onCountryChanged(String countryCode, Bundle currentState) {
        if (this.mFinishOnSwitchCountry) {
            FinskyLog.v("Finishing due to country switch.", new Object[0]);
            Bundle result = new Bundle();
            result.putString("AddressChallengeFlow.switchCountry", countryCode);
            result.putBundle("AddressChallengeFlow.currentState", currentState);
            finish(result);
        }
    }

    public void onInitializing() {
        this.mBillingFlowContext.showProgress(R.string.loading);
    }

    public void onInitialized() {
        this.mBillingFlowContext.hideProgress();
    }
}
