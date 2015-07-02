package com.xiaomi.c;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class i {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    protected String h;
    private String k;
    private String m;
    private long rL;
    private ArrayList rM;
    private double rN;
    private long rO;

    public i(String str) {
        this.rM = new ArrayList();
        this.rN = 0.1d;
        this.m = "s.mi1.cc";
        this.rO = ConfigConstant.MAIN_SWITCH_INTERVAL_UINT;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.rL = System.currentTimeMillis();
        this.rM.add(new b(str, -1));
        this.a = j.en().er();
        this.b = str;
    }

    private void d(String str) {
        synchronized (this) {
            Iterator it = this.rM.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(((b) it.next()).a, str)) {
                    it.remove();
                }
            }
        }
    }

    public void a(double d) {
        this.rN = d;
    }

    public void a(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("the duration is invalid " + j);
        }
        this.rO = j;
    }

    public void a(String str, int i, long j, long j2, Exception exception) {
        a(str, new k(i, j, j2, exception));
    }

    public void a(String str, long j, long j2) {
        a(str, 0, j, j2, null);
    }

    public void a(String str, long j, long j2, Exception exception) {
        a(str, -1, j, j2, exception);
    }

    public void a(String str, k kVar) {
        synchronized (this) {
            Iterator it = this.rM.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (TextUtils.equals(str, bVar.a)) {
                    bVar.a(kVar);
                    break;
                }
            }
        }
    }

    public boolean a() {
        return TextUtils.equals(this.a, j.en().er());
    }

    public boolean a(i iVar) {
        return TextUtils.equals(this.a, iVar.a);
    }

    public ArrayList aK(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the url is empty.");
        }
        URL url = new URL(str);
        if (TextUtils.equals(url.getHost(), this.b)) {
            ArrayList arrayList = new ArrayList();
            Iterator it = dD().iterator();
            while (it.hasNext()) {
                arrayList.add(new URL(url.getProtocol(), (String) it.next(), url.getPort(), url.getFile()).toString());
            }
            return arrayList;
        }
        throw new IllegalArgumentException("the url is not supported by the fallback");
    }

    public void b(b bVar) {
        synchronized (this) {
            d(bVar.a);
            this.rM.add(bVar);
        }
    }

    public void b(String str) {
        synchronized (this) {
            b(new b(str));
        }
    }

    public boolean b() {
        return System.currentTimeMillis() - this.rL < this.rO;
    }

    public void c(String str) {
        this.m = str;
    }

    public void c(String[] strArr) {
        synchronized (this) {
            for (int size = this.rM.size() - 1; size >= 0; size--) {
                for (CharSequence equals : strArr) {
                    if (TextUtils.equals(((b) this.rM.get(size)).a, equals)) {
                        this.rM.remove(size);
                        break;
                    }
                }
            }
            Iterator it = this.rM.iterator();
            int i = 0;
            while (it.hasNext()) {
                b bVar = (b) it.next();
                i = bVar.b > i ? bVar.b : i;
            }
            for (int i2 = 0; i2 < strArr.length; i2++) {
                b(new b(strArr[i2], (strArr.length + i) - i2));
            }
        }
    }

    public String d() {
        String str;
        synchronized (this) {
            if (!TextUtils.isEmpty(this.k)) {
                str = this.k;
            } else if (TextUtils.isEmpty(this.e)) {
                str = "hardcode_isp";
            } else {
                this.k = j.a(new String[]{this.e, this.c, this.d, this.g, this.f}, "_");
                str = this.k;
            }
        }
        return str;
    }

    public ArrayList dD() {
        ArrayList arrayList;
        synchronized (this) {
            b[] bVarArr = new b[this.rM.size()];
            this.rM.toArray(bVarArr);
            Arrays.sort(bVarArr);
            arrayList = new ArrayList();
            for (b bVar : bVarArr) {
                arrayList.add(bVar.a);
            }
        }
        return arrayList;
    }

    public ArrayList dE() {
        return this.rM;
    }

    public double dF() {
        return this.rN < 1.0E-5d ? 0.1d : this.rN;
    }

    public i g(JSONObject jSONObject) {
        synchronized (this) {
            this.a = jSONObject.optString("net");
            this.rO = jSONObject.getLong("ttl");
            this.rN = jSONObject.getDouble("pct");
            this.rL = jSONObject.getLong("ts");
            this.d = jSONObject.optString("city");
            this.c = jSONObject.optString("prv");
            this.g = jSONObject.optString("cty");
            this.e = jSONObject.optString("isp");
            this.f = jSONObject.optString("ip");
            this.b = jSONObject.optString(MiniDefine.aL);
            this.h = jSONObject.optString("xf");
            JSONArray jSONArray = jSONObject.getJSONArray("fbs");
            for (int i = 0; i < jSONArray.length(); i++) {
                b(new b().b(jSONArray.getJSONObject(i)));
            }
        }
        return this;
    }

    public JSONObject g() {
        JSONObject jSONObject;
        synchronized (this) {
            jSONObject = new JSONObject();
            jSONObject.put("net", this.a);
            jSONObject.put("ttl", this.rO);
            jSONObject.put("pct", this.rN);
            jSONObject.put("ts", this.rL);
            jSONObject.put("city", this.d);
            jSONObject.put("prv", this.c);
            jSONObject.put("cty", this.g);
            jSONObject.put("isp", this.e);
            jSONObject.put("ip", this.f);
            jSONObject.put(MiniDefine.aL, this.b);
            jSONObject.put("xf", this.h);
            JSONArray jSONArray = new JSONArray();
            Iterator it = this.rM.iterator();
            while (it.hasNext()) {
                jSONArray.put(((b) it.next()).ak());
            }
            jSONObject.put("fbs", jSONArray);
        }
        return jSONObject;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append("\n");
        stringBuilder.append(d());
        Iterator it = this.rM.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            stringBuilder.append("\n");
            stringBuilder.append(bVar.toString());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
