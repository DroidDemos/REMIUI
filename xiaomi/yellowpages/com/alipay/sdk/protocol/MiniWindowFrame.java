package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.data.Request;
import com.alipay.sdk.data.Response;
import org.json.JSONObject;

public class MiniWindowFrame extends WindowData {
    private int m;
    private boolean n;

    protected MiniWindowFrame(Request request, Response response) {
        super(request, response);
        this.n = false;
    }

    public boolean f() {
        return this.m == 4 || this.m == 9;
    }

    public int g() {
        return this.m;
    }

    public String h() {
        return null;
    }

    public boolean i() {
        return this.n;
    }

    public void a(JSONObject jSONObject) {
        int i = 0;
        super.a(jSONObject);
        if (jSONObject.has(MiniDefine.d)) {
            JSONObject optJSONObject = jSONObject.optJSONObject(MiniDefine.d);
            CharSequence optString = optJSONObject.optString(MiniDefine.m);
            b(Boolean.parseBoolean(optJSONObject.optString("oneTime")));
            if (TextUtils.equals("page", optString)) {
                this.n = true;
                this.m = 9;
            } else if (TextUtils.equals("dialog", optString)) {
                this.m = 7;
                this.n = false;
            } else if (TextUtils.equals("toast", optString)) {
                ElementAction a = ElementAction.a(optJSONObject, MiniDefine.g);
                this.m = 6;
                if (a != null) {
                    ActionType[] a2 = ActionType.a(a);
                    int length = a2.length;
                    while (i < length) {
                        ActionType actionType = a2[i];
                        if (actionType == ActionType.Confirm || actionType == ActionType.Alert) {
                            this.m = 10;
                        }
                        i++;
                    }
                }
            } else if (!TextUtils.equals(MiniDefine.s, optString)) {
                this.n = TextUtils.equals(optString, MiniDefine.V);
                this.m = 4;
            }
        } else if (MiniStatus.a(jSONObject.optString(MiniDefine.b)) == MiniStatus.POP_TYPE) {
            this.m = -10;
        } else {
            this.m = 8;
        }
    }
}
