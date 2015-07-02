package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.ExpressCompany;
import com.miui.yellowpage.ui.ExpressCompanyListItem;

/* compiled from: ExpressCompanyAdapter */
public class i extends a {
    private static int ut;

    public i(Context context) {
        super(context);
        if (ut == 0) {
            ut = (int) context.getResources().getDimension(R.dimen.express_company_list_item_padding);
        }
    }

    public View newView(Context context, Object obj, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.express_company_detail_list_item, viewGroup, false);
    }

    public void bindView(View view, int i, Object obj) {
        if (i == 0) {
            view.setPadding(ut, ut, ut, ut);
        } else {
            view.setPadding(ut, 0, ut, ut);
        }
        ((ExpressCompanyListItem) view).a((ExpressCompany) obj);
    }
}
