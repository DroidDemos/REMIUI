package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Environment;

@fd
public class bq {
    private final Context mContext;

    public bq(Context context) {
        kn.b((Object) context, (Object) "Context can not be null");
        this.mContext = context;
    }

    public static boolean bs() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public boolean a(Intent intent) {
        kn.b((Object) intent, (Object) "Intent can not be null");
        return !this.mContext.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
    }

    public boolean bq() {
        return bs() && this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public boolean bt() {
        return VERSION.SDK_INT >= 14 && a(new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event"));
    }
}
