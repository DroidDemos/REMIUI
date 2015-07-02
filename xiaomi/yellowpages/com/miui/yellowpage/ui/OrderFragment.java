package com.miui.yellowpage.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.b.l;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.model.o;
import com.miui.yellowpage.widget.LoadingProgressView;
import com.miui.yellowpage.widget.a;

public class OrderFragment extends cs implements OnItemClickListener {
    private LoadingProgressView hh;
    private String mMerchantId;
    private ListView zB;
    private l zC;
    private bl zD;
    private Filter zE;
    private String zF;

    enum Filter {
        ALL,
        MERCHANT,
        TYPE
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.order_fragment, viewGroup, false);
        this.zB = (ListView) inflate.findViewById(16908298);
        this.zC = new l(this.mActivity);
        this.zB.setAdapter(this.zC);
        this.zB.setOnItemClickListener(this);
        this.zB.setOnScrollListener(new a(new ao(this)));
        this.hh = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.hh.t(R.string.order_empty_hint);
        this.zE = Filter.ALL;
        gw();
        return inflate;
    }

    private void gw() {
        Uri uri = (Uri) getArguments().getParcelable("com.miui.yellowpage.extra.DATA");
        if (uri != null && TextUtils.equals(uri.getHost(), "order")) {
            CharSequence queryParameter = uri.getQueryParameter("view");
            CharSequence string = this.mActivity.getString(R.string.order_merchant_title);
            CharSequence charSequence;
            if (TextUtils.equals("merchant", queryParameter)) {
                this.zE = Filter.MERCHANT;
                this.mMerchantId = Uri.decode(uri.getQueryParameter("id"));
                if (TextUtils.isEmpty(this.mMerchantId)) {
                    this.mActivity.finish();
                    return;
                }
                String string2;
                if (TextUtils.isEmpty(Uri.decode(uri.getQueryParameter(MiniDefine.l)))) {
                    charSequence = string;
                } else {
                    string2 = this.mActivity.getString(R.string.order_merchant_title_format, new Object[]{string, charSequence});
                }
                string = string2;
            } else if (TextUtils.equals(MiniDefine.m, queryParameter)) {
                this.zE = Filter.TYPE;
                this.zF = uri.getQueryParameter("id");
                if (TextUtils.isEmpty(this.zF)) {
                    this.mActivity.finish();
                    return;
                }
                if (!TextUtils.isEmpty(uri.getQueryParameter(MiniDefine.l))) {
                    string = this.mActivity.getString(R.string.order_merchant_title_format, new Object[]{string, charSequence});
                }
            }
            this.mActivity.setTitle(string);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.zD = new bl();
        getLoaderManager().initLoader(1, null, this.zD);
    }

    private BaseRequest gx() {
        HttpRequest httpRequest = null;
        if (this.zE == Filter.ALL) {
            httpRequest = new HttpRequest(this.mActivity, HostManager.getAllOrdersUrl(), 1);
        } else if (this.zE == Filter.MERCHANT) {
            httpRequest = new HttpRequest(this.mActivity, HostManager.getMerchantOrdersUrl(), 1);
            httpRequest.addParam("clientid", this.mMerchantId);
        } else if (this.zE == Filter.TYPE) {
            httpRequest = new HttpRequest(this.mActivity, HostManager.getTypeOrdersUrl(), 1);
            httpRequest.addParam("orderType", this.zF);
        }
        httpRequest.setRequireLogin(true);
        return httpRequest;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        o oVar = (o) this.zC.getItem(i);
        if (oVar != null) {
            Intent intent;
            Intent intent2 = new Intent();
            if (oVar.gd()) {
                intent2 = new Intent("com.miui.yellowpage.action.ORDER_DETAIL");
                intent2.putExtra("com.miui.yellowpage.extra.ORDER_ID", oVar.gb());
                intent = intent2;
            } else if (oVar.ge()) {
                intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse("yellowpage://express_order?id=" + oVar.gb()));
                intent = intent2;
            } else {
                if (!TextUtils.isEmpty(oVar.gi())) {
                    if (HostManager.isInternalUrl(oVar.gi())) {
                        intent2.setAction("com.miui.yellowppage.action.LOAD_WEBVIEW");
                    } else {
                        intent2.setAction("com.miui.yellowpage.action.LOAD_OPEN_WEBVIEW");
                    }
                    intent2.putExtra("web_url", oVar.gi());
                    intent2.putExtra("web_title", oVar.gf());
                }
                intent = intent2;
            }
            startActivity(intent);
        }
    }
}
