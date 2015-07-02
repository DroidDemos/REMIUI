package com.google.android.gms.checkin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.a;
import com.google.android.gms.internal.jc;
import com.google.android.gms.internal.kn;
import java.io.IOException;

public final class CheckinServiceClient {
    static String b(Context context, a aVar) throws IOException {
        try {
            String hN = jc.a.aO(aVar.hO()).hN();
            try {
                context.unbindService(aVar);
            } catch (Throwable e) {
                Log.i("CheckinServiceClient", "unbind failed: ", e);
            }
            return hN;
        } catch (Throwable e2) {
            Log.i("CheckinServiceClient", "GMS remote exception: ", e2);
            throw new IOException("Remote exception.");
        } catch (InterruptedException e3) {
            throw new IOException("Interrupted exception.");
        } catch (Throwable th) {
            try {
                context.unbindService(aVar);
            } catch (Throwable e4) {
                Log.i("CheckinServiceClient", "unbind failed: ", e4);
            }
        }
    }

    public static String getDeviceDataVersionInfo(Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        kn.be("Calling this from your main thread can lead to deadlock.");
        return b(context, h(context));
    }

    static a h(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            try {
                GooglePlayServicesUtil.B(context);
                Object aVar = new a();
                Intent intent = new Intent("com.google.android.gms.checkin.BIND_TO_SERVICE");
                intent.setPackage("com.google.android.gms");
                if (context.bindService(intent, aVar, 1)) {
                    return aVar;
                }
                throw new IOException("Connection failure.");
            } catch (Throwable e) {
                throw new IOException(e);
            }
        } catch (NameNotFoundException e2) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
    }
}
