package com.google.android.finsky.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.Html;
import android.text.TextUtils;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.receivers.NotificationReceiver;
import com.google.android.finsky.utils.RestrictedDeviceHelper.OnCompleteListener;
import com.google.android.vending.verifier.PackageWarningDialog;
import java.util.ArrayList;
import java.util.List;

public class NotificationManager implements Notifier {
    private static final boolean SUPPORTS_RICH_NOTIFICATIONS;
    private static final int UNKNOWN_PACKAGE_ID;
    private final Context mContext;
    private NotificationListener mListener;

    static {
        UNKNOWN_PACKAGE_ID = "unknown package".hashCode();
        SUPPORTS_RICH_NOTIFICATIONS = VERSION.SDK_INT >= 11;
    }

    public NotificationManager(Context context) {
        this.mContext = context;
    }

    public void setNotificationListener(NotificationListener listener) {
        this.mListener = listener;
    }

    public void showInternalStorageFull(String title, String packageName) {
        showAppErrorMessage(packageName, this.mContext.getString(R.string.cache_space_bar, new Object[]{title}), this.mContext.getString(R.string.cache_space_title, new Object[]{title}), this.mContext.getString(R.string.cache_space_message, new Object[]{title}), 17301642);
    }

    public void showExternalStorageFull(String title, String packageName) {
        showAppErrorMessage(packageName, this.mContext.getString(R.string.external_space_bar, new Object[]{title}), this.mContext.getString(R.string.external_space_title, new Object[]{title}), this.mContext.getString(R.string.external_space_message, new Object[]{title}), 17301642);
    }

    public void showNormalAssetRemovedMessage(String title, String packageName) {
        String str = packageName;
        showAppMessage(str, this.mContext.getString(R.string.asset_removed_bar, new Object[]{title}), this.mContext.getString(R.string.asset_removed_title, new Object[]{title}), this.mContext.getString(R.string.asset_removed_message, new Object[]{title}), 17301642, "status");
    }

    public void showMaliciousAssetRemovedMessage(String title, String packageName) {
        showMessage(this.mContext.getString(R.string.malicious_asset_removed_bar, new Object[]{title}), this.mContext.getString(R.string.malicious_asset_removed_title, new Object[]{title}), this.mContext.getString(R.string.malicious_asset_removed_message, new Object[]{title}));
    }

    public void showHarmfulAppRemovedMessage(String label, String packageName, String description) {
        Intent intent = PackageWarningDialog.createIntent(this.mContext, PackageWarningDialog.NO_ID, 3, label, packageName, description, null);
        String title = this.mContext.getString(R.string.verify_app_removed_notification_title, new Object[]{label});
        showNewNotification("package..removed..".concat(packageName), title, title, this.mContext.getString(R.string.verify_app_removed_notification_message), null, R.drawable.stat_notify_shield, null, -1, intent, false, null);
    }

    public void showHarmfulAppRemoveRequestMessage(String label, String packageName, String description, byte[] responseToken) {
        Intent intent = PackageWarningDialog.createIntent(this.mContext, PackageWarningDialog.NO_ID, 2, label, packageName, description, responseToken);
        String title = this.mContext.getString(R.string.verify_app_remove_request_notification_title, new Object[]{label});
        showNewNotification("package..remove..request..".concat(packageName), title, title, this.mContext.getString(R.string.verify_app_remove_request_notification_message), null, R.drawable.stat_notify_shield_warning, null, -1, intent, false, null, true);
    }

    public void showExternalStorageMissing(String title, String packageName) {
        showAppErrorMessage(packageName, this.mContext.getString(R.string.external_space_missing_bar, new Object[]{title}), this.mContext.getString(R.string.external_space_missing_title, new Object[]{title}), this.mContext.getString(R.string.external_space_missing_message, new Object[]{title}), 17301642);
    }

