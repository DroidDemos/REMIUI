package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.c;
import com.miui.yellowpage.ui.RechargeOrderListItem;
import com.miui.yellowpage.ui.RechargeOrderListPendingItem;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

/* compiled from: RechargeListAdapter */
public class p extends a {
    private OnClickListener hN;

    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (c) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (c) obj, viewGroup);
    }

    public p(Context context) {
        super(context);
    }

    public View a(Context context, c cVar, ViewGroup viewGroup) {
        switch (cVar.af()) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                return LayoutInflater.from(context).inflate(R.layout.recharge_order_list_item, viewGroup, false);
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return LayoutInflater.from(context).inflate(R.layout.recharge_order_list_pending_item, viewGroup, false);
            default:
                return null;
        }
    }

    public void a(View view, int i, c cVar) {
        if (view instanceof RechargeOrderListPendingItem) {
            ((RechargeOrderListPendingItem) view).a(cVar, this.hN);
        } else if (view instanceof RechargeOrderListItem) {
            ((RechargeOrderListItem) view).a(cVar);
        }
    }

    public int getItemViewType(int i) {
        return ((c) this.mData.get(i)).af();
    }

    public int getViewTypeCount() {
        return 2;
    }

    public void a(OnClickListener onClickListener) {
        this.hN = onClickListener;
    }
}
