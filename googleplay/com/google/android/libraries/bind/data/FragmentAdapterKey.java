package com.google.android.libraries.bind.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.libraries.bind.util.ParcelUtil;
import com.google.android.libraries.bind.util.Util;

public class FragmentAdapterKey implements Parcelable {
    public static final Creator<FragmentAdapterKey> CREATOR;
    public final Object key;

    public FragmentAdapterKey(Object key) {
        this.key = key;
    }

    public boolean equals(Object other) {
        if (!(other instanceof FragmentAdapterKey)) {
            return false;
        }
        return Util.objectsEqual(this.key, ((FragmentAdapterKey) other).key);
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("key: %s", new Object[]{this.key});
    }

    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtil.writeObjectToParcel(this.key, dest, flags);
    }

    static {
        CREATOR = new Creator<FragmentAdapterKey>() {
            public FragmentAdapterKey createFromParcel(Parcel source) {
                return new FragmentAdapterKey(ParcelUtil.readObjectFromParcel(source, FragmentAdapterKey.class.getClassLoader()));
            }

            public FragmentAdapterKey[] newArray(int size) {
                return new FragmentAdapterKey[size];
            }
        };
    }
}
