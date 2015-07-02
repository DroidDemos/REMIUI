package com.google.android.finsky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import com.android.vending.R;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.config.G;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.actionbar.ActionBarController;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.SignatureUtils;
import com.google.android.gms.people.PeopleClient;
import com.google.android.play.image.BitmapLoader;

public class InlineAppDetailsDialog extends AuthenticatedActivity implements PageFragmentHost {
    private View mDialog;
    private InlineAppDetailsFragment mFragment;
    private FakeNavigationManager mNavigationManager;

    public InlineAppDetailsDialog() {
        this.mNavigationManager = new FakeNavigationManager(this);
    }

    protected void onCreate(Bundle savedInstanceState) {
        boolean allowedToProceed;
        super.onCreate(savedInstanceState);
        if (SignatureUtils.isCalledByFirstPartyPackage(this) || ((Boolean) G.enableThirdPartyInlineAppInstalls.get()).booleanValue()) {
            allowedToProceed = true;
        } else {
            allowedToProceed = false;
        }
        if (!allowedToProceed) {
            FinskyLog.w("Called from untrusted package.", new Object[0]);
            finish();
        }
        this.mDialog = View.inflate(this, R.layout.inline_app_dialog, null);
        setContentView(this.mDialog);
        this.mNavigationManager.setFragmentManager(getSupportFragmentManager());
        this.mFragment = (InlineAppDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (this.mFragment == null) {
            Intent intent = getIntent();
            String docId = intent.getStringExtra("docid");
            if (TextUtils.isEmpty(docId)) {
                FinskyLog.e("Missing docid.", new Object[0]);
                finish();
                return;
            }
            this.mFragment = InlineAppDetailsFragment.newInstance(docId, intent.getStringExtra("referrer"));
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add((int) R.id.content_frame, this.mFragment);
            ft.commit();
        }
    }

    protected void handleAuthenticationError(VolleyError error) {
        this.mFragment.showError(error);
    }

    protected void onReady(boolean shouldHandleIntent) {
        this.mFragment.setHasBeenAuthenticated();
        this.mFragment.refresh();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 33 && resultCode == -1) {
            setResult(-1);
        }
        finish();
    }

    public NavigationManager getNavigationManager() {
        return this.mNavigationManager;
    }

    public ActionBarController getActionBarController() {
        return null;
    }

    public void updateBreadcrumb(String breadcrumb) {
    }

    public void updateCurrentBackendId(int backend, boolean ignoreActionBarBackground) {
    }

    public void updateActionBarMode(boolean searchBoxOnly) {
    }

    public void goBack() {
        finish();
    }

    public DfeApi getDfeApi(String dfeAccount) {
        return FinskyApp.get().getDfeApi(dfeAccount);
    }

    public BitmapLoader getBitmapLoader() {
        return FinskyApp.get().getBitmapLoader();
    }

    public void showErrorDialog(String title, String message, boolean goBack) {
    }

    public PeopleClient getPeopleClient() {
        return null;
    }

    public void hideDialog() {
        if (this.mDialog != null) {
            this.mDialog.setVisibility(8);
        }
    }

    public void maybeShowSatisfactionSurvey(PageFragment fragment, boolean skipPrompt) {
    }
}
