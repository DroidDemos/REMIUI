package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.a;
import com.miui.yellowpage.ui.ExpressInquiryProgressListItem;

/* compiled from: ExpressProgressAdapter */
public class g extends a {
    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (a) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (a) obj, viewGroup);
    }

    public g(Context context) {
        super(context);
    }

    public View a(Context context, a aVar, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.express_progress_list_item, viewGroup, false);
    }

    public void a(View view, int i, a aVar) {
        ((ExpressInquiryProgressListItem) view).a(aVar);
    }
}
