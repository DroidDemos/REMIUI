package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.d;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.model.j;
import com.miui.yellowpage.utils.s;
import com.miui.yellowpage.widget.LoadingProgressView;

/* compiled from: RechargeOrderDetailFragment */
public class af extends cs {
    private TextView ia;
    private String mId;
    private LoadingProgressView mLoadingProgressView;
    private d mRequestLoader;
    private View mRootView;
    private TextView mb;
    private TextView mc;
    private TextView md;
    private TextView me;
    private TextView mf;
    private Button mg;
    private TextView mh;
    private j mi;
    private O mj;
    private s mk;

    public af() {
        this.mk = new cA(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recharge_order_detail, viewGroup, false);
        this.mb = (TextView) inflate.findViewById(R.id.product_name);
        this.mc = (TextView) inflate.findViewById(R.id.product_shortinfo);
        this.md = (TextView) inflate.findViewById(R.id.order_status);
        this.me = (TextView) inflate.findViewById(R.id.order_price);
        this.ia = (TextView) inflate.findViewById(R.id.order_id);
        this.mf = (TextView) inflate.findViewById(R.id.order_date);
        this.mg = (Button) inflate.findViewById(R.id.pay);
        this.mg.setOnClickListener(new cz(this));
        this.mh = (TextView) inflate.findViewById(R.id.pay_hint);
        bI();
        this.mLoadingProgressView = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.mLoadingProgressView.t(R.string.order_not_found);
        this.mRootView = inflate.findViewById(R.id.root_view);
        bH();
        return inflate;
    }

    private void bH() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mId = arguments.getString("com.miui.yellowpage.extra.ORDER_ID");
        }
    }

    private void bI() {
        CharSequence spannableStringBuilder = new SpannableStringBuilder(this.mActivity.getResources().getString(R.string.recharge_pay_hint_license));
        spannableStringBuilder.setSpan(new com.miui.yellowpage.utils.d(this.mk), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.mActivity.getResources().getColor(R.color.list_text_color_normal_light)), 0, spannableStringBuilder.length(), 33);
        this.mh.setText(spannableStringBuilder);
        this.mh.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private BaseRequest R(String str) {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getRechargeOrderInfoUrl(), 0);
        httpRequest.addParam("order_id", str);
        httpRequest.setRequestCache(false);
        return httpRequest;
    }

    private BaseRequest S(String str) {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getRechargeSecurityPayUrl(), 0);
        httpRequest.addParam("order_id", str);
        return httpRequest;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mj = new O(this);
        getLoaderManager().initLoader(0, null, this.mj);
        this.mRequestLoader = new d();
        this.mRequestLoader.a(new bt(this));
        this.mRequestLoader.a(this.mLoadingProgressView);
    }
}
