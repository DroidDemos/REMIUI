package com.google.android.finsky.activities;

import android.support.v4.app.FragmentManager;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.appstate.UpdateChecker;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyPreferences;

public class AutoUpdateMigrationHelper {
    public static void launchAutoUpdateMigrationDialog(FragmentManager fragmentManager, int requestCodeForResponse, String tagToUse) {
        int messageId = ((Boolean) FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.get()).booleanValue() ? R.string.auto_update_migration_dialog_message_on_wifi : R.string.auto_update_migration_dialog_message_on_mobile;
        Builder builder = new Builder();
        builder.setMessageId(messageId).setPositiveId(R.string.ok).setNegativeId(R.string.not_now).setCallback(null, requestCodeForResponse, null).setEventLog(306, null, 237, 236, null);
        builder.build().show(fragmentManager, tagToUse);
    }

    public static void handlePositiveClick() {
        updateDialogTracking();
        FinskyPreferences.AUTO_UPDATE_ENABLED.put(Boolean.valueOf(true));
        UpdateChecker.setAllAppsToUseGlobalDefault(FinskyApp.get().getAppStates());
        FinskyPreferences.hasAcceptedAutoUpdateMigrationDialog.put(Boolean.valueOf(true));
    }

    public static void handleNegativeClick() {
        updateDialogTracking();
    }

    private static final void updateDialogTracking() {
        FinskyPreferences.autoUpdateMigrationDialogShownCount.put(Integer.valueOf(((Integer) FinskyPreferences.autoUpdateMigrationDialogShownCount.get()).intValue() + 1));
        FinskyPreferences.lastUpdateMigrationDialogTimeShown.put(Long.valueOf(System.currentTimeMillis()));
    }

    public static boolean shouldShowAutoUpdateMigrationDialog() {
        if ((!((Boolean) FinskyPreferences.hadPreJsAutoUpdateSettings.get()).booleanValue() && !((Boolean) G.skipAutoUpdateCleanupClientVersionCheck.get()).booleanValue()) || ((Boolean) FinskyPreferences.hasAcceptedAutoUpdateMigrationDialog.get()).booleanValue() || ((Integer) FinskyPreferences.autoUpdateMigrationDialogShownCount.get()).intValue() >= ((Integer) G.maxAutoUpdateDialogShownCount.get()).intValue()) {
            return false;
        }
        int backoffInDays;
        boolean z;
        switch (((Integer) FinskyPreferences.autoUpdateMigrationDialogShownCount.get()).intValue()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return true;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                backoffInDays = ((Integer) G.autoUpdateDialogFirstBackoffDays.get()).intValue();
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                backoffInDays = ((Integer) G.autoUpdateDialogSecondBackoffDays.get()).intValue();
                break;
            default:
                backoffInDays = ((Integer) G.autoUpdateDialogSubsequentBackoffDays.get()).intValue();
                if (backoffInDays == -1) {
                    return false;
                }
                break;
        }
        if ((86400000 * ((long) backoffInDays)) + ((Long) FinskyPreferences.lastUpdateMigrationDialogTimeShown.get()).longValue() < System.currentTimeMillis()) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
