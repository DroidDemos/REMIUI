package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.b;
import com.google.android.gms.common.util.i;
import com.google.android.gms.common.util.j;
import com.google.android.gms.internal.kn;
import com.google.android.gms.internal.lf;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FastJsonResponse {

    public static class Field<I, O> implements SafeParcelable {
        public static final a CREATOR;
        private FieldMappingDictionary Xb;
        private FieldConverter<I, O> Xc;
        protected final Class<? extends FastJsonResponse> mConcreteType;
        protected final String mConcreteTypeName;
        protected final String mOutputFieldName;
        protected final int mSafeParcelableFieldId;
        protected final int mTypeIn;
        protected final boolean mTypeInArray;
        protected final int mTypeOut;
        protected final boolean mTypeOutArray;
        private final int mVersionCode;

        static {
            CREATOR = new a();
        }

        Field(int versionCode, int typeIn, boolean typeInArray, int typeOut, boolean typeOutArray, String outputFieldName, int safeParcelableFieldId, String concreteTypeName, lf wrappedConverter) {
            this.mVersionCode = versionCode;
            this.mTypeIn = typeIn;
            this.mTypeInArray = typeInArray;
            this.mTypeOut = typeOut;
            this.mTypeOutArray = typeOutArray;
            this.mOutputFieldName = outputFieldName;
            this.mSafeParcelableFieldId = safeParcelableFieldId;
            if (concreteTypeName == null) {
                this.mConcreteType = null;
                this.mConcreteTypeName = null;
            } else {
                this.mConcreteType = f.class;
                this.mConcreteTypeName = concreteTypeName;
            }
            if (wrappedConverter == null) {
                this.Xc = null;
            } else {
                this.Xc = wrappedConverter.ja();
            }
        }

        protected Field(int typeIn, boolean typeInArray, int typeOut, boolean typeOutArray, String outputFieldName, int safeParcelableFieldId, Class<? extends FastJsonResponse> concreteType, FieldConverter<I, O> converter) {
            this.mVersionCode = 1;
            this.mTypeIn = typeIn;
            this.mTypeInArray = typeInArray;
            this.mTypeOut = typeOut;
            this.mTypeOutArray = typeOutArray;
            this.mOutputFieldName = outputFieldName;
            this.mSafeParcelableFieldId = safeParcelableFieldId;
            this.mConcreteType = concreteType;
            if (concreteType == null) {
                this.mConcreteTypeName = null;
            } else {
                this.mConcreteTypeName = concreteType.getCanonicalName();
            }
            this.Xc = converter;
        }

        public static Field<Boolean, Boolean> forBoolean(String outputFieldName, int safeParcelableId) {
            return new Field(6, false, 6, false, outputFieldName, safeParcelableId, null, null);
        }

        public static <T extends FastJsonResponse> Field<T, T> forConcreteType(String fieldName, int safeParcelableId, Class<T> type) {
            return new Field(11, false, 11, false, fieldName, safeParcelableId, type, null);
        }

        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(String fieldName, int safeParcelableId, Class<T> type) {
            return new Field(11, true, 11, true, fieldName, safeParcelableId, type, null);
        }

        public static Field<Double, Double> forDouble(String outputFieldName, int safeParcelableId) {
            return new Field(4, false, 4, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<Integer, Integer> forInteger(String outputFieldName, int safeParcelableId) {
            return new Field(0, false, 0, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<String, String> forString(String outputFieldName, int safeParcelableId) {
            return new Field(7, false, 7, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<ArrayList<String>, ArrayList<String>> forStrings(String outputFieldName, int safeParcelableId) {
            return new Field(7, true, 7, true, outputFieldName, safeParcelableId, null, null);
        }

        public static Field withConverter(String outputFieldName, int safeParcelableId, FieldConverter<?, ?> converter, boolean inputArrayType) {
            return new Field(converter.getTypeIn(), inputArrayType, converter.getTypeOut(), false, outputFieldName, safeParcelableId, null, converter);
        }

        public I convertBack(O output) {
            return this.Xc.convertBack(output);
        }

        public int describeContents() {
            a aVar = CREATOR;
            return 0;
        }

        public Class<? extends FastJsonResponse> getConcreteType() {
            return this.mConcreteType;
        }

        public HashMap<String, Field<?, ?>> getConcreteTypeFieldMappingFromDictionary() {
            kn.k(this.mConcreteTypeName);
            kn.k(this.Xb);
            return this.Xb.getFieldMapping(this.mConcreteTypeName);
        }

        public String getOutputFieldName() {
            return this.mOutputFieldName;
        }

        public int getSafeParcelableFieldId() {
            return this.mSafeParcelableFieldId;
        }

        public int getTypeIn() {
            return this.mTypeIn;
        }

        public int getTypeOut() {
            return this.mTypeOut;
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public boolean hasConverter() {
            return this.Xc != null;
        }

        public boolean isTypeInArray() {
            return this.mTypeInArray;
        }

        public boolean isTypeOutArray() {
            return this.mTypeOutArray;
        }

        String jc() {
            return this.mConcreteTypeName == null ? null : this.mConcreteTypeName;
        }

        lf jd() {
            return this.Xc == null ? null : lf.a(this.Xc);
        }

        public void setFieldMappingDictionary(FieldMappingDictionary dictionary) {
            this.Xb = dictionary;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Field\n");
            stringBuilder.append("            versionCode=").append(this.mVersionCode).append('\n');
            stringBuilder.append("                 typeIn=").append(this.mTypeIn).append('\n');
            stringBuilder.append("            typeInArray=").append(this.mTypeInArray).append('\n');
            stringBuilder.append("                typeOut=").append(this.mTypeOut).append('\n');
            stringBuilder.append("           typeOutArray=").append(this.mTypeOutArray).append('\n');
            stringBuilder.append("        outputFieldName=").append(this.mOutputFieldName).append('\n');
            stringBuilder.append("      safeParcelFieldId=").append(this.mSafeParcelableFieldId).append('\n');
            stringBuilder.append("       concreteTypeName=").append(jc()).append('\n');
            if (getConcreteType() != null) {
                stringBuilder.append("     concreteType.class=").append(getConcreteType().getCanonicalName()).append('\n');
            }
            stringBuilder.append("          converterName=").append(this.Xc == null ? "null" : this.Xc.getClass().getCanonicalName()).append('\n');
            return stringBuilder.toString();
        }

        public void writeToParcel(Parcel out, int flags) {
            a aVar = CREATOR;
            a.a(this, out, flags);
        }
    }

    public interface FieldConverter<I, O> {
        I convertBack(O o);

        int getTypeIn();

        int getTypeOut();
    }

    private void a(StringBuilder stringBuilder, Field field, Object obj) {
        if (field.getTypeIn() == 11) {
            stringBuilder.append(((FastJsonResponse) field.getConcreteType().cast(obj)).toString());
        } else if (field.getTypeIn() == 7) {
            stringBuilder.append("\"");
            stringBuilder.append(i.bp((String) obj));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(obj);
        }
    }

    private void a(StringBuilder stringBuilder, Field field, ArrayList<Object> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                a(stringBuilder, field, obj);
            }
        }
        stringBuilder.append("]");
    }

    public HashMap<String, Object> getConcreteTypeArrays() {
        return null;
    }

    public HashMap<String, Object> getConcreteTypes() {
        return null;
    }

    public abstract HashMap<String, Field<?, ?>> getFieldMappings();

    protected Object getFieldValue(Field field) {
        String outputFieldName = field.getOutputFieldName();
        if (field.getConcreteType() == null) {
            return getValueObject(field.getOutputFieldName());
        }
        kn.a(getValueObject(field.getOutputFieldName()) == null, "Concrete field shouldn't be value object: %s", field.getOutputFieldName());
        Map concreteTypeArrays = field.isTypeOutArray() ? getConcreteTypeArrays() : getConcreteTypes();
        if (concreteTypeArrays != null) {
            return concreteTypeArrays.get(outputFieldName);
        }
        try {
            return getClass().getMethod("get" + Character.toUpperCase(outputFieldName.charAt(0)) + outputFieldName.substring(1), new Class[0]).invoke(this, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    protected <O, I> I getOriginalValue(Field<I, O> field, Object convertedValue) {
        return field.Xc != null ? field.convertBack(convertedValue) : convertedValue;
    }

    protected abstract Object getValueObject(String str);

    protected boolean isConcreteTypeArrayFieldSet(String outputField) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    protected boolean isConcreteTypeFieldSet(String outputField) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    protected boolean isFieldSet(Field field) {
        if (field.getTypeOut() == 11) {
            return field.isTypeOutArray() ? isConcreteTypeArrayFieldSet(field.getOutputFieldName()) : isConcreteTypeFieldSet(field.getOutputFieldName());
        } else {
            return isPrimitiveFieldSet(field.getOutputFieldName());
        }
    }

    protected abstract boolean isPrimitiveFieldSet(String str);

    public String toString() {
        HashMap fieldMappings = getFieldMappings();
        StringBuilder stringBuilder = new StringBuilder(100);
        for (String str : fieldMappings.keySet()) {
            Field field = (Field) fieldMappings.get(str);
            if (isFieldSet(field)) {
                Object originalValue = getOriginalValue(field, getFieldValue(field));
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("{");
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\"").append(str).append("\":");
                if (originalValue != null) {
                    switch (field.getTypeOut()) {
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            stringBuilder.append("\"").append(b.e((byte[]) originalValue)).append("\"");
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            stringBuilder.append("\"").append(b.f((byte[]) originalValue)).append("\"");
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            j.a(stringBuilder, (HashMap) originalValue);
                            break;
                        default:
                            if (!field.isTypeInArray()) {
                                a(stringBuilder, field, originalValue);
                                break;
                            }
                            a(stringBuilder, field, (ArrayList) originalValue);
                            break;
                    }
                }
                stringBuilder.append("null");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("}");
        } else {
            stringBuilder.append("{}");
        }
        return stringBuilder.toString();
    }
}
