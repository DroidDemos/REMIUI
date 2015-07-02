package com.miui.yellowpage.sync.a.b;

import android.content.Context;
import com.miui.yellowpage.base.utils.YellowPageHandler;
import com.miui.yellowpage.sync.action.OrdinaryDataUpdateAction;
import com.miui.yellowpage.sync.action.UpdateAction;
import com.miui.yellowpage.utils.u;

/* compiled from: OrdinaryYellowPage */
public class d extends a {
    public void a(Context context, UpdateAction updateAction) {
        long id = ((OrdinaryDataUpdateAction) updateAction).getId();
        if (YellowPageHandler.getYellowPage(context, id) == null) {
            u.g(context, id);
        } else {
            YellowPageHandler.deleteYellowPage(context, id);
        }
    }
}