    public void showDownloadErrorMessage(String title, String packageName, int errorCode, String serverMessage, boolean isUpdate) {
        String barText;
        String titleText;
        String messageText;
        if (isUpdate) {
            barText = this.mContext.getString(R.string.error_while_downloading_update_bar, new Object[]{title});
            titleText = this.mContext.getString(R.string.error_while_downloading_update_title, new Object[]{title});
            if (serverMessage != null) {
                messageText = this.mContext.getString(R.string.error_while_downloading_update_message_server_message, new Object[]{title, serverMessage});
            } else {
                messageText = this.mContext.getString(R.string.error_while_downloading_update_message, new Object[]{title, Integer.valueOf(errorCode)});
            }
        } else {
            barText = this.mContext.getString(R.string.error_while_downloading_bar, new Object[]{title});
            titleText = this.mContext.getString(R.string.error_while_downloading_title, new Object[]{title});
            if (serverMessage != null) {
                messageText = this.mContext.getString(R.string.error_while_downloading_message_server_message, new Object[]{title, serverMessage});
            } else {
                messageText = this.mContext.getString(R.string.error_while_downloading_message, new Object[]{title, Integer.valueOf(errorCode)});
            }
        }
        showAppErrorMessage(packageName, barText, titleText, messageText, 17301642);
    }

    public void showInstallingMessage(String title, String packageName, boolean isUpdate) {
        showNewNotification("package installing", String.format(this.mContext.getString(isUpdate ? R.string.notification_updating_status : R.string.notification_installing_status), new Object[]{title}), title, String.format(this.mContext.getString(isUpdate ? R.string.notification_updating_message : R.string.notification_installing_message), new Object[]{title}), null, 17301633, null, 0, isUpdate ? MainActivity.getMyDownloadsIntent(this.mContext) : createDefaultClickIntent(this.mContext, packageName, null, null, DfeUtils.createDetailsUrlFromId(packageName)), false, null, false, "progress");
    }

    public void showSuccessfulInstallMessage(String title, String packageName, String continueUrl, boolean isUpdate) {
        hideInstallingMessage();
        if (isUpdate) {
            showSuccessfulUpdateMessage(title, packageName);
        } else {
            showSuccessfulNewInstallMessage(title, packageName, continueUrl);
        }
    }

    private void showSuccessfulNewInstallMessage(String title, String packageName, String continueUrl) {
        String tickerText = String.format(this.mContext.getString(R.string.notification_installation_success_status), new Object[]{title});
        String contentText = this.mContext.getString(R.string.notification_installation_success_message);
        if (!TextUtils.isEmpty(continueUrl)) {
            contentText = this.mContext.getString(R.string.notification_installation_continue_message);
        }
        logNotificationImpression(901);
        Intent onClickIntent = NotificationReceiver.getSuccessfullyInstalledClickedIntent(packageName, continueUrl);
        Bitmap largeBitmap = null;
        if (SUPPORTS_RICH_NOTIFICATIONS) {
            try {
                Drawable appIconDrawable = this.mContext.getPackageManager().getApplicationIcon(packageName);
                if (appIconDrawable != null) {
                    largeBitmap = drawableToBitmap(appIconDrawable, packageName);
                }
            } catch (Exception e) {
            }
        }
        showNewNotification(packageName, tickerText, title, contentText, null, R.drawable.stat_notify_installed, largeBitmap, 0, onClickIntent, true, null);
    }

