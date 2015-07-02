package com.google.android.finsky.installer;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageInstaller.Session;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageInstaller.SessionParams;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.installer.PackageInstallerFacade.InstallListener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.PackageManagerHelper;
import com.google.android.finsky.utils.Sets;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PackageInstallerImpl implements PackageInstallerFacade {
    private final Context mContext;
    private final Map<String, Session> mOpenSessionMap;
    private final PackageInstaller mPackageInstaller;
    private final Map<String, SessionInfo> mSessionInfoMap;

    private static class SessionStreamWrapper extends FilterOutputStream {
        private final Session session;
        private final OutputStream stream;

        public SessionStreamWrapper(OutputStream stream, Session session) {
            super(stream);
            this.stream = stream;
            this.session = session;
        }

        public void write(byte[] buffer, int offset, int length) throws IOException {
            this.stream.write(buffer, offset, length);
        }
    }

    public PackageInstallerImpl(Context context) {
        this.mContext = context;
        this.mPackageInstaller = this.mContext.getPackageManager().getPackageInstaller();
        this.mSessionInfoMap = Maps.newHashMap();
        for (SessionInfo sessionInfo : this.mPackageInstaller.getMySessions()) {
            if (((SessionInfo) this.mSessionInfoMap.put(sessionInfo.getAppPackageName(), sessionInfo)) != null) {
                FinskyLog.w("Multiple sessions for %s found. Removing %d & keeping %d.", sessionInfo.getAppPackageName(), Integer.valueOf(((SessionInfo) this.mSessionInfoMap.put(sessionInfo.getAppPackageName(), sessionInfo)).getSessionId()), Integer.valueOf(sessionInfo.getSessionId()));
            }
        }
        this.mOpenSessionMap = Maps.newHashMap();
    }

    public void createSession(String packageName, long installSize, String title, Bitmap appIcon) {
        try {
            innerCreateSession(packageName, installSize, title, appIcon);
        } catch (IOException ioe) {
            FinskyLog.e(ioe, "Couldn't create session for %s: %s", packageName, ioe.getMessage());
        }
    }

    private synchronized void innerCreateSession(String packageName, long installSize, String title, Bitmap appIcon) throws IOException {
        if (this.mSessionInfoMap.containsKey(packageName)) {
            FinskyLog.wtf("Creating session for %s when one already exists", packageName);
        } else {
            SessionParams params = new SessionParams(1);
            if (appIcon != null) {
                params.setAppIcon(appIcon);
            }
            if (!TextUtils.isEmpty(title)) {
                params.setAppLabel(title);
            }
            params.setAppPackageName(packageName);
            if (installSize > 0) {
                params.setSize(installSize);
            }
            this.mSessionInfoMap.put(packageName, this.mPackageInstaller.getSessionInfo(this.mPackageInstaller.createSession(params)));
            FinskyLog.d("Created session %d for %s", Integer.valueOf(sessionId), packageName);
        }
    }

    public synchronized boolean hasSession(String packageName) {
        return this.mSessionInfoMap.containsKey(packageName);
    }

    public synchronized void cancelSession(String packageName) {
        SessionInfo sessionInfo = (SessionInfo) this.mSessionInfoMap.get(packageName);
        if (sessionInfo != null) {
            cancelSession(sessionInfo.getSessionId(), packageName);
        }
    }

    private synchronized void cancelSession(int sessionId, String packageName) {
        closeSession(packageName);
        this.mSessionInfoMap.remove(packageName);
        try {
            this.mPackageInstaller.abandonSession(sessionId);
            FinskyLog.d("Canceling session %d for %s", Integer.valueOf(sessionId), packageName);
        } catch (SecurityException e) {
        }
    }

    public synchronized void pruneSessions(List<String> packagesToRetain) throws IllegalStateException {
        Set<String> sessionsToRemove = Sets.newHashSet(this.mSessionInfoMap.keySet());
        sessionsToRemove.removeAll(packagesToRetain);
        for (String sessionPackageName : sessionsToRemove) {
            FinskyLog.d("Pruning stale session for %s", sessionPackageName);
            cancelSession(sessionPackageName);
        }
    }

    public synchronized void reportProgress(String packageName, long bytesCompleted, long bytesTotal) {
        Session session = getSession(packageName);
        if (session != null && bytesTotal > 0) {
            try {
                session.setStagingProgress(((float) bytesCompleted) / ((float) bytesTotal));
            } catch (Exception e) {
                FinskyLog.e("Session for %s unexpectedly closed: %s", e.getMessage());
                this.mOpenSessionMap.remove(packageName);
            }
        }
    }

    public int getAppIconSize() {
        return ((ActivityManager) this.mContext.getSystemService("activity")).getLauncherLargeIconSize();
    }

    public synchronized void setAppIcon(String packageName, Bitmap appIcon) {
        SessionInfo info = (SessionInfo) this.mSessionInfoMap.get(packageName);
        if (info != null) {
            try {
                this.mPackageInstaller.updateSessionAppIcon(info.getSessionId(), appIcon);
            } catch (SecurityException e) {
                FinskyLog.w("Stale session id %d for %s", Integer.valueOf(info.getSessionId()), packageName);
            }
        }
    }

    private synchronized Session getSession(String packageName) {
        Session session = null;
        synchronized (this) {
            Session session2 = (Session) this.mOpenSessionMap.get(packageName);
            if (session2 != null) {
                try {
                    session2.getNames();
                    session = session2;
                } catch (IOException ioe) {
                    FinskyLog.e("Stale open session for %s: %s", packageName, ioe.getMessage());
                    this.mOpenSessionMap.remove(packageName);
                } catch (SecurityException se) {
                    FinskyLog.e("Stale open session for %s: %s", packageName, se.getMessage());
                    this.mOpenSessionMap.remove(packageName);
                }
            }
            SessionInfo sessionInfo = (SessionInfo) this.mSessionInfoMap.get(packageName);
            if (sessionInfo != null) {
                try {
                    session2 = this.mPackageInstaller.openSession(sessionInfo.getSessionId());
                    this.mOpenSessionMap.put(packageName, session2);
                    session = session2;
                } catch (SecurityException e) {
                    FinskyLog.w("SessionInfo was stale for %s - deleting info", packageName);
                    this.mSessionInfoMap.remove(packageName);
                } catch (IOException ioe2) {
                    FinskyLog.w("IOException opening old session for %s - deleting info", ioe2.getMessage());
                    this.mSessionInfoMap.remove(packageName);
                }
            }
        }
        return session;
    }

    private synchronized void closeSession(String packageName) {
        Session session = (Session) this.mOpenSessionMap.remove(packageName);
        if (session != null) {
            try {
                session.close();
            } catch (Exception e) {
                FinskyLog.e("Unexpected error closing session for %s: %s", packageName, e.getMessage());
            }
        }
    }

    public boolean requireCopy(boolean isExternal) {
        return true;
    }

    public OutputStream getStream(String packageName, String splitName, long expectedSize) throws IOException {
        Session session = getSession(packageName);
        if (session != null) {
            return new SessionStreamWrapper(session.openWrite(packageName, 0, expectedSize), session);
        }
        FinskyLog.e("Can't open session for %s", packageName);
        throw new IOException();
    }

    public void finishStream(OutputStream stream) throws IOException {
        SessionStreamWrapper streamWrapper = (SessionStreamWrapper) stream;
        OutputStream sessionStream = streamWrapper.stream;
        streamWrapper.session.fsync(sessionStream);
        sessionStream.close();
    }

    public void setInstallUri(String packageName, Uri contentUri) {
        FinskyLog.wtf("Cannot install %s from uri %s", packageName, contentUri);
    }

    public synchronized void install(String packageName, boolean isForwardLocked, final InstallListener callback) {
        SessionInfo sessionInfo = (SessionInfo) this.mSessionInfoMap.get(packageName);
        Session session = (Session) this.mOpenSessionMap.get(packageName);
        if (sessionInfo == null || session == null) {
            FinskyLog.wtf("Unexpected missing open session for %s", packageName);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    callback.installFailed(971, null);
                }
            });
        } else {
            session.commit(getCommitCallback(packageName, sessionInfo.getSessionId(), callback));
            session.close();
            this.mOpenSessionMap.remove(packageName);
        }
    }

    private IntentSender getCommitCallback(final String packageName, final int sessionId, final InstallListener callback) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                PackageInstallerImpl.this.mContext.unregisterReceiver(this);
                PackageInstallerImpl.this.handleCommitCallback(intent, packageName, sessionId, callback);
            }
        };
        String action = "com.android.vending.INTENT_PACKAGE_INSTALL_COMMIT." + packageName;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
        return PendingIntent.getBroadcast(this.mContext, packageName.hashCode(), new Intent(action), 1207959552).getIntentSender();
    }

    private void handleCommitCallback(Intent intent, String packageName, int sessionId, InstallListener callback) {
        String statusMessage = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
        int status = intent.getIntExtra("android.content.pm.extra.STATUS", Integer.MIN_VALUE);
        if (status == 0) {
            FinskyApp.get().getPackageInfoRepository().invalidate(packageName);
            cancelSession(sessionId, packageName);
            callback.installSucceeded();
        } else if (status == -1) {
            cancelSession(sessionId, packageName);
            callback.installFailed(976, null);
        } else {
            int errorCode;
            cancelSession(sessionId, packageName);
            if (status == Integer.MIN_VALUE) {
                errorCode = 977;
            } else {
                errorCode = -500 - status;
            }
            FinskyLog.e("Error %d while installing %s: %s", Integer.valueOf(errorCode), packageName, statusMessage);
            callback.installFailed(errorCode, null);
        }
    }

    public void uninstallPackage(String packageName) {
        PackageManagerHelper.uninstallPackage(packageName);
    }
}
