package com.google.android.finsky.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Binder;
import android.util.Base64;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.play.utils.config.GservicesValue;
import java.util.List;

public class SignatureUtils {
    private static List<Signature> sOtherSignatures;

    public static boolean isCalledByFirstPartyPackage(Activity activity) {
        String callingPackage = activity.getCallingPackage();
        if (callingPackage == null) {
            FinskyLog.d("Couldn't determine caller, did you use startActivityForResult?", new Object[0]);
            return false;
        } else if (callingPackage.equals(activity.getPackageName())) {
            return true;
        } else {
            return isFirstPartyPackage(activity, callingPackage);
        }
    }

    public static boolean containsFirstPartyPackage(Context context, String[] packageNames) {
        for (String packageName : packageNames) {
            if (isFirstPartyPackage(context, packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFirstPartyPackage(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        if (pm.checkSignatures(packageName, context.getPackageName()) == 0) {
            return true;
        }
        faultInOtherSignatures(context);
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, 64);
            if (packageName.equals("com.quickoffice.android")) {
                Signature quickofficeSignature = makeSignature("MIICVzCCAcCgAwIBAgIESVJ9+TANBgkqhkiG9w0BAQUFADBvMQswCQYDVQQGEwJVUzELMAkGA1UECBMCVFgxDzANBgNVBAcTBkRhbGxhczEUMBIGA1UEChMLUXVpY2tvZmZpY2UxDzANBgNVBAsTBlFPLURFVjEbMBkGA1UEAxMSQWxleGFuZGVyIFN0ZXBhbm92MCAXDTA4MTIyNDE4MjI0OVoYDzIwNjMwOTI3MTgyMjQ5WjBvMQswCQYDVQQGEwJVUzELMAkGA1UECBMCVFgxDzANBgNVBAcTBkRhbGxhczEUMBIGA1UEChMLUXVpY2tvZmZpY2UxDzANBgNVBAsTBlFPLURFVjEbMBkGA1UEAxMSQWxleGFuZGVyIFN0ZXBhbm92MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChx/LvhUKzh2bdxAYelxtLR+EU7hWRFw/GmJHgTSvhAVKReWUZolS2bOk0xaybV499hHEAGutl2mX90KJ4EIZh3LZNk3qjy3terL8YDqoayWI+EZtSAifFEBbn3bPdhX6pqI/1v/SivKf/LQrw5VASStE2dylHtRAnLLJGbvOWeQIDAQABMA0GCSqGSIb3DQEBBQUAA4GBABU7YooXGdyniqWBK44g0XjN9NLTKTpXec856AVBQWv+PnqAvGS282KChu2tMYyJHRok0jxvTlYwgXZIa31Sw57372zJ6hDqgnBQdBdF7FnKDhyZZ+XPZHGEr+TXe/3jZwl5tZo7WmoWMAkWnMqda/CQ7TVgr+8gacujXGyhUsWv");
                for (Signature packageSignature : packageInfo.signatures) {
                    if (packageSignature.equals(quickofficeSignature)) {
                        return true;
                    }
                }
            }
            for (Signature packageSignature2 : packageInfo.signatures) {
                for (int i = 0; i < sOtherSignatures.size(); i++) {
                    if (packageSignature2.equals(sOtherSignatures.get(i))) {
                        return true;
                    }
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            FinskyLog.w("Couldn't get info for package %s", packageName);
            return false;
        }
    }

    private static synchronized void faultInOtherSignatures(Context context) {
        synchronized (SignatureUtils.class) {
            if (sOtherSignatures == null) {
                sOtherSignatures = Lists.newArrayList();
                sOtherSignatures.add(makeSignature("MIICUjCCAbsCBEk0mH4wDQYJKoZIhvcNAQEEBQAwcDELMAkGA1UEBhMCVVMxCzAJBgNVBAgTAkNBMRYwFAYDVQQHEw1Nb3VudGFpbiBWaWV3MRQwEgYDVQQKEwtHb29nbGUsIEluYzEUMBIGA1UECxMLR29vZ2xlLCBJbmMxEDAOBgNVBAMTB1Vua25vd24wHhcNMDgxMjAyMDIwNzU4WhcNMzYwNDE5MDIwNzU4WjBwMQswCQYDVQQGEwJVUzELMAkGA1UECBMCQ0ExFjAUBgNVBAcTDU1vdW50YWluIFZpZXcxFDASBgNVBAoTC0dvb2dsZSwgSW5jMRQwEgYDVQQLEwtHb29nbGUsIEluYzEQMA4GA1UEAxMHVW5rbm93bjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAn0gDGZD5sUcmOE4EU9GPjAu/jcd7JQSksSB8TGxEurwArcZhD6a2qy2oDjPy7vFrJqP2uFua+sqQn/u+s/TJT36BIqeY4OunXO090in6c2X0FRZBWqnBYX3Vg84Zuuigu9iF/BeptL0mQIBRIarbk3fetAATOBQYiC7FIoL8WA0CAwEAATANBgkqhkiG9w0BAQQFAAOBgQBAhmae1jHaQ4Td0GHSJuBzuYzEuZ34teS+njy+l1Aeg98cb6lZwM5gXE/SrG0chM7eIEdsurGb6PIgOv93F61lLY/MiQcI0SFtqERXWSZJ4OnTxLtM9Y2hnbHU/EG8uVhPZOZfQQ0FKf1baIOMFB0Km9HbEZHLKg33kOoMsS2zpA=="));
                if (isFinskyWithDebugKeys(context)) {
                    FinskyLog.d("Apps signed by first-party test keys will be allowed", new Object[0]);
                    sOtherSignatures.add(makeSignature("MIIEqDCCA5CgAwIBAgIJAIR+T/LWtd6OMA0GCSqGSIb3DQEBBQUAMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbTAeFw0xMDAxMjAwMTAxMzVaFw0zNzA2MDcwMTAxMzVaMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbTCCASAwDQYJKoZIhvcNAQEBBQADggENADCCAQgCggEBANgocXw20RcP1E0Kew8HESboW7/fM7A0YABalMz7ZaXboLJD32Cxkb+dBt8dilwKM+LRY/UT3x0iU0HqPDN5IuhcAuw0ztlMuAcjpiP/S6/7tOXv5nc7PqK+uLyyAmfP54VRH4Mu+YerdZT+HinPvE0IOh8SUgB3c5byFltpewCjoME6zDCKk/IhY8FunD1KshSfNkxFwEMUMnA58doJYJPxs/wYtlYQlcYiX8cQK5h8bxOkXSTj4MVOhZ1n41tnCCcT0tbwV900V9GfxP6N3eyMOk8/lyMFGacKKDY0rDWBo0q9oX2EWgoJhfv4BgsDaid4YIFj+gw3uefyoQ52vHcCAQOjgfwwgfkwHQYDVR0OBBYEFLXH+RJveA06+8plc3M/9SJrmxc3MIHJBgNVHSMEgcEwgb6AFLXH+RJveA06+8plc3M/9SJrmxc3oYGapIGXMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbYIJAIR+T/LWtd6OMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADggEBAEw+p2V9Jua71xEMjxnfH42hCX0zhg9p3r/K20ajfoflsw+7NHscdVW8uzyZVBSARpZfnHkqAtDb5aZHYbN5R6tr/7C6xqLBoM34Yvh3qWcN/W8GLkBuzhgGDGBJjfw2nycRcZjlb8uhUuYFjc6UzlkfxPSpmCszutgZbXdvVbfQGs8x3dcM7LeJeHYGZRD5SaVSSjExs8tlQc+LNUIOvMRSJVmWP0JmaQVyZmJPs5jP21IXiB0RHG4DRhb4USEY0KKmnRPXkvDNEdvVjiODWlSlSsJR59IsRGo/7hQSEOlER0tAYwe7JoQrT2vTVYIcc5ZR/6JgWwXiJJXXFdh6kfY="));
                } else {
                    FinskyLog.d("Will not allow first-party apps signed by test keys", new Object[0]);
                }
            }
        }
    }

    private static boolean isFinskyWithDebugKeys(Context context) {
        try {
            Signature[] finskySignatures = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            Signature finskyDebugSignature = makeSignature("MIIEqDCCA5CgAwIBAgIJANWFuGx90071MA0GCSqGSIb3DQEBBAUAMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbTAeFw0wODA0MTUyMzM2NTZaFw0zNTA5MDEyMzM2NTZaMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbTCCASAwDQYJKoZIhvcNAQEBBQADggENADCCAQgCggEBANbOLggKv+IxTdGNs8/TGFy0PTP6DHThvbbR24kT9ixcOd9W+EaBPWW+wPPKQmsHxajtWjmQwWfna8mZuSeJS48LIgAZlKkpFeVyxW0qMBujb8X8ETrWy550NaFtI6t9+u7hZeTfHwqNvacKhp1RbE6dBRGWynwMVX8XW8N1+UjFaq6GCJukT4qmpN2afb8sCjUigq0GuMwYXrFVee74bQgLHWGJwPmvmLHC69EH6kWr22ijx4OKXlSIx2xT1AsSHee70w5iDBiK4aph27yH3TxkXy9V89TDdexAcKk/cVHYNnDBapcavl7y0RiQ4biu8ymM8Ga/nmzhRKya6G0cGw8CAQOjgfwwgfkwHQYDVR0OBBYEFI0cxb6VTEM8YYY6FbBMvAPyT+CyMIHJBgNVHSMEgcEwgb6AFI0cxb6VTEM8YYY6FbBMvAPyT+CyoYGapIGXMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbYIJANWFuGx90071MAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEEBQADggEBABnTDPEF+3iSP0wNfdIjIz1AlnrPzgAIHVvXxunW7SBrDhEglQZBbKJEk5kT0mtKoOD1JMrSu1xuTKEBahWRbqHsXclaXjoBADb0kkjVEJu/Lh5hgYZnOjvlba8Ld7HCKePCVePoTJBdI4fvugnL8TsgK05aIskyY0hKI9L8KfqfGTl1lzOv2KoWD0KWwtAWPoGChZxmQ+nBli+gwYMzM1vAkP+aayLe0a1EQimlOalO762r0GXO0ks+UeXde2Z4e+8S/pf7pITEI/tP+MxJTALw9QUWEv9lKTk+jkbqxbsh8nfBUapfKqYn0eidpwq2AzVp3juYl7//fKnaPhJD9gs=");
            for (Signature signature : finskySignatures) {
                if (finskyDebugSignature.equals(signature)) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            FinskyLog.wtf(e, "Unable to look up our own PackageInfo", new Object[0]);
            return false;
        }
    }

    private static Signature makeSignature(String str) {
        return new Signature(Base64.decode(str, 0));
    }

    public static String getCallingAppIfAuthorized(Context context, String docId, GservicesValue<Boolean> guard, FinskyEventLog eventLog, int eventType) {
        int callingUid = Binder.getCallingUid();
        PackageManager pm = context.getPackageManager();
        String[] callingPackages = pm.getPackagesForUid(callingUid);
        if (callingPackages == null || callingPackages.length == 0) {
            FinskyLog.e("getPackagesForUid %d returned 0 packages", Integer.valueOf(callingUid));
            logEvent(eventLog, eventType, docId, "no-packages-for-uid", null, null);
            return null;
        }
        String callerName;
        if (callingPackages.length == 1) {
            callerName = callingPackages[0];
        } else {
            String nameForUid = pm.getNameForUid(callingUid);
            if (nameForUid == null) {
                FinskyLog.e("getNameForUid %d returned null", Integer.valueOf(callingUid));
                logEvent(eventLog, eventType, docId, "no-name-for-uid", null, null);
                return null;
            }
            callerName = "sharedUserId=" + nameForUid;
        }
        if ((guard != null && ((Boolean) guard.get()).booleanValue()) || containsFirstPartyPackage(context, callingPackages)) {
            return callerName;
        }
        FinskyLog.d("Unable to authorize API access for %s", callerName);
        logEvent(eventLog, eventType, docId, "auth-rejection", callerName, null);
        return null;
    }

    public static void logEvent(FinskyEventLog eventLog, int eventType, String document, String reason, String callingPackage, String exceptionType) {
        eventLog.logBackgroundEvent(new BackgroundEventBuilder(eventType).setDocument(document).setReason(reason).setOperationSuccess(reason == null).setCallingPackage(callingPackage).setExceptionType(exceptionType).build());
    }
}
