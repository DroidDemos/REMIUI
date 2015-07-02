package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public final class tq extends FastSafeParcelableJsonResponse implements Moment {
    public static final tr CREATOR;
    private static final HashMap<String, Field<?, ?>> aIy;
    String CB;
    final Set<Integer> aIz;
    String aJm;
    to aJu;
    to aJv;
    final int mVersionCode;
    String vc;

    static {
        CREATOR = new tr();
        aIy = new HashMap();
        aIy.put("id", Field.forString("id", 2));
        aIy.put("result", Field.forConcreteType("result", 4, to.class));
        aIy.put("startDate", Field.forString("startDate", 5));
        aIy.put("target", Field.forConcreteType("target", 6, to.class));
        aIy.put("type", Field.forString("type", 7));
    }

    public tq() {
        this.mVersionCode = 1;
        this.aIz = new HashSet();
    }

    tq(Set<Integer> set, int i, String str, to toVar, String str2, to toVar2, String str3) {
        this.aIz = set;
        this.mVersionCode = i;
        this.CB = str;
        this.aJu = toVar;
        this.aJm = str2;
        this.aJv = toVar2;
        this.vc = str3;
    }

    public int describeContents() {
        tr trVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof tq)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        tq tqVar = (tq) obj;
        for (Field field : aIy.values()) {
            if (isFieldSet(field)) {
                if (!tqVar.isFieldSet(field)) {
                    return false;
                }
                if (!getFieldValue(field).equals(tqVar.getFieldValue(field))) {
                    return false;
                }
            } else if (tqVar.isFieldSet(field)) {
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ Object freeze() {
        return rL();
    }

    public HashMap<String, Field<?, ?>> getFieldMappings() {
        return aIy;
    }

    protected Object getFieldValue(Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return this.CB;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return this.aJu;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return this.aJm;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return this.aJv;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return this.vc;
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

    public tq rL() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        tr trVar = CREATOR;
        tr.a(this, out, flags);
    }
}
