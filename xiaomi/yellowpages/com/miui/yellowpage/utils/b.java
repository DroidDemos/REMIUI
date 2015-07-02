package com.miui.yellowpage.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

/* compiled from: WebUploadHandler */
class b implements Runnable {
    final /* synthetic */ Uri fw;
    final /* synthetic */ H fx;

    b(H h, Uri uri) {
        this.fx = h;
        this.fw = uri;
    }

    public void run() {
        Activity a = this.fx.getActivity();
        if (a != null) {
            Cursor query = a.getContentResolver().query(this.fw, new String[]{"_data"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToNext()) {
                        a.runOnUiThread(new a(this, "file://" + query.getString(0)));
                    }
                    query.close();
                } catch (Throwable th) {
                    query.close();
                }
            }
        }
    }
}
