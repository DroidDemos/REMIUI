package com.miui.yellowpage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.b.q;
import com.miui.yellowpage.model.w;
import java.util.Locale;
import miui.widget.AlphabetFastIndexer;

/* compiled from: YellowPagePickerFragment */
public class dv extends cs {
    protected q JA;
    private AlphabetFastIndexer JB;
    protected String Jz;
    protected ListView bK;
    protected int iN;
    protected View mRoot;

    protected void a(View view, Bundle bundle) {
        c(view, bundle);
        b(view, bundle);
        o(view);
        k(view);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mRoot = layoutInflater.inflate(R.layout.yellow_page_picker_fragment, viewGroup, false);
        a(this.mRoot, getArguments());
        return this.mRoot;
    }

    protected void o(View view) {
        this.bK = (ListView) view.findViewById(R.id.list);
    }

    protected w N(int i) {
        return this.JA.N(i);
    }

    protected void b(View view, Bundle bundle) {
        this.JA = new q(getActivity(), bundle);
    }

    protected void c(View view, Bundle bundle) {
        this.iN = bundle.getInt("picker_index_target");
    }

    protected void k(View view) {
        this.bK.setAdapter(this.JA);
        this.bK.setOnItemClickListener(fZ());
        this.JB = (AlphabetFastIndexer) view.findViewById(R.id.fast_indexer);
        if (this.iN != 0) {
            this.JB.attatch(this.bK);
            this.JB.setVisibility(0);
        } else {
            this.JB.setVisibility(8);
        }
        this.bK.setOnScrollListener(this.JB.decorateScrollListener(new br(this)));
    }

    private String a(YellowPagePickerListItem yellowPagePickerListItem) {
        if (TextUtils.isEmpty(yellowPagePickerListItem.getContent())) {
            return null;
        }
        return yellowPagePickerListItem.getContent().substring(0, 1).toUpperCase(Locale.US);
    }

    protected OnItemClickListener fZ() {
        return new bq(this);
    }
}
