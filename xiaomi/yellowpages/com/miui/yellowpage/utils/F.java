package com.miui.yellowpage.utils;

import android.content.Context;
import com.miui.yellowpage.base.exception.NetworkUnavailableException;
import com.miui.yellowpage.base.exception.ServerException;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import java.io.File;
import java.net.UnknownServiceException;
import miui.util.CoderUtils;
import miui.yellowpage.YellowPageUtils;

/* compiled from: Ivr */
public class F {
    public static String hB() {
        StringBuilder stringBuilder = new StringBuilder();
        int length = "ivr_v90".length() - 1;
        while (length >= 0 && "ivr_v90".charAt(length) != 'v') {
            stringBuilder.insert(0, "ivr_v90".charAt(length));
            Log.d("Ivr", "Current version string " + stringBuilder.toString());
            length--;
        }
        return stringBuilder.toString();
    }

    public static String ab(Context context) {
        return context.getFilesDir().getAbsolutePath() + "/ivr";
    }

    private static String C(Context context, String str) {
        return ab(context) + "/" + CoderUtils.encodeSHA(YellowPageUtils.getNormalizedNumber(context, str, true, "86"));
    }

    public static String D(Context context, String str) {
        return Files.getFileString(C(context, str));
    }

    public static boolean E(Context context, String str) {
        return Files.deleteFile(new File(C(context, str)));
    }

    public static boolean f(Context context, String str, String str2) {
        return Files.saveFileString(C(context, str), str2);
    }

    public static boolean g(Context context, String str, String str2) {
        if (!new File(C(context, str)).exists()) {
            return f(context, str, str2);
        }
        if (E(context, str)) {
            return f(context, str, str2);
        }
        return false;
    }

    public static boolean F(Context context, String str) {
        return new File(C(context, str)).exists();
    }

    public static String a(Context context, String str, boolean z) {
        HttpRequest httpRequest = new HttpRequest(context, HostManager.getCallMenuNIvr(), 0);
        httpRequest.addParam("phone", YellowPageUtils.getNormalizedNumber(context, str));
        httpRequest.setRequireLocId(true);
        if (z) {
            return httpRequest.requestLocal();
        }
        try {
            if (YellowPageUtils.isYellowPageAvailable(context)) {
                return httpRequest.requestServer();
            }
            return null;
        } catch (UnknownServiceException e) {
            e.printStackTrace();
            return null;
        } catch (NetworkUnavailableException e2) {
            e2.printStackTrace();
            return null;
        } catch (ServerException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
