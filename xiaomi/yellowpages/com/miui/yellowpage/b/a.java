package com.miui.yellowpage.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* compiled from: BaseDataAdapter */
public abstract class a extends BaseAdapter {
    private static final String TAG = "BaseDataAdapter";
    protected Context mContext;
    protected ArrayList mData;
    protected boolean mDataValid;

    public abstract void bindView(View view, int i, Object obj);

    public abstract View newView(Context context, Object obj, ViewGroup viewGroup);

    public a(Context context) {
        this.mContext = context;
        this.mDataValid = false;
    }

    public void updateData(ArrayList arrayList) {
        if (arrayList != null) {
            this.mDataValid = true;
            this.mData = arrayList;
            notifyDataSetChanged();
            return;
        }
        this.mDataValid = false;
        notifyDataSetInvalidated();
    }

    public ArrayList getData() {
        ArrayList arrayList = new ArrayList();
        if (this.mData != null) {
            arrayList.addAll(this.mData);
        }
        return arrayList;
    }

    public int getCount() {
        if (!this.mDataValid || this.mData == null) {
            return 0;
        }
        return this.mData.size();
    }

    public Object getItem(int i) {
        if (!this.mDataValid || this.mData == null) {
            return null;
        }
        return this.mData.get(i);
    }

    public long getItemId(int i) {
        if (!this.mDataValid || this.mData == null) {
            return 0;
        }
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the data is valid");
        } else if (i < 0 || i >= this.mData.size()) {
            throw new IllegalStateException("couldn't get view at this position " + i);
        } else {
            Object obj = this.mData.get(i);
            if (view == null) {
                view = newView(this.mContext, obj, viewGroup);
            }
            bindView(view, i, obj);
            return view;
        }
    }
}
