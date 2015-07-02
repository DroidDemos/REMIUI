package com.miui.yellowpage.ui;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.d;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.model.ExpressOrder;
import com.miui.yellowpage.model.ExpressOrder.Cargo;
import com.miui.yellowpage.model.ExpressOrder.State;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.widget.ExpressOrderDetailView;
import com.miui.yellowpage.widget.LoadingProgressView;
import java.io.Serializable;
import miui.telephony.PhoneNumberUtils;

public class ExpressOrderItemFragment extends cs {
    private View cD;
    private TextView cE;
    private TextView cF;
    private TextView cG;
    private View cH;
    private TextView cI;
    private ExpressOrderDetailView cJ;
    private Button cK;
    private Button cL;
    private Button cM;
    private Button cN;
    private State cO;
    private ExpressOrder cP;
    private boolean cQ;
    private bZ cR;
    private B cS;
    private String mInternalId;
    private LoadingProgressView mLoadingProgressView;
    private View mRoot;

    class EditResult extends BaseResult {
        State Ia;
        String mDescription;

        enum State {
            SUCCEEDED,
            FAILED
        }

        private EditResult() {
        }

        public boolean hasData() {
            return true;
        }

        public BaseResult shallowClone() {
            BaseResult editResult = new EditResult();
            editResult.Ia = this.Ia;
            return editResult;
        }
    }

    public void a(B b) {
        this.cS = b;
    }

    public void onResume() {
        super.onResume();
        this.mActivity.setTitle(R.string.express_order_detail);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.express_order_item_fragment, viewGroup, false);
        this.mRoot = inflate.findViewById(R.id.root);
        this.cD = inflate.findViewById(16908292);
        this.cJ = (ExpressOrderDetailView) inflate.findViewById(R.id.detail);
        this.cE = (TextView) inflate.findViewById(R.id.express_company);
        this.cG = (TextView) inflate.findViewById(R.id.state);
        this.cF = (TextView) inflate.findViewById(R.id.tracking_number);
        this.cN = (Button) inflate.findViewById(R.id.track_order);
        this.cH = inflate.findViewById(R.id.state_note_container);
        this.cI = (TextView) inflate.findViewById(R.id.state_note);
        this.mLoadingProgressView = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.cK = (Button) inflate.findViewById(R.id.cancel);
        this.cL = (Button) inflate.findViewById(R.id.view_status);
        this.cM = (Button) inflate.findViewById(R.id.contact);
        this.cM.setOnClickListener(new dp(this));
        this.cR = new bZ();
        this.mRequestLoader = new d();
        this.mRequestLoader.a(this.mLoadingProgressView);
        return inflate;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 10) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            getLoaderManager().restartLoader(0, null, this.cR);
        }
    }

    private void B() {
        if (this.cP != null) {
            Cargo as = this.cP.as();
            if (as != null) {
                Object formatNumber = PhoneNumberUtils.formatNumber(as.iN());
                new Builder(this.mActivity).setTitle(formatNumber).setNegativeButton(17039360, null).setPositiveButton(R.string.call, new do(this, formatNumber, as)).show();
            }
        }
    }

    private void a(Cargo cargo) {
        Intent intent = new Intent("com.miui.yellowppage.express_inquiry");
        intent.putExtra("internalId", cargo.E());
        intent.putExtra("bizCode", cargo.ed());
        intent.putExtra("logistics_name", cargo.ee());
        intent.putExtra("inquiry_type", "inquiry_attach");
        startActivityForResult(intent, 10);
    }

    private void C() {
        this.mRequestLoader.a(new D());
        this.mRequestLoader.a(H(), new A());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        Serializable serializable = arguments.getSerializable("order");
        if (serializable != null) {
            this.cP = (ExpressOrder) serializable;
            if (!(this.cP == null || this.cP.as() == null)) {
                this.cO = this.cP.as().iR();
            }
            D();
        }
        serializable = arguments.getSerializable("extra_state");
        if (serializable != null) {
            this.cO = (State) serializable;
        }
        this.mInternalId = arguments.getString("id");
        if (!TextUtils.isEmpty(this.mInternalId)) {
            getLoaderManager().initLoader(0, null, this.cR);
            this.mRoot.setVisibility(8);
        }
    }

    private void D() {
        this.mRoot.setVisibility(0);
        this.cJ.a(this.cP);
        Cargo as = this.cP.as();
        this.cG.setText(as.iR().gE());
        this.cE.setText(as.ee());
        if (TextUtils.isEmpty(as.iS())) {
            this.cH.setVisibility(8);
        } else {
            this.cH.setVisibility(0);
            this.cI.setText(as.iS());
        }
        if (TextUtils.isEmpty(as.iN())) {
            this.cM.setVisibility(8);
        } else {
            String string;
            if (a(as.iO(), as.iU(), as.iR())) {
                string = this.mActivity.getResources().getString(R.string.express_urge_order);
            } else {
                string = this.mActivity.getResources().getString(R.string.express_contact_prefix);
            }
            this.cM.setText(string + as.ee());
            this.cM.setVisibility(0);
        }
        boolean isEmpty = TextUtils.isEmpty(as.iP());
        if (!isEmpty) {
            this.cK.setVisibility(8);
            this.cL.setVisibility(0);
            this.cL.setOnClickListener(new dr(this, as));
        } else if (as.iR().gD()) {
            this.cK.setText(R.string.express_cancel_order);
            this.cK.setOnClickListener(new da());
            this.cK.setVisibility(0);
            this.cL.setVisibility(8);
        } else {
            this.cK.setVisibility(8);
            this.cL.setVisibility(8);
        }
        if (isEmpty && !as.iR().isFailed() && !as.iR().isFinished()) {
            this.cN.setOnClickListener(new ds(this, as));
            this.cN.setVisibility(0);
            this.cF.setVisibility(8);
        } else if (isEmpty) {
            this.cF.setText(R.string.express_no_order);
            this.cF.setVisibility(0);
            this.cN.setVisibility(8);
        } else {
            this.cF.setVisibility(0);
            this.cF.setText(as.iP());
            this.cN.setVisibility(8);
        }
    }

    private boolean a(long j, long j2, State state) {
        long abs = Math.abs(System.currentTimeMillis() - j);
        return state.gD() && !this.cQ && j2 <= 0 && abs > 1800000 && abs < ConfigConstant.MAIN_SWITCH_INTERVAL_UINT;
    }

    private String E() {
        if (!TextUtils.isEmpty(this.mInternalId)) {
            return this.mInternalId;
        }
        if (this.cP != null) {
            return this.cP.as().E();
        }
        return null;
    }

    private BaseRequest F() {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getExpressCancelUrl(), 1);
        httpRequest.setRequestMethod("POST");
        httpRequest.setRequireLogin(true);
        httpRequest.addParam("internalId", E());
        return httpRequest;
    }

    private BaseRequest G() {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getExpressItemUrl(), 0);
        httpRequest.addParam("internalId", E());
        httpRequest.setRequireLogin(true);
        return httpRequest;
    }

    private BaseRequest H() {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getExpressUrgeUrl(), 0);
        httpRequest.addParam("internalId", E());
        httpRequest.setRequestMethod("POST");
        httpRequest.setRequireLogin(true);
        return httpRequest;
    }

    public static Intent j(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("yellowpage");
        builder.authority("express_order");
        if (!TextUtils.isEmpty(str)) {
            builder.appendQueryParameter("id", str);
        }
        intent.setData(builder.build());
        return intent;
    }
}
