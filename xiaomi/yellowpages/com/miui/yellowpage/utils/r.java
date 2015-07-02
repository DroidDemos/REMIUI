package com.miui.yellowpage.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.miui.yellowpage.base.utils.Log;

/* compiled from: Package */
public class r {
    public static boolean q(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (context.getPackageManager().getPackageInfo(str, 0) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            Log.d("Package", "package not found:" + str);
            return false;
        }
    }
}
