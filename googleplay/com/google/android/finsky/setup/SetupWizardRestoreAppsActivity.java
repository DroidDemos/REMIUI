package com.google.android.finsky.setup;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.Restore.BackupDeviceInfo;
import com.google.android.finsky.protos.Restore.BackupDocumentInfo;
import com.google.android.finsky.services.RestoreService;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.finsky.utils.SetupWizardUtils.SetupWizardParams;
import com.google.protobuf.nano.MessageNano;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SetupWizardRestoreAppsActivity extends FragmentActivity implements Listener, SidecarFragment.Listener, PlayStoreUiElementNode {
    private static final PlayStoreUiElement mUiElementProto;
    private String mAccountName;
    private SetupWizardRestoreAppsSelector mAppListSelector;
    private final OnClickListener mAppsMenuOnClickListener;
    private BackupDeviceInfo[] mBackupDeviceInfos;
    private int mCurrentDevicePosition;
    private boolean[] mCurrentSelectedBackupDocs;
    private SetupWizardRestoreAppsSelector mDeviceListSelector;
    private final OnClickListener mDeviceMenuOnClickListener;
    private FinskyEventLog mEventLogger;
    private boolean mIsBackupDocsLoaded;
    private boolean mIsCreatedFinished;
    protected ViewGroup mMainView;
    private Parcelable[] mParcelableBackupDeviceInfos;
    private SetupWizardParams mSetupWizardParams;
    private boolean mShowAppDialogPostLoad;
    private RestoreAppsSidecar mSidecar;

    public SetupWizardRestoreAppsActivity() {
        this.mDeviceMenuOnClickListener = getDeviceMenuOnClickListener();
        this.mAppsMenuOnClickListener = getAppsMenuOnClickListener();
    }

    static {
        mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(2500);
    }

    public static Intent createIntent(String accountName, BackupDeviceInfo[] backupDeviceInfos) {
        DfeApi dfeApi = FinskyApp.get().getDfeApi(accountName);
        Intent intent = new Intent(FinskyApp.get(), SetupWizardRestoreAppsActivity.class);
        intent.putExtra("authAccount", accountName);
        ParcelableProto<BackupDeviceInfo>[] parcelableBackupDeviceInfos = new ParcelableProto[backupDeviceInfos.length];
        for (int i = 0; i < backupDeviceInfos.length; i++) {
            parcelableBackupDeviceInfos[i] = ParcelableProto.forProto(backupDeviceInfos[i]);
        }
        Bundle backupDeviceBundle = new Bundle();
        backupDeviceBundle.putParcelableArray("SetupWizardRestoreAppsActivity.backup_device_infos", parcelableBackupDeviceInfos);
        intent.putExtra("SetupWizardRestoreAppsActivity.backup_device_infos_bundle", backupDeviceBundle);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.mSetupWizardParams = new SetupWizardParams(intent);
        setTheme(this.mSetupWizardParams.isLightTheme() ? R.style.SetupWizardTheme.Light : R.style.SetupWizardTheme);
        this.mAccountName = intent.getStringExtra("authAccount");
        this.mEventLogger = FinskyApp.get().getEventLogger(this.mAccountName);
        this.mParcelableBackupDeviceInfos = intent.getBundleExtra("SetupWizardRestoreAppsActivity.backup_device_infos_bundle").getParcelableArray("SetupWizardRestoreAppsActivity.backup_device_infos");
        this.mBackupDeviceInfos = new BackupDeviceInfo[this.mParcelableBackupDeviceInfos.length];
        for (int i = 0; i < this.mParcelableBackupDeviceInfos.length; i++) {
            this.mBackupDeviceInfos[i] = (BackupDeviceInfo) ((ParcelableProto) this.mParcelableBackupDeviceInfos[i]).getPayload();
        }
        if (savedInstanceState != null) {
            this.mCurrentDevicePosition = savedInstanceState.getInt("SetupWizardRestoreAppsActivity.current_device", 0);
            this.mIsBackupDocsLoaded = savedInstanceState.getBoolean("SetupWizardRestoreAppsActivity.backup_docs_loaded", false);
            this.mCurrentSelectedBackupDocs = savedInstanceState.getBooleanArray("SetupWizardRestoreAppsActivity.current_selected_backup_docs");
        } else {
            this.mEventLogger.logPathImpression(0, this);
        }
        this.mSidecar = (RestoreAppsSidecar) getSupportFragmentManager().findFragmentByTag("SetupWizardRestoreAppsActivity.sidecar");
        if (this.mSidecar == null) {
            this.mSidecar = RestoreAppsSidecar.newInstance(this.mAccountName);
            getSupportFragmentManager().beginTransaction().add(this.mSidecar, "SetupWizardRestoreAppsActivity.sidecar").commit();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SetupWizardRestoreAppsActivity.current_device", this.mCurrentDevicePosition);
        outState.putBooleanArray("SetupWizardRestoreAppsActivity.current_selected_backup_docs", this.mCurrentSelectedBackupDocs);
        outState.putBoolean("SetupWizardRestoreAppsActivity.backup_docs_loaded", this.mIsBackupDocsLoaded);
    }

    public void onStart() {
        super.onStart();
        this.mSidecar.setListener(this);
    }

    public void onStop() {
        this.mSidecar.setListener(null);
        super.onStop();
    }

    private void finishCreate() {
        if (!this.mIsCreatedFinished) {
            this.mIsCreatedFinished = true;
            LayoutInflater inflater = LayoutInflater.from(this);
            this.mMainView = (ViewGroup) inflater.inflate(R.layout.setup_wizard_play_frame, null);
            setContentView(this.mMainView);
            ((TextView) this.mMainView.findViewById(R.id.title)).setText(R.string.setup_wizard_restore_apps_header);
            ((ViewGroup) this.mMainView.findViewById(R.id.content_frame)).addView(inflater.inflate(R.layout.setup_wizard_restore_apps_view, this.mMainView, false));
            SetupWizardUtils.configureBasicUi(this, this.mSetupWizardParams, 1, false, false, false);
            this.mDeviceListSelector = (SetupWizardRestoreAppsSelector) findViewById(R.id.setup_wizard_device_list_selector);
            syncDeviceSelector();
            configureDeviceMenuClickListener(true);
            this.mAppListSelector = (SetupWizardRestoreAppsSelector) findViewById(R.id.setup_wizard_apps_list_selector);
            syncAppSelector();
            configureAppsMenuClickListener(true);
            configureNavNextButton();
        }
    }

    private void showAppListDialog() {
        if (!shouldSetupAsNewDevice()) {
            int themeId = this.mSetupWizardParams.isLightTheme() ? R.style.SetupWizardTheme.Light : R.style.SetupWizardTheme;
            Builder builder = new Builder();
            builder.setLayoutId(R.layout.setup_wizard_restore_app_list_dialog).setPositiveId(R.string.done).setNegativeId(R.string.cancel).setThemeId(themeId).setEventLog(2505, null, 2506, 2507, FinskyApp.get().getCurrentAccount()).setCallback(null, 2, null);
            BackupDocumentInfo[] backupDocumentInfos = this.mSidecar.getBackupDocumentInfos();
            ArrayList<Parcelable> parcelableBackupDocumentInfos = new ArrayList(backupDocumentInfos.length);
            for (MessageNano forProto : backupDocumentInfos) {
                parcelableBackupDocumentInfos.add(ParcelableProto.forProto(forProto));
            }
            Bundle argumentsBundle = new Bundle();
            argumentsBundle.putParcelableArrayList("SetupWizardAppListDialog.backupDocs", parcelableBackupDocumentInfos);
            argumentsBundle.putBooleanArray("SetupWizardAppListDialog.selectedBackupDocs", this.mCurrentSelectedBackupDocs);
            builder.setViewConfiguration(argumentsBundle);
            builder.build().show(getSupportFragmentManager(), "SetupWizardRestoreAppsActivity.backupAppsDialog");
        }
    }

    private void restoreSelectedPackages() {
        if (this.mSidecar.getState() == 5) {
            int selectedAppsCount = getSelectedAppsCount();
            String[] packageNames = new String[selectedAppsCount];
            int[] versionCodes = new int[selectedAppsCount];
            String[] titles = new String[selectedAppsCount];
            int[] priorities = new int[selectedAppsCount];
            String[] appIconUrls = new String[selectedAppsCount];
            BackupDocumentInfo[] currentBackupDocumentInfos = this.mSidecar.getBackupDocumentInfos();
            int curPosition = 0;
            for (int i = 0; i < currentBackupDocumentInfos.length; i++) {
                if (this.mCurrentSelectedBackupDocs[i]) {
                    BackupDocumentInfo documentInfo = currentBackupDocumentInfos[i];
                    packageNames[curPosition] = documentInfo.docid.backendDocid;
                    versionCodes[curPosition] = documentInfo.versionCode;
                    titles[curPosition] = documentInfo.title;
                    priorities[curPosition] = 3;
                    if (documentInfo.hasRestorePriority && documentInfo.restorePriority < 100) {
                        priorities[curPosition] = 1;
                    }
                    if (documentInfo.thumbnailImage != null && documentInfo.thumbnailImage.hasImageUrl && documentInfo.thumbnailImage.hasSupportsFifeUrlOptions && documentInfo.thumbnailImage.supportsFifeUrlOptions) {
                        appIconUrls[curPosition] = documentInfo.thumbnailImage.imageUrl;
                    } else {
                        appIconUrls[curPosition] = null;
                    }
                    curPosition++;
                }
            }
            RestoreService.restorePackages(getApplicationContext(), true, this.mAccountName, true, packageNames, versionCodes, titles, priorities, null, appIconUrls);
        }
    }

    private int getSelectedAppsCount() {
        int selectedAppsCount = 0;
        for (boolean isSelected : this.mCurrentSelectedBackupDocs) {
            if (isSelected) {
                selectedAppsCount++;
            }
        }
        return selectedAppsCount;
    }

    private void setResultAndFinish(int result) {
        setResult(result);
        finish();
    }

    private boolean shouldSetupAsNewDevice() {
        return this.mCurrentDevicePosition == -1;
    }

    private void syncDeviceSelector() {
        if (shouldSetupAsNewDevice()) {
            this.mDeviceListSelector.setTexts(getResources().getString(R.string.setup_wizard_setup_as_new_option));
            return;
        }
        String lastUsedTitle;
        long days = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - this.mBackupDeviceInfos[this.mCurrentDevicePosition].lastCheckinTimeMs);
        Resources resources = getResources();
        if (days == 0) {
            lastUsedTitle = resources.getString(R.string.setup_wizard_device_last_used_today_message);
        } else {
            lastUsedTitle = getResources().getQuantityString(R.plurals.setup_wizard_device_last_used_message, (int) days, new Object[]{Long.valueOf(days)});
        }
        this.mDeviceListSelector.setTexts(this.mBackupDeviceInfos[this.mCurrentDevicePosition].name, lastUsedTitle);
    }

    private void syncAppSelector() {
        if (this.mAppListSelector != null) {
            TextView appListSelectorTitle = (TextView) findViewById(R.id.setup_wizard_apps_selection_title);
            Resources resources = getResources();
            if (shouldSetupAsNewDevice()) {
                this.mAppListSelector.setVisibility(8);
                appListSelectorTitle.setVisibility(8);
                return;
            }
            this.mAppListSelector.setVisibility(0);
            appListSelectorTitle.setVisibility(0);
            switch (this.mSidecar.getState()) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                    this.mAppListSelector.setTexts(resources.getString(R.string.loading));
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    int selectedAppsCount = getSelectedAppsCount();
                    if (selectedAppsCount == this.mSidecar.getBackupDocumentInfos().length) {
                        this.mAppListSelector.setTexts(resources.getString(R.string.setup_wizard_all_apps), Integer.toString(selectedAppsCount));
                        return;
                    } else if (selectedAppsCount == 0) {
                        this.mAppListSelector.setTexts(resources.getString(R.string.setup_wizard_no_apps_selected));
                        return;
                    } else {
                        this.mAppListSelector.setTexts(resources.getQuantityString(R.plurals.setup_wizard_selected_apps, selectedAppsCount), Integer.toString(selectedAppsCount));
                        return;
                    }
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    this.mAppListSelector.setTexts(resources.getQuantityString(R.plurals.setup_wizard_apps_loading_error_menu_title, this.mBackupDeviceInfos.length));
                    return;
                default:
                    return;
            }
        }
    }

    private void configureDeviceMenuClickListener(boolean enable) {
        if (this.mDeviceListSelector != null) {
            this.mDeviceListSelector.setOnClickListener(enable ? this.mDeviceMenuOnClickListener : null);
        }
    }

    private void configureAppsMenuClickListener(boolean enable) {
        if (this.mAppListSelector != null) {
            this.mAppListSelector.setOnClickListener(enable ? this.mAppsMenuOnClickListener : null);
        }
    }

    private void configureNavNextButton() {
        final SetupWizardNavBar setupWizardNavBar = SetupWizardUtils.getNavBarIfPossible(this);
        setupWizardNavBar.getNextButton().setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                setupWizardNavBar.getNextButton().setOnClickListener(null);
                if (SetupWizardRestoreAppsActivity.this.shouldSetupAsNewDevice()) {
                    SetupWizardRestoreAppsActivity.this.setResultAndFinish(-1);
                    return;
                }
                SetupWizardRestoreAppsActivity.this.restoreSelectedPackages();
                Intent intent = new Intent();
                intent.putExtra("restoreToken", SetupWizardRestoreAppsActivity.this.mBackupDeviceInfos[SetupWizardRestoreAppsActivity.this.mCurrentDevicePosition].restoreToken);
                SetupWizardRestoreAppsActivity.this.setResult(-1, intent);
                SetupWizardRestoreAppsActivity.this.finish();
            }
        });
    }

    private OnClickListener getAppsMenuOnClickListener() {
        return new OnClickListener() {
            public void onClick(View v) {
                SetupWizardRestoreAppsActivity.this.mEventLogger.logClickEvent(2510, null, SetupWizardRestoreAppsActivity.this);
                if (SetupWizardRestoreAppsActivity.this.mSidecar.getState() == 5) {
                    SetupWizardRestoreAppsActivity.this.showAppListDialog();
                } else if (!SetupWizardRestoreAppsActivity.this.shouldSetupAsNewDevice()) {
                    long androidId = SetupWizardRestoreAppsActivity.this.mBackupDeviceInfos[SetupWizardRestoreAppsActivity.this.mCurrentDevicePosition].androidId;
                    SetupWizardRestoreAppsActivity.this.mIsBackupDocsLoaded = false;
                    SetupWizardRestoreAppsActivity.this.mShowAppDialogPostLoad = true;
                    SetupWizardRestoreAppsActivity.this.mSidecar.fetchBackupDocs(androidId);
                }
            }
        };
    }

    private OnClickListener getDeviceMenuOnClickListener() {
        return new OnClickListener() {
            public void onClick(View v) {
                SetupWizardRestoreAppsActivity.this.mEventLogger.logClickEvent(2509, null, SetupWizardRestoreAppsActivity.this);
                int themeId = SetupWizardRestoreAppsActivity.this.mSetupWizardParams.isLightTheme() ? R.style.SetupWizardTheme.Light : R.style.SetupWizardTheme;
                Builder builder = new Builder();
                builder.setLayoutId(R.layout.setup_wizard_restore_device_list_dialog).setPositiveId(R.string.done).setNegativeId(R.string.cancel).setThemeId(themeId).setEventLog(2501, null, 2503, 2504, FinskyApp.get().getCurrentAccount()).setCallback(null, 1, null);
                Bundle argumentsBundle = new Bundle();
                argumentsBundle.putInt("SetupWizardRestoreDeviceDialogView.selectedDevicePosition", SetupWizardRestoreAppsActivity.this.mCurrentDevicePosition);
                argumentsBundle.putParcelableArray("SetupWizardRestoreDeviceDialogView.selectedDevices", SetupWizardRestoreAppsActivity.this.mParcelableBackupDeviceInfos);
                builder.setViewConfiguration(argumentsBundle);
                builder.build().show(SetupWizardRestoreAppsActivity.this.getSupportFragmentManager(), "SetupWizardRestoreAppsActivity.backupDeviceDialog");
            }
        };
    }

    public void onStateChange(SidecarFragment fragment) {
        if (fragment != this.mSidecar) {
            FinskyLog.wtf("Received state change for unknown fragment: %s", fragment);
            return;
        }
        switch (this.mSidecar.getState()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                this.mSidecar.fetchBackupDocs(this.mBackupDeviceInfos[this.mCurrentDevicePosition].androidId);
                finishCreate();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                finishCreate();
                configureDeviceMenuClickListener(false);
                configureAppsMenuClickListener(false);
                syncAppSelector();
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                finishCreate();
                configureDeviceMenuClickListener(true);
                configureAppsMenuClickListener(true);
                BackupDocumentInfo[] backupDocumentInfo = this.mSidecar.getBackupDocumentInfos();
                if (!this.mIsBackupDocsLoaded) {
                    this.mCurrentSelectedBackupDocs = new boolean[backupDocumentInfo.length];
                    for (int i = 0; i < backupDocumentInfo.length; i++) {
                        this.mCurrentSelectedBackupDocs[i] = true;
                    }
                    this.mIsBackupDocsLoaded = true;
                }
                syncAppSelector();
                if (this.mShowAppDialogPostLoad) {
                    showAppListDialog();
                    this.mShowAppDialogPostLoad = false;
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                finishCreate();
                configureDeviceMenuClickListener(true);
                configureAppsMenuClickListener(false);
                syncAppSelector();
                return;
            default:
                return;
        }
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                int newSelection = extraArguments.getInt("SetupWizardRestoreDeviceDialogView.selectedDevicePosition");
                if (newSelection != this.mCurrentDevicePosition) {
                    this.mCurrentDevicePosition = newSelection;
                    if (shouldSetupAsNewDevice()) {
                        this.mIsBackupDocsLoaded = true;
                    } else {
                        this.mEventLogger.logClickEvent(2502, null, getParentNode());
                        configureDeviceMenuClickListener(false);
                        this.mIsBackupDocsLoaded = false;
                        this.mSidecar.fetchBackupDocs(this.mBackupDeviceInfos[newSelection].androidId);
                    }
                    syncDeviceSelector();
                    syncAppSelector();
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                boolean[] currentSelection = extraArguments.getBooleanArray("SetupWizardAppListDialog.selectedBackupDocs");
                if (currentSelection.length != this.mCurrentSelectedBackupDocs.length) {
                    FinskyLog.wtf("Length mismatched, can't update", new Object[0]);
                    return;
                }
                this.mCurrentSelectedBackupDocs = currentSelection;
                syncAppSelector();
                return;
            default:
                return;
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return null;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        throw new UnsupportedOperationException("Unwanted children.");
    }
}
