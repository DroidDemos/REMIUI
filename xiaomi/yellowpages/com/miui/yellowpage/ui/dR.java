package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.d;
import com.miui.yellowpage.b.p;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.widget.LoadingProgressView;
import com.miui.yellowpage.widget.a;

/* compiled from: RechargeOrderListFragment */
public class dR extends cs {
    private p Kt;
    private OnScrollListener Kw;
    private ad Le;
    private cF Lf;
    private ListView bK;
    private LoadingProgressView mLoadingProgressView;
    private OnClickListener mOnClickListener;
    private d mRequestLoader;

    public dR() {
        this.mOnClickListener = new dZ(this);
        this.Kw = new a(new dY(this));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recharge_history_fragment, viewGroup, false);
        this.bK = (ListView) inflate.findViewById(R.id.recharge_list);
        this.bK.setOnScrollListener(this.Kw);
        this.mLoadingProgressView = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.mLoadingProgressView.t(R.string.recharge_history_empty);
        this.Kt = new p(this.mActivity);
        this.Kt.a(this.mOnClickListener);
        this.bK.setAdapter(this.Kt);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.Le = new ad(this);
        getLoaderManager().initLoader(1, null, this.Le);
        this.mRequestLoader = new d();
        this.Lf = new cF(this);
        this.mRequestLoader.a(this.Lf);
        this.mRequestLoader.a(this.mLoadingProgressView);
    }

    private BaseRequest cy() {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getRechargeOrderListUrl(), 1);
        httpRequest.setRequireLogin(true);
        return httpRequest;
    }

    private BaseRequest S(String str) {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getRechargeSecurityPayUrl(), 2);
        httpRequest.addParam("order_id", str);
        httpRequest.setRequireLogin(true);
        return httpRequest;
    }
}
