package com.miui.yellowpage.business.recharge;

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
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.widget.LoadingProgressView;
import com.miui.yellowpage.widget.a;

/* compiled from: RechargeOrderListFragment */
public class N extends cs {
    private p Kt;
    private f Ku;
    private G Kv;
    private OnScrollListener Kw;
    private ListView bK;
    private LoadingProgressView mLoadingProgressView;
    private OnClickListener mOnClickListener;
    private d mRequestLoader;

    public N() {
        this.mOnClickListener = new A(this);
        this.Kw = new a(new B(this));
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
        this.Ku = new f(this);
        getLoaderManager().initLoader(1, null, this.Ku);
        this.mRequestLoader = new d();
        this.Kv = new G(this);
        this.mRequestLoader.a(this.Kv);
        this.mRequestLoader.a(this.mLoadingProgressView);
    }

    private BaseRequest cy() {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getMiPayRechargeOrderListUrl(), 1);
        httpRequest.setRequireLogin(true);
        return httpRequest;
    }

    private BaseRequest S(String str) {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getMiPayRechargeSecurityPayUrl(), 2);
        httpRequest.addParam("order_id", str);
        httpRequest.setRequireLogin(true);
        return httpRequest;
    }
}
