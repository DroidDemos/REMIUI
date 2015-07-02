package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.NotificationManager;

public class NotificationReceiver extends BroadcastReceiver {
    public static Intent getNewUpdateClickedIntent() {
        return new Intent(FinskyApp.get(), NotificationReceiver.class).setAction("com.android.vending.NEW_UPDATE_CLICKED");
    }

    public static Intent getSuccessfullyInstalledClickedIntent(String packageName, String continueUrl) {
        return new Intent(FinskyApp.get(), NotificationReceiver.class).setAction("com.android.vending.SUCCESSFULLY_INSTALLED_CLICKED").putExtra("package_name", packageName).putExtra("continue_url", continueUrl);
    }

    public static Intent getSuccessfullyUpdatedClickedIntent() {
        return new Intent(FinskyApp.get(), NotificationReceiver.class).setAction("com.android.vending.SUCCESSFULLY_UPDATED_CLICKED");
    }

    public static Intent getSuccessfullyUpdatedDeletedIntent() {
        return new Intent(FinskyApp.get(), NotificationReceiver.class).setAction("com.android.vending.SUCCESSFULLY_UPDATED_DELETED");
    }

    public static Intent getOutstandingUpdateClickedIntent() {
        return new Intent(FinskyApp.get(), NotificationReceiver.class).setAction("com.android.vending.OUTSTANDING_UPDATE_CLICKED");
    }

    public static Intent getNewUpdateNeedApprovalClicked() {
        return new Intent(FinskyApp.get(), NotificationReceiver.class).setAction("com.android.vending.NEW_UPDATE_NEED_APPROVAL_CLICKED");
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.android.vending.NEW_UPDATE_CLICKED".equals(action)) {
            newUpdateClicked(context);
        } else if ("com.android.vending.SUCCESSFULLY_INSTALLED_CLICKED".equals(action)) {
            successfullyInstalledClicked(context, intent.getStringExtra("package_name"), intent.getStringExtra("continue_url"));
        } else if ("com.android.vending.SUCCESSFULLY_UPDATED_CLICKED".equals(action)) {
            successfullyUpdatedClicked(context);
        } else if ("com.android.vending.SUCCESSFULLY_UPDATED_DELETED".equals(action)) {
            successfullyUpdatedDeleted();
        } else if ("com.android.vending.OUTSTANDING_UPDATE_CLICKED".equals(action)) {
            outstandingUpdateClicked(context);
        } else if ("com.android.vending.NEW_UPDATE_NEED_APPROVAL_CLICKED".equals(action)) {
            newUpdateNeedApprovalClicked(context);
        }
    }

    private void newUpdateClicked(Context context) {
        logNotificationClick(900);
        startMyDownloads(context);
    }

    private void successfullyInstalledClicked(Context context, String packageName, String continueUrl) {
        logNotificationClick(901);
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (!TextUtils.isEmpty(continueUrl)) {
            intent = IntentUtils.createContinueUrlIntent(packageName, continueUrl);
        }
        if (intent == null) {
            intent = NotificationManager.createDefaultClickIntent(context, packageName, null, null, DfeUtils.createDetailsUrlFromId(packageName));
        }
        context.startActivity(intent.setFlags(268435456));
    }

    private void successfullyUpdatedClicked(Context context) {
        logNotificationClick(902);
        successfullyUpdatedDeleted();
        context.startActivity(MainActivity.getMyDownloadsIntent(context).setFlags(268435456));
    }

    private void successfullyUpdatedDeleted() {
        FinskyPreferences.successfulUpdateNotificationAppList.remove();
    }

    private void outstandingUpdateClicked(Context context) {
        logNotificationClick(903);
        startMyDownloads(context);
    }

    private void newUpdateNeedApprovalClicked(Context context) {
        logNotificationClick(904);
        startMyDownloads(context);
    }

    private void startMyDownloads(Context context) {
        context.startActivity(MainActivity.getMyDownloadsIntent(context).setFlags(268435456));
    }

    private void logNotificationClick(int type) {
        FinskyApp.get().getEventLogger().logClickEvent(type, null, null);
    }
}
