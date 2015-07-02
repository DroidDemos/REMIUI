package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.b.e;
import com.miui.yellowpage.widget.LoadingProgressView;

/* compiled from: RecentYellowPageFragment */
public class cm extends cs {
    private dW Du;
    private e Dv;
    private ListView bK;
    private LoadingProgressView hh;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recent_yellow_page_fragment, viewGroup, false);
        this.Dv = new e(this.mActivity);
        this.bK = (ListView) inflate.findViewById(16908298);
        this.bK.setAdapter(this.Dv);
        this.bK.setOnItemClickListener(new ah(this));
        this.bK.setOnCreateContextMenuListener(new ag(this));
        this.hh = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.hh.t(R.string.general_no_yellow_page);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        this.mLoader.reload();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.Du = new dW();
        getLoaderManager().initLoader(0, null, this.Du);
    }
}
