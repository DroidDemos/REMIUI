package com.miui.yellowpage.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: YellowPagePickerDataEntry */
public class w {
    protected String Li;
    protected String iM;
    protected int mType;

    public w(String str, String str2) {
        this.iM = str;
        this.Li = str2;
        this.mType = 0;
    }

    public String in() {
        return this.iM;
    }

    public String getData() {
        return this.Li;
    }

    public static List a(ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        String str;
        String str2;
        int i = 0;
        List arrayList5 = new ArrayList();
        if (arrayList3 != null) {
            int i2 = 0;
            while (i2 < arrayList3.size()) {
                str = (String) arrayList3.get(i2);
                if (arrayList4 == null || arrayList4.size() <= i2) {
                    str2 = null;
                } else {
                    str2 = (String) arrayList4.get(i2);
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = str;
                }
                arrayList5.add(new h(str, str2));
                i2++;
            }
        }
        if (arrayList != null) {
            while (i < arrayList.size()) {
                str = (String) arrayList.get(i);
                if (arrayList2 == null || arrayList2.size() <= i) {
                    str2 = null;
                } else {
                    str2 = (String) arrayList2.get(i);
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = str;
                }
                arrayList5.add(new w(str, str2));
                i++;
            }
        }
        return arrayList5;
    }

    public String toString() {
        return "[" + this.iM + ":" + this.Li + "]";
    }
}
