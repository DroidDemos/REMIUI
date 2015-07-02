package com.a.a;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.security.InvalidParameterException;

/* compiled from: Mipay */
public class a {
    private final Handler if;
    private boolean ig;
    private final Context mContext;

    private a(Context context) {
        this.mContext = context.getApplicationContext();
        this.if = new Handler(this.mContext.getMainLooper());
        this.ig = u(context);
    }

    public static a s(Context context) {
        return new a(context);
    }

    public static boolean t(Context context) {
        if (aH()) {
            return false;
        }
        if (u(context) || v(context)) {
            return true;
        }
        return false;
    }

    private static boolean aH() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return ((int) ((((float) Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels)) / displayMetrics.density) + 0.5f)) >= 600;
    }

    private static boolean u(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.mipay.wallet", 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private static boolean v(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.mipay.counter", 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public void a(Fragment fragment, String str, Bundle bundle) {
        if (fragment == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (TextUtils.isEmpty(str)) {
            throw new InvalidParameterException("order cannot be empty");
        } else {
            Intent intent = new Intent("com.xiaomi.action.MIPAY_PAY_ORDER");
            intent.setPackage("com.mipay.wallet");
            intent.putExtra("order", str);
            intent.putExtra("extra", bundle);
            fragment.startActivityForResult(intent, 20140424);
        }
    }
}
