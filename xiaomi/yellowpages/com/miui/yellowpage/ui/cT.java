package com.miui.yellowpage.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.yellowpage.R;
import com.miui.yellowpage.b.r;
import com.miui.yellowpage.base.model.YellowPageDataEntry;
import com.miui.yellowpage.base.model.YellowPageDataEntry.Type;
import com.miui.yellowpage.model.YellowPagePhonesEntry;

/* compiled from: YellowPagePhonesFragment */
class cT extends r {
    final /* synthetic */ ap Dy;

    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (YellowPageDataEntry) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (YellowPageDataEntry) obj, viewGroup);
    }

    public View a(Context context, YellowPageDataEntry yellowPageDataEntry, ViewGroup viewGroup) {
        if (yellowPageDataEntry.getItemType() == Type.DETAIL) {
            return LayoutInflater.from(context).inflate(R.layout.yellow_page_phones_list_item, viewGroup, false);
        }
        return super.a(context, yellowPageDataEntry, viewGroup);
    }

    public void a(View view, int i, YellowPageDataEntry yellowPageDataEntry) {
        if (Type.values()[getItemViewType(i)] == Type.DETAIL) {
            ((YellowPagePhonesListItem) view).a((YellowPagePhonesEntry) yellowPageDataEntry);
        } else {
            super.a(view, i, yellowPageDataEntry);
        }
    }

    private cT(ap apVar, Context context) {
        this.Dy = apVar;
        super(context, apVar);
    }
}
