package com.google.android.finsky.billing.lightpurchase.billingprofile;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingProfileBaseFragment;
import com.google.android.finsky.billing.BillingProfileFragment;
import com.google.android.finsky.billing.BillingProfileFragment.Listener;
import com.google.android.finsky.billing.giftcard.RedeemCodeResult;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.utils.ParcelableProto;

public class BillingProfileActivity extends FragmentActivity implements Listener {
    private Account mAccount;
    private String mPurchaseContextToken;

    public static Intent createIntent(Account account, String purchaseContextToken, Docid docid, int offerType) {
        Intent intent = new Intent(FinskyApp.get(), BillingProfileActivity.class);
        intent.putExtra("BillingProfileActivity.account", account);
        intent.putExtra("BillingProfileActivity.purchaseContextToken", purchaseContextToken);
        intent.putExtra("BillingProfileActivity.docid", ParcelableProto.forProto(docid));
        intent.putExtra("BillingProfileActivity.offerType", offerType);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_profile_activity);
        ((TextView) findViewById(R.id.title)).setText(R.string.payment_methods);
        this.mAccount = (Account) getIntent().getParcelableExtra("BillingProfileActivity.account");
        this.mPurchaseContextToken = getIntent().getStringExtra("BillingProfileActivity.purchaseContextToken");
        TextView accountView = (TextView) findViewById(R.id.account);
        accountView.setText(this.mAccount.name);
        accountView.setVisibility(0);
        if (getSupportFragmentManager().findFragmentByTag("BillingProfileActivity.fragment") == null) {
            Intent intent = getIntent();
            getSupportFragmentManager().beginTransaction().add(R.id.content_frame, BillingProfileFragment.newInstance(this.mAccount, this.mPurchaseContextToken, (Docid) ParcelableProto.getProtoFromIntent(intent, "BillingProfileActivity.docid"), intent.getIntExtra("BillingProfileActivity.offerType", 0)), "BillingProfileActivity.fragment").commit();
        }
    }

    public void onInstrumentSelected(String instrumentId) {
        Intent intent = new Intent();
        intent.putExtra("BillingProfileActivity.selectedInstrumentId", instrumentId);
        setResult(-1, intent);
        finish();
    }

    public void onPromoCodeRedeemed(RedeemCodeResult redeemCodeResult) {
        Intent intent = new Intent();
        intent.putExtra("BillingProfileActivity.redeemPromoCodeResult", redeemCodeResult);
        setResult(-1, intent);
        finish();
    }

    public void onCancel() {
        setResult(0);
        finish();
    }

    public void finish() {
        BillingProfileBaseFragment fragment = (BillingProfileBaseFragment) getSupportFragmentManager().findFragmentByTag("BillingProfileActivity.fragment");
        if (!(fragment == null || this.mAccount == null)) {
            FinskyApp.get().getEventLogger(this.mAccount).logPathImpression(0, 603, fragment);
        }
        super.finish();
    }
}
