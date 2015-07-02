package com.xiaomi.c;

import com.alipay.sdk.cons.MiniDefine;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

public class b implements Comparable {
    public String a;
    protected int b;
    private long d;
    private final LinkedList eZ;

    public b() {
        this(null, 0);
    }

    public b(String str) {
        this(str, 0);
    }

    public b(String str, int i) {
        this.eZ = new LinkedList();
        this.d = 0;
        this.a = str;
        this.b = i;
    }

    public int a(b bVar) {
        return bVar == null ? 1 : bVar.b - this.b;
    }

    protected void a(k kVar) {
        synchronized (this) {
            if (kVar != null) {
                l.fn().b();
                this.eZ.add(kVar);
                int a = kVar.a();
                if (a > 0) {
                    this.b += kVar.a();
                } else {
                    int i = 0;
                    int size = this.eZ.size() - 1;
                    while (size >= 0 && ((k) this.eZ.get(size)).a() < 0) {
                        i++;
                        size--;
                    }
                    this.b += a * i;
                }
                if (this.eZ.size() > 30) {
                    this.b -= ((k) this.eZ.remove()).a();
                }
            }
        }
    }

    public ArrayList aj() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList();
            Iterator it = this.eZ.iterator();
            while (it.hasNext()) {
                k kVar = (k) it.next();
                if (kVar.c() > this.d) {
                    arrayList.add(kVar);
                }
            }
            this.d = System.currentTimeMillis();
        }
        return arrayList;
    }

    public JSONObject ak() {
        JSONObject jSONObject;
        synchronized (this) {
            jSONObject = new JSONObject();
            jSONObject.put("tt", this.d);
            jSONObject.put("wt", this.b);
            jSONObject.put(MiniDefine.aL, this.a);
            JSONArray jSONArray = new JSONArray();
            Iterator it = this.eZ.iterator();
            while (it.hasNext()) {
                jSONArray.put(((k) it.next()).ai());
            }
            jSONObject.put("ah", jSONArray);
        }
        return jSONObject;
    }

    public b b(JSONObject jSONObject) {
        synchronized (this) {
            this.d = jSONObject.getLong("tt");
            this.b = jSONObject.getInt("wt");
            this.a = jSONObject.getString(MiniDefine.aL);
            JSONArray jSONArray = jSONObject.getJSONArray("ah");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.eZ.add(new k().l(jSONArray.getJSONObject(i)));
            }
        }
        return this;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return a((b) obj);
    }

    public String toString() {
        return this.a + ":" + this.b;
    }
}
