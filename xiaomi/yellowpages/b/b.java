package b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import b.a.a;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private Context b;
    private d em;

    public b(Context context, d dVar) {
        this.b = context;
        this.em = dVar;
    }

    public void a(String str) {
        Object string;
        Exception e;
        try {
            JSONObject jSONObject = new JSONObject(str);
            string = jSONObject.getString("clientId");
            try {
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("param");
                    if (jSONObject2 instanceof JSONObject) {
                        jSONObject2 = jSONObject2;
                    } else {
                        jSONObject2 = null;
                    }
                    String string2 = jSONObject.getString("func");
                    String string3 = jSONObject.getString("bundleName");
                    a aVar = new a("call");
                    aVar.b(string3);
                    aVar.c(string2);
                    aVar.a(jSONObject2);
                    aVar.a((String) string);
                    a(aVar);
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                if (!TextUtils.isEmpty(string)) {
                    try {
                        a(string, a.RUNTIME_ERROR, true);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        } catch (Exception e4) {
            e = e4;
            string = null;
            e.printStackTrace();
            if (!TextUtils.isEmpty(string)) {
                a(string, a.RUNTIME_ERROR, true);
            }
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            if (TextUtils.isEmpty(aVar.d())) {
                a(aVar.b(), a.INVALID_PARAMETER, true);
            } else {
                a(new c(this, aVar));
            }
        }
    }

    private void a(String str, a aVar, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ConfigConstant.LOG_JSON_STR_ERROR, aVar.ordinal());
            a aVar2 = new a("callback");
            aVar2.a(jSONObject);
            aVar2.a(str);
            if (z) {
                this.em.a(aVar2);
            } else {
                a(aVar2);
            }
        }
    }

    private static void a(Runnable runnable) {
        if (runnable != null) {
            if ((Looper.getMainLooper() == Looper.myLooper() ? 1 : null) != null) {
                runnable.run();
            } else {
                new Handler(Looper.getMainLooper()).post(runnable);
            }
        }
    }

    private a b(a aVar) {
        if (aVar != null && "toast".equals(aVar.d())) {
            c(aVar);
        }
        return a.NONE_ERROR;
    }

    private void c(a aVar) {
        JSONObject ai = aVar.ai();
        CharSequence optString = ai.optString(MiniDefine.at);
        int optInt = ai.optInt("duration");
        int i = 1;
        if (optInt < 2500) {
            i = 0;
        }
        Toast.makeText(this.b, optString, i).show();
        new Timer().schedule(new e(this, aVar), (long) i);
    }
}
