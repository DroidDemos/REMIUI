package com.google.android.finsky.billing.lightpurchase.billingprofile;

import android.accounts.Account;
import android.os.Bundle;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.protos.BillingProfileProtos.BillingProfile;
import com.google.android.finsky.protos.BuyInstruments.BillingProfileResponse;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Map;

public class BillingProfileSidecar extends SidecarFragment implements ErrorListener, Listener<BillingProfileResponse> {
    private Account mAccount;
    private BillingProfileResponse mBillingProfileResponse;
    private DfeApi mDfeApi;
    private String mErrorMessageHtml;
    private Map<String, String> mExtraPostParams;
    private String mPurchaseContextToken;
    private VolleyError mVolleyError;

    public static BillingProfileSidecar newInstance(Account account, String purchaseContextToken) {
        Bundle arguments = new Bundle();
        arguments.putParcelable("BillingProfileSidecar.account", account);
        arguments.putString("BillingProfileSidecar.purchaseContextToken", purchaseContextToken);
        BillingProfileSidecar result = new BillingProfileSidecar();
        result.setArguments(arguments);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        this.mAccount = (Account) getArguments().getParcelable("BillingProfileSidecar.account");
        this.mDfeApi = FinskyApp.get().getDfeApi(this.mAccount.name);
        this.mPurchaseContextToken = getArguments().getString("BillingProfileSidecar.purchaseContextToken");
        super.onCreate(savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("BillingProfileSidecar.billingProfileResponse", ParcelableProto.forProto(this.mBillingProfileResponse));
    }

    protected void restoreFromSavedInstanceState(Bundle savedInstanceState) {
        super.restoreFromSavedInstanceState(savedInstanceState);
        this.mBillingProfileResponse = (BillingProfileResponse) ParcelableProto.getProtoFromBundle(savedInstanceState, "BillingProfileSidecar.billingProfileResponse");
    }

    public void start(Map<String, String> extraPostParams) {
        setState(1, 0);
        this.mExtraPostParams = extraPostParams;
        this.mDfeApi.billingProfile(this.mPurchaseContextToken, this.mExtraPostParams, this, this);
    }

    public BillingProfile getBillingProfile() {
        return this.mBillingProfileResponse.billingProfile;
    }

    public String getErrorMessageHtml() {
        return this.mErrorMessageHtml;
    }

    public VolleyError getVolleyError() {
        return this.mVolleyError;
    }

    public void onResponse(BillingProfileResponse billingProfileResponse) {
        switch (billingProfileResponse.result) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mBillingProfileResponse = billingProfileResponse;
                setState(2, 0);
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mErrorMessageHtml = billingProfileResponse.userMessageHtml;
                setState(3, 4);
                return;
            default:
                this.mErrorMessageHtml = getString(com.android.vending.R.string.error);
                setState(3, 4);
                return;
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.mVolleyError = volleyError;
        setState(3, 5);
    }
}
