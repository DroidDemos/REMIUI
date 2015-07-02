package com.google.android.finsky.activities;

import android.accounts.Account;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.billing.lightpurchase.LightPurchaseFlowActivity;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.ConsumptionUtils;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.DocUtils.OfferFilter;
import com.google.android.finsky.utils.IntentUtils;

class FakeNavigationManager extends NavigationManager {
    private Activity mActivity;

    public FakeNavigationManager(Activity activity) {
        super(null);
        this.mActivity = activity;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public void init(MainActivity activity) {
    }

    public boolean goUp() {
        this.mActivity.onBackPressed();
        return true;
    }

    public boolean goBack() {
        this.mActivity.onBackPressed();
        return true;
    }

    public boolean canGoUp() {
        return true;
    }

    public boolean canSearch() {
        return false;
    }

    public boolean isBackStackEmpty() {
        return false;
    }

    public void addOnBackStackChangedListener(OnBackStackChangedListener listener) {
    }

    public void goToDocPage(String url) {
        this.mActivity.startActivity(IntentUtils.createViewDocumentUrlIntent(this.mActivity, url));
        this.mActivity.finish();
    }

    public void goBrowse(String url, String title, int backendId, DfeToc dfeToc, PlayStoreUiElementNode clickLogNode) {
        this.mActivity.startActivity(IntentUtils.createBrowseIntent(this.mActivity, url, title, backendId, false));
        this.mActivity.finish();
    }

    public PageFragment getActivePage() {
        return null;
    }

    protected Activity getActivityForResolveLink() {
        return this.mActivity;
    }

    public void buy(Account account, Document doc, int offerType, OfferFilter filter, String appsContinueUrl) {
        this.mActivity.startActivityForResult(LightPurchaseFlowActivity.createIntent(account, doc, offerType, filter, doc.getServerLogsCookie(), appsContinueUrl), 33);
    }

    public void openItem(Account account, Document doc) {
        ConsumptionUtils.openItem(this.mActivity, account, doc, this.mFragmentManager, null, 1);
    }

    public void goToImagesLightbox(Document doc, int initialIndex, int imageType) {
        if (this.mActivity != null) {
            ScreenshotsActivity.show(this.mActivity, doc, initialIndex, imageType);
        }
    }
}
