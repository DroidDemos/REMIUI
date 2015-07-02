package com.google.android.finsky.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.finsky.config.G;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.receivers.Installer;
import com.google.android.wallet.instrumentmanager.R;

public class GELInstallerListener implements InstallerListener {
    private final Context mContext;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent;

        static {
            $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent = new int[InstallerPackageEvent.values().length];
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.DOWNLOAD_PENDING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.DOWNLOADING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.INSTALLING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.INSTALLED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.DOWNLOAD_CANCELLED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.DOWNLOAD_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.INSTALL_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public GELInstallerListener(Context context, Installer installer) {
        this.mContext = context;
        installer.addListener(this);
    }

    public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
        Intent intent = null;
        switch (AnonymousClass1.$SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[event.ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                intent = new Intent("com.android.launcher.action.ACTION_PACKAGE_ENQUEUED");
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                intent = new Intent("com.android.launcher.action.ACTION_PACKAGE_DOWNLOADING");
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                intent = new Intent("com.android.launcher.action.ACTION_PACKAGE_INSTALLING");
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                intent = new Intent("com.android.launcher.action.ACTION_PACKAGE_DEQUEUED");
                intent.putExtra("com.android.launcher.action.INSTALL_COMPLETED", event == InstallerPackageEvent.INSTALLED);
                break;
        }
        if (intent != null) {
            intent.setPackage((String) G.gelPackageName.get());
            intent.setData(Uri.fromParts("package", packageName, null));
            if (FinskyLog.DEBUG) {
                FinskyLog.d("GEL broadcast uri=[%s], action=[%s], for package=[%s]", intent.getData(), intent.getAction(), packageName);
            }
            this.mContext.sendBroadcast(intent);
        }
    }
}
