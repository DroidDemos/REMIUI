package com.miui.yellowpage.model;

import android.text.TextUtils;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import org.json.JSONObject;

/* compiled from: RechargePayInfo */
public class i {
    private String oV;
    private String oW;

    public static i e(JSONObject jSONObject) {
        i iVar = new i();
        String string = jSONObject.getString("security");
        int i = jSONObject.getInt("security_source");
        iVar.oV = string;
        switch (i) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                iVar.oW = "mipay";
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                iVar.oW = "alipay";
                break;
            default:
                iVar.oW = null;
                break;
        }
        return iVar;
    }

    public String cB() {
        return this.oV;
    }

    public String cC() {
        return this.oW;
    }

    public boolean isValid() {
        return (TextUtils.isEmpty(this.oV) || TextUtils.isEmpty(this.oW)) ? false : true;
    }
}
