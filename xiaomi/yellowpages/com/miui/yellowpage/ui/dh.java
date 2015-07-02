package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.b.i;
import com.miui.yellowpage.widget.LoadingProgressView;

/* compiled from: ExpressCompanyListFragment */
public class dh extends i {
    private static int HR;
    private static int HS;
    private i HT;
    private X HU;
    private LoadingProgressView mLoadingProgressView;
    private View mRoot;

    static {
        HR = 0;
        HS = 0;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.express_company_list_fragment, viewGroup, false);
        this.mRoot = inflate.findViewById(R.id.root);
        this.mLoadingProgressView = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        ListView listView = (ListView) inflate.findViewById(16908298);
        listView.setEmptyView(inflate.findViewById(16908292));
        this.HT = new i(this.JP);
        listView.setAdapter(this.HT);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.HU = new X(this);
        getLoaderManager().initLoader(HR, null, this.HU);
    }

    protected void a(dU dUVar) {
        if (dUVar.hasData()) {
            this.HT.updateData(dUVar.list);
            this.mRoot.setVisibility(0);
            return;
        }
        this.mRoot.setVisibility(8);
    }

    protected g I() {
        return this.mLoadingProgressView;
    }

    protected int J() {
        return HR;
    }

    protected int K() {
        return HS;
    }

    protected X L() {
        return this.HU;
    }
}
