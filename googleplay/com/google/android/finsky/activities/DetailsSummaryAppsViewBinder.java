package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.adapters.DownloadProgressHelper;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.receivers.ExpireLaunchUrlReceiver;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerProgressReport;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import com.google.android.finsky.utils.AppSupport;
import com.google.android.finsky.utils.AppSupport.RefundListener;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayActionButton;

public class DetailsSummaryAppsViewBinder extends DetailsSummaryViewBinder implements InstallerListener, PackageStatusListener {
    private final AppStates mAppStates;
    private final Installer mInstaller;
    private final Libraries mLibraries;
    private boolean mListenersAdded;
    private final PackageMonitorReceiver mPackageMonitorReceiver;
    private boolean mTrackPackageStatus;

    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState;

        static {
            $SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState = new int[InstallerState.values().length];
            try {
                $SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState[InstallerState.INSTALLING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState[InstallerState.UNINSTALLING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState[InstallerState.NOT_TRACKED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public DetailsSummaryAppsViewBinder(DfeToc dfeToc, Account currentAccount, PackageMonitorReceiver packageMonitorReceiver, Installer installer, AppStates appStates, Libraries libraries) {
        super(dfeToc, currentAccount);
        this.mPackageMonitorReceiver = packageMonitorReceiver;
        this.mInstaller = installer;
        this.mAppStates = appStates;
        this.mLibraries = libraries;
    }

    public void init(Context context, NavigationManager navManager, BitmapLoader bitmapLoader, PageFragment fragment, boolean trackPackageStatus, String continueUrl, String revealTransitionCoverName, PlayStoreUiElementNode parentNode) {
        super.init(context, navManager, bitmapLoader, fragment, trackPackageStatus, continueUrl, revealTransitionCoverName, parentNode);
        this.mTrackPackageStatus = trackPackageStatus;
        attachListeners();
    }

    private void attachListeners() {
        if (this.mTrackPackageStatus) {
            this.mPackageMonitorReceiver.detach(this);
            this.mPackageMonitorReceiver.attach(this);
            if (!this.mListenersAdded) {
                this.mInstaller.addListener(this);
                this.mListenersAdded = true;
            }
        }
    }

    public void bind(Document document, boolean bindDynamicSection, View... views) {
        super.bind(document, bindDynamicSection, views);
        attachListeners();
    }

    public void onDestroyView() {
        this.mPackageMonitorReceiver.detach(this);
        if (this.mListenersAdded) {
            this.mInstaller.removeListener(this);
            this.mListenersAdded = false;
        }
        super.onDestroyView();
    }

    protected void syncDynamicSection() {
        if (this.mDoc.getBackend() != 3) {
            FinskyLog.wtf("Unexpected doc backend %s", Integer.valueOf(this.mDoc.getBackend()), this.mDoc);
            super.syncDynamicSection();
            return;
        }
        final String packageName = this.mDoc.getAppDetails().packageName;
        if (!TextUtils.isEmpty(packageName)) {
            final ViewGroup downloadSection = (ViewGroup) this.mDynamicSection.findViewById(R.id.download_progress_panel);
            InstallerProgressReport progressReport = this.mInstaller.getProgress(packageName);
            switch (AnonymousClass8.$SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState[progressReport.installerState.ordinal()]) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    showDynamicStatus(R.string.installing);
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    showDynamicStatus(R.string.uninstalling);
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    downloadSection.setVisibility(4);
                    super.syncDynamicSection();
                    return;
                default:
                    downloadSection.setVisibility(0);
                    DownloadProgressHelper.configureDownloadProgressUi(this.mContext, progressReport, (TextView) downloadSection.findViewById(R.id.downloading_bytes), (TextView) downloadSection.findViewById(R.id.downloading_percentage), (ProgressBar) downloadSection.findViewById(R.id.progress_bar));
                    ((ImageView) downloadSection.findViewById(R.id.cancel_download)).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            DetailsSummaryAppsViewBinder.this.mInstaller.cancel(packageName);
                            downloadSection.setVisibility(4);
                        }
                    });
                    ((TextView) findViewById(R.id.title_title)).setSelected(false);
                    hideNonDynamicViews();
                    return;
            }
        }
    }

    protected void showDynamicStatus(int statusStringId) {
        super.showDynamicStatus(statusStringId);
        this.mDynamicSection.findViewById(R.id.download_progress_panel).setVisibility(4);
        hideNonDynamicViews();
    }

    private void hideNonDynamicViews() {
        setupActionButtons(true);
    }

    protected void setupActionButtons(boolean isInTransientState) {
        PlayActionButton buyButton = (PlayActionButton) findViewById(R.id.buy_button);
        PlayActionButton launchButton = (PlayActionButton) findViewById(R.id.launch_button);
        PlayActionButton uninstallButton = (PlayActionButton) findViewById(R.id.uninstall_button);
        PlayActionButton updateButton = (PlayActionButton) findViewById(R.id.update_button);
        launchButton.setVisibility(8);
        buyButton.setVisibility(8);
        uninstallButton.setVisibility(8);
        updateButton.setVisibility(8);
        if (!this.mHideDynamicFeatures && !isInTransientState) {
            final String appPackageName = this.mDoc.getAppDetails().packageName;
            final AppActionAnalyzer actions = new AppActionAnalyzer(appPackageName, this.mAppStates, this.mLibraries);
            int numButtons = 0;
            if (actions.isUninstallable) {
                int i;
                final boolean appHasSubscriptions = DocUtils.hasAutoRenewingSubscriptions(this.mLibraries, appPackageName);
                uninstallButton.setVisibility(0);
                numButtons = 0 + 1;
                int backend = this.mDoc.getBackend();
                if (actions.isRefundable) {
                    i = R.string.refund;
                } else {
                    i = R.string.uninstall;
                }
                uninstallButton.configure(backend, i, new OnClickListener() {
                    public void onClick(View v) {
                        DetailsSummaryAppsViewBinder.this.mEventLogger.logClickEvent(215, null, DetailsSummaryAppsViewBinder.this.mParentNode);
                        DetailsSummaryAppsViewBinder.this.refundAndUninstallAsset(appPackageName, actions.isRefundable, actions.refundAccount, actions.isInstalledSystemApp, actions.isInstalledOwnedPackage, appHasSubscriptions);
                    }
                });
            } else if (!actions.isUninstallable && actions.isActiveDeviceAdmin) {
                uninstallButton.setVisibility(0);
                numButtons = 0 + 1;
                uninstallButton.configure(this.mDoc.getBackend(), (int) R.string.deactivate, new OnClickListener() {
                    public void onClick(View v) {
                        FragmentManager fragmentManager = DetailsSummaryAppsViewBinder.this.mContainerFragment.getFragmentManager();
                        if (fragmentManager.findFragmentByTag("deactivate_dialog") == null) {
                            DetailsSummaryAppsViewBinder.this.mEventLogger.logClickEvent(216, null, DetailsSummaryAppsViewBinder.this.mParentNode);
                            Builder builder = new Builder();
                            builder.setMessageId(R.string.deactivate_device_admin_msg).setPositiveId(R.string.ok);
                            builder.build().show(fragmentManager, "deactivate_dialog");
                        }
                    }
                });
            } else if (actions.isRefundable) {
                uninstallButton.setVisibility(0);
                numButtons = 0 + 1;
                uninstallButton.configure(this.mDoc.getBackend(), (int) R.string.refund, new OnClickListener() {
                    public void onClick(View v) {
                        DetailsSummaryAppsViewBinder.this.mEventLogger.logClickEvent(214, null, DetailsSummaryAppsViewBinder.this.mParentNode);
                        DetailsSummaryAppsViewBinder.this.confirmRefundApp(appPackageName, actions.refundAccount, false);
                    }
                });
            }
            Account installAccount = AppActionAnalyzer.getInstallAccount(appPackageName, this.mAccount, this.mAppStates, this.mLibraries);
            AccountLibrary installAccountLibrary = this.mLibraries.getAccountLibrary(installAccount);
            if ((actions.hasUpdateAvailable(this.mDoc) || actions.hasConversionUpdateAvailable(this.mDoc)) && LibraryUtils.isAvailable(this.mDoc, this.mDfeToc, installAccountLibrary) && !actions.isDisabled) {
                updateButton.setVisibility(0);
                updateButton.configure(this.mDoc.getBackend(), R.string.update, this.mNavigationManager.getBuyImmediateClickListener(installAccount, this.mDoc, 1, null, this.mContinueUrl, 217, null));
                numButtons++;
            }
            if (numButtons < 2) {
                OnClickListener clickHandler = null;
                launchButton.setVisibility(0);
                int launchButtonTextId = -1;
                if (actions.isLaunchable) {
                    if (actions.isContinueLaunch) {
                        launchButtonTextId = R.string.continue_text;
                        clickHandler = new OnClickListener() {
                            public void onClick(View v) {
                                DetailsSummaryAppsViewBinder.this.mEventLogger.logClickEvent(219, null, DetailsSummaryAppsViewBinder.this.mParentNode);
                                DetailsSummaryAppsViewBinder.this.mNavigationManager.openItem(DetailsSummaryAppsViewBinder.this.mAccount, DetailsSummaryAppsViewBinder.this.mDoc);
                                ExpireLaunchUrlReceiver.cancel(DetailsSummaryAppsViewBinder.this.mContext, DetailsSummaryAppsViewBinder.this.mDoc.getDocId());
                                FinskyApp.get().getInstallerDataStore().setContinueUrl(DetailsSummaryAppsViewBinder.this.mDoc.getDocId(), null);
                            }
                        };
                    } else {
                        launchButtonTextId = R.string.open;
                        clickHandler = this.mNavigationManager.getOpenClickListener(this.mDoc, this.mAccount, this.mContainerFragment);
                    }
                } else if (actions.isDisabled) {
                    clickHandler = new OnClickListener() {
                        public void onClick(View v) {
                            DetailsSummaryAppsViewBinder.this.mEventLogger.logClickEvent(220, null, DetailsSummaryAppsViewBinder.this.mParentNode);
                            DetailsSummaryAppsViewBinder.this.mContext.getPackageManager().setApplicationEnabledSetting(DetailsSummaryAppsViewBinder.this.mDoc.getDocId(), 1, 0);
                        }
                    };
                    launchButtonTextId = R.string.enable;
                } else {
                    launchButton.setVisibility(8);
                }
                if (launchButton.getVisibility() == 0) {
                    launchButton.configure(this.mDoc.getBackend(), launchButtonTextId, clickHandler);
                }
            }
            if (!actions.isInstalled && LibraryUtils.isAvailable(this.mDoc, this.mDfeToc, this.mLibraries)) {
                Account owner = LibraryUtils.getOwnerWithCurrentAccount(this.mDoc, FinskyApp.get().getLibraries(), this.mAccount);
                buyButton.setVisibility(0);
                boolean isOwned = owner != null;
                int logElementType = getBuyButtonLoggingElementType(isOwned, 1);
                buyButton.configure(this.mDoc.getBackend(), getBuyButtonString(isOwned, 1), this.mNavigationManager.getBuyImmediateClickListener(installAccount, this.mDoc, 1, null, this.mContinueUrl, logElementType, null));
            }
            updateContainerLayouts();
        }
    }

    private void updateContainerLayouts() {
        syncButtonSectionVisibility();
        if (this.mButtonSection.getVisibility() == 0) {
            ((TextView) this.mDynamicSection.findViewById(R.id.summary_dynamic_status)).setVisibility(4);
        }
    }

    private void refundAndUninstallAsset(String packageName, boolean isRefundable, String appRefundAccount, boolean isSystemPackage, boolean isOwned, boolean hasSubscriptions) {
        if (isRefundable) {
            confirmRefundApp(packageName, appRefundAccount, true);
        } else {
            uninstallAsset(packageName, true, isSystemPackage, isOwned, hasSubscriptions);
        }
    }

    private void confirmRefundApp(String packageName, String appRefundAccount, boolean tryUninstall) {
        FragmentManager fragmentManager = this.mContainerFragment.getFragmentManager();
        if (fragmentManager.findFragmentByTag("refund_confirm") == null) {
            Builder builder = new Builder();
            builder.setMessageId(R.string.uninstall_refund_confirmation_body).setPositiveId(R.string.yes).setNegativeId(R.string.no);
            Bundle extraArguments = new Bundle();
            extraArguments.putString("package_name", packageName);
            extraArguments.putString("account_name", appRefundAccount);
            extraArguments.putBoolean("try_uninstall", tryUninstall);
            builder.setCallback(this.mContainerFragment, 4, extraArguments);
            builder.build().show(fragmentManager, "refund_confirm");
        }
    }

    private void refundApp(Bundle extraArguments) {
        AppSupport.silentRefund(this.mContainerFragment.getFragmentManager(), extraArguments.getString("package_name"), extraArguments.getString("account_name"), extraArguments.getBoolean("try_uninstall"), new RefundListener() {
            public void onRefundStart() {
                DetailsSummaryAppsViewBinder.this.mIsPendingRefund = true;
                DetailsSummaryAppsViewBinder.this.refresh();
            }

            public void onRefundComplete(boolean succeeded) {
                DetailsSummaryAppsViewBinder.this.mIsPendingRefund = false;
                DetailsSummaryAppsViewBinder.this.refresh();
            }
        });
    }

    private void uninstallAsset(String packageName, boolean showConfirmationDialog, boolean isSystemPackage, boolean isOwned, boolean hasActiveSubscriptions) {
        if (showConfirmationDialog) {
            AppSupport.showUninstallConfirmationDialog(packageName, this.mContainerFragment, isSystemPackage, isOwned, hasActiveSubscriptions);
            return;
        }
        this.mInstaller.uninstallAssetSilently(packageName);
        refresh();
    }

    private void refreshByPackageName(String packageName) {
        if (this.mDoc != null && this.mDoc.getAppDetails() != null && this.mDoc.getAppDetails().packageName.equals(packageName)) {
            syncDynamicSection();
            if (this.mContainerFragment instanceof DetailsFragment) {
                this.mContainerFragment.onDataChanged();
            }
        }
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        super.onPositiveClick(requestCode, extraArguments);
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                String packageName = extraArguments.getString("package_name");
                if (this.mInstaller != null) {
                    this.mInstaller.uninstallAssetSilently(packageName);
                    refresh();
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                refundApp(extraArguments);
                return;
            default:
                FinskyLog.wtf("Unexpected requestCode %d", Integer.valueOf(requestCode));
                return;
        }
    }

    public void onPackageAdded(String packageName) {
        refreshByPackageName(packageName);
    }

    public void onPackageChanged(String packageName) {
        refreshByPackageName(packageName);
    }

    public void onPackageAvailabilityChanged(String[] packageNames, boolean available) {
    }

    public void onPackageRemoved(String packageName, boolean replacing) {
        if (this.mContainerFragment instanceof DetailsFragment) {
            this.mContainerFragment.onDataChanged();
        }
    }

    public void onPackageFirstLaunch(String packageName) {
    }

    public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
        if (this.mDoc != null && packageName.equals(this.mDoc.getAppDetails().packageName)) {
            listenerRefresh();
        }
    }

    private void listenerRefresh() {
        if (this.mContainerFragment.isAdded()) {
            refresh();
        }
    }
}
