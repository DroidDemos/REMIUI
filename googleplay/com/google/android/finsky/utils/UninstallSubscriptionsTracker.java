package com.google.android.finsky.utils;

import android.content.Context;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;

public class UninstallSubscriptionsTracker implements PackageStatusListener {
    private final Context mContext;
    private DfeDetails mDfeDetails;
    private final Libraries mLibraries;

    public UninstallSubscriptionsTracker(Context context, Libraries libraries, PackageMonitorReceiver receiver) {
        this.mLibraries = libraries;
        this.mContext = context;
        receiver.attach(this);
    }

    public void onPackageAdded(String packageName) {
    }

    public void onPackageRemoved(final String packageName, boolean replacing) {
        if (!replacing) {
            this.mLibraries.load(new Runnable() {
                public void run() {
                    UninstallSubscriptionsTracker.this.checkAndNotifyActiveSubscriptions(packageName);
                }
            });
        }
    }

    public void onPackageChanged(String packageName) {
    }

    public void onPackageAvailabilityChanged(String[] packageNames, boolean available) {
    }

    public void onPackageFirstLaunch(String packageName) {
    }

    private void checkAndNotifyActiveSubscriptions(final String packageName) {
        if (DocUtils.hasAutoRenewingSubscriptions(this.mLibraries, packageName)) {
            this.mDfeDetails = new DfeDetails(FinskyApp.get().getDfeApi(), DfeUtils.createDetailsUrlFromId(packageName));
            this.mDfeDetails.addDataChangedListener(new OnDataChangedListener() {
                public void onDataChanged() {
                    if (UninstallSubscriptionsTracker.this.mDfeDetails.getDocument() != null) {
                        Document doc = UninstallSubscriptionsTracker.this.mDfeDetails.getDocument();
                        FinskyApp.get().getNotifier().showSubscriptionsWarningMessage(UninstallSubscriptionsTracker.this.mContext.getResources().getString(R.string.active_subscriptions_title, new Object[]{doc.getTitle()}), packageName, UninstallSubscriptionsTracker.this.mContext.getResources().getString(R.string.active_subscriptions_body));
                    }
                }
            });
        }
    }
}
