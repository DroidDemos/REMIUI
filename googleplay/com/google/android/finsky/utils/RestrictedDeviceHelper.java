package com.google.android.finsky.utils;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.nfcprovision.ISchoolOwnedService.Stub;

public class RestrictedDeviceHelper {
    private static Boolean sIsEduDevice;
    private static Boolean sIsSchoolOwnedEduDevice;

    public interface OnCompleteListener {
        void onComplete(boolean z);
    }

    private static class SchoolOwnershipServiceConnection implements ServiceConnection {
        private final Context mContext;
        private final OnCompleteListener mListener;

        private SchoolOwnershipServiceConnection(Context context, OnCompleteListener listener) {
            this.mContext = context;
            this.mListener = listener;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            synchronized (RestrictedDeviceHelper.class) {
                try {
                    RestrictedDeviceHelper.sIsSchoolOwnedEduDevice = Boolean.valueOf(Stub.asInterface(service).isSchoolOwned());
                    this.mContext.unbindService(this);
                    this.mListener.onComplete(RestrictedDeviceHelper.sIsSchoolOwnedEduDevice.booleanValue());
                } catch (RemoteException e) {
                    FinskyLog.wtf("Error calling school-ownership service; assume not school-owned", new Object[0]);
                    RestrictedDeviceHelper.sIsSchoolOwnedEduDevice = Boolean.valueOf(false);
                    this.mContext.unbindService(this);
                    this.mListener.onComplete(RestrictedDeviceHelper.sIsSchoolOwnedEduDevice.booleanValue());
                } catch (Throwable th) {
                    this.mContext.unbindService(this);
                    this.mListener.onComplete(RestrictedDeviceHelper.sIsSchoolOwnedEduDevice.booleanValue());
                }
            }
        }

        public void onServiceDisconnected(ComponentName name) {
        }
    }

    static {
        sIsEduDevice = null;
        sIsSchoolOwnedEduDevice = null;
    }

    public static synchronized void isLimitedOrSchoolEduUser(OnCompleteListener listener) {
        synchronized (RestrictedDeviceHelper.class) {
            if (sIsSchoolOwnedEduDevice == null) {
                if (FinskyApp.get().getUsers().isLimitedUser() || ((Boolean) G.debugLimitedUserState.get()).booleanValue()) {
                    sIsSchoolOwnedEduDevice = Boolean.valueOf(true);
                } else if (VERSION.SDK_INT < 18) {
                    sIsSchoolOwnedEduDevice = Boolean.valueOf(false);
                } else if (isEduDevice()) {
                    isSchoolOwnedEduDevice(listener);
                } else {
                    sIsSchoolOwnedEduDevice = Boolean.valueOf(false);
                }
            }
            listener.onComplete(sIsSchoolOwnedEduDevice.booleanValue());
        }
    }

    private static synchronized boolean isEduDevice() {
        boolean booleanValue;
        synchronized (RestrictedDeviceHelper.class) {
            if (sIsEduDevice == null) {
                try {
                    sIsEduDevice = Boolean.valueOf(((DevicePolicyManager) FinskyApp.get().getSystemService("device_policy")).isDeviceOwnerApp((String) G.eduDevicePolicyApp.get()));
                } catch (NoSuchMethodError e) {
                    FinskyLog.wtf(e, "No method named isDeviceOwnerApp exists.", new Object[0]);
                    sIsEduDevice = Boolean.valueOf(false);
                } catch (RuntimeException e2) {
                    FinskyLog.wtf(e2, "Unable to invoke isDeviceOwnerApp", new Object[0]);
                    sIsEduDevice = Boolean.valueOf(false);
                }
            }
            booleanValue = sIsEduDevice.booleanValue();
        }
        return booleanValue;
    }

    private static synchronized void isSchoolOwnedEduDevice(OnCompleteListener listener) {
        synchronized (RestrictedDeviceHelper.class) {
            Context context = FinskyApp.get();
            ServiceConnection conn = new SchoolOwnershipServiceConnection(context, listener);
            Intent intent = new Intent("com.google.android.nfcprovision.IOwnedService.BIND");
            intent.setComponent(new ComponentName("com.google.android.nfcprovision", "com.google.android.nfcprovision.SchoolOwnedService"));
            if (!context.bindService(intent, conn, 1)) {
                sIsSchoolOwnedEduDevice = Boolean.valueOf(false);
                listener.onComplete(false);
            }
        }
    }
}
