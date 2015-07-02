package com.miui.yellowpage.sync.a.b;

import android.content.Context;
import android.database.Cursor;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.sync.action.UpdateAction;
import com.miui.yellowpage.utils.o;
import com.miui.yellowpage.utils.u;
import miui.yellowpage.YellowPageContract.YellowPage;

/* compiled from: Subscriber */
public class c extends a {
    public void a(Context context, UpdateAction updateAction) {
        Log.d("Subscriber", "update");
        Cursor query = context.getContentResolver().query(YellowPage.CONTENT_URI_ALL, new String[]{"yid", "subscribe_stats"}, "subscribe_stats<> ? AND type=?", new String[]{String.valueOf(1), String.valueOf(3)}, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    long j = query.getLong(0);
                    long j2 = query.getLong(1);
                    Log.d("Subscriber", "yid:" + j + ", stats:" + j2);
                    if (j2 == 2) {
                        if (o.f(context, j)) {
                            Log.d("Subscriber", j + " has corresponding call logs, try to subscribe");
                            u.h(context, j);
                        } else {
                            Log.d("Subscriber", j + " has no corresponding call logs, try later");
                        }
                    } else if (j2 == 3) {
                        Log.d("Subscriber", "try to unsubscribe sid: " + j);
                        u.g(context, j);
                    }
                } finally {
                    query.close();
                }
            }
        }
    }
}
