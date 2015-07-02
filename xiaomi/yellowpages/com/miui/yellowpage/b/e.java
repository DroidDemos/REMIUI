package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.ui.RecentYellowPageListItem;

/* compiled from: RecentYellowPageAdapter */
public class e extends a {
    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (YellowPage) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (YellowPage) obj, viewGroup);
    }

    public e(Context context) {
        super(context);
    }

    public View a(Context context, YellowPage yellowPage, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.recent_yellow_page_list_item, viewGroup, false);
    }

    public void a(View view, int i, YellowPage yellowPage) {
        ((RecentYellowPageListItem) view).a(yellowPage);
    }
}
