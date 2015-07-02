package com.alipay.sdk.util;

import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.encrypt.Rsa;
import com.alipay.sdk.encrypt.TriDes;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONObject;

public class JsonUtils {
    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3 = new JSONObject();
        try {
            for (JSONObject jSONObject4 : new JSONObject[]{jSONObject, jSONObject2}) {
                if (jSONObject4 != null) {
                    Iterator keys = jSONObject4.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        jSONObject3.put(str, jSONObject4.get(str));
                    }
                }
            }
        } catch (Object e) {
            LogUtils.a(e);
        }
        return jSONObject3;
    }

    public static String a(String str) {
        return str.replaceAll(MiniDefine.a, "values");
    }

    public static String a(String str, String str2) {
        String a = Rsa.a(str, GlobalConstants.c);
        String a2 = TriDes.a(str, str2);
        return String.format(Locale.getDefault(), "%08X%s%08X%s", new Object[]{Integer.valueOf(a.length()), a, Integer.valueOf(a2.length()), a2});
    }
}
