package com.xiaomi.network;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fallbacks {
    private String host;
    private final ArrayList<Fallback> mFallbacks;

    public Fallbacks(String host) {
        this.mFallbacks = new ArrayList();
        if (TextUtils.isEmpty(host)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.host = host;
    }

    public Fallbacks() {
        this.mFallbacks = new ArrayList();
    }

    public synchronized void addFallback(Fallback fb) {
        int index = 0;
        while (index < this.mFallbacks.size()) {
            if (((Fallback) this.mFallbacks.get(index)).match(fb)) {
                this.mFallbacks.set(index, fb);
                break;
            }
            index++;
        }
        if (index >= this.mFallbacks.size()) {
            this.mFallbacks.add(fb);
        }
    }

    public synchronized void addFallbacks(ArrayList<Fallback> fbs) {
        Iterator i$ = fbs.iterator();
        while (i$.hasNext()) {
            addFallback((Fallback) i$.next());
        }
    }

    public synchronized Fallback getFallback() {
        Fallback fb;
        for (int i = this.mFallbacks.size() - 1; i >= 0; i--) {
            fb = (Fallback) this.mFallbacks.get(i);
            if (!fb.isEffective()) {
                this.mFallbacks.remove(i);
            } else if (fb.match()) {
                HostManager.getInstance().setCurrentISP(fb.getISP());
                break;
            }
        }
        fb = null;
        return fb;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.host);
        sb.append("\n");
        Iterator i$ = this.mFallbacks.iterator();
        while (i$.hasNext()) {
            sb.append((Fallback) i$.next());
        }
        return sb.toString();
    }

    public ArrayList<Fallback> getFallbacks() {
        return this.mFallbacks;
    }

    public synchronized void purge() {
        for (int i = this.mFallbacks.size() - 1; i >= 0; i--) {
            if (!((Fallback) this.mFallbacks.get(i)).isEffective()) {
                this.mFallbacks.remove(i);
            }
        }
    }

    public String getHost() {
        return this.host;
    }

    public synchronized JSONObject toJSON() throws JSONException {
        JSONObject json;
        json = new JSONObject();
        json.put("host", this.host);
        JSONArray jsonArray = new JSONArray();
        Iterator i$ = this.mFallbacks.iterator();
        while (i$.hasNext()) {
            jsonArray.put(((Fallback) i$.next()).toJSON());
        }
        json.put("fbs", jsonArray);
        return json;
    }

    public synchronized Fallbacks fromJSON(JSONObject jsonObject) throws JSONException {
        this.host = jsonObject.getString("host");
        JSONArray ja = jsonObject.getJSONArray("fbs");
        for (int i = 0; i < ja.length(); i++) {
            this.mFallbacks.add(new Fallback(this.host).fromJSON(ja.getJSONObject(i)));
        }
        return this;
    }
}
