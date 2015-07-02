package com.google.android.finsky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.challenge.AddressChallengeFlow;
import com.google.android.finsky.layout.actionbar.ActionBarHelper;
import com.google.android.finsky.protos.ChallengeProto.AddressChallenge;
import com.google.android.finsky.utils.ParcelableProto;

public class AddressChallengeActivity extends ChallengeActivity {
    private BillingFlow mFlow;
    private final FakeNavigationManager mNavigationManager;

    public AddressChallengeActivity() {
        this.mNavigationManager = new FakeNavigationManager(this);
    }

    public static Intent getIntent(int backend, AddressChallenge challenge, Bundle extraParameters) {
        Intent intent = new Intent(FinskyApp.get(), AddressChallengeActivity.class);
        intent.putExtra("backend", backend);
        intent.putExtra("challenge", ParcelableProto.forProto(challenge));
        intent.putExtra("extra_parameters", extraParameters);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.billing_challenge_frame);
        ActionBarHelper actionBarHelper = new ActionBarHelper(this.mNavigationManager, this);
        actionBarHelper.updateCurrentBackendId(getIntent().getIntExtra("backend", 0), false);
        actionBarHelper.updateActionBarMode(false);
        this.mFlow = new AddressChallengeFlow(this, this, (AddressChallenge) ParcelableProto.getProtoFromIntent(getIntent(), "challenge"), getIntent().getBundleExtra("extra_parameters"));
        if (savedInstanceState != null) {
            this.mFlow.resumeFromSavedState(savedInstanceState);
        } else {
            this.mFlow.start();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mFlow != null) {
            this.mFlow.saveState(outState);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        this.mNavigationManager.goUp();
        return true;
    }
}
