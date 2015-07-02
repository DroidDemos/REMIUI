package com.google.android.finsky.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.billing.DownloadSizeDialogFragment;
import com.google.android.finsky.config.G;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.layout.AppPermissionAdapter;
import com.google.android.finsky.layout.AppSecurityPermissions;
import com.google.android.finsky.layout.DetailedPermissionsAdapter;
import com.google.android.finsky.layout.IconButtonGroup;
import com.google.android.finsky.layout.PermissionAdapter;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.play.RootUiElementNode;
import com.google.android.finsky.local.AssetUtils;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.PlayCardImageTypeSequence;
import com.google.android.finsky.utils.image.ThumbnailUtils;
import com.google.android.play.image.FifeImageView;

public class AppsPermissionsActivity extends FragmentActivity implements OnClickListener, ErrorListener, Listener, OnDataChangedListener, RootUiElementNode {
    private String mAccountName;
    private IconButtonGroup mContinueButton;
    private DfeDetails mDfeDetails;
    private Document mDoc;
    private FinskyEventLog mEventLog;
    private AppSecurityPermissions mPermissionsView;
    private Bundle mSavedInstanceState;
    private boolean mShowDetailedPermissions;
    private final PlayStoreUiElement mUiElement;

    public AppsPermissionsActivity() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(790);
    }

    public static Intent createIntent(String accountName, String docidStr, Document doc, boolean showDetailedPermissions) {
        Intent intent = new Intent(FinskyApp.get(), AppsPermissionsActivity.class);
        intent.putExtra("AppsPermissionsActivity.accountName", accountName);
        intent.putExtra("AppsPermissionsActivity.docidStr", docidStr);
        intent.putExtra("AppsPermissionsActivity.doc", doc);
        intent.putExtra("AppsPermissionsActivity.showDetailedPermissions", showDetailedPermissions);
        return intent;
    }

    public static Document extractDoc(Intent data) {
        return (Document) data.getParcelableExtra("AppsPermissionsActivity.doc");
    }

    public static int extractVersionCode(Intent intent) {
        return intent.getIntExtra("AppsPermissionsActivity.appVersion", 0);
    }

    public static String extractTitle(Intent intent) {
        return intent.getStringExtra("AppsPermissionsActivity.appTitle");
    }

    public static boolean extractAcceptedNewBuckets(Intent intent) {
        return intent.getBooleanExtra("AppsPermissionsActivity.acceptedNewBuckets", false);
    }

    public static Bundle getDownloadSizeWarningArguments(Intent intent) {
        return intent.getBundleExtra("AppsPermissionsActivity.appDownloadSizeWarningArguments");
    }

    protected void onCreate(Bundle savedInstanceState) {
        this.mSavedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_purchase_app_permissions);
        Intent intent = getIntent();
        this.mAccountName = intent.getStringExtra("AppsPermissionsActivity.accountName");
        this.mEventLog = FinskyApp.get().getEventLogger(this.mAccountName);
        this.mShowDetailedPermissions = intent.getBooleanExtra("AppsPermissionsActivity.showDetailedPermissions", false);
        String docidStr = intent.getStringExtra("AppsPermissionsActivity.docidStr");
        this.mDoc = (Document) intent.getParcelableExtra("AppsPermissionsActivity.doc");
        this.mPermissionsView = (AppSecurityPermissions) findViewById(R.id.app_permissions);
        if (this.mDoc != null) {
            FinskyEventLog.setServerLogCookie(this.mUiElement, this.mDoc.getServerLogsCookie());
        }
        if (savedInstanceState == null) {
            this.mEventLog.logPathImpression(0, this);
        }
        showLoading();
        this.mDfeDetails = new DfeDetails(FinskyApp.get().getDfeApi(this.mAccountName), DfeUtils.createDetailsUrlFromId(docidStr));
        this.mDfeDetails.addDataChangedListener(this);
        this.mDfeDetails.addErrorListener(this);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mPermissionsView.saveInstanceState(outState);
    }

    protected void onStart() {
        super.onStart();
        if (this.mDfeDetails != null) {
            this.mDfeDetails.addDataChangedListener(this);
            this.mDfeDetails.addErrorListener(this);
        }
    }

    protected void onStop() {
        if (this.mDfeDetails != null) {
            this.mDfeDetails.removeDataChangedListener(this);
            this.mDfeDetails.removeErrorListener(this);
        }
        super.onStop();
    }

    public void finish() {
        if (this.mEventLog != null) {
            this.mEventLog.logPathImpression(0, 603, this);
        }
        super.finish();
    }

    private void updateFromDoc() {
        PermissionAdapter adapter;
        hideLoading();
        String packageName = this.mDoc.getBackendDocId();
        String[] permissions = this.mDoc.getAppDetails().permission;
        ((TextView) findViewById(R.id.title)).setText(this.mDoc.getTitle());
        TextView subHeader = (TextView) findViewById(R.id.account);
        subHeader.setVisibility(0);
        FifeImageView appIcon = (FifeImageView) findViewById(R.id.application_icon);
        Image image = ThumbnailUtils.getImageFromDocument(this.mDoc, appIcon.getWidth(), appIcon.getHeight(), PlayCardImageTypeSequence.CORE_IMAGE_TYPES);
        if (image != null) {
            appIcon.setImage(image.imageUrl, image.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
            appIcon.setVisibility(0);
        } else {
            appIcon.setVisibility(8);
        }
        TextView footerText = (TextView) findViewById(R.id.detailed_footer_text);
        InstallerData installerData = FinskyApp.get().getAppStates().getInstallerDataStore().get(packageName);
        boolean hasAcceptedBuckets = installerData != null && installerData.getPermissionsVersion() == 1;
        String learnMoreLink;
        final String str;
        if (this.mShowDetailedPermissions) {
            if (this.mSavedInstanceState == null) {
                FinskyEventLog.setServerLogCookie(this.mUiElement, this.mDoc.getServerLogsCookie());
                this.mEventLog.logPathImpression(0, 793, this);
            }
            learnMoreLink = (String) G.permissionBucketsLearnMoreLink.get();
            if (TextUtils.isEmpty(learnMoreLink)) {
                footerText.setVisibility(8);
            } else {
                Resources resources = getResources();
                r25 = new Object[2];
                r25[0] = this.mDoc.getTitle();
                r25[1] = learnMoreLink;
                footerText.setText(Html.fromHtml(resources.getString(R.string.detailed_permissions_footer, r25)));
                str = learnMoreLink;
                footerText.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        AppsPermissionsActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    }
                });
                footerText.setVisibility(0);
            }
            PermissionAdapter detailedPermissionsAdapter = new DetailedPermissionsAdapter(this, packageName, permissions);
            String version = this.mDoc.getAppDetails().versionString;
            subHeader.setText(getResources().getString(R.string.version_can_access, new Object[]{version}));
            findViewById(R.id.continue_button_bar).setVisibility(8);
            adapter = detailedPermissionsAdapter;
        } else {
            if (this.mSavedInstanceState == null) {
                FinskyEventLog.setServerLogCookie(this.mUiElement, this.mDoc.getServerLogsCookie());
                this.mEventLog.logPathImpression(0, 791, this);
            }
            footerText.setVisibility(8);
            PermissionAdapter appPermissionAdapter = new AppPermissionAdapter(this, packageName, permissions, hasAcceptedBuckets);
            if (!(hasAcceptedBuckets || ((Boolean) FinskyPreferences.hasSeenPermissionBucketsLearnMore.get()).booleanValue())) {
                View learnMore = findViewById(R.id.learn_more_header);
                learnMore.setVisibility(0);
                learnMoreLink = (String) G.permissionBucketsLearnMoreLink.get();
                if (!TextUtils.isEmpty(learnMoreLink)) {
                    str = learnMoreLink;
                    learnMore.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            AppsPermissionsActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        }
                    });
                }
            }
            int i = (appPermissionAdapter.isAppInstalled() && hasAcceptedBuckets) ? R.string.also_needs_access_to : R.string.needs_access_to;
            subHeader.setText(i);
            this.mContinueButton = (IconButtonGroup) findViewById(R.id.continue_button);
            this.mContinueButton.setText(getResources().getString(R.string.accept));
            this.mContinueButton.setBackendForLabel(3);
            this.mContinueButton.setOnClickListener(this);
            this.mContinueButton.setEnabled(true);
            adapter = appPermissionAdapter;
        }
        this.mPermissionsView.bindInfo(adapter, this.mDoc.getTitle(), this.mSavedInstanceState);
        this.mPermissionsView.requestFocus();
    }

    private void showLoading() {
        findViewById(R.id.contents).setVisibility(8);
        findViewById(R.id.loading_indicator).setVisibility(0);
        this.mEventLog.logPathImpression(0, 213, this);
    }

    private void hideLoading() {
        findViewById(R.id.contents).setVisibility(0);
        findViewById(R.id.loading_indicator).setVisibility(8);
    }

    public void onClick(View view) {
        this.mEventLog.logClickEvent(792, null, this);
        FinskyPreferences.hasSeenPermissionBucketsLearnMore.put(Boolean.valueOf(true));
        finishWithResultOK(true);
    }

    private void finishWithResultOK(boolean acceptedNewBuckets) {
        Intent intent = new Intent();
        intent.putExtra("AppsPermissionsActivity.doc", this.mDoc);
        intent.putExtra("AppsPermissionsActivity.appVersion", this.mDoc.getAppDetails().versionCode);
        intent.putExtra("AppsPermissionsActivity.appTitle", this.mDoc.getTitle());
        intent.putExtra("AppsPermissionsActivity.appDownloadSizeWarningArguments", getDownloadWarningArguments());
        intent.putExtra("AppsPermissionsActivity.acceptedNewBuckets", acceptedNewBuckets);
        setResult(-1, intent);
        finish();
    }

    private Bundle getDownloadWarningArguments() {
        InstallPolicies installPolicies = FinskyApp.get().getInstallPolicies();
        long maxRecommendedBytes = installPolicies.getMaxBytesOverMobileRecommended();
        long maxLimitBytes = installPolicies.getMaxBytesOverMobile();
        AppDetails appDetails = this.mDoc.getAppDetails();
        if (appDetails == null || !installPolicies.hasMobileNetwork() || maxRecommendedBytes <= 0 || AssetUtils.totalDeliverySize(appDetails) < maxRecommendedBytes) {
            return null;
        }
        return DownloadSizeDialogFragment.makeArguments(true, this.mDoc.getAppDetails().installationSize < maxLimitBytes, installPolicies.isMobileNetwork());
    }

    public void onDataChanged() {
        this.mDoc = this.mDfeDetails.getDocument();
        if (this.mDoc == null) {
            showError(getString(R.string.item_unavailable_message));
        } else if (AppActionAnalyzer.isMultiUserCertificateConflict(this.mDoc)) {
            showError(getString(R.string.app_already_installed_other_user));
        } else {
            if (!this.mShowDetailedPermissions) {
                if (!LibraryUtils.isAvailable(this.mDoc, FinskyApp.get().getToc(), FinskyApp.get().getLibraries().getAccountLibrary(AccountHandler.findAccount(this.mAccountName, this)))) {
                    showError(getString(DocUtils.getAvailabilityRestrictionResourceId(this.mDoc)));
                    return;
                }
            }
            updateFromDoc();
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        showError(ErrorStrings.get(this, volleyError));
    }

    private void showError(String message) {
        Builder builder = new Builder();
        builder.setMessage(message).setPositiveId(R.string.ok);
        builder.build().show(getSupportFragmentManager(), "AppsPermissionsActivity.errorDialog");
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        setResult(0);
        finish();
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
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
