package com.google.android.finsky.billing.instrumentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.LoggingFragmentActivity;
import com.google.android.wallet.instrumentmanager.pub.InstrumentManagerFragment;
import com.google.android.wallet.instrumentmanager.pub.InstrumentManagerFragment.ResultListener;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerAnalyticsEventDispatcher;

public class InstrumentManagerActivity extends LoggingFragmentActivity implements ResultListener {
    public static Intent createIntent(String accountName, byte[] commonToken, byte[] actionToken) {
        Intent intent = new Intent(FinskyApp.get(), InstrumentManagerActivity.class);
        LoggingFragmentActivity.addAccountNameExtra(intent, accountName);
        intent.putExtra("common_token", commonToken);
        intent.putExtra("action_token", actionToken);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InstrumentManagerAnalyticsEventDispatcher.setEventListener(new InstrumentManagerLogger(this, this.mEventLog));
        if (getSupportFragmentManager().findFragmentByTag("instrument_manager_fragment") == null) {
            InstrumentManagerFragment fragment = InstrumentManagerFragment.newInstance(getIntent().getByteArrayExtra("common_token"), getIntent().getByteArrayExtra("action_token"), R.style.Theme.InstrumentManager.BuyFlow);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(16908290, fragment, "instrument_manager_fragment");
            transaction.commit();
        }
    }

    protected void onDestroy() {
        InstrumentManagerAnalyticsEventDispatcher.setEventListener(null);
        super.onDestroy();
    }

    public void onInstrumentManagerResult(int resultCode, Bundle data) {
        switch (resultCode) {
            case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                String instrumentId = data.getString("com.google.android.wallet.instrumentmanager.INSTRUMENT_ID");
                Intent result = new Intent();
                result.putExtra("instrument_id", instrumentId);
                setResult(-1, result);
                break;
            case com.google.android.play.R.styleable.Theme_activityChooserViewStyle /*51*/:
            case com.google.android.play.R.styleable.Theme_toolbarStyle /*52*/:
                setResult(0);
                break;
            default:
                throw new IllegalStateException("Unexpected InstrumentManager resultCode: " + resultCode);
        }
        finish();
    }

    protected int getPlayStoreUiElementType() {
        return 1600;
    }
}
