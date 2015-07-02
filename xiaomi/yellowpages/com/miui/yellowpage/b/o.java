package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.v;
import com.miui.yellowpage.ui.RechargeLocalHistoryListItem;
import com.miui.yellowpage.ui.c;

/* compiled from: RechargeLocalHistoryListAdapter */
public class o extends a implements Filterable {
    private c gz;

    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (v) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (v) obj, viewGroup);
    }

    public o(Context context) {
        super(context);
    }

    public View a(Context context, v vVar, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.recharge_local_history_list_item, viewGroup, false);
    }

    public void a(View view, int i, v vVar) {
        RechargeLocalHistoryListItem rechargeLocalHistoryListItem = (RechargeLocalHistoryListItem) view;
        if (this.gz != null) {
            rechargeLocalHistoryListItem.a(this.gz);
        }
        rechargeLocalHistoryListItem.b(vVar);
    }

    public void a(c cVar) {
        this.gz = cVar;
    }

    public Filter getFilter() {
        return new j(this);
    }
}
