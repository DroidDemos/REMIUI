package com.miui.yellowpage.b;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.w;
import com.miui.yellowpage.ui.YellowPagePickerListItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

/* compiled from: YellowPagePickerAdapter */
public class q extends BaseAdapter implements SectionIndexer {
    private ArrayList DZ;
    private ArrayList Ea;
    private ArrayList Eb;
    private ArrayList Ec;
    private String Ed;
    private String Ee;
    private String[] Ef;
    private int[] Eg;
    private SparseArray Eh;
    private int iN;
    private Context mContext;
    private List mList;

    public /* bridge */ /* synthetic */ Object getItem(int i) {
        return N(i);
    }

    public q(Context context, Bundle bundle) {
        this.mContext = context;
        h(bundle);
        hj();
        hi();
        hh();
    }

    private void hh() {
        ArrayList arrayList = null;
        int i = 0;
        if (this.iN != 0) {
            ArrayList arrayList2;
            if (this.iN == 1) {
                arrayList2 = this.DZ;
                arrayList = this.Eb;
            } else if (this.iN == 2) {
                arrayList2 = this.Ea;
                arrayList = this.Ec;
            } else {
                arrayList2 = null;
            }
            SortedSet treeSet = new TreeSet();
            List arrayList3 = new ArrayList();
            if (arrayList2 != null) {
                for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                    arrayList3.add("!");
                    treeSet.add("!");
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String toUpperCase = ((String) it.next()).substring(0, 1).toUpperCase(Locale.US);
                arrayList3.add(toUpperCase);
                treeSet.add(toUpperCase);
            }
            this.Ef = (String[]) treeSet.toArray(new String[0]);
            this.Eg = new int[this.Ef.length];
            while (i < this.Ef.length) {
                this.Eg[i] = arrayList3.indexOf(this.Ef[i]);
                i++;
            }
        }
    }

    private void hi() {
        this.Eh = new SparseArray();
        if (this.DZ != null && this.DZ.size() > 0) {
            this.Eh.put(0, this.Ee);
            this.Eh.put(this.DZ.size(), this.Ed);
        }
    }

    private void hj() {
        this.mList = w.a(this.Eb, this.Ec, this.DZ, this.Ea);
    }

    private void h(Bundle bundle) {
        this.DZ = bundle.getStringArrayList("picker_recommend_presentation");
        this.Ea = bundle.getStringArrayList("picker_recommend_backend_data");
        this.Eb = bundle.getStringArrayList("picker_presentation");
        this.Ec = bundle.getStringArrayList("picker_backend_data");
        this.Ed = bundle.getString("picker_recommend_presentation_text", ConfigConstant.WIRELESS_FILENAME);
        this.Ee = bundle.getString("picker_recommend_section_text", ConfigConstant.WIRELESS_FILENAME);
        this.iN = bundle.getInt("picker_index_target");
    }

    public int getCount() {
        return this.mList.size();
    }

    public w N(int i) {
        return (w) this.mList.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = (YellowPagePickerListItem) ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.yellow_page_picker_item, viewGroup, false);
        } else {
            YellowPagePickerListItem yellowPagePickerListItem = (YellowPagePickerListItem) view;
        }
        w N = N(i);
        view.a(N.in(), N.getData(), this.iN, (String) this.Eh.get(i));
        return view;
    }

    public Object[] getSections() {
        return this.Ef;
    }

    public int getPositionForSection(int i) {
        return this.Eg[i];
    }

    public int getSectionForPosition(int i) {
        int i2 = 0;
        int i3 = 1;
        while (i3 < this.Eg.length && this.Eg[i3] <= i) {
            i2++;
            i3++;
        }
        return i2;
    }
}
