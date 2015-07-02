package com.google.android.libraries.bind.data;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.libraries.bind.util.Util;

public final class Data implements Parcelable {
    public static final Creator<Data> CREATOR;
    private boolean frozen;
    private SparseArray<Object> values;

    public boolean equalsField(Data other, int key) {
        return Util.objectsEqual(get(key), other.get(key));
    }

    public Data copy() {
        return new Data(this.values);
    }

    public Data() {
        this(8);
    }

    public Data(int size) {
        this.values = new SparseArray(size);
    }

    public Data(SparseArray<Object> from) {
        if (VERSION.SDK_INT >= 14) {
            this.values = from.clone();
            return;
        }
        this.values = new SparseArray(from.size());
        putAll(from);
    }

    public boolean equals(Object object) {
        if (object instanceof Data) {
            return this.values.equals(((Data) object).values);
        }
        return false;
    }

    public int hashCode() {
        return this.values.hashCode();
    }

    public void freeze() {
        this.frozen = true;
    }

    private void putAll(SparseArray<Object> otherValues) {
        for (int i = 0; i < otherValues.size(); i++) {
            this.values.put(otherValues.keyAt(i), otherValues.valueAt(i));
        }
    }

    public boolean containsKey(int key) {
        return this.values.get(key) != null;
    }

    public <T> T get(int key) {
        Object value = this.values.get(key);
        if (value instanceof DataProperty) {
            return ((DataProperty) value).apply(this);
        }
        return value;
    }

    public String getAsString(int key) {
        Object value = get(key);
        return value != null ? value.toString() : null;
    }

    public Integer getAsInteger(int key) {
        Object value = get(key);
        return value != null ? Integer.valueOf(((Number) value).intValue()) : null;
    }

    public boolean getAsBoolean(int key, boolean defaultValue) {
        Object value = get(key);
        return value == null ? defaultValue : ((Boolean) value).booleanValue();
    }

    static {
        CREATOR = new Creator<Data>() {
            public Data createFromParcel(Parcel in) {
                return new Data(in.readSparseArray(null));
            }

            public Data[] newArray(int size) {
                return new Data[size];
            }
        };
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeSparseArray(this.values);
    }

    public static String keyName(int key) {
        return Util.getResourceName(key);
    }

    public String toString() {
        if (this.values.size() == 0) {
            return "Data is empty";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.values.size(); i++) {
            int key = this.values.keyAt(i);
            Object value = this.values.valueAt(i);
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(keyName(key) + "=" + value);
        }
        return sb.toString();
    }
}