    private void showSuccessfulUpdateMessage(String title, String packageName) {
        ArrayList<String> notificationAppList;
        String contentText;
        hideAllMessagesForPackage(packageName);
        String notificationAppListString = (String) FinskyPreferences.successfulUpdateNotificationAppList.get();
        title = title.replace('\n', ' ');
        if (TextUtils.isEmpty(notificationAppListString)) {
            notificationAppList = Lists.newArrayList();
        } else {
            notificationAppList = Lists.newArrayList(notificationAppListString.split("\n"));
            notificationAppList.remove(title);
        }
        notificationAppList.add(0, title);
        FinskyPreferences.successfulUpdateNotificationAppList.put(TextUtils.join("\n", notificationAppList));
        int notificationAppCount = notificationAppList.size();
        String tickerText = String.format(this.mContext.getString(R.string.notification_update_success_status), new Object[]{title});
        String titleText = this.mContext.getResources().getQuantityString(R.plurals.notification_update_success_message, notificationAppCount, new Object[]{Integer.valueOf(notificationAppCount)});
        Resources res = this.mContext.getResources();
        switch (notificationAppCount) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                FinskyLog.w("App count is 0 in successful update notification", new Object[0]);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                contentText = (String) notificationAppList.get(0);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                contentText = res.getString(R.string.multiple_items_2, new Object[]{notificationAppList.get(0), notificationAppList.get(1)});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                contentText = res.getString(R.string.multiple_items_3, new Object[]{notificationAppList.get(0), notificationAppList.get(1), notificationAppList.get(2)});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                contentText = res.getString(R.string.multiple_items_4, new Object[]{notificationAppList.get(0), notificationAppList.get(1), notificationAppList.get(2), notificationAppList.get(3)});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                contentText = res.getString(R.string.multiple_items_5, new Object[]{notificationAppList.get(0), notificationAppList.get(1), notificationAppList.get(2), notificationAppList.get(3), notificationAppList.get(4)});
                break;
            default:
                contentText = res.getString(R.string.notification_multiple_updates_more, new Object[]{notificationAppList.get(0), notificationAppList.get(1), notificationAppList.get(2), notificationAppList.get(3), Integer.valueOf(notificationAppCount - 4)});
                break;
        }
        if (notificationAppCount <= 1) {
            logNotificationImpression(902);
        }
        Intent notificationClickedIntent = NotificationReceiver.getSuccessfullyUpdatedClickedIntent();
        Intent notificationDeleteIntent = NotificationReceiver.getSuccessfullyUpdatedDeletedIntent();
        int iconResourceId = (!SUPPORTS_RICH_NOTIFICATIONS || notificationAppCount <= 1) ? R.drawable.stat_notify_installed : R.drawable.stat_notify_installed_collapse;
        showNewNotification("successful update", tickerText, titleText, contentText, contentText, iconResourceId, null, -1, notificationClickedIntent, true, notificationDeleteIntent);
    }

    private synchronized Bitmap drawableToBitmap(Drawable drawable, String loggingId) {
        Bitmap result;
        Resources res = this.mContext.getResources();
        int maxIconWidth = res.getDimensionPixelSize(17104901);
        int maxIconHeight = res.getDimensionPixelSize(17104902);
        int originalWidth = drawable.getIntrinsicWidth();
        int originalHeight = drawable.getIntrinsicHeight();
        if (originalWidth > maxIconWidth || originalHeight > maxIconHeight || !(drawable instanceof BitmapDrawable)) {
            FinskyLog.w("Resource for %s is %s of size %d*%d", loggingId, drawable.getClass().getName(), Integer.valueOf(originalWidth), Integer.valueOf(originalHeight));
            float ratio = Math.min(1.0f, Math.min(((float) maxIconWidth) / ((float) originalWidth), ((float) maxIconHeight) / ((float) originalHeight)));
            int scaledWidth = (int) (((float) originalWidth) * ratio);
            int scaledHeight = (int) (((float) originalHeight) * ratio);
            result = Bitmap.createBitmap(scaledWidth, scaledHeight, Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            drawable.setBounds(0, 0, scaledWidth, scaledHeight);
            drawable.draw(canvas);
        } else {
            result = ((BitmapDrawable) drawable).getBitmap();
        }
        return result;
    }

    public void showInstallationFailureMessage(String title, String packageName, String message, int returnCode) {
        hideInstallingMessage();
        showAppNotificationOrAlert(packageName, message, title, message, 17301642, returnCode, "err");
    }

    public void showPurchaseErrorMessage(String title, String shortMessage, String message, String docId, String detailsUrl) {
        showDocNotification(docId, shortMessage, title, message, 17301642, detailsUrl, "err");
    }

    public void showSubscriptionsWarningMessage(String title, String packageName, String message) {
        showAppNotificationOnly(packageName, message, title, message, 17301642, 2, "err");
    }

    private void showAppErrorMessage(String packageName, String statusBarText, String title, String message, int icon) {
        showAppMessage(packageName, statusBarText, title, message, icon, "err");
    }

    private void showAppMessage(String packageName, String statusBarText, String title, String message, int icon, String category) {
        showAppNotificationOrAlert(packageName, statusBarText, title, message, icon, -1, category);
    }

    private void showAppNotificationOrAlert(String packageName, String statusBarText, String title, String message, int icon, int returnCode, String category) {
        if (this.mListener == null || !this.mListener.showAppAlert(packageName, title, message, returnCode)) {
            Intent detailsIntent = createDefaultClickIntent(this.mContext, packageName, title, message, DfeUtils.createDetailsUrlFromId(packageName));
            detailsIntent.putExtra("error_return_code", returnCode);
            showNotification(packageName, statusBarText, title, message, icon, detailsIntent, category);
        }
    }

    private void showAppNotificationOnly(String packageName, String statusBarText, String title, String message, int icon, int returnCode, String category) {
        if (this.mListener == null || !this.mListener.showAppNotification(packageName, title, message)) {
            Intent detailsIntent = createDefaultClickIntent(this.mContext, packageName, null, null, DfeUtils.createDetailsUrlFromId(packageName));
            detailsIntent.putExtra("error_return_code", returnCode);
            showNotification(packageName, statusBarText, title, message, icon, detailsIntent, category);
        }
    }

    private void showDocNotification(String docId, String statusBarText, String title, String message, int icon, String detailsUrl, String category) {
        if (this.mListener == null || !this.mListener.showDocAlert(docId, title, message, detailsUrl)) {
            showNotification(docId, statusBarText, title, message, icon, createDefaultClickIntent(this.mContext, docId, title, message, detailsUrl), category);
        }
    }

    public void showMessage(String title, String shortMessage, String message) {
        showNotification(null, title, title, message, 17301642, createDefaultClickIntent(this.mContext, null, title, message, null), "status");
    }

    public void showNotification(String notificationId, String statusBarText, String title, String htmlMessage, int icon, Intent clickIntent, String category) {
        final String message = Html.fromHtml(htmlMessage).toString();
        final int notificationCode = notificationId == null ? UNKNOWN_PACKAGE_ID : notificationId.hashCode();
        PendingIntent pendingIntent = PendingIntent.getActivity(this.mContext, notificationCode, clickIntent, 1342177280);
        final Notification notification = new Builder(this.mContext).setSmallIcon(icon).setTicker(statusBarText).setWhen(System.currentTimeMillis()).setCategory(category).setVisibility(0).setPriority(-1).setAutoCancel(true).setLocalOnly(true).build();
        notification.setLatestEventInfo(this.mContext, title, message, pendingIntent);
        notification.setLatestEventInfo(this.mContext, title, message, pendingIntent);
        final String str = notificationId;
        final String str2 = title;
        final Intent intent = clickIntent;
        RestrictedDeviceHelper.isLimitedOrSchoolEduUser(new OnCompleteListener() {
            public void onComplete(boolean isLimitedOrSchoolEduUser) {
                if (!isLimitedOrSchoolEduUser) {
                    ((android.app.NotificationManager) NotificationManager.this.mContext.getSystemService("notification")).notify(notificationCode, notification);
                }
                NotificationManager.logNotification(!isLimitedOrSchoolEduUser, str, str2, message, intent);
            }
        });
    }

    private static void logNotification(boolean notificationShown, String notificationId, String title, String message, Intent clickIntent) {
        if (((Boolean) G.debugOptionsEnabled.get()).booleanValue()) {
            int returnCode = clickIntent.getIntExtra("error_return_code", -1);
            String verb = notificationShown ? "Showing" : "Suppressing";
            FinskyLog.d("%s notification: [ID=%s, Title=%s, Message=%s, returnCode=%d]", verb, notificationId, title, message, Integer.valueOf(returnCode));
        }
    }

    public void showNewUpdatesAvailableMessage(List<Document> appDocs, int totalUpdatesCount) {
        String contentText;
        Resources res = this.mContext.getResources();
        int newUpdatesCount = appDocs.size();
        String tickerText = res.getString(R.string.notification_new_updates_available_status);
        String contentTitle = res.getQuantityString(R.plurals.notification_new_updates_available_message, newUpdatesCount, new Object[]{Integer.valueOf(newUpdatesCount)});
        switch (newUpdatesCount) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                FinskyLog.w("App count is 0 in new updates notification", new Object[0]);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                contentText = res.getString(R.string.notification_touch_to_update_1, new Object[]{((Document) appDocs.get(0)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                contentText = res.getString(R.string.notification_touch_to_update_2, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                contentText = res.getString(R.string.notification_touch_to_update_3, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                contentText = res.getString(R.string.notification_touch_to_update_4, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle(), ((Document) appDocs.get(3)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                contentText = res.getString(R.string.notification_touch_to_update_5, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle(), ((Document) appDocs.get(3)).getTitle(), ((Document) appDocs.get(4)).getTitle()});
                break;
            default:
                contentText = res.getString(R.string.notification_touch_to_update_more, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle(), ((Document) appDocs.get(3)).getTitle(), Integer.valueOf(newUpdatesCount - 4)});
                break;
        }
        int iconResourceId = (!SUPPORTS_RICH_NOTIFICATIONS || newUpdatesCount <= 1) ? R.drawable.stat_notify_update : R.drawable.stat_notify_update_collapse;
        logNotificationImpression(900);
        showNewNotification("updates", tickerText, contentTitle, contentText, contentText, iconResourceId, null, totalUpdatesCount, NotificationReceiver.getNewUpdateClickedIntent(), true, null);
    }

    public void showOutstandingUpdatesMessage(List<Document> appDocs) {
        String contentText;
        Resources res = this.mContext.getResources();
        int updatesCount = appDocs.size();
        String tickerText = res.getQuantityString(R.plurals.notification_outstanding_updates_message, updatesCount, new Object[]{Integer.valueOf(updatesCount)});
        String contentTitle = res.getQuantityString(R.plurals.notification_outstanding_updates_message, updatesCount, new Object[]{Integer.valueOf(updatesCount)});
        switch (updatesCount) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                FinskyLog.w("App count is 0 in new outstanding updates notification", new Object[0]);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                contentText = res.getString(R.string.notification_touch_to_update_1, new Object[]{((Document) appDocs.get(0)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                contentText = res.getString(R.string.notification_touch_to_update_2, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                contentText = res.getString(R.string.notification_touch_to_update_3, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                contentText = res.getString(R.string.notification_touch_to_update_4, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle(), ((Document) appDocs.get(3)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                contentText = res.getString(R.string.notification_touch_to_update_5, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle(), ((Document) appDocs.get(3)).getTitle(), ((Document) appDocs.get(4)).getTitle()});
                break;
            default:
                contentText = res.getString(R.string.notification_touch_to_update_more, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle(), ((Document) appDocs.get(3)).getTitle(), Integer.valueOf(updatesCount - 4)});
                break;
        }
        int iconResourceId = (!SUPPORTS_RICH_NOTIFICATIONS || updatesCount <= 1) ? R.drawable.stat_notify_update : R.drawable.stat_notify_update_collapse;
        logNotificationImpression(903);
        showNewNotification("updates", tickerText, contentTitle, contentText, contentText, iconResourceId, null, -1, NotificationReceiver.getOutstandingUpdateClickedIntent(), true, null);
    }

    public void showUpdatesNeedApprovalMessage(List<Document> appDocs) {
        String contentText;
        Resources res = this.mContext.getResources();
        int updatesCount = appDocs.size();
        String contentTitle = res.getQuantityString(R.plurals.notification_need_approval_message, updatesCount, new Object[]{Integer.valueOf(updatesCount)});
        String tickerText = contentTitle;
        switch (updatesCount) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                FinskyLog.w("App count is 0 in need approval notification", new Object[0]);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                contentText = res.getString(R.string.notification_need_approval_1, new Object[]{((Document) appDocs.get(0)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                contentText = res.getString(R.string.notification_need_approval_2, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                contentText = res.getString(R.string.notification_need_approval_3, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                contentText = res.getString(R.string.notification_need_approval_4, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle(), ((Document) appDocs.get(3)).getTitle()});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                contentText = res.getString(R.string.notification_need_approval_5, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle(), ((Document) appDocs.get(3)).getTitle(), ((Document) appDocs.get(4)).getTitle()});
                break;
            default:
                contentText = res.getString(R.string.notification_need_approval_more, new Object[]{((Document) appDocs.get(0)).getTitle(), ((Document) appDocs.get(1)).getTitle(), ((Document) appDocs.get(2)).getTitle(), ((Document) appDocs.get(3)).getTitle(), Integer.valueOf(updatesCount - 4)});
                break;
        }
        int iconResourceId = (!SUPPORTS_RICH_NOTIFICATIONS || updatesCount <= 1) ? R.drawable.stat_notify_update : R.drawable.stat_notify_update_collapse;
        logNotificationImpression(904);
        showNewNotification("updates", tickerText, contentTitle, contentText, contentText, iconResourceId, null, -1, NotificationReceiver.getNewUpdateNeedApprovalClicked(), true, null);
    }

    private void showNewNotification(String notificationId, String tickerText, String contentTitle, String contentText, String extraText, int icon, Bitmap largeBitmap, int extraCount, Intent clickIntent, boolean isClickIntentBroadcast, Intent deleteIntent) {
        showNewNotification(notificationId, tickerText, contentTitle, contentText, extraText, icon, largeBitmap, extraCount, clickIntent, isClickIntentBroadcast, deleteIntent, false);
    }

    private void showNewNotification(String notificationId, String tickerText, String contentTitle, String contentText, String extraText, int icon, Bitmap largeBitmap, int extraCount, Intent clickIntent, boolean isClickIntentBroadcast, Intent deleteIntent, boolean ongoing) {
        showNewNotification(notificationId, tickerText, contentTitle, contentText, extraText, icon, largeBitmap, extraCount, clickIntent, isClickIntentBroadcast, deleteIntent, ongoing, "status");
    }

    private void showNewNotification(String notificationId, String tickerText, String contentTitle, String contentText, String extraText, int icon, Bitmap largeBitmap, int extraCount, Intent clickIntent, boolean isClickIntentBroadcast, Intent deleteIntent, boolean ongoing, String category) {
        PendingIntent pendingClickIntent;
        final Builder builder = new Builder(this.mContext).setTicker(tickerText).setContentTitle(contentTitle).setContentText(contentText).setCategory(category).setVisibility(0).setLocalOnly(true);
        if (!TextUtils.isEmpty(extraText)) {
            builder.setStyle(new BigTextStyle().bigText(extraText));
        }
        builder.setSmallIcon(icon);
        if (largeBitmap != null) {
            builder.setLargeIcon(largeBitmap);
        }
        if (extraCount > 0) {
            builder.setNumber(extraCount);
        }
        builder.setColor(this.mContext.getResources().getColor(R.color.play_apps_primary));
        builder.setPriority(-1);
        final int notificationCode = notificationId == null ? UNKNOWN_PACKAGE_ID : notificationId.hashCode();
        if (isClickIntentBroadcast) {
            pendingClickIntent = PendingIntent.getBroadcast(this.mContext, notificationCode, clickIntent, 1342177280);
        } else {
            pendingClickIntent = PendingIntent.getActivity(this.mContext, notificationCode, clickIntent, 1342177280);
        }
        builder.setContentIntent(pendingClickIntent);
        if (deleteIntent != null) {
            builder.setDeleteIntent(PendingIntent.getBroadcast(this.mContext, notificationCode, deleteIntent, 1342177280));
        }
        if (ongoing) {
            builder.setOngoing(true);
        } else {
            builder.setAutoCancel(true);
        }
        final android.app.NotificationManager mgr = (android.app.NotificationManager) this.mContext.getSystemService("notification");
        final String str = notificationId;
        final String str2 = contentTitle;
        final String str3 = contentText;
        final Intent intent = clickIntent;
        RestrictedDeviceHelper.isLimitedOrSchoolEduUser(new OnCompleteListener() {
            public void onComplete(boolean isLimitedOrSchoolEduUser) {
                if (!isLimitedOrSchoolEduUser) {
                    mgr.notify(notificationCode, builder.build());
                }
                NotificationManager.logNotification(!isLimitedOrSchoolEduUser, str, str2, str3, intent);
            }
        });
    }

    public void hideAllMessagesForPackage(String packageName) {
        ((android.app.NotificationManager) this.mContext.getSystemService("notification")).cancel(packageName == null ? UNKNOWN_PACKAGE_ID : packageName.hashCode());
    }

    public void hideUpdatesAvailableMessage() {
        ((android.app.NotificationManager) this.mContext.getSystemService("notification")).cancel("updates".hashCode());
    }

    public void hideInstallingMessage() {
        ((android.app.NotificationManager) this.mContext.getSystemService("notification")).cancel("package installing".hashCode());
    }

    public void hidePackageRemoveRequestMessage(String packageName) {
        ((android.app.NotificationManager) this.mContext.getSystemService("notification")).cancel("package..remove..request..".concat(packageName).hashCode());
    }

    public void hidePackageRemovedMessage(String packageName) {
        ((android.app.NotificationManager) this.mContext.getSystemService("notification")).cancel("package..removed..".concat(packageName).hashCode());
    }

    private void logNotificationImpression(int type) {
        FinskyApp.get().getEventLogger().logPathImpression(0, new GenericUiElementNode(type, null, null, null));
    }

    public static Intent createDefaultClickIntent(Context ctx, String docId, String dialogTitle, String dialogHtmlMessage, String detailsUrl) {
        Intent intent = new Intent();
        intent.setClass(ctx, MainActivity.class);
        if (!TextUtils.isEmpty(docId)) {
            intent = IntentUtils.createViewDocumentUrlIntent(ctx, detailsUrl);
            intent.putExtra("error_doc_id", docId);
        }
        if (!TextUtils.isEmpty(dialogTitle)) {
            intent.putExtra("error_title", dialogTitle);
        }
        if (!TextUtils.isEmpty(dialogHtmlMessage)) {
            intent.putExtra("error_html_message", dialogHtmlMessage);
        }
        return intent;
    }
}
