package com.miui.yellowpage.ui;

import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.cons.GlobalDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.request.BaseResult;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import org.json.JSONObject;

/* compiled from: ExpressOrderItemFragment */
class dE implements c {
    final /* synthetic */ ExpressOrderItemFragment hJ;

    private dE(ExpressOrderItemFragment expressOrderItemFragment) {
        this.hJ = expressOrderItemFragment;
    }

    public void onPreRequest(int i) {
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        EditResult editResult = (EditResult) baseResult;
        if (editResult.Ia == State.SUCCEEDED) {
            switch (i) {
                case TransactionXMLFile.MODE_PRIVATE /*0*/:
                    Toast.makeText(this.hJ.mActivity, R.string.express_address_deleted, 0).show();
                    this.hJ.mActivity.finish();
                    return;
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    this.hJ.getLoaderManager().restartLoader(0, null, this.hJ.cR);
                    this.hJ.mRoot.setVisibility(8);
                    if (this.hJ.cS != null) {
                        this.hJ.cS.aF();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (!TextUtils.isEmpty(editResult.mDescription)) {
            Toast.makeText(this.hJ.mActivity, editResult.mDescription, 0).show();
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        EditResult editResult = (EditResult) baseResult;
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                editResult.Ia = State.FAILED;
                if (obj != null) {
                    JSONObject jSONObject = new JSONObject((String) obj);
                    Object optString = jSONObject.optString(GlobalDefine.g);
                    optString = (TextUtils.isEmpty(optString) || !Boolean.valueOf(optString).booleanValue()) ? null : 1;
                    if (optString == null) {
                        editResult.mDescription = jSONObject.optString("description");
                        break;
                    }
                    editResult.Ia = State.SUCCEEDED;
                    break;
                }
                break;
        }
        return editResult;
    }
}
