package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.alipay.sdk.cons.GlobalDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.d;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.model.ExpressOrder;
import com.miui.yellowpage.model.ExpressOrder.Cargo;
import java.io.Serializable;

/* compiled from: SendExpressResultFragment */
public class dn extends dF {
    private Button Ie;
    private Button If;
    private ImageView Ig;
    private TextView Ih;
    private TextView Ii;
    private x Ij;
    private j Ik;
    private am Il;
    private ExpressOrder cP;
    private String mInternalId;

    public void a(j jVar) {
        this.Ik = jVar;
    }

    public void a(am amVar) {
        this.Il = amVar;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.send_express_result_fragment, viewGroup, false);
        this.Ie = (Button) inflate.findViewById(R.id.view_order);
        this.If = (Button) inflate.findViewById(R.id.go_back);
        this.Ig = (ImageView) inflate.findViewById(R.id.result_view);
        this.Ih = (TextView) inflate.findViewById(R.id.result_detail);
        this.Ii = (TextView) inflate.findViewById(R.id.result_description);
        this.If.setOnClickListener(new bA(this));
        this.Ie.setOnClickListener(new bz(this));
        Serializable serializable = getArguments().getSerializable("extra_express_order");
        if (serializable != null) {
            this.cP = (ExpressOrder) serializable;
        }
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.Ij = new x();
        this.mRequestLoader = new d();
        this.mRequestLoader.a(this.Ij);
        this.mRequestLoader.a(hS(), new bN());
    }

    public void onResume() {
        super.onResume();
        this.JP.setTitle((int) R.string.express_order_result);
        this.JP.k(false);
    }

    private BaseRequest hS() {
        HttpRequest httpRequest = new HttpRequest(this.JP, HostManager.getExpressSendUrl(), 0);
        httpRequest.setRequestMethod("POST");
        httpRequest.setRequireLogin(true);
        httpRequest.setConnectTimeout(30000);
        httpRequest.setReadTimeout(30000);
        Cargo as = this.cP.as();
        httpRequest.addParam("companyCode", as.ed());
        httpRequest.addParam("description", as.getName());
        httpRequest.addParam(GlobalDefine.h, as.iT());
        ExpressAddress au = this.cP.au();
        if (au != null) {
            httpRequest.addParam("senderName", au.getName());
            httpRequest.addParam("senderPhone", au.getPhone());
            httpRequest.addParam("senderAdminArea", au.getAdminArea());
            httpRequest.addParam("senderSubAdminArea", au.getSubAdminArea());
            httpRequest.addParam("senderLocality", au.getLocality());
            httpRequest.addParam("senderSubLocality", au.getSubLocality());
            httpRequest.addParam("senderAddress", au.eQ());
            httpRequest.addParam("senderPostalCode", au.getPostalCode());
        }
        au = this.cP.at();
        if (au != null) {
            httpRequest.addParam("receiverName", au.getName());
            httpRequest.addParam("receiverPhone", au.getPhone());
            httpRequest.addParam("receiverAdminArea", au.getAdminArea());
            httpRequest.addParam("senderSubAdminArea", au.getSubAdminArea());
            httpRequest.addParam("receiverLocality", au.getLocality());
            httpRequest.addParam("receiverSubLocality", au.getSubLocality());
            httpRequest.addParam("receiverAddress", au.eQ());
            httpRequest.addParam("receiverPostalCode", au.getPostalCode());
        }
        return httpRequest;
    }
}
