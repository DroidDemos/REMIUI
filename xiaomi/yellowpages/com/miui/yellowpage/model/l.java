package com.miui.yellowpage.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.PriorityQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Express */
public class l {
    private ArrayList vH;
    private ArrayList vI;
    private ArrayList vJ;
    private ArrayList vK;

    public l(ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.vH = arrayList;
        this.vI = arrayList2;
        this.vJ = arrayList3;
        this.vK = arrayList4;
    }

    public ArrayList fj() {
        return this.vI;
    }

    public ArrayList fk() {
        return this.vH;
    }

    public ArrayList fl() {
        return this.vJ;
    }

    public ArrayList fm() {
        return this.vK;
    }

    public static l bm(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        PriorityQueue priorityQueue = new PriorityQueue();
        PriorityQueue priorityQueue2 = new PriorityQueue();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String string = jSONObject.getString("bizName");
                    String string2 = jSONObject.getString("bizCode");
                    int i2 = jSONObject.getInt("order");
                    if (i2 == 0) {
                        priorityQueue2.add(new m(string, string2));
                    } else {
                        priorityQueue2.add(new m(string, string2));
                        priorityQueue.add(new d(string, string2, i2));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        while (!priorityQueue.isEmpty()) {
            d dVar = (d) priorityQueue.poll();
            CharSequence name = dVar.getName();
            CharSequence aP = dVar.aP();
            if (!(TextUtils.isEmpty(name) || TextUtils.isEmpty(aP))) {
                arrayList3.add(dVar.getName());
                arrayList4.add(dVar.aP());
            }
        }
        while (!priorityQueue2.isEmpty()) {
            m mVar = (m) priorityQueue2.poll();
            CharSequence name2 = mVar.getName();
            name = mVar.aP();
            if (!(TextUtils.isEmpty(name2) || TextUtils.isEmpty(name))) {
                arrayList.add(mVar.getName());
                arrayList2.add(mVar.aP());
            }
        }
        return new l(arrayList, arrayList2, arrayList3, arrayList4);
    }
}
