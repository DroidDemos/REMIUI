package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.a;
import com.google.android.gms.internal.kn;
import com.google.android.gms.internal.s;
import java.io.IOException;

public final class GoogleAuthUtil {
    private static final ComponentName Gk;
    private static final ComponentName Gl;
    private static final Intent Gm;
    private static final Intent Gn;
    public static final String KEY_ANDROID_PACKAGE_NAME;
    public static final String KEY_CALLER_UID;

    static {
        KEY_CALLER_UID = VERSION.SDK_INT >= 11 ? "callerUid" : "callerUid";
        KEY_ANDROID_PACKAGE_NAME = VERSION.SDK_INT >= 14 ? "androidPackageName" : "androidPackageName";
        Gk = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
        Gl = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
        Gm = new Intent().setPackage("com.google.android.gms").setComponent(Gk);
        Gn = new Intent().setPackage("com.google.android.gms").setComponent(Gl);
    }

    private static void B(Context context) throws GoogleAuthException {
        try {
            GooglePlayServicesUtil.B(context);
        } catch (GooglePlayServicesRepairableException e) {
            throw new GooglePlayServicesAvailabilityException(e.getConnectionStatusCode(), e.getMessage(), e.getIntent());
        } catch (GooglePlayServicesNotAvailableException e2) {
            throw new GoogleAuthException(e2.getMessage());
        }
    }

    public static RecoveryDecision getRecoveryDetails(Context ctx, String email, String displayMessage, boolean isMessageBroadUse) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        Context applicationContext = ctx.getApplicationContext();
        kn.be("Calling this from your main thread can lead to deadlock");
        B(applicationContext);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ANDROID_PACKAGE_NAME, ctx.getPackageName());
        ServiceConnection aVar = new a();
        if (ctx.bindService(Gn, aVar, 1)) {
            try {
                RecoveryDecision a = s.a.b(aVar.hO()).a(email, displayMessage, isMessageBroadUse, bundle);
                ctx.unbindService(aVar);
                return a;
            } catch (Throwable e) {
                Log.i("GoogleAuthUtil", "GMS remote exception ", e);
                throw new IOException("remote exception");
            } catch (InterruptedException e2) {
                throw new GoogleAuthException("Interrupted");
            } catch (Throwable th) {
                ctx.unbindService(aVar);
            }
        } else {
            throw new IOException("Could not bind to service: " + Gn.getComponent());
        }
    }

    public static PendingIntent getRecoveryIfSuggested(Context ctx, String email, String displayMessage, boolean isMessageBroadUse) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        RecoveryDecision recoveryDetails = getRecoveryDetails(ctx, email, displayMessage, isMessageBroadUse);
        return (recoveryDetails.showRecoveryInterstitial && recoveryDetails.isRecoveryInterstitialAllowed) ? recoveryDetails.recoveryIntent : null;
    }
}
