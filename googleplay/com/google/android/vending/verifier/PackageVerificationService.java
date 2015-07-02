package com.google.android.vending.verifier;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings.Secure;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.config.G;
import com.google.android.finsky.installer.PackageInstallerFactory;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Sets;
import com.google.android.finsky.utils.Utils;
import com.google.android.vending.verifier.api.PackageVerificationApi;
import com.google.android.vending.verifier.api.PackageVerificationApi.FileInfo;
import com.google.android.vending.verifier.api.PackageVerificationResult;
import com.google.android.wallet.instrumentmanager.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PackageVerificationService extends Service {
    private static PackageVerificationService sInstance;
    private int mLastStartId;
    private ArrayList<String> mRemovalRequests;
    private ArrayList<VerificationState> mVerifications;
    private VerifyInstalledPackagesTask mVerifyInstalledPackagesTask;

    private class CleanupRemovalRequestTask extends AsyncTask<Void, Void, Void> {
        private final Context mContext;
        private final boolean mDontWarn;
        private final String mPackageName;
        private final byte[] mResponseToken;
        private final boolean mUninstall;

        public CleanupRemovalRequestTask(String packageName, boolean uninstall, boolean dontWarn, byte[] responseToken) {
            this.mContext = PackageVerificationService.this;
            this.mPackageName = packageName;
            this.mUninstall = uninstall;
            this.mDontWarn = dontWarn;
            this.mResponseToken = responseToken;
        }

        protected Void doInBackground(Void... params) {
            PackageVerificationDataStore datastore = new PackageVerificationDataStore(this.mContext);
            if (this.mUninstall) {
                FinskyLog.d("Uninstalling %s", this.mPackageName);
                PackageInstallerFactory.getPackageInstaller().uninstallPackage(this.mPackageName);
                datastore.remove(this.mPackageName);
            } else {
                datastore.setSuppressUserWarning(this.mPackageName, this.mDontWarn);
            }
            return null;
        }

        protected void onPostExecute(Void param) {
            PackageVerificationApi.reportUserDecision(this.mUninstall ? 1 : 0, this.mDontWarn, this.mResponseToken, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    FinskyLog.d("Verification feedback package=%s error response %s", CleanupRemovalRequestTask.this.mPackageName, error);
                }
            });
            PackageVerificationService.this.mRemovalRequests.remove(this.mPackageName);
            PackageVerificationService.this.stopServiceIfIdle();
        }
    }

    private static class VerificationState {
        public final Uri dataUri;
        public final int flags;
        public final boolean fromVerificationActivity;
        public final int id;
        public String mDescription;
        public Activity mDialog;
        public FileInfo[] mFileInfos;
        public String mLabel;
        public long mLength;
        public String mPackageName;
        public int mResult;
        public byte[] mSha256;
        public byte[][] mSignatures;
        public byte[] mToken;
        public Integer mVersion;
        public final Intent originalIntent;
        public InetAddress originatingIp;
        public String[] originatingPackageNames;
        public byte[][] originatingSignatures;
        public final int originatingUid;
        public final Uri originatingUri;
        public InetAddress referrerIp;
        public final Uri referrerUri;

        public VerificationState(Intent broadcast) {
            this.mResult = 1;
            Bundle extras = broadcast.getExtras();
            this.id = extras.getInt("android.content.pm.extra.VERIFICATION_ID");
            this.dataUri = broadcast.getData();
            this.flags = extras.getInt("android.content.pm.extra.VERIFICATION_INSTALL_FLAGS");
            this.originatingUri = (Uri) extras.getParcelable("android.intent.extra.ORIGINATING_URI");
            this.referrerUri = (Uri) extras.getParcelable("android.intent.extra.REFERRER");
            this.originatingUid = extras.getInt("android.intent.extra.ORIGINATING_UID", -1);
            this.fromVerificationActivity = extras.getBoolean("com.google.android.vending.verifier.extra.FROM_VERIFICATION_ACTIVITY");
            this.originalIntent = broadcast;
            this.mPackageName = extras.getString("android.content.pm.extra.VERIFICATION_PACKAGE_NAME");
            this.mVersion = (Integer) extras.get("android.content.pm.extra.VERIFICATION_VERSION_CODE");
        }

        public String toString() {
            return String.format("id = %d, data=%s flags=%d fromVerificationActivity=%b", new Object[]{Integer.valueOf(this.id), this.dataUri, Integer.valueOf(this.flags), Boolean.valueOf(this.fromVerificationActivity)});
        }
    }

    private class VerifyInstalledPackagesTask extends AsyncTask<Void, Void, Map<String, PackageVerificationData>> {
        private final Context mContext;

        public VerifyInstalledPackagesTask() {
            this.mContext = PackageVerificationService.this;
        }

        protected void onPreExecute() {
            FinskyLog.d("Verifying installed apps", new Object[0]);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected java.util.Map<java.lang.String, com.google.android.vending.verifier.PackageVerificationData> doInBackground(java.lang.Void... r31) {
            /*
            r30 = this;
            r21 = com.google.android.finsky.utils.Maps.newHashMap();
            r0 = r30;
            r10 = com.google.android.vending.verifier.PackageVerificationService.this;
            r10 = r10.getPackageManager();
            r27 = 64;
            r0 = r27;
            r20 = r10.getInstalledPackages(r0);
            r22 = new com.google.android.vending.verifier.PackageVerificationDataStore;
            r0 = r30;
            r10 = r0.mContext;
            r0 = r22;
            r0.<init>(r10);
            r10 = com.google.android.finsky.utils.FinskyPreferences.acceptedAntiMalwareConsent;
            r10 = r10.get();
            r10 = (java.lang.Boolean) r10;
            r11 = r10.booleanValue();
            r10 = com.google.android.finsky.config.G.verifyInstalledPlayPackagesEnabled;
            r10 = r10.get();
            r10 = (java.lang.Boolean) r10;
            r26 = r10.booleanValue();
            r14 = r22.getAll();
            r25 = new java.util.HashSet;
            r10 = r14.keySet();
            r0 = r25;
            r0.<init>(r10);
            r18 = r20.iterator();
        L_0x004a:
            r10 = r18.hasNext();
            if (r10 == 0) goto L_0x0181;
        L_0x0050:
            r23 = r18.next();
            r23 = (android.content.pm.PackageInfo) r23;
            r10 = r30.isCancelled();
            if (r10 == 0) goto L_0x005f;
        L_0x005c:
            r21 = 0;
        L_0x005e:
            return r21;
        L_0x005f:
            r0 = r23;
            r3 = r0.packageName;
            r0 = r25;
            r0.remove(r3);
            r0 = r23;
            r13 = r0.applicationInfo;
            r10 = android.os.Build.VERSION.SDK_INT;
            r27 = 9;
            r0 = r27;
            if (r10 < r0) goto L_0x012a;
        L_0x0074:
            r0 = r23;
            r4 = r0.lastUpdateTime;
        L_0x0078:
            if (r13 == 0) goto L_0x0080;
        L_0x007a:
            r10 = r13.flags;
            r10 = r10 & 1;
            if (r10 != 0) goto L_0x004a;
        L_0x0080:
            r10 = com.google.android.finsky.FinskyApp.get();
            r10 = r10.getLibraries();
            r12 = r10.getAppOwners(r3);
            r10 = r12.isEmpty();
            if (r10 != 0) goto L_0x0131;
        L_0x0092:
            r19 = 1;
        L_0x0094:
            if (r19 != 0) goto L_0x0098;
        L_0x0096:
            if (r11 == 0) goto L_0x004a;
        L_0x0098:
            if (r19 == 0) goto L_0x009c;
        L_0x009a:
            if (r26 == 0) goto L_0x004a;
        L_0x009c:
            r0 = r22;
            r2 = r0.get(r3);
            if (r2 == 0) goto L_0x00ac;
        L_0x00a4:
            r0 = r2.mCacheFingerprint;
            r28 = r0;
            r10 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1));
            if (r10 == 0) goto L_0x00f1;
        L_0x00ac:
            r7 = 0;
            r6 = 0;
            r0 = r13.publicSourceDir;
            r24 = r0;
            r17 = new java.io.File;
            r0 = r17;
            r1 = r24;
            r0.<init>(r1);
            r10 = r17.exists();
            if (r10 != 0) goto L_0x0135;
        L_0x00c2:
            r10 = "Cannot find file for %s";
            r27 = 1;
            r0 = r27;
            r0 = new java.lang.Object[r0];
            r27 = r0;
            r28 = 0;
            r27[r28] = r24;
            r0 = r27;
            com.google.android.finsky.utils.FinskyLog.w(r10, r0);
        L_0x00d5:
            if (r6 == 0) goto L_0x004a;
        L_0x00d7:
            r10 = r13.publicSourceDir;
            r0 = r13.sourceDir;
            r27 = r0;
            r0 = r27;
            r10 = r10.equals(r0);
            if (r10 != 0) goto L_0x017c;
        L_0x00e5:
            r9 = 1;
        L_0x00e6:
            r2 = new com.google.android.vending.verifier.PackageVerificationData;
            r10 = 0;
            r2.<init>(r3, r4, r6, r7, r9, r10);
            r0 = r22;
            r0.put(r2);
        L_0x00f1:
            r0 = r19;
            r2.mInstalledByPlay = r0;
            r0 = r23;
            r10 = r0.applicationInfo;
            r10 = r10.flags;
            r27 = 2097152; // 0x200000 float:2.938736E-39 double:1.0361308E-317;
            r10 = r10 & r27;
            if (r10 == 0) goto L_0x017f;
        L_0x0101:
            r10 = 1;
        L_0x0102:
            r2.mInStoppedState = r10;
            r0 = r23;
            r10 = r0.signatures;
            r10 = com.google.android.vending.verifier.PackageVerificationService.getCertificateFingerprints(r10);
            r2.mCertFingerprints = r10;
            r10 = r2.mPackageName;
            r0 = r21;
            r0.put(r10, r2);
            r10 = "Adding %s for verification";
            r27 = 1;
            r0 = r27;
            r0 = new java.lang.Object[r0];
            r27 = r0;
            r28 = 0;
            r27[r28] = r3;
            r0 = r27;
            com.google.android.finsky.utils.FinskyLog.d(r10, r0);
            goto L_0x004a;
        L_0x012a:
            r0 = r23;
            r10 = r0.versionCode;
            r4 = (long) r10;
            goto L_0x0078;
        L_0x0131:
            r19 = 0;
            goto L_0x0094;
        L_0x0135:
            r10 = r17.canRead();
            if (r10 != 0) goto L_0x014f;
        L_0x013b:
            r10 = "Cannot read file for %s";
            r27 = 1;
            r0 = r27;
            r0 = new java.lang.Object[r0];
            r27 = r0;
            r28 = 0;
            r27[r28] = r24;
            r0 = r27;
            com.google.android.finsky.utils.FinskyLog.w(r10, r0);
            goto L_0x00d5;
        L_0x014f:
            r7 = r17.length();
            r6 = com.google.android.vending.verifier.PackageVerificationService.getSha256Hash(r17);	 Catch:{ IOException -> 0x0159, NoSuchAlgorithmException -> 0x0173 }
            goto L_0x00d5;
        L_0x0159:
            r16 = move-exception;
            r10 = "Error while calculating sha256 for file=%s, error=%s";
            r27 = 2;
            r0 = r27;
            r0 = new java.lang.Object[r0];
            r27 = r0;
            r28 = 0;
            r27[r28] = r24;
            r28 = 1;
            r27[r28] = r16;
            r0 = r27;
            com.google.android.finsky.utils.FinskyLog.d(r10, r0);
            goto L_0x00d5;
        L_0x0173:
            r16 = move-exception;
            r10 = new java.lang.RuntimeException;
            r0 = r16;
            r10.<init>(r0);
            throw r10;
        L_0x017c:
            r9 = 0;
            goto L_0x00e6;
        L_0x017f:
            r10 = 0;
            goto L_0x0102;
        L_0x0181:
            r18 = r25.iterator();
        L_0x0185:
            r10 = r18.hasNext();
            if (r10 == 0) goto L_0x005e;
        L_0x018b:
            r15 = r18.next();
            r15 = (java.lang.String) r15;
            r0 = r22;
            r0.remove(r15);
            goto L_0x0185;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.vending.verifier.PackageVerificationService.VerifyInstalledPackagesTask.doInBackground(java.lang.Void[]):java.util.Map<java.lang.String, com.google.android.vending.verifier.PackageVerificationData>");
        }

        protected void onPostExecute(Map<String, PackageVerificationData> installedPackagesData) {
            if (!isCancelled()) {
                if (installedPackagesData.isEmpty()) {
                    PackageVerificationService.this.cleanupVerifyInstalledPackages(true);
                } else {
                    PackageVerificationService.this.sendVerifyAllAppsRequest(installedPackagesData);
                }
            }
        }
    }

    private class WorkerTask extends AsyncTask<Void, Void, Boolean> {
        private final Context mContext;
        private final VerificationState mState;

        public WorkerTask(VerificationState state) {
            this.mState = state;
            this.mContext = PackageVerificationService.this;
        }

        protected void onPreExecute() {
            FinskyLog.d("Verification Requested for %s", this.mState);
        }

        protected Boolean doInBackground(Void... params) {
            this.mState.mResult = 1;
            if (!PackageVerificationService.this.getPackageInfo(this.mState)) {
                return Boolean.valueOf(false);
            }
            if (this.mState.originatingUid != -1) {
                PackageManager pm = this.mContext.getPackageManager();
                String[] packages = pm.getPackagesForUid(this.mState.originatingUid);
                this.mState.originatingPackageNames = packages;
                if (packages != null && packages.length > 0) {
                    try {
                        this.mState.originatingSignatures = PackageVerificationService.getRawSignatures(pm.getPackageInfo(packages[0], 64).signatures);
                        if (PackageVerificationService.this.rawSignaturesMatch(this.mState.originatingSignatures, this.mState.mSignatures) && !((Boolean) FinskyPreferences.acceptedAntiMalwareConsent.get()).booleanValue()) {
                            for (String packageName : packages) {
                                if (pm.checkPermission("android.permission.INSTALL_PACKAGES", packageName) == 0) {
                                    FinskyLog.d("Skipping verification for id=%d", Integer.valueOf(this.mState.id));
                                    return Boolean.valueOf(false);
                                }
                            }
                        }
                    } catch (NameNotFoundException e) {
                        FinskyLog.d("Could not retrieve signatures for package %s", packages[0]);
                    }
                }
            } else {
                this.mState.originatingPackageNames = null;
            }
            PackageVerificationService.resolveHosts(this.mState);
            return Boolean.valueOf(true);
        }

        protected void onPostExecute(Boolean continueVerification) {
            if (!continueVerification.booleanValue()) {
                PackageVerificationService.this.reportAndCleanup(this.mContext, this.mState);
            } else if (((Boolean) FinskyPreferences.acceptedAntiMalwareConsent.get()).booleanValue()) {
                PackageVerificationService.this.sendVerificationRequest(this.mState);
            } else {
                if (!this.mState.fromVerificationActivity) {
                    PackageVerificationService.this.extendTimeout(this.mState.id, 1);
                }
                ConsentDialog.show(PackageVerificationService.this, this.mState.id);
            }
        }
    }

    public PackageVerificationService() {
        this.mVerifyInstalledPackagesTask = null;
        this.mVerifications = Lists.newArrayList(1);
        this.mRemovalRequests = Lists.newArrayList(0);
    }

    public static void start(Context context, Intent fromBroadcast) {
        Intent intent = new Intent(context, PackageVerificationService.class);
        intent.putExtra("broadcast_intent", fromBroadcast);
        context.startService(intent);
    }

    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public void onDestroy() {
        destroyAllVerifications();
        sInstance = null;
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent broadcast = (Intent) intent.getParcelableExtra("broadcast_intent");
        String action = broadcast.getAction();
        if ("android.intent.action.PACKAGE_NEEDS_VERIFICATION".equals(action) || "android.intent.action.VIEW".equals(action) || "android.intent.action.INSTALL_PACKAGE".equals(action)) {
            handleVerificationIntent(broadcast);
        } else if ("com.google.android.vending.verifier.intent.action.VERIFY_INSTALLED_PACKAGES".equals(action)) {
            verifyInstalledPackages();
        } else if ("com.google.android.vending.verifier.intent.action.REMOVAL_REQUEST_RESPONSE".equals(action)) {
            handleRemovalRequestResponse(broadcast);
        } else if ("android.intent.action.PACKAGE_VERIFIED".equals(action)) {
            cancelVerificationIntent(broadcast);
        }
        this.mLastStartId = startId;
        stopServiceIfIdle();
        return 3;
    }

    private void handleVerificationIntent(Intent broadcast) {
        VerificationState state = new VerificationState(broadcast);
        this.mVerifications.add(state);
        Utils.executeMultiThreaded(new WorkerTask(state), new Void[0]);
    }

    private void verifyInstalledPackages() {
        if (this.mVerifyInstalledPackagesTask == null) {
            this.mVerifyInstalledPackagesTask = new VerifyInstalledPackagesTask();
            Utils.executeMultiThreaded(this.mVerifyInstalledPackagesTask, new Void[0]);
            return;
        }
        FinskyLog.w("Verify installed packages is already running.", new Object[0]);
    }

    private void handleRemovalRequestResponse(Intent broadcast) {
        Bundle extras = broadcast.getExtras();
        String packageName = extras.getString("android.content.pm.extra.VERIFICATION_PACKAGE_NAME");
        if (packageName != null) {
            this.mRemovalRequests.add(packageName);
            new CleanupRemovalRequestTask(packageName, extras.getBoolean("com.google.android.vending.verifier.intent.extra.UNINSTALL"), extras.getBoolean("com.google.android.vending.verifier.intent.extra.DONT_WARN"), extras.getByteArray("com.google.android.vending.verifier.intent.extra.RESPONSE_TOKEN")).execute(new Void[0]);
        }
    }

    public static void sendRemovalResponse(Context context, String packageName, boolean uninstall, boolean dontWarn, byte[] responseToken) {
        Intent intent = new Intent("com.google.android.vending.verifier.intent.action.REMOVAL_REQUEST_RESPONSE");
        intent.putExtra("android.content.pm.extra.VERIFICATION_PACKAGE_NAME", packageName);
        intent.putExtra("com.google.android.vending.verifier.intent.extra.UNINSTALL", uninstall);
        intent.putExtra("com.google.android.vending.verifier.intent.extra.DONT_WARN", dontWarn);
        intent.putExtra("com.google.android.vending.verifier.intent.extra.RESPONSE_TOKEN", responseToken);
        context.sendBroadcast(intent);
    }

    private void cancelVerificationIntent(Intent broadcast) {
        VerificationState state = findVerification(broadcast.getExtras().getInt("android.content.pm.extra.VERIFICATION_ID"));
        if (state != null && state.mResult != -1) {
            FinskyLog.d("Cancel active verification id=%d", Integer.valueOf(id));
            cancelDialog(state);
            this.mVerifications.remove(state);
        }
    }

    private static void resolveHosts(VerificationState state) {
        if (state.originatingUri != null) {
            try {
                state.originatingIp = InetAddress.getByName(state.originatingUri.getHost());
            } catch (UnknownHostException e) {
                FinskyLog.d("Could not resolve host %s", host);
            }
        }
        if (state.referrerUri != null) {
            try {
                state.referrerIp = InetAddress.getByName(state.referrerUri.getHost());
            } catch (UnknownHostException e2) {
                FinskyLog.d("Could not resolve host %s", host);
            }
        }
    }

    private void cleanupVerifyInstalledPackages(boolean success) {
        Utils.ensureOnMainThread();
        if (success) {
            FinskyPreferences.verifyInstalledPackagesLastSuccessMs.put(Long.valueOf(System.currentTimeMillis()));
        }
        this.mVerifyInstalledPackagesTask = null;
        stopServiceIfIdle();
    }

    private void reportAndCleanup(Context context, VerificationState state) {
        Utils.ensureOnMainThread();
        reportResult(context, state, state.mResult);
        this.mVerifications.remove(state);
        stopServiceIfIdle();
    }

    private void stopServiceIfIdle() {
        Utils.ensureOnMainThread();
        if (this.mVerifications.isEmpty() && this.mRemovalRequests.isEmpty() && this.mVerifyInstalledPackagesTask == null) {
            stopSelf(this.mLastStartId);
        }
    }

    private void reportResult(Context context, VerificationState state, int result) {
        if (!state.fromVerificationActivity) {
            reportVerificationResult(context, state.id, result);
        } else if (result == 1) {
            Intent installIntent = state.originalIntent;
            installIntent.setPackage("com.android.packageinstaller");
            installIntent.setComponent(new ComponentName("com.android.packageinstaller", "com.android.packageinstaller.PackageInstallerActivity"));
            installIntent.addFlags(268435456);
            startActivity(installIntent);
        }
    }

    private void reportVerificationResult(Context context, int id, int result) {
        context.getPackageManager().verifyPendingInstall(id, result);
    }

    private void extendTimeout(int id, int code) {
        getPackageManager().extendVerificationTimeout(id, code, ((Long) G.platformAntiMalwareDialogDelayMs.get()).longValue());
    }

    private void destroyAllVerifications() {
        Utils.ensureOnMainThread();
        while (!this.mVerifications.isEmpty()) {
            VerificationState state = (VerificationState) this.mVerifications.remove(0);
            FinskyLog.w("Destroying orphaned verification id=%d", Integer.valueOf(state.id));
            reportResult(this, state, 1);
            cancelDialog(state);
        }
        if (this.mVerifyInstalledPackagesTask != null) {
            this.mVerifyInstalledPackagesTask.cancel(true);
            this.mVerifyInstalledPackagesTask = null;
        }
    }

    private void cancelDialog(VerificationState state) {
        if (state.mDialog != null) {
            state.mDialog.finish();
            state.mDialog = null;
        }
    }

    private VerificationState findVerification(int id) {
        Iterator i$ = this.mVerifications.iterator();
        while (i$.hasNext()) {
            VerificationState state = (VerificationState) i$.next();
            if (state.id == id) {
                return state;
            }
        }
        return null;
    }

    public static boolean registerDialog(int id, Activity a) {
        Utils.ensureOnMainThread();
        if (sInstance == null) {
            return false;
        }
        VerificationState state = sInstance.findVerification(id);
        if (state == null) {
            return false;
        }
        state.mDialog = a;
        return true;
    }

    public static void reportUserChoice(int id, int result) {
        int decision = 1;
        Utils.ensureOnMainThread();
        FinskyLog.d("User selected %d for id=%d", Integer.valueOf(result), Integer.valueOf(id));
        if (sInstance != null) {
            final VerificationState state = sInstance.findVerification(id);
            if (state != null) {
                if (state.mResult != -1) {
                    if (result == 1) {
                        decision = 0;
                    }
                    PackageVerificationApi.reportUserDecision(decision, false, state.mToken, new ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            FinskyLog.d("Verification feedback id=%d error response %s", Integer.valueOf(state.id), error);
                        }
                    });
                }
                state.mResult = result;
                sInstance.reportAndCleanup(sInstance, state);
            }
        }
    }

    public static void reportConsentDialog(int id, boolean granted) {
        Utils.ensureOnMainThread();
        FinskyLog.d("User consent %b for id=%d", Boolean.valueOf(granted), Integer.valueOf(id));
        if (sInstance != null) {
            VerificationState state = sInstance.findVerification(id);
            if (state == null) {
                return;
            }
            if (granted) {
                FinskyPreferences.acceptedAntiMalwareConsent.put(Boolean.valueOf(true));
                migrateAntiMalwareConsent(sInstance);
                sInstance.sendVerificationRequest(state);
                return;
            }
            state.mResult = 1;
            sInstance.reportAndCleanup(sInstance, state);
        }
    }

    public static void migrateAntiMalwareConsent(Context context) {
        boolean consentAlreadyMigratedToSettings = false;
        if (!((Boolean) FinskyPreferences.acceptedAntiMalwareConsent.get()).booleanValue()) {
            return;
        }
        if (VERSION.SDK_INT >= 19) {
            ContentResolver contentResolver = context.getContentResolver();
            if (Secure.getInt(contentResolver, "package_verifier_user_consent", 0) == 1) {
                consentAlreadyMigratedToSettings = true;
            }
            if (!consentAlreadyMigratedToSettings) {
                Secure.putInt(contentResolver, "package_verifier_user_consent", 1);
                new File(new File(context.getApplicationInfo().dataDir, "shared_prefs"), "package_verifer_public_preferences.xml").delete();
                return;
            }
            return;
        }
        SharedPreferences preferences = context.getSharedPreferences("package_verifer_public_preferences", 1);
        if (!preferences.getBoolean("accepted-anti-malware-consent", false)) {
            Editor editor = preferences.edit();
            editor.putBoolean("accepted-anti-malware-consent", true);
            editor.commit();
        }
    }

    private boolean getPackageInfo(VerificationState state) {
        Uri dataUri = state.dataUri;
        if (dataUri == null) {
            FinskyLog.d("Null data for request id=%d", Integer.valueOf(state.id));
            return false;
        }
        if ("file".equalsIgnoreCase(dataUri.getScheme())) {
            String path = dataUri.getPath();
            File file = new File(path);
            if (!file.exists()) {
                FinskyLog.d("Cannot find file for %s in request id=%d", dataUri, Integer.valueOf(state.id));
                return false;
            } else if (file.canRead()) {
                try {
                    PackageInfo info = getPackageManager().getPackageArchiveInfo(path, 64);
                    if (info == null) {
                        FinskyLog.d("Cannot read archive for %s in request id=%d", dataUri, Integer.valueOf(state.id));
                        return false;
                    }
                    state.mPackageName = info.packageName;
                    state.mVersion = Integer.valueOf(info.versionCode);
                    state.mLength = file.length();
                    try {
                        state.mSha256 = getSha256Hash(file);
                        if (info.signatures != null) {
                            state.mSignatures = getRawSignatures(info.signatures);
                        } else {
                            state.mSignatures = CertificateUtils.collectCertificates(path);
                        }
                        try {
                            state.mFileInfos = ZipAnalyzer.analyzeZipFile(file);
                        } catch (IOException ex) {
                            FinskyLog.d("Error while getting information about apk contents for file=%s, error=%s", dataUri, ex);
                        } catch (RuntimeException ex2) {
                            FinskyLog.d("Error while getting information about apk contents for file=%s, error=%s", dataUri, ex2);
                        } catch (NoSuchAlgorithmException ex3) {
                            throw new RuntimeException(ex3);
                        }
                        info.applicationInfo.publicSourceDir = path;
                        CharSequence label = info.applicationInfo.loadLabel(getPackageManager());
                        if (label != null) {
                            state.mLabel = label.toString();
                        }
                        return true;
                    } catch (IOException ex4) {
                        FinskyLog.d("Error while calculating sha256 for file=%s, error=%s", dataUri, ex4);
                        return false;
                    } catch (NoSuchAlgorithmException ex32) {
                        throw new RuntimeException(ex32);
                    }
                } catch (Exception e) {
                    FinskyLog.d("Exception reading %s in request id=%d %s", dataUri, Integer.valueOf(state.id), e);
                    return false;
                }
            } else {
                FinskyLog.d("Cannot read file for %s in request id=%d", dataUri, Integer.valueOf(state.id));
                return false;
            }
        }
        FinskyLog.d("Unsupported scheme for %s in request id=%d", dataUri, Integer.valueOf(state.id));
        return false;
    }

    private static byte[] getSha256Hash(File apkFile) throws IOException, NoSuchAlgorithmException {
        InputStream stream = new FileInputStream(apkFile);
        try {
            byte[] sha256Hash = getSha256Hash(stream);
            return sha256Hash;
        } finally {
            stream.close();
        }
    }

    static byte[] getSha256Hash(InputStream stream) throws IOException, NoSuchAlgorithmException {
        MessageDigest digester = MessageDigest.getInstance("SHA256");
        byte[] buf = new byte[16384];
        while (true) {
            int bytesRead = stream.read(buf);
            if (bytesRead < 0) {
                return digester.digest();
            }
            digester.update(buf, 0, bytesRead);
        }
    }

    private static byte[][] getRawSignatures(Signature[] signatures) {
        if (signatures == null || signatures.length <= 0) {
            return (byte[][]) null;
        }
        byte[][] bArr = new byte[signatures.length][];
        for (int i = 0; i < signatures.length; i++) {
            bArr[i] = signatures[i].toByteArray();
        }
        return bArr;
    }

    private boolean rawSignaturesMatch(byte[][] signature1, byte[][] signature2) {
        if (signature1 == null || signature2 == null) {
            return false;
        }
        HashSet<Signature> set1 = Sets.newHashSet();
        for (byte[] sig : signature1) {
            set1.add(new Signature(sig));
        }
        HashSet<Signature> set2 = Sets.newHashSet();
        for (byte[] sig2 : signature2) {
            set2.add(new Signature(sig2));
        }
        return set1.equals(set2);
    }

    private static byte[][] getCertificateFingerprints(Signature[] signatures) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA1");
            if (signatures == null || signatures.length <= 0) {
                return (byte[][]) null;
            }
            byte[][] bArr = new byte[signatures.length][];
            for (int i = 0; i < signatures.length; i++) {
                bArr[i] = digester.digest(signatures[i].toByteArray());
            }
            return bArr;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void stopApplication(String packageName) {
        if (VERSION.SDK_INT >= 20) {
            FinskyLog.d("Attempting to stop application: %s", packageName);
            ActivityManager activityManager = (ActivityManager) sInstance.getSystemService("activity");
            try {
                ActivityManager.class.getMethod("forceStopPackage", new Class[]{String.class}).invoke(activityManager, new Object[]{packageName});
            } catch (NoSuchMethodException e) {
                FinskyLog.d("Cannot stop applications on this platform", new Object[0]);
            } catch (SecurityException e2) {
                FinskyLog.wtf(e2, "Cannot stop application due to security exception", new Object[0]);
            } catch (IllegalAccessException e3) {
                FinskyLog.wtf(e3, "Cannot stop application due to reflection access exception", new Object[0]);
            } catch (InvocationTargetException e4) {
                FinskyLog.wtf(e4, "Cannot stop application due to reflection invocation exception", new Object[0]);
            }
        }
    }

    private void sendVerificationRequest(final VerificationState state) {
        final VerificationState verificationState = state;
        PackageVerificationApi.verifyApp(state.mSha256, state.mLength, state.mPackageName, state.mVersion, state.mSignatures, state.mFileInfos, state.originatingUri, state.referrerUri, state.originatingIp, state.referrerIp, state.originatingPackageNames, state.originatingSignatures, getResources().getConfiguration().locale.toString(), ((Long) DfeApiConfig.androidId.get()).longValue(), new Listener<PackageVerificationResult>() {
            public void onResponse(PackageVerificationResult response) {
                FinskyLog.d("Verification id=%d response=%d", Integer.valueOf(verificationState.id), Integer.valueOf(response.verdict));
                PackageVerificationService context = PackageVerificationService.this;
                verificationState.mToken = response.token;
                if (response.uploadApk) {
                    PackageVerificationService.this.sendUploadApkIntent(verificationState);
                }
                switch (response.verdict) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        verificationState.mDescription = response.description;
                        verificationState.mResult = -1;
                        PackageVerificationService.this.reportResult(context, verificationState, verificationState.mResult);
                        PackageWarningDialog.show(context, verificationState.id, 1, verificationState.mLabel, verificationState.mDescription);
                        return;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        verificationState.mDescription = response.description;
                        if (!verificationState.fromVerificationActivity) {
                            PackageVerificationService.this.extendTimeout(verificationState.id, -1);
                        }
                        PackageWarningDialog.show(context, verificationState.id, 0, verificationState.mLabel, verificationState.mDescription);
                        return;
                    default:
                        verificationState.mResult = 1;
                        PackageVerificationService.this.reportAndCleanup(context, verificationState);
                        return;
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.d("Verification id=%d error response %s", Integer.valueOf(state.id), error);
                PackageVerificationService.this.reportAndCleanup(PackageVerificationService.this, state);
            }
        });
    }

    private void sendUploadApkIntent(VerificationState state) {
        try {
            Intent intent = new Intent("com.google.android.gms.security.verifyapps.UPLOAD_APK");
            intent.putExtra("package_name", state.mPackageName);
            intent.putExtra("digest", state.mSha256);
            intent.putExtra("version_code", state.mVersion);
            intent.putExtra("length", state.mLength);
            intent.putExtra("token", state.mToken);
            intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.security.verifier.ApkUploadService"));
            startService(intent);
        } catch (SecurityException e) {
            FinskyLog.e("Could not access ApkUploadService", new Object[0]);
        } catch (RuntimeException ex) {
            FinskyLog.e(ex, "Error occured while sending UPLOAD_APK intent", new Object[0]);
        }
    }

    private void sendVerifyAllAppsRequest(Map<String, PackageVerificationData> installedPackagesData) {
        String currentLocale = getResources().getConfiguration().locale.toString();
        long androidId = ((Long) DfeApiConfig.androidId.get()).longValue();
        boolean userConsented = ((Boolean) FinskyPreferences.acceptedAntiMalwareConsent.get()).booleanValue();
        final List<PackageVerificationData> requests = Lists.newArrayList(installedPackagesData.values());
        PackageVerificationApi.verifyInstalledApps(requests, currentLocale, Long.valueOf(androidId), userConsented, new Listener<PackageVerificationResult[]>() {
            public void onResponse(PackageVerificationResult[] responses) {
                for (int i = 0; i < requests.size(); i++) {
                    PackageVerificationData appData = (PackageVerificationData) requests.get(i);
                    PackageVerificationResult response = responses[i];
                    String label;
                    switch (response == null ? 0 : response.verdict) {
                        case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            if (!PackageVerificationService.this.isAlreadyInstalled(appData.mPackageName)) {
                                break;
                            }
                            label = PackageVerificationService.this.getApplicationName(appData.mPackageName);
                            if (label == null) {
                                label = appData.mPackageName;
                            }
                            FinskyApp.get().getNotifier().showHarmfulAppRemovedMessage(label, appData.mPackageName, response.description);
                            PackageVerificationService.this.stopApplication(appData.mPackageName);
                            PackageInstallerFactory.getPackageInstaller().uninstallPackage(appData.mPackageName);
                            break;
                        case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            if (PackageVerificationService.this.isAlreadyInstalled(appData.mPackageName) && !appData.mSuppressUserWarning) {
                                PackageVerificationService.this.stopApplication(appData.mPackageName);
                                label = PackageVerificationService.this.getApplicationName(appData.mPackageName);
                                if (label == null) {
                                    label = appData.mPackageName;
                                }
                                FinskyApp.get().getNotifier().showHarmfulAppRemoveRequestMessage(label, appData.mPackageName, response.description, response.token);
                                break;
                            }
                        default:
                            break;
                    }
                }
                PackageVerificationService.this.cleanupVerifyInstalledPackages(true);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.d("Multi verification error response %s", error);
                PackageVerificationService.this.cleanupVerifyInstalledPackages(false);
            }
        });
    }

    public boolean isAlreadyInstalled(String packageName) {
        try {
            return FinskyApp.get().getPackageManager().getPackageInfo(packageName, 0) != null;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public String getApplicationName(String packageName) {
        String str = null;
        try {
            PackageManager packageManager = FinskyApp.get().getPackageManager();
            ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, 0);
            if (appInfo != null) {
                CharSequence appLabel = packageManager.getApplicationLabel(appInfo);
                if (appLabel != null) {
                    str = appLabel.toString();
                }
            }
        } catch (NameNotFoundException e) {
        }
        return str;
    }
}
