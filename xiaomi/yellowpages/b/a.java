package b;

import org.json.JSONObject;

public class a {
    private String i;
    private String j;
    private String k;
    private String l;
    private JSONObject m;
    private boolean n;

    public enum a {
        NONE_ERROR,
        FUNCTION_NOT_FOUND,
        INVALID_PARAMETER,
        RUNTIME_ERROR,
        NONE_PERMISS
    }

    public a(String str) {
        this.n = false;
        d(str);
    }

    public String b() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public void b(String str) {
        this.j = str;
    }

    public String d() {
        return this.k;
    }

    public void c(String str) {
        this.k = str;
    }

    public void d(String str) {
        this.l = str;
    }

    public JSONObject ai() {
        return this.m;
    }

    public void a(JSONObject jSONObject) {
        this.m = jSONObject;
    }

    public String g() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("clientId", this.i);
        jSONObject.put("func", this.k);
        jSONObject.put("param", this.m);
        jSONObject.put("msgType", this.l);
        return jSONObject.toString();
    }
}
