package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.o;
import com.miui.yellowpage.ui.OrderListItem;

/* compiled from: OrderListAdapter */
public class l extends a {
    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (o) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (o) obj, viewGroup);
    }

    public l(Context context) {
        super(context);
    }

    public View a(Context context, o oVar, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.order_list_item, viewGroup, false);
    }

    public void a(View view, int i, o oVar) {
        ((OrderListItem) view).a(oVar);
    }
}
