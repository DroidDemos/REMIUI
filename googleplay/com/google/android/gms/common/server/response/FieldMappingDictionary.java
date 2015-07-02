package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.internal.kn;
import java.util.ArrayList;
import java.util.HashMap;

public class FieldMappingDictionary implements SafeParcelable {
    public static final c CREATOR;
    private final String XA;
    private final HashMap<String, HashMap<String, Field<?, ?>>> Xy;
    private final ArrayList<Entry> Xz;
    private final int mVersionCode;

    public static class Entry implements SafeParcelable {
        public static final d CREATOR;
        final ArrayList<FieldMapPair> XB;
        final String className;
        final int versionCode;

        static {
            CREATOR = new d();
        }

        Entry(int versionCode, String className, ArrayList<FieldMapPair> fieldMapping) {
            this.versionCode = versionCode;
            this.className = className;
            this.XB = fieldMapping;
        }

        Entry(String className, HashMap<String, Field<?, ?>> fieldMap) {
            this.versionCode = 1;
            this.className = className;
            this.XB = c(fieldMap);
        }

        private static ArrayList<FieldMapPair> c(HashMap<String, Field<?, ?>> hashMap) {
            if (hashMap == null) {
                return null;
            }
            ArrayList<FieldMapPair> arrayList = new ArrayList();
            for (String str : hashMap.keySet()) {
                arrayList.add(new FieldMapPair(str, (Field) hashMap.get(str)));
            }
            return arrayList;
        }

        public int describeContents() {
            d dVar = CREATOR;
            return 0;
        }

        HashMap<String, Field<?, ?>> jf() {
            HashMap<String, Field<?, ?>> hashMap = new HashMap();
            int size = this.XB.size();
            for (int i = 0; i < size; i++) {
                FieldMapPair fieldMapPair = (FieldMapPair) this.XB.get(i);
                hashMap.put(fieldMapPair.key, fieldMapPair.XC);
            }
            return hashMap;
        }

        public void writeToParcel(Parcel out, int flags) {
            d dVar = CREATOR;
            d.a(this, out, flags);
        }
    }

    public static class FieldMapPair implements SafeParcelable {
        public static final b CREATOR;
        final Field<?, ?> XC;
        final String key;
        final int versionCode;

        static {
            CREATOR = new b();
        }

        FieldMapPair(int versionCode, String key, Field<?, ?> value) {
            this.versionCode = versionCode;
            this.key = key;
            this.XC = value;
        }

        FieldMapPair(String key, Field<?, ?> value) {
            this.versionCode = 1;
            this.key = key;
            this.XC = value;
        }

        public int describeContents() {
            b bVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            b bVar = CREATOR;
            b.a(this, out, flags);
        }
    }

    static {
        CREATOR = new c();
    }

    FieldMappingDictionary(int versionCode, ArrayList<Entry> serializedDictionary, String rootClassName) {
        this.mVersionCode = versionCode;
        this.Xz = null;
        this.Xy = c(serializedDictionary);
        this.XA = (String) kn.k(rootClassName);
        linkFields();
    }

    private static HashMap<String, HashMap<String, Field<?, ?>>> c(ArrayList<Entry> arrayList) {
        HashMap<String, HashMap<String, Field<?, ?>>> hashMap = new HashMap();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Entry entry = (Entry) arrayList.get(i);
            hashMap.put(entry.className, entry.jf());
        }
        return hashMap;
    }

    public int describeContents() {
        c cVar = CREATOR;
        return 0;
    }

    public HashMap<String, Field<?, ?>> getFieldMapping(String className) {
        return (HashMap) this.Xy.get(className);
    }

    public String getRootClassName() {
        return this.XA;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    ArrayList<Entry> je() {
        ArrayList<Entry> arrayList = new ArrayList();
        for (String str : this.Xy.keySet()) {
            arrayList.add(new Entry(str, (HashMap) this.Xy.get(str)));
        }
        return arrayList;
    }

    public void linkFields() {
        for (String str : this.Xy.keySet()) {
            HashMap hashMap = (HashMap) this.Xy.get(str);
            for (String str2 : hashMap.keySet()) {
                ((Field) hashMap.get(str2)).setFieldMappingDictionary(this);
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.Xy.keySet()) {
            stringBuilder.append(str).append(":\n");
            HashMap hashMap = (HashMap) this.Xy.get(str);
            for (String str2 : hashMap.keySet()) {
                stringBuilder.append("  ").append(str2).append(": ");
                stringBuilder.append(hashMap.get(str2));
            }
        }
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        c cVar = CREATOR;
        c.a(this, out, flags);
    }
}
