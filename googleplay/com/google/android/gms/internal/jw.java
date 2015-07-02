package com.google.android.gms.internal;

import android.os.Parcel;
import android.view.View;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class jw {
    private final View Sv;
    private final a VA;

    public static final class a implements SafeParcelable {
        public static final km CREATOR;
        private final String Fl;
        private final List<String> ST;
        private final int Su;
        private final String Sw;
        private final int mVersionCode;

        static {
            CREATOR = new km();
        }

        a(int i, String str, List<String> list, int i2, String str2) {
            this.ST = new ArrayList();
            this.mVersionCode = i;
            this.Fl = str;
            this.ST.addAll(list);
            this.Su = i2;
            this.Sw = str2;
        }

        public a(String str, Collection<String> collection, int i, String str2) {
            this(3, str, new ArrayList(collection), i, str2);
        }

        public int describeContents() {
            return 0;
        }

        public String getAccountName() {
            return this.Fl;
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public int iG() {
            return this.Su;
        }

        public List<String> iH() {
            return new ArrayList(this.ST);
        }

        public String iJ() {
            return this.Sw;
        }

        public void writeToParcel(Parcel out, int flags) {
            km.a(this, out, flags);
        }
    }

    public jw(String str, Collection<String> collection, int i, View view, String str2) {
        this.VA = new a(str, collection, i, str2);
        this.Sv = view;
    }

    public List<String> iH() {
        return this.VA.iH();
    }

    public String[] iI() {
        return (String[]) this.VA.iH().toArray(new String[0]);
    }
}
