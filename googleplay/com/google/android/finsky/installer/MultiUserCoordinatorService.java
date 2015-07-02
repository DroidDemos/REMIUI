package com.google.android.finsky.installer;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import com.google.android.finsky.installer.IMultiUserCoordinatorService.Stub;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Sets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class MultiUserCoordinatorService extends Service {
    private boolean DEBUG_FORCE_BUSY_WITH_DELAY;
    private final Stub mBinder;
    private String mDebugPackageJustReleased;
    private HashMap<UserHandle, IMultiUserCoordinatorServiceListener> mListeners;
    private HashMap<String, UserHandle> mPackageOwners;

    public MultiUserCoordinatorService() {
        this.DEBUG_FORCE_BUSY_WITH_DELAY = false;
        this.mDebugPackageJustReleased = null;
        this.mPackageOwners = Maps.newHashMap();
        this.mListeners = Maps.newHashMap();
        this.mBinder = new Stub() {
            public void registerListener(IMultiUserCoordinatorServiceListener listener) {
                UserHandle callerHandle = Binder.getCallingUserHandle();
                synchronized (MultiUserCoordinatorService.this.mListeners) {
                    if (listener != null) {
                        MultiUserCoordinatorService.this.mListeners.put(callerHandle, listener);
                    } else {
                        MultiUserCoordinatorService.this.mListeners.remove(callerHandle);
                    }
                }
            }

            public boolean acquirePackage(final String packageName) {
                if (MultiUserCoordinatorService.this.DEBUG_FORCE_BUSY_WITH_DELAY) {
                    if (packageName.equals(MultiUserCoordinatorService.this.mDebugPackageJustReleased)) {
                        MultiUserCoordinatorService.this.mDebugPackageJustReleased = null;
                    } else {
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public void run() {
                                MultiUserCoordinatorService.this.mDebugPackageJustReleased = packageName;
                                AnonymousClass1.this.notifyReleased(packageName);
                            }
                        }, 15000);
                        return false;
                    }
                }
                UserHandle callerHandle = Binder.getCallingUserHandle();
                synchronized (MultiUserCoordinatorService.this.mPackageOwners) {
                    UserHandle owner = (UserHandle) MultiUserCoordinatorService.this.mPackageOwners.get(packageName);
                    if (owner == null || owner.equals(callerHandle)) {
                        MultiUserCoordinatorService.this.mPackageOwners.put(packageName, callerHandle);
                        FinskyLog.d("User=%s requested=%s granted=true", callerHandle, packageName);
                        notifyTaken(packageName);
                        return true;
                    }
                    FinskyLog.d("User=%s requested=%s granted=false owned by=%s", callerHandle, packageName, owner);
                    return false;
                }
            }

            public void releasePackage(String packageName) {
                UserHandle callerHandle = Binder.getCallingUserHandle();
                boolean notifyReleased = true;
                synchronized (MultiUserCoordinatorService.this.mPackageOwners) {
                    UserHandle owner = (UserHandle) MultiUserCoordinatorService.this.mPackageOwners.get(packageName);
                    if (owner == null) {
                        FinskyLog.w("User=%s released=%s *** was not previously acquired", callerHandle, packageName);
                    } else if (owner.equals(callerHandle)) {
                        MultiUserCoordinatorService.this.mPackageOwners.remove(packageName);
                        FinskyLog.d("User=%s released=%s", callerHandle, packageName);
                    } else {
                        FinskyLog.w("User=%s released=%s *** owned by=%s", callerHandle, packageName, owner);
                        notifyReleased = false;
                    }
                }
                if (notifyReleased) {
                    notifyReleased(packageName);
                }
            }

            public void releaseAllPackages() {
                UserHandle callerHandle = Binder.getCallingUserHandle();
                Set<String> releasedPackages = Sets.newHashSet();
                synchronized (MultiUserCoordinatorService.this.mPackageOwners) {
                    if (MultiUserCoordinatorService.this.mPackageOwners.isEmpty()) {
                        return;
                    }
                    Iterator<Entry<String, UserHandle>> iterator = MultiUserCoordinatorService.this.mPackageOwners.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Entry<String, UserHandle> entry = (Entry) iterator.next();
                        if (((UserHandle) entry.getValue()).equals(callerHandle)) {
                            FinskyLog.w("User=%s removed=%s", callerHandle, (String) entry.getKey());
                            iterator.remove();
                            releasedPackages.add(packageName);
                        }
                    }
                    for (String packageName : releasedPackages) {
                        notifyReleased(packageName);
                    }
                }
            }

            private void notifyTaken(String packageName) {
                synchronized (MultiUserCoordinatorService.this.mListeners) {
                    for (Entry<UserHandle, IMultiUserCoordinatorServiceListener> entry : MultiUserCoordinatorService.this.mListeners.entrySet()) {
                        try {
                            ((IMultiUserCoordinatorServiceListener) entry.getValue()).packageAcquired(packageName);
                        } catch (RemoteException e) {
                            FinskyLog.d("Could not notify listener for user %s", entry.getKey());
                        }
                    }
                }
            }

            private void notifyReleased(String packageName) {
                synchronized (MultiUserCoordinatorService.this.mListeners) {
                    for (Entry<UserHandle, IMultiUserCoordinatorServiceListener> entry : MultiUserCoordinatorService.this.mListeners.entrySet()) {
                        try {
                            ((IMultiUserCoordinatorServiceListener) entry.getValue()).packageReleased(packageName);
                        } catch (RemoteException e) {
                            FinskyLog.d("Could not notify listener for user %s", entry.getKey());
                        }
                    }
                }
            }
        };
    }

    public static Intent createBindIntent(Context context) {
        Intent i = new Intent();
        i.setComponent(new ComponentName(context, MultiUserCoordinatorService.class));
        return i;
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
