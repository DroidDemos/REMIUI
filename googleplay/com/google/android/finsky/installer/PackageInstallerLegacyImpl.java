package com.google.android.finsky.installer;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.finsky.installer.PackageInstallerFacade.InstallListener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.PackageManagerHelper;
import com.google.android.finsky.utils.PackageManagerHelper.InstallPackageListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class PackageInstallerLegacyImpl implements PackageInstallerFacade {
    private Uri mContentUri;
    private final Context mContext;
    private String mInstallingPackageName;
    private File mOutputFile;

    public PackageInstallerLegacyImpl(Context context) {
        this.mInstallingPackageName = null;
        this.mOutputFile = null;
        this.mContentUri = null;
        this.mContext = context;
    }

    public void createSession(String packageName, long installSize, String title, Bitmap appIcon) {
    }

    public boolean hasSession(String packageName) {
        return false;
    }

    public synchronized void cancelSession(String packageName) {
        if (packageName.equals(this.mInstallingPackageName)) {
            if (this.mOutputFile != null) {
                this.mOutputFile.delete();
                this.mOutputFile = null;
            }
            if (this.mContentUri != null) {
                this.mContentUri = null;
            }
            this.mInstallingPackageName = null;
        }
    }

    public void pruneSessions(List<String> list) {
    }

    public void reportProgress(String packageName, long bytesCompleted, long bytesTotal) {
    }

    public int getAppIconSize() {
        return -1;
    }

    public void setAppIcon(String packageName, Bitmap appIcon) {
    }

    public boolean requireCopy(boolean isExternal) {
        return isExternal;
    }

    public synchronized OutputStream getStream(String packageName, String splitName, long expectedSize) throws IOException {
        OutputStream outputStream;
        boolean madeReadable = true;
        synchronized (this) {
            if (this.mOutputFile != null) {
                FinskyLog.wtf("Already streaming file %s for %s", this.mOutputFile.getName(), this.mInstallingPackageName);
            }
            if (this.mContentUri != null) {
                FinskyLog.wtf("Already tracking file %s for %s", this.mContentUri, this.mInstallingPackageName);
                this.mOutputFile = null;
            }
            File cacheDir = this.mContext.getCacheDir();
            if (cacheDir.setExecutable(true, false)) {
                File outputDirectory = new File(cacheDir, "copies");
                outputDirectory.mkdirs();
                if (!(outputDirectory.setExecutable(true, false) && outputDirectory.setReadable(true, false))) {
                    madeReadable = false;
                }
                if (madeReadable) {
                    File outputFile = File.createTempFile(packageName, ".apk", outputDirectory);
                    if (outputFile.setReadable(true, false)) {
                        try {
                            outputStream = new FileOutputStream(outputFile);
                            this.mInstallingPackageName = packageName;
                            this.mOutputFile = outputFile;
                        } catch (IOException ioe) {
                            outputFile.delete();
                            throw ioe;
                        }
                    }
                    FinskyLog.w("Could not make readable %s", outputFile);
                    outputFile.delete();
                    throw new IOException("Could not make destination file writeable");
                }
                FinskyLog.w("Could not make readable %s", outputDirectory);
                throw new IOException("Could not make destination dir readable");
            }
            FinskyLog.w("Could not make executable %s", cacheDir);
            throw new IOException("Could not make cache dir executable");
        }
        return outputStream;
    }

    public void finishStream(OutputStream stream) throws IOException {
        stream.flush();
        stream.close();
    }

    public void setInstallUri(String packageName, Uri contentUri) {
        if (this.mOutputFile != null) {
            FinskyLog.wtf("Already streaming file %s for %s", this.mOutputFile.getName(), this.mInstallingPackageName);
        }
        if (this.mContentUri != null) {
            FinskyLog.wtf("Already tracking file %s for %s", this.mContentUri, this.mInstallingPackageName);
            this.mOutputFile = null;
        }
        this.mInstallingPackageName = packageName;
        this.mContentUri = contentUri;
    }

    public synchronized void install(String packageName, boolean isForwardLocked, final InstallListener callback) {
        Uri contentUri;
        if (this.mOutputFile != null) {
            contentUri = Uri.fromFile(this.mOutputFile);
        } else if (this.mContentUri != null) {
            contentUri = this.mContentUri;
        } else {
            throw new IllegalStateException("No file or URI to install from");
        }
        PackageManagerHelper.installPackage(contentUri, -1, null, new InstallPackageListener() {
            public void installSucceeded() {
                commonCleanup();
                callback.installSucceeded();
            }

            public void installFailed(int errorCode, String exceptionType) {
                synchronized (PackageInstallerLegacyImpl.this) {
                    commonCleanup();
                    callback.installFailed(errorCode, exceptionType);
                }
            }

            private void commonCleanup() {
                synchronized (PackageInstallerLegacyImpl.this) {
                    if (PackageInstallerLegacyImpl.this.mOutputFile != null) {
                        PackageInstallerLegacyImpl.this.mOutputFile.delete();
                        PackageInstallerLegacyImpl.this.mOutputFile = null;
                    }
                    if (PackageInstallerLegacyImpl.this.mContentUri != null) {
                        PackageInstallerLegacyImpl.this.mContentUri = null;
                    }
                    PackageInstallerLegacyImpl.this.mInstallingPackageName = null;
                }
            }
        }, isForwardLocked, packageName);
    }

    public void uninstallPackage(String packageName) {
        PackageManagerHelper.uninstallPackage(packageName);
    }
}
