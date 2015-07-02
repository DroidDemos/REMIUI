package com.google.android.finsky.utils;

import android.content.Context;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageInstallObserver.Stub;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

public class PackageManagerUtils {

    public interface PackageInstallObserver {
        void packageInstalled(String str, int i);
    }

    public interface FreeSpaceListener {
        void onComplete(boolean z);
    }

    class AnonymousClass1 extends Stub {
        private final /* synthetic */ PackageInstallObserver val$observer;

        AnonymousClass1(PackageInstallObserver packageInstallObserver) {
            this.val$observer = packageInstallObserver;
        }

        public void packageInstalled(String packageName, int returnCode) {
            if (this.val$observer != null) {
                this.val$observer.packageInstalled(packageName, returnCode);
            }
        }
    }

    class AnonymousClass2 extends IPackageDataObserver.Stub {
        private final /* synthetic */ FreeSpaceListener val$listener;

        AnonymousClass2(FreeSpaceListener freeSpaceListener) {
            this.val$listener = freeSpaceListener;
        }

        public void onRemoveCompleted(String packageName, boolean succeeded) {
            this.val$listener.onComplete(succeeded);
        }
    }

    public static void installPackage(Context context, Uri uri, PackageInstallObserver observer, int installFlags) {
        context.getPackageManager().installPackage(uri, new AnonymousClass1(observer), installFlags, "com.android.vending");
    }

    public static int installExistingPackage(Context context, String packageName) throws NameNotFoundException {
        return context.getPackageManager().installExistingPackage(packageName);
    }

    public static void freeStorageAndNotify(Context context, long requiredSize, FreeSpaceListener listener) {
        context.getPackageManager().freeStorageAndNotify(requiredSize, new AnonymousClass2(listener));
    }

    public static void uninstallPackage(Context ctx, String packageName) {
        ctx.getPackageManager().deletePackage(packageName, null, 0);
    }
}
