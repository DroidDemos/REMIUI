package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.x;
import com.miui.yellowpage.ui.CallLogListItem;

/* compiled from: CallLogListAdapter */
public class f extends a {
    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (x) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (x) obj, viewGroup);
    }

    public f(Context context) {
        super(context);
    }

    public View a(Context context, x xVar, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.call_log_list_item, viewGroup, false);
    }

    public void a(View view, int i, x xVar) {
        if (view instanceof CallLogListItem) {
            ((CallLogListItem) view).b(xVar);
        }
    }

    public boolean E(int i) {
        if (i < 0 || i >= this.mData.size()) {
            return false;
        }
        this.mData.remove(i);
        notifyDataSetChanged();
        return true;
    }
}
