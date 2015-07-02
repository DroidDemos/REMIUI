package com.google.android.finsky.navigationmanager;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.wallet.instrumentmanager.R;

public class ConsumptionUtils {
    private static int getConsumptionAppRequiredString(int docBackend) {
        switch (docBackend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.string.books_download_required;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.string.music_download_required;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.string.videos_download_required;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.string.newsstand_download_required;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return com.android.vending.R.string.gplus_download_required;
            default:
                return -1;
        }
    }

    private static Intent getConsumptionIntent(Context context, Document doc, Account account) {
        if (doc == null) {
            return null;
        }
        int contentType = doc.getBackend();
        if (doc.getBackendDocId() == null) {
            return null;
        }
        switch (contentType) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return IntentUtils.buildConsumptionAppViewItemIntent(context, doc, account.name);
            default:
                throw new IllegalStateException("Cannot open an item from the corpus " + contentType);
        }
    }

    private static boolean isConsumptionAppNeeded(Context context, Document doc, Account account) {
        String packageName = IntentUtils.getPackageName(doc.getBackend());
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        if (!isConsumptionAppInstalled(packageName)) {
            return true;
        }
        Intent intent = getConsumptionIntent(context, doc, account);
        if (intent == null || !IntentUtils.canResolveIntent(context.getPackageManager(), intent)) {
            return true;
        }
        return false;
    }

    private static boolean isConsumptionAppInstalled(String packageName) {
        PackageState packageState = FinskyApp.get().getAppStates().getPackageStateRepository().get(packageName);
        if (packageState == null) {
            return false;
        }
        if ("com.google.android.videos".equals(packageName) && packageState.installedVersion < IntentUtils.getMinimumRequiredVideosAppVersion()) {
            return false;
        }
        if (!"com.google.android.apps.magazines".equals(packageName) || packageState.installedVersion >= ((Integer) G.firstNewsstandAppVersion.get()).intValue()) {
            return true;
        }
        return false;
    }

    public static void showAppNeededDialog(Context context, int docBackend, FragmentManager fragmentManager, Fragment targetFragment, int requestId) {
        String appDocId = IntentUtils.getPackageName(docBackend);
        if (appDocId == null) {
            Toast.makeText(context, context.getString(com.android.vending.R.string.launch_error), 0).show();
        } else if (fragmentManager.findFragmentByTag("app_needed_dialog") == null) {
            int messageId = getConsumptionAppRequiredString(docBackend);
            Bundle extraArgs = new Bundle();
            extraArgs.putString("dialog_details_url", DfeUtils.createDetailsUrlFromId(appDocId));
            Builder builder = new Builder();
            builder.setMessageId(messageId).setPositiveId(com.android.vending.R.string.ok).setNegativeId(com.android.vending.R.string.cancel);
            builder.setCallback(targetFragment, requestId, extraArgs);
            builder.build().show(fragmentManager, "app_needed_dialog");
        }
    }

    public static boolean showAppNeededDialogIfNeeded(Context context, Account account, Document doc, FragmentManager appRequiredFragmentManager, Fragment appRequiredTargetFragment, int appRequiredDialogId) {
        if (!isConsumptionAppNeeded(context, doc, account)) {
            return false;
        }
        showAppNeededDialog(context, doc.getBackend(), appRequiredFragmentManager, appRequiredTargetFragment, appRequiredDialogId);
        return true;
    }

    public static boolean openItem(Context context, Account account, Document doc, FragmentManager appRequiredFragmentManager, Fragment appRequiredTargetFragment, int appRequiredDialogId) {
        if (showAppNeededDialogIfNeeded(context, account, doc, appRequiredFragmentManager, appRequiredTargetFragment, appRequiredDialogId)) {
            return true;
        }
        Intent intent = getConsumptionIntent(context, doc, account);
        ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(intent, 0);
        if (intent == null || resolveInfo == null) {
            Toast.makeText(context, context.getString(com.android.vending.R.string.launch_error), 0).show();
            return false;
        }
        if (doc.getAppDetails() != null) {
            FinskyApp.get().getNotifier().hideAllMessagesForPackage(doc.getAppDetails().packageName);
        }
        context.startActivity(intent);
        return false;
    }
}
