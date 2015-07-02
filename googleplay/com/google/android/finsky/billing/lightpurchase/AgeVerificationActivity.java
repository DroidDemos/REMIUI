package com.google.android.finsky.billing.lightpurchase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.lightpurchase.ageverification.AgeVerificationHostFragment;
import com.google.android.finsky.billing.lightpurchase.ageverification.AgeVerificationHostFragment.Listener;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.LoggingFragmentActivity;

public class AgeVerificationActivity extends LoggingFragmentActivity implements Listener {
    public static Intent createIntent(String accountName, int backend, String docidStr) {
        Intent intent = new Intent(FinskyApp.get(), AgeVerificationActivity.class);
        intent.putExtra("authAccount", accountName);
        intent.putExtra("AgeVerificationActivity.backend", backend);
        intent.putExtra("AgeVerificationActivity.docid_str", docidStr);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.age_verification_activity, null));
        if (getSupportFragmentManager().findFragmentByTag("AgeVerificationActivity.host_fragment") == null) {
            Fragment fragment = AgeVerificationHostFragment.newInstance(this.mAccountName, getIntent().getIntExtra("AgeVerificationActivity.backend", -1), getIntent().getStringExtra("AgeVerificationActivity.docid_str"));
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, fragment, "AgeVerificationActivity.host_fragment");
            transaction.commit();
        }
    }

    public void onFinished(boolean succeeded) {
        setResult(succeeded ? -1 : 0);
        finish();
    }

    protected int getPlayStoreUiElementType() {
        return 1400;
    }
}
