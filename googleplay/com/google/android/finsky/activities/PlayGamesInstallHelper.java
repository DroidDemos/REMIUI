package com.google.android.finsky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.wallet.instrumentmanager.R;

public class PlayGamesInstallHelper {
    public static void addGameIntentExtras(Document doc, Intent intent) {
        if (doc != null) {
            AppDetails appDetails = doc.getAppDetails();
            if (appDetails != null) {
                String appType = appDetails.appType;
                if (appType != null && appType.equalsIgnoreCase("game")) {
                    intent.putExtra("is_game", true);
                    if (doc.hasBadgeContainer()) {
                        intent.putExtra("has_game_features", true);
                    }
                }
            }
        }
    }

    public static boolean isGameIntent(Intent intent) {
        if (!intent.getBooleanExtra("is_game", false)) {
            return false;
        }
        if (((Boolean) G.playGamesInstallSuggestionOnlyWhenGameFeatures.get()).booleanValue()) {
            return intent.getBooleanExtra("has_game_features", false);
        }
        return true;
    }

    public static boolean shouldSuggestPlayGames() {
        int maxTimesShown = ((Integer) G.playGamesInstallSuggestionMaxShownCount.get()).intValue();
        if (maxTimesShown == 0) {
            return false;
        }
        int numTimesShown = ((Integer) FinskyPreferences.playGamesInstallSuggestionShownCount.get()).intValue();
        if (numTimesShown >= maxTimesShown) {
            return false;
        }
        PackageStateRepository packageStateRepository = FinskyApp.get().getAppStates().getPackageStateRepository();
        PackageState gmsPackageState = packageStateRepository.get("com.google.android.gms");
        if (gmsPackageState == null || gmsPackageState.installedVersion < ((Integer) G.playGamesGmsCoreMinVersion.get()).intValue() || packageStateRepository.get((String) G.playGamesDocId.get()) != null) {
            return false;
        }
        long backoffMs;
        boolean z;
        switch (numTimesShown) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return true;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                backoffMs = ((Long) G.playGamesInstallSuggestionFirstBackoffMs.get()).longValue();
                break;
            default:
                backoffMs = ((Long) G.playGamesInstallSuggestionSubsequentBackoffMs.get()).longValue();
                break;
        }
        if (((Long) FinskyPreferences.playGamesInstallSuggestionLastTimeShown.get()).longValue() + backoffMs < System.currentTimeMillis()) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public static void launchPlayGamesInstallDialog(FragmentManager fragmentManager, int requestCodeForResponse, String tagToUse) {
        Builder builder = new Builder();
        builder.setLayoutId(com.android.vending.R.layout.play_games_install_dialog).setPositiveId(com.android.vending.R.string.install).setNegativeId(com.android.vending.R.string.not_now).setViewConfiguration(new Bundle()).setCallback(null, requestCodeForResponse, null).setEventLog(318, null, 274, 275, null);
        builder.build().show(fragmentManager, tagToUse);
        FinskyPreferences.playGamesInstallSuggestionShownCount.put(Integer.valueOf(((Integer) FinskyPreferences.playGamesInstallSuggestionShownCount.get()).intValue() + 1));
        FinskyPreferences.playGamesInstallSuggestionLastTimeShown.put(Long.valueOf(System.currentTimeMillis()));
    }

    public static void handlePositiveClick(final NavigationManager navigationManager) {
        new Handler().post(new Runnable() {
            public void run() {
                navigationManager.goToDocPage(DfeUtils.createDetailsUrlFromId((String) G.playGamesDocId.get()));
            }
        });
    }
}
