package com.miui.yellowpage.b;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.ui.ExpressAddressListFragment.ActionType;
import com.miui.yellowpage.ui.ExpressAddressListItem;
import com.miui.yellowpage.ui.bM;
import com.miui.yellowpage.ui.cW;

/* compiled from: ExpressAddressAdapter */
public class t extends a {
    private cW Lw;
    private bM Lx;
    private ExpressAddressListItem Me;
    private ExpressAddress bI;
    private ActionType bJ;

    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (ExpressAddress) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (ExpressAddress) obj, viewGroup);
    }

    public void a(cW cWVar) {
        this.Lw = cWVar;
    }

    public void a(bM bMVar) {
        this.Lx = bMVar;
    }

    public void i(ExpressAddress expressAddress) {
        this.bI = expressAddress;
    }

    public void a(ActionType actionType) {
        this.bJ = actionType;
    }

    public t(Context context) {
        super(context);
    }

    public View a(Context context, ExpressAddress expressAddress, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.express_address_list_item, viewGroup, false);
    }

    public void a(View view, int i, ExpressAddress expressAddress) {
        ExpressAddressListItem expressAddressListItem = (ExpressAddressListItem) view;
        expressAddressListItem.f(i, getCount());
        expressAddressListItem.h(expressAddress);
        expressAddressListItem.a(this.bJ);
        expressAddressListItem.a(this.Lx);
        expressAddressListItem.a(this.Lw);
        expressAddressListItem.setOnClickListener(new h(this, expressAddressListItem));
        if (this.bI == null || expressAddress.getId() == null || !TextUtils.equals(this.bI.getId(), expressAddress.getId())) {
            expressAddressListItem.F(false);
            return;
        }
        this.Me = expressAddressListItem;
        this.Me.F(true);
    }
}
