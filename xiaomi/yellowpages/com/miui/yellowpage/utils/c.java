package com.miui.yellowpage.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import com.miui.yellowpage.base.utils.ThreadPool;

/* compiled from: Payment */
public class c {
    public static boolean r(Context context) {
        return true;
    }

    public static boolean a(Activity activity, Handler handler, String str) {
        ThreadPool.execute(new A(activity, str, handler));
        return true;
    }
}
