package com.miui.yellowpage.b;

import android.widget.Filter;
import android.widget.Filter.FilterResults;
import com.miui.yellowpage.model.v;
import java.util.ArrayList;

/* compiled from: RechargeLocalHistoryListAdapter */
class j extends Filter {
    final /* synthetic */ o uI;

    j(o oVar) {
        this.uI = oVar;
    }

    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults filterResults = new FilterResults();
        filterResults.values = this.uI.getData();
        filterResults.count = this.uI.getCount();
        return filterResults;
    }

    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        this.uI.updateData((ArrayList) filterResults.values);
    }

    public CharSequence convertResultToString(Object obj) {
        return ((v) obj).getPhone();
    }
}
