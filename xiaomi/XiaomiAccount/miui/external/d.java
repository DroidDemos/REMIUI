package miui.external;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;

class d {
    public static final String MIUI_SYSTEM_APK_NAME = "miui";

    private d() {
    }

    public static boolean isMiuiSystem() {
        return b(MIUI_SYSTEM_APK_NAME) != null;
    }

    public static String getApkPath(Context context, String str, String str2) {
        if (context == null) {
            return a(str, str2);
        }
        PackageInfo a = a(context, str);
        if (a != null) {
            return a.applicationInfo.publicSourceDir;
        }
        return null;
    }

    private static String a(String str, String str2) {
        String a = a(str);
        if (a == null) {
            return b(str2);
        }
        return a;
    }

    private static String a(String str) {
        return a(new String[]{"/data/app/" + str + "-1.apk", "/data/app/" + str + "-2.apk", "/data/app/" + str + "-1/base.apk", "/data/app/" + str + "-2/base.apk"});
    }

    private static String b(String str) {
        return a(new String[]{"/system/app/" + str + ".apk", "/system/priv-app/" + str + ".apk", "/system/app/" + str + "/" + str + ".apk", "/system/priv-app/" + str + "/" + str + ".apk"});
    }

    private static String a(String[] strArr) {
        for (String str : strArr) {
            if (new File(str).exists()) {
                return str;
            }
        }
        return null;
    }

    public static String getLibPath(Context context, String str) {
        if (context == null) {
            return c(str);
        }
        PackageInfo a = a(context, str);
        if (a != null) {
            return a.applicationInfo.nativeLibraryDir;
        }
        return null;
    }

    private static String c(String str) {
        return "/data/data/" + str + "/lib/";
    }

    private static PackageInfo a(Context context, String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }
}
