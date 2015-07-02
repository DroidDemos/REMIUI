package com.google.android.finsky;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeNotificationManager;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.billing.iab.MarketBillingService;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Accounts;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.protos.AckNotification.AckNotificationResponse;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryMutation;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.finsky.protos.Notifications.AndroidAppNotificationData;
import com.google.android.finsky.protos.Notifications.Notification;
import com.google.android.finsky.protos.Notifications.UserNotificationData;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Notifier;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class DfeNotificationManagerImpl implements DfeNotificationManager {
    private final Accounts mAccounts;
    private final AppStates mAppStates;
    private final Context mContext;
    private final List<String> mHandledNotifications;
    private final Installer mInstaller;
    private final LibraryReplicators mLibraryReplicators;
    private final Notifier mNotifier;
    private final List<String> mPendingAcks;

    public DfeNotificationManagerImpl(Context context, Installer installer, Notifier notifier, AppStates appStates, LibraryReplicators libraryReplicators, Accounts accounts) {
        this.mHandledNotifications = Lists.newArrayList();
        this.mPendingAcks = Lists.newArrayList();
        this.mInstaller = installer;
        this.mNotifier = notifier;
        this.mContext = context;
        this.mAppStates = appStates;
        this.mLibraryReplicators = libraryReplicators;
        this.mAccounts = accounts;
        loadPendingAcks();
    }

    public void processNotification(final Notification notification) {
        if (Looper.myLooper() == Looper.getMainLooper() && this.mAppStates.isLoaded()) {
            handleNotification(notification);
        } else {
            this.mAppStates.load(new Runnable() {
                public void run() {
                    DfeNotificationManagerImpl.this.handleNotification(notification);
                }
            });
        }
    }

    private void handleNotification(final Notification notification) {
        final String notificationId = notification.notificationId;
        if (this.mHandledNotifications.contains(notificationId)) {
            FinskyLog.d("Notification [%s] ignored, already handled.", notificationId);
            ackPendingNotifications(notificationId);
            return;
        }
        FinskyLog.d("Handling notification type=[%s], id=[%s]", Integer.valueOf(notification.notificationType), notificationId);
        Runnable postLibraryUpdateCallback = new Runnable() {
            public void run() {
                switch (notification.notificationType) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        DfeNotificationManagerImpl.this.handlePurchaseDeliveryNotification(notification);
                        break;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        DfeNotificationManagerImpl.this.handlePurchaseRemovalNotification(notification);
                        break;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        DfeNotificationManagerImpl.this.handlePurchaseDeclinedNotification(notification);
                        break;
                    case R.styleable.WalletImFormEditText_required /*4*/:
                        DfeNotificationManagerImpl.this.handleUserNotification(notification);
                        break;
                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                        DfeNotificationManagerImpl.this.handleInAppNotification(notification);
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        DfeNotificationManagerImpl.this.handleLibraryDirtyNotification(notification);
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                        DfeNotificationManagerImpl.this.handleCheckPromoOffersNotification(notification);
                        break;
                    default:
                        FinskyLog.e("Unhandled notification type [%s]", Integer.valueOf(notification.notificationType));
                        break;
                }
                DfeNotificationManagerImpl.this.mHandledNotifications.add(notificationId);
                while (DfeNotificationManagerImpl.this.mPendingAcks.size() >= 10) {
                    DfeNotificationManagerImpl.this.mPendingAcks.remove(0);
                }
                DfeNotificationManagerImpl.this.mPendingAcks.add(notificationId);
                DfeNotificationManagerImpl.this.savePendingAcks();
                DfeNotificationManagerImpl.this.ackPendingNotifications(notificationId);
            }
        };
        if (notification.libraryUpdate != null) {
            Account account = this.mAccounts.getAccount(notification.userEmail);
            if (account != null) {
                FinskyLog.d("Processing notification library update.", new Object[0]);
                if (containsIabMutations(notification.libraryUpdate)) {
                    FinskyLog.wtf("Ignoring notification LibraryUpdate with IAB mutations.", new Object[0]);
                } else {
                    this.mLibraryReplicators.applyLibraryUpdates(account, makeReplicatorDebugTag(notification), postLibraryUpdateCallback, libraryUpdate);
                    return;
                }
            }
            FinskyLog.d("Could not process library update for unknown account.", new Object[0]);
        }
        postLibraryUpdateCallback.run();
    }

    private String makeReplicatorDebugTag(Notification notification) {
        return "notification (type=[" + notification.notificationType + "],id=[" + notification.notificationId + "])";
    }

    private void handlePurchaseRemovalNotification(Notification notification) {
        boolean isMalicious;
        String packageName = notification.docid.backendDocid;
        if (notification.purchaseRemovalData == null || !notification.purchaseRemovalData.malicious) {
            isMalicious = false;
        } else {
            isMalicious = true;
        }
        String title = notification.docTitle;
        FinskyLog.d("Removing package '%s'. Malicious='%s'", packageName, Boolean.valueOf(isMalicious));
        PackageState packageState = this.mAppStates.getPackageStateRepository().get(packageName);
        AppData appData = null;
        if (packageState != null) {
            appData = new AppData();
            appData.oldVersion = packageState.installedVersion;
            appData.hasOldVersion = true;
            appData.systemApp = packageState.isSystemApp;
            appData.hasSystemApp = true;
        }
        FinskyApp.get().getEventLogger().logBackgroundEvent(202, packageName, null, 0, null, appData);
        if (packageState != null) {
            if (isMalicious) {
                this.mNotifier.showMaliciousAssetRemovedMessage(title, packageName);
            } else {
                this.mNotifier.showNormalAssetRemovedMessage(title, packageName);
            }
        }
        if (isMalicious) {
            this.mInstaller.uninstallPackagesByUid(packageName);
        } else {
            this.mInstaller.uninstallAssetSilently(packageName);
        }
    }

    private void handlePurchaseDeclinedNotification(Notification notification) {
        int reason = notification.purchaseDeclinedData.reason;
        FinskyLog.d("Received PURCHASE_DECLINED tickle for %s reason=%d", notification.docid.backendDocid, Integer.valueOf(reason));
        FinskyApp.get().getEventLogger().logBackgroundEvent(200, packageName, String.valueOf(reason), 0, null, null);
    }

    private void handlePurchaseDeliveryNotification(Notification notification) {
        AndroidAppNotificationData notificationData = notification.appData;
        if (notification.appData == null) {
            FinskyLog.d("Ignoring PurchaseDeliveryNotification because AppData was null.", new Object[0]);
        } else if (notification.appDeliveryData == null) {
            FinskyLog.d("Ignoring PurchaseDeliveryNotification because delivery data was null", new Object[0]);
        } else {
            String backendDocId = notification.docid.backendDocid;
            if (notification.appDeliveryData.serverInitiated) {
                String accountName = notification.userEmail;
                AppData appData = new AppData();
                appData.version = notificationData.versionCode;
                appData.hasVersion = true;
                FinskyApp.get().getEventLogger().logBackgroundEvent(201, backendDocId, null, 0, null, appData);
                this.mInstaller.requestInstall(backendDocId, notificationData.versionCode, accountName, notification.docTitle, false, "tickle", 2);
                return;
            }
            FinskyLog.d("Ignoring PurchaseDeliveryNotification with !server_initiated: pkg=%s", backendDocId);
        }
    }

    private void handleUserNotification(Notification notification) {
        UserNotificationData notificationData = notification.userNotificationData;
        this.mNotifier.showMessage(notificationData.notificationTitle, notificationData.tickerText, notificationData.notificationText);
    }

    private void handleInAppNotification(Notification notification) {
        MarketBillingService.sendNotify(this.mContext, notification.docid.backendDocid, notification.inAppNotificationData.inAppNotificationId);
    }

    private void handleLibraryDirtyNotification(Notification notification) {
        if (notification.libraryDirtyData == null) {
            FinskyLog.e("Received LibraryDirty notification without LibraryDirtyData: id=%s", notification.notificationId);
            return;
        }
        Account account = AccountHandler.findAccount(notification.userEmail, this.mContext);
        if (account == null) {
            FinskyLog.e("Received LibraryDirty notification for invalid account: id=%s, account=%s", notification.notificationId, FinskyLog.scrubPii(notification.userEmail));
            return;
        }
        String[] libraryIds = new String[1];
        if (notification.libraryDirtyData.libraryId.length() > 0) {
            libraryIds[0] = notification.libraryDirtyData.libraryId;
        } else {
            libraryIds[0] = AccountLibrary.getLibraryIdFromBackend(notification.libraryDirtyData.backend);
        }
        this.mLibraryReplicators.replicateAccount(account, libraryIds, null, "notification-" + notification.notificationId);
    }

    private void handleCheckPromoOffersNotification(Notification notification) {
        FinskyLog.d("Received CheckPromoOffers notification for account %s", FinskyLog.scrubPii(notification.userEmail));
        FinskyPreferences.checkPromoOffers.get(notification.userEmail).put(Boolean.valueOf(true));
    }

    private void ackPendingNotifications(String notificationId) {
        ackNotification(notificationId);
        for (String pendingAck : this.mPendingAcks) {
            if (!pendingAck.equals(notificationId)) {
                ackNotification(pendingAck);
            }
        }
    }

    private void ackNotification(final String notificationId) {
        FinskyApp.get().getDfeApi().ackNotification(notificationId, new Listener<AckNotificationResponse>() {
            public void onResponse(AckNotificationResponse response) {
                FinskyLog.d("Notification [%s] successfully ack'd.", notificationId);
                DfeNotificationManagerImpl.this.mPendingAcks.remove(notificationId);
                DfeNotificationManagerImpl.this.savePendingAcks();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.d("Error acking notification [%s]", notificationId);
            }
        });
    }

    private void loadPendingAcks() {
        String pendingAckList = (String) FinskyPreferences.dfeNotificationPendingAcks.get();
        if (!TextUtils.isEmpty(pendingAckList)) {
            String[] pendingAcks = pendingAckList.split(",");
            for (int i = 0; i < pendingAcks.length; i++) {
                this.mPendingAcks.add(pendingAcks[i]);
                this.mHandledNotifications.add(pendingAcks[i]);
            }
        }
    }

    private void savePendingAcks() {
        if (this.mPendingAcks.isEmpty()) {
            FinskyPreferences.dfeNotificationPendingAcks.remove();
        } else if (this.mPendingAcks.size() == 1) {
            FinskyPreferences.dfeNotificationPendingAcks.put(this.mPendingAcks.get(0));
        } else {
            FinskyPreferences.dfeNotificationPendingAcks.put(TextUtils.join(",", this.mPendingAcks));
        }
    }

    private static boolean containsIabMutations(LibraryUpdate libraryUpdate) {
        for (LibraryMutation mutation : libraryUpdate.mutation) {
            if (DocUtils.isInAppDocid(mutation.docid)) {
                FinskyLog.d("Encountered IAB item in notification: %s.", arr$[i$].docid.backendDocid);
                return true;
            }
        }
        return false;
    }
}
