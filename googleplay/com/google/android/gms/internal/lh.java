package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class lh implements SafeParcelable, FieldConverter<String, Integer> {
    public static final li CREATOR;
    private final HashMap<String, Integer> WS;
    private final HashMap<Integer, String> WT;
    private final ArrayList<a> WU;
    private final int mVersionCode;

    public static final class a implements SafeParcelable {
        public static final lj CREATOR;
        final String WV;
        final int WW;
        final int versionCode;

        static {
            CREATOR = new lj();
        }

        a(int i, String str, int i2) {
            this.versionCode = i;
            this.WV = str;
            this.WW = i2;
        }

        a(String str, int i) {
            this.versionCode = 1;
            this.WV = str;
            this.WW = i;
        }

        public int describeContents() {
            lj ljVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            lj ljVar = CREATOR;
            lj.a(this, out, flags);
        }
    }

    static {
        CREATOR = new li();
    }

    public lh() {
        this.mVersionCode = 1;
        this.WS = new HashMap();
        this.WT = new HashMap();
        this.WU = null;
    }

    lh(int i, ArrayList<a> arrayList) {
        this.mVersionCode = i;
        this.WS = new HashMap();
        this.WT = new HashMap();
        this.WU = null;
        b(arrayList);
    }

    private void b(ArrayList<a> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            i(aVar.WV, aVar.WW);
        }
    }

    public String a(Integer num) {
        String str = (String) this.WT.get(num);
        return (str == null && this.WS.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    public /* synthetic */ Object convertBack(Object x0) {
        return a((Integer) x0);
    }

    public int describeContents() {
        li liVar = CREATOR;
        return 0;
    }

    public int getTypeIn() {
        return 7;
    }

    public int getTypeOut() {
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public lh i(String str, int i) {
        this.WS.put(str, Integer.valueOf(i));
        this.WT.put(Integer.valueOf(i), str);
        return this;
    }

    ArrayList<a> jb() {
        ArrayList<a> arrayList = new ArrayList();
        for (String str : this.WS.keySet()) {
            arrayList.add(new a(str, ((Integer) this.WS.get(str)).intValue()));
        }
        return arrayList;
    }

    public void writeToParcel(Parcel out, int flags) {
        li liVar = CREATOR;
        li.a(this, out, flags);
    }
}
