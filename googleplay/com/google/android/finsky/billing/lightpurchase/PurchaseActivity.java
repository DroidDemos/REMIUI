package com.google.android.finsky.billing.lightpurchase;

import android.accounts.Account;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AccessRestrictedActivity;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment.Listener;
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment.PurchaseError;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.play.RootUiElementNode;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.RestrictedDeviceHelper;
import com.google.android.finsky.utils.RestrictedDeviceHelper.OnCompleteListener;

public class PurchaseActivity extends FragmentActivity implements Listener, RootUiElementNode {
    private static final boolean DISABLE_DISMISS_ON_TOUCH_OUTSIDE;
    protected Account mAccount;
    private FinskyEventLog mEventLog;
    private final Rect mHitRect;
    private byte[] mInitialServerLogsCookie;
    protected PurchaseParams mParams;

    public PurchaseActivity() {
        this.mHitRect = new Rect();
    }

    static {
        DISABLE_DISMISS_ON_TOUCH_OUTSIDE = VERSION.SDK_INT < 11;
    }

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.light_purchase_activity, null));
        final Intent intent = getIntent();
        this.mParams = (PurchaseParams) intent.getParcelableExtra("PurchaseActivity.params");
        this.mAccount = (Account) intent.getParcelableExtra("PurchaseActivity.account");
        this.mEventLog = FinskyApp.get().getEventLogger(this.mAccount);
        this.mInitialServerLogsCookie = intent.getByteArrayExtra("PurchaseActivity.serverLogsCookie");
        RestrictedDeviceHelper.isLimitedOrSchoolEduUser(new OnCompleteListener() {
            public void onComplete(boolean isLimitedOrSchoolEduUser) {
                if (isLimitedOrSchoolEduUser) {
                    AccessRestrictedActivity.showLimitedPurchaseUI(PurchaseActivity.this);
                    PurchaseActivity.this.handleAccessRestriction();
                    PurchaseActivity.this.finish();
                    return;
                }
                PurchaseActivity.this.continueOnCreate(savedInstanceState, intent);
            }
        });
    }

    private void continueOnCreate(Bundle savedInstanceState, Intent intent) {
        if (savedInstanceState == null) {
            this.mEventLog.logPathImpression(0, this);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add((int) R.id.content_frame, PurchaseFragment.newInstance(this.mAccount, this.mParams, this.mInitialServerLogsCookie, intent.getBundleExtra("PurchaseActivity.appDownloadSizeWarningArgs")));
            transaction.commit();
        }
    }

    protected void handleAccessRestriction() {
        setResult(0);
    }

    public void onBackPressed() {
        this.mEventLog.logClickEvent(600, null, this);
        dismissIfPossible(1, "back press");
    }

    private void dismissIfPossible(int dismissType, String debugEvent) {
        PurchaseFragment fragment = getPurchaseFragment();
        if (fragment == null || fragment.isDismissable(dismissType)) {
            onDialogDismissed();
            finish();
            return;
        }
        FinskyLog.d("PurchaseFragment not dismissable by %s, ignore.", debugEvent);
    }

    private PurchaseFragment getPurchaseFragment() {
        return (PurchaseFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
    }

    protected void onDialogDismissed() {
        setResult(0);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (DISABLE_DISMISS_ON_TOUCH_OUTSIDE) {
            return super.dispatchTouchEvent(ev);
        }
        getWindow().getDecorView().getHitRect(this.mHitRect);
        if (ev.getAction() == 0 && !this.mHitRect.contains((int) ev.getX(), (int) ev.getY())) {
            this.mEventLog.logClickEvent(601, null, this);
            dismissIfPossible(2, "click outside");
        }
        return super.dispatchTouchEvent(ev);
    }

    public void finish() {
        PurchaseFragment purchaseFragment = getPurchaseFragment();
        if (purchaseFragment != null) {
            onFinish(purchaseFragment);
            this.mEventLog.logPathImpression(0, 603, this);
        } else {
            FinskyLog.d("Purchase fragment null.", new Object[0]);
        }
        super.finish();
    }

    protected void onFinish(PurchaseFragment purchaseFragment) {
        int errorSubtype = -1;
        if (purchaseFragment.hasSucceeded()) {
            setResult(-1);
        } else if (purchaseFragment.hasFailed()) {
            int errorType;
            PurchaseError error = purchaseFragment.getError();
            if (error != null) {
                errorType = error.errorType;
            } else {
                errorType = -1;
            }
            if (error != null) {
                errorSubtype = error.errorSubtype;
            }
            FinskyLog.e("Purchase failed: %d / %d", Integer.valueOf(errorType), Integer.valueOf(errorSubtype));
            setResult(0);
        }
    }

    public void onFinished() {
        finish();
    }

    public static Intent createIntent(Account account, PurchaseParams params, byte[] serverLogsCookie, Bundle appDownloadSizeWarningArgs) {
        Intent intent = new Intent(FinskyApp.get(), PurchaseActivity.class);
        intent.putExtra("PurchaseActivity.account", account);
        intent.putExtra("PurchaseActivity.params", params);
        intent.putExtra("PurchaseActivity.appDownloadSizeWarningArgs", appDownloadSizeWarningArgs);
        intent.putExtra("PurchaseActivity.serverLogsCookie", serverLogsCookie);
        return intent;
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        byte[] serverLogsCookie;
        PlayStoreUiElement uiElement = FinskyEventLog.obtainPlayStoreUiElement(700);
        PlayStoreUiElementInfo clientLogsCookie = new PlayStoreUiElementInfo();
        clientLogsCookie.document = this.mParams.docidStr;
        clientLogsCookie.hasDocument = true;
        clientLogsCookie.offerType = this.mParams.offerType;
        clientLogsCookie.hasOfferType = true;
        uiElement.clientLogsCookie = clientLogsCookie;
        PurchaseFragment purchaseFragment = getPurchaseFragment();
        if (purchaseFragment != null) {
            serverLogsCookie = purchaseFragment.getServerLogsCookie();
        } else {
            serverLogsCookie = this.mInitialServerLogsCookie;
        }
        FinskyEventLog.setServerLogCookie(uiElement, serverLogsCookie);
        return uiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return null;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyLog.wtf("Not using tree impressions.", new Object[0]);
    }

    public void startNewImpression() {
        FinskyLog.wtf("Not using impression id's.", new Object[0]);
    }

    public void flushImpression() {
        FinskyLog.wtf("Not using tree impressions.", new Object[0]);
    }
}
