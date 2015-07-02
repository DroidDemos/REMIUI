package com.miui.yellowpage.ui;

import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.request.BaseResult;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import org.json.JSONObject;

/* compiled from: SendExpressResultFragment */
class x implements c {
    final /* synthetic */ dn ho;

    private x(dn dnVar) {
        this.ho = dnVar;
    }

    public void onPreRequest(int i) {
        this.ho.Ig.setImageResource(R.drawable.order_waiting);
        this.ho.Ii.setText(R.string.express_order_ongoing);
        this.ho.Ih.setText(null);
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        bN bNVar = (bN) baseResult;
        if (bNVar.zV) {
            this.ho.Ig.setImageResource(R.drawable.order_success);
            this.ho.Ie.setVisibility(0);
            this.ho.Ii.setText(R.string.express_order_succeeded);
            this.ho.Ih.setText(this.ho.JP.getResources().getString(R.string.express_order_success_hint, new Object[]{this.ho.cP.as().ee()}));
            if (this.ho.Ik != null) {
                this.ho.Ik.onSuccess();
                return;
            }
            return;
        }
        this.ho.Ig.setImageResource(R.drawable.order_error);
        switch (bB.xn[bNVar.getState().ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                this.ho.Ih.setText(R.string.network_unavaliable);
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                this.ho.Ih.setText(R.string.data_error);
                break;
            case WindowData.d /*3*/:
                this.ho.Ih.setText(R.string.service_unavailiable);
                break;
            default:
                this.ho.Ih.setText(bNVar.description);
                break;
        }
        this.ho.Ii.setText(R.string.express_order_failed);
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        bN bNVar = (bN) baseResult;
        if (obj != null) {
            JSONObject jSONObject = new JSONObject((String) obj);
            bNVar.zV = jSONObject.optBoolean(GlobalDefine.g);
            if (bNVar.zV) {
                this.ho.mInternalId = jSONObject.optString("internalId");
            } else {
                bNVar.description = jSONObject.optString("description");
            }
        }
        return bNVar;
    }
}
