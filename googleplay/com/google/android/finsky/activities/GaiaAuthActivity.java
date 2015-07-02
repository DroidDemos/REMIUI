package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.android.vending.R;
import com.google.android.finsky.fragments.GaiaAuthFragment;
import com.google.android.finsky.fragments.GaiaAuthFragment.Listener;

public class GaiaAuthActivity extends FragmentActivity implements Listener {
    private GaiaAuthFragment mGaiaAuthFragment;

    public static Intent getIntent(Context context, String accountName, boolean showWarning, boolean useGmsCoreForAuth, boolean usePinBasedAuth, Bundle extraParams) {
        Intent intent = new Intent(context, GaiaAuthActivity.class);
        intent.putExtra("GaiaAuthActivity_useGmsCoreForAuth", useGmsCoreForAuth);
        intent.putExtra("GaiaAuthActivity_usePinBasedAuth", usePinBasedAuth);
        intent.putExtra("GaiaAuthActivity_accountName", accountName);
        intent.putExtra("GaiaAuthActivity_showWarning", showWarning);
        intent.putExtra("GaiaAuthActivity_extraParams", extraParams);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaia_auth_frame);
        setTitle(getString(getIntent().getBooleanExtra("GaiaAuthActivity_usePinBasedAuth", false) ? R.string.pin_dialog_title : R.string.password_dialog_title));
        if (savedInstanceState != null) {
            this.mGaiaAuthFragment = (GaiaAuthFragment) getSupportFragmentManager().getFragment(savedInstanceState, "GaiaAuthActivity_GaiaAuthFragment");
            this.mGaiaAuthFragment.setListener(this);
            return;
        }
        Intent intent = getIntent();
        this.mGaiaAuthFragment = GaiaAuthFragment.newInstance(intent.getStringExtra("GaiaAuthActivity_accountName"), intent.getBooleanExtra("GaiaAuthActivity_showWarning", false), intent.getBooleanExtra("GaiaAuthActivity_useGmsCoreForAuth", false), intent.getBooleanExtra("GaiaAuthActivity_usePinBasedAuth", false));
        this.mGaiaAuthFragment.setListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add((int) R.id.content_frame, this.mGaiaAuthFragment);
        transaction.commit();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "GaiaAuthActivity_GaiaAuthFragment", this.mGaiaAuthFragment);
    }

    public void onSuccess(int authenticationType, int retryCount) {
        Intent intent = new Intent();
        intent.putExtra("GaiaAuthActivity_extraParams", getIntent().getBundleExtra("GaiaAuthActivity_extraParams"));
        setResult(-1, intent);
        finish();
    }

    public void onCancel() {
        setResult(0);
        finish();
    }
}
