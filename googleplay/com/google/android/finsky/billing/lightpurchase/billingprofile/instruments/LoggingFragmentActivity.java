package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.utils.FinskyLog;

public abstract class LoggingFragmentActivity extends FragmentActivity implements PlayStoreUiElementNode {
    protected String mAccountName;
    protected FinskyEventLog mEventLog;
    private PlayStoreUiElement mUiElement;

    protected abstract int getPlayStoreUiElementType();

    public LoggingFragmentActivity() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(getPlayStoreUiElementType());
    }

    public static void addAccountNameExtra(Intent intent, String accountName) {
        intent.putExtra("authAccount", accountName);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAccountName = getIntent().getStringExtra("authAccount");
        if (this.mAccountName == null) {
            FinskyLog.wtf("authAccount argument not set.", new Object[0]);
        }
        this.mEventLog = FinskyApp.get().getEventLogger(this.mAccountName);
        if (savedInstanceState == null) {
            this.mEventLog.logPathImpression(0, this);
        }
    }

    public void onBackPressed() {
        this.mEventLog.logClickEvent(600, null, this);
        super.onBackPressed();
    }

    protected void onDestroy() {
        if (isFinishing() && this.mEventLog != null) {
            this.mEventLog.logPathImpression(0, 603, this);
        }
        super.onDestroy();
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return null;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        throw new UnsupportedOperationException("Unwanted children.");
    }
}
