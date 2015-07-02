package com.google.android.finsky.utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.PackageManagerUtils.PackageInstallObserver;
import com.google.android.finsky.utils.Sha1Util.DigestResult;
import java.io.IOException;

public class PackageManagerHelper {

    public interface InstallPackageListener {
        void installFailed(int i, String str);

        void installSucceeded();
    }

    private static class OnCompleteListenerNotifier extends AsyncTask<Void, Void, Uri> {
        private final boolean mAllowDowngrade;
        private final Uri mContentUri;
        private volatile DigestResult mDigestResult;
        private final String mExpectedSignature;
        private final long mExpectedSize;
        private final Handler mHandler;
        private final boolean mIsForwardLocked;
        private final String mPackageName;
        private final InstallPackageListener mPostInstallCallback;
        private volatile IOException mVerificationException;

        private OnCompleteListenerNotifier(Uri contentUri, long expectedSize, String expectedSignature, InstallPackageListener postInstallCallback, boolean isForwardLocked, String packageName, boolean allowDowngrade) {
            this.mHandler = new Handler(Looper.getMainLooper());
            this.mContentUri = contentUri;
            this.mExpectedSize = expectedSize;
            this.mExpectedSignature = expectedSignature;
            this.mPostInstallCallback = postInstallCallback;
            this.mIsForwardLocked = isForwardLocked;
            this.mPackageName = packageName;
            this.mAllowDowngrade = allowDowngrade;
        }

        protected Uri doInBackground(Void... params) {
            this.mVerificationException = null;
            if (this.mExpectedSize >= 0) {
                try {
                    this.mDigestResult = Sha1Util.secureHash(FinskyApp.get().getContentResolver().openInputStream(this.mContentUri));
                } catch (IOException ioe) {
                    this.mVerificationException = ioe;
                }
            }
            return FinskyApp.get().getDownloadQueue().getDownloadManager().getFileUriForContentUri(this.mContentUri);
        }

        protected void onPostExecute(Uri fileUri) {
            String str = null;
            int errorCode = 0;
            if (this.mVerificationException != null) {
                FinskyLog.w("Verification check of %s failed, exception=%s", this.mPackageName, this.mVerificationException);
                errorCode = 961;
            } else if (this.mDigestResult != null) {
                if (this.mExpectedSize != this.mDigestResult.byteCount) {
                    FinskyLog.w("Signature check of %s failed, size expected=%d actual=%d", this.mPackageName, Long.valueOf(this.mExpectedSize), Long.valueOf(this.mDigestResult.byteCount));
                    errorCode = 919;
                } else if (!this.mExpectedSignature.equals(this.mDigestResult.hashBase64)) {
                    FinskyLog.w("Signature check of %s failed, hash expected=%s actual=%s", this.mPackageName, this.mExpectedSignature, this.mDigestResult.hashBase64);
                    errorCode = 960;
                }
            }
            if (errorCode != 0) {
                FinskyLog.w("Signature check failed, aborting installation. Error %d", Integer.valueOf(errorCode));
                InstallPackageListener installPackageListener = this.mPostInstallCallback;
                if (this.mVerificationException != null) {
                    str = this.mVerificationException.getClass().getSimpleName();
                }
                installPackageListener.installFailed(errorCode, str);
                return;
            }
            Uri uri;
            int installFlags = 2;
            if (this.mIsForwardLocked) {
                installFlags = 2 | 1;
            }
            if (this.mAllowDowngrade) {
                FinskyLog.d("Allowing downgrade install for %s", this.mPackageName);
                installFlags |= 128;
            }
            PackageInstallObserver observer = new PackageInstallObserver() {
                public void packageInstalled(String pkg, final int returnCode) {
                    FinskyApp.get().getPackageInfoRepository().invalidate(OnCompleteListenerNotifier.this.mPackageName);
                    try {
                        FinskyLog.d("Package install status for %s is %d", OnCompleteListenerNotifier.this.mPackageName, Integer.valueOf(returnCode));
                        if (returnCode == 1) {
                            OnCompleteListenerNotifier.this.mHandler.post(new Runnable() {
                                public void run() {
                                    OnCompleteListenerNotifier.this.mPostInstallCallback.installSucceeded();
                                }
                            });
                        } else {
                            OnCompleteListenerNotifier.this.mHandler.post(new Runnable() {
                                public void run() {
                                    OnCompleteListenerNotifier.this.mPostInstallCallback.installFailed(returnCode, null);
                                }
                            });
                        }
                    } catch (Exception e) {
                        final String exceptionName = e.getClass().getSimpleName();
                        OnCompleteListenerNotifier.this.mHandler.post(new Runnable() {
                            public void run() {
                                OnCompleteListenerNotifier.this.mPostInstallCallback.installFailed(915, exceptionName);
                            }
                        });
                        FinskyLog.wtf(e, "Package install observer exception", new Object[0]);
                    }
                }
            };
            if (fileUri == null) {
                uri = this.mContentUri;
            } else {
                uri = fileUri;
            }
            if (uri != null) {
                PackageManagerUtils.installPackage(FinskyApp.get(), uri, observer, installFlags);
            } else {
                this.mPostInstallCallback.installFailed(-3, null);
            }
        }
    }

    public static void installPackage(Uri contentUri, long expectedSize, String expectedSignature, InstallPackageListener postInstallCallback, boolean isForwardLocked, String packageName) {
        Utils.executeMultiThreaded(new OnCompleteListenerNotifier(contentUri, expectedSize, expectedSignature, postInstallCallback, isForwardLocked, packageName, false), new Void[0]);
    }

    public static void installPackageWithDowngrade(Uri contentUri, InstallPackageListener postInstallCallback, boolean isUpdate, String packageName, boolean allowDowngrade) {
        Utils.executeMultiThreaded(new OnCompleteListenerNotifier(contentUri, -1, null, postInstallCallback, false, packageName, allowDowngrade), new Void[0]);
    }

    public static void uninstallPackage(String packageName) {
        PackageManagerUtils.uninstallPackage(FinskyApp.get(), packageName);
    }
}
