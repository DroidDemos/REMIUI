package com.google.android.finsky.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import com.google.android.finsky.utils.AppSupport.RefundListener;

public class UninstallRefundTracker implements PackageStatusListener {
    private final AppStates mAppStates;
    private final Context mContext;

    public UninstallRefundTracker(Context context, AppStates appStates, PackageMonitorReceiver receiver) {
        this.mContext = context;
        this.mAppStates = appStates;
        receiver.attach(this);
    }

    public void onPackageAdded(String packageName) {
    }

    public void onPackageRemoved(final String packageName, boolean replacing) {
        if (!replacing) {
            this.mAppStates.load(new Runnable() {
                public void run() {
                    UninstallRefundTracker.this.refundIfNecessary(packageName);
                }
            });
        }
    }

    public void onPackageChanged(String packageName) {
    }

    public void onPackageAvailabilityChanged(String[] packageNames, boolean available) {
    }

    private void refundIfNecessary(String packageName) {
        AppActionAnalyzer appActionAnalyzer = new AppActionAnalyzer(packageName, FinskyApp.get().getAppStates(), FinskyApp.get().getLibraries());
        if (appActionAnalyzer.isRefundable) {
            AppSupport.silentRefund(null, packageName, appActionAnalyzer.refundAccount, false, new RefundListener() {
                public void onRefundStart() {
                }

                public void onRefundComplete(boolean succeeded) {
                    if (succeeded) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                Toast.makeText(UninstallRefundTracker.this.mContext, R.string.refunded, 1).show();
                            }
                        });
                    }
                }
            });
        }
    }

    public void onPackageFirstLaunch(String packageName) {
    }
}
