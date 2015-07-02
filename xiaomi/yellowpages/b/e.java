package b;

import com.alipay.sdk.cons.MiniDefine;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

class e extends TimerTask {
    final /* synthetic */ a en;
    final /* synthetic */ b eo;

    e(b bVar, a aVar) {
        this.eo = bVar;
        this.en = aVar;
    }

    public void run() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("toastCallBack", MiniDefine.F);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a aVar = new a("callback");
        aVar.a(this.en.b());
        aVar.a(jSONObject);
        this.eo.em.a(aVar);
    }
}
