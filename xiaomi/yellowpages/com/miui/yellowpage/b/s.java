package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.ui.FavoriteYellowPageListItem;

/* compiled from: FavoriteYellowPageAdapter */
public class s extends a {
    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (YellowPage) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (YellowPage) obj, viewGroup);
    }

    public s(Context context) {
        super(context);
    }

    public View a(Context context, YellowPage yellowPage, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.favorite_yellow_page_list_item, viewGroup, false);
    }

    public void a(View view, int i, YellowPage yellowPage) {
        ((FavoriteYellowPageListItem) view).a(yellowPage);
    }
}
