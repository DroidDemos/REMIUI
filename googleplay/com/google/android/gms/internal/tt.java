package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.AgeRange;
import com.google.android.gms.plus.model.people.Person.Cover;
import com.google.android.gms.plus.model.people.Person.Cover.CoverInfo;
import com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto;
import com.google.android.gms.plus.model.people.Person.Image;
import com.google.android.gms.plus.model.people.Person.Name;
import com.google.android.gms.plus.model.people.Person.Organizations;
import com.google.android.gms.plus.model.people.Person.PlacesLived;
import com.google.android.gms.plus.model.people.Person.Urls;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class tt extends FastSafeParcelableJsonResponse implements Person {
    public static final tu CREATOR;
    private static final HashMap<String, Field<?, ?>> aIy;
    String CB;
    String Oe;
    String WN;
    final Set<Integer> aIz;
    String aJA;
    int aJB;
    b aJC;
    String aJD;
    c aJE;
    boolean aJF;
    d aJG;
    String aJH;
    int aJI;
    List<f> aJJ;
    List<g> aJK;
    int aJL;
    int aJM;
    String aJN;
    List<h> aJO;
    boolean aJP;
    String aJx;
    a aJy;
    String aJz;
    final int mVersionCode;
    int oy;
    String vf;

    public static final class a extends FastSafeParcelableJsonResponse implements AgeRange {
        public static final tv CREATOR;
        private static final HashMap<String, Field<?, ?>> aIy;
        final Set<Integer> aIz;
        int aJQ;
        int aJR;
        final int mVersionCode;

        static {
            CREATOR = new tv();
            aIy = new HashMap();
            aIy.put("max", Field.forInteger("max", 2));
            aIy.put("min", Field.forInteger("min", 3));
        }

        public a() {
            this.mVersionCode = 1;
            this.aIz = new HashSet();
        }

        a(Set<Integer> set, int i, int i2, int i3) {
            this.aIz = set;
            this.mVersionCode = i;
            this.aJQ = i2;
            this.aJR = i3;
        }

        public int describeContents() {
            tv tvVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            a aVar = (a) obj;
            for (Field field : aIy.values()) {
                if (isFieldSet(field)) {
                    if (!aVar.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(aVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (aVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return rO();
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return aIy;
        }

        protected Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return Integer.valueOf(this.aJQ);
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return Integer.valueOf(this.aJR);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public int hashCode() {
            int i = 0;
            for (Field field : aIy.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        protected boolean isFieldSet(Field field) {
            return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        public a rO() {
            return this;
        }

        public void writeToParcel(Parcel out, int flags) {
            tv tvVar = CREATOR;
            tv.a(this, out, flags);
        }
    }

    public static final class b extends FastSafeParcelableJsonResponse implements Cover {
        public static final tw CREATOR;
        private static final HashMap<String, Field<?, ?>> aIy;
        final Set<Integer> aIz;
        a aJS;
        b aJT;
        int aJU;
        final int mVersionCode;

        public static final class a extends FastSafeParcelableJsonResponse implements CoverInfo {
            public static final tx CREATOR;
            private static final HashMap<String, Field<?, ?>> aIy;
            final Set<Integer> aIz;
            int aJV;
            int aJW;
            final int mVersionCode;

            static {
                CREATOR = new tx();
                aIy = new HashMap();
                aIy.put("leftImageOffset", Field.forInteger("leftImageOffset", 2));
                aIy.put("topImageOffset", Field.forInteger("topImageOffset", 3));
            }

            public a() {
                this.mVersionCode = 1;
                this.aIz = new HashSet();
            }

            a(Set<Integer> set, int i, int i2, int i3) {
                this.aIz = set;
                this.mVersionCode = i;
                this.aJV = i2;
                this.aJW = i3;
            }

            public int describeContents() {
                tx txVar = CREATOR;
                return 0;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof a)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                a aVar = (a) obj;
                for (Field field : aIy.values()) {
                    if (isFieldSet(field)) {
                        if (!aVar.isFieldSet(field)) {
                            return false;
                        }
                        if (!getFieldValue(field).equals(aVar.getFieldValue(field))) {
                            return false;
                        }
                    } else if (aVar.isFieldSet(field)) {
                        return false;
                    }
                }
                return true;
            }

            public /* synthetic */ Object freeze() {
                return rQ();
            }

            public HashMap<String, Field<?, ?>> getFieldMappings() {
                return aIy;
            }

            protected Object getFieldValue(Field field) {
                switch (field.getSafeParcelableFieldId()) {
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        return Integer.valueOf(this.aJV);
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        return Integer.valueOf(this.aJW);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
                }
            }

            public int hashCode() {
                int i = 0;
                for (Field field : aIy.values()) {
                    int hashCode;
                    if (isFieldSet(field)) {
                        hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                    } else {
                        hashCode = i;
                    }
                    i = hashCode;
                }
                return i;
            }

            protected boolean isFieldSet(Field field) {
                return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
            }

            public a rQ() {
                return this;
            }

            public void writeToParcel(Parcel out, int flags) {
                tx txVar = CREATOR;
                tx.a(this, out, flags);
            }
        }

        public static final class b extends FastSafeParcelableJsonResponse implements CoverPhoto {
            public static final ty CREATOR;
            private static final HashMap<String, Field<?, ?>> aIy;
            final Set<Integer> aIz;
            int lh;
            int li;
            final int mVersionCode;
            String vf;

            static {
                CREATOR = new ty();
                aIy = new HashMap();
                aIy.put("height", Field.forInteger("height", 2));
                aIy.put("url", Field.forString("url", 3));
                aIy.put("width", Field.forInteger("width", 4));
            }

            public b() {
                this.mVersionCode = 1;
                this.aIz = new HashSet();
            }

            b(Set<Integer> set, int i, int i2, String str, int i3) {
                this.aIz = set;
                this.mVersionCode = i;
                this.li = i2;
                this.vf = str;
                this.lh = i3;
            }

            public int describeContents() {
                ty tyVar = CREATOR;
                return 0;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof b)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                b bVar = (b) obj;
                for (Field field : aIy.values()) {
                    if (isFieldSet(field)) {
                        if (!bVar.isFieldSet(field)) {
                            return false;
                        }
                        if (!getFieldValue(field).equals(bVar.getFieldValue(field))) {
                            return false;
                        }
                    } else if (bVar.isFieldSet(field)) {
                        return false;
                    }
                }
                return true;
            }

            public /* synthetic */ Object freeze() {
                return rR();
            }

            public HashMap<String, Field<?, ?>> getFieldMappings() {
                return aIy;
            }

            protected Object getFieldValue(Field field) {
                switch (field.getSafeParcelableFieldId()) {
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        return Integer.valueOf(this.li);
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        return this.vf;
                    case R.styleable.WalletImFormEditText_required /*4*/:
                        return Integer.valueOf(this.lh);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
                }
            }

            public int hashCode() {
                int i = 0;
                for (Field field : aIy.values()) {
                    int hashCode;
                    if (isFieldSet(field)) {
                        hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                    } else {
                        hashCode = i;
                    }
                    i = hashCode;
                }
                return i;
            }

            protected boolean isFieldSet(Field field) {
                return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
            }

            public b rR() {
                return this;
            }

            public void writeToParcel(Parcel out, int flags) {
                ty tyVar = CREATOR;
                ty.a(this, out, flags);
            }
        }

        static {
            CREATOR = new tw();
            aIy = new HashMap();
            aIy.put("coverInfo", Field.forConcreteType("coverInfo", 2, a.class));
            aIy.put("coverPhoto", Field.forConcreteType("coverPhoto", 3, b.class));
            aIy.put("layout", Field.withConverter("layout", 4, new lh().i("banner", 0), false));
        }

        public b() {
            this.mVersionCode = 1;
            this.aIz = new HashSet();
        }

        b(Set<Integer> set, int i, a aVar, b bVar, int i2) {
            this.aIz = set;
            this.mVersionCode = i;
            this.aJS = aVar;
            this.aJT = bVar;
            this.aJU = i2;
        }

        public int describeContents() {
            tw twVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            b bVar = (b) obj;
            for (Field field : aIy.values()) {
                if (isFieldSet(field)) {
                    if (!bVar.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(bVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (bVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return rP();
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return aIy;
        }

        protected Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return this.aJS;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return this.aJT;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    return Integer.valueOf(this.aJU);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public int hashCode() {
            int i = 0;
            for (Field field : aIy.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        protected boolean isFieldSet(Field field) {
            return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        public b rP() {
            return this;
        }

        public void writeToParcel(Parcel out, int flags) {
            tw twVar = CREATOR;
            tw.a(this, out, flags);
        }
    }

    public static final class c extends FastSafeParcelableJsonResponse implements Image {
        public static final tz CREATOR;
        private static final HashMap<String, Field<?, ?>> aIy;
        final Set<Integer> aIz;
        final int mVersionCode;
        String vf;

        static {
            CREATOR = new tz();
            aIy = new HashMap();
            aIy.put("url", Field.forString("url", 2));
        }

        public c() {
            this.mVersionCode = 1;
            this.aIz = new HashSet();
        }

        c(Set<Integer> set, int i, String str) {
            this.aIz = set;
            this.mVersionCode = i;
            this.vf = str;
        }

        public int describeContents() {
            tz tzVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof c)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            c cVar = (c) obj;
            for (Field field : aIy.values()) {
                if (isFieldSet(field)) {
                    if (!cVar.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(cVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (cVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return rS();
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return aIy;
        }

        protected Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return this.vf;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public int hashCode() {
            int i = 0;
            for (Field field : aIy.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        protected boolean isFieldSet(Field field) {
            return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        public c rS() {
            return this;
        }

        public void writeToParcel(Parcel out, int flags) {
            tz tzVar = CREATOR;
            tz.a(this, out, flags);
        }
    }

    public static final class d extends FastSafeParcelableJsonResponse implements Name {
        public static final ua CREATOR;
        private static final HashMap<String, Field<?, ?>> aIy;
        String aIX;
        final Set<Integer> aIz;
        String aJX;
        String aJY;
        String aJZ;
        String aJa;
        String aKa;
        final int mVersionCode;

        static {
            CREATOR = new ua();
            aIy = new HashMap();
            aIy.put("familyName", Field.forString("familyName", 2));
            aIy.put("formatted", Field.forString("formatted", 3));
            aIy.put("givenName", Field.forString("givenName", 4));
            aIy.put("honorificPrefix", Field.forString("honorificPrefix", 5));
            aIy.put("honorificSuffix", Field.forString("honorificSuffix", 6));
            aIy.put("middleName", Field.forString("middleName", 7));
        }

        public d() {
            this.mVersionCode = 1;
            this.aIz = new HashSet();
        }

        d(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, String str6) {
            this.aIz = set;
            this.mVersionCode = i;
            this.aIX = str;
            this.aJX = str2;
            this.aJa = str3;
            this.aJY = str4;
            this.aJZ = str5;
            this.aKa = str6;
        }

        public int describeContents() {
            ua uaVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof d)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            d dVar = (d) obj;
            for (Field field : aIy.values()) {
                if (isFieldSet(field)) {
                    if (!dVar.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(dVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (dVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return rT();
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return aIy;
        }

        protected Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return this.aIX;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return this.aJX;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    return this.aJa;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    return this.aJY;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    return this.aJZ;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    return this.aKa;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public int hashCode() {
            int i = 0;
            for (Field field : aIy.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        protected boolean isFieldSet(Field field) {
            return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        public d rT() {
            return this;
        }

        public void writeToParcel(Parcel out, int flags) {
            ua uaVar = CREATOR;
            ua.a(this, out, flags);
        }
    }

    public static final class f extends FastSafeParcelableJsonResponse implements Organizations {
        public static final ub CREATOR;
        private static final HashMap<String, Field<?, ?>> aIy;
        int EB;
        String Yv;
        String aFR;
        String aIW;
        final Set<Integer> aIz;
        String aJm;
        String aKb;
        boolean aKc;
        String mDescription;
        String mName;
        final int mVersionCode;

        static {
            CREATOR = new ub();
            aIy = new HashMap();
            aIy.put("department", Field.forString("department", 2));
            aIy.put("description", Field.forString("description", 3));
            aIy.put("endDate", Field.forString("endDate", 4));
            aIy.put("location", Field.forString("location", 5));
            aIy.put("name", Field.forString("name", 6));
            aIy.put("primary", Field.forBoolean("primary", 7));
            aIy.put("startDate", Field.forString("startDate", 8));
            aIy.put("title", Field.forString("title", 9));
            aIy.put("type", Field.withConverter("type", 10, new lh().i("work", 0).i("school", 1), false));
        }

        public f() {
            this.mVersionCode = 1;
            this.aIz = new HashSet();
        }

        f(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i2) {
            this.aIz = set;
            this.mVersionCode = i;
            this.aKb = str;
            this.mDescription = str2;
            this.aIW = str3;
            this.aFR = str4;
            this.mName = str5;
            this.aKc = z;
            this.aJm = str6;
            this.Yv = str7;
            this.EB = i2;
        }

        public int describeContents() {
            ub ubVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof f)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            f fVar = (f) obj;
            for (Field field : aIy.values()) {
                if (isFieldSet(field)) {
                    if (!fVar.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(fVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (fVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return rU();
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return aIy;
        }

        protected Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return this.aKb;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return this.mDescription;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    return this.aIW;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    return this.aFR;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    return this.mName;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    return Boolean.valueOf(this.aKc);
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    return this.aJm;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    return this.Yv;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    return Integer.valueOf(this.EB);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public int hashCode() {
            int i = 0;
            for (Field field : aIy.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        protected boolean isFieldSet(Field field) {
            return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        public f rU() {
            return this;
        }

        public void writeToParcel(Parcel out, int flags) {
            ub ubVar = CREATOR;
            ub.a(this, out, flags);
        }
    }

    public static final class g extends FastSafeParcelableJsonResponse implements PlacesLived {
        public static final uc CREATOR;
        private static final HashMap<String, Field<?, ?>> aIy;
        final Set<Integer> aIz;
        boolean aKc;
        String mValue;
        final int mVersionCode;

        static {
            CREATOR = new uc();
            aIy = new HashMap();
            aIy.put("primary", Field.forBoolean("primary", 2));
            aIy.put("value", Field.forString("value", 3));
        }

        public g() {
            this.mVersionCode = 1;
            this.aIz = new HashSet();
        }

        g(Set<Integer> set, int i, boolean z, String str) {
            this.aIz = set;
            this.mVersionCode = i;
            this.aKc = z;
            this.mValue = str;
        }

        public int describeContents() {
            uc ucVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof g)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            g gVar = (g) obj;
            for (Field field : aIy.values()) {
                if (isFieldSet(field)) {
                    if (!gVar.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(gVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (gVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return rV();
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return aIy;
        }

        protected Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return Boolean.valueOf(this.aKc);
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return this.mValue;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public int hashCode() {
            int i = 0;
            for (Field field : aIy.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        protected boolean isFieldSet(Field field) {
            return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        public g rV() {
            return this;
        }

        public void writeToParcel(Parcel out, int flags) {
            uc ucVar = CREATOR;
            uc.a(this, out, flags);
        }
    }

    public static final class h extends FastSafeParcelableJsonResponse implements Urls {
        public static final ud CREATOR;
        private static final HashMap<String, Field<?, ?>> aIy;
        int EB;
        final Set<Integer> aIz;
        private final int aKd;
        String ajQ;
        String mValue;
        final int mVersionCode;

        static {
            CREATOR = new ud();
            aIy = new HashMap();
            aIy.put("label", Field.forString("label", 5));
            aIy.put("type", Field.withConverter("type", 6, new lh().i("home", 0).i("work", 1).i("blog", 2).i("profile", 3).i("other", 4).i("otherProfile", 5).i("contributor", 6).i("website", 7), false));
            aIy.put("value", Field.forString("value", 4));
        }

        public h() {
            this.aKd = 4;
            this.mVersionCode = 1;
            this.aIz = new HashSet();
        }

        h(Set<Integer> set, int i, String str, int i2, String str2, int i3) {
            this.aKd = 4;
            this.aIz = set;
            this.mVersionCode = i;
            this.ajQ = str;
            this.EB = i2;
            this.mValue = str2;
        }

        public int describeContents() {
            ud udVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof h)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            h hVar = (h) obj;
            for (Field field : aIy.values()) {
                if (isFieldSet(field)) {
                    if (!hVar.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(hVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (hVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return rX();
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return aIy;
        }

        protected Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case R.styleable.WalletImFormEditText_required /*4*/:
                    return this.mValue;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    return this.ajQ;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    return Integer.valueOf(this.EB);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public int hashCode() {
            int i = 0;
            for (Field field : aIy.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        protected boolean isFieldSet(Field field) {
            return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        @Deprecated
        public int rW() {
            return 4;
        }

        public h rX() {
            return this;
        }

        public void writeToParcel(Parcel out, int flags) {
            ud udVar = CREATOR;
            ud.a(this, out, flags);
        }
    }

    static {
        CREATOR = new tu();
        aIy = new HashMap();
        aIy.put("aboutMe", Field.forString("aboutMe", 2));
        aIy.put("ageRange", Field.forConcreteType("ageRange", 3, a.class));
        aIy.put("birthday", Field.forString("birthday", 4));
        aIy.put("braggingRights", Field.forString("braggingRights", 5));
        aIy.put("circledByCount", Field.forInteger("circledByCount", 6));
        aIy.put("cover", Field.forConcreteType("cover", 7, b.class));
        aIy.put("currentLocation", Field.forString("currentLocation", 8));
        aIy.put("displayName", Field.forString("displayName", 9));
        aIy.put("gender", Field.withConverter("gender", 12, new lh().i("male", 0).i("female", 1).i("other", 2), false));
        aIy.put("id", Field.forString("id", 14));
        aIy.put("image", Field.forConcreteType("image", 15, c.class));
        aIy.put("isPlusUser", Field.forBoolean("isPlusUser", 16));
        aIy.put("language", Field.forString("language", 18));
        aIy.put("name", Field.forConcreteType("name", 19, d.class));
        aIy.put("nickname", Field.forString("nickname", 20));
        aIy.put("objectType", Field.withConverter("objectType", 21, new lh().i("person", 0).i("page", 1), false));
        aIy.put("organizations", Field.forConcreteTypeArray("organizations", 22, f.class));
        aIy.put("placesLived", Field.forConcreteTypeArray("placesLived", 23, g.class));
        aIy.put("plusOneCount", Field.forInteger("plusOneCount", 24));
        aIy.put("relationshipStatus", Field.withConverter("relationshipStatus", 25, new lh().i("single", 0).i("in_a_relationship", 1).i("engaged", 2).i("married", 3).i("its_complicated", 4).i("open_relationship", 5).i("widowed", 6).i("in_domestic_partnership", 7).i("in_civil_union", 8), false));
        aIy.put("tagline", Field.forString("tagline", 26));
        aIy.put("url", Field.forString("url", 27));
        aIy.put("urls", Field.forConcreteTypeArray("urls", 28, h.class));
        aIy.put("verified", Field.forBoolean("verified", 29));
    }

    public tt() {
        this.mVersionCode = 1;
        this.aIz = new HashSet();
    }

    tt(Set<Integer> set, int i, String str, a aVar, String str2, String str3, int i2, b bVar, String str4, String str5, int i3, String str6, c cVar, boolean z, String str7, d dVar, String str8, int i4, List<f> list, List<g> list2, int i5, int i6, String str9, String str10, List<h> list3, boolean z2) {
        this.aIz = set;
        this.mVersionCode = i;
        this.aJx = str;
        this.aJy = aVar;
        this.aJz = str2;
        this.aJA = str3;
        this.aJB = i2;
        this.aJC = bVar;
        this.aJD = str4;
        this.WN = str5;
        this.oy = i3;
        this.CB = str6;
        this.aJE = cVar;
        this.aJF = z;
        this.Oe = str7;
        this.aJG = dVar;
        this.aJH = str8;
        this.aJI = i4;
        this.aJJ = list;
        this.aJK = list2;
        this.aJL = i5;
        this.aJM = i6;
        this.aJN = str9;
        this.vf = str10;
        this.aJO = list3;
        this.aJP = z2;
    }

    public int describeContents() {
        tu tuVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof tt)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        tt ttVar = (tt) obj;
        for (Field field : aIy.values()) {
            if (isFieldSet(field)) {
                if (!ttVar.isFieldSet(field)) {
                    return false;
                }
                if (!getFieldValue(field).equals(ttVar.getFieldValue(field))) {
                    return false;
                }
            } else if (ttVar.isFieldSet(field)) {
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ Object freeze() {
        return rN();
    }

    public HashMap<String, Field<?, ?>> getFieldMappings() {
        return aIy;
    }

    protected Object getFieldValue(Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return this.aJx;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return this.aJy;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return this.aJz;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return this.aJA;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return Integer.valueOf(this.aJB);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return this.aJC;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return this.aJD;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return this.WN;
            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                return Integer.valueOf(this.oy);
            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                return this.CB;
            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                return this.aJE;
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                return Boolean.valueOf(this.aJF);
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                return this.Oe;
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                return this.aJG;
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                return this.aJH;
            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                return Integer.valueOf(this.aJI);
            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                return this.aJJ;
            case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                return this.aJK;
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                return Integer.valueOf(this.aJL);
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                return Integer.valueOf(this.aJM);
            case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                return this.aJN;
            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                return this.vf;
            case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                return this.aJO;
            case com.google.android.play.R.styleable.Theme_actionModeCutDrawable /*29*/:
                return Boolean.valueOf(this.aJP);
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
        }
    }

    public int hashCode() {
        int i = 0;
        for (Field field : aIy.values()) {
            int hashCode;
            if (isFieldSet(field)) {
                hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
            } else {
                hashCode = i;
            }
            i = hashCode;
        }
        return i;
    }

    protected boolean isFieldSet(Field field) {
        return this.aIz.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    public tt rN() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        tu tuVar = CREATOR;
        tu.a(this, out, flags);
    }
}
