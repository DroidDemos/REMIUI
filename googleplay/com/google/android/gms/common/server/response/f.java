package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.util.b;
import com.google.android.gms.common.util.i;
import com.google.android.gms.common.util.j;
import com.google.android.gms.internal.kn;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class f extends FastJsonResponse implements SafeParcelable {
    public static final g CREATOR;
    private final Parcel XD;
    private final int XE;
    private int XF;
    private int XG;
    private final FieldMappingDictionary Xb;
    private final String mClassName;
    private final int mVersionCode;

    static {
        CREATOR = new g();
    }

    f(int i, Parcel parcel, FieldMappingDictionary fieldMappingDictionary) {
        this.mVersionCode = i;
        this.XD = (Parcel) kn.k(parcel);
        this.XE = 2;
        this.Xb = fieldMappingDictionary;
        if (this.Xb == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.Xb.getRootClassName();
        }
        this.XF = 2;
    }

    private void a(StringBuilder stringBuilder, int i, Object obj) {
        switch (i) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                stringBuilder.append(obj);
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                stringBuilder.append("\"").append(i.bp(obj.toString())).append("\"");
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                stringBuilder.append("\"").append(b.e((byte[]) obj)).append("\"");
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                stringBuilder.append("\"").append(b.f((byte[]) obj));
                stringBuilder.append("\"");
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                j.a(stringBuilder, (HashMap) obj);
                return;
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void a(StringBuilder stringBuilder, Field<?, ?> field, Parcel parcel, int i) {
        switch (field.getTypeOut()) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                b(stringBuilder, (Field) field, getOriginalValue(field, Integer.valueOf(a.g(parcel, i))));
                return;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                b(stringBuilder, (Field) field, getOriginalValue(field, a.k(parcel, i)));
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                b(stringBuilder, (Field) field, getOriginalValue(field, Long.valueOf(a.i(parcel, i))));
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                b(stringBuilder, (Field) field, getOriginalValue(field, Float.valueOf(a.l(parcel, i))));
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
                b(stringBuilder, (Field) field, getOriginalValue(field, Double.valueOf(a.m(parcel, i))));
                return;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                b(stringBuilder, (Field) field, getOriginalValue(field, a.o(parcel, i)));
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                b(stringBuilder, (Field) field, getOriginalValue(field, Boolean.valueOf(a.c(parcel, i))));
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                b(stringBuilder, (Field) field, getOriginalValue(field, a.p(parcel, i)));
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                b(stringBuilder, (Field) field, getOriginalValue(field, a.s(parcel, i)));
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                b(stringBuilder, (Field) field, getOriginalValue(field, i(a.r(parcel, i))));
                return;
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + field.getTypeOut());
        }
    }

    private void a(StringBuilder stringBuilder, String str, Field<?, ?> field, Parcel parcel, int i) {
        stringBuilder.append("\"").append(str).append("\":");
        if (field.hasConverter()) {
            a(stringBuilder, field, parcel, i);
        } else {
            b(stringBuilder, field, parcel, i);
        }
    }

    private void a(StringBuilder stringBuilder, HashMap<String, Field<?, ?>> hashMap, Parcel parcel) {
        HashMap d = d(hashMap);
        stringBuilder.append('{');
        int bT = a.bT(parcel);
        Object obj = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            Entry entry = (Entry) d.get(Integer.valueOf(a.dk(bS)));
            if (entry != null) {
                if (obj != null) {
                    stringBuilder.append(",");
                }
                a(stringBuilder, (String) entry.getKey(), (Field) entry.getValue(), parcel, bS);
                obj = 1;
            }
        }
        if (parcel.dataPosition() != bT) {
            throw new a.a("Overread allowed size end=" + bT, parcel);
        }
        stringBuilder.append('}');
    }

    private void b(StringBuilder stringBuilder, Field<?, ?> field, Parcel parcel, int i) {
        if (field.isTypeOutArray()) {
            stringBuilder.append("[");
            switch (field.getTypeOut()) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    com.google.android.gms.common.util.a.a(stringBuilder, a.v(parcel, i));
                    break;
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    com.google.android.gms.common.util.a.a(stringBuilder, a.x(parcel, i));
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    com.google.android.gms.common.util.a.a(stringBuilder, a.w(parcel, i));
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    com.google.android.gms.common.util.a.a(stringBuilder, a.y(parcel, i));
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    com.google.android.gms.common.util.a.a(stringBuilder, a.z(parcel, i));
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    com.google.android.gms.common.util.a.a(stringBuilder, a.A(parcel, i));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    com.google.android.gms.common.util.a.a(stringBuilder, a.u(parcel, i));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    com.google.android.gms.common.util.a.a(stringBuilder, a.B(parcel, i));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    Parcel[] G = a.G(parcel, i);
                    int length = G.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            stringBuilder.append(",");
                        }
                        G[i2].setDataPosition(0);
                        a(stringBuilder, field.getConcreteTypeFieldMappingFromDictionary(), G[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            stringBuilder.append("]");
            return;
        }
        switch (field.getTypeOut()) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                stringBuilder.append(a.g(parcel, i));
                return;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                stringBuilder.append(a.k(parcel, i));
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                stringBuilder.append(a.i(parcel, i));
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                stringBuilder.append(a.l(parcel, i));
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
                stringBuilder.append(a.m(parcel, i));
                return;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                stringBuilder.append(a.o(parcel, i));
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                stringBuilder.append(a.c(parcel, i));
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                stringBuilder.append("\"").append(i.bp(a.p(parcel, i))).append("\"");
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                stringBuilder.append("\"").append(b.e(a.s(parcel, i))).append("\"");
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                stringBuilder.append("\"").append(b.f(a.s(parcel, i)));
                stringBuilder.append("\"");
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                Bundle r = a.r(parcel, i);
                Set<String> keySet = r.keySet();
                keySet.size();
                stringBuilder.append("{");
                int i3 = 1;
                for (String str : keySet) {
                    if (i3 == 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append("\"").append(str).append("\"");
                    stringBuilder.append(":");
                    stringBuilder.append("\"").append(i.bp(r.getString(str))).append("\"");
                    i3 = 0;
                }
                stringBuilder.append("}");
                return;
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                Parcel F = a.F(parcel, i);
                F.setDataPosition(0);
                a(stringBuilder, field.getConcreteTypeFieldMappingFromDictionary(), F);
                return;
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void b(StringBuilder stringBuilder, Field<?, ?> field, Object obj) {
        if (field.isTypeInArray()) {
            b(stringBuilder, (Field) field, (ArrayList) obj);
        } else {
            a(stringBuilder, field.getTypeIn(), obj);
        }
    }

    private void b(StringBuilder stringBuilder, Field<?, ?> field, ArrayList<?> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            a(stringBuilder, field.getTypeIn(), arrayList.get(i));
        }
        stringBuilder.append("]");
    }

    private static HashMap<Integer, Entry<String, Field<?, ?>>> d(HashMap<String, Field<?, ?>> hashMap) {
        HashMap<Integer, Entry<String, Field<?, ?>>> hashMap2 = new HashMap();
        for (Entry entry : hashMap.entrySet()) {
            hashMap2.put(Integer.valueOf(((Field) entry.getValue()).getSafeParcelableFieldId()), entry);
        }
        return hashMap2;
    }

    public static HashMap<String, String> i(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public int describeContents() {
        g gVar = CREATOR;
        return 0;
    }

    public HashMap<String, Field<?, ?>> getFieldMappings() {
        return this.Xb == null ? null : this.Xb.getFieldMapping(this.mClassName);
    }

    protected Object getValueObject(String key) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    protected boolean isPrimitiveFieldSet(String outputField) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public Parcel jg() {
        switch (this.XF) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                this.XG = com.google.android.gms.common.internal.safeparcel.b.bU(this.XD);
                com.google.android.gms.common.internal.safeparcel.b.J(this.XD, this.XG);
                this.XF = 2;
                break;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                com.google.android.gms.common.internal.safeparcel.b.J(this.XD, this.XG);
                this.XF = 2;
                break;
        }
        return this.XD;
    }

    FieldMappingDictionary jh() {
        switch (this.XE) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return null;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return this.Xb;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return this.Xb;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.XE);
        }
    }

    public String toString() {
        kn.b(this.Xb, (Object) "Cannot convert to JSON on client side.");
        Parcel jg = jg();
        jg.setDataPosition(0);
        StringBuilder stringBuilder = new StringBuilder(100);
        a(stringBuilder, this.Xb.getFieldMapping(this.mClassName), jg);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        g gVar = CREATOR;
        g.a(this, out, flags);
    }
}
