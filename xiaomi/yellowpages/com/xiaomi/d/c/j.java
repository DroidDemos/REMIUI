package com.xiaomi.d.c;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.d.e.g;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class j implements e {
    private String[] ME;
    private String[] MF;
    private String a;
    private String b;
    private String e;
    private List f;

    public j(String str, String str2, String[] strArr, String[] strArr2) {
        this.ME = null;
        this.MF = null;
        this.f = null;
        this.a = str;
        this.b = str2;
        this.ME = strArr;
        this.MF = strArr2;
    }

    public j(String str, String str2, String[] strArr, String[] strArr2, String str3, List list) {
        this.ME = null;
        this.MF = null;
        this.f = null;
        this.a = str;
        this.b = str2;
        this.ME = strArr;
        this.MF = strArr2;
        this.e = str3;
        this.f = list;
    }

    public static Parcelable[] a(j[] jVarArr) {
        if (jVarArr == null) {
            return null;
        }
        Parcelable[] parcelableArr = new Parcelable[jVarArr.length];
        for (int i = 0; i < jVarArr.length; i++) {
            parcelableArr[i] = jVarArr[i].ji();
        }
        return parcelableArr;
    }

    public static Parcelable[] h(List list) {
        return a((j[]) list.toArray(new j[list.size()]));
    }

    public static j k(Bundle bundle) {
        List arrayList;
        int i = 0;
        String string = bundle.getString("ext_ele_name");
        String string2 = bundle.getString("ext_ns");
        String string3 = bundle.getString("ext_text");
        Bundle bundle2 = bundle.getBundle("attributes");
        Set<String> keySet = bundle2.keySet();
        String[] strArr = new String[keySet.size()];
        String[] strArr2 = new String[keySet.size()];
        int i2 = 0;
        for (String str : keySet) {
            strArr[i2] = str;
            strArr2[i2] = bundle2.getString(str);
            i2++;
        }
        if (bundle.containsKey("children")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("children");
            arrayList = new ArrayList(parcelableArray.length);
            int length = parcelableArray.length;
            while (i < length) {
                arrayList.add(k((Bundle) parcelableArray[i]));
                i++;
            }
        } else {
            arrayList = null;
        }
        return new j(string, string2, strArr, strArr2, string3, arrayList);
    }

    public String a() {
        return this.a;
    }

    public String a(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (this.ME != null) {
            for (int i = 0; i < this.ME.length; i++) {
                if (str.equals(this.ME[i])) {
                    return this.MF[i];
                }
            }
        }
        return null;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            this.e = str;
        } else {
            this.e = g.a(str);
        }
    }

    public String c() {
        return !TextUtils.isEmpty(this.e) ? g.b(this.e) : this.e;
    }

    public String d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(this.a);
        if (!TextUtils.isEmpty(this.b)) {
            stringBuilder.append(" ").append("xmlns=").append("\"").append(this.b).append("\"");
        }
        if (this.ME != null && this.ME.length > 0) {
            for (int i = 0; i < this.ME.length; i++) {
                if (!TextUtils.isEmpty(this.MF[i])) {
                    stringBuilder.append(" ").append(this.ME[i]).append("=\"").append(g.a(this.MF[i])).append("\"");
                }
            }
        }
        if (!TextUtils.isEmpty(this.e)) {
            stringBuilder.append(">").append(this.e).append("</").append(this.a).append(">");
        } else if (this.f == null || this.f.size() <= 0) {
            stringBuilder.append("/>");
        } else {
            stringBuilder.append(">");
            for (j d : this.f) {
                stringBuilder.append(d.d());
            }
            stringBuilder.append("</").append(this.a).append(">");
        }
        return stringBuilder.toString();
    }

    public Bundle jh() {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", this.a);
        bundle.putString("ext_ns", this.b);
        bundle.putString("ext_text", this.e);
        Bundle bundle2 = new Bundle();
        if (this.ME != null && this.ME.length > 0) {
            for (int i = 0; i < this.ME.length; i++) {
                bundle2.putString(this.ME[i], this.MF[i]);
            }
        }
        bundle.putBundle("attributes", bundle2);
        if (this.f != null && this.f.size() > 0) {
            bundle.putParcelableArray("children", h(this.f));
        }
        return bundle;
    }

    public Parcelable ji() {
        return jh();
    }

    public String toString() {
        return d();
    }
}
