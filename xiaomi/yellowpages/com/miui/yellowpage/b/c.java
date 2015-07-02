package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPageModuleEntry;
import com.miui.yellowpage.ui.YellowPageGridModuleItem;
import java.util.ArrayList;

/* compiled from: ServiceAdapter */
public class c extends BaseAdapter {
    private Context mContext;
    private int mCount;
    private boolean mx;
    private boolean my;
    private ArrayList mz;

    public c(Context context, boolean z) {
        this.mContext = context;
        this.my = z;
    }

    public int getCount() {
        return this.mCount;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public Object getItem(int i) {
        return this.mz.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Object item = getItem(i);
        if (view == null) {
            view = (YellowPageGridModuleItem) LayoutInflater.from(this.mContext).inflate(R.layout.yellow_page_grid_module_item, viewGroup, false);
        } else {
            YellowPageGridModuleItem yellowPageGridModuleItem = (YellowPageGridModuleItem) view;
        }
        if (u(i)) {
            view.bc();
        } else {
            view.b((YellowPageModuleEntry) item);
        }
        return view;
    }

    public void updateData(ArrayList arrayList) {
        if (arrayList.size() <= 4 || !this.my) {
            this.mx = false;
            this.mCount = arrayList.size();
        } else {
            this.mx = true;
            this.mCount = 4;
        }
        this.mz = arrayList;
        notifyDataSetChanged();
    }

    public boolean u(int i) {
        return this.mx && i >= 3 && this.my;
    }
}
