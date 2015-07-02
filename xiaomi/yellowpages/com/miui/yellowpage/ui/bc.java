package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.b.s;
import com.miui.yellowpage.widget.LoadingProgressView;

/* compiled from: FavoriteYellowPageFragment */
public class bc extends cs {
    private ListView bK;
    private LoadingProgressView hh;
    private dS uB;
    private s uC;

    public void onResume() {
        super.onResume();
        this.mLoader.reload();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.favorite_yellow_page_fragment, viewGroup, false);
        this.uC = new s(this.mActivity);
        this.bK = (ListView) inflate.findViewById(16908298);
        this.bK.setAdapter(this.uC);
        this.bK.setOnItemClickListener(new co(this));
        this.bK.setOnCreateContextMenuListener(new cn(this));
        this.hh = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.hh.t(R.string.general_no_yellow_page);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.uB = new dS();
        getLoaderManager().initLoader(0, null, this.uB);
    }
}
