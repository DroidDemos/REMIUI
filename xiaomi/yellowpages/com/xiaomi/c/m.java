package com.xiaomi.c;

import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class m {
    private final ArrayList DB;
    private String host;

    public m() {
        this.DB = new ArrayList();
    }

    public m(String str) {
        this.DB = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.host = str;
    }

    public void b(i iVar) {
        synchronized (this) {
            int i = 0;
            while (i < this.DB.size()) {
                if (((i) this.DB.get(i)).a(iVar)) {
                    this.DB.set(i, iVar);
                    break;
                }
                i++;
            }
            if (i >= this.DB.size()) {
                this.DB.add(iVar);
            }
        }
    }

    public String getHost() {
        return this.host;
    }

    public i hb() {
        i iVar;
        synchronized (this) {
            for (int size = this.DB.size() - 1; size >= 0; size--) {
                iVar = (i) this.DB.get(size);
                if (!iVar.b()) {
                    this.DB.remove(size);
                } else if (iVar.a()) {
                    j.en().bb(iVar.d());
                    break;
                }
            }
            iVar = null;
        }
        return iVar;
    }

    public ArrayList hc() {
        return this.DB;
    }

    public JSONObject hd() {
        JSONObject jSONObject;
        synchronized (this) {
            jSONObject = new JSONObject();
            jSONObject.put(MiniDefine.aL, this.host);
            JSONArray jSONArray = new JSONArray();
            Iterator it = this.DB.iterator();
            while (it.hasNext()) {
                jSONArray.put(((i) it.next()).g());
            }
            jSONObject.put("fbs", jSONArray);
        }
        return jSONObject;
    }

    public m m(JSONObject jSONObject) {
        synchronized (this) {
            this.host = jSONObject.getString(MiniDefine.aL);
            JSONArray jSONArray = jSONObject.getJSONArray("fbs");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.DB.add(new i(this.host).g(jSONArray.getJSONObject(i)));
            }
        }
        return this;
    }

    public void purge() {
        synchronized (this) {
            for (int size = this.DB.size() - 1; size >= 0; size--) {
                if (!((i) this.DB.get(size)).b()) {
                    this.DB.remove(size);
                }
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.host);
        stringBuilder.append("\n");
        Iterator it = this.DB.iterator();
        while (it.hasNext()) {
            stringBuilder.append((i) it.next());
        }
        return stringBuilder.toString();
    }
}
