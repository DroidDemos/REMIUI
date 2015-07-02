package com.miui.yellowpage.sync.a.c;

import android.content.Context;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.sync.a.a;
import com.miui.yellowpage.sync.action.UpdateAction;
import com.miui.yellowpage.utils.u;
import java.util.LinkedList;
import java.util.List;

/* compiled from: PushTask */
public class c extends a {
    public void a(Context context, UpdateAction updateAction) {
        E(context);
        u.x(context.getApplicationContext());
    }

    protected void E(Context context) {
        Log.d("PushTask", "ugc task");
        List<Class> linkedList = new LinkedList();
        linkedList.add(b.class);
        linkedList.add(d.class);
        linkedList.add(a.class);
        for (Class newInstance : linkedList) {
            ((c) newInstance.newInstance()).E(context);
        }
    }
}
