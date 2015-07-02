package com.xiaomi.network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeightedHost implements Comparable<WeightedHost> {
    private static final int HISTORY_SIZE = 30;
    private final LinkedList<AccessHistory> accessHistories;
    public String host;
    private long touchedTime;
    protected int weight;

    public WeightedHost() {
        this(null, 0);
    }

    public WeightedHost(String host) {
        this(host, 0);
    }

    public WeightedHost(String host, int weight) {
        this.accessHistories = new LinkedList();
        this.touchedTime = 0;
        this.host = host;
        this.weight = weight;
    }

    protected synchronized void addAccessHistory(AccessHistory ah) {
        if (ah != null) {
            UploadHostStatHelper.getInstance().fireHostEvent();
            this.accessHistories.add(ah);
            int newWeight = ah.getWeight();
            if (newWeight > 0) {
                this.weight += ah.getWeight();
            } else {
                int cnt = 0;
                int i = this.accessHistories.size() - 1;
                while (i >= 0 && ((AccessHistory) this.accessHistories.get(i)).getWeight() < 0) {
                    cnt++;
                    i--;
                }
                this.weight += newWeight * cnt;
            }
            if (this.accessHistories.size() > HISTORY_SIZE) {
                this.weight -= ((AccessHistory) this.accessHistories.remove()).getWeight();
            }
        }
    }

    public String toString() {
        return this.host + ":" + this.weight;
    }

    public synchronized ArrayList<AccessHistory> getAccessHistory() {
        return new ArrayList(this.accessHistories);
    }

    public synchronized ArrayList<AccessHistory> getUnTouchedAccessHistory() {
        ArrayList<AccessHistory> ahs;
        ahs = new ArrayList();
        Iterator i$ = this.accessHistories.iterator();
        while (i$.hasNext()) {
            AccessHistory ah = (AccessHistory) i$.next();
            if (ah.getTime() > this.touchedTime) {
                ahs.add(ah);
            }
        }
        this.touchedTime = System.currentTimeMillis();
        return ahs;
    }

    public int compareTo(WeightedHost another) {
        if (another == null) {
            return 1;
        }
        return another.weight - this.weight;
    }

    public synchronized JSONObject toJSON() throws JSONException {
        JSONObject jsonObject;
        jsonObject = new JSONObject();
        jsonObject.put("tt", this.touchedTime);
        jsonObject.put("wt", this.weight);
        jsonObject.put("host", this.host);
        JSONArray jsonArray = new JSONArray();
        Iterator i$ = this.accessHistories.iterator();
        while (i$.hasNext()) {
            jsonArray.put(((AccessHistory) i$.next()).toJSON());
        }
        jsonObject.put("ah", jsonArray);
        return jsonObject;
    }

    public synchronized WeightedHost fromJSON(JSONObject jsonObject) throws JSONException {
        this.touchedTime = jsonObject.getLong("tt");
        this.weight = jsonObject.getInt("wt");
        this.host = jsonObject.getString("host");
        JSONArray ah = jsonObject.getJSONArray("ah");
        for (int i = 0; i < ah.length(); i++) {
            this.accessHistories.add(new AccessHistory().fromJSON(ah.getJSONObject(i)));
        }
        return this;
    }
}
