package com.google.android.finsky.installer;

import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface PackageInstallerFacade {

    public interface InstallListener {
        void installFailed(int i, String str);

        void installSucceeded();
    }

    void cancelSession(String str);

    void createSession(String str, long j, String str2, Bitmap bitmap);

    void finishStream(OutputStream outputStream) throws IOException;

    int getAppIconSize();

    OutputStream getStream(String str, String str2, long j) throws IOException;

    boolean hasSession(String str);

    void install(String str, boolean z, InstallListener installListener);

    void pruneSessions(List<String> list);

    void reportProgress(String str, long j, long j2);

    boolean requireCopy(boolean z);

    void setAppIcon(String str, Bitmap bitmap);

    void setInstallUri(String str, Uri uri);

    void uninstallPackage(String str);
}
