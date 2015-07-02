package com.miui.yellowpage.sync.a.b;

import android.content.Context;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.sync.a.a.d;
import com.miui.yellowpage.sync.action.UpdateAction;
import com.miui.yellowpage.sync.action.UpdateAction.Type;

/* compiled from: PullTaskDaemon */
public class b extends a {
    public void a(Context context, UpdateAction updateAction) {
        for (Type type : Type.values()) {
            if (d.class.isAssignableFrom(type.aD())) {
                Log.d("PullTaskDaemon", type.aD() + " seems to be a pull task");
                try {
                    d dVar = (d) type.aD().newInstance();
                    long D = dVar.D(context);
                    Log.d("PullTaskDaemon", "local version is " + D);
                    dVar.a(context, null, D);
                    Log.d("PullTaskDaemon", "all is well");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d("PullTaskDaemon", "mission complete");
    }
}
